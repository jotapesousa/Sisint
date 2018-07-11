package br.pcrn.sisint.dominio;

import br.pcrn.sisint.conversor.ConvertivelOpcaoSelect;

/**
 * Created by samue on 09/09/2017.
 */
public enum StatusServico implements ConvertivelOpcaoSelect{
    CANCELADO("Cancelado"), EM_ESPERA("Aguardando execução"), EM_EXECUCAO("Em execução"), CONCLUIDO("Concluído");

    private String chave;

    StatusServico(String chave) {
        this.chave = chave;
    }

    @Override
    public String getChave() {
        return chave;
    }

    @Override
    public String getValor() {
        return this.toString();
    }
}
