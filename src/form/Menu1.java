/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package form;

/**
 *
 * @author User
 */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

public class Menu1 extends javax.swing.JInternalFrame {
    public Statement st;
    public ResultSet rs;
    Connection cn = koneksi.KoneksiDatabase.BukaKoneksi();
    JFrame parentComponent;

    /**
     * Creates new form Menu1
     *
     * @param parentComponentParam
     */
    public Menu1(JFrame parentComponentParam) {
        parentComponent = parentComponentParam;
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        
        TampilDataUser();
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row){
                System.out.println("Edit row: " + row);
            }
            
            @Override
            public void onDelete(int row){
                if(UserTable.isEditing()) {
                    UserTable.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) UserTable.getModel();
                String nomorAkun = model.getValueAt(row, 0).toString();
                System.out.println(nomorAkun);
                try {
                    DeleteUserUsingNomorAkun(nomorAkun);
//                model.removeRow(row);
                } catch (SQLException ex) {
                    Logger.getLogger(Menu1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        UserTable.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        UserTable.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
    }
    
    private void DeleteUserUsingNomorAkun(String nomorAkun) throws SQLException {
        String query = "DELETE FROM akun WHERE nomor_akun = ?";
        PreparedStatement statement = cn.prepareStatement(query);
        
        statement.setString(1, nomorAkun);
        
        int confirmation = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin akan MENGHAPUS DATA AKUN?", "WASPADA!", JOptionPane.YES_NO_OPTION);
        
        if(confirmation == JOptionPane.YES_OPTION) {
            statement.execute();
            JOptionPane.showMessageDialog(null, "Berhasil menghapus akun!");
        }
    }
    
    private void addAkun(String username, String password, String nama_lengkap, String alamat, String tanggal_lahir, String role) throws SQLException {
        boolean userExists = isUsernameDuplicateForAkun(username);

        if (userExists) {
            JOptionPane.showMessageDialog(null, "Username \"" + username + "\" sudah ada!");
        } else {
            long number = (long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L;
            String nomorAkun = String.valueOf(number);
            st = cn.createStatement();
            String query = "INSERT INTO akun (nomor_akun, username, password, nama_lengkap, alamat, tanggal_lahir, role) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = cn.prepareStatement(query);
            statement.setString(1, nomorAkun);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, nama_lengkap);
            statement.setString(5, alamat);
            statement.setString(6, tanggal_lahir);
            statement.setString(7, role);
            statement.execute();
            
            this.dispose();
            JOptionPane.showMessageDialog(null, "Berhasil Menambahan Akun baru!");
        }
    }
    
    private void TampilDataUser() {
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM akun");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nomor Akun");
            model.addColumn("Username");
            model.addColumn("Password");
            model.addColumn("Nama Lengkap");            
            model.addColumn("Alamat");
            model.addColumn("Role");
            model.addColumn("Actions");
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            UserTable.setRowSelectionAllowed(true);
            
            
            
            while (rs.next()) {
                Object[] data = {
                    rs.getString("nomor_akun"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nama_lengkap"),
                    rs.getString("alamat"),
                    rs.getString("role"),
                };
                
                model.addRow(data);
                UserTable.setModel(model);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        UserTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 660));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 660));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        UserTable.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        UserTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nomor Akun", "Username", "Password", "Nama Lengkap", "Alamat", "Tanggal Lahir"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        UserTable.setGridColor(new java.awt.Color(255, 147, 2));
        UserTable.setRowHeight(40);
        UserTable.setRowSelectionAllowed(false);
        UserTable.setSelectionBackground(new java.awt.Color(255, 249, 235));
        UserTable.setSelectionForeground(new java.awt.Color(1, 1, 1));
        UserTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(UserTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 910, 540));

        jButton1.setBackground(new java.awt.Color(254, 254, 254));
        jButton1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jButton1.setText("Tambah Akun");
        jButton1.setToolTipText("");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusable(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 150, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        TambahAkun tambahAkun = new TambahAkun(cn);

        tambahAkun.setVisible(true);
        parentComponent.setEnabled(false);

        tambahAkun.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parentComponent.setEnabled(true);
            }
        });
        
        parentComponent.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable UserTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
