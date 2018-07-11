package br.pcrn.sisint.dao;


import br.pcrn.sisint.dominio.Termo;
import br.pcrn.sisint.dominio.relatorios.TermoGeral;

import javax.persistence.EntityManager;
import java.util.List;

public class TermoJpaDao extends EntidadeJpaDao<Termo> implements TermoDao  {

    protected TermoJpaDao() { this(null); }

    public TermoJpaDao(EntityManager entityManager) {
        super(entityManager, Termo.class);
    }
}
