/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBViews;

import DBConnect.Connector;
import DBTables.CurrentSession;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author MR
 */
public class usersnames_Rv {
    int User_ID ;
    String sql;
   
    public String getCurrentUserName() throws Exception{
        User_ID = new CurrentSession().getCurrentUserID();
        String name="";
        sql = " select name from usersnames_Rv where USERS_ID = "+User_ID;
        Statement st = new Connector().start_Statment();
         ResultSet rs = st.executeQuery(sql);
         while (rs.next()){
             name = rs.getString("name");
         }
         //System.out.println(""+name);
         return name;
    }
    public static void main(String[] args) throws Exception {
        usersnames_Rv u = new usersnames_Rv();
        u.getCurrentUserName();
    }
}
