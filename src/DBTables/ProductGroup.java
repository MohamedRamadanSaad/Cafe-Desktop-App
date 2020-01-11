/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTables;


import DBConnect.Connector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
public class ProductGroup {
     private int PRODUCTGROUP_ID;
    private String NAME ;
    private Date DATECREATED ;
    private int CREATEDBY;
    private String DESCRIPTION;
    private JTable table;
    private JTextField [] TextFileds;
    Connector DB ;
    Statement st;
    
    public ProductGroup (int PRODUCTGROUP_ID ,String NAME,Date DATECREATED , int CREATEDBY,String DESCRIPTION){
        this.PRODUCTGROUP_ID = PRODUCTGROUP_ID ;
        this.NAME=NAME;
        this.DATECREATED =DATECREATED;
        this.CREATEDBY=CREATEDBY;
        this.DESCRIPTION=DESCRIPTION;
        
    } 
    public ProductGroup() throws Exception{
        DB = new Connector();
        st= DB.start_Statment();
    }
    public ProductGroup (String NAME,String DESCRIPTION){
        
        this.NAME=NAME;
        
        this.DESCRIPTION=DESCRIPTION;
        
    } 
    public int getPRODUCTGROUP_ID(){
        return PRODUCTGROUP_ID;
    }
    
    public void setPRODUCTGROUP_ID(int PRODUCTGROUP_ID){
        this.PRODUCTGROUP_ID = PRODUCTGROUP_ID;
    }
    
     public String getNAME(){
        return NAME;
    }
    
    public void setNAME(String NAME){
        this.NAME = NAME;
    }
    
     public Date getDATECREATED(){
        return DATECREATED;
    }
    
    public void setDATECREATED(Date DATECREATED){
        this.DATECREATED = DATECREATED;
    }
    
    public float getCREATEDBY(){
        return CREATEDBY;
    }
    
    public void setCREATEDBY(int CREATEDBY){
        this.CREATEDBY = CREATEDBY;
    }
    
    public String getDESCRIPTION(){
        return DESCRIPTION;
    }
    
    public void setDESCRIPTION(String DESCRIPTION){
        this.DESCRIPTION = DESCRIPTION;
    }
    
    public ArrayList<ProductGroup> getList (){
    ArrayList<ProductGroup> List = new ArrayList<>();
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
           ProductGroup pgroup = new ProductGroup(rs.getString("name"),rs.getString("description"));
          
           List.add(pgroup);
            
        }
        rs.close();
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
}
 public void Show_user_inTable(JTable Table){
    ArrayList<ProductGroup> list = getList();
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    this.table = Table;
    Object [] row = new Object[3];
    for(int i = 0 ; i<list.size();i++){
      
        row[2] = i+1;
        row[1] =list.get(i).getNAME();
        row[0] =list.get(i).getDESCRIPTION();
        
        model.addRow(row);
    }
}
  public void addProductGroup(JTextField name,JTextField Description) throws Exception{
      TableINF table =new TableINF();
        try {//Insert into O_UNCLOSEDORDER values ()
            String sql = "Insert into ProductGroup  values ("+ table.getNewID("ProductGroup")+",'"+name.getText()+"',sysdate,100,'"+Description.getText()+"')";
            this.executeQuery(sql, "Insert");
        } catch (SQLException ex) {
         //   Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
           // Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
  public void executeQuery(String Query, String Message) throws Exception{
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
             JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully :"+e);
        }
    }
  public void update(JTextField[] TXF) throws Exception{
      int commingLength =TXF.length;
      
      JTextField [ ]innerTXF= new JTextField[commingLength];
      for (int i =0 ; i<commingLength ;i++){
          innerTXF[i]=TXF[i];
      }
     int ID= getPGID(innerTXF[0].getText().toString());
      String nameTXT = innerTXF[0].getText().toString();
      String DescriptionTXT = innerTXF[1].getText().toString();
      Statement st = new  Connector().start_Statment();
      String sql;
      sql = "Update productGroup set name = '"+ nameTXT+"' ,Description = '"+DescriptionTXT+"' where productGroup_ID = "+ID+"";
      this.executeQuery(sql, "Update");
      Show_user_inTable(table);
  }
  
  public int getPGID(String PGname) throws Exception{
       int ID = 0 ;
        String IDString = " select productgroup_ID id from productGroup where name = '"+PGname+"'";
         Statement st = new  Connector().start_Statment();
        try {
           ResultSet rs =st.executeQuery(IDString);
          while (rs.next()){
              ID = rs.getInt("ID");
    
          }
          rs.close();
      } catch (Exception e) {
      }
     return ID ; 
  }
  public void DeleteProductGroup(String PGName) throws Exception{
      ResultSet rs;
      Statement st =new Connector().start_Statment();
      int numberOfProductInGroup = 0;
      String sqlCheckforProductST =" select count(name) cn from product where productgroup_ID ='"+getPGID(PGName)+"'";
      String sqlDelete = " delete from productGroup where name = '"+PGName+"'";
      try {
          rs= st.executeQuery(sqlCheckforProductST);
          while (rs.next()){
              numberOfProductInGroup = rs.getInt("cn");
              
          }
          rs.close();
      } catch (Exception e) {
          System.err.println("Error "+e);
      }
      if (numberOfProductInGroup !=0){
          JOptionPane.showMessageDialog(table, "You can not delete group contain products , you must replace product in another group first");
      }else {
      
      try {
          rs=st.executeQuery(sqlDelete);
          JOptionPane.showMessageDialog(table, "Group Deleted Sucessfully");
          Show_user_inTable(table);
          rs.close();
      } catch (Exception e) {
JOptionPane.showMessageDialog(table, e);
      }}
  }
   public int checkIfProductGroupFound(String name ) throws SQLException{
      int flag = 0; 
      int ifGroupFound = 0;
      String sql = "Select count(name) cn from productGroup where name = '"+name+"' ";
      ResultSet rs = st.executeQuery(sql);
      while(rs.next()){
          ifGroupFound = rs.getInt("cn");
      }
      if(ifGroupFound >0){
          flag=1;
      }
      rs.close();
      return flag;
  }
   public int CountProductGroup() throws SQLException{
       int output=0;
       String sql = "select Count(name) cn from productgroup";
       ResultSet rs = st.executeQuery(sql);
       while(rs.next()){
           output = rs.getInt("cn");
       }
       rs.close();
       return output;
   }
}

