package br.pcrn.sisint.negocio;

import javax.inject.Inject;

import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.ServicoDao;
import br.pcrn.sisint.dao.TarefaDao;
import br.pcrn.sisint.dominio.Tarefa;
import br.pcrn.sisint.dominio.LogServico;
import br.pcrn.sisint.dominio.UsuarioLogado;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class TarefaNegocio {

    @Inject
    private TarefaDao tarefaDao;

    @Inject
    private ServicoDao servicoDao;

    @Inject
    private UsuarioLogado usuarioLogado;

    @Transacional
    public void salvar(Tarefa tarefa) {
        Tarefa tarefaBanco = tarefaDao.buscarPorId(tarefa.getId());

        String mensagemLog = "O usuário " + usuarioLogado.getUsuario().getNome() + " alterou a tarefa. " + compararTarefaEGerarLog(tarefa, tarefaBanco);
        LogServico logServico = new LogServico();
        logServico.setLog(mensagemLog);
        logServico.setDataAlteracao(LocalDateTime.now());
        logServico.setServico(tarefa.getServico());
        logServico.setUsuario(usuarioLogado.getUsuario());
        tarefaDao.salvar(tarefa);
        servicoDao.salvarLogServico(logServico);
        servicoDao.verificarConlusaoEAtualizar(tarefa.getServico().getId());
    }


    private String compararTarefaEGerarLog(Tarefa tarefa, Tarefa tarefaBanco) {
        String retorno = "";
        if (!tarefaBanco.getTitulo().equals(tarefa.getTitulo())) {
            retorno = retorno + "Titulo modificado de " + tarefaBanco.getTitulo() + " para " + tarefa.getTitulo() + ". ";
        }
        if (!tarefaBanco.getStatusTarefa().equals(tarefa.getStatusTarefa())) {
            retorno = retorno + "Status modificado de " + tarefaBanco.getStatusTarefa().getChave()
                    + " para " + tarefa.getStatusTarefa().getChave() + ". ";
        }
        if(tarefaBanco.getDescricao() != null ) {
            if (!tarefaBanco.getDescricao().equals(tarefa.getDescricao())) {
                retorno = retorno + "Descrição modificada de " + tarefaBanco.getDescricao() + " para " + tarefa.getDescricao() + ". ";
            }
        } else {
            if(tarefa.getDescricao() != null) {
                retorno = retorno + "Descrição modificada para " + tarefa.getDescricao() + ". ";
            }
        }
        return retorno;
    }

    private void verificarConclusaoServico(Tarefa tarefa) {

    }

}
