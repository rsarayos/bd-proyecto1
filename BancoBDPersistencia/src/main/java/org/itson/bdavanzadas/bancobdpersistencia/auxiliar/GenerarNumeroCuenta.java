/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.auxiliar;

import java.util.Random;

/**
 *
 * @author alex_
 */
public class GenerarNumeroCuenta {
    
    public String generarNumeroCuenta() {
        String primerosTresDigitos = "580";
        String ultimosTresDigitos = generarDigitosAleatorios();
        String numeroCuenta = primerosTresDigitos + ultimosTresDigitos;

        return numeroCuenta;
    }

    private String generarDigitosAleatorios() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(1000);
        String digitosAleatorios = String.format("%03d", numeroAleatorio);

        return digitosAleatorios;
    }
    
}
