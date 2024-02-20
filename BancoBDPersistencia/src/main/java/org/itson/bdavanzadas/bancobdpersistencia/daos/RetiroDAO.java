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
import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.EncriptarContra;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.RetiroNuevoDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * Clase que implementa la interfaz IRetiroDAO y proporciona métodos para interactuar con la tabla de retiros en la base de datos.
 * Esta clase realiza operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la tabla de direcciones.
 *
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class RetiroDAO implements IRetiroDAO {

    // Objeto IConexion utilizado para obtener conexiones a la base de datos.
    final IConexion conexionDB;
    // Objeto Logger utilizado para registrar mensajes de registro.
    static final Logger logger = Logger.getLogger(TransferenciaDAO.class.getName());
    // Objeto EncriptarContra utilizado para cifrar y descifrar contraseñas.
    private EncriptarContra encriptador;

    /**
     * Constructor de la clase RetiroDAO que recibe un objeto IConexion.
     *
     * @param conexionDB Objeto IConexion utilizado para obtener conexiones a la base de datos.
     */
    public RetiroDAO(IConexion conexionDB) {
        this.conexionDB = conexionDB;
        this.encriptador = new EncriptarContra();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Retiro nuevo(RetiroNuevoDTO retiroNuevo) throws PersistenciaException {
        String sentenciaSQL = """
                             INSERT INTO retiros (folioRetiro, contraseniaRetiro, estado, idTransaccion)
                                         VALUES(?,?,?,?);
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(
                sentenciaSQL,
                Statement.RETURN_GENERATED_KEYS);) {
            
            String contraEncriptada = encriptador.encriptar(retiroNuevo.getContraseniaRetiro());
            
            comando.setString(1, retiroNuevo.getFolioRetiro());
            comando.setBytes(2, contraEncriptada.getBytes());
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
        } catch (Exception ex) {
            logger.log(Level.INFO, "No se ha podido agregar el retiro", ex);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Retiro actualizar(RetiroNuevoDTO retiroNuevo) throws PersistenciaException {
        String sentenciaSQL = """
                             UPDATE retiros
                             SET estado=?
                             WHERE idRetiro=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Retiro obtener(int idRetiro) throws PersistenciaException {
        String sentenciaSQL = """
                             SELECT *
                             FROM retiros
                             INNER JOIN transacciones ON retiros.idTransaccion = transacciones.idTransaccion
                             WHERE idRetiro=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setInt(1, idRetiro);

            ResultSet resultados = comando.executeQuery();

            if (resultados.next()) {
                String folioRetiro = resultados.getString("folioRetiro");
                String contraseniaCifrada = resultados.getString("contraseniaRetiro");
                String contraDesencriptada = encriptador.desencriptar(contraseniaCifrada);
                int estado = resultados.getInt("estado");
                int idTransaccion = resultados.getInt("idTransaccion");
                Timestamp fecha = resultados.getTimestamp("fecha");
                long cantidad = resultados.getInt("cantidad");
                String numCuenta = resultados.getString("numCuenta");
                Retiro retiro = new Retiro(idRetiro,
                        folioRetiro,
                        contraDesencriptada,
                        estado,
                        idTransaccion,
                        fecha,
                        cantidad,
                        numCuenta);
                return retiro;
            } else {
                return null; // No se encontró el socio con el telefono dado
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el retiro", ex);
            throw new PersistenciaException("No se ha podido obtener el retiro", ex);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el retiro", ex);
            throw new PersistenciaException("No se ha podido obtener el retiro", ex);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Retiro obtenerPorFolio(String folioRetiro) throws PersistenciaException {
        String sentenciaSQL = """
                             SELECT *
                             FROM retiros
                             INNER JOIN transacciones ON retiros.idTransaccion = transacciones.idTransaccion
                             WHERE folioRetiro=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setString(1, folioRetiro);

            ResultSet resultados = comando.executeQuery();

            if (resultados.next()) {
                int idRetiro = resultados.getInt("idRetiro");
                String contraseniaCifrada = resultados.getString("contraseniaRetiro");
                String contraDesencriptada = encriptador.desencriptar(contraseniaCifrada);
                int estado = resultados.getInt("estado");
                int idTransaccion = resultados.getInt("idTransaccion");
                Timestamp fecha = resultados.getTimestamp("fecha");
                long cantidad = resultados.getInt("cantidad");
                String numCuenta = resultados.getString("numCuenta");
                Retiro retiro = new Retiro(idRetiro,
                        folioRetiro,
                        contraDesencriptada,
                        estado,
                        idTransaccion,
                        fecha,
                        cantidad,
                        numCuenta);
                return retiro;
            } else {
                return null; // No se encontró el socio con el telefono dado
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el retiro", ex);
            throw new PersistenciaException("No se ha podido obtener el retiro", ex);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el retiro", ex);
            throw new PersistenciaException("No se ha podido obtener el retiro", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Retiro> consultar() throws PersistenciaException {
        String sentenciaSQL = """
                             SELECT *
                             FROM retiros
                             INNER JOIN transacciones ON retiros.idTransaccion = transacciones.idTransaccion;
                             """;
        List<Retiro> listaRetiros = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL); ResultSet resultados = comando.executeQuery();) {

            while (resultados.next()) {
                int idRetiro = resultados.getInt("idRetiro");
                String contraseniaCifrada = resultados.getString("contraseniaRetiro");
                String contraDesencriptada = encriptador.desencriptar(contraseniaCifrada);
                String folioRetiro = resultados.getString("folioRetiro");
                int estado = resultados.getInt("estado");
                int idTransaccion = resultados.getInt("idTransaccion");
                Timestamp fecha = resultados.getTimestamp("fecha");
                long cantidad = resultados.getInt("cantidad");
                String numCuenta = resultados.getString("numCuenta");
                Retiro retiro = new Retiro(idRetiro,
                        folioRetiro,
                        contraDesencriptada,
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
        } catch (Exception ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar los retiros", ex);
            throw new PersistenciaException("No se pudieron consultar los retiros", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Retiro> consultarRetiroCuenta(String numCuenta) throws PersistenciaException {
        String sentenciaSQL = """
                             SELECT *
                             FROM retiros
                             INNER JOIN transacciones ON retiros.idTransaccion = transacciones.idTransaccion
                             WHERE numCuenta=?
                             ORDER BY transacciones.fecha DESC;
                             """;
        List<Retiro> listaRetiros = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setString(1, numCuenta);
            ResultSet resultados = comando.executeQuery();
            while (resultados.next()) {
                int idRetiro = resultados.getInt("idRetiro");
                String folioRetiro = resultados.getString("folioRetiro");
                String contraseniaCifrada = resultados.getString("contraseniaRetiro");
                String contraDesencriptada = encriptador.desencriptar(contraseniaCifrada);
                int estado = resultados.getInt("estado");
                int idTransaccion = resultados.getInt("idTransaccion");
                Timestamp fecha = resultados.getTimestamp("fecha");
                long cantidad = resultados.getInt("cantidad");
                Retiro retiro = new Retiro(idRetiro,
                        folioRetiro,
                        contraDesencriptada,
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
        } catch (Exception ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar los retiros", ex);
            throw new PersistenciaException("No se pudieron consultar los retiros", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Retiro> consultarRetiroCuentaPorFechas(String numCuenta, Timestamp fechaInicio, Timestamp fechaFin) throws PersistenciaException {
        String sentenciaSQL = """
                             SELECT *
                             FROM retiros
                             INNER JOIN transacciones ON retiros.idTransaccion = transacciones.idTransaccion
                             WHERE numCuenta=?
                             AND (DATE(transacciones.fecha) BETWEEN DATE(?) AND DATE(?))
                             ORDER BY transacciones.fecha DESC;
                             """;
        List<Retiro> listaRetiros = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setString(1, numCuenta);
            comando.setTimestamp(2, fechaInicio);
            comando.setTimestamp(3, fechaFin);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Retiro realizarRetiro(int idRetiro) throws PersistenciaException {
        String sentenciaSQL = "{call realizar_retiro(?, ?)}";
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); CallableStatement callableStatement = conexion.prepareCall(sentenciaSQL);) {
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
