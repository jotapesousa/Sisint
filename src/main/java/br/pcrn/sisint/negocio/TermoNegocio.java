package br.pcrn.sisint.negocio;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormats;
import br.com.caelum.vraptor.observer.download.Download;
import br.pcrn.sisint.dao.EquipamentoDao;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dao.TermoDao;
import br.pcrn.sisint.dao.UsuarioDao;
import br.pcrn.sisint.dominio.*;
import br.pcrn.sisint.dominio.relatorios.RelatorioServico;
import br.pcrn.sisint.dominio.relatorios.TermoDeResponsabilidade;
import br.pcrn.sisint.util.EntidadeReport;
import br.pcrn.sisint.util.OpcaoSelect;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TermoNegocio {

    private TermoDao termoDao;
    private EquipamentoDao equipamentoDao;
    private SetorDao setorDao;
    private UsuarioDao usuarioDao;

    @Inject
    private ServletContext context;

    @Inject
    private UsuarioLogado usuarioLogado;

    @Inject
    public TermoNegocio(EquipamentoDao equipamentoDao, SetorDao setorDao, UsuarioDao usuarioDao, TermoDao termoDao) {
        this.equipamentoDao = equipamentoDao;
        this.setorDao = setorDao;
        this.usuarioDao = usuarioDao;
        this.termoDao = termoDao;
    }

    @Deprecated
    public TermoNegocio() { this(null, null, null, null);}

    public List<OpcaoSelect> geraListaOpcoesUsuarios() {
        List<Usuario> todos = this.usuarioDao.listar().stream().collect(Collectors.toList());
        return todos.stream().map(
                usuario -> new OpcaoSelect(usuario.getNome(), usuario.getId()))
                .collect(Collectors.toList());
    }

    public List<OpcaoSelect> geraListaOpcoesEquipamentos() {
        List<Equipamento> todos = this.equipamentoDao.todos().stream().collect(Collectors.toList());
        return todos.stream().map(
                equipamento -> new OpcaoSelect(equipamento.getNome(), equipamento.getId()))
                .collect(Collectors.toList());
    }

    public List<OpcaoSelect> geraListaOpcoesSetores() {
        List<Setor> todos = this.setorDao.todos().stream().collect(Collectors.toList());
        return todos.stream().map(
                setor -> new OpcaoSelect(setor.getNome(), setor.getId()))
                .collect(Collectors.toList());
    }

    public void salvar(Termo termo) {
        if (termo.getId() == null) {
//            termo.setNumero(numTermo() + 1);
//            termo.setAno(LocalDate.now().getYear());
            termo.setDataCriacao(LocalDate.now());
            termo.setTecnico(usuarioLogado.getUsuario());
        }
//        if (termo.isRecebido()) {
//            termo.setHoraRecebimento(LocalDateTime.now());
//            for (Equipamento equipamento : termo.getEquipamentos()) {
//                equipamento.setSetor(termo.getSetor());
//            }
//        }

        termoDao.salvar(termo);
    }

    public int numTermo() {
        return termoDao.numTermo();
    }

    public Download imprimir(Long id) {
        List<TermoDeResponsabilidade> termos = new ArrayList<>();
        Report report;
        ReportDownload reportDownload;

        Termo termoGerado = termoDao.buscarPorId(id);

        for (int i=0; i < termoGerado.getEquipamentos().size();i++) {
            Equipamento equipamento = termoGerado.getEquipamentos().get(i);
            TermoDeResponsabilidade termo = new TermoDeResponsabilidade(termoGerado.getSetor().getNome(), equipamento.getNome(),
                    equipamento.getTombo().toString(), equipamento.getNumeroSerie(), i,3);
            termos.add(termo);
        }

        report = new EntidadeReport<TermoDeResponsabilidade>(termos, "termoDeResponsabilidade.jasper", context);
        report.addParameter("numTermo", termoGerado.getNumero());
        report.addParameter("anoTermo", termoGerado.getAno());
        report.addParameter("setorTermo", termoGerado.getSetor().getNome());
        Download download = new ReportDownload(report, ExportFormats.pdf(), false);

        return download;
    }

    public Download gerarTermo(String nomeSetor, int numTermo, int anoTermo, List<Equipamento> equipamentos) {
        List<TermoDeResponsabilidade> termos = new ArrayList<>();
        Report report;
        ReportDownload reportDownload;

        for (int i=0; i < equipamentos.size();i++) {
            Equipamento equipamento = equipamentos.get(i);
            TermoDeResponsabilidade termo = new TermoDeResponsabilidade(nomeSetor, equipamento.getNome(),
                    equipamento.getTombo().toString(), equipamento.getNumeroSerie(), i,3);
            termos.add(termo);
        }

        report = new EntidadeReport<TermoDeResponsabilidade>(termos, "termoDeResponsabilidade.jasper", context);
        report.addParameter("numTermo", numTermo);
        report.addParameter("anoTermo", anoTermo);
        report.addParameter("setorTermo", nomeSetor);
        Download download = new ReportDownload(report, ExportFormats.pdf(), false);

        return download;
    }

}
