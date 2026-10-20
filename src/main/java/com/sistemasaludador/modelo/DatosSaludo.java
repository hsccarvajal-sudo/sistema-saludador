package com.sistemasaludador.modelo;

/**
 * Datos capturados en el formulario de saludo.
 */
public class DatosSaludo {

    private final String nombre;
    private final int edad;
    private final int hora12;
    private final int minutos;
    private final boolean pm;

    public DatosSaludo(String nombre, int edad, int hora12, int minutos, boolean pm) {
        this.nombre = nombre;
        this.edad = edad;
        this.hora12 = hora12;
        this.minutos = minutos;
        this.pm = pm;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getHora12() {
        return hora12;
    }

    public int getMinutos() {
        return minutos;
    }

    public boolean isPm() {
        return pm;
    }
}
