package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Termo;

import java.util.List;

public interface TermoDao extends EntidadeDao<Termo> {

    @Override
    List<Termo> listar();

    @Override
    List<Termo> todos();
}
