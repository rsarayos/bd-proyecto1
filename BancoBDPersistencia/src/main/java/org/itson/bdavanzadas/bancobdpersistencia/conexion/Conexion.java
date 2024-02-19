/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase Conexion implementa la interfaz IConexion y proporciona métodos para establecer
 * y obtener conexiones a una base de datos utilizando JDBC.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class Conexion implements IConexion {
    // Cadena de conexión a la base de datos.
    final String cadenaConexion;
    // Usuario para la conexión a la base de datos.
    final String usuario;
    // Contraseña para la conexión a la base de datos.
    final String contrasenia;
    // Objeto Logger para el registro de eventos de la clase.
    final static Logger logger = Logger.getLogger(Conexion.class.getName());

    /**
     * Constructor que inicializa la clase con la cadena de conexión, usuario y contraseña.
     *
     * @param cadenaConexion Cadena de conexión a la base de datos.
     * @param usuario        Usuario para la conexión a la base de datos.
     * @param contrasenia    Contraseña para la conexión a la base de datos.
     */
    public Conexion(String cadenaConexion, String usuario, String contrasenia) {
        this.cadenaConexion = cadenaConexion;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    /**
     * Implementación del método obtenerConexion de la interfaz IConexion.
     * Establece y devuelve una conexión a la base de datos utilizando JDBC.
     *
     * @return Objeto de tipo Connection que representa la conexión a la base de datos.
     * @throws SQLException Si ocurre un error al intentar establecer la conexión.
     */
    @Override
    public Connection obtenerConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(
                cadenaConexion,
                usuario,
                contrasenia
        );
        logger.log(Level.INFO, "Conexion establecida {0}", cadenaConexion);
        return conexion;
    }
    
}
