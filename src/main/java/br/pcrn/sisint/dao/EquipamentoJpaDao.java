package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Equipamento;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class EquipamentoJpaDao extends EntidadeJpaDao<Equipamento> implements EquipamentoDao {


    @Deprecated
    protected EquipamentoJpaDao(){this(null);}

    @Inject
    public EquipamentoJpaDao(EntityManager entityManager) {
        super(entityManager, Equipamento.class);
    }


    @Override
    public Long contarTotalEquipamentos() {
        Query query = manager.createQuery("select count(t) from Equipamento t WHERE t.deletado = false");
        return (Long) query.getSingleResult();
    }


    @Override
    public List<Equipamento> listarPorNSerie(String codigo) {
        Query query = manager.createQuery("SELECT e FROM Equipamento e WHERE e.numeroSerie LIKE :codigo")
                .setParameter("codigo","%"+codigo+"%");
        return query.getResultList();
    }

    @Override
    public List<Equipamento> listarPorTombo(Long codigo) {
        Query query = manager.createQuery("SELECT e FROM Equipamento e WHERE str(e.tombo) LIKE :codigo AND e.deletado = false")
                .setParameter("codigo","%"+codigo+"%");
        return query.getResultList();
    }

    @Override
    public Optional<Equipamento> buscarPorNSerie(String codigo) {
        Query query = manager.createQuery("SELECT e FROM Equipamento e WHERE e.numeroSerie = :codigo AND e.deletado = false")
                .setParameter("codigo", codigo);
        query.setMaxResults(1);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Optional<Equipamento> buscarPorTombo(Long codigo) {
        Query query = manager.createQuery("SELECT e FROM Equipamento e WHERE e.tombo = :codigo AND e.deletado = false")
                .setParameter("codigo", codigo);
        query.setMaxResults(1);
        return query.getResultList().stream().findFirst();
    }
}
