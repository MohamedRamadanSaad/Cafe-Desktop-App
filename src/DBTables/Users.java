/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTables;

import DBConnect.Connector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import DBTables.TableINF;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author MR
 */

public class Users {
    int USERS_ID;
    String USERNAME;
    String PASSWORD;
    int EMPLOYEE_ID;
    String TYPE;
     String Pass="";
    private JTable table;
    
    TableINF inf;

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    
    
    public  Users(String USERNAME, String PASSWORD, String TYPE) throws Exception{
        this.USERNAME = USERNAME;
        this.PASSWORD=PASSWORD;
        this.TYPE = TYPE;
         inf = new TableINF();
        
    }
    public  Users(String USERNAME, int Employee_ID, String TYPE) throws Exception{
        this.USERNAME = USERNAME;
        this.EMPLOYEE_ID = Employee_ID;
        this.TYPE = TYPE;
        inf = new TableINF();
        
    }
    
    public  Users(){
        
    }
    
    
    
    public int getUSERS_ID() {
        return USERS_ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public int getEMPLOYEE_ID() {
        return EMPLOYEE_ID;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setUSERS_ID(int USERS_ID) {
        this.USERS_ID = USERS_ID;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setEMPLOYEE_ID(int EMPLOYEE_ID) {
        this.EMPLOYEE_ID = EMPLOYEE_ID;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
    
    public int[] LogIN(String USERNAME ,String Password , boolean Type) throws Exception{
        int [] flag = new int[2];
        int Tflag = 10;
        String inType;
        if (Type==true){
            inType ="A";
        }else {
            inType ="U";
        }
       int ID = new TableINF().getNewID("CURRENTSESSION");
       int UserID=0; 
        String sqlLogin = "select * from users where username = '"+USERNAME+"' and password = '"+Password+"' and type = '"+inType+"'";
      
// String sqlCurrentSession = "insert into currentsession values (1,"TableINF")"; 
        //هنعمل فاريابل ثابت اثناء فتح البرنامج
        
        Connection conn = new Connector().open_connection();
        Statement st = conn.createStatement();
        try {
            ResultSet rs = st.executeQuery(sqlLogin);
            while(rs.next()){
             Pass = rs.getString("Type");
             UserID=rs.getInt("Users_ID");
            }
           
            String sqlCurrentSession = "insert into CURRENTSESSION values ("+ID+","+UserID+",sysdate,'"+inType+"')"; 
             st.executeQuery(sqlCurrentSession);
            
            if(Pass!=""){
                flag[0] =1; 
                
            if(Pass=="A"){flag[1]=1;}else{flag[1]=0;}
            
            }else if (Pass==""){
                 JOptionPane.showMessageDialog(null, "Wrong Uername , Password and Type please ");
            
            }
            
        } catch (Exception e) {
           
            System.err.println("Error: "+e);
            
        }
        
      return flag;  
    }
     public ArrayList<Users> getList (){
    ArrayList<Users> List = new ArrayList<>();
    Connector DB = new Connector();
    Connection conn;
    String sql ="select * from users order by Type";
    Statement st;
    ResultSet rs;
    try {
     conn =DB.open_connection();
     st=conn.createStatement();
     rs=st.executeQuery(sql);
        while (rs.next()) {
           Users user = new Users(rs.getString("username"),rs.getInt("Employee_ID"),rs.getString("Type"));
           
           List.add(user);
            
        }
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
}
    
 
    public void Show_user_inTable(JTable Table) throws Exception{
    ArrayList<Users> list = getList();
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    this.table = Table;
    inf = new TableINF();
    Object [] row = new Object[4];
    for(int i = 0 ; i<list.size();i++){
      if (list.get(i).getTYPE().equals("U")){
          row[0]="User";
      }else  if (list.get(i).getTYPE().equals("A")){
          row[0]="Administrator";
      }
     row[1]=list.get(i).getUSERNAME();
     String f = inf.getNameForTRX(list.get(i).getEMPLOYEE_ID(), "Employee");
     row[2]=f;
     row[3]=i+1;
     model.addRow(row);
    }
}    
    public void executeQuery(String Query, String Message,JTable jTable) throws Exception{
        Connector DB=new Connector();
        Connection conn = DB.open_connection();
        Statement st;  
        try {
            st= conn.createStatement();
            if((st.executeUpdate(Query)) ==1){
               Show_user_inTable(jTable);
                JOptionPane.showMessageDialog(null, "Data "+Message+" Succesfully");
            }else {
                JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully");
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully ");
        }
    }
    public void addUser(String Username, String Password ,int Employee_ID ,String Type,JTable jtable) throws Exception{
      TableINF inf = new TableINF();
      int ID = inf.getNewID("users");
        try {//Insert into O_UNCLOSEDORDER values ()
            String sql = "Insert into users  values ("+ID +",'"+Username+"','"+Password+"',"+Employee_ID+",'"+Type+"')";
          Statement st = Connector.start_Statment();
          //st.executeQuery(sql);
            this.executeQuery(sql, "Insert",jtable);
        } catch (SQLException ex) {
            Logger.getLogger(UNClosedOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UNClosedOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void deleteUser(int ID ,JTable table) throws Exception{
      int count =inf.Count("Username","Users", " where type='A'");
      if (count<2 && inf.getUserType(ID).equals("A")){
          JOptionPane.showMessageDialog(table, "You Can Not Delete All Adminstrators , This is the last Administrator");
      }else{
         String sql = "delete from Users where Users_ID = "+ID;
       Statement st = Connector.start_Statment();
       try{
       st.executeQuery(sql);
           Show_user_inTable(table);
            JOptionPane.showMessageDialog(table, "User Deleted Sucessfully ");
       } catch (Exception e ){
          JOptionPane.showMessageDialog(table, "Error.. ");
       }}
       
   }
     public void resetPassword(int ID,String newPassword) throws Exception{
         String sql = "Update Users set Password = '"+newPassword+"' where users_ID = "+ID;
         Statement st = Connector.start_Statment();
         ResultSet rs ;
         try {
              st.executeQuery(sql);
              JOptionPane.showMessageDialog(table, "Password updated successfully");
         } catch (Exception e) {
             JOptionPane.showMessageDialog(table, "Error: Password not updated successfully");
         }
         
     }
     
}
