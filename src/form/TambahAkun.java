/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class TambahAkun extends javax.swing.JFrame {

    /**
     * Creates new form TambahAkun
     */
    Connection cn;
    public Statement st;
    public ResultSet rs;

    public TambahAkun(Connection connection) {
        initComponents();
        cn = connection;
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }

    private boolean isUsernameDuplicateForAkun(String username) {
        boolean exists = false;
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT id FROM akun WHERE username = '" + username + "'");

            while (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return exists;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tanggalLahirField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        roleCombobox = new javax.swing.JComboBox<>();
        namaLengkapField = new javax.swing.JTextField();
        alamatField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        submitBtn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Daftar Akun Baru");
        setMinimumSize(new java.awt.Dimension(810, 540));
        setPreferredSize(new java.awt.Dimension(810, 540));
        setResizable(false);
        setSize(new java.awt.Dimension(809, 450));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setToolTipText("");
        jPanel2.setMinimumSize(new java.awt.Dimension(810, 550));
        jPanel2.setPreferredSize(new java.awt.Dimension(810, 550));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Montserrat Medium", 1, 30)); // NOI18N
        title.setForeground(new java.awt.Color(255, 147, 2));
        title.setText("Daftarkan Akun");
        jPanel2.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 260, -1));

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel3.setText("Alamat");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 160, 20));

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel4.setText("Nama Lengkap");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 160, 20));

        tanggalLahirField.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        tanggalLahirField.setForeground(new java.awt.Color(64, 64, 64));
        tanggalLahirField.setMargin(new java.awt.Insets(3, 6, 2, 6));
        tanggalLahirField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalLahirFieldActionPerformed(evt);
            }
        });
        jPanel2.add(tanggalLahirField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 350, 40));

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel5.setText("Tanggal Lahir");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 160, 20));

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel7.setText("Tipe Akun");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 160, 20));

        roleCombobox.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        roleCombobox.setForeground(new java.awt.Color(64, 64, 64));
        roleCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Admin" }));
        roleCombobox.setToolTipText("Tipe Akun");
        roleCombobox.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        roleCombobox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roleCombobox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                roleComboboxMousePressed(evt);
            }
        });
        jPanel2.add(roleCombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, 350, 40));

        namaLengkapField.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        namaLengkapField.setForeground(new java.awt.Color(64, 64, 64));
        namaLengkapField.setMargin(new java.awt.Insets(3, 6, 2, 6));
        namaLengkapField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaLengkapFieldActionPerformed(evt);
            }
        });
        jPanel2.add(namaLengkapField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 350, 40));

        alamatField.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        alamatField.setForeground(new java.awt.Color(64, 64, 64));
        alamatField.setMargin(new java.awt.Insets(3, 6, 2, 6));
        alamatField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatFieldActionPerformed(evt);
            }
        });
        jPanel2.add(alamatField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 350, 40));

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel6.setText("Username");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 160, 20));

        usernameField.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        usernameField.setForeground(new java.awt.Color(64, 64, 64));
        usernameField.setMargin(new java.awt.Insets(3, 6, 2, 6));
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        jPanel2.add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 350, 40));

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel8.setText("Password");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 160, 20));

        passwordField.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        passwordField.setForeground(new java.awt.Color(64, 64, 64));
        passwordField.setMargin(new java.awt.Insets(2, 15, 2, 6));
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        jPanel2.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 350, 40));

        submitBtn1.setBackground(new java.awt.Color(255, 147, 2));
        submitBtn1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        submitBtn1.setForeground(new java.awt.Color(255, 255, 255));
        submitBtn1.setText("DAFTAR");
        submitBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitBtn1MouseClicked(evt);
            }
        });
        submitBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtn1ActionPerformed(evt);
            }
        });
        jPanel2.add(submitBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 730, 50));

        getContentPane().add(jPanel2, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tanggalLahirFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalLahirFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalLahirFieldActionPerformed

    private void namaLengkapFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaLengkapFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaLengkapFieldActionPerformed

    private void alamatFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatFieldActionPerformed

    private void roleComboboxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roleComboboxMousePressed
        roleCombobox.setSelectedIndex(1);
    }//GEN-LAST:event_roleComboboxMousePressed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void submitBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitBtn1ActionPerformed

    private void submitBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitBtn1MouseClicked
        String namaLengkap = namaLengkapField.getText();
        String alamat = alamatField.getText();
        String tanggalLahir = tanggalLahirField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = roleCombobox.getItemAt(roleCombobox.getSelectedIndex()).toLowerCase();

        String regex = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tanggalLahir);

        if (username.equals("")) {
            JOptionPane.showMessageDialog(null, "Username diperlukan!");
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, "Password diperlukan!");
        } else if (namaLengkap.equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Lengkap diperlukan!");
        } else if (alamat.equals("")) {
            JOptionPane.showMessageDialog(null, "Alamat diperlukan!");
        } else if (tanggalLahir.equals("")) {
            JOptionPane.showMessageDialog(null, "Tanggal Lahir diperlukan!");
        } else if (!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Format Tanggal Lahir salah! Tahun-Bulan-Hari -> 2006-10-27");
        } else {
            try {
                addAkun(username, password, namaLengkap, alamat, tanggalLahir, role);
            } catch (SQLException ex) {
                Logger.getLogger(TambahAkun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_submitBtn1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TambahAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahAkun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamatField;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField namaLengkapField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JComboBox<String> roleCombobox;
    private javax.swing.JButton submitBtn1;
    private javax.swing.JTextField tanggalLahirField;
    private javax.swing.JLabel title;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables

}
