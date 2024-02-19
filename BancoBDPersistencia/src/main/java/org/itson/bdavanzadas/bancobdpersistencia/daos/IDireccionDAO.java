/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import org.itson.bdavanzadas.bancobddominio.Direccion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.DireccionNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * La interfaz IDireccionDAO proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * relacionadas con la entidad Direccion en la capa de acceso a datos.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public interface IDireccionDAO {
    
    /**
     * Agrega una nueva dirección a la base de datos.
     *
     * @param direccionNueva Objeto DireccionNuevaDTO que contiene los datos de la nueva dirección.
     * @return Objeto Direccion que representa la dirección agregada.
     * @throws PersistenciaException Si ocurre un error al intentar agregar la dirección.
     */
    Direccion agregar(DireccionNuevaDTO direccionNueva) throws PersistenciaException;
    
    /**
     * Obtiene una dirección de la base de datos utilizando su identificador único.
     *
     * @param idDireccion Identificador único de la dirección a obtener.
     * @return Objeto Direccion que representa la dirección obtenida.
     * @throws PersistenciaException Si ocurre un error al intentar obtener la dirección.
     */
    Direccion obtener(int idDireccion) throws PersistenciaException;
    
    /**
     * Actualiza la información de una dirección existente en la base de datos.
     *
     * @param direccionNueva Objeto DireccionNuevaDTO que contiene los nuevos datos de la dirección.
     * @return Objeto Direccion que representa la dirección actualizada.
     * @throws PersistenciaException Si ocurre un error al intentar actualizar la dirección.
     */
    Direccion actualizar(DireccionNuevaDTO direccionNueva) throws PersistenciaException;
    
}
