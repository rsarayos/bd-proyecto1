
package org.itson.bdavanzadas.bancobdpersistencia.dtos;

import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 *
 * @author alex_
 */
public class TransferenciaNuevaDTO extends TransaccionNuevaDTO{
    private int idTransferencia;
    private String cuentaDestino;

    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
    
    @Override
    public boolean esValido() throws ValidacionDTOException {
        return true;
    }
    
}
