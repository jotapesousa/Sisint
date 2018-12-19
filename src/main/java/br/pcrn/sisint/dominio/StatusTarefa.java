package br.pcrn.sisint.dominio;

import br.pcrn.sisint.conversor.ConvertivelOpcaoSelect;

/**
 * Created by samue on 09/09/2017.
 */
public enum StatusTarefa implements ConvertivelOpcaoSelect{
    EM_ESPERA("Aguardando Execução"), EM_EXECUCAO("Em Execução"), CONCLUIDO("Concluído");

    private String chave;

    StatusTarefa(String chave) {
        this.chave = chave;
    }

    public String getChave() {
        return chave;
    }

    public String getValor() {
        return this.toString();
    }

}
