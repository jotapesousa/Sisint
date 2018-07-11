package br.pcrn.sisint.dominio;

import javax.persistence.*;

/**
 * Created by samue on 09/09/2017.
 */
@Entity
public class Manutencao extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeSolicitante;
    private String codigoManutencao;
    private String dataAbertura;
    private String dataFechamento;
    private String descricao;
    private boolean deletado;

    @ManyToOne
    private Setor setor;

    @OneToOne(cascade = CascadeType.ALL)
    private Arquivo arquivo;

    @ManyToOne
    private Usuario tecnico;

    @Enumerated(EnumType.STRING)
    private StatusManutencao status;

    @OneToOne
    private Equipamento equipamento;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getCodigoManutencao() {
        return codigoManutencao;
    }

    public void setCodigoManutencao(String codigoManutencao) {
        this.codigoManutencao = codigoManutencao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) { this.setor = setor; }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    //    public LocalDate getDataAbertura() {
//        return dataAbertura;
//    }
//
//    public void setDataAbertura(LocalDate dataAbertura) {
//        this.dataAbertura = dataAbertura;
//    }
//
//    public LocalDate getDataFechamento() {
//        return dataFechamento;
//    }
//
//    public void setDataFechamento(LocalDate dataFechamento) {
//        this.dataFechamento = dataFechamento;
//    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public StatusManutencao getStatus() {  return status; }

    public void setStatus(StatusManutencao status) { this.status = status; }

    public Equipamento getEquipamento() {  return equipamento;  }

    public void setEquipamento(Equipamento equipamento) { this.equipamento = equipamento; }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }
}
