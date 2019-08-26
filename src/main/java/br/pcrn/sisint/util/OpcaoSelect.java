package br.pcrn.sisint.util;

import br.pcrn.sisint.conversor.ConvertivelOpcaoSelect;

import java.util.*;

/**
 * Dto que auxilia montar selects na jsp
 */
public class OpcaoSelect {
    private Object chave;
    private Object valor;

    public OpcaoSelect(Object chave, Object valor) {
        this.chave = chave;
        this.valor = valor;
    }
    /**
     * Converte objeto para opcao select
     * @param opcaoConvertivel instancia de opcao convertivel
     * @return retorna o opção select convertido
     */
    public static OpcaoSelect converteParaOpcaoSelect(ConvertivelOpcaoSelect opcaoConvertivel) {
        return new OpcaoSelect(opcaoConvertivel.getChave(), opcaoConvertivel.getValor());
    }
    /**
     * Converte a lista de valores de tipos de opcaoselect para uma lista de {@link OpcaoSelect}
     * @param listaTodos lista de valores
     * @return retorna uma lista de {@link OpcaoSelect}
     */
    public static List<OpcaoSelect> toListaOpcoes(ConvertivelOpcaoSelect[] listaTodos) {
        List<OpcaoSelect> lista = new ArrayList<>();
        for (ConvertivelOpcaoSelect opcao: listaTodos) {
            lista.add(OpcaoSelect.converteParaOpcaoSelect(opcao));
        }
        return lista;
    }

    public static List<OpcaoSelect> toListaOpcoesOrdenada(ConvertivelOpcaoSelect[] listaTodos) {
        List<OpcaoSelect> lista = toListaOpcoes(listaTodos);

        Collections.sort(lista, new Comparator<OpcaoSelect>() {
            @Override
            public int compare(OpcaoSelect o1, OpcaoSelect o2) {
                return o1.getValor().toString().compareTo(o2.getValor().toString());
            }
        });

        return lista;
    }

    public Object getChave() {
        return chave;
    }
    public void setChave(String chave) {
        this.chave = chave;
    }
    public Object getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
}
