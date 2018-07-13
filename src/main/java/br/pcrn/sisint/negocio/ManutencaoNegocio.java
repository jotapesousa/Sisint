package br.pcrn.sisint.negocio;

import br.pcrn.sisint.dao.*;
import br.pcrn.sisint.dominio.*;
import br.pcrn.sisint.util.OpcaoSelect;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ManutencaoNegocio {

    private EntidadeDao<Manutencao> dao;
    private SetorDao setorDao;
    private UsuarioDao usuarioDao;
    private EquipamentoDao equipamentoDao;

    @Deprecated
    protected ManutencaoNegocio(){
        this(null, null,null, null);
    }

    @Inject
    public ManutencaoNegocio(EntidadeDao<Manutencao> dao, UsuarioDao usuarioDao,
                             SetorDao setorDao, EquipamentoDao equipamentoDao) {
        this.dao = dao;
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
}
