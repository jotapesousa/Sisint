package br.pcrn.sisint.negocio;

import br.pcrn.sisint.dao.UsuarioDao;
import br.pcrn.sisint.dominio.Usuario;
import br.pcrn.sisint.util.Criptografia;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by samue on 20/12/2017.
 */
public class LoginNegocio {

    @Inject
    private UsuarioDao usuarioDao;

    public Usuario validarUsuario(Usuario usuario) {
        if(usuario != null) {
            Optional<Usuario> usuarioBanco = usuarioDao.buscarPorLogin(usuario.getLogin());
            if(usuarioBanco.isPresent()) {
                if(compararSenha(usuario.getSenha(), usuarioBanco.get().getSenha())){
                    return usuarioBanco.get();
                }
            }
        }
        return null;
    }

    private boolean compararSenha(String senha, String senhaBanco) {
        if(Criptografia.criptografar(senha).equals(senhaBanco)) {
            return true;
        }
        return false;
    }
}
