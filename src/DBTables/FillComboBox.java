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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author MR
 */
public class FillComboBox {
    Statement st ;
    Connector DB;
    ResultSet rs ;
    ProductGroup pg;
    TableINF inf ;
    public FillComboBox () throws Exception{
        DB = new Connector();
        st = DB.start_Statment();
       inf = new TableINF();
    }
    
    public void fillCB(JComboBox CB ,String column, String Table) throws SQLException, Exception{
        String sql = "select "+column+" from "+Table+" ";
        Object obj;
        int i = 0;
        // know wich table i will get from it 
        
        Object [] output= new Object[inf.Count(column, Table,"")];
        rs= st.executeQuery(sql);
        while(rs.next()){
            try {
                output[i]= rs.getString(column); 
            }catch(Exception e){
                 output[i]= rs.getInt(column); 
            }
            i++;
        }
        CB.removeAllItems();
         CB.setModel(new DefaultComboBoxModel(output));
        
    }
    public void fillCBOrders(JComboBox cb ,String column, String table ,String where) throws SQLException, Exception{
        String sql = "select "+column+" from "+table+" where = "+where+" ";
        Object obj;
        int i = 0;
        // know wich table i will get from it 
        
        Object [] output= new Object[inf.Count(column, table,"")];
        rs= st.executeQuery(sql);
        while(rs.next()){
            try {
                output[i]= rs.getString(column); 
            }catch(Exception e){
                 output[i]= rs.getInt(column); 
            }
            i++;
        }
        cb.removeAllItems();
         cb.setModel(new DefaultComboBoxModel(output));
        
    }
    public void fillCBFreeTables(JComboBox cb ) throws SQLException, Exception{
        String sql = "select T_TableNo_ID ta from T_TableNo where status= 'N' order by T_TableNo_ID ";
        Object obj;
        int i = 0;
        // know wich table i will get from it 
        
        Object [] output= new Object[inf.countFreeTables()];
        rs= st.executeQuery(sql);
        while(rs.next()){
                 output[i]= rs.getInt("ta"); 
            i++;
        }
        cb.removeAllItems();
         cb.setModel(new DefaultComboBoxModel(output));
        
    }
    
    public void fillCBUNFreeTables(JComboBox cb ) throws SQLException, Exception{
        String sql = "select T_TableNo_ID ta from T_TableNo where status= 'Y'  order by T_TableNo_ID ";
        Object obj;
        int i = 0;
        // know wich table i will get from it 
        
        Object [] output= new Object[inf.countUNFreeTables()];
        rs= st.executeQuery(sql);
        while(rs.next()){
                 output[i]= rs.getInt("ta"); 
            
            i++;
        }
        cb.removeAllItems();
         cb.setModel(new DefaultComboBoxModel(output));
        
    }
    public void fillCBSpecifiedTables(JComboBox cb ,JTextField person,int NO) throws SQLException, Exception{
        String sql = "select T_TableNo_ID ta from T_TableNo where T_TableNo_ID ="+NO+"  order by T_TableNo_ID ";
        Object obj;
        int i = 0;
        // know wich table i will get from it 
        
        Object [] output= new Object[inf.countUNFreeTables()];
        rs= st.executeQuery(sql);
        while(rs.next()){
                 output[i]= rs.getInt("ta"); 
            
            i++;
        }
        cb.removeAllItems();
         cb.setModel(new DefaultComboBoxModel(output));
         person.setEnabled(false);
         cb.setEnabled(false);
        
    }
    
}
