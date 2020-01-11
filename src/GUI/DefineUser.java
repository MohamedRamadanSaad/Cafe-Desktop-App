/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DBTables.FillComboBox;
import DBTables.TableINF;
import DBTables.Users;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MR
 */
public class DefineUser extends javax.swing.JFrame {

    /**
     * Creates new form DefineUser
     */
    Users user;
    TableINF inf;
    FillComboBox fill;
    public DefineUser() throws Exception {
        initComponents();
        this.setLocationRelativeTo(null);
        user = new Users();
        inf= new TableINF();
        fill = new FillComboBox();
        fill.fillCB(CB_employeename,  "name","Employee");
        
        user.Show_user_inTable(jTable_Users);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CB_priviledge = new javax.swing.JComboBox();
        passfirst = new javax.swing.JPasswordField();
        passTwo = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tx_username = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        CB_employeename = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Users = new javax.swing.JTable();

        setTitle("المستخدمين");
        setBackground(new java.awt.Color(204, 204, 255));
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("إسم الموظف");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("إسم المستخدم");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("كلمة السر");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("إعادة إدخال كلمة السر");

        CB_priviledge.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CB_priviledge.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "User", "Administrator" }));

        passfirst.setBackground(new java.awt.Color(108, 122, 137));
        passfirst.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        passfirst.setForeground(new java.awt.Color(255, 255, 255));
        passfirst.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passfirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passfirstActionPerformed(evt);
            }
        });

        passTwo.setBackground(new java.awt.Color(108, 122, 137));
        passTwo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        passTwo.setForeground(new java.awt.Color(255, 255, 255));
        passTwo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passTwoActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 102, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("إضافة");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("حذف");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 102, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("إعادة إدخال الباسورد");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("الصلاحية");

        tx_username.setBackground(new java.awt.Color(108, 122, 137));
        tx_username.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tx_username.setForeground(new java.awt.Color(255, 255, 255));
        tx_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton4.setBackground(new java.awt.Color(255, 102, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("تفريغ الحقول");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        CB_employeename.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CB_employeename.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "User", "Administrator" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(125, 125, 125)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(passfirst, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passTwo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_priviledge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_username)
                    .addComponent(CB_employeename, 0, 238, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(68, 68, 68))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CB_priviledge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jButton4))
                        .addGap(9, 9, 9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(CB_employeename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tx_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)))
                        .addGap(44, 44, 44)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(passfirst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(passTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);

        jTable_Users.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable_Users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الصلاحية", "إسم المستخدم", "الإسم", "م"
            }
        ));
        jTable_Users.setRowHeight(20);
        jTable_Users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_UsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Users);
        if (jTable_Users.getColumnModel().getColumnCount() > 0) {
            jTable_Users.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable_Users.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable_Users.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        jPanel1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passfirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passfirstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passfirstActionPerformed

    private void passTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passTwoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passTwoActionPerformed

    private void jTable_UsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_UsersMouseClicked
        actionTableSelected();
    }//GEN-LAST:event_jTable_UsersMouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        resetTX();
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
            actionAdd();
            
        } catch (Exception ex) {
            Logger.getLogger(DefineUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        try {
            actionDeleteUser();
            resetTX();
        } catch (Exception ex) {
            Logger.getLogger(DefineUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        try {
            actionResetPassword();
            resetTX();
        } catch (Exception ex) {
            Logger.getLogger(DefineUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3MouseClicked

    public void actionTableSelected(){
        int row = jTable_Users.getSelectedRow();
        CB_employeename.setSelectedItem(jTable_Users.getModel().getValueAt(row, 2).toString());
        tx_username.setText( jTable_Users.getModel().getValueAt(row, 1).toString());
        CB_priviledge.setSelectedItem(jTable_Users.getModel().getValueAt(row, 0).toString());
    }
    public void resetTX(){
        
        tx_username.setText(null);
        passfirst.setText(null);
        passTwo.setText(null);
    }
    public void actionAdd()throws Exception{
        String Username = tx_username.getText().toString();
        if(checkPassword() == true){
          String Password = passfirst.getText().toString();
          int ID = inf.getIDForTRX("Employee", CB_employeename.getSelectedItem().toString());
        user.addUser(Username, Password, ID, getSelectedUserType(), jTable_Users);  
        }
        
    }
    public boolean checkPassword(){
        boolean state = false ;
        if (passfirst.getText().toString().equals(passTwo.getText().toString())){
            state = true ;
        }else {
            JOptionPane.showMessageDialog(this, "Password is not matched");
        }
        return state;
    }
    public String getSelectedUserType(){
        String Type ;
        if (CB_priviledge.getSelectedItem().equals("User")){
            Type="U";
        }else {
            Type="A";
        }
        return Type;
    }
    public void actionDeleteUser() throws Exception{
        int ID = inf.getUserIDForTRX("Users", tx_username.getText().toString());
        user.deleteUser(ID, jTable_Users);
    }
    
    public void actionResetPassword() throws SQLException, Exception{
        int ID = inf.getUserIDForTRX("Users", tx_username.getText().toString());
       if( checkPassword()==true){
        user.resetPassword(ID,passTwo.getText().toString() );
    }
        
    }
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
            java.util.logging.Logger.getLogger(DefineUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DefineUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DefineUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DefineUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DefineUser().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(DefineUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CB_employeename;
    private javax.swing.JComboBox CB_priviledge;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Users;
    private javax.swing.JPasswordField passTwo;
    private javax.swing.JPasswordField passfirst;
    private javax.swing.JPasswordField passord_JPass;
    private javax.swing.JTextField tx_username;
    // End of variables declaration//GEN-END:variables
}
