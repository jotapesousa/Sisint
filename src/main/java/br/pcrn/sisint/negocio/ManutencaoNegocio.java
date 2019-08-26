package br.pcrn.sisint.negocio;

import br.pcrn.sisint.dao.*;
import br.pcrn.sisint.dominio.*;
import br.pcrn.sisint.util.OpcaoSelect;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManutencaoNegocio {

    private ManutencaoDao manutencaoDao;
    private SetorDao setorDao;
    private UsuarioDao usuarioDao;
    private EquipamentoDao equipamentoDao;

    @Inject
    private UsuarioLogado usuarioLogado;

    @Deprecated
    protected ManutencaoNegocio(){
        this(null, null,null, null);
    }

    @Inject
    public ManutencaoNegocio(ManutencaoDao manutencaoDao, UsuarioDao usuarioDao,
                             SetorDao setorDao, EquipamentoDao equipamentoDao) {
        this.manutencaoDao = manutencaoDao;
        this.usuarioDao = usuarioDao;
        this.setorDao = setorDao;
        this.equipamentoDao = equipamentoDao;
    }


    public List<OpcaoSelect> geraListaOpcoesUsuarios() {
        List<Usuario> todos = this.usuarioDao.todos().stream().collect(Collectors.toList());
        return todos.stream().map(
                usuario -> new OpcaoSelect(usuario.getNome(), usuario.getId()))
                .collect(Collectors.toList());
    }

    public List<OpcaoSelect> geraListaOpcoesSetor() {
        List<Setor> todos = this.setorDao.todos();
        return todos.stream().map(
                setor -> new OpcaoSelect(setor.getNome(), setor.getId()))
                .collect(Collectors.toList());
    }

    public List<OpcaoSelect> geraListaOpcoesEquipamentos() {
        List<Equipamento> todos = this.equipamentoDao.todos().stream().collect(Collectors.toList());
        return todos.stream().map(
                equipamento -> new OpcaoSelect(equipamento.getNome(), equipamento.getId()))
                .collect(Collectors.toList());
    }

    public void verificarStatus(Manutencao manutencao) {
        Long idEquipamento = manutencao.getEquipamento().getId();
        Equipamento equipamento = equipamentoDao.buscarPorId(idEquipamento);

        if (manutencao.getStatus() == StatusManutencao.AGUARDANDO_MANUTENCAO) {
            equipamento.setStatus(StatusEquipamento.QUEBRADO);
        } else if (manutencao.getStatus() == StatusManutencao.EM_MANUTENCAO) {
           equipamento.setStatus(StatusEquipamento.EM_CONSERTO);
        }
    }

    public void verificarConclusao(Manutencao manutencao, String checkConserto) {
        Long idEquipamento = manutencao.getEquipamento().getId();
        Equipamento equipamento = equipamentoDao.buscarPorId(idEquipamento);

        if (checkConserto.equals("OK")) {
            equipamento.setStatus(StatusEquipamento.OK);
        } else {
            equipamento.setStatus(StatusEquipamento.QUEBRADO);
        }
    }

    public void gerarLog(Manutencao manutencao) {
        LocalDateTime horario = LocalDateTime.now().plusSeconds(1l);
        if(manutencao.getId() == null) {
            manutencao.setLogManutencoes(new ArrayList<LogManutencao>());
            manutencao.getLogManutencoes().add(gerarLogCadastroManutencao(manutencao));
        } else {
            Manutencao manutencaoAntiga = manutencaoDao.buscarPorId(manutencao.getId());
            String comparaManutencao = compararManutencoes(manutencaoAntiga, manutencao);
            if (!comparaManutencao.equals("")) {
                manutencao.getLogManutencoes().add(gerarLogAtualizacaoManutencao(manutencao.getId(), horario, comparaManutencao));
            }
        }
    }

    public LogManutencao gerarLogCadastroManutencao(Manutencao manutencao){
        LogManutencao logManutencao = new LogManutencao();
//        logManutencao.setUsuario(usuarioLogado.getUsuario());
//        logManutencao.setLog("O usuario "+usuarioLogado.getUsuario().getNome()+" criou o servico " +servico.getCodigoServico()
//                +" com status "+
//                servico.getStatusServico().getChave());
//        logManutencao.setDataAlteracao(LocalDateTime.now());
        return logManutencao;
    }

    private LogManutencao gerarLogAtualizacaoManutencao(Long id, LocalDateTime horario, String comparaManutencao) {
        LogManutencao logManutencao = new LogManutencao();
//        logManutencao.setUsuario(usuarioLogado.getUsuario());
//        logManutencao.setLog("O usuario "+usuarioLogado.getUsuario().getNome()+" criou o servico " +servico.getCodigoServico()
//                +" com status "+
//                servico.getStatusServico().getChave());
//        logManutencao.setDataAlteracao(LocalDateTime.now());
        return logManutencao;
    }

    public String compararManutencoes(Manutencao manutencaoAntiga, Manutencao manutencaoNova) {
        String retorno = "";

//        if (manutencaoAntiga.getTecnico() == null) {
//            retorno += manutencaoNova.getTecnico().getNome() + " é o responsável pelo serviço!";
//        } else
        if (!manutencaoAntiga.getTecnico().equals(manutencaoNova.getTecnico())){
            retorno += "Técnico modificado! Antigo: '" + manutencaoAntiga.getTecnico().getNome() + "'.";
        }
        if (!manutencaoAntiga.getStatus().equals(manutencaoNova.getStatus())) {
            retorno += " Status da manutenção modificado! Antigo: '" + manutencaoAntiga.getStatus().getChave() + "'.";
        }
        if (!manutencaoAntiga.getDescricao().equals(manutencaoNova.getDescricao())) {
            retorno += " Descricao modificada! Antiga: '" + manutencaoAntiga.getDescricao() + "'.";
        }
        if (!manutencaoAntiga.getEquipamento().getId().equals(manutencaoNova.getEquipamento().getId())) {
            retorno += " Equipamento modificado! Tombo antigo: '" + manutencaoAntiga.getEquipamento().getTombo() + "'.";
        }
        if (!manutencaoAntiga.getDataFechamento().equals(manutencaoNova.getDataFechamento())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            retorno += " Data de Finalização Modificada! Antiga: '" + manutencaoAntiga.getDataFechamento().format(String.valueOf(formatter)) + "'.";
        }
        if (!manutencaoAntiga.getSetor().getId().equals(manutencaoNova.getSetor().getId())) {
            retorno += " Setor Modificado! Antigo: '" + manutencaoAntiga.getSetor().getNome() + "'.";
        }

        return retorno;
    }
}

