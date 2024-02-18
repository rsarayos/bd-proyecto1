/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobddominio;

import java.sql.Timestamp;

/**
 * Representa la clase Transferencia que hereda de la clase Transaccion y encapsula la información de una transferencia.
 * 
 * Un objeto de la clase Transferencia contiene los siguientes atributos:
 * 
 *     idTransferencia: id de la transferencia.
 *     cuentaDestino: numero de cuenta destino.
 * 
 * La clase proporciona constructores para instanciar objetos de Transferencia de diferentes maneras,
 * así como métodos de acceso y modificación para cada atributo. Además, implementa los métodos
 * equals, hashCode y toString para facilitar la comparación, generación de hash y representación
 * en cadena de los objetos Transferencia.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class Transferencia extends Transaccion{
    
    // Atributos de la clase
    
    private int idTransferencia;
    private String cuentaDestino;

    /**
     * Constructor por defecto. Crea un objeto Transferencia sin inicializar sus atributos.
     */
    public Transferencia() {
    }

    /**
     * Constructor que permite crear un objeto Transferencia con el id.
     *
     * @param idTransferencia id de la transferencia.
     */
    public Transferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }
    
    /**
     * Constructor que permite crear un objeto Transferencia con la cuenta destino.
     *
     * @param cuentaDestino Número de cuenta destino.
     */
    public Transferencia(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    /**
     * Constructor que permite crear un objeto Transferencia con el id, la cuenta destino y el id de transaccion
     * utilizando el constructor de Transaccion
     *
     * @param idTransferencia id de la transferencia.
     * @param cuentaDestino Número de cuenta destino.
     * @param idTransaccion id de la transaccion.
     */
    public Transferencia(int idTransferencia, String cuentaDestino, int idTransaccion) {
        super(idTransaccion);
        this.idTransferencia = idTransferencia;
        this.cuentaDestino = cuentaDestino;
    }

    /**
     * Constructor que permite crear un objeto Transferencia con el id, la cuenta destino, asi como los atributos de transaccion
     * utilizando el constructor de Transaccion
     *
     * @param idTransferencia id de la transferencia.
     * @param cuentaDestino Número de cuenta destino.
     * @param idTransaccion id de la transaccion.
     * @param fecha fecha de la transaccion.
     * @param cantidad de la transaccion.
     * @param numCuenta origen de la transaccion.
     */
    public Transferencia(int idTransferencia, String cuentaDestino, int idTransaccion, Timestamp fecha, float cantidad, String numCuenta) {
        super(idTransaccion, fecha, cantidad, numCuenta);
        this.idTransferencia = idTransferencia;
        this.cuentaDestino = cuentaDestino;
    }

    // Métodos de acceso y modificación
    
    /**
     * Obtiene el número id de la transferencia.
     *
     * @return Número id de la transferencia.
     */
    public int getIdTransferencia() {
        return idTransferencia;
    }

    /**
     * Establece el id de la transferencia.
     *
     * @param idTransferencia Nuevo id de la transferencia.
     */
    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    /**
     * Obtiene el número de cuenta destino de la transferencia.
     *
     * @return Número de cuenta destino de la transferencia.
     */
    public String getCuentaDestino() {
        return cuentaDestino;
    }

    /**
     * Establece la cuenta destino de la transferencia.
     *
     * @param cuentaDestino Nuevo numero de cuenta destino de la transferencia.
     */
    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    /**
     * Calcula el código hash para el objeto Transferencia basado en su id.
     *
     * @return Código hash del objeto Transaccion.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.idTransferencia;
        return hash;
    }

    /**
     * Compara si dos objetos Transferencia son iguales basándose en el id.
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
        final Transferencia other = (Transferencia) obj;
        return this.idTransferencia == other.idTransferencia;
    }    

    /**
     * Representación en forma de cadena de la clase Transferencia.
     *
     * @return Cadena que representa la transferencia.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transferencia{");
        sb.append("idTransaccion=").append(this.getIdTransaccion());
        sb.append(", fecha=").append(this.getFecha());
        sb.append(", cantidad=").append(this.getCantidad());
        sb.append(", numCuenta=").append(this.getNumCuenta());
        sb.append(", idTransferencia=").append(idTransferencia);
        sb.append(", cuentaDestino=").append(cuentaDestino);
        sb.append('}');
        return sb.toString();
    }
    
}
