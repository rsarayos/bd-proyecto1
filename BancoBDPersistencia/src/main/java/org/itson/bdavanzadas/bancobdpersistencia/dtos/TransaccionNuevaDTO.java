/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import java.sql.Timestamp;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 * La clase TransaccionNuevaDTO representa un objeto de transferencia de datos (DTO) para la creación de nuevas transacciones.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class TransaccionNuevaDTO {
    
    // Atributos de la clase Direccion
    private int idTransaccion;
    private Timestamp fecha;
    private float cantidad;
    private String numCuenta;
    private String estado;

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
     * Realiza validaciones en los datos de la transaccion y devuelve true si los datos son válidos.
     *
     * @return true si los datos de la transaccion son válidos, false de lo contrario.
     * @throws ValidacionDTOException Si alguno de los campos de la transaccion no es válido.
     */
    public boolean esValido() throws ValidacionDTOException {
        if (this.cantidad < 0) {
            throw new ValidacionDTOException("cantidad invalida");
        }
        return true;
    }
    
}
