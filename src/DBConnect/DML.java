/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnect;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author MR
 */
public class DML extends Connector {
    
    //conn;
    String sql;
    Map columns;
    
    public void doInsert(String []columnsStrings ,String TableString,String []ValuesStrings,String Where) throws SQLException{
        
        String INSERT = "Insert ";
        String INTO = " into "+TableString;
        String VALUES = " Values ";
        String WHERE = " Where ";
        
        for(int i = 0 ; i <= columnsStrings.length;i++){
            INSERT+=" "+columnsStrings[i];
            VALUES+=" "+ValuesStrings[i];
        }
        sql = INSERT+INTO+VALUES+WHERE;
       try{
           st.executeQuery(sql);
       }catch(Exception e){
           System.err.println("Error : "+e.getMessage());
       }
    }
    
    public void doInsert(String []columnsStrings ,String TableString,String []ValuesStrings) throws SQLException{
        
        String INSERT = "Insert ";
        String INTO = " into "+TableString;
        String VALUES = " Values ";
        
        
        for(int i = 0 ; i <= columnsStrings.length;i++){
            INSERT+=" "+columnsStrings[i];
            VALUES+=" "+ValuesStrings[i];
        }
        sql = INSERT+INTO+VALUES;
       try{
           st.executeQuery(sql);
       }catch(Exception e){
           System.err.println("Error : "+e.getMessage());
       }
    }
    
    public void doInsert(String ColumnString , String TableString,Object ValueString){
        sql = "insert "+ColumnString+" into "+TableString+" Values "+ValueString;
       try{
           st.executeQuery(sql);
       }catch(Exception e){
           System.err.println("Error : "+e.getMessage());
       }
    }
    
   
   
   
}
