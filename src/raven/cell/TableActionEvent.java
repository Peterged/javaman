/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.cell;

/**
 *
 * @author User
 */
abstract public class TableActionEvent {
    abstract public void onEdit(int row);
    abstract public void onDelete(int row);
}
