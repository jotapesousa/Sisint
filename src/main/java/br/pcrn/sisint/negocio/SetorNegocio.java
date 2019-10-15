package br.pcrn.sisint.negocio;

import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dao.TarefaDao;
import br.pcrn.sisint.dominio.Setor;
import br.pcrn.sisint.dominio.Tarefa;
import br.pcrn.sisint.util.OpcaoSelect;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class SetorNegocio {

    private SetorDao setorDao;
    private TarefaDao tarefaDao;

    @Deprecated
    public SetorNegocio() { this(null, null); }

    @Inject
    public SetorNegocio(SetorDao setorDao, TarefaDao tarefaDao) {
        this.setorDao = setorDao;
        this.tarefaDao = tarefaDao;
    }

    public void salvar(Setor setor){
        setorDao.salvar(setor);
    }

    public Setor editar(Long id) {
        return setorDao.buscarPorId(id);
    }

    public void remover(Long id) {
        Setor setor = setorDao.buscarPorId(id);
        setor.setDeletado(true);
        this.setorDao.salvar(setor);
    }

    public List<Tarefa> verTarefasPorSetor(Long id){
        return tarefaDao.filtrarMaisRecentesPorSetorDESC(id);
    }

    public List<OpcaoSelect> gerarListaOpcoesSetor() {
        List<Setor> todos = this.setorDao.todos();
        todos.sort((a,b) ->a.getNome().compareTo(b.getNome()));
        return todos.stream().map(
                setor -> new OpcaoSelect(setor.getNome(), setor.getId()))
                .collect(Collectors.toList());
    }

}
