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
public class WareHouseLine {
    private int WAREHOUSELINE_ID;
    private int WAREHOUSE_ID;
    private int PRODUCTGROUP_ID;
    private int QTY;
    private Timestamp DATECREATED;
    private int CREATEDBY;
    private JTable table;
    TableINF inf;
    
    public WareHouseLine (int WAREHOUSE_ID,int PRODUCTGROUP_ID ) throws Exception{
        this.PRODUCTGROUP_ID =PRODUCTGROUP_ID;
        this.WAREHOUSE_ID = WAREHOUSE_ID;
        inf = new TableINF();
        
    }
    public WareHouseLine() throws Exception{
        inf = new TableINF();
    }
    
    public int getWAREHOUSELINE_ID() {
        return WAREHOUSELINE_ID;
    }

    public int getWAREHOUSE_ID() {
        return WAREHOUSE_ID;
    }

    public int getPRODUCTGROUP_ID() {
        return PRODUCTGROUP_ID;
    }

    public int getQTY() {
        return QTY;
    }

    public Timestamp getDATECREATED() {
        return DATECREATED;
    }

    public int getCREATEDBY() {
        return CREATEDBY;
    }

    public void setWAREHOUSELINE_ID(int WAREHOUSELINE_ID) {
        this.WAREHOUSELINE_ID = WAREHOUSELINE_ID;
    }

    public void setWAREHOUSE_ID(int WAREHOUSE_ID) {
        this.WAREHOUSE_ID = WAREHOUSE_ID;
    }

    public void setPRODUCTGROUP_ID(int PRODUCTGROUP_ID) {
        this.PRODUCTGROUP_ID = PRODUCTGROUP_ID;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public void setDATECREATED(Timestamp DATECREATED) {
        this.DATECREATED = DATECREATED;
    }

    public void setCREATEDBY(int CREATEDBY) {
        this.CREATEDBY = CREATEDBY;
    }
  CurrentSession cur = new CurrentSession();
  public ArrayList<WareHouseLine> getList (){
    ArrayList<WareHouseLine> list = new ArrayList<>();
    Connector DB = new Connector();
    Connection conn;
    String sql ="select * from WareHouseLine ";
    Statement st;
    ResultSet rs;
    try {
     conn =DB.open_connection();
     st=conn.createStatement();
     rs=st.executeQuery(sql);
        while (rs.next()) {
           WareHouseLine line = new WareHouseLine(rs.getInt("WAREHOUSE_ID"),rs.getInt("ProductGroup_ID"));
          
           list.add(line);
            
        }
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return list;
}
 public void Show_user_inTable(JTable Table) throws Exception{
    ArrayList<WareHouseLine> list = getList();
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    this.table = Table;
    Object [] row = new Object[3];
    for(int i = 0 ; i<list.size();i++){
      
        row[2] = i+1;
        row[1] =inf.getNameForTRX(list.get(i).getWAREHOUSE_ID(), "WareHouse");
        row[0] =inf.getNameForTRX(list.get(i).getPRODUCTGROUP_ID(), "ProductGroup");
       
        
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
  public void updateWarehouseLine(int WHNIDnew,int PGIDnew ,int IDLine,JTable Jtable) throws SQLException, Exception{
       
       String sql = " Update warehouseline set warehouse_ID= "+WHNIDnew+" , productGroup_ID = "+PGIDnew+"  where warehouseline_ID = "+IDLine;
       executeQuery(sql, "Update", Jtable);
       
   }
     public void DeleteWarehouseLine(int ID, JTable JTable) throws Exception{
     TableINF inf = new TableINF();
      String sql= " delete from warehouseline where warehouseline_Id= "+ID+"";
      int count;
     
           executeQuery(sql, " Delete ",JTable );
       
   }
     
     public void addWarehouseLine( int WHID,int PGID ,JTable table) throws Exception{
      TableINF inf =new TableINF();
        try {//Insert into O_UNCLOSEDORDER values ()
            String sql = "Insert into warehouseline  values ("+ inf.getNewID("warehouseline")+","+WHID+","+PGID+",sysdate,"+cur.getCurrentUserID()+")";
            this.executeQuery(sql, "Insert",table);
        } catch (SQLException ex) {
        
        } catch (Exception ex) {
           // Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
