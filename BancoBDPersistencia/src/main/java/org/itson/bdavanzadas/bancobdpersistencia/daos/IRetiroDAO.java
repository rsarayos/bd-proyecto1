
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.itson.bdavanzadas.bancobddominio.Retiro;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.RetiroNuevoDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * La interfaz IRetiroDAO proporciona métodos para realizar operaciones relacionadas con retiros
 * en la capa de acceso a datos.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public interface IRetiroDAO {
    
    /**
     * Registra un nuevo retiro en la base de datos.
     *
     * @param retiroNuevo Objeto RetiroNuevoDTO que contiene los datos del nuevo retiro.
     * @return Objeto Retiro que representa el retiro registrado.
     * @throws PersistenciaException Si ocurre un error al intentar registrar el retiro.
     */
    Retiro nuevo(RetiroNuevoDTO retiroNuevo) throws PersistenciaException;
    
    /**
     * Actualiza la información de un retiro existente en la base de datos.
     *
     * @param retiroNuevo Objeto RetiroNuevoDTO que contiene los nuevos datos del retiro.
     * @return Objeto Retiro que representa el retiro actualizado.
     * @throws PersistenciaException Si ocurre un error al intentar actualizar el retiro.
     */
    Retiro actualizar(RetiroNuevoDTO retiroNuevo) throws PersistenciaException;
    
    /**
     * Obtiene un retiro de la base de datos utilizando su identificador único.
     *
     * @param idRetiro Identificador único del retiro a obtener.
     * @return Objeto Retiro que representa el retiro obtenido.
     * @throws PersistenciaException Si ocurre un error al intentar obtener el retiro.
     */
    Retiro obtener(int idRetiro) throws PersistenciaException;
    
    /**
     * Obtiene un retiro de la base de datos utilizando su folio único.
     *
     * @param folioRetiro Folio único del retiro a obtener.
     * @return Objeto Retiro que representa el retiro obtenido.
     * @throws PersistenciaException Si ocurre un error al intentar obtener el retiro por folio.
     */
    Retiro obtenerPorFolio(String folioRetiro) throws PersistenciaException;
    
    /**
     * Realiza un retiro de fondos.
     *
     * @param idRetiro Identificador único del retiro a realizar.
     * @return Objeto Retiro que representa el retiro realizado.
     * @throws PersistenciaException Si ocurre un error al intentar realizar el retiro.
     */
    Retiro realizarRetiro(int idRetiro) throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de todos los retiros registrados en la base de datos.
     *
     * @return Lista de objetos Retiro que representa todos los retiros.
     * @throws PersistenciaException Si ocurre un error al intentar consultar los retiros.
     */
    List<Retiro> consultar() throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de retiros asociados a una cuenta específica.
     *
     * @param numCuenta Número de cuenta asociado a los retiros.
     * @return Lista de objetos Retiro que representa los retiros de la cuenta.
     * @throws PersistenciaException Si ocurre un error al intentar consultar los retiros de la cuenta.
     */
    List<Retiro> consultarRetiroCuenta(String numCuenta) throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de retiros asociados a una cuenta específica dentro de un rango de fechas.
     *
     * @param numCuenta    Número de cuenta asociado a los retiros.
     * @param fechaInicio  Fecha de inicio del rango.
     * @param fechaFin     Fecha de fin del rango.
     * @return Lista de objetos Retiro que representa los retiros de la cuenta en el rango de fechas.
     * @throws PersistenciaException Si ocurre un error al intentar consultar los retiros de la cuenta por fechas.
     */
    List<Retiro> consultarRetiroCuentaPorFechas(String numCuenta, Timestamp fechaInicio, Timestamp fechaFin) throws PersistenciaException;
    
}
