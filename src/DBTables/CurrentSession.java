/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTables;

import DBConnect.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MR
 */
public class CurrentSession {
 int USER_ID;
    String Type ;
    
    String sql = "SELECT C.USER_ID id , C.TYPE t" +
 " FROM CurrentSession c "+
 " WHERE C.CURRENTSESSION_ID = (SELECT MAX (d.CURRENTSESSION_ID) " +
                               " FROM CurrentSession d)";
   public String getCurrentUserType() throws SQLException, Exception{
       ResultSet rs = new Connector().start_Statment().executeQuery(sql);
        while (rs.next()) {
            
            USER_ID =rs.getInt("id");
            Type = rs.getString("t");
          //  System.out.println(USER_ID + " "+ rs.getInt("id") +" "+Type+" "+rs.getString("t")+"");
            
            
        }
        return Type;
   }
   public int getCurrentUserID() throws SQLException, Exception{
       ResultSet rs = new Connector().start_Statment().executeQuery(sql);
        while (rs.next()) {
            
            USER_ID =rs.getInt("id");
            Type = rs.getString("t");
          //  System.out.println(USER_ID + " "+ rs.getInt("id") +" "+Type+" "+rs.getString("t")+"");
            
            
        }
        return USER_ID;
   }
    
    public CurrentSession() {
        
    }
    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

   
    public int getUSER_ID() {
        return USER_ID;
    }

    public String getType() {
        return Type;
    }
    
   
    
    
}
