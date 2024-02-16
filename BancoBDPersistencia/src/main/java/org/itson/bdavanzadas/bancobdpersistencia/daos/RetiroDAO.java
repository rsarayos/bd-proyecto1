/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancobddominio.Retiro;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.RetiroNuevoDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author alex_
 */
public class RetiroDAO implements IRetiroDAO{
    
    final IConexion conexionDB;
    static final Logger logger = Logger.getLogger(TransferenciaDAO.class.getName());

    public RetiroDAO(IConexion conexionDB) {
        this.conexionDB = conexionDB;
    }

    @Override
    public Retiro nuevo(RetiroNuevoDTO retiroNuevo) throws PersistenciaException {
        String setenciaSQL = """
                             INSERT INTO retiros (folioRetiro, contraseniaRetiro, estado, idTransaccion)
                                         VALUES(?,?,?,?);
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(
                setenciaSQL,
                Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, retiroNuevo.getFolioRetiro());
            comando.setString(2, retiroNuevo.getContraseniaRetiro());
            comando.setInt(3, retiroNuevo.getEstado());
            comando.setInt(4, retiroNuevo.getIdTransaccion());
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agrearon {0} retiro", numeroRegistrosInsertados);
            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            return new Retiro(idsGenerados.getInt(1),
                    retiroNuevo.getFolioRetiro(),
                    retiroNuevo.getContraseniaRetiro(),
                    retiroNuevo.getEstado(), 
                    retiroNuevo.getIdTransaccion(), 
                    retiroNuevo.getFecha(), 
                    retiroNuevo.getCantidad(), 
                    retiroNuevo.getNumCuenta());
        } catch (SQLException ex) {
            logger.log(Level.INFO, "No se ha podido agregar el retiro", ex);
            return null;
        }
    }

    @Override
    public Retiro actualizar(RetiroNuevoDTO retiroNuevo) throws PersistenciaException {
        String setenciaSQL = """
                             UPDATE retiros
                             SET estado=?
                             WHERE idRetiro=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(setenciaSQL);) {
            comando.setInt(1, retiroNuevo.getEstado());
            comando.setInt(2, retiroNuevo.getIdRetiro());
            int numeroRegistrosActualizados = comando.executeUpdate();
            logger.log(Level.INFO, "Se actualizaron {0} registros", numeroRegistrosActualizados);
                return new Retiro(retiroNuevo.getIdRetiro(),
                    retiroNuevo.getFolioRetiro(),
                    retiroNuevo.getContraseniaRetiro(),
                    retiroNuevo.getEstado(), 
                    retiroNuevo.getIdTransaccion(), 
                    retiroNuevo.getFecha(), 
                    retiroNuevo.getCantidad(), 
                    retiroNuevo.getNumCuenta());
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudo actualizar el retiro", ex);
            return null;
        }
    }
    
    @Override
    public Retiro obtener(int idRetiro) throws PersistenciaException {
        String setenciaSQL = """
                             SELECT idRetiro, folioRetiro, contraseniaRetiro, estado, idTransaccion
                             FROM retiros
                             WHERE idRetiro=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion();
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL);
        ) {
            comando.setInt(1, idRetiro);

            ResultSet resultados = comando.executeQuery();

            if (resultados.next()) {
                return new Retiro(
                        resultados.getInt("idRetiro"),
                        resultados.getString("folioRetiro"),
                        resultados.getString("contraseniaRetiro"),
                        resultados.getInt("estado"),
                        resultados.getInt("idTransaccion")
                );
            } else {
                return null; // No se encontró el socio con el telefono dado
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el retiro", ex);
            throw new PersistenciaException("No se ha podido obtener el retiro", ex);
        }
    }
    
    @Override
    public List<Retiro> consultar() throws PersistenciaException {
        String setenciaSQL = """
                             SELECT *
                             FROM retiros
                             INNER JOIN transacciones ON retiros.idTransaccion = transacciones.idTransaccion;
                             """;
        List<Retiro> listaRetiros = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); 
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL); 
                ResultSet resultados = comando.executeQuery();) {
            
            while (resultados.next()) {                
                int idRetiro = resultados.getInt("idRetiro");
                String folioRetiro = resultados.getString("folioRetiro");
                String contraseniaRetiro = resultados.getString("contraseniaRetiro");
                int estado = resultados.getInt("estado");
                int idTransaccion = resultados.getInt("idTransaccion");
                Timestamp fecha = resultados.getTimestamp("fecha");
                long cantidad = resultados.getInt("cantidad");
                String numCuenta = resultados.getString("numCuenta");
                Retiro retiro = new Retiro(idRetiro,
                    folioRetiro,
                    contraseniaRetiro,
                    estado, 
                    idTransaccion, 
                    fecha, 
                    cantidad, 
                    numCuenta);
                listaRetiros.add(retiro);
            }
            logger.log(Level.INFO, "Se consultaron {0} retiros", listaRetiros.size());
            return listaRetiros;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar los retiros", ex);
            throw new PersistenciaException("No se pudieron consultar los retiros", ex);
        }
    }

    @Override
    public List<Retiro> consultarRetiroCuenta(String numCuenta) throws PersistenciaException {
        String setenciaSQL = """
                             SELECT *
                             FROM retiros
                             INNER JOIN transacciones ON retiros.idTransaccion = transacciones.idTransaccion
                             WHERE numCuenta=?;
                             """;
        List<Retiro> listaRetiros = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); 
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL); 
                ) {
            comando.setString(1, numCuenta);
            ResultSet resultados = comando.executeQuery();
            while (resultados.next()) {                
                int idRetiro = resultados.getInt("idRetiro");
                String folioRetiro = resultados.getString("folioRetiro");
                String contraseniaRetiro = resultados.getString("contraseniaRetiro");
                int estado = resultados.getInt("estado");
                int idTransaccion = resultados.getInt("idTransaccion");
                Timestamp fecha = resultados.getTimestamp("fecha");
                long cantidad = resultados.getInt("cantidad");
                Retiro retiro = new Retiro(idRetiro,
                    folioRetiro,
                    contraseniaRetiro,
                    estado, 
                    idTransaccion, 
                    fecha, 
                    cantidad, 
                    numCuenta);
                listaRetiros.add(retiro);
            }
            logger.log(Level.INFO, "Se consultaron {0} retiros", listaRetiros.size());
            return listaRetiros;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar los retiros", ex);
            throw new PersistenciaException("No se pudieron consultar los retiros", ex);
        }
    }

    @Override
    public Retiro realizarRetiro(int idRetiro) throws PersistenciaException {
        String setenciaSQL = "{call realizar_retiro(?, ?)}";
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); 
                CallableStatement callableStatement = conexion.prepareCall(setenciaSQL);) {
            callableStatement.setInt(1, idRetiro);
            callableStatement.registerOutParameter(2, Types.BOOLEAN);
            callableStatement.execute();
            
            boolean resultado = callableStatement.getBoolean(2);
            
            if (resultado) {
                    return obtener(idRetiro);
                } else {
                    throw new PersistenciaException("No se pudo realizar el retiro");
                }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudo procesar el retiro", ex);
            throw new PersistenciaException("No se pudo procesar el retiro", ex);
        }
    }
    
}
