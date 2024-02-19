package org.itson.bdavanzadas.bancobd;

import java.awt.Font;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.itson.bdavanzadas.bancobd.dlgTransferencia;
import org.itson.bdavanzadas.bancobddominio.Cliente;
import org.itson.bdavanzadas.bancobddominio.Cuenta;
import org.itson.bdavanzadas.bancobddominio.Retiro;
import org.itson.bdavanzadas.bancobddominio.Transaccion;
import org.itson.bdavanzadas.bancobddominio.Transferencia;
import org.itson.bdavanzadas.bancobdpersistencia.daos.DatosConexion;
import org.itson.bdavanzadas.bancobdpersistencia.excepciones.PersistenciaException;

/**
 *
 * @author victo
 */
public class dlgHistorial extends javax.swing.JDialog {

    private final DatosConexion datosConexion;
    private Cliente cliente;
    private Cuenta cuenta;
    private ButtonGroup seleccionFecha;
    private ButtonGroup seleccionTipo;

    /**
     * Creates new form dlgHistorial
     */
    public dlgHistorial(java.awt.Frame parent, boolean modal, DatosConexion datosConexion, Cliente cliente) {
        super(parent, modal);
        initComponents();
        this.datosConexion = datosConexion;
        this.cliente = cliente;
        mostrarCuentas();

        ButtonGroup seleccionTipo = new ButtonGroup();
        seleccionTipo.add(rbtnTransferencia);
        seleccionTipo.add(rbtnRetiro);
        seleccionTipo.add(rbtnTodas);
        
        ButtonGroup seleccionFecha= new ButtonGroup();
        seleccionFecha.add(rbtnFechas);

    }

