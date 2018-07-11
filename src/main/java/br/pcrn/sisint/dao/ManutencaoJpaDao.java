package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Manutencao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ManutencaoJpaDao extends EntidadeJpaDao<Manutencao> implements ManutencaoDao {


    @Deprecated
    protected ManutencaoJpaDao(){
        this(null);
    }

    @Inject
    public ManutencaoJpaDao(EntityManager entityManager) {
        super(entityManager, Manutencao.class);
    }

    @Override
    public List<Manutencao> listarEmAberto() {
        return manager.createQuery("SELECT t FROM "+tClass.getSimpleName()+" t where t.status != 'CONCLUIDO' AND t.deletado = false").getResultList();
    }
}
