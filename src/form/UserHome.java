/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package form;

import java.util.regex.*;

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

public class UserHome extends javax.swing.JInternalFrame {

    public Statement st;
    public ResultSet rs;
    Connection cn = koneksi.KoneksiDatabase.BukaKoneksi();
    JFrame parentComponent;
    String NomorAkun;

    /**
     * Creates new form Menu1
     *
     * @param parentComponentParam
     * @param nomorAkun
     */
    public UserHome(JFrame parentComponentParam, String nomorAkun) throws SQLException {
        parentComponent = parentComponentParam;
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        NomorAkun = nomorAkun;
        
        st = cn.createStatement();
    }

    private void Deposit(String nomorAkun, double jumlah) throws SQLException {
        ResultSet result = getAccount(nomorAkun);

        if (result != null) {
            double currentBalance = getBalance(nomorAkun);
            double totalBalance = currentBalance + jumlah;
            String query = "UPDATE user SET balance = ? WHERE no_akun = ?";
            PreparedStatement statement = cn.prepareStatement(query);
            statement.setDouble(1, totalBalance);
            statement.setString(2, nomorAkun);
            statement.execute();

            JOptionPane.showMessageDialog(null, "Berhasil Deposit!");
            
            balanceLabel.setText(Double.toString(totalBalance));
        } else {
            System.out.println("ADDING NEW USER!");
            String query = "INSERT INTO user (no_akun, balance) VALUES (?, ?)";
            PreparedStatement statement = cn.prepareStatement(query);
            statement.setString(1, nomorAkun);
            statement.setDouble(2, jumlah);

            statement.execute();
            JOptionPane.showMessageDialog(null, "Berhasil deposit!");
            
            balanceLabel.setText(Double.toString(jumlah));
        }
    }

    public double getBalance(String nomorAkun) throws SQLException {

        String query = "SELECT balance FROM user WHERE no_akun = '" + nomorAkun + "'";
        rs = st.executeQuery(query);
        double balance = 0;
        if (rs.next()) {
            balance = rs.getDouble("balance");
        }
        return balance;
    }

    public ResultSet getAccount(String nomorAkun) throws SQLException {
        String query = "SELECT * FROM user WHERE no_akun = '" + nomorAkun + "'";

        rs = st.executeQuery(query);
        ResultSet result = null;
        if (rs.next()) {
            result = rs;
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, akun tidak ditemukan!");
        }
        return result;
    }

