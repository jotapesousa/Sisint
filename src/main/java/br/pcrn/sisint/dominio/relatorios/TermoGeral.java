package br.pcrn.sisint.dominio.relatorios;

public class TermoGeral extends Relatorio {

    private String numero;
    private String ano;
    private String setor;
    private String nomeEquipamento;
    private String tomboEquipamento;
    private String numSerieEquipamento;
    private Integer posicao;

    public TermoGeral(String numero, String ano, String setor, String nomeEquipamento, String tomboEquipamento,
                      String numSerieEquipamento, Integer posicao) {
        this.numero = (numero != null) ? numero : "";
        this.ano = (ano != null) ? ano : "";
        this.setor = (setor != null) ? setor : "";
        this.nomeEquipamento = (nomeEquipamento != null) ? nomeEquipamento : "";
        this.tomboEquipamento = (tomboEquipamento != null)? tomboEquipamento : "";
        this.numSerieEquipamento = (numSerieEquipamento != null) ? numSerieEquipamento : "";
        this.posicao = posicao;

        System.out.println(tomboEquipamento);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
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
}
