package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Arquivo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.net.URI;

public class DiretorioJpaDao implements DiretorioDao {

    private EntityManager manager;

    @Deprecated
    protected DiretorioJpaDao(){this(null);}
    @Inject

    public DiretorioJpaDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public URI grava(Arquivo arquivo) {
        if(arquivo.getId() != null) {
            manager.persist(arquivo);
        } else {
            manager.merge(arquivo);
        }
        return URI.create("bd://"+arquivo.getId());
    }

    @Override
    public Arquivo recupera(URI arquivo) {
        return null;
    }
}
