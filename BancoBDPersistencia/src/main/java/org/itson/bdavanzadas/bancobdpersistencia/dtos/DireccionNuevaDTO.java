/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.Validaciones;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 *
 * @author alex_
 */
public class DireccionNuevaDTO {
    
    Validaciones valida = new Validaciones();
    
    private int idDireccion;
    private String calle;
    private String colonia;
    private String numero;
    private String ciudad;
    private String cp;

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
    
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
