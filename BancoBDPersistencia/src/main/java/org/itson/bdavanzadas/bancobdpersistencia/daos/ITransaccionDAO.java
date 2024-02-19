/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.bancobddominio.Transaccion;
import java.sql.Date;
import java.sql.Timestamp;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.TransaccionNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * La interfaz ITransaccionDAO proporciona métodos para realizar operaciones relacionadas con la entidad Transaccion
 * en la capa de acceso a datos.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public interface ITransaccionDAO {
    
    /**
     * Registra una nueva transacción en la base de datos.
     *
     * @param transaccionNueva Objeto TransaccionNuevaDTO que contiene los datos de la nueva transacción.
     * @return Objeto Transaccion que representa la transacción registrada.
     * @throws PersistenciaException Si ocurre un error al intentar registrar la transacción.
     */
    Transaccion nueva(TransaccionNuevaDTO transaccionNueva) throws PersistenciaException;
    
    /**
     * Obtiene una transacción de la base de datos utilizando su identificador único.
     *
     * @param idTransaccion Identificador único de la transacción a obtener.
     * @return Objeto Transaccion que representa la transacción obtenida.
     * @throws PersistenciaException Si ocurre un error al intentar obtener la transacción.
     */
    Transaccion obtener(int idTransaccion) throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de todas las transacciones en la base de datos.
     *
     * @return Lista de objetos Transaccion que representa todas las transacciones.
     * @throws PersistenciaException Si ocurre un error al intentar consultar las transacciones.
     */
    List<Transaccion> consultar() throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de transacciones asociadas a una cuenta específica.
     *
     * @param numCuenta Número de cuenta asociado a las transacciones.
     * @return Lista de objetos Transaccion que representa las transacciones de la cuenta.
     * @throws PersistenciaException Si ocurre un error al intentar consultar las transacciones de la cuenta.
     */
    List<Transaccion> consultarTransaccionesCuenta(String numCuenta) throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de transacciones asociadas a una cuenta específica dentro de un rango de fechas.
     *
     * @param numCuenta     Número de cuenta asociado a las transacciones.
     * @param fechaInicio   Marca de tiempo que representa la fecha de inicio del rango.
     * @param fechaFin      Marca de tiempo que representa la fecha de fin del rango.
     * @return Lista de objetos Transaccion que representa las transacciones de la cuenta en el rango de fechas.
     * @throws PersistenciaException Si ocurre un error al intentar consultar las transacciones de la cuenta por fechas.
     */
    List<Transaccion> consultarTransaccionesCuentaPorFechas(String numCuenta, Timestamp fechaInicio, Timestamp fechaFin) throws PersistenciaException;
    
}
