package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.FileDownload;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;

import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.*;
import br.pcrn.sisint.dominio.*;
import br.pcrn.sisint.negocio.ManutencaoNegocio;
import br.pcrn.sisint.util.OpcaoSelect;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.File;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;

@Seguranca(tipoUsuario = TipoUsuario.TECNICO)
@Controller
public class ManutencaoController extends ControladorSisInt<Manutencao> {

    private ManutencaoDao manutencaoDao;
    private SetorDao setorDao;
    private UsuarioDao usuarioDao;
    private ManutencaoNegocio manutencaoNegocio;
    private Validator validator;
    private ServletContext context;

    @Inject
    UsuarioLogado usuarioLogado;

    @Inject
    private EquipamentoDao equipamentoDao;

    @Deprecated
    protected ManutencaoController(){
        this(null,null,null, null,null, null, null);
    }

    @Inject
    public ManutencaoController(Result resultado, ManutencaoDao manutencaoDao, SetorDao setorDao,
                                UsuarioDao usuarioDao, ManutencaoNegocio manutencaoNegocio, Validator validator, ServletContext context) {
        super(resultado);
        this.manutencaoDao = manutencaoDao;
        this.setorDao = setorDao;
        this.usuarioDao = usuarioDao;
        this.manutencaoNegocio = manutencaoNegocio;
        this.validator = validator;
        this.context = context;
    }

    public void form() {
        resultado.include("setores", manutencaoNegocio.geraListaOpcoesSetor());
        resultado.include("usuarios", manutencaoNegocio.geraListaOpcoesUsuarios());
        resultado.include("equipamentos", manutencaoNegocio.geraListaOpcoesEquipamentos());
        resultado.include("status", OpcaoSelect.toListaOpcoes(StatusManutencao.values()));
        resultado.include("statusEquipamento", OpcaoSelect.toListaOpcoes(StatusEquipamento.values()));
        resultado.include("tipo", OpcaoSelect.toListaOpcoes(TipoEquipamento.values()));
    }

    @Post
    @Transacional
    @UploadSizeLimit(sizeLimit = 40 * 1024 * 1024, fileSizeLimit = 10 * 1024 * 1024)
    public void salvar(Manutencao manutencao) throws IOException {
        if (manutencao.getId() == null || manutencao.getDataAbertura() == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            manutencao.setDataAbertura(LocalDate.now().format(formatter));
        }
        manutencaoNegocio.verificarStatus(manutencao);
        manutencao.setTecnico(usuarioLogado.getUsuario());

        // GERAR LOG DA MANUTENCAO

        this.manutencaoDao.salvar(manutencao);

        this.resultado.include("manutencoes", manutencaoDao.todos());
        this.resultado.redirectTo(this).lista();///////
    }

    public void editar(Long id) {
        Manutencao manutencao = this.manutencaoDao.buscarPorId(id);

        resultado.include("manutencao", manutencao);
        resultado.include("setores", manutencaoNegocio.geraListaOpcoesSetor());
        resultado.include("usuarios", manutencaoNegocio.geraListaOpcoesUsuarios());
        resultado.include("status", OpcaoSelect.toListaOpcoes(StatusManutencao.values()));
        resultado.include("tipo", OpcaoSelect.toListaOpcoes(TipoEquipamento.values()));
        resultado.of(this).form();
    }

    @Transacional
    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public void remover(Long id) {
        Manutencao manutencao = this.manutencaoDao.buscarPorId(id);
        manutencao.setDeletado(true);
        this.manutencaoDao.salvar(manutencao);
        this.resultado.redirectTo(this).lista();
    }

    public void lista() { this.resultado.include("manutencoes", this.manutencaoDao.listarEmAberto()); }

    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public void listarTodos() { this.resultado.include("manutencoes", this.manutencaoDao.listar()); }

    public void listarConcluidos() { this.resultado.include("manutencoes", this.manutencaoDao.listarConcluidos()); }

    public void detalhar(Long id) {
        Manutencao manutencao = this.manutencaoDao.buscarPorId(id);
        resultado.include("manutencao", manutencao);
    }

    @Path("/assumirManutencao")
    @Transacional
    public void assumirManutencao(Long id) {
        Manutencao manutencao = manutencaoDao.buscarPorId(id);
        manutencao.setTecnico(usuarioLogado.getUsuario());
        manutencao.setStatus(StatusManutencao.EM_MANUTENCAO);
        manutencaoNegocio.verificarStatus(manutencao);
        manutencaoDao.salvar(manutencao);
        resultado.redirectTo(this).lista();
    }

    @Transacional
    public void concluir(Long id, String checkConserto, String descricaoFinal) {
        System.out.println(checkConserto);
        Manutencao manutencao = manutencaoDao.buscarPorId(id);
        manutencao.setStatus(StatusManutencao.CONCLUIDO);
        manutencao.setDescricaoFinal(descricaoFinal);
        manutencaoNegocio.verificarConclusao(manutencao, checkConserto);
        manutencaoDao.salvar(manutencao);
        resultado.redirectTo(this).lista();
    }

    @Get
    public void buscarEquipamentos(String texto){
        List<Equipamento> equipamentos = equipamentoDao.listarPorNSerie(texto);
        JsonArray jsonArray = equipamentoJson(equipamentos);
        resultado.use(Results.json()).withoutRoot().from(jsonArray).recursive().serialize();
    }

    @Get
    public void buscarEquipamentosPorTombo(Long tombo){
        List<Equipamento> equipamentos = equipamentoDao.listarPorTombo(tombo);
        JsonArray jsonArray = equipamentoJson(equipamentos);
        resultado.use(Results.json()).withoutRoot().from(jsonArray).recursive().serialize();
    }

    private JsonArray equipamentoJson(List<Equipamento> equipamentos) {
        JsonArray jsonArray = new JsonArray();

        for (Equipamento eq : equipamentos) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id",eq.getId());
            jsonObject.addProperty("nome",eq.getNome());
            jsonObject.addProperty("tombo",eq.getTombo());
            jsonObject.addProperty("nserie",eq.getNumeroSerie());
            jsonObject.addProperty("descricao", eq.getDescricao());
            jsonObject.addProperty("status", eq.getStatus().getChave());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    @Get
    public Download download(Long id) throws IOException {
        Manutencao manutencao = this.manutencaoDao.buscarPorId(id);

        String name = manutencao.getArquivo().getName();
        String path = manutencao.getArquivo().getPath();
        String contentType = manutencao.getArquivo().getContentType();

        File file = new File(path);

        return new FileDownload(file, contentType, name, true);
    }

}
