
package org.itson.bdavanzadas.bancobddominio;

import java.sql.Timestamp;

/**
 * Representa la clase Transaccion que encapsula la información de una transaccion.
 * 
 * Un objeto de la clase Transaccion contiene los siguientes atributos:
 * 
 *     idTransaccion: id de la transaccion.
 *     fecha: fecha de la transaccion.
 *     cantidad: importe de la transaccion.
 *     numCuenta: numero de cuenta origen de la transaccion.
 *     tipo: tipo de la transaccion (transferencia o retiro).
 *     estadoTransaccion: estado de la transaccion.
 * 
 * La clase proporciona constructores para instanciar objetos de Transaccion de diferentes maneras,
 * así como métodos de acceso y modificación para cada atributo. Además, implementa los métodos
 * equals, hashCode y toString para facilitar la comparación, generación de hash y representación
 * en cadena de los objetos Transaccion.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class Transaccion {
    
    // Atributos de la clase
    
    private int idTransaccion;
    private Timestamp fecha;
    private float cantidad;
    private String numCuenta;
    private String tipo;
    private String estadoTransaccion;

    /**
     * Constructor por defecto. Crea un objeto Transaccion sin inicializar sus atributos.
     */
    public Transaccion() {
    }

    /**
     * Constructor que permite crear un objeto Transaccion con el id.
     *
     * @param idTransaccion id de la transaccion.
     */
    public Transaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    /**
     * Constructor que permite crear un objeto Cliente con todos los atributos especificados.
     * 
     * @param idTransaccion id de la transaccion.
     * @param fecha fecha y hora de creacion de la transaccion.
     * @param cantidad importe de la transaccion.
     * @param numCuenta numero de la cuenta origen.
     */
    public Transaccion(int idTransaccion, Timestamp fecha, float cantidad, String numCuenta) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.numCuenta = numCuenta;
    }
    
    // Métodos de acceso y modificación
    
    /**
     * Obtiene el número id de la transaccion.
     *
     * @return Número id de la transaccion.
     */
    public int getIdTransaccion() {
        return idTransaccion;
    }

    /**
     * Establece el id de la transaccion.
     *
     * @param idTransaccion Nuevo id de la transaccion.
     */
    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    /**
     * Obtiene fecha y hora de la transaccion.
     *
     * @return fecha y hora de la transaccion.
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha y hora de la transaccion.
     *
     * @param fecha Nueva fecha de la transaccion.
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el importe de la transaccion.
     *
     * @return importe de la transaccion.
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * Establece el importe de la transaccion.
     *
     * @param cantidad Nuevo importe de la transaccion.
     */
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene numero de la cuenta origen de la transaccion.
     *
     * @return numero de la cuenta origen de la transaccion.
     */
    public String getNumCuenta() {
        return numCuenta;
    }

    /**
     * Establece el numero de cuenta origen de la transaccion.
     *
     * @param numCuenta Nuevo numero de cuenta origen de la transaccion.
     */
    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    /**
     * Obtiene el tipo de la transaccion.
     *
     * @return tipo de la transaccion.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de la transaccion.
     *
     * @param tipo Nuevo tipo de la transaccion.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Obtiene estado de la transaccion.
     *
     * @return estado de la transaccion.
     */
    public String getEstadoTransaccion() {
        return estadoTransaccion;
    }

    /**
     * Establece el estado de la transaccion.
     *
     * @param estadoTransaccion Nuevo estado de la transaccion.
     */
    public void setEstadoTransaccion(String estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }
    
    /**
     * Calcula el código hash para el objeto Transaccion basado en su id.
     *
     * @return Código hash del objeto Transaccion.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idTransaccion;
        return hash;
    }

    /**
     * Compara si dos objetos Transaccion son iguales basándose en el id.
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
        final Transaccion other = (Transaccion) obj;
        return this.idTransaccion == other.idTransaccion;
    }

    /**
     * Representación en forma de cadena de la clase Transaccion.
     *
     * @return Cadena que representa la transaccion.
     */
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
