package br.pcrn.sisint.dominio.relatorios;

public class RelatorioServico {

    private String setorServico;
    private Integer numServicos;
    private Integer totalRegistro;
    private String dtDe;
    private String dtAte;
    private String dataFechamento;
    private String tecnicoResponsavel;
    private String titulo;

    public RelatorioServico(String setorServico, Integer numServicos, Integer totalRegistro, String dtDe, String dtAte) {
        this.setorServico = setorServico != null ? setorServico : "";
        this.dtDe = dtDe != null ? dtDe : "";
        this.dtAte = dtAte != null ? dtAte : "";
        this.numServicos = numServicos;
        this.totalRegistro = totalRegistro;
    }

    public RelatorioServico(String setorServico, Integer totalRegistro, String dataFechamento, String tecnicoResponsavel, String titulo) {
        this.setorServico = setorServico != null ? setorServico : "";
        this.totalRegistro = totalRegistro != null ? totalRegistro : 0;
        this.dataFechamento = dataFechamento != null ? dataFechamento : "";
        this.tecnicoResponsavel = tecnicoResponsavel != null ? tecnicoResponsavel : "";
        this.titulo = titulo != null ? titulo : "";
    }


    public String getSetorServico() {
        return setorServico;
    }

    public void setSetorServico(String setorServico) {
        this.setorServico = setorServico;
    }

    public Integer getNumServicos() {
        return numServicos;
    }

    public void setNumServicos(Integer numServicos) {
        this.numServicos = numServicos;
    }

    public Integer getTotalRegistro() {
        return totalRegistro;
    }

    public void setTotalRegistro(Integer totalRegistro) {
        this.totalRegistro = totalRegistro;
    }

    public String getDtDe() {
        return dtDe;
    }

    public void setDtDe(String dtDe) {
        this.dtDe = dtDe;
    }

    public String getDtAte() {
        return dtAte;
    }

    public void setDtAte(String dtAte) {
        this.dtAte = dtAte;
    }

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(String tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
