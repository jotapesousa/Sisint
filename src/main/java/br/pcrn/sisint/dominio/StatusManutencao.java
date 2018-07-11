package br.pcrn.sisint.dominio;

import br.pcrn.sisint.conversor.ConvertivelOpcaoSelect;

public enum StatusManutencao implements ConvertivelOpcaoSelect {
    AGUARDANDO_MANUTENCAO("Aguardando Manutencao"), EM_MANUTENCAO("Em Manutencao"), CONCLUIDO("Concluído");

    private String chave;

    StatusManutencao(String chave) {
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
