/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DBConnect.Connector;
import DBTables.FillComboBox;
import DBTables.TableINF;
import DBTables.Tables;
import DBTables.UNClosedOrder;
import DBTables.UNClosedOrderLine;
import DBViews.Orders_RV;
import Utils.ReportGenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static mondrian.olap.fun.vba.Vba.round;
import org.apache.batik.dom.svg.ListHandler;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author MR
 */
public class OpenOrder extends javax.swing.JFrame {

    /**
     * Creates new form OpenOrder
     */
    FillComboBox fill ;
    TableINF inf ;
    UNClosedOrder or;
    UNClosedOrderLine orl;
    Tables t;
    int Holestatus;
     int SessionMasterID=0;
     double total=0;
    int i = 0 ; // ارجعه لصفر لما اضغط حفظ الطلب
    SplitPaneEXP frame;
    JButton []TableButtons;
    JButton btn;
     ListHandler lh;
    public OpenOrder(int TableID ,int ComingStatus,SplitPaneEXP frame) throws Exception {
      if (frame != null){
        this.frame= frame;
      } else {
          this.frame=null;
      }
       
        int Status = ComingStatus;
       initComponents();
        createTablesButtons();
        lh= new ListHandler();
         jListProduct.addListSelectionListener(lh);
        jListProduct.removeAll();
       tx_infprice.setText("");
       this.setSize(1100, 700);
        this.setLocationRelativeTo(null);
        or = new UNClosedOrder();
        orl =new UNClosedOrderLine();
         inf = new TableINF();
          inf.getProductInProductGroup(jListProduct,1);
           fill = new FillComboBox(); 
           t=new Tables();
        Orders_RV rv = new Orders_RV();
           if (Status == 0){//red button
             rv = new Orders_RV();
          SessionMasterID=  rv.Show_user_inTable(jTable_unclosedOrder, TableID,null);
          tx_orderNo.setText(""+SessionMasterID);
            fill.fillCB(CB_Product, "name", "Product");
            fill.fillCB(CB_Code, "Code", "Product");
           autoCompleteCB(CB_Code);
           autoCompleteCB(CB_Product);
             CB_Code.setEnabled(false);
             CB_TableNo.setEnabled(false);
             CB_TableNo.removeAllItems();
             CB_TableNo.addItem(TableID);
            autoCompleteCB(CB_Product);
            tx_person.setText(inf.getPersonOrdered(SessionMasterID));
            tx_person.setEnabled(false);
          
            tx_total.setText(""+orl.returnTotal(SessionMasterID));
            tx_Taxtotal.setText(tx_total.getText().toString());
        
             i=1;
        }else if (Status ==1){// green button to reserve free table 
             rv.Show_user_inTable(jTable_unclosedOrder, TableID,null);
             fill.fillCB(CB_Product, "name", "Product");
             fill.fillCB(CB_Code, "Code", "Product");
             CB_Code.setEnabled(false);
             CB_TableNo.setEnabled(false);
             CB_TableNo.removeAllItems();
             CB_TableNo.addItem(TableID);
             autoCompleteCB(CB_Product);
             tx_total.setText(""+orl.returnTotal(SessionMasterID));
             tx_orderNo.setText(""+SessionMasterID);
             tx_Taxtotal.setText(tx_total.getText().toString());
        }else if (Status ==2){//take away 
            if (!t.isTakeAwayCompleted()==false){
                 i=0;
                  rv.Show_user_inTable(jTable_unclosedOrder, TableID,null);
             }else {
                 i=1;
                  SessionMasterID=rv.Show_user_inTable(jTable_unclosedOrder, TableID,null);
                  tx_orderNo.setText(""+SessionMasterID);
             }
             rv.Show_user_inTable(jTable_unclosedOrder, TableID,null);
             fill.fillCB(CB_Product, "name", "Product");
             fill.fillCB(CB_Code, "Code", "Product");
             CB_Code.setEnabled(false);
             CB_TableNo.setEnabled(false);
             CB_TableNo.removeAllItems();
             CB_TableNo.addItem(TableID);
             autoCompleteCB(CB_Product);
             tx_Description.setText("أوردر تاك أواي");
             tx_Description.setEnabled(false);
             Holestatus =2;
             tx_total.setText(""+orl.returnTotal(SessionMasterID));
             tx_Taxtotal.setText(tx_total.getText().toString());
             
        }else if (Status == 3){//المرتجعات
           rv = new Orders_RV();
           SessionMasterID=  rv.Show_CompletedUser_inTable(jTable_unclosedOrder, TableID,null);
           tx_orderNo.setText(""+SessionMasterID);
           fill.fillCB(CB_Product, "name", "Product");
           fill.fillCB(CB_Code, "Code", "Product");
           autoCompleteCB(CB_Code);
           autoCompleteCB(CB_Product);
           CB_Code.setEnabled(false);
           CB_TableNo.setEnabled(false);
           CB_TableNo.removeAllItems();
           CB_TableNo.addItem(TableID);
           autoCompleteCB(CB_Product);
           tx_total.setText(""+orl.returnTotal(SessionMasterID));
           tx_Taxtotal.setText(tx_total.getText().toString());
           tx_total.setText(""+orl.returnTotal(SessionMasterID));
           tx_Taxtotal.setText(tx_total.getText().toString());
           txf_TaxSign.setText(or.getTaxSign(TableID));
           txf_Tax.setText(""+or.getTaxAmt(TableID));
           taxCBSelected();
             i=1;
        }
            else {
        int i = 0;
        fill.fillCB(CB_Product, "name", "Product");
         fill.fillCB(CB_Code, "Code", "Product");
         autoCompleteCB(CB_Code);
         autoCompleteCB(CB_Product);
         CB_Code.setEnabled(false);
        if(i==0)
        {
            fill.fillCBFreeTables(CB_TableNo);
        }else if (i==1){
            fill.fillCBUNFreeTables(CB_TableNo);
        }else if (i==2){
            fill.fillCBSpecifiedTables(CB_TableNo,tx_person, 5);
        }
        
      
       
     
      
        pan_CB_Product.add(CB_Product,BorderLayout.CENTER);
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
        jTable_unclosedOrder = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListProduct = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        PGButtons = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        tx_Taxtotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tf_cach = new javax.swing.JTextField();
        tx_remaning = new javax.swing.JLabel();
        jb_remaining = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        tx_total = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txf_Tax = new javax.swing.JTextField();
        txf_TaxSign = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tx_person = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CB_TableNo = new javax.swing.JComboBox();
        BT_PrintReport = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        tx_orderNo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        CB_Code = new javax.swing.JComboBox();
        pan_CB_Product = new javax.swing.JPanel();
        CB_Product = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tx_QTY = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tx_Description = new javax.swing.JTextField();
        BT_Sync = new javax.swing.JButton();
        Check_Search = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        bt_addToOrder = new javax.swing.JButton();
        bt_deleteFromOrder = new javax.swing.JButton();
        bt_deleteOrder = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        bt_saveOrder1 = new javax.swing.JButton();
        bt_saveOrder = new javax.swing.JButton();
        tx_infprice = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setTitle("بيانات الطلب");
        getContentPane().setLayout(new java.awt.GridLayout(1, 3));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new java.awt.GridLayout(3, 0));

        jTable_unclosedOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable_unclosedOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الملاحظات", "الإجمالي", "السعر", "الكمية", "الصنف", "م"
            }
        ));
        jTable_unclosedOrder.setRowHeight(25);
        jTable_unclosedOrder.setSelectionBackground(new java.awt.Color(0, 0, 0));
        jTable_unclosedOrder.setSelectionForeground(new java.awt.Color(51, 255, 51));
        jTable_unclosedOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_unclosedOrderMouseClicked(evt);
            }
        });
        jTable_unclosedOrder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable_unclosedOrderKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_unclosedOrder);
        if (jTable_unclosedOrder.getColumnModel().getColumnCount() > 0) {
            jTable_unclosedOrder.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable_unclosedOrder.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTable_unclosedOrder.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTable_unclosedOrder.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTable_unclosedOrder.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable_unclosedOrder.getColumnModel().getColumn(5).setPreferredWidth(15);
        }

        jPanel1.add(jScrollPane1);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        jListProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jListProduct.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jListProduct.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jListProduct);

        jPanel9.add(jScrollPane3);

        PGButtons.setBackground(new java.awt.Color(204, 204, 255));
        PGButtons.setLayout(new java.awt.GridLayout(20, 1, 1, 1));
        jScrollPane2.setViewportView(PGButtons);

        jPanel9.add(jScrollPane2);

        jPanel1.add(jPanel9);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("جنيه");

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        tx_Taxtotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tx_Taxtotal.setForeground(new java.awt.Color(255, 51, 0));
        tx_Taxtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tx_Taxtotal.setText("الإجمالي");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tx_Taxtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tx_Taxtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("بعد الخصم");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("دفع");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("الباقي");

        tf_cach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tf_cach.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tx_remaning.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tx_remaning.setForeground(new java.awt.Color(255, 51, 51));
        tx_remaning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tx_remaning.setText("تبقي له");

        jb_remaining.setText("حساب المتبقي");
        jb_remaining.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_remainingMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("الإجمالي");

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));

        tx_total.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tx_total.setForeground(new java.awt.Color(0, 255, 51));
        tx_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tx_total.setText("الإجمالي");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tx_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tx_total, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("جنيه");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setText("الخصم");

        txf_Tax.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txf_Tax.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txf_TaxSign.setEditable(false);
        txf_TaxSign.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txf_TaxSign.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txf_TaxSign.setText("%");
        txf_TaxSign.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txf_TaxSignMouseClicked(evt);
            }
        });

        jCheckBox1.setText("خصم");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jb_remaining)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_cach, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(tx_remaning)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 587, Short.MAX_VALUE)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txf_TaxSign, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txf_Tax, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jb_remaining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_cach, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 19, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txf_Tax, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txf_TaxSign, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox1))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tx_remaning))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel1.add(jPanel5);

        getContentPane().add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "بيانات الحجز الأساسية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("إسم العميل");

        tx_person.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tx_person.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("رقم التربيزة");

        CB_TableNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CB_TableNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        BT_PrintReport.setBackground(new java.awt.Color(102, 204, 0));
        BT_PrintReport.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BT_PrintReport.setText("إنهاء الطلب وطباعة الفاتورة");
        BT_PrintReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_PrintReportMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("أوردر رقم");

        tx_orderNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(534, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BT_PrintReport, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(tx_orderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tx_person, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CB_TableNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tx_person, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(BT_PrintReport, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tx_orderNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel17))))
                    .addComponent(CB_TableNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "تفاصيل الحجز", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("الصنف");

        CB_Code.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CB_Code.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        pan_CB_Product.setLayout(new java.awt.BorderLayout());

        CB_Product.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CB_Product.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CB_Product.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CB_ProductItemStateChanged(evt);
            }
        });
        pan_CB_Product.add(CB_Product, java.awt.BorderLayout.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("الكود");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("الكمية");

        tx_QTY.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tx_QTY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tx_QTY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tx_QTYKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("الملاحظات");

        tx_Description.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tx_Description.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tx_Description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tx_DescriptionKeyPressed(evt);
            }
        });

        BT_Sync.setText("SYNC");
        BT_Sync.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SyncMouseClicked(evt);
            }
        });

        Check_Search.setText("البحث بالكود");
        Check_Search.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Check_SearchItemStateChanged(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(51, 255, 51));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        bt_addToOrder.setBackground(new java.awt.Color(255, 102, 51));
        bt_addToOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_addToOrder.setText("إضافة إلى الطلب");
        bt_addToOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addToOrderMouseClicked(evt);
            }
        });
        jPanel7.add(bt_addToOrder);

        bt_deleteFromOrder.setBackground(new java.awt.Color(255, 102, 51));
        bt_deleteFromOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_deleteFromOrder.setText("حذف من الطلب");
        bt_deleteFromOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_deleteFromOrderMouseClicked(evt);
            }
        });
        jPanel7.add(bt_deleteFromOrder);

        bt_deleteOrder.setBackground(new java.awt.Color(255, 102, 51));
        bt_deleteOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_deleteOrder.setText("إلغاء الطلب");
        bt_deleteOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_deleteOrderMouseClicked(evt);
            }
        });
        jPanel7.add(bt_deleteOrder);

        jPanel8.setBackground(new java.awt.Color(51, 255, 51));
        jPanel8.setLayout(new java.awt.GridLayout());

        bt_saveOrder1.setBackground(new java.awt.Color(255, 102, 51));
        bt_saveOrder1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_saveOrder1.setText("بيان للعميل");
        bt_saveOrder1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_saveOrder1MouseClicked(evt);
            }
        });
        jPanel8.add(bt_saveOrder1);

        bt_saveOrder.setBackground(new java.awt.Color(255, 102, 51));
        bt_saveOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_saveOrder.setText("بيان بالطلبات");
        bt_saveOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_saveOrderMouseClicked(evt);
            }
        });
        jPanel8.add(bt_saveOrder);

        tx_infprice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tx_infprice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("سعر الوحدة");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pan_CB_Product, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(BT_Sync)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CB_Code, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tx_Description)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tx_infprice, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(tx_QTY, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Check_Search)
                        .addGap(169, 169, 169))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Check_Search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CB_Code, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(BT_Sync))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pan_CB_Product, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(tx_QTY, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tx_infprice, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(15, 15, 15)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tx_Description, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Check_SearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Check_SearchItemStateChanged
       if (Check_Search.isSelected()) {
           CB_Product.setEnabled(false);
            CB_Code.setEnabled(true);
       }else {
           CB_Product.setEnabled(true);
           CB_Code.setEnabled(false);
       }
    }//GEN-LAST:event_Check_SearchItemStateChanged

    private void bt_addToOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addToOrderMouseClicked
        try {
            if(!tx_QTY.getText().equals("")&&Integer.parseInt(tx_QTY.getText().toString())>0){
              actionAddSelected();
             tx_orderNo.setText(""+SessionMasterID);
            resetFields();
            tx_total.setText(""+orl.returnTotal(SessionMasterID));
             tx_Taxtotal.setText(tx_total.getText().toString());
           if(frame!=null)
            frame.refreshTablebuttons();
            }
            else {
                 JOptionPane.showMessageDialog(this, "يجب إدخال كمية"); 
            }
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "الكمية غير صحيحة"); 
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_bt_addToOrderMouseClicked

    private void BT_SyncMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SyncMouseClicked
        try {
            CB_Product.setSelectedItem(inf.getProductNameFromCode(CB_Code.getSelectedItem().toString()));
        } catch (Exception ex) {
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BT_SyncMouseClicked

    private void jTable_unclosedOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_unclosedOrderMouseClicked
        try {
            actionTableSelected();
        } catch (Exception ex) {
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable_unclosedOrderMouseClicked

    private void bt_deleteFromOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteFromOrderMouseClicked
        try {
            actionDeleteOneLineFromOrderOrder("P");
            actionDeleteFromLneSelected();
            tx_total.setText(""+orl.returnTotal(SessionMasterID));
            taxCBSelected();
        } catch (Exception ex) {
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_deleteFromOrderMouseClicked

    private void bt_deleteOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteOrderMouseClicked
        try {
            actionSaveOrder("P");
            orl.deleteOrder(SessionMasterID,Integer.parseInt(CB_TableNo.getSelectedItem().toString()),jTable_unclosedOrder);
            this.setVisible(false);
            if(frame!=null)
            frame.refreshTablebuttons();
        } catch (Exception ex) {
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_deleteOrderMouseClicked

    private void jb_remainingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_remainingMouseClicked
       if(!tf_cach.getText().equals("")){
        calculateRemaining();    
       }else{
           JOptionPane.showMessageDialog(btn, "أدخل  المبلغ المدفوع لحساب الباقي");
        }
       
    }//GEN-LAST:event_jb_remainingMouseClicked

    private void bt_saveOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveOrderMouseClicked
       
        ReportGenerator report = new ReportGenerator(Integer.parseInt(CB_TableNo.getSelectedItem().toString()),null,null,2);
        
    }//GEN-LAST:event_bt_saveOrderMouseClicked

    private void BT_PrintReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_PrintReportMouseClicked
     int TableNo=  Integer.parseInt(CB_TableNo.getSelectedItem().toString());
        try {
            double GrandTotal = Double.parseDouble( tx_Taxtotal.getText().toString());
             String Type = txf_TaxSign.getText().toString();
            if(txf_Tax.getText().equals("")){
                txf_Tax.setText(""+0);
            }
             double Tax =Double.parseDouble( txf_Tax.getText().toString());
            
            or.setGrandTotal(SessionMasterID, GrandTotal,Type,Tax);
            ReportGenerator report = new ReportGenerator(TableNo,null,null,1);
             
            actionSaveOrder("M");
            or.CompleteOrder(SessionMasterID);
            t.resetTable(TableNo);
            this.setVisible(false);
             if(frame!=null)
            frame.refreshTablebuttons();
        } catch (Exception ex) {
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_BT_PrintReportMouseClicked

    private void CB_ProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CB_ProductItemStateChanged
        try {
            int ID ;
            if (CB_Product.getSelectedItem()==null){
              ID=0;  
            }else{   
            ID= inf.getIDForTRX("product", CB_Product.getSelectedItem().toString());
            }
            tx_infprice.setText(""+inf.getProductPrice(ID));
        } catch (SQLException ex) {
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_CB_ProductItemStateChanged

    private void txf_TaxSignMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txf_TaxSignMouseClicked
        System.out.println(txf_TaxSign.getText());
        if(txf_TaxSign.getText().equals("%")){
           txf_TaxSign.setText("AMT");
       }else if (txf_TaxSign.getText().equals("AMT")){
            txf_TaxSign.setText("%");
       }
    }//GEN-LAST:event_txf_TaxSignMouseClicked

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
       if(jCheckBox1.isSelected()){
           taxCBSelected();
          
       }else if (!jCheckBox1.isSelected()){
             tx_Taxtotal.setText(tx_total.getText().toString());
       }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged
   
    
  
    private void tx_QTYKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_QTYKeyPressed
         String  line1 = "" + evt.getKeyText( evt.getKeyCode());
    
     if(line1.equals("Enter")){ 
        try {
            if(!tx_QTY.getText().equals("")&&Integer.parseInt(tx_QTY.getText().toString())>0){
              actionAddSelected();
             tx_orderNo.setText(""+SessionMasterID);
            resetFields();
            tx_total.setText(""+orl.returnTotal(SessionMasterID));
             tx_Taxtotal.setText(tx_total.getText().toString());
           if(frame!=null)
            frame.refreshTablebuttons();
            }
            else {
                 JOptionPane.showMessageDialog(this, "يجب إدخال كمية"); 
            }
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "الكمية غير صحيحة"); 
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }}else if (line1.equals("F1")){
              ReportGenerator report = new ReportGenerator(Integer.parseInt(CB_TableNo.getSelectedItem().toString()),null,null,2);
        }
    }//GEN-LAST:event_tx_QTYKeyPressed

    private void tx_DescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_DescriptionKeyPressed
         String  line1 = "" + evt.getKeyText( evt.getKeyCode());
    
     if(line1.equals("Enter")){ 
        try {
            if(!tx_QTY.getText().equals("")&&Integer.parseInt(tx_QTY.getText().toString())>0){
              actionAddSelected();
             tx_orderNo.setText(""+SessionMasterID);
            resetFields();
            tx_total.setText(""+orl.returnTotal(SessionMasterID));
             tx_Taxtotal.setText(tx_total.getText().toString());
           if(frame!=null)
            frame.refreshTablebuttons();
            }
            else {
                 JOptionPane.showMessageDialog(this, "يجب إدخال كمية"); 
            }
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "الكمية غير صحيحة"); 
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }}else if (line1.equals("F1")){
              ReportGenerator report = new ReportGenerator(Integer.parseInt(CB_TableNo.getSelectedItem().toString()),null,null,2);
        }
    }//GEN-LAST:event_tx_DescriptionKeyPressed

    private void jTable_unclosedOrderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_unclosedOrderKeyPressed
