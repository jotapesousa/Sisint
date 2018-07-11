package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Setor;

import java.util.List;

public interface SetorDao extends EntidadeDao<Setor> {
    @Override
    List<Setor> listar();

    @Override
    List<Setor> todos();
}
