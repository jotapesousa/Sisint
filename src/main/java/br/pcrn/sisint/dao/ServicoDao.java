package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.LogServico;
import br.pcrn.sisint.dominio.Servico;
import br.pcrn.sisint.dominio.StatusServico;

import java.util.List;

/**
 * Created by samue on 17/09/2017.
 */
public interface ServicoDao extends EntidadeDao<Servico> {
    Long contarTotalServicos();
    Long contarServicosStatus(StatusServico statusServico);
    @Override
    List<Servico> listar();

    List<Servico> listarServicos();
    public List<Servico> listarMeusServicos(Long id);
    public List<Servico> listarServicosEmAberto();
    Servico BuscarPorId(Long id);
    void salvarLogServico(LogServico logServico);

    public void verificarConlusaoEAtualizar(Long id);
}
