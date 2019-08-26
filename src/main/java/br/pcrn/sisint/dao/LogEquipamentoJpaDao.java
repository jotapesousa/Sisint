package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.LogEquipamento;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class LogEquipamentoJpaDao extends EntidadeJpaDao<LogEquipamento> implements LogEquipamentoDao {

    @Inject
    public LogEquipamentoJpaDao(EntityManager entityManager) {
        super(entityManager, LogEquipamento.class);
    }
}
