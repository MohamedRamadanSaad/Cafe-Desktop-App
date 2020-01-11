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
public class UNClosedOrderLine {
    int O_UNCLOSEDORDERLINE_ID;
    int O_UNCLOSEDORDER_ID;
    int PRODUCT_ID ;
    int QTY;
    double PRICE;
    String DESCRIPTION;

    
    double TOTALLINE ;

String ProductName;
    private JTable table;
  
    
    @Override
    public String toString() {
        return "UNClosedOrderLine{" + "O_UNCLOSEDORDERLINE_ID=" + O_UNCLOSEDORDERLINE_ID + ", O_UNCLOSEDORDER_ID=" + O_UNCLOSEDORDER_ID + ", PRODUCT_ID=" + PRODUCT_ID + ", QTY=" + QTY + ", PRICE=" + PRICE + ", DESCRIPTION=" + DESCRIPTION + '}';
    }
    
    public UNClosedOrderLine(int PRODUCT_ID,int QTY,double PRICE,double TotalLine, String DESCRIPTION){
        this.PRODUCT_ID = PRODUCT_ID;
        this.QTY = QTY;
        this.PRICE = PRICE;
        this.TOTALLINE = TotalLine;
        this.DESCRIPTION = DESCRIPTION;
    }
    public UNClosedOrderLine(String ProductName,int QTY,double PRICE, String DESCRIPTION){
        this.ProductName = ProductName;
        this.QTY = QTY;
        this.PRICE = PRICE;
        this.DESCRIPTION = DESCRIPTION;
    }
    public UNClosedOrderLine(){
        
    }

    public void setO_UNCLOSEDORDERLINE_ID(int O_UNCLOSEDORDERLINE_ID) {
        this.O_UNCLOSEDORDERLINE_ID = O_UNCLOSEDORDERLINE_ID;
    }

    public void setO_UNCLOSEDORDER_ID(int O_UNCLOSEDORDER_ID) {
        this.O_UNCLOSEDORDER_ID = O_UNCLOSEDORDER_ID;
    }

    public void setPRODUCT_ID(int PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public void setPRICE(double PRICE) {
        this.PRICE = PRICE;
    }

   

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public int getO_UNCLOSEDORDERLINE_ID() {
        return O_UNCLOSEDORDERLINE_ID;
    }

    public int getO_UNCLOSEDORDER_ID() {
        return O_UNCLOSEDORDER_ID;
    }

    public int getPRODUCT_ID() {
        return PRODUCT_ID;
    }
    
    
    public String getProductName() {
        return ProductName;
    }

    public int getQTY() {
        return QTY;
    }

    public double getPRICE() {
        return PRICE;
    }

    
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }
    public void setTOTALLINE(double TOTALLINE) {
        this.TOTALLINE = TOTALLINE;
    }

    public double getTOTALLINE() {
        return TOTALLINE;
    }
    
    public String getProductName(int Product_ID ) throws Exception{
        String sqlGetProductName = " Select name from product where product_ID = "+Product_ID;
        String ProductName="";
        ResultSet rs = new Connector().start_Statment().executeQuery(sqlGetProductName);
        try {
            while(rs.next()){
                ProductName = rs.getString("name");
            }
        } catch (Exception e) {
        }
        return ProductName;
    } 
    
