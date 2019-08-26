package br.pcrn.sisint.dominio;

import br.pcrn.sisint.conversor.ConvertivelOpcaoSelect;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public enum TipoEquipamento implements ConvertivelOpcaoSelect {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String nome;
//
//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }

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
