package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.UsuarioDao;
import br.pcrn.sisint.dominio.TipoUsuario;
import br.pcrn.sisint.dominio.Usuario;
import br.pcrn.sisint.util.Criptografia;
import br.pcrn.sisint.util.OpcaoSelect;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by samue on 09/09/2017.
 */

@Controller
public class UsuariosController extends ControladorSisInt<Usuario> {

    UsuarioDao usuarioDao;

    /**
     * @deprecated CDI eyes only
     */
    protected UsuariosController() {
        this(null, null);
    }

    @Inject
    public UsuariosController(UsuarioDao usuarioDao, Result resultado) {
        super(resultado);
        this.usuarioDao = usuarioDao;
    }

    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public void form(){
        this.resultado.include("tipoUsuario", OpcaoSelect.toListaOpcoes(TipoUsuario.values()));
    }

    @Post("/usuarios")
    @Transacional
    public void salvar(Usuario usuario){
        usuario.setDataCadastro(LocalDate.now());
        usuario.setDeletado(false);
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        this.usuarioDao.salvar(usuario);
        resultado.include("mensagem", new SimpleMessage("success","mensagem.salvar.sucesso"));
        resultado.redirectTo(UsuariosController.class).lista();
    }

    @Transacional
    public void atualizar(Usuario usuario){
        usuario.setDataCadastro(LocalDate.now());
        this.usuarioDao.salvar(usuario);
        resultado.include("mensagem", new SimpleMessage("success","mensagem.salvar.sucesso"));
        resultado.redirectTo(InicioController.class).index();
    }

    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public void lista() {
        resultado.include("usuarios", usuarioDao.listar());
    }

    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public void editar(Long id) {
        Usuario usuario = usuarioDao.buscarPorId(id);
        this.resultado.include("tipoUsuario", OpcaoSelect.toListaOpcoes(TipoUsuario.values()));
        this.resultado.include("usuario", usuario);
        resultado.of(this).form();
    }

    @Transacional
    public void trocarSenha(Usuario usuario) {
        Usuario usuarioBanco = usuarioDao.buscarPorId(usuario.getId());
        if(Criptografia.criptografar(usuario.getSenha()).equals(usuarioBanco.getSenha()) && usuario.getNovaSenha().equals(usuario.getConfirmaSenha())) {
            usuarioBanco.setSenha(Criptografia.criptografar(usuario.getNovaSenha()));
            resultado.include("mensagem", new SimpleMessage("success","mensagem.salvar.sucesso"));
            resultado.redirectTo(InicioController.class).index();
        } else {
            resultado.include("mensagem", new SimpleMessage("error","mensagem.dadoIncorreto"));
            resultado.redirectTo(PerfilController.class).form(usuario.getId());
        }

    }

    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public void remover(Long id) {
        try {
            Usuario usuario = usuarioDao.buscarPorId(id);
            usuario.setDeletado(true);
            usuarioDao.salvar(usuario);
            resultado.include("mensagem", new SimpleMessage("success","mensagem.usuario.remover.sucesso"));
            resultado.of(this).lista();
        } catch (Exception e) {
            resultado.include("mensagem", new SimpleMessage("error", "mensagem.ususario.remover.error"));
            resultado.of(this).lista();
        }
    }


    private String criptografarSenha(String senha) {
        return Criptografia.criptografar(senha);
    }



}
