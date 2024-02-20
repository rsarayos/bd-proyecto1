package org.itson.bdavanzadas.bancobd;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import org.itson.bdavanzadas.bancobd.buttonColumn.ButtonColumn;
import org.itson.bdavanzadas.bancobddominio.Cliente;
import org.itson.bdavanzadas.bancobddominio.Cuenta;
import org.itson.bdavanzadas.bancobdpersistencia.daos.DatosConexion;
import org.itson.bdavanzadas.bancobdpersistencia.dtos.CuentaNuevaDTO;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 * Representa el dialog Cuentas, permite mostrar los datos de las cuentas como 
 * el número, el saldo y el estado.
 * 
 * La clase proporciona un constructor para instanciar el dialog, además 
 * contiene métodos oyentes para cada botón presente en el menú.
 * 
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class dlgCuentas extends javax.swing.JDialog {

    private final DatosConexion datosConexion;
    private Cliente cliente;

    /**
     * Método constructor que permite inicializar el diálogo para mostrar las 
     * cuentas de un usuario.
     * 
     * @param parent El Frame padre del diálogo.
     * @param modal Indica si el diálogo es modal o no.
     * @param datosConexion Objeto DatosConexion que contiene la información de 
     * conexión a la base de datos.
     * @param cliente El cliente a actualizar.
     */
    public dlgCuentas(java.awt.Frame parent, boolean modal, DatosConexion datosConexion, Cliente cliente) {
        super(parent, modal);
        initComponents();
        this.datosConexion = datosConexion;
        this.cliente = cliente;
        mostrarTabla();
    }

    /**
     * Método que permite mostrar la tabla que muestra las cuentas de un 
     * cliente.
     */
    private void mostrarTabla() {
        List<Cuenta> listaCuentas;
        try {
            listaCuentas = datosConexion.getCuentaDAO().consultarCuentasCliente(cliente.getTelefono());
            DefaultTableModel modelo = new DefaultTableModel();
            
            modelo.addColumn("CUENTA");
            modelo.addColumn("SALDO");
            modelo.addColumn("ESTADO");
            modelo.addColumn("CANCELAR");

            Locale mexico = new Locale("es", "MX");
            NumberFormat formatoPesos = NumberFormat.getCurrencyInstance(mexico);

            for (Cuenta cuenta : listaCuentas) {
                float cantidad = cuenta.getSaldo();
                String cantidadFormateada = formatoPesos.format(cantidad);

                Object[] fila = {cuenta.getNumCuenta(), cantidadFormateada, cuenta.isEstado() == true ? "Activa" : "Cancelada", "Cancelar"};
                modelo.addRow(fila);
            }
            jTablaCuentas.setModel(modelo);
            TableColumnModel columnModel = jTablaCuentas.getColumnModel();

            ButtonColumn botonCancelarCuenta = new ButtonColumn("Cancelar", (ActionEvent e) -> {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas cancelar la cuenta? No podrás volverla a usar", "Confirmación", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    int row = jTablaCuentas.convertRowIndexToModel(jTablaCuentas.getEditingRow());
                    try {
                        Cuenta cuentaFila = obtenerFilaDeCuenta(row);
                        
                        CuentaNuevaDTO cuentaActualizada = new CuentaNuevaDTO();
                        cuentaActualizada.setNumCuenta(cuentaFila.getNumCuenta());
                        cuentaActualizada.setSaldo(cuentaFila.getSaldo());
                        cuentaActualizada.setFechaApertura(cuentaFila.getFechaApertura());
                        cuentaActualizada.setTelefonoTitular(cuentaFila.getTelefonoTitular());
                        cuentaActualizada.setEstado(false);
                        
                        Cuenta cuentaActu = datosConexion.getCuentaDAO().actualizar(cuentaActualizada);
                        if (cuentaActu != null) {
                            JOptionPane.showMessageDialog(this, "Se canceló la cuenta correctamente", "Cuenta cancelada", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            mostrarTabla();
                        }
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(dlgCuentas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (respuesta == JOptionPane.NO_OPTION) {
                    JOptionPane.getRootFrame().dispose();
                }

            });

            columnModel.getColumn(3).setCellRenderer(botonCancelarCuenta);
            columnModel.getColumn(3).setCellEditor(botonCancelarCuenta);

            JTableHeader header = jTablaCuentas.getTableHeader();
            jTablaCuentas.getTableHeader().setReorderingAllowed(false);
            jTablaCuentas.setDefaultEditor(Object.class, null);
            jTablaCuentas.getTableHeader().setResizingAllowed(false);

            header.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
            header.setPreferredSize(new java.awt.Dimension(80, 30));
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar información de los socios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que permite obtener la cuenta correspondiente a una fila de la 
     * tabla.
     * 
     * @param fila El índice de la fila de la tabla.
     * @return La cuenta asociada a la fila especificada.
     * @throws PersistenciaException Si ocurre un error al consultar la base de 
     * datos.
     */
    private Cuenta obtenerFilaDeCuenta(int fila) throws PersistenciaException {
        List<Cuenta> listaCuentas = datosConexion.getCuentaDAO().consultarCuentasCliente(cliente.getTelefono());
        if (fila >= 0 && fila < listaCuentas.size()) {
            return listaCuentas.get(fila);
        } else {
            return null;
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
        btnAgregarCuenta = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaCuentas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cuentas");
        setResizable(false);

        fondo.setBackground(new java.awt.Color(102, 153, 255));

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cuentas");

        btnAgregarCuenta.setBackground(new java.awt.Color(0, 102, 255));
        btnAgregarCuenta.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnAgregarCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCuenta.setText("Agregar");
        btnAgregarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCuentaActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(0, 102, 255));
        btnSalir.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jTablaCuentas.setFont(new java.awt.Font("Leelawadee UI", 0, 22)); // NOI18N
        jTablaCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablaCuentas.setRowHeight(30);
        jScrollPane1.setViewportView(jTablaCuentas);

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addComponent(btnAgregarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)))
                .addGap(156, 156, 156))
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnAgregarCuenta))
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

    /**
     * Método oyente que permite confirmar abrir el menú de agregar cuentas.
     * 
     * @param evt Evento de dar clic en el botón.
     */
    private void btnAgregarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCuentaActionPerformed
        dlgAgregarCuenta agregarCuenta = new dlgAgregarCuenta(null, true, datosConexion, cliente);
        dispose();
        agregarCuenta.setVisible(true);
    }//GEN-LAST:event_btnAgregarCuentaActionPerformed

    /**
     * Método oyente que permite salir de la opción agregar cuenta y 
     * redirige al diálogo de Cuentas.
     * 
     * @param evt Evento de dar clic en el botón.
     */
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCuenta;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaCuentas;
    // End of variables declaration//GEN-END:variables
}
