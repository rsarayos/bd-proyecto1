package org.itson.bdavanzadas.bancobd;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.bancobddominio.Cliente;
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
public class dlgActualizarCliente extends javax.swing.JDialog {

    private final DatosConexion datosConexion;
    private Cliente cliente;
 

    /**
     * Creates new form dlgActualizarCliente
     */
    public dlgActualizarCliente(java.awt.Frame parent, boolean modal, DatosConexion datosConexion, Cliente cliente) {
        super(parent, modal);
        initComponents();
        this.datosConexion = datosConexion;
        this.cliente = cliente;

        System.out.println(cliente);
        
        try {
            Cliente clienteActu = datosConexion.getClientesDAO().obtener(cliente.getTelefono());
            System.out.println(clienteActu);
            txtNombres.setText(clienteActu.getNombre());
            txtApellidoPaterno.setText(clienteActu.getApellidoPaterno());
            txtApellidoMaterno.setText(clienteActu.getApellidoMaterno());
            txtTelefono.setText(clienteActu.getTelefono());
            jDateFechaNacimiento.setDate(clienteActu.getFechaNacimiento());
            pswContrasenia.setText(clienteActu.getPassword());
        } catch (PersistenciaException ex) {
            Logger.getLogger(dlgActualizarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Direccion direccion = datosConexion.getDireccionDAO().obtener(cliente.getIdDireccion());
            txtCalle.setText(direccion.getCalle());
            txtNumero.setText(direccion.getNumero());
            txtColonia.setText(direccion.getColonia());
            txtCiudad.setText(direccion.getCiudad());
            txtCodigoPostal.setText(direccion.getCp());
        } catch (PersistenciaException ex) {
            Logger.getLogger(dlgActualizarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizar() {
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

        DireccionNuevaDTO direccionActualizada = new DireccionNuevaDTO();
        direccionActualizada.setIdDireccion(cliente.getIdDireccion());
        direccionActualizada.setCalle(calle);
        direccionActualizada.setNumero(numero);
        direccionActualizada.setColonia(colonia);
        direccionActualizada.setCiudad(ciudad);
        direccionActualizada.setCp(cp);

        ClienteNuevoDTO clienteActualizado = new ClienteNuevoDTO();
        clienteActualizado.setTelefono(cliente.getTelefono());
        clienteActualizado.setNombre(nombres);
        clienteActualizado.setApellidoPaterno(apellidoPaterno);
        clienteActualizado.setApellidoMaterno(apellidoMaterno);
        clienteActualizado.setFechaNacimiento(fechaNacimiento);
        clienteActualizado.setPassword(contrasenia);

        try {
            direccionActualizada.esValido();
            Direccion direccionActu = this.datosConexion.getDireccionDAO().actualizar(direccionActualizada);
            clienteActualizado.setIdDireccion(direccionActu.getIdDireccion());
            clienteActualizado.esValido();
            Cliente clienteActualizadoNuevo = this.datosConexion.getClientesDAO().actualizar(clienteActualizado, telefono);
            
            if(clienteActualizadoNuevo != null){
            JOptionPane.showMessageDialog(this, "Se modificó al cliente exitosamente", "Cliente modificado", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            }
        } catch (ValidacionDTOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, "No fue posible actualizar al cliente", "Error de almacenamiento", JOptionPane.ERROR_MESSAGE);
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
        setTitle("Actualizar cliente");
        setResizable(false);

        fondo.setBackground(new java.awt.Color(102, 153, 255));
        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 70)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Actualizar datos");
        fondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

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

        txtTelefono.setEditable(false);
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
                                                    actualizar();
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
