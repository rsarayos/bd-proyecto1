/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.auxiliar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author alex_
 */
public class Validaciones {
    
    public boolean validaNombre(String nombre) {

        Pattern patron = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]{1,100}$");
        Matcher matcher = patron.matcher(nombre);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validaApellidos(String apellido) {

        Pattern patron = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]{1,50}$");
        Matcher matcher = patron.matcher(apellido);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validaTelefono(String telefono) {

        Pattern patron = Pattern.compile("^\\d{10}$");
        Matcher matcher = patron.matcher(telefono);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validaFecha(String fecha) {

        Pattern patron = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher matcher = patron.matcher(fecha);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validaDomicilio(String domicilio) {

        Pattern patron = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\d\\s]{1,50}$");
        Matcher matcher = patron.matcher(domicilio);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validaNumero(String numero) {

        Pattern patron = Pattern.compile("^\\d{1,10}$");
        Matcher matcher = patron.matcher(numero);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validaCp(String cp) {

        Pattern patron = Pattern.compile("^\\d{1,5}$");
        Matcher matcher = patron.matcher(cp);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validaCuenta(String cuenta) {

        Pattern patron = Pattern.compile("^\\d{6}$");
        Matcher matcher = patron.matcher(cuenta);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validaContra(String contrasenia) {

        Pattern patron = Pattern.compile("^[a-zA-Z0-9.,\\-_\\*]{1,20}$");
        Matcher matcher = patron.matcher(contrasenia);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
}
