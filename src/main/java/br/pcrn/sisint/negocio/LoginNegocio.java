package br.pcrn.sisint.negocio;

import br.pcrn.sisint.dao.UsuarioDao;
import br.pcrn.sisint.dominio.Usuario;
import br.pcrn.sisint.util.Criptografia;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Hashtable;
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

//    public Usuario buscarUsuario(Usuario usuario) {
//        if(usuario != null) {
//            Optional<Usuario> usuarioBanco = usuarioDao.buscarPorLogin(usuario.getLogin());
//            if (usuarioBanco.isPresent()) {
//                return usuarioBanco.get();
//            } else {
//                usuario.setTipoUsuario(TipoUsuario.COMUM);
//                usuario.setDeletado(false);
//                usuario.setDataCadastro(LocalDateTime.now());
//                usuario.setPermissao(false);
//                usuarioDao.salvar(usuario);
//                System.out.println("BUSCOU E NAO ACHOUs");
//            }
//        }
//        return usuario;
//    }

//    public Usuario ldapAuth(Usuario usuario) throws NamingException, UnknownHostException, IOException {
//
//        Hashtable<String, String> env;
//        LdapContext ctx;
//        Control[] connectionControls;
//
//        env = new Hashtable<String, String>();
//        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//        env.put(Context.PROVIDER_URL, "ldap://10.9.0.4:389");
//        env.put(Context.SECURITY_AUTHENTICATION, "simple");
////        env.put(Context.SECURITY_PRINCIPAL, "CN=SEI TESTE,CN=Users,OU=CONTAS NOVAS,DC=pcrn,DC=local");
////        env.put(Context.SECURITY_CREDENTIALS, "pcrn@2018");
//        env.put(Context.SECURITY_PRINCIPAL, usuario.getLogin() + "@pcrn.local");
//        env.put(Context.SECURITY_CREDENTIALS, usuario.getSenha());
//        ctx = null;
//
//        if (usuario != null) {
//            if (usuario.getLogin().equals("")) {
//                // INFORMAR CPF EM BRANCO
//                System.out.println("USUARIO EM BRANCO");
//            }
//
//            ctx = null;
//            try {
//                // CONEXAO SENDO FEITA
//                connectionControls = null;
//                ctx = new InitialLdapContext(env, connectionControls);
//
//                return buscarUsuario(usuario);
//
//            } catch (NamingException e) {
//                e.printStackTrace();
//                System.out.println("USUÁRIO NÃO CADASTRADO!");
//                return null;
//            } finally {
//                if (ctx != null) {
//                    try {
//                        ctx.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return null;
//    }

}
