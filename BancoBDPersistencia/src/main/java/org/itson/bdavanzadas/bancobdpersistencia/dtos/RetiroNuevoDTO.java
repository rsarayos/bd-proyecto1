/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 * La clase RetiroNuevoDTO representa un objeto de transferencia de datos (DTO) para la creación de nuevos Retiros.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class RetiroNuevoDTO extends TransaccionNuevaDTO{
    
    // Atributos de la clase Direccion
    private int idRetiro;
    private String folioRetiro;
    private String contraseniaRetiro;
    private int estado;

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
     * Realiza validaciones en los datos del retiro y devuelve true si los datos son válidos.
     *
     * @return true si los datos del retiro son válidos, false de lo contrario.
     * @throws ValidacionDTOException Si alguno de los campos del cliente no es válido.
     */
    @Override
    public boolean esValido() throws ValidacionDTOException {
        if (this.folioRetiro == null 
            || this.folioRetiro.isBlank()) {
            throw new ValidacionDTOException("folio retiro invalido");
        }
        if (this.contraseniaRetiro == null 
            || this.contraseniaRetiro.isBlank()) {
            throw new ValidacionDTOException("contraseña de retiro invalida");
        }
        return true;
    }
    
}
