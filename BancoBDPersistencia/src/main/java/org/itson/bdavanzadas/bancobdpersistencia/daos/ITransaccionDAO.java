/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.bancobddominio.Transaccion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.TransaccionNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author alex_
 */
public interface ITransaccionDAO {
    Transaccion nueva(TransaccionNuevaDTO transaccionNueva) throws PersistenciaException;
    List<Transaccion> consultar() throws PersistenciaException;
}
