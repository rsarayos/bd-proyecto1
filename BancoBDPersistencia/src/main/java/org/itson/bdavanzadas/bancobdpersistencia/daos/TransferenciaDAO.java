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
import org.itson.bdavanzadas.bancobddominio.Transferencia;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.TransferenciaNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author alex_
 */
public class TransferenciaDAO implements ITransferenciaDAO{

    final IConexion conexionDB;
    static final Logger logger = Logger.getLogger(TransferenciaDAO.class.getName());

    public TransferenciaDAO(IConexion conexionDB) {
        this.conexionDB = conexionDB;
    }
    
    @Override
    public Transferencia nueva(TransferenciaNuevaDTO transferenciaNueva) throws PersistenciaException {
        String setenciaSQL = """
                             INSERT INTO transacciones (idTransaccion, numCuentaDestino)
                                         VALUES(?,?);
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(
                setenciaSQL,
                Statement.RETURN_GENERATED_KEYS);) {
            comando.setInt(1, transferenciaNueva.getIdTransaccion());
            comando.setString(2, transferenciaNueva.getCuentaDestino());
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agrearon {0} transferencia", numeroRegistrosInsertados);
            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            return new Transferencia(idsGenerados.getInt(1),
                    transferenciaNueva.getCuentaDestino(),
                    transferenciaNueva.getIdTransaccion(),
                    transferenciaNueva.getFecha(), 
                    transferenciaNueva.getCantidad(), 
                    transferenciaNueva.getNumCuenta());
        } catch (SQLException ex) {
            logger.log(Level.INFO, "No se ha podido agregar la transferencia", ex);
            return null;
        }
    }

    @Override
    public List<Transferencia> consultar() throws PersistenciaException {
        String setenciaSQL = """
                             SELECT *
                             FROM transferencias
                             INNER JOIN transacciones ON transferencias.idTransaccion = transacciones.idTransaccion;
                             """;
        List<Transferencia> listaTranferencias = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); 
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL); 
                ResultSet resultados = comando.executeQuery();) {
            
            while (resultados.next()) {                
                int idTransferencia = resultados.getInt("idTransferencia");
                int idTransaccion = resultados.getInt("idTransaccion");
                String numCuentaDest = resultados.getString("numCuentaDestino");
                Date fecha = resultados.getDate("fecha");
                long cantidad = resultados.getInt("cantidad");
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
    
}
