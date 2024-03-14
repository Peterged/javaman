/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

/**
 *
 * @author Yoga
 */
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class index extends javax.swing.JFrame {
    public boolean skipLogin = true;
    public String skipLoginUsername = "admin";
    public String skipLoginPassword = "admin";
    public Statement st;
    public ResultSet rs;
    Connection cn = koneksi.KoneksiDatabase.BukaKoneksi();

    public index() {
        initComponents();

        if (skipLogin) {
            String[] result = getUser(skipLoginUsername, skipLoginPassword);
            boolean exists = !result[0].isEmpty();
            if (!exists) {
                JOptionPane.showMessageDialog(null, "Username / Password Salah!");
                System.exit(0);
            } else {
                String role = result[1];
                String nomor_akun = result[2];

                Home home = new Home(nomor_akun, role);
                home.setVisible(true);

            }
        }
    }

    private void Bersih() {
        inputId.setText("");
        inputNama.setText("");
        inputPengarang.setText("");
        inputPenerbit.setText("");

        btnSimpan.setText("Simpan");
        inputId.setEditable(true);
    }

    private String[] getUser(String username, String password) {

        String role = "";
        String nomor_akun = "";
        boolean exists = false;
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT role, nomor_akun FROM akun WHERE username = '" + username + "' AND password = '" + password + "' LIMIT 1");

            while (rs.next()) {
                role = rs.getString("role");
                nomor_akun = rs.getString("nomor_akun");
                exists = true;
            }
        } catch (Exception e) {
        }
        String strExists = exists ? "1" : "";
        String[] result = {strExists, role, nomor_akun};
        return result;
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
        loginSubmit = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        usernameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Firebank | LOGIN");
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(440, 530));
        setPreferredSize(new java.awt.Dimension(445, 540));
        setResizable(false);
        setSize(new java.awt.Dimension(445, 540));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(430, 500));
        jPanel2.setPreferredSize(new java.awt.Dimension(430, 500));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginSubmit.setBackground(new java.awt.Color(255, 147, 2));
        loginSubmit.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        loginSubmit.setForeground(new java.awt.Color(255, 255, 255));
        loginSubmit.setText("LOGIN");
        loginSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginSubmitMouseClicked(evt);
            }
        });
        loginSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginSubmitActionPerformed(evt);
            }
        });
        jPanel2.add(loginSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 350, 50));

        jLabel6.setFont(new java.awt.Font("Montserrat Medium", 1, 44)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 147, 2));
        jLabel6.setText("Firebank");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 220, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Firebank_Logo_44x72.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        passwordField.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        passwordField.setForeground(new java.awt.Color(64, 64, 64));
        passwordField.setMargin(new java.awt.Insets(2, 15, 2, 6));
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        jPanel2.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 350, 50));

        usernameField.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        usernameField.setForeground(new java.awt.Color(64, 64, 64));
        usernameField.setMargin(new java.awt.Insets(3, 15, 2, 6));
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        jPanel2.add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 350, 50));

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel2.setText("Password");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 160, 20));

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        jLabel3.setText("Username");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 160, 20));

        getContentPane().add(jPanel2, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void loginSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginSubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginSubmitActionPerformed

    private void loginSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginSubmitMouseClicked
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (username.equals("")) {
            JOptionPane.showMessageDialog(null, "Username diperlukan!");
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, "Password Diperlukan!");
        } else {
            String[] result = getUser(username, password);
            boolean exists = !result[0].isEmpty() ? true : false;
            if (!exists) {
                JOptionPane.showMessageDialog(null, "Username / Password Salah!");
            } else {
                String role = result[1];
                String nomor_akun = result[2];
                this.dispose();
                Home home = new Home(nomor_akun, role);
                home.setVisible(true);
            }

        }
    }//GEN-LAST:event_loginSubmitMouseClicked

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
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        index idx = new index();
        if (!idx.skipLogin) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new index().setVisible(true);
                }
            });
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginSubmit;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
