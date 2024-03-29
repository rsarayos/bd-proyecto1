package org.itson.bdavanzadas.bancobd;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.bancobddominio.Cliente;
import org.itson.bdavanzadas.bancobddominio.Cuenta;
import org.itson.bdavanzadas.bancobddominio.Transferencia;
import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.Validaciones;
import org.itson.bdavanzadas.bancobdpersistencia.daos.DatosConexion;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * Representa el dialog Transferencia que permite realizar una transferencia
 * entre las cuentas que pertenezcan a un cliente a una cuenta que exista dentro
 * del banco y el monto a transferir.
 *
 * La clase proporciona un constructor para instanciar el dialog, además
 * contiene métodos oyentes para cada botón presente en el menú.
 *
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class dlgTransferencia extends javax.swing.JDialog {

    private final DatosConexion datosConexion;
    private Cliente cliente;
    private Validaciones validar;

    /**
     * Método constructor que permite inicializar el diálogo para realizar una
     * transferencia entre cuentas.
     *
     * @param parent El frame padre del diálogo.
     * @param modal Indica si el diálogo es modal o no.
     * @param datosConexion El objeto de conexión a la base de datos.
     * @param cliente El cliente que realiza la transferencia.
     */
    public dlgTransferencia(java.awt.Frame parent, boolean modal, DatosConexion datosConexion, Cliente cliente) {
        super(parent, modal);
        initComponents();
        this.datosConexion = datosConexion;
        this.cliente = cliente;
        this.validar = new Validaciones();
        mostrarCuentas();
    }

    /**
     * Método que permite mostrar las cuentas disponibles para realizar la
     * transferencia dentro de un comboBox.
     */
    private void mostrarCuentas() {
        List<Cuenta> listaCuentasRetiro = null;
        try {
            listaCuentasRetiro = datosConexion.getCuentaDAO().consultarCuentasCliente(cliente.getTelefono());
            for (Cuenta cuenta : listaCuentasRetiro) {
                if (cuenta.isEstado() == true) {
                    cbxCuentaRetiro.addItem(cuenta.getNumCuenta());
                }
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(dlgTransferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que permite realizar la transferencia entre dos cuentas.
     */
    private void transferencia() {

        List<Cuenta> listaCuentas = null;
        try {
            listaCuentas = datosConexion.getCuentaDAO().consultar();
        } catch (PersistenciaException ex) {
            Logger.getLogger(dlgAgregarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        String cuentaDestino = "";
        boolean cuentaExistente = false;
        for (Cuenta cuenta : listaCuentas) {
            if (validar.validaCuenta(txtCuentaDestino.getText())) {
                if (cuenta.getNumCuenta().equals(txtCuentaDestino.getText()) && cuenta.isEstado()) {
                    cuentaDestino = txtCuentaDestino.getText();
                    cuentaExistente = true;
                }
            }
        }

        String cuentaRetiro = String.valueOf(cbxCuentaRetiro.getSelectedItem());
        if (cuentaExistente) {
            if (validar.validaCantidad(txtMonto.getText())) {
                float monto = Float.parseFloat(txtMonto.getText());
                if (monto > 0) {
                    Transferencia transExito = null;
                    try {
                        transExito = datosConexion.getTransferenciaDAO().realizarTransferencia(cuentaRetiro, cuentaDestino, monto);
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(dlgTransferencia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (transExito != null) {
                        String resultado = "Folio Transferencia: " + transExito.getIdTransferencia();
                        JOptionPane.showMessageDialog(this, "Tranferencia realizada con éxito\n" + resultado, "Transferencia Exitosa", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese un monto correcto", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Monto Invalido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "La cuenta destino no es existente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblCuentaRetiro = new javax.swing.JLabel();
        lblContrasenia = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        lblContrasenia1 = new javax.swing.JLabel();
        cbxCuentaRetiro = new javax.swing.JComboBox<>();
        txtCuentaDestino = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        fondo.setBackground(new java.awt.Color(102, 153, 255));

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Transferencia");

        btnConfirmar.setBackground(new java.awt.Color(0, 102, 255));
        btnConfirmar.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(0, 102, 255));
        btnCancelar.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblCuentaRetiro.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblCuentaRetiro.setForeground(new java.awt.Color(255, 255, 255));
        lblCuentaRetiro.setText("Cuenta de retiro:");

        lblContrasenia.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblContrasenia.setForeground(new java.awt.Color(255, 255, 255));
        lblContrasenia.setText("Cuenta destino:");

        txtMonto.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });

        lblContrasenia1.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblContrasenia1.setForeground(new java.awt.Color(255, 255, 255));
        lblContrasenia1.setText("Monto:");

        cbxCuentaRetiro.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N

        txtCuentaDestino.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        txtCuentaDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuentaDestinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnConfirmar)
                                .addGap(155, 155, 155)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(fondoLayout.createSequentialGroup()
                                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblContrasenia1)
                                    .addComponent(lblContrasenia)
                                    .addComponent(lblCuentaRetiro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxCuentaRetiro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMonto)
                                    .addComponent(txtCuentaDestino))
                                .addGap(30, 30, 30))))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addContainerGap(196, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addGap(196, 196, 196))
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel2)
                .addGap(49, 49, 49)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCuentaRetiro)
                    .addComponent(cbxCuentaRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasenia)
                    .addComponent(txtCuentaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasenia1)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método oyente que permite confirmar la transferencia, valida que los
     * campos de texto no estén vacíos.
     *
     * @param evt Evento de dar clic en el botón.
     */
    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (!String.valueOf(cbxCuentaRetiro.getSelectedItem()).equals(txtCuentaDestino.getText())) {
            if (!txtCuentaDestino.getText().isBlank()) {
                if (!txtMonto.getText().isBlank()) {
                    transferencia();
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese un monto");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese una cuenta destino");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No es posible realizar una transferencia a la misma cuenta");
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    /**
     * Método oyente que permite salir de la opción transferencia y redirige al
     * menú principal.
     *
     * @param evt Evento de dar clic en el botón.
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void txtCuentaDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuentaDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentaDestinoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<String> cbxCuentaRetiro;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblContrasenia1;
    private javax.swing.JLabel lblCuentaRetiro;
    private javax.swing.JTextField txtCuentaDestino;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
