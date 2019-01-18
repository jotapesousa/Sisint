package br.pcrn.sisint.dominio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Termo extends Entidade{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    private int ano;
    private LocalDate dataCriacao;
    private String codigoSei;
    private String nomeServidor;
    private String matriculaServidor;
    private LocalDate horaRecebimento;
    private boolean recebido;
    private boolean deletado;

    @ManyToOne(fetch = FetchType.EAGER)
    private Setor setor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Equipamento> equipamentos;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario tecnico;

    @Deprecated
    public Termo(){
    }

    @Override
    public Long getId() { return id; }

    @Override
    public void setId(Long id) { this.id = id; }

    public Termo(String texto) {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    public String getCodigoSei() {
        return codigoSei;
    }

    public void setCodigoSei(String codigoSei) {
        this.codigoSei = codigoSei;
    }

    public String getNomeServidor() {
        return nomeServidor;
    }

    public void setNomeServidor(String nomeServidor) {
        this.nomeServidor = nomeServidor;
    }

    public String getMatriculaServidor() {
        return matriculaServidor;
    }

    public void setMatriculaServidor(String matriculaServidor) {
        this.matriculaServidor = matriculaServidor;
    }

    public LocalDate getHoraRecebimento() {
        return horaRecebimento;
    }

    public void setHoraRecebimento(LocalDate horaRecebimento) {
        this.horaRecebimento = horaRecebimento;
    }

    public boolean isRecebido() {
        return recebido;
    }

    public void setRecebido(boolean recebido) {
        this.recebido = recebido;
    }
}
