
package org.itson.bdavanzadas.bancobdpersistencia.daos;

/**
 * Clase que encapsula instancias de interfaces DAO para proporcionar
 * acceso a la capa de persistencia de datos relacionados con clientes,
 * direcciones, cuentas, transacciones, transferencias y retiros.
 *
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class DatosConexion {
    
    // instancia de las distintas interfaces para el manejo de persistencia
    private IClientesDAO clientesDAO;
    private IDireccionDAO direccionDAO;
    private ICuentaDAO cuentaDAO;
    private ITransaccionDAO transaccionDAO;
    private ITransferenciaDAO transferenciaDAO;
    private IRetiroDAO retiroDAO;

    /**
     * Constructor que recibe instancias de las interfaces DAO como parámetros
     * para inicializar las variables miembro de la clase.
     *
     * @param clientesDAO     Instancia de IClientesDAO.
     * @param direccionDAO    Instancia de IDireccionDAO.
     * @param cuentaDAO       Instancia de ICuentaDAO.
     * @param transaccionDAO  Instancia de ITransaccionDAO.
     * @param transferenciaDAO Instancia de ITransferenciaDAO.
     * @param retiroDAO       Instancia de IRetiroDAO.
     */
    public DatosConexion(IClientesDAO clientesDAO, IDireccionDAO direccionDAO, ICuentaDAO cuentaDAO, ITransaccionDAO transaccionDAO, ITransferenciaDAO transferenciaDAO, IRetiroDAO retiroDAO) {
        this.clientesDAO = clientesDAO;
        this.direccionDAO = direccionDAO;
        this.cuentaDAO = cuentaDAO;
        this.transaccionDAO = transaccionDAO;
        this.transferenciaDAO = transferenciaDAO;
        this.retiroDAO = retiroDAO;
    }

    /**
     * Obtiene la instancia de IClientesDAO.
     *
     * @return Instancia de IClientesDAO.
     */
    public IClientesDAO getClientesDAO() {
        return clientesDAO;
    }

    /**
     * Obtiene la instancia de IDireccionDAO.
     *
     * @return Instancia de IDireccionDAO.
     */
    public IDireccionDAO getDireccionDAO() {
        return direccionDAO;
    }

    /**
     * Obtiene la instancia de ICuentaDAO.
     *
     * @return Instancia de ICuentaDAO.
     */
    public ICuentaDAO getCuentaDAO() {
        return cuentaDAO;
    }

    /**
     * Obtiene la instancia de ITransaccionDAO.
     *
     * @return Instancia de ITransaccionDAO.
     */
    public ITransaccionDAO getTransaccionDAO() {
        return transaccionDAO;
    }

    /**
     * Obtiene la instancia de ITransferenciaDAO.
     *
     * @return Instancia de ITransferenciaDAO.
     */
    public ITransferenciaDAO getTransferenciaDAO() {
        return transferenciaDAO;
    }

    /**
     * Obtiene la instancia de IRetiroDAO.
     *
     * @return Instancia de IRetiroDAO.
     */
    public IRetiroDAO getRetiroDAO() {
        return retiroDAO;
    }
    
    
}
