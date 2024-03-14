/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.cell;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class TableActionCellEditor extends DefaultCellEditor {
    final private TableActionEvent event;
    public TableActionCellEditor(TableActionEvent Event) {
        super(new JCheckBox());
        event = Event;
    }
    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column){
        PanelAction action = new PanelAction();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
