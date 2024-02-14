/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import java.util.Date;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 *
 * @author alex_
 */
public class ClienteNuevoDTO {
    
    private String telefono;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private int edad;
    private String password;
    private int idDireccion;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    public boolean esValido() throws ValidacionDTOException {
        if (this.telefono == null 
            || this.telefono.isBlank() 
            || this.telefono.trim().length() > 10) {
            throw new ValidacionDTOException("Telefono de cliente invalido");
        }
        if (this.nombre == null 
            || this.nombre.isBlank() 
            || this.nombre.trim().length() > 100) {
            throw new ValidacionDTOException("Nombre de cliente invalido");
        }
        if (this.apellidoPaterno == null 
            || this.apellidoPaterno.isBlank() 
            || this.apellidoPaterno.trim().length() > 50) {
            throw new ValidacionDTOException("Apellido paterno de cliente invalido");
        }
        if (this.apellidoMaterno == null 
            || this.apellidoMaterno.isBlank() 
            || this.apellidoMaterno.trim().length() > 50) {
            throw new ValidacionDTOException("Apellido materno de cliente invalido");
        }
        return true;
    }
    
}