    private double Withdraw(String nomorAkun, double jumlahYangAkanDitarik) throws SQLException {
        double balance = getBalance(nomorAkun);
        double newBalance = balance - jumlahYangAkanDitarik;

        if (newBalance < 0) {
            JOptionPane.showMessageDialog(null, "Kebanyakan wdnya woi!");
        } else {
            String query = "UPDATE user SET balance = ? WHERE no_akun = ?";
            PreparedStatement statement = cn.prepareStatement(query);

            statement.setDouble(1, newBalance);
            statement.setString(2, nomorAkun);
            statement.execute();
            balanceLabel.setText(Double.toString(newBalance));
        }
        return newBalance;
    }

//    private void addAkun(String username, String password, String nama_lengkap, String alamat, String tanggal_lahir, String role) throws SQLException {
//        boolean userExists = isUsernameDuplicateForAkun(username);
//
//        if (userExists) {
//            JOptionPane.showMessageDialog(null, "Username \"" + username + "\" sudah ada!");
//        } else {
//            long number = (long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L;
//            String nomorAkun = String.valueOf(number);
//            st = cn.createStatement();
//            String query = "INSERT INTO akun (nomor_akun, username, password, nama_lengkap, alamat, tanggal_lahir, role) VALUES(?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement statement = cn.prepareStatement(query);
//            statement.setString(1, nomorAkun);
//            statement.setString(2, username);
//            statement.setString(3, password);
//            statement.setString(4, nama_lengkap);
//            statement.setString(5, alamat);
//            statement.setString(6, tanggal_lahir);
//            statement.setString(7, role);
//            statement.execute();
//
//            this.dispose();
//            JOptionPane.showMessageDialog(null, "Berhasil Menambahan Akun baru!");
//        }
//    }
//    private void TampilDataUser() {
//        try {
//            st = cn.createStatement();
//            rs = st.executeQuery("SELECT * FROM akun");
//            DefaultTableModel model = new DefaultTableModel();
//            model.addColumn("Nomor Akun");
//            model.addColumn("Username");
//            model.addColumn("Password");
//            model.addColumn("Nama Lengkap");
//            model.addColumn("Alamat");
//            model.addColumn("Role");
//            model.addColumn("Actions");
//            model.getDataVector().removeAllElements();
//            model.fireTableDataChanged();
//            model.setRowCount(0);
//            UserTable.setRowSelectionAllowed(true);
//
//            while (rs.next()) {
//                Object[] data = {
//                    rs.getString("nomor_akun"),
//                    rs.getString("username"),
//                    rs.getString("password"),
//                    rs.getString("nama_lengkap"),
//                    rs.getString("alamat"),
//                    rs.getString("role"),};
//
//                model.addRow(data);
//                UserTable.setModel(model);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        balanceLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        withdrawField = new javax.swing.JTextField();
        depositField = new javax.swing.JTextField();
        withdrawLabel = new javax.swing.JLabel();
        depositLabel = new javax.swing.JLabel();
        withdrawBtn = new javax.swing.JButton();
        depositBtn = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 660));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 660));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        balanceLabel.setFont(new java.awt.Font("Montserrat", 0, 36)); // NOI18N
        balanceLabel.setText("0");
        jPanel1.add(balanceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        jLabel2.setText("Balance");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        withdrawField.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        withdrawField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawFieldActionPerformed(evt);
            }
        });
        jPanel1.add(withdrawField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 210, 30));

        depositField.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        depositField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositFieldActionPerformed(evt);
            }
        });
        jPanel1.add(depositField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 210, 30));

        withdrawLabel.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        withdrawLabel.setText("Jumlah Withdraw");
        jPanel1.add(withdrawLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        depositLabel.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        depositLabel.setText("Jumlah Deposit");
        jPanel1.add(depositLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        withdrawBtn.setBackground(new java.awt.Color(254, 254, 254));
        withdrawBtn.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        withdrawBtn.setText("Withdraw");
        withdrawBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        withdrawBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                withdrawBtnMouseClicked(evt);
            }
        });
        jPanel1.add(withdrawBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 120, 30));

        depositBtn.setBackground(new java.awt.Color(254, 254, 254));
        depositBtn.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        depositBtn.setText("Deposit");
        depositBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        depositBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                depositBtnMouseClicked(evt);
            }
        });
        jPanel1.add(depositBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 120, 30));

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

    private void withdrawFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_withdrawFieldActionPerformed

    private void depositFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_depositFieldActionPerformed

    private void depositBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositBtnMouseClicked
        Pattern p = Pattern.compile("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)");//. represents single character  
        String depositValue = depositField.getText();
        Matcher m = p.matcher(depositValue);
        boolean b = m.matches();

        if (!b) {
            JOptionPane.showMessageDialog(null, "Tolong jumlah deposit diketik dalam bentuk angka!");
        } else {
            try {
                Deposit(NomorAkun, Double.parseDouble(depositValue));
            } catch (SQLException ex) {
                Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_depositBtnMouseClicked

    private void withdrawBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_withdrawBtnMouseClicked
        Pattern p = Pattern.compile("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)");//. represents single character  
        String withdrawValue = depositField.getText();
        Matcher m = p.matcher(withdrawValue);
        boolean b = m.matches();

        if (!b) {
            JOptionPane.showMessageDialog(null, "Tolong jumlah withdraw diketik dalam bentuk angka!");
        } else {
            try {
                Withdraw(NomorAkun, Double.parseDouble(withdrawValue));
            } catch (SQLException ex) {
                Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_withdrawBtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JButton depositBtn;
    private javax.swing.JTextField depositField;
    private javax.swing.JLabel depositLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton withdrawBtn;
    private javax.swing.JTextField withdrawField;
    private javax.swing.JLabel withdrawLabel;
    // End of variables declaration//GEN-END:variables
}
