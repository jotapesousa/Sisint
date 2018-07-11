package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Entidade;

import javax.persistence.EntityManager;
import java.util.Collection;

public abstract class EntidadeJpaDao<T extends Entidade> implements EntidadeDao<T> {

    protected EntityManager manager;
    protected Class<T> tClass;

    public EntidadeJpaDao(EntityManager entityManager, Class<T> tClass) {
        this.manager = entityManager;
        this.tClass = tClass;
    }

    @Override
    public T buscarPorId(Long id) {
        return manager.find(tClass, id);
    }

    @Override
    public T salvar(T entidade) {
        if(entidade.getId() != null) {
            this.manager.merge(entidade);
        } else {
            this.manager.persist(entidade);
        }
        return entidade;
    }

    @Override
    public void remover(T entidade) {
        manager.remove(entidade);
    }

    /**
     * A função listar() deve ser utilizada para todas as entidades que possuem delete lógico
     * @return Collection
     */
    @Override
    public Collection<T> listar() {
        return manager.createQuery("SELECT t FROM "+tClass.getSimpleName()+" t where t.deletado = false").getResultList();
    }

    @Override
    public Collection<T> todos() {
        return manager.createQuery("SELECT t FROM "+tClass.getSimpleName()+" t").getResultList();
    }
}
