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
                             INSERT INTO transacciones (fecha, cantidad, numCuenta)
                                         VALUES(?,?,?);
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(
                setenciaSQL,
                Statement.RETURN_GENERATED_KEYS);) {
            comando.setDate(1, (Date) transaccionNueva.getFecha());
            comando.setLong(2, transaccionNueva.getCantidad());
            comando.setString(3, transaccionNueva.getNumCuenta());
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agrearon {0} transaccion", numeroRegistrosInsertados);
            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            return new Transaccion(idsGenerados.getInt(1),
                    transaccionNueva.getFecha(),
                    transaccionNueva.getCantidad(),
                    transaccionNueva.getNumCuenta());
        } catch (SQLException ex) {
            logger.log(Level.INFO, "No se ha podido agregar la transaccion", ex);
            return null;
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
                Date fecha = resultados.getDate("fecha");
                long cantidad = resultados.getInt("cantidad");
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
