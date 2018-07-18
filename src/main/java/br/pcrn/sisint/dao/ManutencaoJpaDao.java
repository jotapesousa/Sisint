package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Manutencao;
import br.pcrn.sisint.dominio.StatusManutencao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    public Long contarTotalManutencoes() {
        Query query = manager.createQuery("select count(s) from Manutencao s where s.deletado = false");
        return (Long) query.getSingleResult();
    }

    @Override
    public Long contarManutencoesStatus(StatusManutencao statusManutencao) {
        Query query = manager.createQuery("select count(s) from Manutencao s where s.deletado = false AND s.status = :status");
        query.setParameter("status", statusManutencao);

        return (Long) query.getSingleResult();
    }

    @Override
    public List<Manutencao> listarEmAberto() {
        return manager.createQuery("SELECT t FROM "+tClass.getSimpleName()+" t where t.status != 'CONCLUIDO' AND t.deletado = false").getResultList();
    }

    @Override
    public List<Manutencao> listarConcluidos() {
        return manager.createQuery("SELECT t FROM " +tClass.getSimpleName()+" t where t.status = 'CONCLUIDO' and t.deletado = false").getResultList();
    }
}
