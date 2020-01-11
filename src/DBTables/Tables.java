/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTables;

import DBConnect.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author MR
 */
public class Tables {
    
    String sqlTablesNo = "select count(T_TableNo_ID) no from T_TableNo";
    String sqlTableName = "select T_TableNo_ID name from T_TableNo where T_TableNo_ID =";
    String TableActiveNow = "select count(T_TableNo_ID) no from T_TableNo where status = 'Y' ";
    String TableDisactivNow = "select count(T_TableNo_ID) no from T_TableNo where status = 'N' ";
    int tableNo,tablename[];
    Statement st;
    public  Tables () throws Exception{
        st = new Connector().start_Statment(); 
    }
    public int getTablesNo() throws Exception{
      
      ResultSet rs = st.executeQuery(sqlTablesNo);
      while(rs.next()){
          tableNo = rs.getInt("no");
      }
      return tableNo;
    }
    public boolean isTakeAwayCompleted() throws Exception{// if complete return true
        boolean Status =false;
        String sql ="select count(T_Tableno_ID) cn from o_unclosedorder where T_Tableno_ID = 100 and docStatus ='D'";
    int Number=0;
        Statement st =Connector.start_Statment();
    ResultSet rs;
        try {
            rs = st.executeQuery(sql);
    while (rs.next()){
        Number = rs.getInt("cn");
        
    }        
        } catch (Exception e) {
            System.err.println(""+e);
        }
    if (Number > 0 ){
        Status =false;
    }else {
        Status =true;
    }
    return Status;
    }
    public boolean isTableFree(int TableNo) throws Exception{
        boolean Status=false ;
        String TableStatus="N";
        String sql = "Select status from t_tableNO where T_TableNO_ID = "+TableNo;
        Statement st = Connector.start_Statment();
        ResultSet rs ;
        try {
            rs = st.executeQuery(sql);
            while (rs.next()){
                TableStatus = rs.getString("status");
            }
        } catch (Exception e) {
            System.err.println(""+e);
        }
 if (TableStatus.equalsIgnoreCase("Y")){
     Status = false;// not free return false 
 }else if (TableStatus.equalsIgnoreCase("N")){
     Status = true;// free return true 
 }
 return Status;
    }
    public int getTablesName() throws Exception{
        int tableNo = 0;
        int i =0;
        tablename = new int[tableNo];
        sqlTableName+=" "+tablename[i];
         ResultSet rs = st.executeQuery(sqlTableName);
         while(rs.next()){
             tableNo= rs.getInt("name");
            i++;
             return tableNo;  
         }
         return 0;
    }
    
    public int getTablesActiveNo() throws SQLException{
        int No=0;
        ResultSet rs =st.executeQuery(TableActiveNow);
        while (rs.next()){
            No=rs.getInt("no");
        }
        return No;
    }
    public int getTablesFreeNo() throws SQLException{
        int No=0;
        ResultSet rs =st.executeQuery(TableDisactivNow);
        while (rs.next()){
            No=rs.getInt("no");
        }
        return No;
    }
    public void resetTable(int i) throws Exception{
        String sql = "update T_TableNO set STATUS ='N' where T_TableNO_ID ="+i;
        Statement st =Connector.start_Statment();
        st.executeQuery(sql);
    }
     public void updateTableNumbers(int NewTableNo) throws SQLException, Exception{
       // String in = JOptionPane.showInputDialog("Enter number of tables you want ");
        
       /* for(int i =1 ;i<= NewTableNo ; i++){
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT (T_TABLENO_ID) INTO T_TABLENO " +
                       "VALUES ("+i+")");
        }*/
        try
    {
      
      String in = JOptionPane.showInputDialog("Enter number of tables you want ");
       
        st.executeQuery("Truncate table T_TABLENO");
        for(int i =1 ;i<= NewTableNo ; i++){
      st.executeUpdate("INSERT  INTO T_TABLENO " +
                       "VALUES ("+i+",0)");
        }
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
    }
    
    
}
