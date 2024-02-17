/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import java.util.Date;
import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.Validaciones;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 *
 * @author alex_
 */
public class CuentaNuevaDTO {
    
    Validaciones valida = new Validaciones();
    
    private String numCuenta;
    private Date fechaApertura;
    private float saldo;
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

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
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
        if (this.saldo == 0f 
            || this.saldo < 0) {
            throw new ValidacionDTOException("saldo en cuenta invalida");
        }
        return true;
    }
    
}
