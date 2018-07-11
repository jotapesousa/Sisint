package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.dao.ServicoDao;
import br.pcrn.sisint.dao.TarefaDao;
import br.pcrn.sisint.dominio.StatusServico;
import br.pcrn.sisint.dominio.TipoUsuario;

import javax.inject.Inject;

@Path("/")
@Seguranca(tipoUsuario = TipoUsuario.TECNICO)
@Controller
public class InicioController extends Controlador {

    private ServicoDao servicoDao;
    private TarefaDao tarefaDao;

    @Inject
    private Validator validator;

    protected InicioController() {
        this(null,null, null);
    }

    @Inject
    public InicioController(Result resultado, ServicoDao servicoDao,
                            TarefaDao tarefaDao) {
        super(resultado);
        this.servicoDao = servicoDao;
        this.tarefaDao = tarefaDao;

    }

    @Path("")
    public void index(){
        resultado.include("totalServicos",servicoDao.contarTotalServicos());
        resultado.include("servicosAbertos",servicoDao.contarServicosStatus(StatusServico.EM_ESPERA));
        resultado.include("servicosExecucao",servicoDao.contarServicosStatus(StatusServico.EM_EXECUCAO));
        resultado.include("totalTarefas",tarefaDao.contarTotalTarefas());
    }


}
