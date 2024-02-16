/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancobddominio.Cliente;
import org.itson.bdavanzadas.bancobddominio.Cuenta;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import static org.itson.bdavanzadas.bancobdpersistencia.daos.ClientesDAO.logger;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.CuentaNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author alex_
 */
public class CuentaDAO implements ICuentaDAO{
    
    final IConexion conexionDB;
    static final Logger logger = Logger.getLogger(ClientesDAO.class.getName());

    public CuentaDAO(IConexion conexionDB) {
        this.conexionDB = conexionDB;
    }
    
    @Override
    public Cuenta agregar(CuentaNuevaDTO cuentaNueva) throws PersistenciaException {
        String setenciaSQL = """
                             INSERT INTO cuentas (numCuenta, saldo, estado, telefonoTitular)
                                         VALUES(?,?,?,?);
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(
                setenciaSQL,
                Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, cuentaNueva.getNumCuenta());
            comando.setFloat(2, cuentaNueva.getSaldo());
            comando.setBoolean(3, cuentaNueva.isEstado());
            comando.setString(4, cuentaNueva.getTelefonoTitular());
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agrearon {0} cuentas", numeroRegistrosInsertados);
            return obtener(cuentaNueva.getNumCuenta());
        } catch (SQLException ex) {
            logger.log(Level.INFO, "No se ha podido agregar la cuenta", ex);
            return null;
        }
    }

    @Override
    public Cuenta actualizar(CuentaNuevaDTO cuentaNueva) throws PersistenciaException {
        String setenciaSQL = """
                             UPDATE cuentas
                             SET estado=?
                             WHERE numCuenta=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(setenciaSQL);) {
            comando.setBoolean(1, cuentaNueva.isEstado());
            comando.setString(2, cuentaNueva.getNumCuenta());
            int numeroRegistrosActualizados = comando.executeUpdate();
            logger.log(Level.INFO, "Se actualizaron {0} registros", numeroRegistrosActualizados);
                Cuenta cuenta = new Cuenta(cuentaNueva.getNumCuenta(),
                    cuentaNueva.getFechaApertura(),
                    cuentaNueva.getSaldo(),
                    cuentaNueva.isEstado(),
                    cuentaNueva.getTelefonoTitular());
            return cuenta;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudo actualizar la cuenta", ex);
            return null;
        }
    }

    @Override
    public Cuenta obtener(String numCuenta) throws PersistenciaException {
        String setenciaSQL = """
                             SELECT numCuenta, fechaApertura, saldo, estado, telefonoTitular
                             FROM cuentas
                             WHERE numCuenta=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion();
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL);
        ) {
            comando.setString(1, numCuenta);

            ResultSet resultados = comando.executeQuery();

            if (resultados.next()) {
                return new Cuenta(
                        resultados.getString("numCuenta"),
                        resultados.getDate("fechaApertura"),
                        resultados.getFloat("saldo"),
                        resultados.getBoolean("estado"),
                        resultados.getString("telefonoTitular")
                );
            } else {
                return null; // No se encontró el socio con el telefono dado
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la cuenta", ex);
            throw new PersistenciaException("No se ha podido obtener la cuenta", ex);
        }
    }

    @Override
    public List<Cuenta> consultar() throws PersistenciaException {
        String setenciaSQL = """
                             SELECT numCuenta, fechaApertura, saldo, estado, telefonoTitular
                             FROM cuentas;
                             """;
        List<Cuenta> listaCuentas = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); 
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL); 
                ResultSet resultados = comando.executeQuery();) {
            
            while (resultados.next()) {                
                String numCuenta = resultados.getString("numCuenta");
                Date fechaApertura = resultados.getDate("fechaApertura");
                float saldo = resultados.getLong("saldo");
                boolean estado = resultados.getBoolean("estado");
                String telefonoTitular = resultados.getString("telefonoTitular");
                Cuenta cuenta = new Cuenta(numCuenta, 
                        fechaApertura, 
                        saldo, 
                        estado, 
                        telefonoTitular);
                listaCuentas.add(cuenta);
            }
            logger.log(Level.INFO, "Se consultaron {0} cuentas", listaCuentas.size());
            return listaCuentas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar las cuentas", ex);
            throw new PersistenciaException("No se pudieron consultar las cuentas", ex);
        }
    }

    @Override
    public List<Cuenta> consultarCuentasCliente(String telefonoTitular) throws PersistenciaException {
        String setenciaSQL = """
                             SELECT numCuenta, fechaApertura, saldo, estado, telefonoTitular
                             FROM cuentas
                             WHERE telefonoTitular=?;
                             """;
        List<Cuenta> listaCuentas = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); 
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL);
                ) {
            comando.setString(1, telefonoTitular);
            ResultSet resultados = comando.executeQuery();
            while (resultados.next()) {                
                String numCuenta = resultados.getString("numCuenta");
                Date fechaApertura = resultados.getDate("fechaApertura");
                float saldo = resultados.getLong("saldo");
                boolean estado = resultados.getBoolean("estado");
                Cuenta cuenta = new Cuenta(numCuenta, 
                        fechaApertura, 
                        saldo, 
                        estado, 
                        telefonoTitular);
                listaCuentas.add(cuenta);
            }
            logger.log(Level.INFO, "Se consultaron {0} cuentas", listaCuentas.size());
            return listaCuentas;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar las cuentas", ex);
            throw new PersistenciaException("No se pudieron consultar las cuentas", ex);
        }
    }
    
}
