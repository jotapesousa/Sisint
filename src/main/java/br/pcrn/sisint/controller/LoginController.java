package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.pcrn.sisint.dao.UsuarioDao;
import br.pcrn.sisint.dominio.Usuario;
import br.pcrn.sisint.dominio.UsuarioLogado;
import br.pcrn.sisint.negocio.LoginNegocio;

import javax.inject.Inject;
@Path("/login")
@Controller
public class LoginController extends Controlador {

    private UsuarioLogado usuarioLogado;
    private UsuarioDao usuarioDao;
    private LoginNegocio loginNegocio;
    @Deprecated
    LoginController(){
        this(null,null,null, null);
    }

    @Inject
    public LoginController(UsuarioLogado usuarioLogado, Result resultado, UsuarioDao usuarioDao, LoginNegocio loginNegocio) {
        super(resultado);
        this.usuarioLogado = usuarioLogado;
        this.usuarioDao = usuarioDao;
        this.loginNegocio = loginNegocio;
    }

    @Post("/login")
    public void login(Usuario usuario){
        Usuario usuarioLogin = loginNegocio.validarUsuario(usuario);
        if(usuarioLogin != null){
            usuarioLogado.setUsuario(usuarioLogin);
            resultado.redirectTo(InicioController.class).index();
        } else{
            resultado.include("mensagem", new SimpleMessage("error","login.dadoIncorreto"));
            resultado.of(this).form();
        }
    }

    @Path("/")
    public void form(){

    }

    @Get("/logout")
    public void logout(){
        this.usuarioLogado.desloga();
        this.resultado.redirectTo(ServicosController.class).lista();
    }
}
