package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Result;
import br.pcrn.sisint.dominio.Entidade;

public class ControladorSisInt<T extends Entidade> extends Controlador {

    public ControladorSisInt(Result resultado) {
        super(resultado);
    }


}
