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

    @Override
    public Long contarPorSetor(Long id) {
        Query query = manager.createQuery("SELECT COUNT(s) FROM Servico s WHERE s.setor.id = :id")
                .setParameter("id", id);

        return (Long) query.getSingleResult();
    }


    public List<Servico> filtrarAPartirDeDESC(LocalDate dtMin) {
        Query query = manager.createQuery("SELECT s FROM Servico s WHERE s.dataFechamento > :dtDe ORDER BY s.dataFechamento DESC").setParameter("dtDe", dtMin);
        return query.getResultList();
    }

    public List<Servico> filtrarAteDataDESC(LocalDate dtMax) {
        Query query = manager.createQuery("SELECT s FROM Servico s WHERE s.dataFechamento < :dtAte ORDER BY s.dataFechamento DESC")
                .setParameter("dtAte", dtMax);
        return query.getResultList();
    }

    @Override
    public List<Servico> filtrarDeAteDataDESC(LocalDate dtDe, LocalDate dtAte) {
        Query query = manager.createQuery("SELECT s FROM Servico s WHERE (s.dataFechamento >= :dtDe AND s.dataFechamento <= :dtAte) ORDER BY s.dataFechamento DESC")
                .setParameter("dtDe", dtDe)
                .setParameter("dtAte", dtAte);
        return query.getResultList();
    }

    @Override
    public List<Object> contarDeAteDataDESC(LocalDate dtDe, LocalDate dtAte) {
        Query query = manager.createNativeQuery("SELECT COUNT(s) AS total, EXTRACT(MONTH FROM s.dataFechamento) AS mes, EXTRACT(YEAR FROM s.dataFechamento) AS ano FROM Servico s " +
                " WHERE s.dataFechamento > '"+dtDe.toString()+"' AND s.dataFechamento < '"+dtAte.toString()+"' GROUP BY ano,mes ORDER BY ano,mes");
        List<Object> informacoes = query.getResultList();
        return informacoes;
    }

    @Override
    public List<Servico> filtrarDeAteDataPorSetorDESC(Long id, LocalDate dtDe, LocalDate dtAte) {
        Query query = manager.createQuery("SELECT s FROM Servico s WHERE (s.setor.id = :id AND s.dataFechamento >= :dtDe AND s.dataFechamento <= :dtAte) ORDER BY s.dataFechamento DESC")
                .setParameter("id", id)
                .setParameter("dtDe", dtDe)
                .setParameter("dtAte", dtAte);
        return query.getResultList();
    }

    @Override
    public List<Servico> filtrarMaisRecentesPorSetor(Long id) {
        Query query = manager.createQuery("SELECT s FROM Servico s WHERE s.setor.id = :id ORDER BY s.dataFechamento DESC").setParameter("id", id).setMaxResults(10);
        return query.getResultList();
    }

//    @Override
//    public List<Servico> filtrarPorMesAno(int mes, int ano) {
//        Query query = manager.createQuery("SELECT s FROM Servico s WHERE (EXTRACT(year FROM s.dataFechamento)) = :ano AND (EXTRACT(month FROM s.dataFechamento) = :mes)")
//                .setParameter("ano", ano)
//                .setParameter("mes", mes);
//        return query.getResultList();
//    }

}
