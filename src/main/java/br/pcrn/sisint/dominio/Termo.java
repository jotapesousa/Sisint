package br.pcrn.sisint.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Termo extends Entidade{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String ano;

    @ManyToOne(fetch = FetchType.EAGER)
    private Setor setor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Equipamento> equipamentos;

    public Termo(){

    }

    @Override
    public Long getId() { return id; }

    @Override
    public void setId(Long id) { this.id = id; }

    public Termo(String texto) {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAno() { return ano;  }

    public void setAno(String ano) { this.ano = ano; }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
}
