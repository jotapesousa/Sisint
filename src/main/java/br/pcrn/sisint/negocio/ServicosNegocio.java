package br.pcrn.sisint.negocio;

import br.pcrn.sisint.dao.ServicoDao;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dao.TarefaJpaDao;
import br.pcrn.sisint.dao.UsuarioDao;
import br.pcrn.sisint.dominio.*;
import br.pcrn.sisint.util.OpcaoSelect;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicosNegocio {

    private ServicoDao servicoDao;
    private SetorDao setorDao;
    private UsuarioDao usuarioDao;

    @Inject
    private TarefaJpaDao tarefaJpaDao;

    @Inject
    private UsuarioLogado usuarioLogado;

    public ServicosNegocio(){
        this(null, null,null);
    }

    @Inject
    public ServicosNegocio(ServicoDao servicoDao, UsuarioDao usuarioDao, SetorDao setorDao) {
        this.servicoDao = servicoDao;
        this.usuarioDao = usuarioDao;
        this.setorDao = setorDao;
    }

    public List<OpcaoSelect> geraListaOpcoesUsuarios() {
        List<Usuario> todos = this.usuarioDao.listarAptos().stream().collect(Collectors.toList());
        return todos.stream().map(
                usuario -> new OpcaoSelect(usuario.getNome(), usuario.getId()))
                .collect(Collectors.toList());
    }

    public List<OpcaoSelect> geraListaOpcoesSetor() {
        List<Setor> todos = this.setorDao.todos();
        todos.sort((a,b) ->a.getNome().compareTo(b.getNome()));
        return todos.stream().map(
                setor -> new OpcaoSelect(setor.getNome(), setor.getId()))
                .collect(Collectors.toList());
    }

    public String gerarCodigoServico () {
        Long qt = servicoDao.contarTotalServicos();
        return "S" + LocalDate.now().getYear() + qt;
    }

    public void gerarLog(Servico servico) {
        LocalDateTime horario = LocalDateTime.now().plusSeconds(1l);
        if(servico.getId() == null) {
            servico.setLogServicos(new ArrayList<LogServico>());
            servico.getLogServicos().add(gerarLogAberturaServico(servico));
            if(servico.getTarefas() != null) {
                for(Tarefa tarefa : servico.getTarefas()) {
                    servico.getLogServicos().add(gerarLogAberturaTarefa(tarefa, horario));
                    horario = horario.plusSeconds(1l);
                }
            }
        } else {
            Servico servicoAntigo = servicoDao.BuscarPorId(servico.getId());
            String comparaServico = compararServicos(servicoAntigo, servico);
            if (!comparaServico.equals("")) {
                servico.getLogServicos().add(gerarLogAtualizacaoServico(servico.getId(), horario, comparaServico));
            }

            if(servico.getTarefas() != null) {
                for(Tarefa tarefa : servico.getTarefas()) {
                    if(tarefa.getId() != null) {
                        //verificar estado de cada tarefa
                        Tarefa tarefaAntiga = tarefaJpaDao.buscarPorId(tarefa.getId());
                        String comparacao = compararTarefas(tarefaAntiga, tarefa);
                        if(!(comparacao.equals(""))) {
                            servico.getLogServicos().add(gerarLogAtualizacaoTarefa(tarefa,horario,comparacao));
                        }
                    } else {
                        servico.getLogServicos().add(gerarLogAberturaTarefa(tarefa,horario));
                    }
                    horario.plusSeconds(1l);
                }
            }
        }
    }

    public LogServico gerarLogAberturaServico(Servico servico) {
        LogServico logServico = new LogServico();
        logServico.setUsuario(usuarioLogado.getUsuario());
        logServico.setLog("O usuario "+usuarioLogado.getUsuario().getNome()+" criou o servico " +servico.getCodigoServico()
                +" com status "+
                servico.getStatusServico().getChave());
        logServico.setDataAlteracao(LocalDateTime.now());
        return logServico;
    }

    public LogServico gerarLogAberturaTarefa(Tarefa tarefa, LocalDateTime horario) {
        LogServico logServico = new LogServico();
        logServico.setLog("O usuario "+usuarioLogado.getUsuario().getNome()+" criou a tarefa " +tarefa.getCodigoTarefa()
                +" com status "+
                tarefa.getStatusTarefa().getChave());
        logServico.setDataAlteracao(horario);
        logServico.setUsuario(usuarioLogado.getUsuario());
        return logServico;
    }

    public void gerarCodigoTarefas(String codigoServico, List<Tarefa> tarefas) {
        Long id = tarefaJpaDao.contarTotalTarefas();
        String codigoTarefa;
        if(!tarefas.isEmpty()){
            for (Tarefa tarefa: tarefas) {
                if(tarefa.getCodigoTarefa() == null || tarefa.getCodigoTarefa().equals("")) {
                    codigoTarefa = codigoServico + "T" + id;
                    tarefa.setCodigoTarefa(codigoTarefa);
                    id = id + 1;
                }
            }
        }
    }

    public LogServico gerarLogAtualizacaoTarefa(Tarefa tarefa, LocalDateTime horario, String comparacao){
        LogServico logServico =  new LogServico();
        Tarefa tarefaAntiga = tarefaJpaDao.buscarPorId(tarefa.getId());
        String logString = "O usuario " + usuarioLogado.getUsuario().getNome() + " alterou a tarefa " + tarefa.getCodigoTarefa()
                +". "+comparacao;
        logServico.setLog(logString);
        logServico.setUsuario(usuarioLogado.getUsuario());
        logServico.setDataAlteracao(horario);

        return logServico;
    }

    public LogServico gerarLogAtualizacaoServico(Long idServico, LocalDateTime horario, String comparacao) {
        LogServico logServico = new LogServico();

        Servico servicoAntigo = servicoDao.BuscarPorId(idServico);
        String logString = "O usuario " + usuarioLogado.getUsuario().getNome() + " alterou este serviço. " +
                "\n" + comparacao;
        logServico.setLog(logString);
        logServico.setUsuario(usuarioLogado.getUsuario());
        logServico.setDataAlteracao(horario);

        return logServico;
    }

    public String compararTarefas(Tarefa tarefaAntiga, Tarefa tarefaNova) {
        String retorno = "";
        if(!tarefaAntiga.getTitulo().equals(tarefaNova.getTitulo())) {
            retorno = retorno + "Titulo modificado de \"" +tarefaAntiga.getTitulo()+ "\" para \"" +tarefaNova.getTitulo() + "\". ";
        }
        if(!tarefaAntiga.getStatusTarefa().equals(tarefaNova.getStatusTarefa())) {
            retorno = retorno + "Status modificado de " +tarefaAntiga.getStatusTarefa().getChave()
                    + " para " +tarefaNova.getStatusTarefa().getChave() + ". ";
        }
        if(tarefaAntiga.getDescricao() != null) {
            if(!tarefaAntiga.getDescricao().equals(tarefaNova.getDescricao())) {
                retorno = retorno + "Descrição modificada de " +tarefaAntiga.getDescricao()+ " para " +tarefaNova.getDescricao() + ". ";
            }
        }
        if(!tarefaAntiga.getTitulo().equals(tarefaNova.getTitulo())) {
            retorno = retorno + "Titulo modificado de " +tarefaAntiga.getTitulo()+ " para " +tarefaNova.getTitulo() + ". ";
        }
        return retorno;
    }

    public String compararServicos(Servico servicoAntigo, Servico servicoNovo) {
        String retorno = "";

        if (servicoAntigo.getTecnico() == null) {
            retorno += servicoNovo.getTecnico().getNome() + " é o responsável pelo serviço!";
        } else if (!servicoAntigo.getTecnico().equals(servicoNovo.getTecnico())){
            retorno += "Técnico modificado! Antigo: '" + servicoAntigo.getTecnico().getNome() + "'.";
        }
        if (!servicoAntigo.getStatusServico().equals(servicoNovo.getStatusServico())) {
            retorno += " Status do servico modificado! Antigo: '" + servicoAntigo.getStatusServico().getChave() + "'.";
        }
        if (!servicoAntigo.getTitulo().equals(servicoNovo.getTitulo())) {
            retorno += " Titulo modificado! Antigo:  '" + servicoAntigo.getTitulo() + "'.";
        }
        if (!servicoAntigo.getDescricao().equals(servicoNovo.getDescricao())) {
            retorno += " Descricao modificada! Antiga: '" + servicoAntigo.getDescricao() + "'.";
        }
        if (!servicoAntigo.getPrioridade().equals(servicoNovo.getPrioridade())){
            retorno += " Prioridade Modificada! Antiga: '" + servicoAntigo.getPrioridade().getChave() + "'.";
        }
        if (!servicoAntigo.getDataFechamento().equals(servicoNovo.getDataFechamento())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            retorno += " Data de Finalização Modificada! Antiga: '" + servicoAntigo.getDataFechamento().format(formatter) + "'.";
        }
        if (!servicoAntigo.getSetor().getId().equals(servicoNovo.getSetor().getId())) {
            retorno += " Setor Modificado! Antigo: '" + servicoAntigo.getSetor().getNome() + "'.";
        }

        return retorno;
    }

    public boolean verificarConclusaoServico(List<Tarefa> tarefas) {
        boolean concluida = true;
        for(Tarefa tarefa : tarefas) {
            if(!tarefa.getStatusTarefa().equals(StatusTarefa.CONCLUIDO)) {
                concluida = false;
            }
        }
        return concluida;
    }

    public void gerarLogAssumirServico(Servico servico) {
        LogServico logServico = new LogServico();
        String logString = servico.getTecnico().getNome() + " é o responsável por este serviço!";

        logServico.setUsuario(servico.getTecnico());
        logServico.setDataAlteracao(LocalDateTime.now().plusSeconds(1l));
        logServico.setLog(logString);

        servico.getLogServicos().add(logServico);
    }
}
