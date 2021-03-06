/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DBTables.CurrentSession;
import DBTables.Users;
import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author MR
 */
public class SlideMenu extends javax.swing.JPanel {

    /**
     * Creates new form SlideMenu
     */
    final String UserType ;
    public SlideMenu() throws Exception {
        initComponents();
       // this.setSize(new Dimension(new SplitPaneEXP().SplashLeftSide.getWidth(), new SplitPaneEXP().SplashLeftSide.getHeight()));
        UserType = new CurrentSession().getCurrentUserType();
        checkPermission(UserType);
        
        
    }
public void checkPermission(String flag){
    if(flag.equals("A")){
        
    }else if (flag.equals("U")) {
        safePAnel.setVisible(false);
        reportsPanel.setVisible(false);
        settingPanel.setVisible(false);
        warehuse.setVisible(false);
    }
    
}
 public void setMenuColor(JPanel jPanel){
     if (UserType.equals("A")){
        safePAnel.setBackground(new Color(147,112,219));
        reportsPanel.setBackground(new Color(147,112,219));
        settingPanel.setBackground(new Color(147,112,219));
        homepanel.setBackground(new Color(147,112,219));
        orderPanel.setBackground(new Color(147,112,219));
        warehuse.setBackground(new Color(147,112,219));
        jPanel.setBackground(new Color(85,55,118));
     } else if (UserType.equals("U")){
         orderPanel.setBackground(new Color(147,112,219));
          homepanel.setBackground(new Color(147,112,219));
          jPanel.setBackground(new Color(85,55,118));
         
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
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        homepanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        orderPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        safePAnel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        warehuse = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        reportsPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        settingPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 50, 204));
        setLayout(new java.awt.GridLayout(11, 1));

        jPanel1.setBackground(new java.awt.Color(153, 50, 204));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Happy Cafe  v 1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        add(jPanel1);

        jPanel4.setBackground(new java.awt.Color(153, 50, 204));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        add(jPanel4);

        homepanel.setBackground(new java.awt.Color(85, 55, 118));
        homepanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homepanelMouseClicked(evt);
            }
        });
        homepanel.setLayout(new java.awt.BorderLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/home-automation-40.png"))); // NOI18N
        homepanel.add(jLabel2, java.awt.BorderLayout.LINE_END);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("الرئيسية");
        homepanel.add(jLabel3, java.awt.BorderLayout.CENTER);

        add(homepanel);

        orderPanel.setBackground(new java.awt.Color(147, 112, 219));
        orderPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderPanelMouseClicked(evt);
            }
        });
        orderPanel.setLayout(new java.awt.BorderLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/advertisement-page-50.png"))); // NOI18N
        orderPanel.add(jLabel4, java.awt.BorderLayout.LINE_END);

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("الطلبات");
        orderPanel.add(jLabel5, java.awt.BorderLayout.CENTER);

        add(orderPanel);

        safePAnel.setBackground(new java.awt.Color(147, 112, 219));
        safePAnel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                safePAnelMouseClicked(evt);
            }
        });
        safePAnel.setLayout(new java.awt.BorderLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/safe.png"))); // NOI18N
        safePAnel.add(jLabel6, java.awt.BorderLayout.LINE_END);

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("الخزنه");
        safePAnel.add(jLabel7, java.awt.BorderLayout.CENTER);

        add(safePAnel);

        warehuse.setBackground(new java.awt.Color(147, 112, 219));
        warehuse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                warehuseMouseClicked(evt);
            }
        });
        warehuse.setLayout(new java.awt.BorderLayout());

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("المخزن");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        warehuse.add(jLabel12, java.awt.BorderLayout.CENTER);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/warehouse.png"))); // NOI18N
        warehuse.add(jLabel13, java.awt.BorderLayout.LINE_END);

        add(warehuse);

        reportsPanel.setBackground(new java.awt.Color(147, 112, 219));
        reportsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportsPanelMouseClicked(evt);
            }
        });
        reportsPanel.setLayout(new java.awt.BorderLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/reports.png"))); // NOI18N
        reportsPanel.add(jLabel8, java.awt.BorderLayout.LINE_END);

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("التقارير");
        reportsPanel.add(jLabel9, java.awt.BorderLayout.CENTER);

        add(reportsPanel);

        settingPanel.setBackground(new java.awt.Color(147, 112, 219));
        settingPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingPanelMouseClicked(evt);
            }
        });
        settingPanel.setLayout(new java.awt.BorderLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/administrator-40.png"))); // NOI18N
        settingPanel.add(jLabel10, java.awt.BorderLayout.LINE_END);

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("الإعدادات");
        settingPanel.add(jLabel11, java.awt.BorderLayout.CENTER);

        add(settingPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void safePAnelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_safePAnelMouseClicked
     setMenuColor(safePAnel);
    }//GEN-LAST:event_safePAnelMouseClicked

    private void homepanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homepanelMouseClicked
        setMenuColor(homepanel);
        System.out.println("Home Clicked "+State.Home_STATE);
       // new SplitPaneEXP(State.Home_STATE);
    }//GEN-LAST:event_homepanelMouseClicked

    private void orderPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderPanelMouseClicked
        setMenuColor(orderPanel);
    }//GEN-LAST:event_orderPanelMouseClicked

    private void reportsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsPanelMouseClicked
      setMenuColor(reportsPanel);
    }//GEN-LAST:event_reportsPanelMouseClicked

    private void settingPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingPanelMouseClicked
      setMenuColor(settingPanel);
    }//GEN-LAST:event_settingPanelMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
         setMenuColor(warehuse);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void warehuseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_warehuseMouseClicked
setMenuColor(warehuse);
        
            
       
    }//GEN-LAST:event_warehuseMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel homepanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel orderPanel;
    private javax.swing.JPanel reportsPanel;
    private javax.swing.JPanel safePAnel;
    private javax.swing.JPanel settingPanel;
    private javax.swing.JPanel warehuse;
    // End of variables declaration//GEN-END:variables
}
