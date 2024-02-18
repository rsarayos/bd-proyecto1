/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobddominio;

import java.util.Date;
import java.util.Objects;

/**
 * Representa la clase Cuenta que encapsula la información de una cuenta financiera.
 * 
 * Un objeto de la clase Cuenta contiene los siguientes atributos:
 * 
 *     numCuenta: Número unico de la cuenta.
 *     fechaApertura: fecha en la que se creo la cuenta.
 *     saldo: fondos disponibles en la cuenta.
 *     estado: vigencia de la cuenta.
 *     telefonoTitular: el numero telefonico del cliente titular de la cuenta.
 * 
 * La clase proporciona constructores para instanciar objetos de Cuenta de diferentes maneras,
 * así como métodos de acceso y modificación para cada atributo. Además, implementa los métodos
 * equals, hashCode y toString para facilitar la comparación, generación de hash y representación
 * en cadena de los objetos Cuenta.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class Cuenta {
    
    // Atributos de la clase
    
    private String numCuenta;
    private Date fechaApertura;
    private float saldo;
    private boolean estado;
    private String telefonoTitular;

    /**
     * Constructor por defecto. Crea un objeto Cuenta sin inicializar sus atributos.
     */
    public Cuenta() {
    }

    /**
     * Constructor que inicializa todos los atributos de la clase Cuenta.
     *
     * @param numCuenta        Número de cuenta único que identifica la cuenta.
     * @param fechaApertura    Fecha de apertura de la cuenta.
     * @param saldo            Saldo actual de la cuenta.
     * @param estado           Estado de la cuenta (activo/inactivo).
     * @param telefonoTitular  Número de teléfono del titular de la cuenta.
     */
    public Cuenta(String numCuenta, Date fechaApertura, float saldo, boolean estado, String telefonoTitular) {
        this.numCuenta = numCuenta;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.estado = true;
        this.telefonoTitular = telefonoTitular;
    }
    
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
     * Calcula el código hash para el objeto Cuenta basado en su número de cuenta.
     *
     * @return Código hash del objeto Cuenta.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.numCuenta);
        return hash;
    }

    /**
     * Compara si dos objetos Cuenta son iguales basándose en el número de cuenta.
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
        final Cuenta other = (Cuenta) obj;
        return Objects.equals(this.numCuenta, other.numCuenta);
    }

    /**
     * Representación en forma de cadena de la clase Cuenta.
     *
     * @return Cadena que representa la cuenta.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cuenta{");
        sb.append("numCuenta=").append(numCuenta);
        sb.append(", fechaApertura=").append(fechaApertura);
        sb.append(", saldo=").append(saldo);
        sb.append(", estado=").append(estado);
        sb.append(", telefonoTitular=").append(telefonoTitular);
        sb.append('}');
        return sb.toString();
    }
    
}
