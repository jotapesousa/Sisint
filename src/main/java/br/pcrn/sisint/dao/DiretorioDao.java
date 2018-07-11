package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Arquivo;

import java.net.URI;

public interface DiretorioDao {
    URI grava(Arquivo arquivo);
    Arquivo recupera(URI arquivo);
}
