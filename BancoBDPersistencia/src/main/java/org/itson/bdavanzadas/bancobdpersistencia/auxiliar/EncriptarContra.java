/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.auxiliar;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author alex_
 */
public class EncriptarContra {
      
    private static final String ALGORITMO = "AES";
    private static final String CLAVE_SECRETA = "Contra123";
    
    private SecretKeySpec clave;

    public EncriptarContra() {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] claveBytes = sha.digest(CLAVE_SECRETA.getBytes());
            claveBytes = Arrays.copyOf(claveBytes, 16); // Tomar los primeros 16 bytes para una clave de 128 bits
            this.clave = new SecretKeySpec(claveBytes, ALGORITMO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encriptar(String textoPlano) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        byte[] bytesEncriptados = cipher.doFinal(textoPlano.getBytes());
        return Base64.getEncoder().encodeToString(bytesEncriptados);
    }

    public String desencriptar(String textoEncriptado) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] bytesDesencriptados = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado));
        return new String(bytesDesencriptados);
    }
    
}
