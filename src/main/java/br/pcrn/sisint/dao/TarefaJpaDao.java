package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.StatusTarefa;
import br.pcrn.sisint.dominio.Tarefa;
import br.pcrn.sisint.dominio.UsuarioLogado;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TarefaJpaDao extends EntidadeJpaDao<Tarefa> implements TarefaDao{

    @Inject
    private UsuarioLogado usuarioLogado;

    @Inject
    public TarefaJpaDao(EntityManager entityManager) {
        super(entityManager, Tarefa.class);
    }

    @Override
    public List<Tarefa> tarefasEmAberto() {
        Query query = this.manager.createQuery("SELECT p FROM Tarefa p WHERE p.statusTarefa = :status AND p.deletado = false");
        query.setParameter("status", StatusTarefa.EM_ESPERA);
        List<Tarefa> tarefas = query.getResultList();

        return tarefas;
    }

    @Override
    public List<Tarefa> minhasTarefas() {
        Query query = this.manager.createQuery("SELECT p FROM Tarefa p WHERE (p.statusTarefa = 'EM_ESPERA' " +
                "OR p.statusTarefa = 'EM_EXECUCAO') AND p.tecnico.id = :usuario AND p.deletado = false");
        query.setParameter("usuario", usuarioLogado.getUsuario().getId());
        List<Tarefa> tarefas = query.getResultList();
        return tarefas;
    }

    @Override
    public List<Tarefa> tarefasConcluidas() {
        Query query = this.manager.createQuery("SELECT p FROM Tarefa p WHERE p.statusTarefa = 'CONCLUIDO' AND p.deletado = false");
        List<Tarefa> tarefas = query.getResultList();
        return tarefas;
    }

    @Override
    public Long contarTotalTarefas() {
        Query query = manager.createQuery("select count(t) from Tarefa t WHERE t.deletado = false");
        return (Long) query.getSingleResult();
    }

}
