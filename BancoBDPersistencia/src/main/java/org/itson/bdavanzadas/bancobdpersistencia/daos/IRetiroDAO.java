
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.itson.bdavanzadas.bancobddominio.Retiro;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.RetiroNuevoDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author alex_
 */
public interface IRetiroDAO {
    Retiro nuevo(RetiroNuevoDTO retiroNuevo) throws PersistenciaException;
    Retiro actualizar(RetiroNuevoDTO retiroNuevo) throws PersistenciaException;
    Retiro obtener(int idRetiro) throws PersistenciaException;
    Retiro obtenerPorFolio(String folioRetiro) throws PersistenciaException;
    Retiro realizarRetiro(int idRetiro) throws PersistenciaException;
    List<Retiro> consultar() throws PersistenciaException;
    List<Retiro> consultarRetiroCuenta(String numCuenta) throws PersistenciaException;
    List<Retiro> consultarRetiroCuentaPorFechas(String numCuenta, Timestamp fechaInicio, Timestamp fechaFin) throws PersistenciaException;
}
