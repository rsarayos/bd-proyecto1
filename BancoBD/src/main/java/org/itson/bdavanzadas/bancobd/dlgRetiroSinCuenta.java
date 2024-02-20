package org.itson.bdavanzadas.bancobd;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.bancobddominio.Cliente;
import org.itson.bdavanzadas.bancobddominio.Cuenta;
import org.itson.bdavanzadas.bancobddominio.Retiro;
import org.itson.bdavanzadas.bancobddominio.Transaccion;
import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.GenerarFolioContraRetiros;
import org.itson.bdavanzadas.bancobdpersistencia.auxiliar.Validaciones;
import org.itson.bdavanzadas.bancobdpersistencia.daos.DatosConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.RetiroNuevoDTO;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.TransaccionNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 *
 * @author victo
 */
public class dlgRetiroSinCuenta extends javax.swing.JDialog {

    private final DatosConexion datosConexion;
    private Cliente cliente;
    private Validaciones validar;

    /**
     * Creates new form dlgRetiroSinCuenta
     */
    public dlgRetiroSinCuenta(java.awt.Frame parent, boolean modal, DatosConexion datosConexion, Cliente cliente) {
        super(parent, modal);
        initComponents();
        this.datosConexion = datosConexion;
        this.cliente = cliente;
        this.validar = new Validaciones();
        mostrarCuentas();
    }

    private void mostrarCuentas() {
        List<Cuenta> listaCuentasRetiro = null;
        try {
            listaCuentasRetiro = datosConexion.getCuentaDAO().consultarCuentasCliente(cliente.getTelefono());
            for (Cuenta cuenta : listaCuentasRetiro) {
                if (cuenta.isEstado()==true) {
                    cbxCuentaRetiro.addItem(cuenta.getNumCuenta());
                }
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(dlgTransferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String generarFolio() {
        GenerarFolioContraRetiros generarFolioContra = new GenerarFolioContraRetiros();

        List<Retiro> listaRetiros = null;
        try {
            listaRetiros = datosConexion.getRetiroDAO().consultar();
        } catch (PersistenciaException ex) {
            Logger.getLogger(dlgAgregarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        String folio = "";
        for (Retiro retiro : listaRetiros) {
            folio = generarFolioContra.generarFolio();
            if (!retiro.getFolioRetiro().equals(folio)) {
                return folio;
            }
        }
        return null;
    }

    private void generarRetiro() {
        String cuenta = String.valueOf(cbxCuentaRetiro.getSelectedItem());

        if (validar.validaCantidad(txtMonto.getText())) {
            float monto = Float.parseFloat(txtMonto.getText());

            GenerarFolioContraRetiros generarFolioContra = new GenerarFolioContraRetiros();
            String folio = generarFolio();
            String contrasenia = generarFolioContra.generarContraseña();

            Date fechaActual = new Date();
            Timestamp fecha = new Timestamp(fechaActual.getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaActual);
            calendar.add(Calendar.MINUTE, 10);
            Date fechaCon10Minutos = calendar.getTime();
            Timestamp fechaCancelacion = new Timestamp(fechaCon10Minutos.getTime());

            if (monto > 0) {
                TransaccionNuevaDTO transaccionNueva = new TransaccionNuevaDTO();
                transaccionNueva.setNumCuenta(cuenta);
                transaccionNueva.setCantidad(monto);
                transaccionNueva.setFecha(fecha);

                RetiroNuevoDTO retiroNuevo = new RetiroNuevoDTO();
                retiroNuevo.setFolioRetiro(folio);
                retiroNuevo.setContraseniaRetiro(contrasenia);
                retiroNuevo.setEstado(1);

                try {
                    transaccionNueva.esValido();
                    retiroNuevo.esValido();
                    Transaccion transaccion = datosConexion.getTransaccionDAO().nueva(transaccionNueva);
                    retiroNuevo.setIdTransaccion(transaccion.getIdTransaccion());
                    Retiro retiro = datosConexion.getRetiroDAO().nuevo(retiroNuevo);
                    if (retiro != null) {
                        String mensaje = "Retiro sin cuenta creado, guarda el folio y la contraseña\nNo. Folio de la transacción: " + folio + "\nContraseña: " + contrasenia + "\nFecha de vencimiento: " + fechaCancelacion.toString();
                        JOptionPane.showMessageDialog(this, mensaje, "Retiro sin cuenta", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "No fue posible crear el retiro", "Retiro sin cuenta", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (PersistenciaException ex) {
                    Logger.getLogger(dlgRetiroSinCuenta.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ValidacionDTOException ex) {
                    Logger.getLogger(dlgRetiroSinCuenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un monto correcto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Monto Invalido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
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
        txtMonto = new javax.swing.JTextField();
        lblContrasenia1 = new javax.swing.JLabel();
        cbxCuentaRetiro = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Retiro sin cuenta");
        setResizable(false);

        fondo.setBackground(new java.awt.Color(102, 153, 255));

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Retiro sin cuenta");

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

        txtMonto.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });

        lblContrasenia1.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblContrasenia1.setForeground(new java.awt.Color(255, 255, 255));
        lblContrasenia1.setText("Monto:");

        cbxCuentaRetiro.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tienes 10 minutos para realizar el retiro antes de que sea cancelado!");

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                        .addGap(0, 56, Short.MAX_VALUE)
                        .addComponent(btnConfirmar)
                        .addGap(126, 126, 126)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblContrasenia1)
                            .addComponent(lblCuentaRetiro))
                        .addGap(28, 28, 28)
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxCuentaRetiro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMonto))))
                .addGap(196, 196, 196))
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(186, 186, 186))
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addGap(62, 62, 62)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCuentaRetiro)
                    .addComponent(cbxCuentaRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasenia1)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
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

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (!txtMonto.getText().isBlank()) {
            generarRetiro();
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un monto", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<String> cbxCuentaRetiro;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblContrasenia1;
    private javax.swing.JLabel lblCuentaRetiro;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
