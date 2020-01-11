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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MR
 */
public class Employee {
private int Employee_ID;
    private String Name ;
    private int Age;
    private String Phone;
    private String Email;
    private String Adress;
    private JTable table;
    
    public Employee(String Name , int Age , String Phone , String Email,String Adress ){
        this.Name =Name;
        this.Age = Age;
        this.Phone = Phone;
        this.Email= Email;
        this.Adress = Adress;
    }
    public Employee(){
        
    }
    public int getEmployee_ID() {
        return Employee_ID;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getAdress() {
        return Adress;
    }

    public void setEmployee_ID(int Employee_ID) {
        this.Employee_ID = Employee_ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }
    
     public ArrayList<Employee> getList (){
    ArrayList<Employee> List = new ArrayList<>();
    Connector DB = new Connector();
    Connection conn;
    String sql ="select * from Employee ";
    Statement st;
    ResultSet rs;
    try {
     conn =DB.open_connection();
     st=conn.createStatement();
     rs=st.executeQuery(sql);
        while (rs.next()) {
           Employee Emp = new Employee(rs.getString("name"),rs.getInt("Age"),rs.getString("Phone"),rs.getString("Email"),rs.getString("Adress"));
           
           List.add(Emp);
            
        }
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
}
    public void Show_user_inTable(JTable Table){
    ArrayList<Employee> list = getList();
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    this.table = Table;
    Object [] row = new Object[6];
    for(int i = 0 ; i<list.size();i++){
      
        row[5] = i+1;
        row[4] = list.get(i).getName();
        
        row[3] = list.get(i).getAge();
       
        if(list.get(i).getAdress()== null){
            row[2]="";
        }else{
        row[2] = list.get(i).getAdress();
        }
        if (list.get(i).getPhone() ==null){
            row[1]="";
        }else {
        row[1] =list.get(i).getPhone();
        }
        if (list.get(i).getEmail() ==null){
         row[0]="";   
        }else{
        row[0] =list.get(i).getEmail();
        }
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
    public void addEmployee(String Name , int Age ,  String Adress ,String Phone , String Email,JTable jtable) throws Exception{
      TableINF inf = new TableINF();
      int ID = inf.getNewID("Employee");
        try {//Insert into O_UNCLOSEDORDER values ()
            String sql = "Insert into employee  values ("+ID +",'"+Name+"',"+Age+",'"+Phone+"','"+Email+"','"+Adress+"')";
          Statement st = Connector.start_Statment();
          //st.executeQuery(sql);
            this.executeQuery(sql, "Insert",jtable);
        } catch (SQLException ex) {
            Logger.getLogger(UNClosedOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UNClosedOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void updateEmployee(int OldID,String NewName ,int Age, String Adress , String Phone , String Email ,JTable table) throws Exception{
       String sql = "update employee set name = '"+NewName+"',Age = "+Age+", Adress = '"+Adress+"' ,Phone = '"+Phone+"' ,email = '"+Email+"'   where Employee_ID = "+OldID+"";
   Statement st = Connector.start_Statment();
   try{
   st.executeQuery(sql);
      Show_user_inTable(table);
   JOptionPane.showMessageDialog(null, "Employee Updated Sucessfully ");
    
   }catch (Exception e){
       System.err.println(""+e);
   }
   
   }
     
     public void deleteEmployee(int ID ,JTable table) throws Exception{
       String sql = "delete from employee where Employee_ID = "+ID;
       Statement st = Connector.start_Statment();
       try{
       st.executeQuery(sql);
           Show_user_inTable(table);
            JOptionPane.showMessageDialog(table, "Employee Deleted Sucessfully ");
       } catch (Exception e ){
          JOptionPane.showMessageDialog(table, "This Employee have a user you must delete the user first ");
       }
       
   }
     
}
