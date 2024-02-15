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
public class Transferencia extends Transaccion{
    private int idTransferencia;
    private String cuentaDestino;

    public Transferencia() {
    }
    
    public Transferencia(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Transferencia(int idTransferencia, String cuentaDestino, int idTransaccion, Date fecha, long cantidad, String numCuenta) {
        super(idTransaccion, fecha, cantidad, numCuenta);
        this.idTransferencia = idTransferencia;
        this.cuentaDestino = cuentaDestino;
    }

    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.idTransferencia;
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
        final Transferencia other = (Transferencia) obj;
        return this.idTransferencia == other.idTransferencia;
    }    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transferencia{");
        sb.append("idTransferencia=").append(idTransferencia);
        sb.append(", cuentaDestino=").append(cuentaDestino);
        sb.append('}');
        return sb.toString();
    }
    
}
