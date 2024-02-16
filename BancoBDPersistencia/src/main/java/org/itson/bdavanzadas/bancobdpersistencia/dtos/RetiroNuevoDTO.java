/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 *
 * @author alex_
 */
public class RetiroNuevoDTO extends TransaccionNuevaDTO{
    private int idRetiro;
    private String folioRetiro;
    private String contraseniaRetiro;
    private int estado;

    public int getIdRetiro() {
        return idRetiro;
    }

    public void setIdRetiro(int idRetiro) {
        this.idRetiro = idRetiro;
    }

    public String getFolioRetiro() {
        return folioRetiro;
    }

    public void setFolioRetiro(String folioRetiro) {
        this.folioRetiro = folioRetiro;
    }

    public String getContraseniaRetiro() {
        return contraseniaRetiro;
    }

    public void setContraseniaRetiro(String contraseniaRetiro) {
        this.contraseniaRetiro = contraseniaRetiro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    @Override
    public boolean esValido() throws ValidacionDTOException {
        return true;
    }
    
}
