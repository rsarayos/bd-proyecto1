/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebasPersistencia;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.Conexion;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancobdpersistencia.daos.ClientesDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.IClientesDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.TransferenciaDAO;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.ClienteNuevoDTO;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.TransferenciaNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author alex_
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String candenaConexion = "jdbc:mysql://localhost/bancobd";
        String usuario = "root";
        String password = "Base1234";
        IConexion conexion = new Conexion(candenaConexion, usuario, password);
         
            // PRUEBAS CLIENTEDAO
//        ClienteNuevoDTO cliente = new ClienteNuevoDTO();
//        cliente.setTelefono("6666666666");
//        cliente.setNombre("Juan");
//        cliente.setApellidoPaterno("Perez");
//        cliente.setApellidoMaterno("Rodriguez");
//        Date fecha = new Date(1995, 1, 20);
//        cliente.setFechaNacimiento(fecha);
//        cliente.setPassword("contra123");
//        cliente.setEdad(29);
//        cliente.setIdDireccion(1);
//        
//        IClientesDAO clienteDAO = new ClientesDAO(conexion);
//        try {
//            System.out.println(clienteDAO.consultar().size());
//        } catch (PersistenciaException ex) {
//            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    
}
