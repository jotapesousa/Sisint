package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Manutencao;

import java.util.List;


public interface ManutencaoDao extends EntidadeDao<Manutencao> {

    List<Manutencao> listarEmAberto();
}
