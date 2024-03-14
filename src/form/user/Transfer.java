/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form.user;

import form.*;
import java.awt.Color;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Transfer extends javax.swing.JFrame {

    /**
     * Creates new form TambahAkun
     */
    Connection cn;
    public Statement st;
    public ResultSet rs;
    public String NomorAkun;
    public String lastNomorAkun = "";
    public int minimalJumlahTransfer = 5000;

    public Color selectedText = new Color(78, 202, 255);

    public Transfer(Connection connection, String nomorAkun) {
        initComponents();
        setTitle("Deposit");
        cn = connection;
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        NomorAkun = nomorAkun;
        depositButtonSubmit.setEnabled(false);
    }

    private boolean isUsernameDuplicateForAkun(String username) {
        boolean exists = false;
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT id FROM akun WHERE username = '" + username + "'");

            while (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return exists;
    }

    private boolean doesAccountExists(String nomorAkun) {
        boolean exists = false;
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT username, nomorAkun, role FROM akun WHERE nomor_akun = '" + nomorAkun + "'");

            while (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exists;
    }

    private Transfer() {
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

    private void validateNomorRekeningForTextInput(String nomorAkun) {
        int textLength = nomorAkun.length();
        boolean isEnabled = true;
        if (textLength < 10) {
            isEnabled = false;
        }
        checkAccountButton.setEnabled(isEnabled);

        if (lastNomorAkun.equals(nomorAkun)) {
            depositButtonSubmit.setEnabled(true);
            checkAccountButton.setEnabled(false);
            checkAccountButton.setBackground(new Color(0, 107, 153));
        }
    }

    private void validateFormForSubmitButton(String nomorAkun, double jumlah) {

        lastNomorAkun = lastNomorAkun.equals(nomorAkun) ? lastNomorAkun : nomorAkun;
        boolean isLastNomorAkunTheSameWithNomorAkun = lastNomorAkun.equals(nomorAkun);
        if (isLastNomorAkunTheSameWithNomorAkun && jumlah >= minimalJumlahTransfer) {
            depositButtonSubmit.setEnabled(true);
            checkAccountButton.setEnabled(false);
            checkAccountButton.setBackground(new Color(0, 107, 153));
        } else if (!isLastNomorAkunTheSameWithNomorAkun) {
            checkAccountButton.setEnabled(true);
            checkAccountButton.setBackground(new Color(78, 202, 255));
        } 
        if (jumlah < minimalJumlahTransfer) {
            JOptionPane.showMessageDialog(null, "Jumlah transfer minimal Rp " + minimalJumlahTransfer);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        depositButtonSubmit = new raven.swing.buttons.ButtonRounded55Panel();
        jLabel4 = new javax.swing.JLabel();
        depositButtonPanel500 = new javax.swing.JPanel();
        depositButtonPanelLabel500 = new javax.swing.JLabel();
        depositButtonPanel100 = new javax.swing.JPanel();
        depositButtonPanelLabel100 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        depositButtonPanel50 = new javax.swing.JPanel();
        depositButtonPanelLabel50 = new javax.swing.JLabel();
        depositButtonPanel200 = new javax.swing.JPanel();
        depositButtonPanelLabel200 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        totalDepositLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        depositAmountField = new javax.swing.JTextField();
        checkAccountButton = new raven.swing.buttons.ButtonRounded30Panel();
        jLabel1 = new javax.swing.JLabel();
        nomorRekeningField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Daftar Akun Baru");
        setMinimumSize(new java.awt.Dimension(500, 749));
        setPreferredSize(new java.awt.Dimension(500, 799));
        setResizable(false);
        setSize(new java.awt.Dimension(500, 799));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setToolTipText("");
        jPanel2.setMinimumSize(new java.awt.Dimension(500, 799));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 799));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        title1.setFont(new java.awt.Font("Inter Medium", 1, 30)); // NOI18N
        title1.setForeground(new java.awt.Color(78, 202, 255));
        title1.setText("Transfer");

        title.setFont(new java.awt.Font("Inter Medium", 1, 30)); // NOI18N
        title.setForeground(new java.awt.Color(255, 205, 89));
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logos/BLUE-Firebank_Logo_28x45.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(title1)
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 24, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 70));

        depositButtonSubmit.setBackground(new java.awt.Color(78, 202, 255));
        depositButtonSubmit.setEnabled(false);
        depositButtonSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                depositButtonSubmitMousePressed(evt);
            }
        });
        depositButtonSubmit.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TRANSFER");
        depositButtonSubmit.add(jLabel4, new java.awt.GridBagConstraints());

        jPanel2.add(depositButtonSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 660, 360, 50));

        depositButtonPanel500.setBackground(new java.awt.Color(255, 255, 255));
        depositButtonPanel500.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(78, 202, 255), 1, true));
        depositButtonPanel500.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        depositButtonPanel500.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                depositButtonPanel500MouseClicked(evt);
            }
        });
        depositButtonPanel500.setLayout(new java.awt.GridBagLayout());

        depositButtonPanelLabel500.setFont(new java.awt.Font("Inter Medium", 0, 18)); // NOI18N
        depositButtonPanelLabel500.setForeground(new java.awt.Color(78, 202, 255));
        depositButtonPanelLabel500.setText("Rp 500.000");
        depositButtonPanelLabel500.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                depositButtonPanelLabel500MouseClicked(evt);
            }
        });
        depositButtonPanel500.add(depositButtonPanelLabel500, new java.awt.GridBagConstraints());

        jPanel2.add(depositButtonPanel500, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 170, 70));

        depositButtonPanel100.setBackground(new java.awt.Color(255, 255, 255));
        depositButtonPanel100.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(78, 202, 255), 1, true));
        depositButtonPanel100.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        depositButtonPanel100.setPreferredSize(new java.awt.Dimension(170, 70));
        depositButtonPanel100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                depositButtonPanel100MouseClicked(evt);
            }
        });
        depositButtonPanel100.setLayout(new java.awt.GridBagLayout());

        depositButtonPanelLabel100.setFont(new java.awt.Font("Inter Medium", 0, 18)); // NOI18N
        depositButtonPanelLabel100.setForeground(new java.awt.Color(78, 202, 255));
        depositButtonPanelLabel100.setText("Rp 100.000");
        depositButtonPanelLabel100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                depositButtonPanelLabel100MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 38, 22, 42);
        depositButtonPanel100.add(depositButtonPanelLabel100, gridBagConstraints);

        jPanel2.add(depositButtonPanel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 170, 70));

        jLabel11.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel11.setText("Nomor Rekening");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 160, 20));

        depositButtonPanel50.setBackground(new java.awt.Color(255, 255, 255));
        depositButtonPanel50.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(78, 202, 255), 1, true));
        depositButtonPanel50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        depositButtonPanel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                depositButtonPanel50MouseClicked(evt);
            }
        });
        depositButtonPanel50.setLayout(new java.awt.GridBagLayout());

        depositButtonPanelLabel50.setFont(new java.awt.Font("Inter Medium", 0, 18)); // NOI18N
        depositButtonPanelLabel50.setForeground(new java.awt.Color(78, 202, 255));
        depositButtonPanelLabel50.setText("Rp 50.000");
        depositButtonPanelLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                depositButtonPanelLabel50MouseClicked(evt);
            }
        });
        depositButtonPanel50.add(depositButtonPanelLabel50, new java.awt.GridBagConstraints());

        jPanel2.add(depositButtonPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 170, 70));

        depositButtonPanel200.setBackground(new java.awt.Color(255, 255, 255));
        depositButtonPanel200.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(78, 202, 255), 1, true));
        depositButtonPanel200.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        depositButtonPanel200.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                depositButtonPanel200MouseClicked(evt);
            }
        });
        depositButtonPanel200.setLayout(new java.awt.GridBagLayout());

        depositButtonPanelLabel200.setFont(new java.awt.Font("Inter Medium", 0, 18)); // NOI18N
        depositButtonPanelLabel200.setForeground(new java.awt.Color(78, 202, 255));
        depositButtonPanelLabel200.setText("Rp 200.000");
        depositButtonPanelLabel200.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                depositButtonPanelLabel200MouseClicked(evt);
            }
        });
        depositButtonPanel200.add(depositButtonPanelLabel200, new java.awt.GridBagConstraints());

        jPanel2.add(depositButtonPanel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 170, 70));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        totalDepositLabel.setFont(new java.awt.Font("Inter Medium", 0, 28)); // NOI18N
        totalDepositLabel.setForeground(new java.awt.Color(78, 202, 255));
        totalDepositLabel.setText("Rp 0");
        jPanel6.add(totalDepositLabel, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 500, 50));

        jSeparator1.setBackground(new java.awt.Color(250, 250, 250));
        jSeparator1.setForeground(new java.awt.Color(250, 250, 250));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 500, 10));

        jLabel12.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel12.setText("Jumlah transfer");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 120, 20));

        depositAmountField.setFont(new java.awt.Font("Inter Medium", 0, 16)); // NOI18N
        depositAmountField.setForeground(new java.awt.Color(50, 50, 50));
        depositAmountField.setText("0");
        depositAmountField.setMargin(new java.awt.Insets(2, 12, 2, 56));
        depositAmountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositAmountFieldActionPerformed(evt);
            }
        });
        depositAmountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                depositAmountFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                depositAmountFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                depositAmountFieldKeyTyped(evt);
            }
        });
        jPanel2.add(depositAmountField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 360, 50));

        checkAccountButton.setBackground(new java.awt.Color(78, 202, 255));
        checkAccountButton.setEnabled(false);
        checkAccountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkAccountButtonMouseClicked(evt);
            }
        });
        checkAccountButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkAccountButtonKeyPressed(evt);
            }
        });
        checkAccountButton.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Inter SemiBold", 0, 13)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("CEK");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        checkAccountButton.add(jLabel1, new java.awt.GridBagConstraints());

        jPanel2.add(checkAccountButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 550, 80, 30));

        nomorRekeningField.setFont(new java.awt.Font("Inter Medium", 0, 16)); // NOI18N
        nomorRekeningField.setForeground(new java.awt.Color(50, 50, 50));
        nomorRekeningField.setMargin(new java.awt.Insets(2, 12, 2, 6));
        nomorRekeningField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomorRekeningFieldActionPerformed(evt);
            }
        });
        nomorRekeningField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomorRekeningFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomorRekeningFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomorRekeningFieldKeyTyped(evt);
            }
        });
        jPanel2.add(nomorRekeningField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, 360, 50));

        getContentPane().add(jPanel2, new java.awt.GridBagConstraints());

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void depositButtonPanel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositButtonPanel50MouseClicked
        Color selectedColor = selectedText;
        depositButtonPanel50.setBackground(selectedColor);
        depositButtonPanelLabel50.setForeground(Color.WHITE);
        depositButtonPanel100.setBackground(Color.WHITE);
        depositButtonPanelLabel100.setForeground(selectedColor);
        depositButtonPanel200.setBackground(Color.WHITE);
        depositButtonPanelLabel200.setForeground(selectedColor);
        depositButtonPanel500.setBackground(Color.WHITE);
        depositButtonPanelLabel500.setForeground(selectedColor);
        totalDepositLabel.setText("Rp 50.000");
    }//GEN-LAST:event_depositButtonPanel50MouseClicked

    private void depositButtonPanelLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositButtonPanelLabel50MouseClicked
        Color selectedColor = selectedText;
        depositButtonPanel50.setBackground(selectedColor);
        depositButtonPanelLabel50.setForeground(Color.WHITE);
        depositButtonPanel100.setBackground(Color.WHITE);
        depositButtonPanelLabel100.setForeground(selectedColor);
        depositButtonPanel200.setBackground(Color.WHITE);
        depositButtonPanelLabel200.setForeground(selectedColor);
        depositButtonPanel500.setBackground(Color.WHITE);
        depositButtonPanelLabel500.setForeground(selectedColor);
        totalDepositLabel.setText("Rp 50.000");
    }//GEN-LAST:event_depositButtonPanelLabel50MouseClicked

    private void depositButtonPanel100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositButtonPanel100MouseClicked
        Color selectedColor = selectedText;
        // Rp 50.000
        depositButtonPanel50.setBackground(Color.WHITE);
        depositButtonPanelLabel50.setForeground(selectedColor);

        // Rp 100.000
        depositButtonPanel100.setBackground(selectedColor);
        depositButtonPanelLabel100.setForeground(Color.WHITE);

        // Rp 200.000
        depositButtonPanel200.setBackground(Color.WHITE);
        depositButtonPanelLabel200.setForeground(selectedColor);

        // Rp 500.000
        depositButtonPanel500.setBackground(Color.WHITE);
        depositButtonPanelLabel500.setForeground(selectedColor);

        // SET TOTAL DEPOSIT TO "Rp 100.000"
        totalDepositLabel.setText("Rp 100.000");
    }//GEN-LAST:event_depositButtonPanel100MouseClicked

    private void depositButtonPanelLabel100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositButtonPanelLabel100MouseClicked
        Color selectedColor = selectedText;
        // Rp 50.000
        depositButtonPanel50.setBackground(Color.WHITE);
        depositButtonPanelLabel50.setForeground(selectedColor);

        // Rp 100.000
        depositButtonPanel100.setBackground(selectedColor);
        depositButtonPanelLabel100.setForeground(Color.WHITE);

        // Rp 200.000
        depositButtonPanel200.setBackground(Color.WHITE);
        depositButtonPanelLabel200.setForeground(selectedColor);

        // Rp 500.000
        depositButtonPanel500.setBackground(Color.WHITE);
        depositButtonPanelLabel500.setForeground(selectedColor);

        // SET TOTAL DEPOSIT TO "Rp 100.000"
        totalDepositLabel.setText("Rp 100.000");
    }//GEN-LAST:event_depositButtonPanelLabel100MouseClicked

    private void depositButtonPanel200MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositButtonPanel200MouseClicked
        Color selectedColor = selectedText;
        // Rp 50.000
        depositButtonPanel50.setBackground(Color.WHITE);
        depositButtonPanelLabel50.setForeground(selectedColor);

        // Rp 100.000
        depositButtonPanel100.setBackground(Color.WHITE);
        depositButtonPanelLabel100.setForeground(selectedColor);

        // Rp 200.000
        depositButtonPanel200.setBackground(selectedColor);
        depositButtonPanelLabel200.setForeground(Color.WHITE);

        // Rp 500.000
        depositButtonPanel500.setBackground(Color.WHITE);
        depositButtonPanelLabel500.setForeground(selectedColor);

        // SET TOTAL DEPOSIT TO "Rp 200.000"
        totalDepositLabel.setText("Rp 200.000");
    }//GEN-LAST:event_depositButtonPanel200MouseClicked

    private void depositButtonPanelLabel200MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositButtonPanelLabel200MouseClicked
        Color selectedColor = selectedText;
        // Rp 50.000
        depositButtonPanel50.setBackground(Color.WHITE);
        depositButtonPanelLabel50.setForeground(selectedColor);

        // Rp 100.000
        depositButtonPanel100.setBackground(Color.WHITE);
        depositButtonPanelLabel100.setForeground(selectedColor);

        // Rp 200.000
        depositButtonPanel200.setBackground(selectedColor);
        depositButtonPanelLabel200.setForeground(Color.WHITE);

        // Rp 500.000
        depositButtonPanel500.setBackground(Color.WHITE);
        depositButtonPanelLabel500.setForeground(selectedColor);

        // SET TOTAL DEPOSIT TO "Rp 200.000"
        totalDepositLabel.setText("Rp 200.000");
    }//GEN-LAST:event_depositButtonPanelLabel200MouseClicked

    private void depositButtonPanel500MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositButtonPanel500MouseClicked
        Color selectedColor = selectedText;
        // Rp 50.000
        depositButtonPanel50.setBackground(Color.WHITE);
        depositButtonPanelLabel50.setForeground(selectedColor);

        // Rp 100.000
        depositButtonPanel100.setBackground(Color.WHITE);
        depositButtonPanelLabel100.setForeground(selectedColor);

        // Rp 200.000
        depositButtonPanel200.setBackground(Color.WHITE);
        depositButtonPanelLabel200.setForeground(selectedColor);

        // Rp 500.000
        depositButtonPanel500.setBackground(selectedColor);
        depositButtonPanelLabel500.setForeground(Color.WHITE);

        // SET TOTAL DEPOSIT TO "Rp 500.000"
        totalDepositLabel.setText("Rp 500.000");
    }//GEN-LAST:event_depositButtonPanel500MouseClicked

    private void depositButtonPanelLabel500MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositButtonPanelLabel500MouseClicked
        Color selectedColor = selectedText;
        // Rp 50.000
        depositButtonPanel50.setBackground(Color.WHITE);
        depositButtonPanelLabel50.setForeground(selectedColor);

        // Rp 100.000
        depositButtonPanel100.setBackground(Color.WHITE);
        depositButtonPanelLabel100.setForeground(selectedColor);

        // Rp 200.000
        depositButtonPanel200.setBackground(Color.WHITE);
        depositButtonPanelLabel200.setForeground(selectedColor);

        // Rp 500.000
        depositButtonPanel500.setBackground(selectedColor);
        depositButtonPanelLabel500.setForeground(Color.WHITE);

        // SET TOTAL DEPOSIT TO "Rp 500.000"
        totalDepositLabel.setText("Rp 500.000");
    }//GEN-LAST:event_depositButtonPanelLabel500MouseClicked

    private void depositAmountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositAmountFieldActionPerformed

    }//GEN-LAST:event_depositAmountFieldActionPerformed

    private void depositAmountFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_depositAmountFieldKeyTyped

    }//GEN-LAST:event_depositAmountFieldKeyTyped

    private void depositAmountFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_depositAmountFieldKeyPressed
        Color selectedColor = selectedText;
        // Rp 50.000
        depositButtonPanel50.setBackground(Color.WHITE);
        depositButtonPanelLabel50.setForeground(selectedColor);

        // Rp 100.000
        depositButtonPanel100.setBackground(Color.WHITE);
        depositButtonPanelLabel100.setForeground(selectedColor);

        // Rp 200.000
        depositButtonPanel200.setBackground(Color.WHITE);
        depositButtonPanelLabel200.setForeground(selectedColor);

        // Rp 500.000
        depositButtonPanel500.setBackground(Color.WHITE);
        depositButtonPanelLabel500.setForeground(selectedColor);

        // SET TOTAL DEPOSIT TO the text input's value
        NumberFormat num = new DecimalFormat("#,###");
        String textValue = depositAmountField.getText();
        textValue = textValue.replaceAll("[^\\d]+", "");
        depositAmountField.setText(textValue);
        if (textValue.length() == 0) {
            textValue = "0";
        }
        double numberValue = Double.parseDouble(textValue);

        System.out.println(numberValue);

        totalDepositLabel.setText("Rp " + num.format(numberValue));
    }//GEN-LAST:event_depositAmountFieldKeyPressed

    private void depositAmountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_depositAmountFieldKeyReleased
        Color selectedColor = selectedText;
        // Rp 50.000
        depositButtonPanel50.setBackground(Color.WHITE);
        depositButtonPanelLabel50.setForeground(selectedColor);

        // Rp 100.000
        depositButtonPanel100.setBackground(Color.WHITE);
        depositButtonPanelLabel100.setForeground(selectedColor);

        // Rp 200.000
        depositButtonPanel200.setBackground(Color.WHITE);
        depositButtonPanelLabel200.setForeground(selectedColor);

        // Rp 500.000
        depositButtonPanel500.setBackground(Color.WHITE);
        depositButtonPanelLabel500.setForeground(selectedColor);

        // SET TOTAL DEPOSIT TO the text input's value
        NumberFormat num = new DecimalFormat("#,###");
        String textValue = depositAmountField.getText();
        textValue = textValue.replaceAll("[^\\d]+", "");
        depositAmountField.setText(textValue);
        if (textValue.length() == 0) {
            textValue = "0";
        }
        double numberValue = Double.parseDouble(textValue);

        System.out.println(numberValue);

        totalDepositLabel.setText("Rp " + num.format(numberValue));
    }//GEN-LAST:event_depositAmountFieldKeyReleased

    private void depositButtonSubmitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositButtonSubmitMousePressed
        String totalTransferStr = totalDepositLabel.getText();
        double totalTransfer = Double.parseDouble(totalTransferStr);
        String nomorAkun = nomorRekeningField.getText();
        this.validateFormForSubmitButton(nomorAkun, totalTransfer);
    }//GEN-LAST:event_depositButtonSubmitMousePressed

    private void nomorRekeningFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomorRekeningFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomorRekeningFieldActionPerformed

    private void nomorRekeningFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomorRekeningFieldKeyPressed
        this.validateNomorRekeningForTextInput(nomorRekeningField.getText());
    }//GEN-LAST:event_nomorRekeningFieldKeyPressed

    private void nomorRekeningFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomorRekeningFieldKeyReleased
        this.validateNomorRekeningForTextInput(nomorRekeningField.getText());
    }//GEN-LAST:event_nomorRekeningFieldKeyReleased

    private void nomorRekeningFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomorRekeningFieldKeyTyped
        this.validateNomorRekeningForTextInput(nomorRekeningField.getText());
    }//GEN-LAST:event_nomorRekeningFieldKeyTyped

    private void checkAccountButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkAccountButtonKeyPressed
        String nomorAkun = nomorRekeningField.getText();
        int textLength = nomorAkun.length();
        boolean isEnabled = true;
        if (textLength < 10) {
            isEnabled = false;
        }
        checkAccountButton.setEnabled(isEnabled);

        if (lastNomorAkun.equals(nomorAkun)) {
            depositButtonSubmit.setEnabled(true);
            checkAccountButton.setEnabled(false);
        }
        else {
            boolean accountExists = this.doesAccountExists(nomorAkun);
            if (accountExists == false) {
                JOptionPane.showMessageDialog(null, "Akun tidak ditemukan!");
                return;
            }
        }
    }//GEN-LAST:event_checkAccountButtonKeyPressed

    private void checkAccountButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkAccountButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_checkAccountButtonMouseClicked

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed

    }//GEN-LAST:event_jLabel1MousePressed

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
            java.util.logging.Logger.getLogger(Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transfer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.swing.buttons.ButtonRounded30Panel checkAccountButton;
    private javax.swing.JTextField depositAmountField;
    private javax.swing.JPanel depositButtonPanel100;
    private javax.swing.JPanel depositButtonPanel200;
    private javax.swing.JPanel depositButtonPanel50;
    private javax.swing.JPanel depositButtonPanel500;
    private javax.swing.JLabel depositButtonPanelLabel100;
    private javax.swing.JLabel depositButtonPanelLabel200;
    private javax.swing.JLabel depositButtonPanelLabel50;
    private javax.swing.JLabel depositButtonPanelLabel500;
    private raven.swing.buttons.ButtonRounded55Panel depositButtonSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nomorRekeningField;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel totalDepositLabel;
    // End of variables declaration//GEN-END:variables

}
