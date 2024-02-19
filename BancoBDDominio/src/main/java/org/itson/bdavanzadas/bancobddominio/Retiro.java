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
     * Constructor que permite crear un objeto Retiro con el id.
     *
     * @param idRetiro id del retiro.
     */
    public Retiro(int idRetiro) {
        this.idRetiro = idRetiro;
    }

    /**
     * Constructor que permite crear un objeto Retiro con el folio y contraseña del retiro.
     *
     * @param folioRetiro folio del retiro.
     * @param contraseniaRetiro contraseña del retiro.
     */
    public Retiro(String folioRetiro, String contraseniaRetiro) {
        this.folioRetiro = folioRetiro;
        this.contraseniaRetiro = contraseniaRetiro;
    }

    /**
     * Constructor que permite crear un objeto Retiro con el idTransaccion, el folio y contraseña del retiro.
     *
     * @param idRetiro id del retiro.
     * @param folioRetiro folio del retiro.
     * @param contraseniaRetiro contraseña del retiro.
     * @param estado estado del retiro.
     * @param idTransaccion id de la transaccion del retiro.
     */
    public Retiro(int idRetiro, String folioRetiro, String contraseniaRetiro, int estado, int idTransaccion) {
        super(idTransaccion);
        this.idRetiro = idRetiro;
        this.folioRetiro = folioRetiro;
        this.contraseniaRetiro = contraseniaRetiro;
        this.estado = estado;
    }

    /**
     * Constructor que permite crear un objeto Retiro con sus parametros de transaccion, y los del retiro.
     *
     * @param idRetiro id del retiro.
     * @param folioRetiro folio del retiro.
     * @param contraseniaRetiro contraseña del retiro.
     * @param estado estado del retiro.
     * @param idTransaccion id de la transaccion del retiro.
     * @param fecha fecha de la transaccion.
     * @param cantidad cantidad de la transaccion.
     * @param numCuenta numero de cuenta origen de la transaccion.
     */
    public Retiro(int idRetiro, String folioRetiro, String contraseniaRetiro, int estado, int idTransaccion, Timestamp fecha, float cantidad, String numCuenta) {
        super(idTransaccion, fecha, cantidad, numCuenta);
        this.idRetiro = idRetiro;
        this.folioRetiro = folioRetiro;
        this.contraseniaRetiro = contraseniaRetiro;
        this.estado = estado;
    }

    // Métodos de acceso y modificación
    
    /**
     * Obtiene el número id del retiro.
     *
     * @return Número id del retiro.
     */
    public int getIdRetiro() {
        return idRetiro;
    }

    /**
     * Establece el id del retiro.
     *
     * @param idRetiro Nuevo id del retiro.
     */
    public void setIdRetiro(int idRetiro) {
        this.idRetiro = idRetiro;
    }

    /**
     * Obtiene el folio del retiro.
     *
     * @return folio del retiro.
     */
    public String getFolioRetiro() {
        return folioRetiro;
    }

    /**
     * Establece el folio del retiro.
     *
     * @param folioRetiro Nuevo id de la transferencia.
     */
    public void setFolioRetiro(String folioRetiro) {
        this.folioRetiro = folioRetiro;
    }

    /**
     * Obtiene la contraseña del retiro.
     *
     * @return contraseña del retiro.
     */
    public String getContraseniaRetiro() {
        return contraseniaRetiro;
    }

    /**
     * Establece la contraseña del retiro.
     *
     * @param contraseniaRetiro Nueva contraseña del retiro.
     */
    public void setContraseniaRetiro(String contraseniaRetiro) {
        this.contraseniaRetiro = contraseniaRetiro;
    }

    /**
     * Obtiene el estado del retiro.
     *
     * @return estado del retiro.
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Establece el estado del retiro.
     *
     * @param estado Nuevo estado del retiro.
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    /**
     * Calcula el código hash para el objeto Transferencia basado en su id.
     *
     * @return Código hash del objeto Retiro.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idRetiro;
        return hash;
    }

    /**
     * Compara si dos objetos Retiro son iguales basándose en el id.
     *
     * @param obj Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
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

    /**
     * Representación en forma de cadena de la clase Retiro.
     *
     * @return Cadena que representa el Retiro.
     */
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
