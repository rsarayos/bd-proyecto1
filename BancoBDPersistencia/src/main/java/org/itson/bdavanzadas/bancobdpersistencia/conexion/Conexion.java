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
 *
 * @author alex_
 */
public class Conexion implements IConexion {
    
    final String cadenaConexion;
    final String usuario;
    final String contrasenia;
    final static Logger logger = Logger.getLogger(Conexion.class.getName());

    public Conexion(String cadenaConexion, String usuario, String contrasenia) {
        this.cadenaConexion = cadenaConexion;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

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
