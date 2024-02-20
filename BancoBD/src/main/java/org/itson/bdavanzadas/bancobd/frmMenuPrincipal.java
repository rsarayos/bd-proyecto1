
package org.itson.bdavanzadas.bancobd;

import org.itson.bdavanzadas.bancobddominio.Cliente;
import org.itson.bdavanzadas.bancobdpersistencia.daos.DatosConexion;

/**
 * Representa el frame MenuPrincipal que muestra el menú para actualizar un 
 * cliente, crear una cuenta, hacer una transferencia, consultar el historial de
 * operaciones y empezar la transacción de un retiro sin cuenta.
 * 
 * La clase proporciona un constructor para instanciar el frame, además contiene
 * métodos oyentes para cada botón presente en el menú.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class frmMenuPrincipal extends javax.swing.JFrame {

    private final DatosConexion datosConexion;
    private Cliente cliente;
    
    /**
     * Método constructor que permite inicializar el objeto frmMenuInicio.
     * 
     * @param datosConexion Objeto que permite obtener la conexión con las 
     * interfaces DAO.
     * @param cliente Objeto Cliente que contiene los datos del cliente que 
     * ingresó al sistema.
     */
    public frmMenuPrincipal(DatosConexion datosConexion, Cliente cliente) {
        initComponents();
        this.datosConexion=datosConexion;
        this.cliente=cliente;
        
        lblBienvenida.setText("Bienvenido/a "+cliente.getNombre());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        fondo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCuentas = new javax.swing.JButton();
        btnRetiroSinCuenta = new javax.swing.JButton();
        btnTransferencia = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblBienvenida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Banco BDA");
        setResizable(false);

        fondo.setBackground(new java.awt.Color(102, 153, 255));

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BANCO BDA");

        btnCuentas.setBackground(new java.awt.Color(0, 102, 255));
        btnCuentas.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnCuentas.setForeground(new java.awt.Color(255, 255, 255));
        btnCuentas.setText("Cuentas");
        btnCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuentasActionPerformed(evt);
            }
        });

        btnRetiroSinCuenta.setBackground(new java.awt.Color(0, 102, 255));
        btnRetiroSinCuenta.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnRetiroSinCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnRetiroSinCuenta.setText("Retirar sin cuenta");
        btnRetiroSinCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetiroSinCuentaActionPerformed(evt);
            }
        });

        btnTransferencia.setBackground(new java.awt.Color(0, 102, 255));
        btnTransferencia.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnTransferencia.setForeground(new java.awt.Color(255, 255, 255));
        btnTransferencia.setText("Transferencia");
        btnTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferenciaActionPerformed(evt);
            }
        });

        btnHistorial.setBackground(new java.awt.Color(0, 102, 255));
        btnHistorial.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnHistorial.setForeground(new java.awt.Color(255, 255, 255));
        btnHistorial.setText("Historial");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        btnCerrarSesion.setBackground(new java.awt.Color(0, 102, 255));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 36)); // NOI18N
        jLabel1.setText("Cliente");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });

        lblBienvenida.setBackground(new java.awt.Color(102, 153, 255));
        lblBienvenida.setFont(new java.awt.Font("Leelawadee UI", 1, 26)); // NOI18N
        lblBienvenida.setForeground(new java.awt.Color(255, 255, 255));
        lblBienvenida.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRetiroSinCuenta))
                .addGap(158, 158, 158))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addGap(35, 35, 35))
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(57, 57, 57))
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRetiroSinCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addGap(35, 35, 35))
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
     * Método oyente que permite ver las cuentas de un cliente, dentro se pueden
     * agregar o cancelar cuentas.
     * 
     * @param evt Evento de dar clic en el botón.
     */
    private void btnCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentasActionPerformed
        dlgCuentas cuentas = new dlgCuentas(this, true, datosConexion, cliente);
        cuentas.setVisible(true);
    }//GEN-LAST:event_btnCuentasActionPerformed

    /**
     * Método oyente que permite crear un retiro sin cuenta, dentro se elige la
     * cuenta y el monto deseado.
     * 
     * @param evt Evento de dar clic en el botón.
     */
    private void btnRetiroSinCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetiroSinCuentaActionPerformed
        dlgRetiroSinCuenta retiroSinCuenta = new dlgRetiroSinCuenta(this, true, datosConexion, cliente);
        retiroSinCuenta.setVisible(true);
    }//GEN-LAST:event_btnRetiroSinCuentaActionPerformed

    /**
     * Método oyente que permite crear una transferencia, dentro se elige la
     * cuenta, se agrega la cuenta destino y  el monto deseado.
     * 
     * @param evt Evento de dar clic en el botón.
     */
    private void btnTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferenciaActionPerformed
        dlgTransferencia transferencia = new dlgTransferencia(this, true, datosConexion, cliente);
        transferencia.setVisible(true);
    }//GEN-LAST:event_btnTransferenciaActionPerformed

    /**
     * Método oyente que permite ver el historial de operaciones, dentro se 
     * eligen las cuentas, los tipos de operaciones realizadas y el un filtro 
     * para buscarlas por fechas.
     * 
     * @param evt Evento de dar clic en el botón.
     */
    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        dlgHistorial historial = new dlgHistorial(this, true, datosConexion, cliente);
        historial.setVisible(true);
    }//GEN-LAST:event_btnHistorialActionPerformed

    /**
     * Método oyente que permite cerrar la sesión y redirige al menú de inicio
     * 
     * @param evt Evento de dar clic en el botón.
     */
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        dispose();
        frmMenuInicio menuInicio = new frmMenuInicio(datosConexion);
        menuInicio.setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    /**
     * Método oyente que permite actualizar los datos del cliente, excepto el 
     * teléfono.
     * 
     * @param evt Evento de dar clic en el texto.
     */
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        dlgActualizarCliente actualizarCliente = new dlgActualizarCliente(this, true, datosConexion, cliente);
        actualizarCliente.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCuentas;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnRetiroSinCuenta;
    private javax.swing.JButton btnTransferencia;
    private javax.swing.JPanel fondo;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblBienvenida;
    // End of variables declaration//GEN-END:variables
}
