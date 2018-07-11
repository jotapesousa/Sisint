package br.pcrn.sisint.validadores;

import br.pcrn.sisint.dao.UsuarioDao;
import br.pcrn.sisint.dominio.Usuario;

import javax.inject.Inject;

public class UsuarioValidador {

    private UsuarioDao usuarioDao;

    @Inject
    public UsuarioValidador(UsuarioDao usuarioDao){
        this.usuarioDao = usuarioDao;
    }

    /**
     * Verifica se a senha é válida retorna true se a senha for válida
     * @param usuario
     * @return boolean
     */
    public boolean verificarSenha(Usuario usuario){
        Usuario user = usuarioDao.buscarPorLogin(usuario.getLogin()).get();
        if(user.getSenha() == usuario.getSenha()){
            return true;
        }
        return false;
    }

    /**
     * Verifica se o usuario cadastrado e retorna true se o usuario ja estiver cadastrado
     * @param usuario
     * @return boolean
     */
    public boolean verificaUsuarioCadastrado(Usuario usuario){
        Usuario user = usuarioDao.buscarPorLogin(usuario.getLogin()).get();
        if(user != null){
            return true;
        }

        return false;

    }

}
