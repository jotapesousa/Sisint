package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.*;
import br.pcrn.sisint.dominio.Equipamento;
import br.pcrn.sisint.dominio.StatusEquipamento;
import br.pcrn.sisint.negocio.EquipamentoNegocio;
import br.pcrn.sisint.negocio.ServicosNegocio;
import br.pcrn.sisint.util.OpcaoSelect;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Optional;

@Controller
public class EquipamentoController extends Controlador{

    private EntidadeDao<Equipamento> dao;
    private EquipamentoDao equipamentoDao;
    private SetorDao setorDao;
    private ServicosNegocio servicosNegocio;
    private EquipamentoNegocio equipamentoNegocio;
    private Validator validator;
    private ServletContext context;

    @Deprecated
    protected EquipamentoController() { this(null, null, null, null, null,
                                    null, null); }

    @Inject
    public EquipamentoController(Result resultado, EntidadeDao<Equipamento> dao, EquipamentoDao equipamentoDao,
                                 SetorDao setorDao, ServicosNegocio servicosNegocio, EquipamentoNegocio equipamentoNegocio,
                                 Validator validator) {
        super(resultado);
        this.dao = dao;
        this.equipamentoDao = equipamentoDao;
        this.setorDao = setorDao;
        this.servicosNegocio = servicosNegocio;
        this.validator = validator;
    }

    public void form(){
        resultado.include("setores", servicosNegocio.geraListaOpcoesSetor());
        resultado.include("status", OpcaoSelect.toListaOpcoes(StatusEquipamento.values()));
    }

    @Post
    @Transacional
    @UploadSizeLimit(sizeLimit = 40 * 1024 * 1024, fileSizeLimit = 10 * 1024 * 1024)
    public void salvar(Equipamento equipamento) throws IOException {
        validator.onErrorRedirectTo(this).form();

        Optional<Equipamento> equipamento1;

        //equipamentoNegocio.verificarTombo(equipamento, resultado);

        if (equipamento.getId() == null) {
            equipamento1 = this.equipamentoDao.buscarPorTombo(equipamento.getTombo());

            if (equipamento1.isPresent()) {
                resultado.include("mensagem", new SimpleMessage("error", "mensagem.equipamento.salvar.erroTombo"));
                resultado.include("equipamento", equipamento);
                resultado.include("setores", servicosNegocio.geraListaOpcoesSetor());
                resultado.of(this).form();
            } else {
                equipamento1 = this.equipamentoDao.buscarPorNSerie(equipamento.getNumeroSerie());

                if (equipamento1.isPresent()) {
                    resultado.include("mensagem", new SimpleMessage("error", "mensagem.equipamento.salvar.erroNSerie"));
                    resultado.include("equipamento", equipamento);
                    resultado.include("setores", servicosNegocio.geraListaOpcoesSetor());
                    resultado.of(this).form();
                } else {
                    dao.salvar(equipamento);
                    resultado.redirectTo(this).lista();
                }
            }
        } else {
            equipamento1 = this.equipamentoDao.buscarPorTombo(equipamento.getTombo());

            if (equipamento1.isPresent()) {
                if (equipamento.getId() != equipamento1.get().getId()) {
                    resultado.include("mensagem", new SimpleMessage("error", "mensagem.equipamento.salvar.erroTombo"));
                    resultado.include("equipamento", equipamento);
                    resultado.include("setores", servicosNegocio.geraListaOpcoesSetor());
                    resultado.of(this).form();
                } else {
                    equipamento1 = this.equipamentoDao.buscarPorNSerie(equipamento.getNumeroSerie());

                    if (equipamento1.isPresent()) {
                        if (equipamento.getId() != equipamento1.get().getId()) {
                            resultado.include("mensagem", new SimpleMessage("error", "mensagem.equipamento.salvar.erroNSerie"));
                            resultado.include("equipamento", equipamento);
                            resultado.include("setores", servicosNegocio.geraListaOpcoesSetor());
                            resultado.of(this).form();
                        } else {
                            dao.salvar(equipamento);
                            resultado.redirectTo(this).lista();
                        }
                    } else {
                        dao.salvar(equipamento);
                        resultado.redirectTo(this).lista();
                    }
                }
            } else {
                equipamento1 = this.equipamentoDao.buscarPorNSerie(equipamento.getNumeroSerie());

                if (equipamento1.isPresent()) {
                    if (equipamento.getId() != equipamento1.get().getId()) {
                        resultado.include("mensagem", new SimpleMessage("error", "mensagem.equipamento.salvar.erroNSerie"));
                        resultado.include("equipamento", equipamento);
                        resultado.include("setores", servicosNegocio.geraListaOpcoesSetor());
                        resultado.of(this).form();
                    } else {
                        dao.salvar(equipamento);
                        resultado.redirectTo(this).lista();
                    }
                } else {
                    dao.salvar(equipamento);
                    resultado.redirectTo(this).lista();
                }
            }
        }
    }

    public void lista() { resultado.include("equipamentos", dao.listar()); }

    public void editar(Long id) {
        Equipamento equipamento = dao.buscarPorId(id);
        resultado.include("equipamento", equipamento);
        resultado.include("setores", servicosNegocio.geraListaOpcoesSetor());
        resultado.include("status", OpcaoSelect.toListaOpcoes(StatusEquipamento.values()));
        resultado.of(this).form();
    }

    @Transacional
    public void remover(Long id) {
        Equipamento equipamento = this.dao.buscarPorId(id);
        equipamento.setDeletado(true);
        this.dao.salvar(equipamento);
        resultado.redirectTo(this).lista();
    }

    public void download(Long id) {
        Equipamento equipamento = dao.buscarPorId(id);

        resultado.of(this).lista();
    }

    @Transacional
    @Post
    public void salvarAjax(Equipamento equipamento) {
        dao.salvar(equipamento);
        resultado.use(Results.json()).withoutRoot().from(equipamento).recursive().serialize();
    }

}
