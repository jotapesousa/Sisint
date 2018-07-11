package br.pcrn.sisint.dominio;


import javax.enterprise.context.SessionScoped;


@SessionScoped
public class UsuarioLogado extends Sessao {
    private Usuario usuario;

    public void loga(Usuario usuario){
        this.usuario = usuario;
    }

    public boolean isLogado() {
        return this.usuario != null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void desloga(){
        this.usuario = null;
//        destruir();
    }

    public boolean isAdmin(){
        if(usuario != null) {
            if(usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
                return true;
            }
        }
        return false;
    }

    public boolean isTecnico(){
        if(usuario != null) {
            if(usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR) || usuario.getTipoUsuario().equals(TipoUsuario.TECNICO)){
                return true;
            }
        }
        return false;
    }

    public boolean isCliente(){
        if(usuario != null) {
            if(usuario.getTipoUsuario().equals(TipoUsuario.CLIENTE)){
                return true;
            }
        }
        return false;
    }
}
