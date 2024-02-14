/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import org.itson.bdavanzadas.bancobddominio.Direccion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.DireccionNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author alex_
 */
public interface IDireccionDAO {
    Direccion agregar(DireccionNuevaDTO direccionNueva) throws PersistenciaException;
    Direccion actualizar(DireccionNuevaDTO direccionNueva) throws PersistenciaException;
}
