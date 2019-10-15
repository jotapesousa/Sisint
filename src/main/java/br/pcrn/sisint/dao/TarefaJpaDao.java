package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Servico;
import br.pcrn.sisint.dominio.StatusTarefa;
import br.pcrn.sisint.dominio.Tarefa;
import br.pcrn.sisint.dominio.UsuarioLogado;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
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

    @Override
    public List<Tarefa> buscarDezUltimas() {
        Query query = manager.createQuery("SELECT t FROM Tarefa t ORDER BY t.dataFechamento DESC")
                .setMaxResults(9);
        List<Tarefa> tarefas = query.getResultList();
        return tarefas;
    }

    public List<Tarefa> filtrarDeAteDataPorSetorDESC(Long id, LocalDate dtDe, LocalDate dtAte) {
        Query query = manager.createQuery("SELECT t FROM Tarefa t WHERE (t.servico.setor.id = :id AND t.dataFechamento >= :dtDe AND t.dataFechamento <= :dtAte) ORDER BY t.dataFechamento DESC")
                .setParameter("id", id)
                .setParameter("dtDe", dtDe)
                .setParameter("dtAte", dtAte);
        return query.getResultList();
    }

    public List<Tarefa> filtrarMaisRecentesPorSetorDESC(Long id) {
        Query query = manager.createQuery("SELECT t FROM Tarefa t WHERE (t.servico.setor.id = :id and t.deletado = false) ORDER BY t.dataFechamento DESC")
                .setParameter("id", id);

        return query.getResultList();
    }

}
