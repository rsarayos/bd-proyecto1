/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobddominio;

import java.util.Date;

/**
 *
 * @author alex_
 */
public class Transaccion {
    private int idTransaccion;
    private Date fecha;
    private long cantidad;
    private String numCuenta;

    public Transaccion() {
    }

    public Transaccion(Date fecha, long cantidad, String numCuenta) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.numCuenta = numCuenta;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idTransaccion;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaccion other = (Transaccion) obj;
        return this.idTransaccion == other.idTransaccion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaccion{");
        sb.append("idTransaccion=").append(idTransaccion);
        sb.append(", fecha=").append(fecha);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", numCuenta=").append(numCuenta);
        sb.append('}');
        return sb.toString();
    }
    
}
