/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobddominio;

/**
 * Representa la clase Direccion que encapsula la información de una Direccion.
 * 
 * Un objeto de la clase Direccion contiene los siguientes atributos:
 * 
 *     idDireccion: Numero unico de identificación de la direccion.
 *     calle: Nombre de la calle.
 *     colonia: Nombre de la colonio.
 *     numero: Numero exterior del domicilio.
 *     ciudad: Nombre de la ciudad.
 *     cp: Codigo postal de la direccion.
 * 
 * La clase proporciona constructores para instanciar objetos de Direccion de diferentes maneras,
 * así como métodos de acceso y modificación para cada atributo. Además, implementa los métodos
 * equals, hashCode y toString para facilitar la comparación, generación de hash y representación
 * en cadena de los objetos Direccion.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class Direccion {
    
    // Atributos de la clase
    
    private int idDireccion;
    private String calle;
    private String colonia;
    private String numero;
    private String ciudad;
    private String cp;

    /**
     * Constructor que permite crear un objeto Direccion con todos los atributos especificados.
     *
     * @param idDireccion      Número de identificacion de la direccion.
     * @param calle            Nombre de la calle.
     * @param colonia          Nombre de la colonia.
     * @param numero           Numero de domicilio.
     * @param ciudad           Nombre de la ciudad.
     * @param cp               Codigo postal de la direccion.
     */
    public Direccion(int idDireccion, String calle, String colonia, String numero, String ciudad, String cp) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
        this.ciudad = ciudad;
        this.cp = cp;
    }
    
    // Métodos de acceso y modificación
    
    /**
     * Obtiene el número de identificacion de la direccion.
     *
     * @return Número de identificacion de la direccion.
     */
    public int getIdDireccion() {
        return idDireccion;
    }

    /**
     * Establece el número de identicacion de la direccion.
     *
     * @param idDireccion Nuevo número de identificacion de la direccion.
     */
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    /**
     * Obtiene el nombre de la calle de la direccion.
     *
     * @return nombre de calle de la direccion.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece el nombre de calle de la direccion.
     *
     * @param calle Nuevo nombre de calle de la direccion.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene el nombre de la colonia de la direccion.
     *
     * @return nombre de colonia de la direccion.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Establece el nombre de colonia de la direccion.
     *
     * @param colonia Nuevo nombre de colonia de la direccion.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Obtiene el numero exterior de la direccion.
     *
     * @return numero exterior de la direccion.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el numero de exterior de la direccion.
     *
     * @param numero Nuevo numero exterior de la direccion.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el nombre de la ciudad de la direccion.
     *
     * @return nombre de ciudad de la direccion.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece el nombre de ciudad de la direccion.
     *
     * @param ciudad Nuevo nombre de ciudad de la direccion.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene el codigo postal de la direccion.
     *
     * @return codigo postal de la direccion.
     */
    public String getCp() {
        return cp;
    }

    /**
     * Establece el codigo postal de la direccion.
     *
     * @param cp Nuevo codigo postal de la direccion.
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * Calcula el código hash para el objeto Direccion basado en su id.
     *
     * @return Código hash del objeto Direccion.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.idDireccion;
        return hash;
    }

    /**
     * Compara si dos objetos Direccion son iguales basándose en su id.
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
        final Direccion other = (Direccion) obj;
        return this.idDireccion == other.idDireccion;
    }

    /**
     * Representación en forma de cadena de la clase Direccion.
     *
     * @return Cadena que representa la cuenta.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Direccion{");
        sb.append("idDireccion=").append(idDireccion);
        sb.append(", calle=").append(calle);
        sb.append(", colonia=").append(colonia);
        sb.append(", numero=").append(numero);
        sb.append(", ciudad=").append(ciudad);
        sb.append(", cp=").append(cp);
        sb.append('}');
        return sb.toString();
    }
    
}
