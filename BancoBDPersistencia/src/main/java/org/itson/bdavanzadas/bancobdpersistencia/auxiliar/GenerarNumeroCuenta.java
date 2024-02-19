
package org.itson.bdavanzadas.bancobdpersistencia.auxiliar;

import java.util.Random;

/**
 * La clase GenerarNumeroCuenta proporciona un método para generar números de cuenta aleatorios
 * en un formato específico.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class GenerarNumeroCuenta {

    /**
     * Genera un número de cuenta aleatorio con un formato específico que
     * consiste en los primeros tres dígitos fijos (580) seguidos por tres
     * dígitos aleatorios generados.
     *
     * @return Número de cuenta aleatorio generado.
     */
    public String generarNumeroCuenta() {
        String primerosTresDigitos = "580";
        String ultimosTresDigitos = generarDigitosAleatorios();
        String numeroCuenta = primerosTresDigitos + ultimosTresDigitos;
        return numeroCuenta;
    }

    /**
     * Genera una cadena de tres dígitos aleatorios.
     *
     * @return Tres dígitos aleatorios en formato de cadena.
     */
    private String generarDigitosAleatorios() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(1000);
        // Formatea el número aleatorio a una cadena de tres dígitos rellenados con ceros a la izquierda si es necesario.
        String digitosAleatorios = String.format("%03d", numeroAleatorio);
        return digitosAleatorios;
    }

}
