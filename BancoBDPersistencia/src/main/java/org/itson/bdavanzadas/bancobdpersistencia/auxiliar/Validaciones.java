/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.auxiliar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase Validaciones proporciona métodos para validar diferentes tipos de datos, como nombres,
 * apellidos, teléfonos, fechas, domicilios, números, códigos postales, cuentas, contraseñas y cantidades.
 *
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class Validaciones {

    /**
     * Valida si el nombre proporcionado cumple con el formato permitido.
     *
     * @param nombre Nombre a validar.
     * @return true si el nombre es válido, false de lo contrario.
     */
    public boolean validaNombre(String nombre) {

        Pattern patron = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]{1,100}$");
        Matcher matcher = patron.matcher(nombre);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si el apellido proporcionado cumple con el formato permitido.
     *
     * @param apellido Apellido a validar.
     * @return true si el apellido es válido, false de lo contrario.
     */
    public boolean validaApellidos(String apellido) {

        Pattern patron = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]{1,50}$");
        Matcher matcher = patron.matcher(apellido);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si el número de teléfono proporcionado cumple con el formato permitido.
     *
     * @param telefono Número de teléfono a validar.
     * @return true si el número de teléfono es válido, false de lo contrario.
     */
    public boolean validaTelefono(String telefono) {

        Pattern patron = Pattern.compile("^\\d{10}$");
        Matcher matcher = patron.matcher(telefono);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si la fecha proporcionada cumple con el formato permitido.
     *
     * @param fecha Fecha a validar.
     * @return true si la fecha es válida, false de lo contrario.
     */
    public boolean validaFecha(String fecha) {

        Pattern patron = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher matcher = patron.matcher(fecha);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si el domicilio proporcionado cumple con el formato permitido.
     *
     * @param domicilio Domicilio a validar.
     * @return true si el domicilio es válido, false de lo contrario.
     */
    public boolean validaDomicilio(String domicilio) {

        Pattern patron = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\d\\s]{1,50}$");
        Matcher matcher = patron.matcher(domicilio);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si el número proporcionado cumple con el formato permitido.
     *
     * @param numero Número a validar.
     * @return true si el número es válido, false de lo contrario.
     */
    public boolean validaNumero(String numero) {

        Pattern patron = Pattern.compile("^\\d{1,10}$");
        Matcher matcher = patron.matcher(numero);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si el código postal proporcionado cumple con el formato permitido.
     *
     * @param cp Código postal a validar.
     * @return true si el código postal es válido, false de lo contrario.
     */
    public boolean validaCp(String cp) {

        Pattern patron = Pattern.compile("^\\d{1,5}$");
        Matcher matcher = patron.matcher(cp);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si el número de cuenta proporcionado cumple con el formato permitido.
     *
     * @param cuenta Número de cuenta a validar.
     * @return true si el número de cuenta es válido, false de lo contrario.
     */
    public boolean validaCuenta(String cuenta) {

        Pattern patron = Pattern.compile("^\\d{6}$");
        Matcher matcher = patron.matcher(cuenta);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si la contraseña proporcionada cumple con el formato permitido.
     *
     * @param contrasenia Contraseña a validar.
     * @return true si la contraseña es válida, false de lo contrario.
     */
    public boolean validaContra(String contrasenia) {

        Pattern patron = Pattern.compile("^[a-zA-Z0-9.,\\-_\\*]{1,20}$");
        Matcher matcher = patron.matcher(contrasenia);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si la cantidad proporcionada cumple con el formato permitido.
     *
     * @param cantidad Cantidad a validar.
     * @return true si la cantidad es válida, false de lo contrario.
     */
    public boolean validaCantidad(String cantidad) {

        Pattern patron = Pattern.compile("^(?!0\\d)(\\d+(\\.\\d{1,2})?)?$");
        Matcher matcher = patron.matcher(cantidad);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
