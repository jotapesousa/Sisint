package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Usuario;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

/**
 * Created by samue on 08/09/2017.
 */
public class UsuarioJpaDao extends EntidadeJpaDao<Usuario> implements UsuarioDao {

    /**
     * @deprecated CDI
     */
    @Deprecated
    public UsuarioJpaDao() {
        this(null);
    }

    @Inject
    public UsuarioJpaDao(EntityManager entityManager) {
        super(entityManager, Usuario.class);
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
            Query query = this.manager.createQuery("SELECT p from Usuario p where p.login = :login");
            query.setParameter("login",login);
            return query.setMaxResults(1).getResultList().stream().findFirst();
    }
}
