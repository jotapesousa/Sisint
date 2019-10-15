package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dao.TarefaDao;
import br.pcrn.sisint.dominio.Setor;
import br.pcrn.sisint.dominio.Tarefa;
import br.pcrn.sisint.dominio.TipoUsuario;
import br.pcrn.sisint.negocio.SetorNegocio;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.inject.Inject;
import java.util.List;

@Controller
@Seguranca(tipoUsuario = TipoUsuario.TECNICO)
public class SetorController extends ControladorSisInt<Setor> {

    private SetorDao setorDao;
    private TarefaDao tarefaDao;
    private SetorNegocio setorNegocio;

    @Deprecated
    public SetorController(){
        this(null,null, null, null);
    }

    @Inject
    public SetorController(Result resultado, SetorDao setorDao, TarefaDao tarefaDao, SetorNegocio setorNegocio) {
        super(resultado);
        this.setorDao = setorDao;
        this.tarefaDao = tarefaDao;
        this.setorNegocio = setorNegocio;
    }

    public void lista(){
        resultado.include("setores", setorDao.listar());
    }

    public void form(){}

    @Transacional
    @Post("/setor")
    public void salvar(Setor setor){
        setorNegocio.salvar(setor);
        resultado.redirectTo(this).lista();
    }

    public void editar(Long id){
        Setor setor = setorNegocio.editar(id);
        resultado.include("setor",setor);
        resultado.of(this).form();
    }

    @Transacional
    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public void remover(Long id) {
        setorNegocio.remover(id);
        resultado.redirectTo(this).lista();
    }

    @Seguranca(tipoUsuario = TipoUsuario.TECNICO)
    public void tarefasPorSetor(Setor setor) {

        resultado.include("setores", setorNegocio.gerarListaOpcoesSetor());

        if (setor.getId() != null) {
            resultado.include("tarefas", setorNegocio.verTarefasPorSetor(setor.getId()));
        }
        resultado.of(this).tarefas();
    }

    public void tarefas() {

    }
}
