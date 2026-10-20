package com.sistemasaludador.servicio;

import com.sistemasaludador.modelo.DatosSaludo;

/**
 * Convierte hora 12 h + AM/PM a un periodo y arma el mensaje de saludo.
 *
 * <p>Regla del prototipo (documentada también en el README):</p>
 * <ul>
 *   <li>Buenos días: desde las 5:00 AM hasta antes de las 12:00 PM.</li>
 *   <li>Buenas tardes: desde las 12:00 PM hasta antes de las 6:00 PM.</li>
 *   <li>Buenas noches: el resto de las horas (6:00 PM hasta antes de las 5:00 AM).</li>
 * </ul>
 */
public class GeneradorSaludo {

    private static final int MINUTOS_INICIO_DIAS = 5 * 60;
    private static final int MINUTOS_INICIO_TARDES = 12 * 60;
    private static final int MINUTOS_INICIO_NOCHES = 18 * 60;

    public PeriodoSaludo calcularPeriodo(int hora12, int minutos, boolean pm) {
        int minutosDelDia = aMinutosDelDia(hora12, minutos, pm);
        if (minutosDelDia >= MINUTOS_INICIO_DIAS && minutosDelDia < MINUTOS_INICIO_TARDES) {
            return PeriodoSaludo.DIAS;
        }
        if (minutosDelDia >= MINUTOS_INICIO_TARDES && minutosDelDia < MINUTOS_INICIO_NOCHES) {
            return PeriodoSaludo.TARDES;
        }
        return PeriodoSaludo.NOCHES;
    }

    public String generar(DatosSaludo datos) {
        PeriodoSaludo periodo = calcularPeriodo(datos.getHora12(), datos.getMinutos(), datos.isPm());
        return periodo.getTexto() + ", " + datos.getNombre()
                + ". Tienes " + datos.getEdad() + " años.";
    }

    /**
     * Convierte una hora en formato 12 h a minutos desde medianoche (0–1439).
     * 12:00 AM es 00:00 y 12:00 PM es 12:00.
     */
    public int aMinutosDelDia(int hora12, int minutos, boolean pm) {
        int hora24 = hora12 % 12;
        if (pm) {
            hora24 += 12;
        }
        return hora24 * 60 + minutos;
    }
}
