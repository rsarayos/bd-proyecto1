
package org.itson.bdavanzadas.bancobdpersistencia.auxiliar;

import java.security.SecureRandom;

/**
 *
 * @author alex_
 */
public class GenerarFolioContraRetiros {
    
    private static final String CARACTERES_POSIBLES_FOLIO = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CARACTERES_POSIBLES_CONTRA = "0123456789";
    private static final int LONGITUD_FOLIO = 10;
    private static final int LONGITUD_CONTRASENIA = 8;
    
    public String generarFolio() {
        return generarAleatorioFolio(LONGITUD_FOLIO);
    }

    public String generarContraseña() {
        return generarAleatorioContra(LONGITUD_CONTRASENIA);
    }

    private String generarAleatorioFolio(int longitud) {
        SecureRandom random = new SecureRandom();
        StringBuilder resultado = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(CARACTERES_POSIBLES_FOLIO.length());
            resultado.append(CARACTERES_POSIBLES_FOLIO.charAt(indice));
        }
        return resultado.toString();
    }
    
    private String generarAleatorioContra(int longitud) {
        SecureRandom random = new SecureRandom();
        StringBuilder resultado = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(CARACTERES_POSIBLES_CONTRA.length());
            resultado.append(CARACTERES_POSIBLES_CONTRA.charAt(indice));
        }
        return resultado.toString();
    }
    
}
