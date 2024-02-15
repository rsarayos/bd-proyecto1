
package org.itson.bdavanzadas.bancobdpersistencia.daos;

/**
 *
 * @author victo
 */
public class DatosConexion {
    private IClientesDAO clientesDAO;
    private IDireccionDAO direccionDAO;
    private ICuentaDAO cuentaDAO;
    private ITransaccionDAO transaccionDAO;
    private ITransferenciaDAO transferenciaDAO;
    private IRetiroDAO retiroDAO;

    public DatosConexion(IClientesDAO clientesDAO, IDireccionDAO direccionDAO, ICuentaDAO cuentaDAO, ITransaccionDAO transaccionDAO, ITransferenciaDAO transferenciaDAO, IRetiroDAO retiroDAO) {
        this.clientesDAO = clientesDAO;
        this.direccionDAO = direccionDAO;
        this.cuentaDAO = cuentaDAO;
        this.transaccionDAO = transaccionDAO;
        this.transferenciaDAO = transferenciaDAO;
        this.retiroDAO = retiroDAO;
    }

    public IClientesDAO getClientesDAO() {
        return clientesDAO;
    }

    public IDireccionDAO getDireccionDAO() {
        return direccionDAO;
    }

    public ICuentaDAO getCuentaDAO() {
        return cuentaDAO;
    }

    public ITransaccionDAO getTransaccionDAO() {
        return transaccionDAO;
    }

    public ITransferenciaDAO getTransferenciaDAO() {
        return transferenciaDAO;
    }

    public IRetiroDAO getRetiroDAO() {
        return retiroDAO;
    }
    
    
}
