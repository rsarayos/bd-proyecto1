
package org.itson.bdavanzadas.bancobdpersistencia.auxiliar;

import java.security.SecureRandom;

/**
 * La clase GenerarFolioContraRetiros proporciona métodos para generar folios y contraseñas aleatorias
 * utilizadas en el contexto de retiros.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class GenerarFolioContraRetiros {
    
    // Caracteres posibles para la generación de folios.
    private static final String CARACTERES_POSIBLES_FOLIO = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // Caracteres posibles para la generación de contraseñas.
    private static final String CARACTERES_POSIBLES_CONTRA = "0123456789";
    // Longitud deseada para los folios generados.
    private static final int LONGITUD_FOLIO = 10;
    // Longitud deseada para las contraseñas generadas.
    private static final int LONGITUD_CONTRASENIA = 8;
    
    /**
     * Genera un folio aleatorio con la longitud especificada.
     *
     * @return Folio aleatorio generado.
     */
    public String generarFolio() {
        return generarAleatorioFolio(LONGITUD_FOLIO);
    }

    /**
     * Genera una contraseña aleatoria con la longitud especificada.
     *
     * @return Contraseña aleatoria generada.
     */
    public String generarContraseña() {
        return generarAleatorioContra(LONGITUD_CONTRASENIA);
    }

    /**
     * Genera una cadena aleatoria de folio con la longitud especificada.
     *
     * @param longitud Longitud deseada para el folio.
     * @return Folio aleatorio generado.
     */
    private String generarAleatorioFolio(int longitud) {
        SecureRandom random = new SecureRandom();
        StringBuilder resultado = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(CARACTERES_POSIBLES_FOLIO.length());
            resultado.append(CARACTERES_POSIBLES_FOLIO.charAt(indice));
        }
        return resultado.toString();
    }
    
    /**
     * Genera una cadena aleatoria de contraseña con la longitud especificada.
     *
     * @param longitud Longitud deseada para la contraseña.
     * @return Contraseña aleatoria generada.
     */
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
