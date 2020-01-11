/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTables;


import DBConnect.Connector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MR
 */
public class UNClosedOrder {
     private int O_UNCLOSEDORDER_ID ;
    private int T_TABLENO_ID ;
    private String PRESON ;
    private Timestamp DATECREATED;
    private int U_USER_ID ;
    private JTable table;
    CurrentSession cur;

   public UNClosedOrder (int o_UNCLOSEDORDER_ID ,int T_TABLENO_ID ,String PRESON,Timestamp DATECREATED,int U_USER_ID){
        this.O_UNCLOSEDORDER_ID  =o_UNCLOSEDORDER_ID;
        this.T_TABLENO_ID =T_TABLENO_ID;
        this.PRESON= PRESON;
        this.DATECREATED= DATECREATED;
        this.U_USER_ID = U_USER_ID;
   }
   
    public UNClosedOrder (int T_TABLENO_ID ,String PRESON,Timestamp DATECREATED){
       
        this.T_TABLENO_ID =T_TABLENO_ID;
        this.PRESON= PRESON;
        this.DATECREATED= DATECREATED;
        
   }
    public UNClosedOrder(){
        cur = new CurrentSession();
    }
    
    public void setO_UNCLOSEDORDER_ID(int O_UNCLOSEDORDER_ID) {
        this.O_UNCLOSEDORDER_ID = O_UNCLOSEDORDER_ID;
    }

    public void setT_TABLENO_ID(int T_TABLENO_ID) {
        this.T_TABLENO_ID = T_TABLENO_ID;
    }

    public void setPRESON(String PRESON) {
        this.PRESON = PRESON;
    }

    public void setDATECREATED(Timestamp DATECREATED) {
        this.DATECREATED = DATECREATED;
    }

    public void setU_USER_ID(int U_USER_ID) {
        this.U_USER_ID = U_USER_ID;
    }

    public int getO_UNCLOSEDORDER_ID() {
        return O_UNCLOSEDORDER_ID;
    }

    public int getT_TABLENO_ID() {
        return T_TABLENO_ID;
    }

    public String getPRESON() {
        return PRESON;
    }

    public Timestamp getDATECREATED() {
        return DATECREATED;
    }

    public int getU_USER_ID() {
        return U_USER_ID;
    }
    public ArrayList<UNClosedOrder> getList (){
    ArrayList<UNClosedOrder> List = new ArrayList<>();
    Connector DB = new Connector();
    Connection conn;
    String sql ="select * from ProductGroup ";
    Statement st;
    ResultSet rs;
    try {
     conn =DB.open_connection();
     st=conn.createStatement();
     rs=st.executeQuery(sql);
        while (rs.next()) {
           UNClosedOrder UNClosedOrder = new UNClosedOrder(rs.getInt("T_TABLENO_ID"),rs.getString("PRESON"),rs.getTimestamp("DATECREATED"));
           
           List.add(UNClosedOrder);
            
        }
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
}
   public void Show_user_inTable(JTable Table){
    ArrayList<UNClosedOrder> list = getList();
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    this.table = Table;
    Object [] row = new Object[4];
    for(int i = 0 ; i<list.size();i++){
      
        row[3] = i+1;
        row[2] = list.get(i).getT_TABLENO_ID();
        row[1] =list.get(i).getPRESON();
        row[0] =list.get(i).getDATECREATED();
        
        model.addRow(row);
    }
}
    public void executeQuery(String Query, String Message) throws Exception{
        Connector DB=new Connector();
        Connection conn = DB.open_connection();
        Statement st;  
        try {
            st= conn.createStatement();
            if((st.executeUpdate(Query)) ==1){
              //  Show_user_inTable(table);
                JOptionPane.showMessageDialog(null, "Data "+Message+" Succesfully");
            }else {
                JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully");
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully ");
        }
    }
   public void addUnClosedOrder(int MasterID,int TableNo,String Person) throws Exception{
      TableINF inf = new TableINF();
        try {//Insert into O_UNCLOSEDORDER values ()
            String sql = "Insert into O_unclosedorder  values ("+MasterID +","+TableNo+",'"+Person+"',sysdate,"+cur.getCurrentUserID()+",'D',0,0,'')";
          Statement st = Connector.start_Statment();
          st.executeQuery(sql);
          st.close();
            //this.executeQuery(sql, "Insert");
        } catch (SQLException ex) {
            Logger.getLogger(UNClosedOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UNClosedOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public void setGrandTotal(int MasterID , double GrandTotal,String Type,double Tax)throws Exception{
       String sql = "Update O_unclosedorder set DISSACCOUNT = "+Tax+" ,GRANDTOTAL ="+GrandTotal+" , DISACCOUNTTYPE = '"+Type+"' where O_unclosedorder_ID = "+MasterID;
       Statement st =Connector.start_Statment();
      st.execute(sql);
      st.close();
       
   }
     public void deleteMasterOrder(int masterID) throws Exception{
         String sql = " delete from O_unclosedOrder where O_unclosedOrder_ID = "+masterID;
         Statement st = Connector.start_Statment();
        try{
         st.executeQuery(sql);
         JOptionPane.showMessageDialog(table, "Order deleted");
        } catch (Exception e){
            System.err.println(""+e);
        }
}
     public void CompleteOrder(int MasterID) throws Exception{
         String sql = "update  O_unclosedOrder set docStatus = 'C' Where O_unclosedOrder_ID="+MasterID;
         Statement st = Connector.start_Statment();
         
         try {
              st.executeQuery(sql);
             st.close();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(table, "Order Completed");
             System.err.println(""+e);
         }
     }
     public double getGrandTotal(int MasterID) throws Exception{
         String sql = "Select GRANDTOTAL from O_unclosedOrder where O_unclosedOrder_ID = "+MasterID;
         double GrandTotal = 0;
         Statement st = Connector.start_Statment();
         ResultSet rs ;
         try {
             rs=st.executeQuery(sql);
             while(rs.next()){
                 GrandTotal = rs.getDouble("GRANDTOTAL");
             }
         } catch (Exception e) {
             System.err.println(""+e);
         }
         return GrandTotal;
     }
      public int getTaxAmt(int MasterID) throws Exception{
         String sql = "Select DISSACCOUNT from O_unclosedOrder where O_unclosedOrder_ID = "+MasterID;
         int GrandTotal = 0;
         Statement st = Connector.start_Statment();
         ResultSet rs ;
         try {
             rs=st.executeQuery(sql);
             while(rs.next()){
                 GrandTotal = rs.getInt("DISSACCOUNT");
             }
         } catch (Exception e) {
             System.err.println(""+e);
         }
         return GrandTotal;
     }
       public String getTaxSign(int MasterID) throws Exception{
         String sql = "Select DISACCOUNTTYPE from O_unclosedOrder where O_unclosedOrder_ID = "+MasterID;
         String GrandTotal = "";
         Statement st = Connector.start_Statment();
         ResultSet rs ;
         try {
             rs=st.executeQuery(sql);
             while(rs.next()){
                 GrandTotal = rs.getString("DISACCOUNTTYPE");
             }
         } catch (Exception e) {
             System.err.println(""+e);
         }
         return GrandTotal;
     }
}
