/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBViews;

import DBConnect.Connector;
import DBTables.TableINF;
import DBTables.UNClosedOrderLine;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MR
 */
public class Orders_RV {
    int O_UNCLOSEDORDER_ID;
    int O_UNCLOSEDORDERLINE_ID;
    int T_TABLENO_ID;
    String PRODUCT;
    int QTY ;
    double PRICE;
    double TOTALLINE;
    String DESCRIPTION;
    String DOCSTATUS;

    
    String PERSON;
    
    public Orders_RV ( int O_UNCLOSEDORDER_ID,String PRODUCT ,int QTY,double PRICE,double TOTALLINE,String DESCRIPTION){
       this.O_UNCLOSEDORDER_ID = O_UNCLOSEDORDER_ID;
        this.PRODUCT = PRODUCT;
        this.QTY=QTY;
        this.PRICE=PRICE;
        this.TOTALLINE = TOTALLINE;
        this.DESCRIPTION = DESCRIPTION;
        
    }
    public Orders_RV(){
        
    }
public String getPERSON() {
        return PERSON;
    }

    public void setPERSON(String PERSON) {
        this.PERSON = PERSON;
    }
    public void setO_UNCLOSEDORDER_ID(int O_UNCLOSEDORDER_ID) {
        this.O_UNCLOSEDORDER_ID = O_UNCLOSEDORDER_ID;
    }

    public void setO_UNCLOSEDORDERLINE_ID(int O_UNCLOSEDORDERLINE_ID) {
        this.O_UNCLOSEDORDERLINE_ID = O_UNCLOSEDORDERLINE_ID;
    }

    public void setT_TABLENO_ID(int T_TABLENO_ID) {
        this.T_TABLENO_ID = T_TABLENO_ID;
    }

    public void setPRODUCT(String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public void setPRICE(double PRICE) {
        this.PRICE = PRICE;
    }

    public void setTOTALLINE(double TOTALLINE) {
        this.TOTALLINE = TOTALLINE;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public void setDOCSTATUS(String DOCSTATUS) {
        this.DOCSTATUS = DOCSTATUS;
    }

    public int getO_UNCLOSEDORDER_ID() {
        return O_UNCLOSEDORDER_ID;
    }

    public int getO_UNCLOSEDORDERLINE_ID() {
        return O_UNCLOSEDORDERLINE_ID;
    }

    public int getT_TABLENO_ID() {
        return T_TABLENO_ID;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public int getQTY() {
        return QTY;
    }

    public double getPRICE() {
        return PRICE;
    }

    public double getTOTALLINE() {
        return TOTALLINE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public String getDOCSTATUS() {
        return DOCSTATUS;
    }
      public ArrayList<Orders_RV> getListForTable (int TableNo){// للاوردرات المفتوحه حاليا
    ArrayList<Orders_RV> List = new ArrayList<>();
    Connector DB = new Connector();
    Connection conn;
    String sql ="select * from orders_RV where T_TABLENO_ID = "+TableNo+" and docStatus= 'D'";
    String ProductName;
    Statement st;
    ResultSet rs;
    try {
     conn =DB.open_connection();
     st=conn.createStatement();
     rs=st.executeQuery(sql);
        while (rs.next()) {
       //    String per = rs.getString("Person");
           Orders_RV OrdersRV = new Orders_RV(rs.getInt("O_UNCLOSEDORDER_ID"),rs.getString("PRODUCT"),rs.getInt("QTY"),rs.getDouble("Price"),rs.getDouble("TOTALLINE"),rs.getString("Description"));
          
           List.add(OrdersRV);
            
        }
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
}
  public ArrayList<Orders_RV> getListForClosedOrder (int O_UNCLOSEDORDER_ID){// للمرتجاعات
    ArrayList<Orders_RV> List = new ArrayList<>();
    Connector DB = new Connector();
    Connection conn;
    String sql ="select * from orders_RV where O_UNCLOSEDORDER_ID = "+O_UNCLOSEDORDER_ID+" and docStatus= 'C'";
    String ProductName;
    Statement st;
    ResultSet rs;
    try {
     conn =DB.open_connection();
     st=conn.createStatement();
     rs=st.executeQuery(sql);
        while (rs.next()) {
       //    String per = rs.getString("Person");
           Orders_RV OrdersRV = new Orders_RV(rs.getInt("O_UNCLOSEDORDER_ID"),rs.getString("PRODUCT"),rs.getInt("QTY"),rs.getDouble("Price"),rs.getDouble("TOTALLINE"),rs.getString("Description"));
          
           List.add(OrdersRV);
            
        }
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
}
        
      
    
    public int  Show_user_inTable(JTable Table ,int TableID,JTextField jtext) throws Exception{//بنملي اليست في منيو سواء كانت مرتجعات او اوردر جديد
    ArrayList<Orders_RV> list = getListForTable(TableID);// التابل اي دي ده بيكون في المرتجعات هو الاوردر ايدي 
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    TableINF inf = new TableINF();
   
    Object [] row = new Object[6];
   int  MasterID = 0;
   if(list.size()==0){
       MasterID = inf.getNewID("O_UNclosedOrder");
   }
    for(int i = 0 ; i<list.size();i++){
        MasterID=list.get(0).getO_UNCLOSEDORDER_ID();
   // jtext.setText(list.get(i).getPERSON());
        row[5] = i+1;
        row[4]= list.get(i).getPRODUCT();
        row[3] = list.get(i).getQTY();
        row[2] = list.get(i).getPRICE();
        row[1] =list.get(i).getTOTALLINE();
     if (list.get(i).getDESCRIPTION()== null){
             row[0] ="";
         }else
         {        row[0] =list.get(i).getDESCRIPTION();}
        
        model.addRow(row);
    }
    
    return MasterID;
}
    public int  Show_CompletedUser_inTable(JTable Table ,int O_UNclosedOrderID,JTextField jtext) throws Exception{//بنملي اليست في منيو سواء كانت مرتجعات او اوردر جديد
    ArrayList<Orders_RV> list = getListForClosedOrder(O_UNclosedOrderID);// التابل اي دي ده بيكون في المرتجعات هو الاوردر ايدي 
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    TableINF inf = new TableINF();
   
    Object [] row = new Object[6];
   int  MasterID = 0;
   if(list.size()==0){
       MasterID = inf.getNewID("O_UNclosedOrder");
   }
    for(int i = 0 ; i<list.size();i++){
        MasterID=list.get(0).getO_UNCLOSEDORDER_ID();
   // jtext.setText(list.get(i).getPERSON());
        row[5] = i+1;
        row[4]= list.get(i).getPRODUCT();
        row[3] = list.get(i).getQTY();
        row[2] = list.get(i).getPRICE();
        row[1] =list.get(i).getTOTALLINE();
        row[0] =list.get(i).getDESCRIPTION();
        
        model.addRow(row);
    }
     return MasterID;
    }
}
