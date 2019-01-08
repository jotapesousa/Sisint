package br.pcrn.sisint.dominio;

import br.pcrn.sisint.conversor.ConvertivelOpcaoSelect;

public enum TipoEquipamento implements ConvertivelOpcaoSelect {
    MOUSE("Mouse"), TECLADO("Teclado"), MONITOR("Monitor"), NOBREAK("No-Break"), ESTABILIZADOR("Estabilizador"), CPU("CPU"),
    NOTEBOOK("Notebook"), IMPRESSORA("Impressora"), USB("USB"), MODEM("Modem"), SWITCH("Switch"), WEBCAM("Webcam"), HD("HD"),
    SSD("SSD"), MICROSD("MicroSD"), OUTROS("Outros");

    private String chave;

    TipoEquipamento(String chave) {
        this.chave = chave;
    }

    public String getChave() {
        return chave;
    }

    public String getValor() {
        return this.toString();
    }
}
