package com.sistemasaludador.servicio;

import java.util.ArrayList;
import java.util.List;

/**
 * Valida los datos del formulario antes de generar el saludo.
 */
public class ValidadorEntrada {

    public List<String> validar(String nombre, String edadTexto, Integer hora12, Integer minutos, String amPm) {
        List<String> errores = new ArrayList<>();

        if (nombre == null || nombre.trim().isEmpty()) {
            errores.add("El nombre no puede estar vacío.");
        }

        if (edadTexto == null || edadTexto.trim().isEmpty()) {
            errores.add("La edad es obligatoria.");
        } else {
            try {
                int edad = Integer.parseInt(edadTexto.trim());
                if (edad < 1 || edad > 120) {
                    errores.add("La edad debe ser un número entero entre 1 y 120.");
                }
            } catch (NumberFormatException ex) {
                errores.add("La edad debe ser un número entero entre 1 y 120. No uses decimales ni letras.");
            }
        }

        if (hora12 == null || hora12 < 1 || hora12 > 12) {
            errores.add("Selecciona una hora válida entre 1 y 12.");
        }

        if (minutos == null || minutos < 0 || minutos > 59) {
            errores.add("Selecciona los minutos, de 00 a 59.");
        }

        if (amPm == null || (!amPm.equals("AM") && !amPm.equals("PM"))) {
            errores.add("Selecciona AM o PM.");
        }

        return errores;
    }
}
