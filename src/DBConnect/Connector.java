/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnect;

/**
 *
 * @author MR
 */
import java.sql.*;
import javax.swing.JOptionPane;


public class Connector
{
  public Connection conn;
  static Statement st;
  
  static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
  static String USERNAME = "ca13";
  static String PASSWORD = "bc4j";
  int tableentered;
  static private  Connection con;

 /* public static void main(String[] args)
  {
    new Connector();
  }*/
 
  public static Connection open_connection() throws Exception {
     try{
      if (con ==null){
       Class.forName("oracle.jdbc.OracleDriver" ).newInstance(); //"com.oracle.jdbc.Driver"
      con = DriverManager.getConnection(url, USERNAME, PASSWORD);
      }}catch(Exception e){
          JOptionPane.showMessageDialog(null, "There is no Databade Found please Contact Administrator ..");
//           JOptionPane.showMessageDialog(null, ""+e);
          
          
          e.printStackTrace();
      }
      
      return con;
  }
  
   public static Statement start_Statment() throws Exception {
     /*  Class.forName("oracle.jdbc.OracleDriver" ).newInstance(); //"com.oracle.jdbc.Driver"
      conn = DriverManager.getConnection(url, USERNAME, PASSWORD);*/
      
      Statement st = open_connection().createStatement();
      
      return st;
  }
  public void close_connection() throws SQLException{
      try{
      conn.close();
      } catch (Exception e){
          System.out.println("Error : "+e);
      }    
  }
  
  public Connector()
  {
  }

  
}


