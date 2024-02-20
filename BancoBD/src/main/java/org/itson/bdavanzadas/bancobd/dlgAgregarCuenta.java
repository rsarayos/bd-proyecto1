package org.itson.bdavanzadas.bancobd;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.bancobddominio.Cliente;
import org.itson.bdavanzadas.bancobddominio.Cuenta;
import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.GenerarNumeroCuenta;
import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.Validaciones;
import org.itson.bdavanzadas.bancobdpersistencia.daos.DatosConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.CuentaNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 *
 * @author victo
 */
public class dlgAgregarCuenta extends javax.swing.JDialog {

    private final DatosConexion datosConexion;
    private Cliente cliente;
    private Validaciones validar;

    /**
     * Creates new form dlgAgregarCuenta
     */
    public dlgAgregarCuenta(java.awt.Frame parent, boolean modal, DatosConexion datosConexion, Cliente cliente) {
        super(parent, modal);
        initComponents();
        this.datosConexion = datosConexion;
        this.cliente = cliente;
        this.validar = new Validaciones();
        generarNumCuenta();
    }

    private void generarNumCuenta() {
        GenerarNumeroCuenta gnc = new GenerarNumeroCuenta();
        String numero = gnc.generarNumeroCuenta();

        List<Cuenta> listaCuentas = null;

        try {
            listaCuentas = datosConexion.getCuentaDAO().consultar();
        } catch (PersistenciaException ex) {
            Logger.getLogger(dlgAgregarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Cuenta cuenta : listaCuentas) {
            if (!cuenta.getNumCuenta().equals(numero)) {
                txtCuenta.setText(numero);
                break;
            } else {
                generarNumCuenta();
                break;
            }
        }
    }

    private void agregarCuenta() {
        String numCuenta = txtCuenta.getText();

        if (validar.validaCantidad(txtMonto.getText())) {
            float monto = Float.parseFloat(txtMonto.getText());
            CuentaNuevaDTO cuentaNueva = new CuentaNuevaDTO();
            cuentaNueva.setNumCuenta(numCuenta);
            cuentaNueva.setSaldo(monto);
            cuentaNueva.setEstado(true);
            cuentaNueva.setTelefonoTitular(cliente.getTelefono());
            cuentaNueva.setFechaApertura(null);

            try {
                cuentaNueva.esValido();
                datosConexion.getCuentaDAO().agregar(cuentaNueva);
                JOptionPane.showMessageDialog(this, "Se agregó la cuenta correctamente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
            } catch (ValidacionDTOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
            } catch (PersistenciaException ex) {
                JOptionPane.showMessageDialog(this, "No fue posible agregar la cuenta", "Error de almacenamiento", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Ingresar un monto valido", "Error de registro", JOptionPane.ERROR_MESSAGE);
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
        lblTelefono = new javax.swing.JLabel();
        txtCuenta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblMonto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar cuentas");
        setResizable(false);

        fondo.setBackground(new java.awt.Color(102, 153, 255));

        lblTelefono.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono.setText("No. Cuenta:");

        txtCuenta.setEditable(false);
        txtCuenta.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        txtCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuentaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cuenta nueva");

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

        lblMonto.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(255, 255, 255));
        lblMonto.setText("Monto inicial:");

        txtMonto.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jLabel2)
                .addContainerGap(193, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(208, 208, 208))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMonto)
                            .addComponent(lblTelefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(260, 260, 260))))
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(81, 81, 81)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMonto)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnConfirmar))
                .addGap(90, 90, 90))
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (!txtMonto.getText().isBlank()) {
            agregarCuenta();
            dlgCuentas cuentas = new dlgCuentas(null, true, datosConexion, cliente);
            cuentas.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un monto");
        }

    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void txtCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentaActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtCuenta;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