String  line1 = "" + evt.getKeyText( evt.getKeyCode());
    
     if(line1.equals("Delete")){
        try {
            actionDeleteOneLineFromOrderOrder("P");
            actionDeleteFromLneSelected();
            tx_total.setText(""+orl.returnTotal(SessionMasterID));
             taxCBSelected();
        } catch (Exception ex) {
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }     }  
    }//GEN-LAST:event_jTable_unclosedOrderKeyPressed

    private void bt_saveOrder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveOrder1MouseClicked
       int TableNo=  Integer.parseInt(CB_TableNo.getSelectedItem().toString());
        try{
            double GrandTotal = Double.parseDouble( tx_Taxtotal.getText().toString());
             String Type = txf_TaxSign.getText().toString();
            if(txf_Tax.getText().equals("")){
                txf_Tax.setText(""+0);
            }
             double Tax =Double.parseDouble( txf_Tax.getText().toString());
            
            or.setGrandTotal(SessionMasterID, GrandTotal,Type,Tax);
            ReportGenerator report = new ReportGenerator(TableNo,null,null,1);
        }catch(Exception e){
            e.printStackTrace();
        } 
    }//GEN-LAST:event_bt_saveOrder1MouseClicked
public void taxCBSelected (){
     if(!txf_Tax.getText().equals("")){
           if (txf_TaxSign.getText().equals("%")){
           double Total = Double.parseDouble(tx_total.getText().toString());
           double Tax =Double.parseDouble(txf_Tax.getText().toString());
           double Dissaccount = (Total*Tax)/100;
           double AfterTax = Total - Dissaccount;
        tx_Taxtotal.setText(""+AfterTax);
        
           }else if (txf_TaxSign.getText().equals("AMT")){
                double Total = Double.parseDouble(tx_total.getText().toString());
           double Tax =Double.parseDouble(txf_Tax.getText().toString());
           
           double AfterTax = Total - Tax;
        tx_Taxtotal.setText(""+AfterTax);
           }
           if(!tx_Taxtotal.getText().equals(tx_Taxtotal.getText())){
               jCheckBox1.setSelected(true);
           }
           }else {
               JOptionPane.showMessageDialog(btn, "أدخل قيمة الخصم");
           }
}
    public void actionAddSelected() throws Exception{
         
        
         int ProductID = inf.getIDForTRX("Product", CB_Product.getSelectedItem().toString());
         int QTY =Integer.parseInt( tx_QTY.getText().toString());
         String Description; 
         if(tx_Description.getText()==null){
             System.err.println("Descritption is null");
             Description = " ";
         }
         else {
             Description  =tx_Description.getText().toString();
         }
       if(i==0){
           
           int masterID=inf.getNewID("O_UnclosedOrder");
           
           SessionMasterID = masterID;
           String STTableNo = CB_TableNo.getSelectedItem().toString();
           int TableNo = Integer.parseInt(STTableNo);
           String Person = tx_person.getText().toString();
           inf.reserveTable(TableNo);
           total = QTY * inf.getProductPrice(ProductID);
           
           or.addUnClosedOrder(masterID,TableNo,Person);
           orl.addUNClosedOrderLine(masterID,ProductID , QTY, Description,jTable_unclosedOrder);
           //tx_total.setText(String.valueOf(total));
           tx_person.setEnabled(false);
           CB_TableNo.setEnabled(false);
           i++;
       }else {
           total+=QTY * inf.getProductPrice(ProductID);
            orl.addUNClosedOrderLine(SessionMasterID,ProductID , QTY, Description,jTable_unclosedOrder);
     // tx_total.setText(String.valueOf(total));
       }
    }
    
    public void actionTableSelected() throws Exception{
        try {
         int row = jTable_unclosedOrder.getSelectedRow();
        CB_Product.setSelectedItem(jTable_unclosedOrder.getModel().getValueAt(row,4).toString());
        CB_Code.setSelectedItem(inf.getProductCodeFromName(CB_Product.getSelectedItem().toString()));
        tx_QTY.setText(jTable_unclosedOrder.getModel().getValueAt(row,3).toString());
        
         // JOptionPane.showMessageDialog(btn, ""+jTable_unclosedOrder.getModel().getValueAt(row,0).toString());
       
        tx_Description.setText(jTable_unclosedOrder.getModel().getValueAt(row,0).toString());
        }catch (Exception e ){
           // JOptionPane.showMessageDialog(btn, e);
        }
    }
    
    public void actionDeleteFromLneSelected() throws SQLException, Exception{
        int ID = inf.getUNClosedLineID(SessionMasterID,  inf.getIDForTRX("Product", CB_Product.getSelectedItem().toString()));
     //  total =Integer.parseInt(tx_total.getText())-  ;
        orl.deletelineFromOrder(ID,SessionMasterID,jTable_unclosedOrder);
        orl.Show_user_inTable(jTable_unclosedOrder, SessionMasterID);
        
    }
    public void calculateRemaining(){
         double total = Double.parseDouble(tx_Taxtotal.getText().toString());
       double cach=Double.parseDouble(tf_cach.getText().toString());
       double result = round(cach -total,2);
        tx_remaning.setText(""+result);
    }
    public void resetFields(){
        tx_QTY.setText("");
        if(Holestatus!=2)
        tx_Description.setText("");
    }
    public void autoCompleteCB(JComboBox CB){
  
       AutoCompleteDecorator.decorate(CB);
       //CB.setSelectedIndex();
    }
     public void createTablesButtons() throws Exception{
    String sqlPGName = "select  name  from ProductGroup  order by name";
    
    
   ResultSet rs = new Connector().start_Statment().executeQuery(sqlPGName);
   while(rs.next()){ 
        String name=""+rs.getString("name");
         btn=new JButton(name);
         btn.setText(name);
         btn.setFont(new java.awt.Font("Segoe UI", 1, 14));
         btn.addActionListener(new PGListener());
         //System.out.println(" Name "+btn.getName()+" Text "+btn.getText());
        
                PGButtons.add(btn);
        
    }
    rs.close();
    
}
     public void actionSaveOrder(String P_OR_M) throws SQLException, Exception{
         int rows = jTable_unclosedOrder.getRowCount();
         int QTYEntered;
         int ID;
         for(int i = 0 ; i < rows ; i++){
             QTYEntered = Integer.parseInt(jTable_unclosedOrder.getModel().getValueAt(i,3).toString());
             ID =inf.getIDForTRX("product", jTable_unclosedOrder.getModel().getValueAt(i,4).toString());
             inf.updateProductQTY(ID, QTYEntered, P_OR_M);
         }
       // JOptionPane.showMessageDialog(btn, ""+jTable_unclosedOrder.getModel().getValueAt(i,3).toString());
     }
     public void actionDeleteOneLineFromOrderOrder(String P_OR_M) throws SQLException, Exception{// لمسح سطر واحد بس الدالة التانيه بتمسح كله او تزود كله 
         int rows = jTable_unclosedOrder.getRowCount();
         int QTYEntered;
         int ID;
         
             QTYEntered = Integer.parseInt(tx_QTY.getText().toString());
             ID =inf.getIDForTRX("product", CB_Product.getSelectedItem().toString());
             inf.updateProductQTY(ID, QTYEntered, P_OR_M);
         
       // JOptionPane.showMessageDialog(btn, ""+jTable_unclosedOrder.getModel().getValueAt(i,3).toString());
     }
      public   class PGListener implements ActionListener {
  
    
     public PGListener() throws Exception{
    
     }
  public void actionPerformed(ActionEvent e)  {
     
    if (e.getSource().toString()!=null){
        
        try {
            //  JOptionPane.showMessageDialog(btn, ""+e.getActionCommand().toString());
            int ID = inf.getIDForTRX("ProductGroup",e.getActionCommand().toString());
            inf.getProductInProductGroup(jListProduct,ID);
        } catch (SQLException ex) {
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
  }}
      private class ListHandler implements ListSelectionListener
    {
        public void valueChanged(ListSelectionEvent e)
        {
            if(e.getSource()==jListProduct )
            {
                CB_Product.setSelectedItem(jListProduct.getSelectedValue().toString());
            }
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
            java.util.logging.Logger.getLogger(OpenOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpenOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpenOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpenOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                  //  new OpenOrder().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_PrintReport;
    private javax.swing.JButton BT_Sync;
    private javax.swing.JComboBox CB_Code;
    private javax.swing.JComboBox CB_Product;
    private javax.swing.JComboBox CB_TableNo;
    private javax.swing.JCheckBox Check_Search;
    private javax.swing.JPanel PGButtons;
    private javax.swing.JButton bt_addToOrder;
    private javax.swing.JButton bt_deleteFromOrder;
    private javax.swing.JButton bt_deleteOrder;
    private javax.swing.JButton bt_saveOrder;
    private javax.swing.JButton bt_saveOrder1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListProduct;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_unclosedOrder;
    private javax.swing.JButton jb_remaining;
    private javax.swing.JPanel pan_CB_Product;
    private javax.swing.JTextField tf_cach;
    private javax.swing.JTextField tx_Description;
    private javax.swing.JTextField tx_QTY;
    private javax.swing.JLabel tx_Taxtotal;
    private javax.swing.JLabel tx_infprice;
    private javax.swing.JLabel tx_orderNo;
    private javax.swing.JTextField tx_person;
    private javax.swing.JLabel tx_remaning;
    private javax.swing.JLabel tx_total;
    private javax.swing.JTextField txf_Tax;
    private javax.swing.JTextField txf_TaxSign;
    // End of variables declaration//GEN-END:variables
}
