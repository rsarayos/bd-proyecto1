/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.itson.bdavanzadas.bancobddominio.Transferencia;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.TransferenciaNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * La interfaz ITransferenciaDAO proporciona métodos para realizar operaciones relacionadas con transferencias
 * en la capa de acceso a datos.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public interface ITransferenciaDAO {
    
    /**
     * Registra una nueva transferencia en la base de datos.
     *
     * @param transferenciaNueva Objeto TransferenciaNuevaDTO que contiene los datos de la nueva transferencia.
     * @return Objeto Transferencia que representa la transferencia registrada.
     * @throws PersistenciaException Si ocurre un error al intentar registrar la transferencia.
     */
    Transferencia nueva(TransferenciaNuevaDTO transferenciaNueva) throws PersistenciaException;
    
    /**
     * Obtiene una transferencia de la base de datos utilizando su identificador único.
     *
     * @param idTransferencia Identificador único de la transferencia a obtener.
     * @return Objeto Transferencia que representa la transferencia obtenida.
     * @throws PersistenciaException Si ocurre un error al intentar obtener la transferencia.
     */
    Transferencia obtener(int idTransferencia) throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de todas las transferencias registradas en la base de datos.
     *
     * @return Lista de objetos Transferencia que representa todas las transferencias.
     * @throws PersistenciaException Si ocurre un error al intentar consultar las transferencias.
     */
    List<Transferencia> consultar() throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de transferencias asociadas a una cuenta específica.
     *
     * @param numCuenta Número de cuenta asociado a las transferencias.
     * @return Lista de objetos Transferencia que representa las transferencias de la cuenta.
     * @throws PersistenciaException Si ocurre un error al intentar consultar las transferencias de la cuenta.
     */
    List<Transferencia> consultarTransCuenta(String numCuenta) throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de transferencias asociadas a una cuenta específica dentro de un rango de fechas.
     *
     * @param numCuenta    Número de cuenta asociado a las transferencias.
     * @param fechaInicio  Fecha de inicio del rango.
     * @param fechaFin     Fecha de fin del rango.
     * @return Lista de objetos Transferencia que representa las transferencias de la cuenta en el rango de fechas.
     * @throws PersistenciaException Si ocurre un error al intentar consultar las transferencias de la cuenta por fechas.
     */
    List<Transferencia> consultarTransCuentaPorFechas(String numCuenta, Timestamp fechaInicio, Timestamp fechaFin) throws PersistenciaException;
    
    /**
     * Realiza una transferencia de fondos entre dos cuentas.
     *
     * @param cuentaOrigen  Número de cuenta de origen.
     * @param cuentaDestino Número de cuenta de destino.
     * @param cantidad      Cantidad a transferir.
     * @return Objeto Transferencia que representa la transferencia realizada.
     * @throws PersistenciaException Si ocurre un error al intentar realizar la transferencia.
     */
    Transferencia realizarTransferencia(String cuentaOrigen, String cuentaDestino, float cantidad) throws PersistenciaException;
    
}
