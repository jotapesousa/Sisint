package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormats;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.view.Results;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dao.TermoDao;
import br.pcrn.sisint.dominio.*;
import br.pcrn.sisint.dominio.relatorios.EntityReport;

import br.pcrn.sisint.dominio.relatorios.TermoDeResponsabilidade;
import br.pcrn.sisint.negocio.EquipamentoNegocio;
import br.pcrn.sisint.negocio.TermoNegocio;
import br.pcrn.sisint.util.OpcaoSelect;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import javax.inject.Inject;
import javax.servlet.ServletContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;


@Controller
@Seguranca(tipoUsuario = TipoUsuario.TECNICO)
public class TermoController extends Controlador {

    private TermoNegocio termoNegocio;
    private TermoDao dao;
    private SetorDao setorDao;
    private UsuarioLogado usuarioLogado;

    @Inject
    private ServletContext context;
    @Inject
    private EquipamentoNegocio equipamentoNegocio;

    @Deprecated
    protected TermoController(){
        this(null, null, null, null, null);
    }

    @Inject
    public TermoController(Result resultado, TermoNegocio termoNegocio, TermoDao dao, SetorDao setorDao, UsuarioLogado usuarioLogado) {
        super(resultado);
        this.termoNegocio = termoNegocio;
        this.dao = dao;
        this.setorDao = setorDao;
        this.usuarioLogado = usuarioLogado;
    }

    public void form() {
        resultado.include("equipamentos", termoNegocio.geraListaOpcoesEquipamentos());
        resultado.include("setores", termoNegocio.geraListaOpcoesSetores());
        resultado.include("usuarios", termoNegocio.geraListaOpcoesUsuarios());
        resultado.include("statusEquipamento", OpcaoSelect.toListaOpcoes(StatusEquipamento.values()));
        resultado.include("tipo", OpcaoSelect.toListaOpcoes(TipoEquipamento.values()));
        resultado.include("numTermo", termoNegocio.numTermo()+1);
    }

    @Post
    @Transacional
    public void salvar(Termo termo) {
        termoNegocio.salvar(termo);
        this.resultado.redirectTo(this).lista();
    }

    public void lista() { this.resultado.include("termos", this.dao.listar()); }

    public void editar(Long id) {
        Termo termo = this.dao.buscarPorId(id);
        resultado.include("setores", termoNegocio.geraListaOpcoesSetores());
        resultado.include("termo", termo);
        resultado.of(this).form();
    }

    @Transacional
    public void remover(Long id) {
        Termo termo = this.dao.buscarPorId(id);
        termo.setDeletado(true);
        this.dao.salvar(termo);
        this.resultado.redirectTo(this).lista();
    }

    public void detalhes(Long id) {
        Termo termo = dao.buscarPorId(id);
        resultado.include("termo", termo);
    }

    public Download imprimir(Long id) {
        Download download = null;

        try {
            download = termoNegocio.imprimir(id);
        } catch (Exception e) {
            resultado.include("mensagem", new SimpleMessage("error", "mensagem.termo.error"));
            resultado.redirectTo(this).lista();
        }
        return download;
    }

    public void receber(Long id){

    }

    @Post
    @Transacional
    public void salvarAjax(Termo termo) {
        System.out.println(termo.toString());
//        termo.setNumero(termoNegocio.numTermo() + 1);
//        termo.setAno(LocalDate.now().getYear());
//        termo.setDataCriacao(LocalDate.now());
//        termo.setTecnico(usuarioLogado.getUsuario());
//
//        if (termo.isRecebido()) {
//            termo.setHoraRecebimento(LocalDateTime.now());
//        }
//
//        dao.salvar(termo);
//        resultado.use(Results.json()).withoutRoot().from(termo).recursive().serialize();
        resultado.redirectTo(this).lista();

    }

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

//        List<TermoDeResponsabilidade> termos = this.termoNegocio.gerarTermoGeral(termo);
        Setor setor = this.setorDao.buscarPorId(termo.getSetor().getId());

//        Report report = new EntityReport<TermoDeResponsabilidade>(termos,"termoResponsabilidade.jasper", context);
//        report.addParameter("setor1", setor.getNome());
//        ReportDownload download = new ReportDownload(report, ExportFormats.pdf(), false);
        return null;
    }

    @Get
    public void buscarEquipamentosPorTipo(TipoEquipamento tipoEquip){
        JsonArray jsonArray = equipamentoNegocio.buscarEquipamentoPorTipo(tipoEquip);
        resultado.use(Results.json()).withoutRoot().from(jsonArray).recursive().serialize();
    }
}
