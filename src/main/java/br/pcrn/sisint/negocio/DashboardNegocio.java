package br.pcrn.sisint.negocio;

import br.com.caelum.vraptor.Result;
import br.pcrn.sisint.dao.ServicoDao;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dominio.Setor;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DashboardNegocio {

    private Result resultado;
    private ServicoDao servicoDao;
    private SetorDao setorDao;

    protected  DashboardNegocio() {
        this(null,null, null);
    }

    @Inject
    public DashboardNegocio(Result resultado, ServicoDao servicoDao, SetorDao setorDao) {
        this.resultado = resultado;
        this.servicoDao = servicoDao;
        this.setorDao = setorDao;
    }

    public JsonElement dashServicos() {
        JsonObject jsonDash = new JsonObject();


        return null;
    }

    public JsonElement servicosPorSetor() {
        List<Setor> setores = setorDao.listar();
        JsonObject jsonDash = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        Long total = servicoDao.contarTotalServicos();

        for ( Setor setor : setores) {
            Long numServicos = servicoDao.contarPorSetor(setor.getId());
            Double porcentagem = Double.valueOf(numServicos) / Double.valueOf(total);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("setor", setor.getNome());
            jsonObject.addProperty("quantidade", numServicos);
            jsonObject.addProperty("porcentagem", porcentagem);
            jsonArray.add(jsonObject);
        }
        jsonDash.addProperty("total", total);
        jsonDash.add("servicos", jsonArray);
        jsonDash.add("dates", informacoesDataEntrada());
        return jsonDash;
    }


    public JsonElement informacoesDataEntrada () {
        LocalDate agora = LocalDate.now();
        LocalDate inicio = agora.minusYears(1l);
        List<Object> informacoes = servicoDao.contarDeAteDataDESC(inicio,agora);
        return gerarJsonDeServicosPorData(informacoes);
    }

    private JsonElement gerarJsonDeServicosPorData (List<Object> informacoes) {
        LocalDate localDate = LocalDate.now().minusYears(1l).plusMonths(1);
        JsonArray jsonArray = new JsonArray();
        for (int i=0; i<12; i++) {
            int mes = localDate.getMonthValue();
            int ano = localDate.getYear();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("date",customizarData(localDate));
            jsonObject.addProperty("quantidade",0);
            for(Object info : informacoes) {
//                if( (int) info. == ano && (int) info.get(1) == mes) {
//                    jsonObject.addProperty("quantidade",info.get);
//                }
            }
            localDate = localDate.plusMonths(1l);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    private String customizarData(LocalDate date) {
        if (date.getMonthValue() == 1){
            return "Jan" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 2){
            return "Fev" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 3){
            return "Mar" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 4){
            return "Abr" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 5){
            return "Mai" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 6){
            return "Jun" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 7){
            return "Jul" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 8){
            return "Ago" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 9){
            return "Set" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 10){
            return "Out" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 11){
            return "Nov" + "/"+date.getYear();
        }
        else if(date.getMonthValue() == 12){
            return "Dez" + "/"+date.getYear();
        }
        return "";
    }
}
