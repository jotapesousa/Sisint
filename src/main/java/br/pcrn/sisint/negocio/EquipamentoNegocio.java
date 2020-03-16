package br.pcrn.sisint.negocio;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.pcrn.sisint.controller.EquipamentoController;
import br.pcrn.sisint.dao.EquipamentoDao;
import br.pcrn.sisint.dominio.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipamentoNegocio {

    @Inject
    private EquipamentoDao equipamentoDao;

    @Inject
    private UsuarioLogado usuarioLogado;

//    Optional<Equipamento> equip;

//    public void verificarTombo(Equipamento equipamento, Result resultado) {
//
//        if (equipamento.getId() == null) {
//            equip = this.equipamentoDao.buscarPorTombo(equipamento.getTombo());
//
//            if (equip.isPresent()) {
//                resultado.include("mensagem", new SimpleMessage("error", "mensagem.equipamento.salvar.erroTombo"));
//                resultado.include("equipamento", equipamento);
//                resultado.include("setores", servicosNegocio.geraListaOpcoesSetor());
//                resultado.of(EquipamentoController.class).form();
//            }
//        }
//    }

    public EquipamentoNegocio () { this(null, null); }

    public EquipamentoNegocio(EquipamentoDao equipamentoDao, UsuarioLogado usuarioLogado) {
        this.equipamentoDao = equipamentoDao;
        this.usuarioLogado = usuarioLogado;
    }

    public boolean validarEquipamento(Equipamento equipamento) {
        if (equipamento.getId() == null) {
            if (equipamentoDao.verificarNSerie(equipamento.getNumeroSerie())) {
                System.out.println("Equipamento com numero de serie ja existente");
            } else {
                if (equipamentoDao.verificarTombo(equipamento.getTombo())) {
                    System.out.println("Equipamento com tombo ja existente");
                }
            }
        } else {

        }

        return true;
    }

    public void gerarLog(Equipamento equipamento) {
        LocalDateTime horario = LocalDateTime.now().plusSeconds(1l);
        if(equipamento.getId() == null) {
            equipamento.setLogEquipamentos(new ArrayList<LogEquipamento>());
            equipamento.getLogEquipamentos().add(gerarLogCadastroEquipamento(equipamento));
        } else {
            if (equipamento.getLogEquipamentos() == null) {
                equipamento.setLogEquipamentos(new ArrayList<LogEquipamento>());
            }
            Equipamento equipamentoAntigo = equipamentoDao.buscarPorId(equipamento.getId());
            String comparaEquipamento = compararEquipamentos(equipamentoAntigo, equipamento);
            if (!comparaEquipamento.equals("")) {
                equipamento.getLogEquipamentos().add(gerarLogAtualizacaoEquipamento(equipamento.getId(), horario, comparaEquipamento));
            }
        }
    }

    public LogEquipamento gerarLogCadastroEquipamento(Equipamento equipamento) {
        LogEquipamento logEquipamento = new LogEquipamento();
        logEquipamento.setUsuario(usuarioLogado.getUsuario());
        if (!equipamento.getTombo().equals("")) {
            logEquipamento.setLog("Equipamento cadastrado pelo usuário" + usuarioLogado.getUsuario().getNome() + " cadastrou o equipamento do tipo " +
                    equipamento.getTipo().getChave() + ", tombo " + equipamento.getTombo() + " com status " + equipamento.getStatus().getChave());
            logEquipamento.setLog("O usuario " + usuarioLogado.getUsuario().getNome() + " cadastrou o equipamento do tipo " +
                    equipamento.getTipo().getChave() + ", tombo " + equipamento.getTombo() + " com status " + equipamento.getStatus().getChave());
        }
        logEquipamento.setDataAlteracao(LocalDateTime.now());
        logEquipamento.setEquipamento(equipamento);
        return logEquipamento;
    }

    public LogEquipamento gerarLogAtualizacaoEquipamento(Long idEquipamento, LocalDateTime horario, String comparacao) {
        LogEquipamento logEquipamento = new LogEquipamento();

        Equipamento equipAntigo = equipamentoDao.buscarPorId(idEquipamento);
        String logString = "O usuario " + usuarioLogado.getUsuario().getNome() + " alterou este Equipamento. " +
                "\n" + comparacao;
        logEquipamento.setLog(logString);
        logEquipamento.setUsuario(usuarioLogado.getUsuario());
        logEquipamento.setDataAlteracao(horario);

        return logEquipamento;
    }

    public String compararEquipamentos(Equipamento equipamentoAntigo, Equipamento equipamentoNovo) {
        String retorno = "";

        if (!equipamentoAntigo.getNome().equals(equipamentoNovo.getNome())){
            retorno += "Nome do equipamento modificado! Antigo: '" + equipamentoAntigo.getNome() + "'.";
        }
        if (!equipamentoAntigo.getStatus().equals(equipamentoNovo.getStatus())) {
            retorno += " Status do equipamento modificado! Antigo: '" + equipamentoAntigo.getStatus().getChave() + "'.";
        }
        if (!equipamentoAntigo.getNumeroSerie().equals(equipamentoNovo.getNumeroSerie())) {
            retorno += " Número de Série modificado! Antigo:  '" + equipamentoAntigo.getNumeroSerie() + "'.";
        }
        if (!equipamentoAntigo.getDescricao().equals(equipamentoNovo.getDescricao())) {
            retorno += " Descricao modificada! Antiga: '" + equipamentoAntigo.getDescricao() + "'.";
        }
        if (!equipamentoAntigo.getTombo().equals(equipamentoNovo.getTombo())){
            retorno += " Tombo Modificado! Antigo: '" + equipamentoAntigo.getTombo() + "'.";
        }
        if (!equipamentoAntigo.getSetor().getId().equals(equipamentoNovo.getSetor().getId())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            retorno += " Setor modificado! Antigo: '" + equipamentoAntigo.getSetor().getNome() + "'.";
        }

        return retorno;
    }

    public JsonArray buscarEquipamentoPorTipo(TipoEquipamento tipoEquip) {
        List<Equipamento> equipamentos = equipamentoDao.buscarPorTipo(tipoEquip);
        JsonArray jsonArray = equipamentoJson(equipamentos);
        return jsonArray;
    }

    public JsonArray buscarEquipamentoPorTombo(Long tombo, TipoEquipamento tipoEquip) {
        List<Equipamento> equipamentos = equipamentoDao.listarPorTomboETipo(tombo, tipoEquip);
        JsonArray jsonArray = equipamentoJson(equipamentos);
        return jsonArray;
    }

    private JsonArray equipamentoJson(List<Equipamento> equipamentos) {
        JsonArray jsonArray = new JsonArray();

        for (Equipamento eq : equipamentos) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id",eq.getId());
            jsonObject.addProperty("nome",eq.getNome());
            jsonObject.addProperty("tombo",eq.getTombo());
            jsonObject.addProperty("tipo",eq.getTipo().getChave());
            jsonObject.addProperty("nserie",eq.getNumeroSerie());
            jsonObject.addProperty("descricao", eq.getDescricao());
            jsonObject.addProperty("status", eq.getStatus().getChave());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
