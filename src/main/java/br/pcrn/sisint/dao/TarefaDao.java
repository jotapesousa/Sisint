package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Servico;
import br.pcrn.sisint.dominio.Tarefa;

import java.time.LocalDate;
import java.util.List;

public interface TarefaDao extends EntidadeDao<Tarefa> {
    Long contarTotalTarefas();
    List<Tarefa> tarefasEmAberto();
    List<Tarefa> minhasTarefas();
    List<Tarefa> tarefasConcluidas();
    List<Tarefa> buscarDezUltimas();
    List<Tarefa> filtrarDeAteDataPorSetorDESC(Long id, LocalDate dtDe, LocalDate dtAte);
    List<Tarefa> filtrarMaisRecentesPorSetorDESC(Long id);
}
