package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.LogServico;
import br.pcrn.sisint.dominio.Servico;
import br.pcrn.sisint.dominio.StatusServico;

import javax.ejb.Local;
import java.time.LocalDate;
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
    Long servicoPorSetor(Long id);
    Servico BuscarPorId(Long id);
    void salvarLogServico(LogServico logServico);

    public void verificarConlusaoEAtualizar(Long id);

    Long meusServicos();
    Long contarPorSetor(Long id);
    List<Object[]> contarDeAteDataPorSetorDESC(LocalDate dtDe, LocalDate dtAte);
    List<Object[]> contarAPartirDePorSetorDESC(LocalDate dtDe);
    List<Object[]> contarAteDataPorSetorDESC(LocalDate dtAte);
    List<Object[]> contarDeAteDataDESC(LocalDate dtDe, LocalDate dtAte);
    List<Servico> filtrarDeAteDataPorSetorDESC(Long id, LocalDate dtDe, LocalDate dtAte);
    List<Servico> filtrarAPartirDePorSetorDESC(Long id, LocalDate dtDe);
    List<Servico> filtrarAteDataPorSetorDESC(Long id, LocalDate dtAte);
    List<Servico> filtrarDeAteDataDESC(LocalDate dtDe, LocalDate dtAte);

    List<Servico> filtrarMaisRecentesPorSetor(Long id);
}
