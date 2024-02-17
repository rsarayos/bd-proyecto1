/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.auxiliar;

import java.security.SecureRandom;

/**
 *
 * @author alex_
 */
public class GenerarFolioContraRetiros {
    
    private static final String CARACTERES_POSIBLES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LONGITUD_FOLIO = 10;
    private static final int LONGITUD_CONTRASENIA = 8;
    
    public String generarFolio() {
        return generarAleatorio(LONGITUD_FOLIO);
    }

    public String generarContraseña() {
        return generarAleatorio(LONGITUD_CONTRASENIA);
    }

    private String generarAleatorio(int longitud) {
        SecureRandom random = new SecureRandom();
        StringBuilder resultado = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(CARACTERES_POSIBLES.length());
            resultado.append(CARACTERES_POSIBLES.charAt(indice));
        }

        return resultado.toString();
    }
    
}
