/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.Validaciones;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 * La clase DireccionNuevaDTO representa un objeto de transferencia de datos (DTO) para la creación de nuevas direcciones.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class DireccionNuevaDTO {
    
    // Objeto Validaciones utilizado para realizar validaciones en los datos de la Cuenta.
    Validaciones valida = new Validaciones();
    
    // Atributos de la clase Direccion
    private int idDireccion;
    private String calle;
    private String colonia;
    private String numero;
    private String ciudad;
    private String cp;
    
    // Métodos de acceso y modificación

    /**
     * Obtiene el número de identificacion de la direccion.
     *
     * @return Número de identificacion de la direccion.
     */
    public int getIdDireccion() {
        return idDireccion;
    }

    /**
     * Establece el número de identicacion de la direccion.
     *
     * @param idDireccion Nuevo número de identificacion de la direccion.
     */
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    /**
     * Obtiene el nombre de la calle de la direccion.
     *
     * @return nombre de calle de la direccion.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece el nombre de calle de la direccion.
     *
     * @param calle Nuevo nombre de calle de la direccion.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene el nombre de la colonia de la direccion.
     *
     * @return nombre de colonia de la direccion.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Establece el nombre de colonia de la direccion.
     *
     * @param colonia Nuevo nombre de colonia de la direccion.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Obtiene el numero exterior de la direccion.
     *
     * @return numero exterior de la direccion.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el numero de exterior de la direccion.
     *
     * @param numero Nuevo numero exterior de la direccion.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el nombre de la ciudad de la direccion.
     *
     * @return nombre de ciudad de la direccion.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece el nombre de ciudad de la direccion.
     *
     * @param ciudad Nuevo nombre de ciudad de la direccion.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene el codigo postal de la direccion.
     *
     * @return codigo postal de la direccion.
     */
    public String getCp() {
        return cp;
    }

    /**
     * Establece el codigo postal de la direccion.
     *
     * @param cp Nuevo codigo postal de la direccion.
     */
    public void setCp(String cp) {
        this.cp = cp;
    }
    
    /**
     * Realiza validaciones en los datos de la direccion y devuelve true si los datos son válidos.
     *
     * @return true si los datos de la direccion son válidos, false de lo contrario.
     * @throws ValidacionDTOException Si alguno de los campos de la direecion no es válido.
     */
    public boolean esValido() throws ValidacionDTOException {
        if (this.calle == null 
            || this.calle.isBlank() 
            || !this.valida.validaDomicilio(calle)) {
            throw new ValidacionDTOException("calle de cliente invalido");
        }
        if (this.ciudad == null 
            || this.ciudad.isBlank() 
            || !this.valida.validaDomicilio(ciudad)) {
            throw new ValidacionDTOException("ciudad de cliente invalido");
        }
        if (this.colonia == null 
            || this.colonia.isBlank() 
            || !this.valida.validaDomicilio(colonia)) {
            throw new ValidacionDTOException("colonia de cliente invalido");
        }
        if (this.cp == null 
            || this.cp.isBlank() 
            || !this.valida.validaCp(cp)) {
            throw new ValidacionDTOException("codigo postal de cliente invalido");
        }
        if (this.numero == null 
            || this.numero.isBlank() 
            || !this.valida.validaNumero(numero)) {
            throw new ValidacionDTOException("numero de domicilio de cliente invalido");
        }
        return true;
    }
}
