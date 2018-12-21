package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.*;
import br.pcrn.sisint.dominio.*;
import br.pcrn.sisint.negocio.TarefaNegocio;
import br.pcrn.sisint.util.OpcaoSelect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TarefasController extends ControladorSisInt<Tarefa> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TarefasController.class);
    private TarefaDao tarefaDao;
    private EntidadeDao<Tarefa> dao;

    @Inject
    private UsuarioLogado usuarioLogado;

    @Inject
    private UsuarioDao usuarioDao;

    @Inject
    private TarefaNegocio tarefaNegocio;

    @Deprecated
    public TarefasController() {
        this(null,null, null);
    }

    @Inject
    public TarefasController(Result resultado, EntidadeDao<Tarefa> dao, TarefaDao tarefaDao) {
        super(resultado);
        this.tarefaDao = tarefaDao;
        this.dao = dao;
    }

    public void form(){
        resultado.include("status", OpcaoSelect.toListaOpcoes(StatusTarefa.values()));
        resultado.include("usuarios", geraListaOpcoesUsuarios());
    }

    public List<OpcaoSelect> geraListaOpcoesUsuarios() {
        List<Usuario> todos = this.usuarioDao.listar().stream().collect(Collectors.toList());
        return todos.stream().map(
                usuario -> new OpcaoSelect(usuario.getNome(), usuario.getId()))
                .collect(Collectors.toList());
    }

    @Transacional
    public void salvar(Tarefa tarefa) {
        tarefaNegocio.salvar(tarefa);
        resultado.include("mensagem",new SimpleMessage("success","mensagem.salvar.sucesso"));
        resultado.redirectTo(this).minhasTarefas();
    }

    public void editar(Long id) {
        Tarefa tarefa = tarefaDao.buscarPorId(id);
        resultado.include("tarefa",tarefa);
        resultado.include("status", OpcaoSelect.toListaOpcoes(StatusTarefa.values()));
        resultado.include("usuarios", geraListaOpcoesUsuarios());
        resultado.of(this).form();
    }

    @Transacional
    public void remover(Long id) {
        Tarefa tarefa = tarefaDao.buscarPorId(id);
        tarefa.setDeletado(true);
        resultado.include("mensagem", new SimpleMessage("success","mensagem.salvar.sucesso"));
        resultado.redirectTo(this).tarefasAbertas();
    }

    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public void lista(){
        this.resultado.include("tarefas", tarefaDao.listar());

    }

    public void minhasTarefas () {
        List<Tarefa> minhasTarefas =  tarefaDao.minhasTarefas();
        resultado.include("tarefas", minhasTarefas);
    }

    public void tarefasAbertas () {
        List<Tarefa> tarefasAbertas =  tarefaDao.tarefasEmAberto();
        resultado.include("tarefas", tarefasAbertas);
    }

    public void tarefasConcluidas () {
        List<Tarefa> tarefasConcluidas =  tarefaDao.tarefasConcluidas();
        resultado.include("tarefas", tarefasConcluidas);
    }

    public void detalhes(Long id){
        Tarefa tarefa = this.tarefaDao.buscarPorId(id);

        this.resultado.include("tarefa", tarefa);
    }

    @Transacional
    public void concluir(Long id) {
        Tarefa tarefa = this.tarefaDao.buscarPorId(id);

        tarefa.setStatusTarefa(StatusTarefa.CONCLUIDO);
        this.tarefaNegocio.salvar(tarefa);

        resultado.redirectTo(this).minhasTarefas();
    }

    @Transacional
    public void salvarNota(Nota nota) {
        nota.setDataCriacao(LocalDateTime.now());
        nota.setTecnico(usuarioLogado.getUsuario());

        Tarefa tarefaNota = this.dao.buscarPorId(nota.getTarefa().getId());
        tarefaNota.getNotas().add(nota);
        this.dao.salvar(tarefaNota);
        this.resultado.include("mensagem", new SimpleMessage("success","mensagem.salvar.sucesso"));
        this.resultado.redirectTo(this).detalhes(tarefaNota.getId());

    }
}
