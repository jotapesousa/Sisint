package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Equipamento;

import java.util.List;
import java.util.Optional;

public interface EquipamentoDao extends EntidadeDao<Equipamento> {
    Long contarTotalEquipamentos();

    List<Equipamento> listarPorTombo(Long codigo);
    List<Equipamento> listarPorNSerie(String codigo);
    Optional<Equipamento> buscarPorNSerie(String codigo);
    Optional<Equipamento> buscarPorTombo(Long codigo);
    boolean verificarNSerie(String nserie);
    boolean verificarTombo(Long tombo);
}
