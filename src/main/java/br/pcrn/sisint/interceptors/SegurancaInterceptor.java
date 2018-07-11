package br.pcrn.sisint.interceptors;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.view.Results;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.controller.LoginController;
import br.pcrn.sisint.dominio.TipoUsuario;
import br.pcrn.sisint.dominio.UsuarioLogado;

import javax.inject.Inject;
import java.lang.reflect.Method;
import java.util.*;

@Intercepts(after = AutenticacaoInterceptor.class)
public class SegurancaInterceptor {

    private Result resultado;
    private UsuarioLogado usuarioLogado;

    @Inject
    public SegurancaInterceptor(Result resultado, UsuarioLogado usuarioLogado) {
        this.resultado = resultado;
        this.usuarioLogado = usuarioLogado;
    }

    @Deprecated SegurancaInterceptor(){}

    @Accepts
    public boolean verificarSegurança(ControllerMethod method){
        return !method.getController().getType().equals(LoginController.class);
    }

    private boolean verificarPermissao(ControllerMethod method){
        return method.getController().getType().getAnnotation(Seguranca.class) != null
                || method.containsAnnotation(Seguranca.class);
    }

    @AroundCall
    public void autoriza(SimpleInterceptorStack stack, ControllerMethod method) {
        acesso(method);
        stack.next();
    }

    /**
     * Verifica as permissões através da anotação @Seguranca
     * @param method
     * @return
     */
    public boolean acesso(ControllerMethod method){
        Seguranca anotacaoMethod = method.getMethod().getAnnotation(Seguranca.class);
        Seguranca anotacaoClasse = method.getController().getType().getAnnotation(Seguranca.class);
        if(possuiAnotacao(anotacaoMethod, anotacaoClasse)) {
            if(anotacaoMethod != null) {
                if(usuarioLogado.getUsuario().getTipoUsuario().equals(anotacaoMethod.tipoUsuario())
                        || usuarioLogado.getUsuario().getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
                    return true;
                } else {
                    resultado.use(Results.http()).sendError(403, "Usuário não autorizado");
                }
            } else {
                if(anotacaoClasse != null) {
                    if(usuarioLogado.getUsuario().getTipoUsuario().equals(anotacaoClasse.tipoUsuario())
                            || usuarioLogado.getUsuario().getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
                        return true;
                    } else {
                        resultado.use(Results.http()).sendError(403, "Usuário não autorizado");
                    }
                }
            }
        }

        return true;
    }

    public boolean acessoMethod(Seguranca anotacao){
        boolean permitido = anotacao.tipoUsuario().equals(usuarioLogado.getUsuario().getTipoUsuario());
        return permitido;
    }

    public boolean possuiAnotacao(Seguranca anotacaoMethod, Seguranca anotacaoClasse) {
        return (anotacaoMethod != null || anotacaoClasse != null);
    }
    public boolean apenasMetodoAnotado(Seguranca anotacaoMethod, Seguranca anotacaoClasse) {
        return (anotacaoMethod != null && anotacaoClasse == null);
    }
}
