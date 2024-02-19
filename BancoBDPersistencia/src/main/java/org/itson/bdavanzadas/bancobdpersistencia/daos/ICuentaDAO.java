/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.bancobdpersistencia.daos;

import java.util.List;
import org.itson.bdavanzadas.bancobddominio.Cuenta;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.CuentaNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * La interfaz ICuentaDAO proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * relacionadas con la entidad Cuenta en la capa de acceso a datos.
 *
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public interface ICuentaDAO {
    
    /**
     * Agrega una nueva cuenta a la base de datos.
     *
     * @param cuentaNueva Objeto CuentaNuevaDTO que contiene los datos de la nueva cuenta.
     * @return Objeto Cuenta que representa la cuenta agregada.
     * @throws PersistenciaException Si ocurre un error al intentar agregar la cuenta.
     */
    Cuenta agregar(CuentaNuevaDTO cuentaNueva) throws PersistenciaException;
    
    /**
     * Actualiza la información de una cuenta existente en la base de datos.
     *
     * @param cuentaNueva Objeto CuentaNuevaDTO que contiene los nuevos datos de la cuenta.
     * @return Objeto Cuenta que representa la cuenta actualizada.
     * @throws PersistenciaException Si ocurre un error al intentar actualizar la cuenta.
     */
    Cuenta actualizar(CuentaNuevaDTO cuentaNueva) throws PersistenciaException;
    
    /**
     * Obtiene una cuenta de la base de datos utilizando su número de cuenta.
     *
     * @param numCuenta Número de cuenta de la cuenta a obtener.
     * @return Objeto Cuenta que representa la cuenta obtenida.
     * @throws PersistenciaException Si ocurre un error al intentar obtener la cuenta.
     */
    Cuenta obtener(String numCuenta) throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de todas las cuentas en la base de datos.
     *
     * @return Lista de objetos Cuenta que representa todas las cuentas.
     * @throws PersistenciaException Si ocurre un error al intentar consultar las cuentas.
     */
    List<Cuenta> consultar() throws PersistenciaException;
    
    /**
     * Consulta y devuelve una lista de cuentas asociadas a un cliente específico.
     *
     * @param telefonoTitular Número de teléfono del titular de las cuentas.
     * @return Lista de objetos Cuenta que representa las cuentas del cliente.
     * @throws PersistenciaException Si ocurre un error al intentar consultar las cuentas del cliente.
     */
    List<Cuenta> consultarCuentasCliente(String telefonoTitular) throws PersistenciaException;
    
}
