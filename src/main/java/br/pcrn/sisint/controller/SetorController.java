package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dominio.Setor;
import br.pcrn.sisint.dominio.TipoUsuario;
import com.google.gson.JsonObject;

import javax.inject.Inject;

@Controller
@Seguranca(tipoUsuario = TipoUsuario.TECNICO)
public class SetorController extends ControladorSisInt<Setor> {

    private SetorDao setorDao;

    @Deprecated
    public SetorController(){
        this(null,null);
    }

    @Inject
    public SetorController(Result resultado, SetorDao setorDao) {
        super(resultado);
        this.setorDao = setorDao;
    }

    public void lista(){
        resultado.include("setores", setorDao.listar());
    }

    public void form(){}

    @Post("/setor")
    @Transacional
    public void salvar(Setor setor){
        this.setorDao.salvar(setor);
        resultado.redirectTo(this).lista();
    }

    public void editar(Long id){
        Setor setor = setorDao.buscarPorId(id);
        resultado.include("setor",setor);
        resultado.of(this).form();
    }

    @Transacional
    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public void remover(Long id) {
        Setor setor = setorDao.buscarPorId(id);
        setor.setDeletado(true);
        this.setorDao.salvar(setor);
        resultado.redirectTo(this).lista();
    }
}
