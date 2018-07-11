package br.pcrn.sisint.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Arquivo extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String path;
    private String contentType;

    public Arquivo() { this(null, null, null); }

    public Arquivo(String name, String path, String contentType) {
        this.name = name;
        this.path = path;
        this.contentType = contentType;
    }


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