    private void mostrarCuentas() {
        List<Cuenta> listaCuentasRetiro = null;
        try {
            listaCuentasRetiro = datosConexion.getCuentaDAO().consultarCuentasCliente(cliente.getTelefono());
            for (Cuenta cuentaL : listaCuentasRetiro) {
                cbxCuenta.addItem(cuentaL.getNumCuenta());
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(dlgTransferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarTablaTodas(Cuenta cuenta) {
        List<Transaccion> listaTransacciones;
        List<Transferencia> listaTransferencias;
        List<Retiro> listaRetiros;
        try {
            listaTransacciones = datosConexion.getTransaccionDAO().consultarTransaccionesCuenta(cuenta.getNumCuenta());
            listaTransferencias = datosConexion.getTransferenciaDAO().consultarTransCuenta(cuenta.getNumCuenta());
            listaRetiros = datosConexion.getRetiroDAO().consultarRetiroCuenta(cuenta.getNumCuenta());

            crearTablaTodas(listaTransacciones, listaTransferencias, listaRetiros);

        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar información de las cuentas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarTablaTodasPorFechas(Cuenta cuenta) {
        List<Transaccion> listaTransacciones;
        List<Transferencia> listaTransferencias;
        List<Retiro> listaRetiros;

        Timestamp fechaInicio = new Timestamp(jDateFechaInicio.getDate().getTime());
        Timestamp fechaFin = new Timestamp(jDateFechaFin.getDate().getTime());

        try {
            listaTransacciones = datosConexion.getTransaccionDAO().consultarTransaccionesCuentaPorFechas(cuenta.getNumCuenta(), fechaInicio, fechaFin);
            listaTransferencias = datosConexion.getTransferenciaDAO().consultarTransCuentaPorFechas(cuenta.getNumCuenta(), fechaInicio, fechaFin);
            listaRetiros = datosConexion.getRetiroDAO().consultarRetiroCuentaPorFechas(cuenta.getNumCuenta(), fechaInicio, fechaFin);

            crearTablaTodas(listaTransacciones, listaTransferencias, listaRetiros);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar información de las cuentas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearTablaTodas(List<Transaccion> listaTransacciones, List<Transferencia> listaTransferencias, List<Retiro> listaRetiros) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CUENTA DESTINO");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("FECHA");
        modelo.addColumn("TIPO");
        modelo.addColumn("ESTADO");

        Object[] fila = new Object[5];
        for (Transaccion transaccion : listaTransacciones) {
            for (Transferencia transferencia : listaTransferencias) {
                fila[0] = transferencia.getCuentaDestino();
                if (transaccion.getIdTransaccion() == (transferencia.getIdTransaccion())) {
                    fila[3] = "Transferencia";
                }
            }
            for (Retiro retiro : listaRetiros) {
                if (transaccion.getIdTransaccion() == (retiro.getIdTransaccion())) {
                    fila[3] = "Retiro";
                }
            }
            fila[1] = transaccion.getCantidad();
            fila[2] = transaccion.getFecha();
            fila[4] = transaccion.getEstadoTransaccion();

            modelo.addRow(fila);
        }

        jTablaHistorial.setModel(modelo);
        jTablaHistorial.getTableHeader().setReorderingAllowed(false);
        jTablaHistorial.setDefaultEditor(Object.class, null);
        jTablaHistorial.getTableHeader().setResizingAllowed(false);
        JTableHeader header = jTablaHistorial.getTableHeader();
        header.setFont(new Font("Leelawadee UI", Font.BOLD, 12));
    }

    private void mostrarTablaTransferencias(Cuenta cuenta) {
        List<Transferencia> listaTransferencias;
        try {
            listaTransferencias = datosConexion.getTransferenciaDAO().consultarTransCuenta(cuenta.getNumCuenta());
            crearTablaTransferencias(listaTransferencias);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar información de las cuentas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarTablaTransferenciasPorFechas(Cuenta cuenta) {
        List<Transferencia> listaTransferencias;

        Timestamp fechaInicio = new Timestamp(jDateFechaInicio.getDate().getTime());
        Timestamp fechaFin = new Timestamp(jDateFechaFin.getDate().getTime());

        try {
            listaTransferencias = datosConexion.getTransferenciaDAO().consultarTransCuentaPorFechas(cuenta.getNumCuenta(), fechaInicio, fechaFin);
            crearTablaTransferencias(listaTransferencias);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar información de las cuentas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearTablaTransferencias(List<Transferencia> listaTransferencias) {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CUENTA DESTINO");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("FECHA");
        modelo.addColumn("TIPO");
        modelo.addColumn("ESTADO");

        Object[] fila = new Object[5];
        for (Transferencia transferencia : listaTransferencias) {
            fila[0] = transferencia.getCuentaDestino();
            fila[1] = transferencia.getCantidad();
            fila[2] = transferencia.getFecha();
            fila[3] = "Transferencia";
            fila[4] = "Completado";

            modelo.addRow(fila);
        }

        jTablaHistorial.setModel(modelo);
        jTablaHistorial.getTableHeader().setReorderingAllowed(false);
        jTablaHistorial.setDefaultEditor(Object.class, null);
        jTablaHistorial.getTableHeader().setResizingAllowed(false);
        JTableHeader header = jTablaHistorial.getTableHeader();
        header.setFont(new Font("Leelawadee UI", Font.BOLD, 12));

    }

    private void mostrarTablaRetiros(Cuenta cuenta) {
        List<Retiro> listaRetiros;
        List<Transaccion> listaTransacciones;
        try {
            listaTransacciones = datosConexion.getTransaccionDAO().consultarTransaccionesCuenta(cuenta.getNumCuenta());
            listaRetiros = datosConexion.getRetiroDAO().consultarRetiroCuenta(cuenta.getNumCuenta());
            crearTablaRetiros(listaTransacciones, listaRetiros);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar información de las cuentas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarTablaRetirosPorFechas(Cuenta cuenta) {
        List<Retiro> listaRetiros;
        List<Transaccion> listaTransacciones;

        Timestamp fechaInicio = new Timestamp(jDateFechaInicio.getDate().getTime());
        Timestamp fechaFin = new Timestamp(jDateFechaFin.getDate().getTime());

        try {
            listaTransacciones = datosConexion.getTransaccionDAO().consultarTransaccionesCuentaPorFechas(cuenta.getNumCuenta(), fechaInicio, fechaFin);
            listaRetiros = datosConexion.getRetiroDAO().consultarRetiroCuentaPorFechas(cuenta.getNumCuenta(), fechaInicio, fechaFin);
            crearTablaRetiros(listaTransacciones, listaRetiros);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar información de las cuentas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearTablaRetiros(List<Transaccion> listaTransacciones, List<Retiro> listaRetiros) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CUENTA");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("FECHA");
        modelo.addColumn("TIPO");
        modelo.addColumn("ESTADO");

        Object[] fila = new Object[5];
        for (Transaccion transaccion : listaTransacciones) {
            for (Retiro retiro : listaRetiros) {
                if (transaccion.getIdTransaccion() == (retiro.getIdTransaccion())) {
                    fila[0] = transaccion.getNumCuenta();
                }
                fila[1] = retiro.getCantidad();
                fila[2] = retiro.getFecha();
                fila[3] = "Retiro";
                if (retiro.getEstado() == 1) {
                    fila[4] = "No cobrado";
                } else if (retiro.getEstado() == 2) {
                    fila[4] = "Cobrado";
                } else if (retiro.getEstado() == 3) {
                    fila[4] = "Cancelado";
                }

                modelo.addRow(fila);
            }
        }

        jTablaHistorial.setModel(modelo);
        jTablaHistorial.getTableHeader().setReorderingAllowed(false);
        jTablaHistorial.setDefaultEditor(Object.class, null);
        jTablaHistorial.getTableHeader().setResizingAllowed(false);
        JTableHeader header = jTablaHistorial.getTableHeader();
        header.setFont(new Font("Leelawadee UI", Font.BOLD, 12));
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
        btnRefrescar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        cbxCuenta = new javax.swing.JComboBox<>();
        jDateFechaInicio = new com.toedter.calendar.JDateChooser();
        lblCuentaRetiro = new javax.swing.JLabel();
        lblCuentaRetiro1 = new javax.swing.JLabel();
        lblCuentaRetiro2 = new javax.swing.JLabel();
        jDateFechaFin = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaHistorial = new javax.swing.JTable();
        rbtnTransferencia = new javax.swing.JRadioButton();
        rbtnRetiro = new javax.swing.JRadioButton();
        rbtnTodas = new javax.swing.JRadioButton();
        rbtnFechas = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Historial");
        setResizable(false);

        fondo.setBackground(new java.awt.Color(102, 153, 255));

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 80)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Historial");

        btnRefrescar.setBackground(new java.awt.Color(0, 102, 255));
        btnRefrescar.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnRefrescar.setForeground(new java.awt.Color(255, 255, 255));
        btnRefrescar.setText("Refrescar");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
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

        cbxCuenta.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N

        jDateFechaInicio.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N

        lblCuentaRetiro.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblCuentaRetiro.setForeground(new java.awt.Color(255, 255, 255));
        lblCuentaRetiro.setText("Cuenta:");

        lblCuentaRetiro1.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblCuentaRetiro1.setForeground(new java.awt.Color(255, 255, 255));
        lblCuentaRetiro1.setText("Fecha inicio:");

        lblCuentaRetiro2.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblCuentaRetiro2.setForeground(new java.awt.Color(255, 255, 255));
        lblCuentaRetiro2.setText("Fecha fin:");

        jDateFechaFin.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N

        jTablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTablaHistorial);

        rbtnTransferencia.setBackground(new java.awt.Color(102, 153, 255));
        rbtnTransferencia.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        rbtnTransferencia.setForeground(new java.awt.Color(255, 255, 255));
        rbtnTransferencia.setText("Transferencia");

        rbtnRetiro.setBackground(new java.awt.Color(102, 153, 255));
        rbtnRetiro.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        rbtnRetiro.setForeground(new java.awt.Color(255, 255, 255));
        rbtnRetiro.setText("Retiro");
        rbtnRetiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRetiroActionPerformed(evt);
            }
        });

        rbtnTodas.setBackground(new java.awt.Color(102, 153, 255));
        rbtnTodas.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        rbtnTodas.setForeground(new java.awt.Color(255, 255, 255));
        rbtnTodas.setText("Todas");
        rbtnTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnTodasActionPerformed(evt);
            }
        });

        rbtnFechas.setBackground(new java.awt.Color(102, 153, 255));
        rbtnFechas.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        rbtnFechas.setForeground(new java.awt.Color(255, 255, 255));
        rbtnFechas.setText("Buscar por fechas");
        rbtnFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFechasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jLabel2))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lblCuentaRetiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCuentaRetiro1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCuentaRetiro2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rbtnTransferencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnRetiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnTodas)
                        .addGap(383, 383, 383)))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rbtnFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCuentaRetiro)
                        .addComponent(lblCuentaRetiro1))
                    .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateFechaInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateFechaFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCuentaRetiro2, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(18, 18, 18)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnTransferencia)
                    .addComponent(rbtnRetiro)
                    .addComponent(rbtnTodas)
                    .addComponent(rbtnFechas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefrescar)
                    .addComponent(btnSalir))
                .addGap(38, 38, 38))
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

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        try {
            cuenta = datosConexion.getCuentaDAO().obtener(String.valueOf(cbxCuenta.getSelectedItem()));
            if (rbtnTodas.isSelected()) {
                if (rbtnFechas.isSelected()) {
                    if (jDateFechaInicio.getDate() != null && jDateFechaFin.getDate() != null) {
                        mostrarTablaTodasPorFechas(cuenta);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingresa las fechas");
                    }
                } else {
                    mostrarTablaTodas(cuenta);
                }
            } else if (rbtnTransferencia.isSelected()) {
                if (rbtnFechas.isSelected()) {
                    if (jDateFechaInicio.getDate() != null && jDateFechaFin.getDate() != null) {
                        mostrarTablaTransferenciasPorFechas(cuenta);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingresa las fechas");
                    }
                } else {
                    mostrarTablaTransferencias(cuenta);
                }
            } else if (rbtnRetiro.isSelected()) {
                if (rbtnFechas.isSelected()) {
                    if (jDateFechaInicio.getDate() != null && jDateFechaFin.getDate() != null) {
                        mostrarTablaRetirosPorFechas(cuenta);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingresa las fechas");
                    }
                } else {
                    mostrarTablaRetiros(cuenta);
                }
            } else if (rbtnRetiro.isSelected()) {
                if (rbtnFechas.isSelected()) {
                    if (jDateFechaInicio.getDate() != null && jDateFechaFin.getDate() != null) {
                        mostrarTablaRetirosPorFechas(cuenta);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingresa las fechas");
                    }
                } else {
                    mostrarTablaRetiros(cuenta);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una opción");
            }

        } catch (PersistenciaException ex) {
            Logger.getLogger(dlgHistorial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void rbtnRetiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRetiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnRetiroActionPerformed

    private void rbtnTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnTodasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnTodasActionPerformed

    private void rbtnFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFechasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnFechasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxCuenta;
    private javax.swing.JPanel fondo;
    private com.toedter.calendar.JDateChooser jDateFechaFin;
    private com.toedter.calendar.JDateChooser jDateFechaInicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaHistorial;
    private javax.swing.JLabel lblCuentaRetiro;
    private javax.swing.JLabel lblCuentaRetiro1;
    private javax.swing.JLabel lblCuentaRetiro2;
    private javax.swing.JRadioButton rbtnFechas;
    private javax.swing.JRadioButton rbtnRetiro;
    private javax.swing.JRadioButton rbtnTodas;
    private javax.swing.JRadioButton rbtnTransferencia;
    // End of variables declaration//GEN-END:variables
}
