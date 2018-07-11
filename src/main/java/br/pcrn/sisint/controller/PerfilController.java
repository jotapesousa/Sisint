package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.pcrn.sisint.dominio.Usuario;
import br.pcrn.sisint.dominio.UsuarioLogado;
import br.pcrn.sisint.dao.UsuarioDao;
import javax.inject.Inject;

@Controller
public class PerfilController extends Controlador{

    UsuarioDao usuarioDao;
    UsuarioLogado usuarioLogado;

    @Deprecated
    protected  PerfilController(){
        this(null,null,null);
    }

    @Inject
    public PerfilController(Result resultado, UsuarioLogado usuarioLogado, UsuarioDao usuarioDao) {
        super(resultado);
        this.usuarioLogado = usuarioLogado;
        this.usuarioDao = usuarioDao;
    }

    public void form(Long id) {
        Usuario usuario = usuarioDao.buscarPorId(id);
        resultado.include("usuario", usuario);
    }
}
