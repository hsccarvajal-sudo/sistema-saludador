package com.sistemasaludador.servicio;

/**
 * Periodo del día usado para elegir el saludo.
 */
public enum PeriodoSaludo {
    DIAS("Buenos días"),
    TARDES("Buenas tardes"),
    NOCHES("Buenas noches");

    private final String texto;

    PeriodoSaludo(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
