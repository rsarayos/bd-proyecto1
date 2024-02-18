/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobddominio;

import java.util.Date;
import java.util.Objects;

/**
 * Representa la clase Cliente que encapsula la información de un cliente.
 * 
 * Un objeto de la clase Cliente contiene los siguientes atributos:
 * 
 *     telefono: Número telefónico del cliente.
 *     nombre: Nombre del cliente.
 *     apellidoPaterno: Apellido paterno del cliente.
 *     apellidoMaterno: Apellido materno del cliente.
 *     fechaNacimiento: Fecha de nacimiento del cliente.
 *     edad: Edad del cliente.
 *     password: Contraseña del cliente.
 *     idDireccion: Identificador de la dirección del cliente.
 * 
 * La clase proporciona constructores para instanciar objetos de Cliente de diferentes maneras,
 * así como métodos de acceso y modificación para cada atributo. Además, implementa los métodos
 * equals, hashCode y toString para facilitar la comparación, generación de hash y representación
 * en cadena de los objetos Cliente.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class Cliente {

    // Atributos de la clase
    
    private String telefono;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private int edad;
    private String password;
    private int idDireccion;

    /**
     * Constructor por defecto. Crea un objeto Cliente sin inicializar sus atributos.
     */
    public Cliente() {
    }

    /**
     * Constructor que permite crear un objeto Cliente con el número de teléfono y contraseña proporcionados.
     *
     * @param telefono Número telefónico del cliente.
     * @param password Contraseña del cliente.
     */
    public Cliente(String telefono, String password) {
        this.telefono = telefono;
        this.password = password;
    }

    /**
     * Constructor que permite crear un objeto Cliente con todos los atributos especificados.
     *
     * @param telefono         Número telefónico del cliente.
     * @param nombre           Nombre del cliente.
     * @param apellidoPaterno  Apellido paterno del cliente.
     * @param apellidoMaterno  Apellido materno del cliente.
     * @param fechaNacimiento  Fecha de nacimiento del cliente.
     * @param edad             Edad del cliente.
     * @param password         Contraseña del cliente.
     * @param idDireccion      Identificador de la dirección del cliente.
     */
    public Cliente(String telefono, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento,int edad,  String password, int idDireccion) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.password = password;
        this.idDireccion = idDireccion;
    }

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
     * Calcula el código hash para el objeto Cliente basado en su número telefónico.
     *
     * @return Código hash del objeto Cliente.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.telefono);
        return hash;
    }

    /**
     * Compara si dos objetos Cliente son iguales basándose en el número de telefono.
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.telefono, other.telefono);
    }

    /**
     * Representación en forma de cadena de la clase Cliente.
     *
     * @return Cadena que representa la cuenta.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("telefono=").append(telefono);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellidoPaterno=").append(apellidoPaterno);
        sb.append(", apellidoMaterno=").append(apellidoMaterno);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", edad=").append(edad);
        sb.append(", password=").append(password);
        sb.append(", idDireccion=").append(idDireccion);
        sb.append('}');
        return sb.toString();
    }

}
