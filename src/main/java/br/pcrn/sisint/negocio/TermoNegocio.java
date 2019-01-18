package br.pcrn.sisint.negocio;

import br.pcrn.sisint.dao.EquipamentoDao;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dao.TermoDao;
import br.pcrn.sisint.dao.UsuarioDao;
import br.pcrn.sisint.dominio.Equipamento;
import br.pcrn.sisint.dominio.Setor;
import br.pcrn.sisint.dominio.Termo;
import br.pcrn.sisint.dominio.Usuario;
import br.pcrn.sisint.dominio.relatorios.TermoGeral;
import br.pcrn.sisint.util.OpcaoSelect;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TermoNegocio {

    private TermoDao termoDao;
    private EquipamentoDao equipamentoDao;
    private SetorDao setorDao;
    private UsuarioDao usuarioDao;

    @Inject
    public TermoNegocio(EquipamentoDao equipamentoDao, SetorDao setorDao, UsuarioDao usuarioDao, TermoDao termoDao) {
        this.equipamentoDao = equipamentoDao;
        this.setorDao = setorDao;
        this.usuarioDao = usuarioDao;
        this.termoDao = termoDao;
    }

    @Deprecated
    public TermoNegocio() { this(null, null, null, null);}

    public List<OpcaoSelect> geraListaOpcoesUsuarios() {
        List<Usuario> todos = this.usuarioDao.listar().stream().collect(Collectors.toList());
        return todos.stream().map(
                usuario -> new OpcaoSelect(usuario.getNome(), usuario.getId()))
                .collect(Collectors.toList());
    }

    public List<OpcaoSelect> geraListaOpcoesEquipamentos() {
        List<Equipamento> todos = this.equipamentoDao.todos().stream().collect(Collectors.toList());
        return todos.stream().map(
                equipamento -> new OpcaoSelect(equipamento.getNome(), equipamento.getId()))
                .collect(Collectors.toList());
    }

    public List<OpcaoSelect> geraListaOpcoesSetores() {
        List<Setor> todos = this.setorDao.todos().stream().collect(Collectors.toList());
        return todos.stream().map(
                setor -> new OpcaoSelect(setor.getNome(), setor.getId()))
                .collect(Collectors.toList());
    }

    public List<TermoGeral> gerarTermoGeral(Termo termo) {
        List<TermoGeral> termosGerais = new ArrayList<TermoGeral>();
        TermoGeral termoGeral = null;
        Integer iterador = 1;

        for (Equipamento equip  : termo.getEquipamentos()) {
            termoGeral = new TermoGeral(
                    String.valueOf(termo.getNumero()),
                    String.valueOf(termo.getAno()),
                    String.valueOf(termo.getSetor()),
                    equip.getNome(),
                    String.valueOf(equip.getTombo()),
                    String.valueOf(equip.getNumeroSerie()),
                    iterador);
            termosGerais.add(termoGeral);
            iterador++;
        }
        return termosGerais;
    }

    public int numTermo() {
        return termoDao.numTermo();
    }

}
