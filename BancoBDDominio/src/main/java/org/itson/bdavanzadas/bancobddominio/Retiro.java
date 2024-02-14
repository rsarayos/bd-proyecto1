/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobddominio;

/**
 *
 * @author alex_
 */
public class Retiro {
    private int idRetiro;
    private String folioRetiro;
    private String contraseniaRetiro;
    private boolean estado;
    private int idTransaccion;

    public Retiro() {
    }

    public Retiro(String folioRetiro, String contraseniaRetiro, int idTransaccion) {
        this.folioRetiro = folioRetiro;
        this.contraseniaRetiro = contraseniaRetiro;
        this.idTransaccion = idTransaccion;
    }

    public int getIdRetiro() {
        return idRetiro;
    }

    public void setIdRetiro(int idRetiro) {
        this.idRetiro = idRetiro;
    }

    public String getFolioRetiro() {
        return folioRetiro;
    }

    public void setFolioRetiro(String folioRetiro) {
        this.folioRetiro = folioRetiro;
    }

    public String getContraseniaRetiro() {
        return contraseniaRetiro;
    }

    public void setContraseniaRetiro(String contraseniaRetiro) {
        this.contraseniaRetiro = contraseniaRetiro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idRetiro;
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
        final Retiro other = (Retiro) obj;
        return this.idRetiro == other.idRetiro;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Retiro{");
        sb.append("idRetiro=").append(idRetiro);
        sb.append(", folioRetiro=").append(folioRetiro);
        sb.append(", contraseniaRetiro=").append(contraseniaRetiro);
        sb.append(", estado=").append(estado);
        sb.append(", idTransaccion=").append(idTransaccion);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
