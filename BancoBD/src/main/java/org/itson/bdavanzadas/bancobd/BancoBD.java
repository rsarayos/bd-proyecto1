
package org.itson.bdavanzadas.bancobd;

import org.itson.bdavanzadas.bancobdpersistencia.conexion.Conexion;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancobdpersistencia.daos.ClientesDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.DireccionDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.IClientesDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.IDireccionDAO;

/**
 *
 * @author alex_
 */
public class BancoBD {

    public static void main(String[] args) {
        String cadenaConexion = "jdbc:mysql://localhost/bancobd";
        String usuario = "root";
        String contrasenia = "vh.15.en.06";
        IConexion conexion= new Conexion(cadenaConexion, usuario, contrasenia);
        IClientesDAO clientesDAO = new ClientesDAO(conexion);
        IDireccionDAO direccionDAO = new DireccionDAO(conexion);
        frmMenuInicio menuInicio = new frmMenuInicio(clientesDAO, direccionDAO);
        menuInicio.setVisible(true);
    }
}
