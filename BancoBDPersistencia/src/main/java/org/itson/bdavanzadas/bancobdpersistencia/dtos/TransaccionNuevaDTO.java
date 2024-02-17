/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import java.sql.Timestamp;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 *
 * @author alex_
 */
public class TransaccionNuevaDTO {
    
    private int idTransaccion;
    private Timestamp fecha;
    private float cantidad;
    private String numCuenta;
    private String estado;

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }
  
    public boolean esValido() throws ValidacionDTOException {
        return true;
    }
    
}
