/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package org.itson.bdavanzadas.bancobd;

import com.google.protobuf.TextFormat.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.bancobddominio.Direccion;
import org.itson.bdavanzadas.bancobdpersistencia.daos.DatosConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.ClienteNuevoDTO;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.DireccionNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.ValidacionDTOException;

/**
 *
 * @author victo
 */
public class dlgRegistrarse extends javax.swing.JDialog {

    private final DatosConexion datosConexion;

    /**
     * Creates new form dlgRegistrarse
     */
    public dlgRegistrarse(java.awt.Frame parent, boolean modal, DatosConexion datosConexion) {
        super(parent, modal);
        initComponents();
        this.datosConexion = datosConexion;

    }

    private void registrar() throws java.text.ParseException {
        String telefono = txtTelefono.getText();
        String nombres = txtNombres.getText();
        String apellidoPaterno = txtApellidoPaterno.getText();
        String apellidoMaterno = txtApellidoMaterno.getText();
        java.util.Date fechaSeleccionada = jDateFechaNacimiento.getDate();
        java.sql.Date fechaNacimiento = new java.sql.Date(fechaSeleccionada.getTime());
        String calle = txtCalle.getText();
        String numero = txtNumero.getText();
        String colonia = txtColonia.getText();
        String ciudad = txtCiudad.getText();
        String cp = txtCodigoPostal.getText();

        char[] contraseniaArray = pswContrasenia.getPassword();
        String contrasenia = new String(contraseniaArray);

        DireccionNuevaDTO direccionNueva = new DireccionNuevaDTO();
        direccionNueva.setCalle(calle);
        direccionNueva.setNumero(numero);
        direccionNueva.setColonia(colonia);
        direccionNueva.setCiudad(ciudad);
        direccionNueva.setCp(cp);

        ClienteNuevoDTO clienteNuevo = new ClienteNuevoDTO();
        clienteNuevo.setTelefono(telefono);
        clienteNuevo.setNombre(nombres);
        clienteNuevo.setApellidoPaterno(apellidoPaterno);
        clienteNuevo.setApellidoMaterno(apellidoMaterno);
        clienteNuevo.setFechaNacimiento(fechaNacimiento);
        clienteNuevo.setPassword(contrasenia);
        limpiarDatos();
        try {
            direccionNueva.esValido();
            Direccion direccion = this.datosConexion.getDireccionDAO().agregar(direccionNueva);
            clienteNuevo.setIdDireccion(direccion.getIdDireccion());
            clienteNuevo.esValido();
            if (datosConexion.getClientesDAO().obtener(clienteNuevo.getTelefono()) == null) {
                this.datosConexion.getClientesDAO().agregar(clienteNuevo);
                JOptionPane.showMessageDialog(this, "Se registró al cliente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El número de teléfono ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ValidacionDTOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, "No fue posible agregar al cliente", "Error de almacenamiento", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    private void limpiarDatos() {
        txtApellidoMaterno.setText("");
        txtApellidoPaterno.setText("");
        txtCalle.setText("");
        txtCiudad.setText("");
        txtCodigoPostal.setText("");
        txtColonia.setText("");
        txtNombres.setText("");
        txtNumero.setText("");
        txtTelefono.setText("");
        pswContrasenia.setText("");
        jDateFechaNacimiento.setDate(null);
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
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblContrasenia = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        pswContrasenia = new javax.swing.JPasswordField();
        lblTelefono1 = new javax.swing.JLabel();
        txtApellidoPaterno = new javax.swing.JTextField();
        lblTelefono2 = new javax.swing.JLabel();
        txtApellidoMaterno = new javax.swing.JTextField();
        lblTelefono3 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblTelefono4 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        lblTelefono5 = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        lblTelefono6 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblTelefono7 = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        lblTelefono8 = new javax.swing.JLabel();
        txtCodigoPostal = new javax.swing.JTextField();
        lblTelefono9 = new javax.swing.JLabel();
        jDateFechaNacimiento = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrarse");
        setResizable(false);

        fondo.setBackground(new java.awt.Color(102, 153, 255));
        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 70)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Registrarse");
        fondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 22, -1, -1));

        btnAceptar.setBackground(new java.awt.Color(0, 102, 255));
        btnAceptar.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        fondo.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 515, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(0, 102, 255));
        btnCancelar.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        fondo.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 515, -1, -1));

        lblContrasenia.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblContrasenia.setForeground(new java.awt.Color(255, 255, 255));
        lblContrasenia.setText("Contraseña:");
        fondo.add(lblContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 443, -1, -1));

        lblTelefono.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono.setText("Nombres:");
        fondo.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 166, -1, -1));

        txtNombres.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        fondo.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 167, 230, -1));

        pswContrasenia.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        fondo.add(pswContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 444, 228, -1));

        lblTelefono1.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTelefono1.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono1.setText("Apellido paterno:");
        fondo.add(lblTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 211, -1, -1));

        txtApellidoPaterno.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        fondo.add(txtApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 212, 228, -1));

        lblTelefono2.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTelefono2.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono2.setText("Apellido materno:");
        fondo.add(lblTelefono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 256, -1, -1));

        txtApellidoMaterno.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        fondo.add(txtApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 257, 228, -1));

        lblTelefono3.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTelefono3.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono3.setText("Calle:");
        fondo.add(lblTelefono3, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 166, -1, -1));

        txtTelefono.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        txtTelefono.setToolTipText("");
        fondo.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 228, -1));

        lblTelefono4.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTelefono4.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono4.setText("Teléfono:");
        fondo.add(lblTelefono4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, -1));

        txtCalle.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        fondo.add(txtCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 167, 228, -1));

        lblTelefono5.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTelefono5.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono5.setText("Colonia:");
        fondo.add(lblTelefono5, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 256, -1, -1));

        txtColonia.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        fondo.add(txtColonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 257, 228, -1));

        lblTelefono6.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTelefono6.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono6.setText("Número:");
        fondo.add(lblTelefono6, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 211, -1, -1));

        txtNumero.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        fondo.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 212, 228, -1));

        lblTelefono7.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTelefono7.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono7.setText("Ciudad:");
        fondo.add(lblTelefono7, new org.netbeans.lib.awtextra.AbsoluteConstraints(537, 301, -1, -1));

        txtCiudad.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        fondo.add(txtCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 302, 228, -1));

        lblTelefono8.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTelefono8.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono8.setText("CP:");
        fondo.add(lblTelefono8, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 340, -1, 33));

        txtCodigoPostal.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        fondo.add(txtCodigoPostal, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 345, 228, -1));

        lblTelefono9.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTelefono9.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono9.setText("Fecha de nacimiento:");
        fondo.add(lblTelefono9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jDateFechaNacimiento.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        fondo.add(jDateFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 230, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (!txtNombres.getText().isBlank()) {
            if (!txtApellidoPaterno.getText().isBlank()) {
                if (!txtApellidoMaterno.getText().isBlank()) {
                    if (jDateFechaNacimiento.getDate() != null) {
                        if (!txtTelefono.getText().isBlank()) {
                            if (!txtCalle.getText().isBlank()) {
                                if (!txtNumero.getText().isBlank()) {
                                    if (!txtColonia.getText().isBlank()) {
                                        if (!txtCiudad.getText().isBlank()) {
                                            if (!txtCodigoPostal.getText().isBlank()) {
                                                if (!pswContrasenia.getText().isBlank()) {
                                                    try {
                                                        registrar();
                                                    } catch (java.text.ParseException ex) {
                                                        Logger.getLogger(dlgRegistrarse.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(this, "Ingrese una contraseña");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(this, "Ingrese el código postal");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this, "Ingrese una ciudad");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Ingrese una colonia");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Ingrese una número");
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Ingrese una calle");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Ingrese un teléfono");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingrese una fecha de nacimiento");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese el apellido materno");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese el apellido paterno");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese los nombres");
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JPanel fondo;
    private com.toedter.calendar.JDateChooser jDateFechaNacimiento;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JLabel lblTelefono2;
    private javax.swing.JLabel lblTelefono3;
    private javax.swing.JLabel lblTelefono4;
    private javax.swing.JLabel lblTelefono5;
    private javax.swing.JLabel lblTelefono6;
    private javax.swing.JLabel lblTelefono7;
    private javax.swing.JLabel lblTelefono8;
    private javax.swing.JLabel lblTelefono9;
    private javax.swing.JPasswordField pswContrasenia;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCodigoPostal;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
