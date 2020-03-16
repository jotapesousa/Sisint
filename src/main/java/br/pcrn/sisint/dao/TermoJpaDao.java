package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Termo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TermoJpaDao extends EntidadeJpaDao<Termo> implements TermoDao  {

    @Deprecated
    protected TermoJpaDao() { this(null); }

    @Inject
    public TermoJpaDao(EntityManager entityManager) {
        super(entityManager, Termo.class);
    }

    @Override
    public List<Termo> listar() {
        return super.listar().stream().collect(Collectors.toList());
    }

    @Override
    public List<Termo> todos() {
        return super.todos().stream().collect(Collectors.toList());
    }

    public int numTermo() {
        Query query = this.manager.createNativeQuery("SELECT COUNT(t.dataCriacao) FROM Termo t WHERE EXTRACT(YEAR FROM t.dataCriacao) = :ano ")
                .setParameter("ano", LocalDate.now().getYear());

        BigInteger resultado = (BigInteger) query.getSingleResult();
        return resultado.intValue();
    }
}