    public int getProductID(String name ) throws Exception{
        int Product_ID=0;
        String sqlGetProductName = " Select product_ID from product where  name= "+name;
        ResultSet rs = new Connector().start_Statment().executeQuery(sqlGetProductName);
        try {
            while(rs.next()){
                Product_ID = rs.getInt("Product_ID");
            }
        } catch (Exception e) {
        }
        return Product_ID;
    } 
   
    
    public ArrayList<UNClosedOrderLine> getList (int ID){
    ArrayList<UNClosedOrderLine> List = new ArrayList<>();
    Connector DB = new Connector();
    Connection conn;
    String sql ="select * from O_UNClosedOrderLine where O_UNClosedOrder_ID = "+ID;
    String ProductName;
    Statement st;
    ResultSet rs;
    try {
     conn =DB.open_connection();
     st=conn.createStatement();
     rs=st.executeQuery(sql);
        while (rs.next()) {
           
           UNClosedOrderLine UNClosedLines = new UNClosedOrderLine(rs.getInt("PRODUCT_ID"),rs.getInt("QTY"),rs.getDouble("Price"),rs.getDouble("TOTALLINE"),rs.getString("Description"));
          
           List.add(UNClosedLines);
            
        }
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
}
    
  
    
    public void Show_user_inTable(JTable Table ,int ID) throws Exception{
    ArrayList<UNClosedOrderLine> list = getList(ID);
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    TableINF inf = new TableINF();
    this.table = Table;
    Object [] row = new Object[6];
    for(int i = 0 ; i<list.size();i++){
      
        row[5] = i+1;
        row[4]= inf.getNameForTRX( list.get(i).getPRODUCT_ID(),"Product");
        row[3] = list.get(i).getQTY();
        row[2] = list.get(i).getPRICE();
        row[1] =list.get(i).getTOTALLINE();
        row[0] =list.get(i).getDESCRIPTION();
        
        model.addRow(row);
    }
    
}
    
     public void addUNClosedOrderLine(int master_ID,int product_ID,int QTY,String Description,JTable jTable) throws Exception{
      TableINF inf =new TableINF();
        try {//Insert into O_UNCLOSEDORDER values ()
           
            double price = inf.getProductPrice(product_ID);
            double Totalline = QTY*price;
            String sql = "Insert into O_UNClosedOrderLine  values ("+ inf.getNewID("O_UNClosedOrderLine")+","+master_ID+","+product_ID+","+QTY+","+price+","+Totalline+",'"+Description+"')";
            
            this.executeQuery(sql, "Insert",jTable,master_ID);
        } catch (SQLException ex) {
          //  Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
           // Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void executeQuery(String Query, String Message,JTable jTable,int MasterID) throws Exception{
        Connector DB=new Connector();
        Connection conn = DB.open_connection();
        Statement st;  
        try {
            st= conn.createStatement();
            if((st.executeUpdate(Query)) ==1){
                Show_user_inTable(jTable,MasterID);
             //   JOptionPane.showMessageDialog(null, "Data "+Message+" Succesfully");
            }else {
                JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully");
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully :"+e);
        }
    }
     
     public void deletelineFromOrder(int ID,int MasterID,JTable jTable) throws Exception{
         String sql = "delete from O_unclosedOrderLine where O_unclosedOrderLine_ID = "+ID;
         Statement st = Connector.start_Statment();
        try{
         st.executeQuery(sql);
          //  Show_user_inTable(jTable, ID);
        // JOptionPane.showMessageDialog(table, "Deleted sucessfully");
         
        } catch(Exception e){
            System.err.println(""+e);
        }
     }
     public void deleteOrder(int masterID,int TableNo,JTable jTable) throws Exception{
         String sql = " delete from O_unclosedOrderLine where O_unclosedOrder_ID = "+masterID;
         Statement st = Connector.start_Statment();
        try{
         st.executeQuery(sql);
       //     Show_user_inTable(jTable, masterID);
         Tables t= new Tables();
         t.resetTable(TableNo);
         UNClosedOrder or = new UNClosedOrder();
         or.deleteMasterOrder(masterID);
        } catch (Exception e){
            System.err.println(""+e);
        }
     }
     public double returnTotal(int MasterID) throws Exception{
         double Total=0;
         String sql ="select sum(TOTALLINE) total from O_unclosedOrderLine where O_unclosedOrder_ID = "+MasterID;
         Statement st = Connector.start_Statment();
         ResultSet rs;
         try {
             rs=st.executeQuery(sql);
             while(rs.next()){
                 Total =rs.getDouble("total");
             }
         } catch (Exception e) {
             System.err.println(""+e);
         }
         return Total;
     }
    
}
