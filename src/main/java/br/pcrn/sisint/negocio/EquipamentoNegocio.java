package br.pcrn.sisint.negocio;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.pcrn.sisint.controller.EquipamentoController;
import br.pcrn.sisint.dao.EquipamentoDao;
import br.pcrn.sisint.dominio.Equipamento;

import javax.inject.Inject;
import java.util.Optional;

public class EquipamentoNegocio {

    @Inject
    private EquipamentoDao equipamentoDao;

    private ServicosNegocio servicosNegocio;

    Optional<Equipamento> equip;

    public void verificarTombo(Equipamento equipamento, Result resultado) {

        if (equipamento.getId() == null) {
            equip = this.equipamentoDao.buscarPorTombo(equipamento.getTombo());

            if (equip.isPresent()) {
                resultado.include("mensagem", new SimpleMessage("error", "mensagem.equipamento.salvar.erroTombo"));
                resultado.include("equipamento", equipamento);
                resultado.include("setores", servicosNegocio.geraListaOpcoesSetor());
                resultado.of(EquipamentoController.class).form();
            }
        }
    }
}
