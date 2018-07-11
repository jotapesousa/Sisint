package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Setor;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class SetorJpaDao extends EntidadeJpaDao<Setor> implements SetorDao{
    /**
     * @deprecated CDI
     */
    @Deprecated
    public SetorJpaDao() {
        this(null);
    }

    @Inject
    public SetorJpaDao(EntityManager entityManager) {
        super(entityManager, Setor.class);
    }

    @Override
    public List<Setor> listar() {
        return super.listar().stream().collect(Collectors.toList());
    }

    @Override
    public List<Setor> todos() {
        return super.todos().stream().collect(Collectors.toList());
    }
}
