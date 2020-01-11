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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MR
 */
public class UOM {

    
    private int UOM_ID ;
    private String NAME;
    private Timestamp DATECREATED;
    private int CREATEDBY;
    private String SYMBOL;
    Connector conn ;
    Statement st ;
    CurrentSession cur;
    TableINF inf ;
    
    public UOM(int uom_id , String name,String Symbol) throws Exception{
        this.UOM_ID= uom_id;
        this.NAME = name;
        this.SYMBOL =Symbol;
        conn = new Connector();
        st = conn.start_Statment();
    }
    public UOM () throws Exception{
       conn = new Connector();
        st = conn.start_Statment(); 
        cur = new CurrentSession();
        inf=new TableINF();
    }

    public void setUOM_ID(int UOM_ID) {
        this.UOM_ID = UOM_ID;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setDATECREATED(Timestamp DATECREATED) {
        this.DATECREATED = DATECREATED;
    }

    public void setCREATEDBY(int CREATEDBY) {
        this.CREATEDBY = CREATEDBY;
    }
    
    
    public int getUOM_ID() {
        return UOM_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public Timestamp getDATECREATED() {
        return DATECREATED;
    }

    public int getCREATEDBY() {
        return CREATEDBY;
    }
     public String getSYMBOL() {
        return SYMBOL;
    }

    public void setSYMBOL(String SYMBOL) {
        this.SYMBOL = SYMBOL;
    }
      
    public ArrayList<UOM> getList () throws Exception{
    ArrayList<UOM> List = new ArrayList<>();
  
    String sql ="select * from uom ";
    
    ResultSet rs = null;
    try {
     
     rs= st.executeQuery(sql);
        while (rs.next()) {
           UOM product = new UOM(rs.getInt("UOM_ID"),rs.getString("name"),rs.getString("Symbol"));
           
           List.add(product);
            
        }
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
}
   public void Show_user_inTable(JTable Table) throws Exception{
    ArrayList<UOM> list = getList();
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    TableINF DBTable = new TableINF();
    Object [] row = new Object[3];
    for(int i = 0 ; i<list.size();i++){
      
        
      row[0]=list.get(i).getSYMBOL();
         row[1] =list.get(i).getNAME();
        row[2] =i+1;
        
     
        
        model.addRow(row);
    }
    
    
}
   public void executeQuery(String Query, String Message,JTable table) throws Exception{
        Connector DB=new Connector();
        Connection conn = DB.open_connection();
        Statement st;  
        try {
            st= conn.createStatement();
            if((st.executeUpdate(Query)) ==1){
                Show_user_inTable(table);
                JOptionPane.showMessageDialog(null, "Data "+Message+" Succesfully");
            }else {
                JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully");
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully :");
        }
    }
   public void addUOM(JTextField name,JTextField Symbol ,JTable Table) throws Exception{
      TableINF table =new TableINF();
        try {//Insert into O_UNCLOSEDORDER values ()
            String sql = "Insert into UOM  values ("+ table.getNewID("UOM")+",'"+name.getText().toString()+"',sysdate,"+cur.getCurrentUserID()+",'"+Symbol.getText().toString()+"')";
            this.executeQuery(sql, "Insert",Table);
        } catch (SQLException ex) {
         //   Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
           // Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
  
   public void updateUOM(String name,String Symbol ,int ID,JTable Jtable) throws SQLException, Exception{
       
       String sql = " Update UOM set name= '"+name+"' , Symbol = '"+Symbol+"' where UOM_ID = "+ID;
       executeQuery(sql, "Update", Jtable);
       
   }
   public void DeleteUOM(int ID, JTable JTable) throws Exception{
      inf = new TableINF();
      String sql= " delete from UOM where UOM_ID= "+ID+"";
      int count;
      String where = " where UOM_ID = "+ID;
       count = inf.Count("UOM_ID", "Product", where);
       if(count>0){
           JOptionPane.showMessageDialog(null, "You have products that related to this UOM , you must Define those Products to another UOM before Delete operation..");
       } else 
       {
           executeQuery(sql, " Delete ",JTable );
       }
   }
}
