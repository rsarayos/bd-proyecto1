/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import java.util.Date;
import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.Validaciones;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 * La clase CuentaNuevaDTO representa un objeto de transferencia de datos (DTO) para la creación de nuevas Cuentas.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class CuentaNuevaDTO {
    
    // Objeto Validaciones utilizado para realizar validaciones en los datos de la Cuenta.
    Validaciones valida = new Validaciones();
    
    // Atributos de la clase Cuenta
    private String numCuenta;
    private Date fechaApertura;
    private float saldo;
    private boolean estado;
    private String telefonoTitular;
    
    // Métodos de acceso y modificación

    /**
     * Obtiene el número de cuenta de la Cuenta.
     *
     * @return Número de cuenta de la Cuenta.
     */
    public String getNumCuenta() {
        return numCuenta;
    }

    /**
     * Establece el número de la cuenta a la Cuenta.
     *
     * @param numCuenta Nuevo número de cuenta.
     */
    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    /**
     * Obtiene la fecha de creacion de la cuenta.
     *
     * @return fecha de creacion de la cuenta.
     */
    public Date getFechaApertura() {
        return fechaApertura;
    }

    /**
     * Establece la fecha de apertura de la cuenta.
     *
     * @param fechaApertura Nueva fecha de apertura.
     */
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * Obtiene el saldo disponible de la cuenta.
     *
     * @return saldo de la cuenta.
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo de la cuenta.
     *
     * @param saldo Nuevo saldo.
     */
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtiene la vigencia de la cuenta.
     *
     * @return true si la cuenta se encuentra vigente, false en caso contrario
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado de la cuenta.
     *
     * @param estado true en caso de estar vigente, false en caso contrario.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el numero de telefono del titular de la cuenta.
     *
     * @return numero de telefono del titular de la cuenta.
     */
    public String getTelefonoTitular() {
        return telefonoTitular;
    }

    /**
     * Establece el telefono del titular de la cuenta.
     *
     * @param telefonoTitular true en caso de estar vigente, false en caso contrario.
     */
    public void setTelefonoTitular(String telefonoTitular) {
        this.telefonoTitular = telefonoTitular;
    }
    
    /**
     * Realiza validaciones en los datos de la Cuenta y devuelve true si los datos son válidos.
     *
     * @return true si los datos de la cuenta son válidos, false de lo contrario.
     * @throws ValidacionDTOException Si alguno de los campos de la cuenta no es válido.
     */
    public boolean esValido() throws ValidacionDTOException {
        if (this.numCuenta == null 
            || this.numCuenta.isBlank() 
            || !this.valida.validaCuenta(numCuenta)) {
            throw new ValidacionDTOException("numero de cuenta invalido");
        }
        if (this.saldo == 0f 
            || this.saldo < 0) {
            throw new ValidacionDTOException("saldo en cuenta invalida");
        }
        return true;
    }
    
}
