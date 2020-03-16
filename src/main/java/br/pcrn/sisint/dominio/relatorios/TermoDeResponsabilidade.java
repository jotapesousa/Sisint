package br.pcrn.sisint.dominio.relatorios;

public class TermoDeResponsabilidade extends Relatorio {

    private String setor;
    private Integer qtdeEquipamento;
    private String tipoEquipamento;
    private String tomboEquipamento;
    private String numSerieEquipamento;
    private Integer posicao;

    public TermoDeResponsabilidade(String setor, String tipoEquipamento, String tomboEquipamento, String numSerieEquipamento, Integer qtdeEquipamento, Integer posicao) {
        this.setor = (setor != null) ? setor : "";
        this.tipoEquipamento = (tipoEquipamento != null) ? tipoEquipamento : "";
        this.tomboEquipamento = (tomboEquipamento != null)? tomboEquipamento : "";
        this.numSerieEquipamento = (numSerieEquipamento != null) ? numSerieEquipamento : "";
        this.qtdeEquipamento = qtdeEquipamento;
        this.posicao = posicao;

        System.out.println(tomboEquipamento);
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getTomboEquipamento() { return tomboEquipamento; }

    public void setTomboEquipamento(String tomboEquipamento) { this.tomboEquipamento = tomboEquipamento; }

    public String getNumSerieEquipamento() {
        return numSerieEquipamento;
    }

    public void setNumSerieEquipamento(String numSerieEquipamento) {
        this.numSerieEquipamento = numSerieEquipamento;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public Integer getQtdeEquipamento() {
        return qtdeEquipamento;
    }

    public void setQtdeEquipamento(Integer qtdeEquipamento) {
        this.qtdeEquipamento = qtdeEquipamento;
    }
}
