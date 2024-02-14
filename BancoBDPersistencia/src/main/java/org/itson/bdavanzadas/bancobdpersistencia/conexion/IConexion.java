/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author alex_
 */
public interface IConexion {
    Connection obtenerConexion() throws SQLException;
}
