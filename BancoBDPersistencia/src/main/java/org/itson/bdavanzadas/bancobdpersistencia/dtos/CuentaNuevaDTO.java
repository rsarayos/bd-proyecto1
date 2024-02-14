/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import java.util.Date;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 *
 * @author alex_
 */
public class CuentaNuevaDTO {
    private String numCuenta;
    private Date fechaApertura;
    private long saldo;
    private boolean estado;
    private String telefonoTitular;

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTelefonoTitular() {
        return telefonoTitular;
    }

    public void setTelefonoTitular(String telefonoTitular) {
        this.telefonoTitular = telefonoTitular;
    }
    
    public boolean esValido() throws ValidacionDTOException {
        if (this.numCuenta == null 
            || this.numCuenta.isBlank() 
            || this.numCuenta.trim().length() > 6) {
            throw new ValidacionDTOException("cuenta de cliente invalido");
        }
        return true;
    }
    
}
