/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.bancobddominio.Cliente;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.ClienteNuevoDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * La interfaz IClientesDAO proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * relacionadas con la entidad Cliente en la capa de acceso a datos.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public interface IClientesDAO {
    
    /**
     * Agrega un nuevo cliente a la base de datos.
     *
     * @param clienteNuevo Objeto ClienteNuevoDTO que contiene los datos del nuevo cliente.
     * @return Objeto Cliente que representa el cliente agregado.
     * @throws PersistenciaException Si ocurre un error al intentar agregar el cliente.
     */
    Cliente agregar(ClienteNuevoDTO clienteNuevo) throws PersistenciaException;
    
    /**
     * Actualiza la información de un cliente existente en la base de datos.
     *
     * @param clienteNuevo Objeto ClienteNuevoDTO que contiene los nuevos datos del cliente.
     * @param telefono     Número de teléfono del cliente a actualizar.
     * @return Objeto Cliente que representa el cliente actualizado.
     * @throws PersistenciaException Si ocurre un error al intentar actualizar el cliente.
     */
    Cliente actualizar(ClienteNuevoDTO clienteNuevo, String telefono) throws PersistenciaException;
    
    /**
     * Obtiene un cliente de la base de datos utilizando su número de teléfono.
     *
     * @param telefono Número de teléfono del cliente a obtener.
     * @return Objeto Cliente que representa el cliente obtenido.
     * @throws PersistenciaException Si ocurre un error al intentar obtener el cliente.
     */
    Cliente obtener(String telefono) throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de todos los clientes en la base de datos.
     *
     * @return Lista de objetos Cliente que representa todos los clientes.
     * @throws PersistenciaException Si ocurre un error al intentar consultar los clientes.
     */
    List<Cliente> consultar() throws PersistenciaException;
    
}
