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
import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.EncriptarContra;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.ClienteNuevoDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * La clase ClientesDAO implementa la interfaz IClientesDAO y proporciona métodos para realizar operaciones CRUD
 * (Crear, Leer, Actualizar, Eliminar) relacionadas con la entidad Cliente en la base de datos.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class ClientesDAO implements IClientesDAO {

    // Objeto IConexion utilizado para obtener conexiones a la base de datos.
    final IConexion conexionDB;
    // Objeto Logger utilizado para registrar mensajes de registro.
    static final Logger logger = Logger.getLogger(ClientesDAO.class.getName());
    // Objeto EncriptarContra utilizado para cifrar y descifrar contraseñas.
    private EncriptarContra encriptador;

    /**
     * Constructor de la clase ClientesDAO que recibe un objeto IConexion.
     *
     * @param conexionDB Objeto IConexion utilizado para obtener conexiones a la base de datos.
     */
    public ClientesDAO(IConexion conexionDB) {
        this.conexionDB = conexionDB;
        this.encriptador = new EncriptarContra();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cliente agregar(ClienteNuevoDTO clienteNuevo) throws PersistenciaException {
        String setenciaSQL = """
                             INSERT INTO clientes (telefono, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, contrasenia, idDireccion)
                                         VALUES(?,?,?,?,?,?,?);
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); 
                PreparedStatement comando = conexion.prepareStatement(
                setenciaSQL,
                Statement.RETURN_GENERATED_KEYS);) {
            
            String contraEncriptada = encriptador.encriptar(clienteNuevo.getPassword());
            
            comando.setString(1, clienteNuevo.getTelefono());
            comando.setString(2, clienteNuevo.getNombre());
            comando.setString(3, clienteNuevo.getApellidoPaterno());
            comando.setString(4, clienteNuevo.getApellidoMaterno());
            comando.setDate(5, (Date) clienteNuevo.getFechaNacimiento());
            comando.setBytes(6, contraEncriptada.getBytes());
            comando.setInt(7, clienteNuevo.getIdDireccion());
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agrearon {0} clientes", numeroRegistrosInsertados);
            return obtener(clienteNuevo.getTelefono());
        } catch (SQLException ex) {
            logger.log(Level.INFO, "No se ha podido agregar al cliente", ex);
            return null;
        } catch (Exception ex) {
            logger.log(Level.INFO, "No se ha podido agregar al cliente", ex);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cliente actualizar(ClienteNuevoDTO clienteNuevo, String telefono) throws PersistenciaException {
        String setenciaSQL = """
                             UPDATE clientes
                             SET telefono=?, nombre=?, apellidoPaterno=?, apellidoMaterno=?, fechaNacimiento=?, contrasenia=?, idDireccion=?
                             WHERE telefono=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); 
                PreparedStatement comando = conexion.prepareStatement(setenciaSQL);) {
            
            String contraEncriptada = encriptador.encriptar(clienteNuevo.getPassword());
            
            comando.setString(1, clienteNuevo.getTelefono());
            comando.setString(2, clienteNuevo.getNombre());
            comando.setString(3, clienteNuevo.getApellidoPaterno());
            comando.setString(4, clienteNuevo.getApellidoMaterno());
            comando.setDate(5, (Date) clienteNuevo.getFechaNacimiento());
            comando.setBytes(6, contraEncriptada.getBytes());
            comando.setInt(7, clienteNuevo.getIdDireccion());
            comando.setString(8, telefono);
            int numeroRegistrosActualizados = comando.executeUpdate();
            logger.log(Level.INFO, "Se actualizaron {0} registros", numeroRegistrosActualizados);
            return obtener(clienteNuevo.getTelefono());
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudo actualizar al socio", ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudo actualizar al socio", ex);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cliente obtener(String telefono) throws PersistenciaException {
        String setenciaSQL = """
                             SELECT telefono, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, edad, contrasenia, idDireccion
                             FROM clientes
                             WHERE telefono=?;
                             """;

        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(setenciaSQL);) {
            comando.setString(1, telefono);

            ResultSet resultados = comando.executeQuery();

            if (resultados.next()) {
                String telefonoCliente = resultados.getString("telefono");
                String nombre = resultados.getString("nombre");
                String apellidoPaterno = resultados.getString("apellidoPaterno");
                String apellidoMaterno = resultados.getString("apellidoMaterno");
                Date fechaNacimiento = resultados.getDate("fechaNacimiento");
                int edad = resultados.getInt("edad");
                String contraseniaCifrada = resultados.getString("contrasenia");
                String contraseniaDesencriptada = encriptador.desencriptar(contraseniaCifrada);
                int idDireccion = resultados.getInt("idDireccion");
                
                return new Cliente(
                        telefonoCliente,
                        nombre,
                        apellidoPaterno,
                        apellidoMaterno,
                        fechaNacimiento,
                        edad,
                        contraseniaDesencriptada,
                        idDireccion
                );
            } else {
                return null;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el socio por telefono", ex);
            throw new PersistenciaException("No se ha podido obtener el socio por telefono", ex);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el socio por telefono", ex);
            throw new PersistenciaException("No se ha podido obtener el socio por telefono", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cliente> consultar() throws PersistenciaException {
        String setenciaSQL = """
                             SELECT telefono, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, edad, contrasenia, idDireccion
                             FROM clientes;
                             """;
        List<Cliente> listaClientes = new LinkedList<>();
        try (
                Connection conexion = this.conexionDB.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(setenciaSQL); ResultSet resultados = comando.executeQuery();) {

            while (resultados.next()) {
                String telefono = resultados.getString("telefono");
                String nombre = resultados.getString("nombre");
                String apellidoPa = resultados.getString("apellidoPaterno");
                String apellidoMa = resultados.getString("apellidoMaterno");
                Date fechaNacimiento = resultados.getDate("fechaNacimiento");
                int edad = resultados.getInt("edad");
                String contraseniaCifrada = resultados.getString("contrasenia");
                String contraseniaDesencriptada = encriptador.desencriptar(contraseniaCifrada);
                int idDireccion = resultados.getInt("idDireccion");
                Cliente cliente = new Cliente(telefono,
                        nombre,
                        apellidoPa,
                        apellidoMa,
                        fechaNacimiento,
                        edad,
                        contraseniaDesencriptada,
                        idDireccion);
                listaClientes.add(cliente);
            }
            logger.log(Level.INFO, "Se consultaron {0} socios", listaClientes.size());
            return listaClientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar los socios", ex);
            throw new PersistenciaException("No se pudieron consultar los socios", ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, "No se pudieron consultar los socios", ex);
            throw new PersistenciaException("No se pudieron consultar los socios", ex);
        }
    }

}
