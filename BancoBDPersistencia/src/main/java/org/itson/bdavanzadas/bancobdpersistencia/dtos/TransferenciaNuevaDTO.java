
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

/**
 * La clase TransferenciaNuevaDTO representa un objeto de transferencia de datos (DTO) para la creación de nuevas transferencias.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class TransferenciaNuevaDTO extends TransaccionNuevaDTO{
    
    // Atributos de la clase Direccion
    private int idTransferencia;
    private String cuentaDestino;
    
    // Métodos de acceso y modificación

    /**
     * Obtiene el número id de la transferencia.
     *
     * @return Número id de la transferencia.
     */
    public int getIdTransferencia() {
        return idTransferencia;
    }

    /**
     * Establece el id de la transferencia.
     *
     * @param idTransferencia Nuevo id de la transferencia.
     */
    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    /**
     * Obtiene el número de cuenta destino de la transferencia.
     *
     * @return Número de cuenta destino de la transferencia.
     */
    public String getCuentaDestino() {
        return cuentaDestino;
    }

    /**
     * Establece la cuenta destino de la transferencia.
     *
     * @param cuentaDestino Nuevo numero de cuenta destino de la transferencia.
     */
    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
    
}
