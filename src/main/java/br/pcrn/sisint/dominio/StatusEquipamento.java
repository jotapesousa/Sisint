package br.pcrn.sisint.dominio;

import br.pcrn.sisint.conversor.ConvertivelOpcaoSelect;

public enum StatusEquipamento implements ConvertivelOpcaoSelect {
    OK("OK"), EM_CONSERTO("Em Conserto"), QUEBRADO("Quebrado");

    private String chave;

    StatusEquipamento(String chave) {
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
