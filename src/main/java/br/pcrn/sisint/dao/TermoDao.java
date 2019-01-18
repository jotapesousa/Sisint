package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Termo;

import javax.persistence.Query;
import java.util.List;

public interface TermoDao extends EntidadeDao<Termo> {

    @Override
    List<Termo> listar();

    @Override
    List<Termo> todos();

    int numTermo();
}
