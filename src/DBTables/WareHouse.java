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
public class WareHouse {
    private int WareHouse_ID ;
    private String Name ;
    private Timestamp DateCreated; 
    private int CreatedBy;
    private double WarehouseCost ;
    private JTable table;
    
    public WareHouse(String Name ){
        this.Name = Name;
    }
    public WareHouse(){
        
    }

    public void setWareHouse_ID(int WareHouse_ID) {
        this.WareHouse_ID = WareHouse_ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDateCreated(Timestamp DateCreated) {
        this.DateCreated = DateCreated;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setWarehouseCost(double WarehouseCost) {
        this.WarehouseCost = WarehouseCost;
    }

    public int getWareHouse_ID() {
        return WareHouse_ID;
    }

    public String getName() {
        return Name;
    }

    public Timestamp getDateCreated() {
        return DateCreated;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public double getWarehouseCost() {
        return WarehouseCost;
    }
    
     CurrentSession cur = new CurrentSession();
  public ArrayList<WareHouse> getList (){
    ArrayList<WareHouse> List = new ArrayList<>();
    Connector DB = new Connector();
    Connection conn;
    String sql ="select * from WareHouse ";
    Statement st;
    ResultSet rs;
    try {
     conn =DB.open_connection();
     st=conn.createStatement();
     rs=st.executeQuery(sql);
        while (rs.next()) {
           WareHouse pgroup = new WareHouse(rs.getString("name"));
          
           List.add(pgroup);
            
        }
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
}
 public void Show_user_inTable(JTable Table){
    ArrayList<WareHouse> list = getList();
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    this.table = Table;
    Object [] row = new Object[2];
    for(int i = 0 ; i<list.size();i++){
      
        row[1] = i+1;
        row[0] =list.get(i).getName();
       
        
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
             JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully ");
        }
    }
  
   public void updateWarehouse(String name ,int ID,JTable Jtable) throws SQLException, Exception{
       
       String sql = " Update warehouse set name= '"+name+"'  where warehouse_ID = "+ID;
       executeQuery(sql, "Update", Jtable);
       
   }
  
  public int getWarehouseID(String Warehousename,JTable table) throws Exception{
       int ID = 0 ;
        String IDString = " select warehouse_ID id from warehouse where name = '"+Warehousename+"'";
         Statement st = new  Connector().start_Statment();
        try {
           ResultSet rs =st.executeQuery(IDString);
          while (rs.next()){
              ID = rs.getInt("ID");
    
          }
      } catch (Exception e) {
      }
     return ID ; 
  }
 public void addWarehouse(JTextField name ,JTable table) throws Exception{
      TableINF inf =new TableINF();
        try {//Insert into O_UNCLOSEDORDER values ()
            String sql = "Insert into warehouse  values ("+ inf.getNewID("warehouse")+",'"+name.getText()+"',sysdate,"+cur.getCurrentUserID()+",0)";
            this.executeQuery(sql, "Insert",table);
        } catch (SQLException ex) {
        
        } catch (Exception ex) {
           // Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
 public void DeleteWarehouse(int ID, JTable JTable) throws Exception{
     TableINF inf = new TableINF();
      String sql= " delete from warehouse where warehouse_Id= "+ID+"";
      int count;
      String where = " where warehouse_ID = "+ID;
       count = inf.Count("productgroup_ID", "warehouseline", where);
       if(count>0){
           JOptionPane.showMessageDialog(null, "You have productGroup that related to this Warehouse , you must Define those ProductGroup to another warehouse before Delete operation..");
       } else 
       {
           executeQuery(sql, " Delete ",JTable );
       }
   }
}
