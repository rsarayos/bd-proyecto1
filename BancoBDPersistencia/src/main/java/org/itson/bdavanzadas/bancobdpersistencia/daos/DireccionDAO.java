
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancobddominio.Direccion;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.DireccionNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * Clase que implementa la interfaz IDireccionDAO y proporciona métodos para interactuar con la tabla de direcciones en la base de datos.
 * Esta clase realiza operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la tabla de direcciones.
 *
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class DireccionDAO implements IDireccionDAO{

    // Objeto IConexion utilizado para obtener conexiones a la base de datos.
    final IConexion conexionDB;
    // Objeto Logger utilizado para registrar mensajes de registro.
    static final Logger logger = Logger.getLogger(DireccionDAO.class.getName());

    /**
     * Constructor que recibe una conexión a la base de datos.
     *
     * @param conexionDB Conexión a la base de datos.
     */
    public DireccionDAO(IConexion conexionDB) {
        this.conexionDB = conexionDB;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Direccion agregar(DireccionNuevaDTO direccionNueva) throws PersistenciaException {
        String setenciaSQL = """
                             INSERT INTO direcciones (calle, colonia, numero, ciudad, cp)
                                         VALUES(?,?,?,?,?);
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(
                setenciaSQL,
                Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, direccionNueva.getCalle());
            comando.setString(2, direccionNueva.getColonia());
            comando.setString(3, direccionNueva.getNumero());
            comando.setString(4, direccionNueva.getCiudad());
            comando.setString(5, direccionNueva.getCp());
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agrego {0} direccion", numeroRegistrosInsertados);
            ResultSet idsGenerados = comando.getGeneratedKeys();
            idsGenerados.next();
            return new Direccion(idsGenerados.getInt(1),
                    direccionNueva.getCalle(),
                    direccionNueva.getColonia(),
                    direccionNueva.getNumero(),
                    direccionNueva.getCiudad(),
                    direccionNueva.getCp());
        } catch (SQLException ex) {
            logger.log(Level.INFO, "No se ha podido agregar la direccion", ex);
            return null;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Direccion obtener(int idDireccion) throws PersistenciaException {
        String setenciaSQL = """
                             SELECT idDireccion, calle, colonia, numero, ciudad, cp
                             FROM direcciones
                             WHERE idDireccion=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion();
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL);
        ) {
            comando.setInt(1, idDireccion);

            ResultSet resultados = comando.executeQuery();

            if (resultados.next()) {
                return new Direccion(
                        resultados.getInt("idDireccion"),
                        resultados.getString("calle"),
                        resultados.getString("colonia"),
                        resultados.getString("numero"),
                        resultados.getString("ciudad"),
                        resultados.getString("cp")
                );
            } else {
                return null; // No se encontró el socio con el telefono dado
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la direccion", ex);
            throw new PersistenciaException("No se ha podido obtener la direccion", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direccion actualizar(DireccionNuevaDTO direccionNueva) throws PersistenciaException {
        String setenciaSQL = """
                             UPDATE direcciones
                             SET calle=?, colonia=?, numero=?, ciudad=?, cp=?
                             WHERE idDireccion=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(setenciaSQL);) {
            comando.setString(1, direccionNueva.getCalle());
            comando.setString(2, direccionNueva.getColonia());
            comando.setString(3, direccionNueva.getNumero());
            comando.setString(4, direccionNueva.getCiudad());
            comando.setString(5, direccionNueva.getCp());
            comando.setInt(6, direccionNueva.getIdDireccion());
            int numeroRegistrosActualizados = comando.executeUpdate();
            logger.log(Level.INFO, "Se actualizaron {0} registros", numeroRegistrosActualizados);
                return new Direccion(direccionNueva.getIdDireccion(),
                    direccionNueva.getCalle(),
                    direccionNueva.getColonia(),
                    direccionNueva.getNumero(),
                    direccionNueva.getCiudad(),
                    direccionNueva.getCp());
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudo actualizar la direccion", ex);
            return null;
        }
    }
    
}
