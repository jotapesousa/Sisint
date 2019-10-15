package br.pcrn.sisint.dominio;

import br.pcrn.sisint.conversor.ConvertivelOpcaoSelect;

public enum StatusManutencao implements ConvertivelOpcaoSelect {

    VERIFICACAO_PREVIA("Verificação Prévia"),
    AGUARDANDO_MANUTENCAO("Aguardando Manutenção"),
    EM_MANUTENCAO("Em Manutenção"),
    CONCLUIDO("Concluído");

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
