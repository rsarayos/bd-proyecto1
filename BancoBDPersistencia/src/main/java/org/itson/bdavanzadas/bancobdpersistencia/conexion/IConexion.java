/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * La interfaz IConexion proporciona un método para obtener una conexión a una base de datos.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public interface IConexion {
    Connection obtenerConexion() throws SQLException;
}
