package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormats;
import br.com.caelum.vraptor.observer.download.Download;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dao.TermoDao;
import br.pcrn.sisint.dominio.Equipamento;
import br.pcrn.sisint.dominio.Setor;
import br.pcrn.sisint.dominio.Termo;
import br.pcrn.sisint.dominio.relatorios.EntityReport;

import br.pcrn.sisint.dominio.relatorios.TermoGeral;
import br.pcrn.sisint.negocio.TermoNegocio;


import javax.inject.Inject;
import javax.servlet.ServletContext;

import java.util.ArrayList;

import java.util.List;


@Controller
public class TermoController extends Controlador {

    private TermoNegocio termoNegocio;
    private TermoDao termoDao;
    private SetorDao setorDao;

    @Inject
    private ServletContext context;

    @Deprecated
    protected TermoController(){
        this(null, null, null, null);
    }

    @Inject
    public TermoController(Result resultado, TermoNegocio termoNegocio, TermoDao termoDao, SetorDao setorDao) {
        super(resultado);
        this.termoNegocio = termoNegocio;
        this.termoDao = termoDao;
        this.setorDao = setorDao;
    }

    public void form() {
        resultado.include("equipamentos", termoNegocio.geraListaOpcoesEquipamentos());
        resultado.include("setores", termoNegocio.geraListaOpcoesSetores());
    }

    public void lista() {  }

    @Get
    @Path("/imprimirTermo")
    public Download imprimirTermo(Termo termo) {
        //termoDao.salvar(termo);


        Equipamento equip = new Equipamento();
        equip.setNome("Notebook Dell Inspiron");
        equip.setTombo(Long.parseLong("12345678900"));
        equip.setNumeroSerie("PCRN20180418");
        equip.setDeletado(false);

        Equipamento equip2 = new Equipamento();
        equip2.setNome("Impressora Kyocera");
        equip2.setTombo(Long.parseLong("09876543210"));
        equip2.setNumeroSerie("20182104K21");
        equip2.setDeletado(false);

        Equipamento equip3 = new Equipamento();
        equip3.setNome("Switch TP-Link 8 portas");
        equip3.setNumeroSerie("PCRN3041231");
        equip3.setDeletado(false);

        List<Equipamento> equipamentos = new ArrayList<Equipamento>();
        equipamentos.add(equip);
        equipamentos.add(equip2);
        equipamentos.add(equip3);
        termo.setEquipamentos(equipamentos);

        List<TermoGeral> termos = this.termoNegocio.gerarTermoGeral(termo);
        Setor setor = this.setorDao.buscarPorId(termo.getSetor().getId());

        Report report = new EntityReport<TermoGeral>(termos,"relatorioServico.jasper", context);
        report.addParameter("setor1", setor.getNome());
        ReportDownload download = new ReportDownload(report, ExportFormats.pdf(), false);
        return download;
    }
}
