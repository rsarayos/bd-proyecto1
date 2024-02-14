/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobddominio;

/**
 *
 * @author alex_
 */
public class Transferencia {
    private int idTransferencia;
    private int idTransaccion;
    private String cuentaDestino;

    public Transferencia() {
    }
    
    public Transferencia(int idTransaccion, String cuentaDestino) {
        this.idTransaccion = idTransaccion;
        this.cuentaDestino = cuentaDestino;
    }

    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
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
        sb.append(", idTransaccion=").append(idTransaccion);
        sb.append(", cuentaDestino=").append(cuentaDestino);
        sb.append('}');
        return sb.toString();
    }
    
}
