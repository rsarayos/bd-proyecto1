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
 *
 * @author alex_
 */
public interface IClientesDAO {
    Cliente agregar(ClienteNuevoDTO clienteNuevo) throws PersistenciaException;
    Cliente actualizar(ClienteNuevoDTO clienteNuevo) throws PersistenciaException;
    Cliente obtener(String telefono) throws PersistenciaException;
    List<Cliente> consultar() throws PersistenciaException;
}
