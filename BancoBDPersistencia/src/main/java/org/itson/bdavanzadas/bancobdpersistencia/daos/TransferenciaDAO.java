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
import org.itson.bdavanzadas.bancobddominio.Transferencia;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.TransferenciaNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * Clase que implementa la interfaz ITransferenciaDAO y proporciona métodos para interactuar con la tabla de transferencias en la base de datos.
 * Esta clase realiza operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la tabla de transferencias.
 *
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class TransferenciaDAO implements ITransferenciaDAO {

    // Objeto IConexion utilizado para obtener conexiones a la base de datos.
    final IConexion conexionDB;
    // Objeto Logger utilizado para registrar mensajes de registro.
    static final Logger logger = Logger.getLogger(TransferenciaDAO.class.getName());

    /**
     * Constructor que recibe una conexión a la base de datos.
     *
     * @param conexionDB Conexión a la base de datos.
     */
    public TransferenciaDAO(IConexion conexionDB) {
        this.conexionDB = conexionDB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transferencia nueva(TransferenciaNuevaDTO transferenciaNueva) throws PersistenciaException {
        String sentenciaSQL = """
                             INSERT INTO transacciones (idTransaccion, cantidad, numCuenta)
                                         VALUES(?,?,?);
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setInt(1, transferenciaNueva.getIdTransaccion());
            comando.setFloat(2, transferenciaNueva.getCantidad());
            comando.setString(3, transferenciaNueva.getCuentaDestino());
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agrearon {0} transferencia", numeroRegistrosInsertados);
            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            Transferencia transferencia = new Transferencia(idsGenerados.getInt(1));
            return obtener(transferencia.getIdTransferencia());
        } catch (SQLException ex) {
            logger.log(Level.INFO, "No se ha podido agregar la transferencia", ex);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transferencia obtener(int idTransferencia) throws PersistenciaException {
        String sentenciaSQL = """
                             SELECT *
                             FROM transferencias
                             INNER JOIN transacciones ON transferencias.idTransaccion = transacciones.idTransaccion
                             WHERE idTransferencia=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setInt(1, idTransferencia);

            ResultSet resultados = comando.executeQuery();

            if (resultados.next()) {
                return new Transferencia(
                        resultados.getInt("idTransferencia"),
                        resultados.getString("numCuentaDestino"),
                        resultados.getInt("idTransaccion"),
                        resultados.getTimestamp("fecha"),
                        resultados.getFloat("cantidad"),
                        resultados.getString("numCuenta")
                );
            } else {
                return null; // No se encontró el socio con el telefono dado
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la transferencia", ex);
            throw new PersistenciaException("No se ha podido obtener la transferencia", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Transferencia> consultar() throws PersistenciaException {
        String sentenciaSQL = """
                             SELECT *
                             FROM transferencias
                             INNER JOIN transacciones ON transferencias.idTransaccion = transacciones.idTransaccion;
                             """;
        List<Transferencia> listaTranferencias = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL); ResultSet resultados = comando.executeQuery();) {

            while (resultados.next()) {
                int idTransferencia = resultados.getInt("idTransferencia");
                int idTransaccion = resultados.getInt("idTransaccion");
                String numCuentaDest = resultados.getString("numCuentaDestino");
                Timestamp fecha = resultados.getTimestamp("fecha");
                float cantidad = resultados.getFloat("cantidad");
                String numCuenta = resultados.getString("numCuenta");
                Transferencia transferencia = new Transferencia(idTransferencia,
                        numCuentaDest,
                        idTransaccion,
                        fecha,
                        cantidad,
                        numCuenta);
                listaTranferencias.add(transferencia);
            }
            logger.log(Level.INFO, "Se consultaron {0} transferencias", listaTranferencias.size());
            return listaTranferencias;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar las transacciones", ex);
            throw new PersistenciaException("No se pudieron consultar las transacciones", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Transferencia> consultarTransCuenta(String numCuenta) throws PersistenciaException {
        String sentenciaSQL = """
                             SELECT *
                             FROM transferencias
                             INNER JOIN transacciones ON transferencias.idTransaccion = transacciones.idTransaccion
                             WHERE numCuenta=?
                             ORDER BY transacciones.fecha DESC;
                             """;
        List<Transferencia> listaTranferencias = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setString(1, numCuenta);
            ResultSet resultados = comando.executeQuery();
            while (resultados.next()) {
                int idTransferencia = resultados.getInt("idTransferencia");
                int idTransaccion = resultados.getInt("idTransaccion");
                String numCuentaDest = resultados.getString("numCuentaDestino");
                Timestamp fecha = resultados.getTimestamp("fecha");
                float cantidad = resultados.getFloat("cantidad");
                Transferencia transferencia = new Transferencia(idTransferencia,
                        numCuentaDest,
                        idTransaccion,
                        fecha,
                        cantidad,
                        numCuenta);
                listaTranferencias.add(transferencia);
            }
            logger.log(Level.INFO, "Se consultaron {0} transferencias", listaTranferencias.size());
            return listaTranferencias;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar las transacciones", ex);
            throw new PersistenciaException("No se pudieron consultar las transacciones", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Transferencia> consultarTransCuentaPorFechas(String numCuenta, Timestamp fechaInicio, Timestamp fechaFin) throws PersistenciaException {
        String sentenciaSQL = """
                             SELECT *
                             FROM transferencias
                             INNER JOIN transacciones ON transferencias.idTransaccion = transacciones.idTransaccion
                             WHERE numCuenta=?
                             AND (DATE(transacciones.fecha) BETWEEN DATE(?) AND DATE(?))
                             ORDER BY transacciones.fecha DESC;
                             """;
        List<Transferencia> listaTranferencias = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setString(1, numCuenta);
            comando.setTimestamp(2, fechaInicio);
            comando.setTimestamp(3, fechaFin);
            ResultSet resultados = comando.executeQuery();
            while (resultados.next()) {
                int idTransferencia = resultados.getInt("idTransferencia");
                int idTransaccion = resultados.getInt("idTransaccion");
                String numCuentaDest = resultados.getString("numCuentaDestino");
                Timestamp fecha = resultados.getTimestamp("fecha");
                float cantidad = resultados.getFloat("cantidad");
                Transferencia transferencia = new Transferencia(idTransferencia,
                        numCuentaDest,
                        idTransaccion,
                        fecha,
                        cantidad,
                        numCuenta);
                listaTranferencias.add(transferencia);
            }
            logger.log(Level.INFO, "Se consultaron {0} transferencias", listaTranferencias.size());
            return listaTranferencias;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar las transacciones", ex);
            throw new PersistenciaException("No se pudieron consultar las transacciones", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transferencia realizarTransferencia(String cuentaOrigen, String cuentaDestino, float cantidad) throws PersistenciaException {
        String sentenciaSQL = "{call realizar_transferencia(?, ?, ?, ?, ?)}";
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); CallableStatement callableStatement = conexion.prepareCall(sentenciaSQL);) {
            callableStatement.setString(1, cuentaOrigen);
            callableStatement.setString(2, cuentaDestino);
            callableStatement.setFloat(3, cantidad);
            callableStatement.registerOutParameter(4, Types.BOOLEAN);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.execute();

            boolean resultado = callableStatement.getBoolean(4);
            int idTransferencia = callableStatement.getInt(5);

            if (resultado) {
                return this.obtener(idTransferencia);
            } else {
                throw new PersistenciaException("No se pudo realizar la transferencia");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudo procesar la transferencia", ex);
            throw new PersistenciaException("No se pudo procesar la transferencia", ex);
        }
    }
}
