package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Tarefa;

import java.util.List;

public interface TarefaDao extends EntidadeDao<Tarefa> {
    Long contarTotalTarefas();
    List<Tarefa> tarefasEmAberto();
    List<Tarefa> minhasTarefas();
    List<Tarefa> tarefasConcluidas();
    List<Tarefa> buscarDezUltimas();
}
