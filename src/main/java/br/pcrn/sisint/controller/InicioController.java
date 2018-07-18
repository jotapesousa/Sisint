package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.dao.EquipamentoDao;
import br.pcrn.sisint.dao.ManutencaoDao;
import br.pcrn.sisint.dao.ServicoDao;
import br.pcrn.sisint.dao.TarefaDao;
import br.pcrn.sisint.dominio.StatusManutencao;
import br.pcrn.sisint.dominio.StatusServico;
import br.pcrn.sisint.dominio.Tarefa;
import br.pcrn.sisint.dominio.TipoUsuario;

import javax.inject.Inject;

@Path("/")
@Seguranca(tipoUsuario = TipoUsuario.TECNICO)
@Controller
public class InicioController extends Controlador {

    private ServicoDao servicoDao;
    private TarefaDao tarefaDao;
    private ManutencaoDao manutencaoDao;
    private EquipamentoDao equipamentoDao;

    @Inject
    private Validator validator;

    protected InicioController() {
        this(null,null, null, null, null);
    }

    @Inject
    public InicioController(Result resultado, ServicoDao servicoDao,
                            TarefaDao tarefaDao, ManutencaoDao manutencaoDao, EquipamentoDao equipamentoDao) {
        super(resultado);
        this.servicoDao = servicoDao;
        this.tarefaDao = tarefaDao;
        this.manutencaoDao = manutencaoDao;
        this.equipamentoDao = equipamentoDao;

    }

    @Path("")
    public void index(){
        resultado.include("totalServicos",servicoDao.contarTotalServicos());
        resultado.include("servicosAbertos",servicoDao.contarServicosStatus(StatusServico.EM_ESPERA));
        resultado.include("servicosExecucao",servicoDao.contarServicosStatus(StatusServico.EM_EXECUCAO));
        resultado.include("totalTarefas",tarefaDao.contarTotalTarefas());

        resultado.include("totalManutencoes",manutencaoDao.contarTotalManutencoes());
        resultado.include("manutencoesAbertas",manutencaoDao.contarManutencoesStatus(StatusManutencao.AGUARDANDO_MANUTENCAO));
        resultado.include("manutencoesExecucao",manutencaoDao.contarManutencoesStatus(StatusManutencao.EM_MANUTENCAO));
        resultado.include("totalEquipamentos",equipamentoDao.contarTotalEquipamentos());


    }


}
