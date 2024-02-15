package org.itson.bdavanzadas.bancobd;

import org.itson.bdavanzadas.bancobdpersistencia.conexion.Conexion;
import org.itson.bdavanzadas.bancobdpersistencia.conexion.IConexion;
import org.itson.bdavanzadas.bancobdpersistencia.daos.ClientesDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.CuentaDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.DatosConexion;
import org.itson.bdavanzadas.bancobdpersistencia.daos.DireccionDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.IClientesDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.ICuentaDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.IDireccionDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.IRetiroDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.ITransaccionDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.ITransferenciaDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.RetiroDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.TransaccionDAO;
import org.itson.bdavanzadas.bancobdpersistencia.daos.TransferenciaDAO;

/**
 *
 * @author alex_
 */
public class BancoBD {

    public static void main(String[] args) {
        String cadenaConexion = "jdbc:mysql://localhost/bancobd";
        String usuario = "root";
        String contrasenia = "vh.15.en.06";
        IConexion conexion = new Conexion(cadenaConexion, usuario, contrasenia);

        IClientesDAO clientesDAO = new ClientesDAO(conexion);
        IDireccionDAO direccionDAO = new DireccionDAO(conexion);
        ICuentaDAO cuentaDAO = new CuentaDAO(conexion);
        ITransaccionDAO transaccionDAO = new TransaccionDAO(conexion);
        ITransferenciaDAO transferenciaDAO = new TransferenciaDAO(conexion);
        IRetiroDAO retiroDAO = new RetiroDAO(conexion);
        
        DatosConexion datosConexion = new DatosConexion(clientesDAO,direccionDAO,cuentaDAO, transaccionDAO, transferenciaDAO, retiroDAO);

        frmMenuInicio menuInicio = new frmMenuInicio(datosConexion);
        menuInicio.setVisible(true);
    }
}
