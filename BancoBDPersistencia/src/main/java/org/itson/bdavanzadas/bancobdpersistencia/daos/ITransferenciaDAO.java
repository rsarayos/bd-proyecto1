/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.bancobddominio.Transferencia;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.TransferenciaNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author alex_
 */
public interface ITransferenciaDAO {
    Transferencia nueva(TransferenciaNuevaDTO transferenciaNueva) throws PersistenciaException;
    List<Transferencia> consultar() throws PersistenciaException;
}
