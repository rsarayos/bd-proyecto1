package org.itson.bdavanzadas.bancobd.buttonColumn;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * Representa la clase ButtonColumn que nos permite generar botones en las
 * celdas de una tabla.
 *
 * Un objeto de la clase ButtonColumn contiene los siguientes atributos:
 *
 *      renderButton: Componente para renderizar el botón 
 *      editButton: Componente para editar el botón 
 *      currentValue: Valor actual del botón
 *
 * La clase proporciona constructores para instanciar objetos de ButtonColumn, y
 * algunos métodos para renderizar y otro para editar el botón en una celda.
 *
 * @author Victor Humberto Encinas Guzman & Raul Alejandro Sauceda Rayos
 */
public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private final JButton renderButton;
    private final JButton editButton;
    private Object currentValue;

    /**
     * Constructor por defecto. Permite inicializar los valores del texto del
     * botón y el método oyente
     *
     * @param buttonText Texto del botón
     * @param actionListener Método oyente del botón
     */
    public ButtonColumn(String buttonText, ActionListener actionListener) {
        renderButton = new JButton(buttonText);
        renderButton.setFocusPainted(false);

        editButton = new JButton(buttonText);
        editButton.setFocusPainted(false);
        editButton.addActionListener(actionListener);
    }

    /**
     * Método que devuelve un componente para renderizar el botón dentro de la 
     * tabla especificada.
     * 
     * @param table      la tabla que contiene la celda
     * @param value      el valor contenido en la celda
     * @param isSelected un indicador de si la celda está seleccionada
     * @param hasFocus   un indicador de si la celda tiene el foco
     * @param row        el índice de la fila de la celda
     * @param column     el índice de la columna de la celda
     * @return un componente para renderizar el botón dentro de la celda
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (hasFocus) {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(table.getBackground());
        } else if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(table.getBackground());
        }

        renderButton.setText((value == null) ? "" : value.toString());
        return renderButton;
    }

    /**
     * Método que devuelve un componente para editar el botón dentro de la tabla especificada.
     *
     * @param table      la tabla que contiene la celda
     * @param value      el valor contenido en la celda
     * @param isSelected un indicador de si la celda está seleccionada
     * @param row        el índice de la fila de la celda
     * @param column     el índice de la columna de la celda
     * @return un componente para editar el botón dentro de la celda
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentValue = value;
        editButton.setText((value == null) ? "" : value.toString());
        return editButton;
    }

    /**
     * Método que devuelve el valor actual del botón.
     *
     * @return el valor actual del botón
     */
    @Override
    public Object getCellEditorValue() {
        return currentValue;
    }

}
