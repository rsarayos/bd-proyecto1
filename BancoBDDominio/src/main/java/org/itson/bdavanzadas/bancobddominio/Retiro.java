/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobddominio;

import java.sql.Timestamp;

/**
 * Representa la clase Retiro que hereda de la clase Transaccion y encapsula la información de un retiro.
 * 
 * Un objeto de la clase Retiro contiene los siguientes atributos:
 * 
 *     idRetiro: id del retiro.
 *     folioRetiro: folio del retiro.
 *     contraseniaRetiro: contraseña del retiro.
 *     estado: estado del retiro.
 * 
 * La clase proporciona constructores para instanciar objetos de Retiro de diferentes maneras,
 * así como métodos de acceso y modificación para cada atributo. Además, implementa los métodos
 * equals, hashCode y toString para facilitar la comparación, generación de hash y representación
 * en cadena de los objetos Retiro.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class Retiro extends Transaccion{
    
    // Atributos de la clase
    
    private int idRetiro;
    private String folioRetiro;
    private String contraseniaRetiro;
    private int estado;

    /**
     * Constructor por defecto. Crea un objeto Retiro sin inicializar sus atributos.
     */
    public Retiro() {
    }

    /**
     * Constructor que permite crear un objeto Transferencia con el id.
     *
     * @param idRetiro id del retiro.
     */
    public Retiro(int idRetiro) {
        this.idRetiro = idRetiro;
    }

    public Retiro(String folioRetiro, String contraseniaRetiro) {
        this.folioRetiro = folioRetiro;
        this.contraseniaRetiro = contraseniaRetiro;
    }

    public Retiro(int idRetiro, String folioRetiro, String contraseniaRetiro, int estado, int idTransaccion) {
        super(idTransaccion);
        this.idRetiro = idRetiro;
        this.folioRetiro = folioRetiro;
        this.contraseniaRetiro = contraseniaRetiro;
        this.estado = estado;
    }

    public Retiro(int idRetiro, String folioRetiro, String contraseniaRetiro, int estado, int idTransaccion, Timestamp fecha, float cantidad, String numCuenta) {
        super(idTransaccion, fecha, cantidad, numCuenta);
        this.idRetiro = idRetiro;
        this.folioRetiro = folioRetiro;
        this.contraseniaRetiro = contraseniaRetiro;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
        sb.append('}');
        return sb.toString();
    }
 
}
