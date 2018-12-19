package br.pcrn.sisint.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by samue on 08/09/2017.
 */
@Entity
public class Setor extends Entidade{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean deletado;
    private String nome;
    private String senha;
    private String telefone;
    private String endereco;
    private String informacao;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

//    @Override
//    public String toString() {
//        return "Setor{" +
//                "id=" + id +
//                ", deletado=" + deletado +
//                ", nome='" + nome + '\'' +
//                ", senha='" + senha + '\'' +
//                ", telefone='" + telefone + '\'' +
//                ", endereco='" + endereco + '\'' +
//                ", informacao='" + informacao + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//
//        Setor setor = (Setor) o;
//
//        if (deletado != setor.deletado) return false;
//        if (id != null ? !id.equals(setor.id) : setor.id != null) return false;
//        if (nome != null ? !nome.equals(setor.nome) : setor.nome != null) return false;
//        if (senha != null ? !senha.equals(setor.senha) : setor.senha != null) return false;
//        if (telefone != null ? !telefone.equals(setor.telefone) : setor.telefone != null) return false;
//        if (endereco != null ? !endereco.equals(setor.endereco) : setor.endereco != null) return false;
//        return informacao != null ? informacao.equals(setor.informacao) : setor.informacao == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (deletado ? 1 : 0);
//        result = 31 * result + (nome != null ? nome.hashCode() : 0);
//        result = 31 * result + (senha != null ? senha.hashCode() : 0);
//        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
//        result = 31 * result + (endereco != null ? endereco.hashCode() : 0);
//        result = 31 * result + (informacao != null ? informacao.hashCode() : 0);
//        return result;
//    }
}
