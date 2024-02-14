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
//        String setenciaSQL1 = """
//                              INSERT INTO transacciones (fecha, cantidad, numCuenta)
//                                         VALUES(?,?,?);
//                              """;
//        String setenciaSQL2 = """
//                              INSERT INTO transferencia (idTransaccion, numCuentaDestino)
//                                         VALUES(?,?);
//                              """;
//        try (
//                Connection conexion = this.conexionDB.obtenerConexion(); 
//                PreparedStatement comando1 = conexion.prepareStatement(
//                setenciaSQL1,
//                Statement.RETURN_GENERATED_KEYS);
//                PreparedStatement comando2 = conexion.prepareStatement(
//                setenciaSQL2,
//                Statement.RETURN_GENERATED_KEYS)
//                ) {
//            comando1.setDate(1, (Date) transferenciaNueva.getFecha());
//            comando1.setString(2, direccionNueva.getColonia());
//            comando1.setString(3, direccionNueva.getNumero());
//            comando2.setString(1, direccionNueva.getCiudad());
//            comando2.setString(2, direccionNueva.getCp());
//            int numeroRegistrosInsertados = comando.executeUpdate();
//            logger.log(Level.INFO, "Se agrego {0} direccion", numeroRegistrosInsertados);
//            ResultSet idsGenerados = comando.getGeneratedKeys();
//            idsGenerados.next();
//            return new Direccion(idsGenerados.getInt(1),
//                    direccionNueva.getCalle(),
//                    direccionNueva.getColonia(),
//                    direccionNueva.getNumero(),
//                    direccionNueva.getCiudad(),
//                    direccionNueva.getCp());
//        } catch (SQLException ex) {
//            logger.log(Level.INFO, "No se ha podido agregar la direccion", ex);
//            return null;
//        }
        return null;
    }

    @Override
    public Transferencia obtener(int idTransferencia) throws PersistenciaException {
        return null;
    }

    @Override
    public List<Transferencia> consultar() throws PersistenciaException {
        return null;
    }
    
}
