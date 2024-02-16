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
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancobddominio.Transaccion;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.TransaccionNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author alex_
 */
public class TransaccionDAO implements ITransaccionDAO{

    final IConexion conexionDB;
    static final Logger logger = Logger.getLogger(TransferenciaDAO.class.getName());

    public TransaccionDAO(IConexion conexionDB) {
        this.conexionDB = conexionDB;
    }
    
    @Override
    public Transaccion nueva(TransaccionNuevaDTO transaccionNueva) throws PersistenciaException {
        String setenciaSQL = """
                             INSERT INTO transacciones (cantidad, numCuenta)
                                         VALUES(?,?);
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(
                setenciaSQL,
                Statement.RETURN_GENERATED_KEYS);) {
            comando.setFloat(1, transaccionNueva.getCantidad());
            comando.setString(2, transaccionNueva.getNumCuenta());
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agrearon {0} transaccion", numeroRegistrosInsertados);
            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            Transaccion transaccion = new Transaccion(idsGenerados.getInt(1));
            return obtener(transaccion.getIdTransaccion());
        } catch (SQLException ex) {
            logger.log(Level.INFO, "No se ha podido agregar la transaccion", ex);
            return null;
        }
    }
    
    @Override
    public Transaccion obtener(int idTransaccion) throws PersistenciaException {
        String setenciaSQL = """
                             SELECT idTransaccion, fecha, cantidad, numCuenta
                             FROM transacciones
                             WHERE idTransaccion=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion();
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL);
        ) {
            comando.setInt(1, idTransaccion);

            ResultSet resultados = comando.executeQuery();

            if (resultados.next()) {
                return new Transaccion(
                        resultados.getInt("idTransaccion"),
                        resultados.getTimestamp("fecha"),
                        resultados.getFloat("cantidad"),
                        resultados.getString("numCuenta")
                );
            } else {
                return null; // No se encontró el socio con el telefono dado
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la transaccion", ex);
            throw new PersistenciaException("No se ha podido obtener la transaccion", ex);
        }
    }
    
    @Override
    public List<Transaccion> consultar() throws PersistenciaException {
        String setenciaSQL = """
                             SELECT idTransaccion, fecha, cantidad, numCuenta
                             FROM transacciones;
                             """;
        List<Transaccion> listaTransacciones = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); 
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL); 
                ResultSet resultados = comando.executeQuery();) {
            
            while (resultados.next()) {                
                int idTransaccion = resultados.getInt("idTransaccion");
                Timestamp fecha = resultados.getTimestamp("fecha");
                float cantidad = resultados.getFloat("cantidad");
                String numCuenta = resultados.getString("numCuenta");
                Transaccion transaccion = new Transaccion(idTransaccion, 
                        fecha, 
                        cantidad,
                        numCuenta);
                listaTransacciones.add(transaccion);
            }
            logger.log(Level.INFO, "Se consultaron {0} transacciones", listaTransacciones.size());
            return listaTransacciones;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar las transacciones", ex);
            throw new PersistenciaException("No se pudieron consultar las transacciones", ex);
        }
    }
  
}
