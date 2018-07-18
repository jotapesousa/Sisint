package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Manutencao;
import br.pcrn.sisint.dominio.StatusManutencao;

import java.util.List;


public interface ManutencaoDao extends EntidadeDao<Manutencao> {

    Long contarTotalManutencoes();
    Long contarManutencoesStatus(StatusManutencao statusManutencao);

    List<Manutencao> listarEmAberto();
    List<Manutencao> listarConcluidos();
}
