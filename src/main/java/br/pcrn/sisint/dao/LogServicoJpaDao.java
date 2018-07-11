package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.LogServico;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class LogServicoJpaDao extends EntidadeJpaDao<LogServico> implements LogServicoDao{

    @Inject
    public LogServicoJpaDao(EntityManager entityManager) {
        super(entityManager, LogServico.class);
    }
}
