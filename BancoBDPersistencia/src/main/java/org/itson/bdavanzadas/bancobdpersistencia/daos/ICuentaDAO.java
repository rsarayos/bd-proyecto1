/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.bancobddominio.Cuenta;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.CuentaNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author alex_
 */
public interface ICuentaDAO {
    Cuenta agregar(CuentaNuevaDTO cuentaNueva) throws PersistenciaException;
    Cuenta actualizar(CuentaNuevaDTO cuentaNueva) throws PersistenciaException;
    Cuenta obtener(String numCuenta) throws PersistenciaException;
    List<Cuenta> consultar() throws PersistenciaException;
}
