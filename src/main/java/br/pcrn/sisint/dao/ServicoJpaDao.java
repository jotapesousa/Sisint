package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by samue on 17/09/2017.
 */
public class ServicoJpaDao extends EntidadeJpaDao<Servico> implements ServicoDao {
    @Inject
    private TarefaDao tarefaDao;

    @Inject
    UsuarioLogado usuarioLogado;

    @Deprecated
    public ServicoJpaDao() {
        this(null);
    }

    @Inject
    public ServicoJpaDao(EntityManager entityManager) {
        super(entityManager, Servico.class);
    }

    @Override
    public Servico salvar(Servico servico) {
        referenciarLogsTarefas(servico);
        removerTarefas(servico);
        return super.salvar(servico);
    }

    private void removerTarefas(Servico servico) {
        boolean deletar = true;
        if (servico.getId() != null) {
            Servico servicoBanco = buscarPorId(servico.getId());
            if (servicoBanco.getTarefas() != null) {
                for (Tarefa tarefa : servicoBanco.getTarefas()) {
                    if (servico.getTarefas() != null) {
                        for (Tarefa tarefaAux : servico.getTarefas()) {
                            if (tarefa.getId() == tarefaAux.getId()) {
                                deletar = false;
                            }
                        }
                    } else {
                        deletar = true;
                    }
                    if (deletar == true) {
                        LogServico logServico = new LogServico();

                        logServico.setUsuario(usuarioLogado.getUsuario());
                        logServico.setServico(servico);
                        logServico.setDataAlteracao(LocalDateTime.now());
                        logServico.setLog("O usuário " + usuarioLogado.getUsuario().getNome() + " deletou a tarefa " + tarefa.getCodigoTarefa() + ".");
                        servico.getLogServicos().add(logServico);
//                        manager.refresh(servicoBanco);
                        tarefa.setDeletado(true);
                    }
                    deletar = true;
                }
            }
        }
    }

    @Override
    public List<Servico> listar() {
        return super.listar().stream().collect(Collectors.toList());
    }

    private void referenciarLogsTarefas(Servico servico) {
        List<Tarefa> tarefas = servico.getTarefas();
        if (servico.getTarefas() != null) {
            for (Tarefa tarefa : tarefas) {
                if (tarefa.getServico() == null) {
                    tarefa.setServico(servico);
                }
                if (tarefa.getId() == null) {
                    tarefa.setDataAbertura(LocalDate.now());
                }
            }
        }
        if (servico.getLogServicos() != null) {
            for (LogServico logServico : servico.getLogServicos()) {
                if (logServico.getServico() == null) {
                    logServico.setServico(servico);
                }
            }
        }
    }

    @Override
    public Long contarTotalServicos() {
        Query query = manager.createQuery("select count(s) from Servico s where s.deletado = false");
        return (Long) query.getSingleResult();
    }

    @Override
    public Long meusServicos() {
        Query query = manager.createQuery("select count(s) from Servico s where (s.statusServico = 'EM_EXECUCAO' or " +
                "s.statusServico = 'EM_ESPERA') and s.tecnico.id = :usuario and s.deletado = false");
        query.setParameter("usuario", usuarioLogado.getUsuario().getId());
        return (Long) query.getSingleResult();
    }

    @Override
    public Long contarServicosStatus(StatusServico statusServico) {
        Query query = manager.createQuery("select count(s) from Servico s where s.deletado = false AND s.statusServico = :status");
        query.setParameter("status", statusServico);

        return (Long) query.getSingleResult();
    }

    @Override
    public List<Servico> listarServicos() {
        Query query = manager.createQuery("select s from Servico s where s.deletado = false");
        return query.getResultList();
    }

    @Override
    public List<Servico> listarMeusServicos(Long id) {
        Query query = manager.createQuery("select s from Servico s where (s.statusServico = 'EM_EXECUCAO' OR s.statusServico = 'EM_ESPERA') AND s.deletado = false AND s.tecnico.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Servico> listarServicosEmAberto() {
        Query query = manager.createQuery("select s from Servico s where s.deletado = false AND s.statusServico = :status");
        query.setParameter("status", StatusServico.EM_ESPERA);
        return query.getResultList();
    }

    @Override
    public Long servicoPorSetor(Long id) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT count(p) from Servico p ");
        if(id > 0l) {
            queryString.append(" WHERE p.setor.id = :idSetor ");
        }
        Query query = manager.createQuery(queryString.toString());
        if(id > 0l) {
            query.setParameter("idSetor",id);
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Servico BuscarPorId(Long id) {
        Query query = manager.createQuery("select s from Servico s where s.id = :id");
        query.setParameter("id", id);
        query.setMaxResults(1);
        Optional<Servico> servico = query.getResultList().stream().findFirst();
        if(servico.isPresent()){
            return servico.get();
        }
        return null;
    }

    public void salvarLogServico(LogServico logServico) {
        Servico servico = buscarPorId(logServico.getServico().getId());
        servico.getLogServicos().add(logServico);
    }

    public void verificarConlusaoEAtualizar(Long id) {
        Servico servico = buscarPorId(id);
        boolean concluida = true;
        if (servico.getTarefas() != null) {
            for (Tarefa tarefa : servico.getTarefas()) {
                if (!tarefa.getStatusTarefa().equals(StatusTarefa.CONCLUIDO)) {
                    concluida = false;
                }
            }
        } else {
            concluida = false;
        }

        if(concluida) {
            servico.setStatusServico(StatusServico.CONCLUIDO);
//            manager.merge(servico);
        }
    }

}
