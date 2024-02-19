/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import java.util.Date;
import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.Validaciones;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 * La clase ClienteNuevoDTO representa un objeto de transferencia de datos (DTO) para la creación de nuevos clientes.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class ClienteNuevoDTO {
    
    // Objeto Validaciones utilizado para realizar validaciones en los datos del cliente.
    Validaciones valida = new Validaciones();
    
    // Atributos de la clase Cliente
    private String telefono;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private int edad;
    private String password;
    private int idDireccion;

    // Métodos de acceso y modificación
    
    /**
     * Obtiene el número telefónico del cliente.
     *
     * @return Número telefónico del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número telefónico del cliente.
     *
     * @param telefono Nuevo número telefónico del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombre Nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del cliente.
     *
     * @return Apellido paterno del cliente.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del cliente.
     *
     * @param apellidoPaterno Nuevo apellido paterno del cliente.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del cliente.
     *
     * @return Apellido materno del cliente.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del cliente.
     *
     * @param apellidoMaterno Nuevo apellido materno del cliente.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene la fecha de nacimiento del cliente.
     *
     * @return Fecha de nacimiento del cliente.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del cliente.
     *
     * @param fechaNacimiento Nueva fecha de nacimiento del cliente.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene la edad del cliente.
     *
     * @return Edad del cliente.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del cliente.
     *
     * @param edad Nueva edad del cliente.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene la contraseña del cliente.
     *
     * @return Contraseña del cliente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del cliente.
     *
     * @param password Nueva contraseña del cliente.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el identificador de la dirección del cliente.
     *
     * @return Identificador de la dirección del cliente.
     */
    public int getIdDireccion() {
        return idDireccion;
    }

    /**
     * Establece el identificador de la dirección del cliente.
     *
     * @param idDireccion Nuevo identificador de la dirección del cliente.
     */
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    /**
     * Realiza validaciones en los datos del cliente y devuelve true si los datos son válidos.
     *
     * @return true si los datos del cliente son válidos, false de lo contrario.
     * @throws ValidacionDTOException Si alguno de los campos del cliente no es válido.
     */
    public boolean esValido() throws ValidacionDTOException {
        if (this.telefono == null 
            || this.telefono.isBlank() 
            || !this.valida.validaTelefono(telefono)) {
            throw new ValidacionDTOException("Telefono de cliente invalido");
        }
        if (this.nombre == null 
            || this.nombre.isBlank() 
            || !this.valida.validaNombre(nombre)) {
            throw new ValidacionDTOException("Nombre de cliente invalido");
        }
        if (this.apellidoPaterno == null 
            || this.apellidoPaterno.isBlank() 
            || !this.valida.validaApellidos(apellidoPaterno)) {
            throw new ValidacionDTOException("Apellido paterno de cliente invalido");
        }
        if (this.apellidoMaterno == null 
            || this.apellidoMaterno.isBlank() 
            || !this.valida.validaApellidos(apellidoMaterno)) {
            throw new ValidacionDTOException("Apellido materno de cliente invalido");
        }
        if (this.password == null 
            || this.apellidoMaterno.isBlank() 
            || !this.valida.validaContra(password)) {
            throw new ValidacionDTOException("Contraseña de cliente invalido");
        }
        return true;
    }
    
}
