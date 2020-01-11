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
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author MR
 */
public class TableINF {
    
    Statement st;
    public TableINF() throws Exception{
        
     
        
    }
    public int getNewID(String DBTable) throws Exception{
        int newID =1;
        try{
        
        String sql = "Select max("+DBTable+"_ID) no from "+DBTable;
        st =  Connector.start_Statment();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
        newID = rs.getInt("no") +1;
        }
        System.out.println("New ID Is "+newID);
        rs.close();
       }catch (Exception e){
           System.err.println(e);
       }
       
        st.close();
        return newID;
        
    }
    
     public int getProductGroupID(String groupname) throws Exception{
        int ID =0 ;
        String sql = "select productgroup_ID from productgroup where name ="+groupname;
        st =  Connector.start_Statment();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            ID = rs.getInt("productgroup_ID");
        }
        rs.close();
        st.close();
        return ID; 
    }
     
     public String getProductGroupName(int productgroup_ID) throws Exception{
        String Name ="" ;
        String sql = "select name from productgroup where  productgroup_ID="+productgroup_ID;
        st =  Connector.start_Statment();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            Name = rs.getString("name");
        }
        rs.close();
        st.close();
        return Name; 
    }
     
     public String getNameForTRX(int ID ,String TableName) throws Exception{
        String Name ="" ;
        String sql = "select name from "+TableName+" where  "+TableName+"_ID="+ID;
        st =  Connector.start_Statment();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            Name = rs.getString("name");
        }
        rs.close();
        st.close();
        return Name; 
    }
     
     
    public int getIDForTRX(String table , String name) throws SQLException, Exception{
        
       String sql = " Select "+table+"_ID from "+table+" where name = '"+name+"'";
       int ID =0;
       st =  Connector.start_Statment();
       ResultSet rs = st.executeQuery(sql);
       while (rs.next()){
           ID =rs.getInt(table+"_ID");
       
   }
       rs.close();
       st.close();
       return ID;
    }
     public int getUserIDForTRX(String table , String name) throws SQLException, Exception{
        
       String sql = " Select "+table+"_ID from "+table+" where username = '"+name+"'";
       int ID =0;
       st =  Connector.start_Statment();
       ResultSet rs = st.executeQuery(sql);
       while (rs.next()){
           ID =rs.getInt(table+"_ID");
       
   }
       rs.close();
       st.close();
       return ID;
    }
    // for warehouse line 
    public int getIDLineForTRX(String table , int WHID ,int PGID) throws SQLException, Exception{
        
       String sql = " Select "+table+"_ID from "+table+" where warehouse_ID = "+WHID+" and productGroup_ID = "+PGID;
       int ID =0;
       st =  Connector.start_Statment();
       ResultSet rs = st.executeQuery(sql);
       while (rs.next()){
           ID =rs.getInt(table+"_ID");
       
   }
       rs.close();
       st.close();
       return ID;
    }
    
     public int Count(String column , String Table ,String where) throws SQLException, Exception{
       int output=0;
       String inWhere =where ;
       if(where.equals("")){
           inWhere = "";
       }else if (where != ""){
           inWhere = "  "+where;
       }
       st =  Connector.start_Statment();
       String sql = "select Count("+column+") cn from "+Table+" "+inWhere+"";
       ResultSet rs = st.executeQuery(sql);
       while(rs.next()){
           output = rs.getInt("cn");
       }
       rs.close();
       st.close();
       return output;
   }
     
     public int countUNFreeTables() throws SQLException, Exception{
       int output=0;
       String sql = "select Count(T_TableNo_ID) cn from T_TableNO where Status ='Y'";
       st = Connector.start_Statment();
       ResultSet rs = st.executeQuery(sql);
       while(rs.next()){
           output = rs.getInt("cn");
       }
       rs.close();
       st.close();
       return output;
   }
      public int countFreeTables() throws SQLException, Exception{
       int output=0;
       String sql = "select Count(T_TableNo_ID) cn from T_TableNO where Status ='N'";
       st = Connector.start_Statment();
       ResultSet rs = st.executeQuery(sql);
       while(rs.next()){
           output = rs.getInt("cn");
       }
       rs.close();
       st.close();
       return output;
   }
      public double getProductPrice(int ID) throws Exception{
          double price =0 ;
          String sql = " Select saleprice pr from product where product_ID = "+ID+" ";
          Statement st = Connector.start_Statment();
          ResultSet rs = st.executeQuery(sql);
          while (rs.next()){
              price = rs.getDouble("pr");
              
          }
          rs.close();
          st.close();
          return price;
      }
      
      public String getProductNameFromCode(String Code) throws Exception{
          String Name="";
          String sql = "Select name from Product where code = '"+Code+"'";
          Statement st = Connector.start_Statment();
          ResultSet rs = st.executeQuery(sql);
          while (rs.next()){
              Name =rs.getString("name");
          }
          rs.close();
          st.close();
          return Name;
      }
       public String getProductCodeFromName(String name) throws Exception{
          String code="";
          String sql = "Select code from Product where name = '"+name+"'";
          Statement st = Connector.start_Statment();
          ResultSet rs = st.executeQuery(sql);
          while (rs.next()){
              code =rs.getString("code");
          }
          rs.close();
          st.close();
          return code;
      }
      public void reserveTable(int tableno) throws Exception{
          String sql = "update T_tableNo set Status = 'Y' where T_tableNo_ID= "+tableno+" ";
          Statement st = Connector.start_Statment();
          try{
          st.executeQuery(sql);
          }catch(Exception e){
              
          }
          st.close();
      }
      public void UNreservedTable(int tableno) throws Exception{
          String sql = "update T_tableNo set Status = 'N' where T_tableNo_ID= "+tableno+" ";
          Statement st = Connector.start_Statment();
          st.executeQuery(sql);
          st.close();
      }
      
      public int getUNClosedLineID(int masterID,int productID) throws Exception{
          int ID =0;
          String sql = "select O_UNCLOSEDORDERLINE_ID id from O_UNCLOSEDORDERLINE where O_UNCLOSEDORDER_ID = "+masterID+""
                  + " and product_ID = "+productID+"";
          Statement st = Connector.start_Statment();
         ResultSet rs =  st.executeQuery(sql);
          while (rs.next()){
              ID = rs.getInt("id");
          }
          rs.close();
          st.close();
          return ID ; 
      }
      public void getProductInProductGroup(JList list,int ID) throws Exception{
          String sqlCount ="Select count(name) cn from Product where productgroup_ID = "+ID;
          int count=0;
          int i =0;
          String sql = "Select name from Product where productgroup_ID = "+ID;
          String[] arr;
          Statement st = Connector.start_Statment();
          ResultSet rs ;
          try {
              rs =st.executeQuery(sqlCount);
              while (rs.next()){
                  count = rs.getInt("cn");
              }
              rs.close();
              arr= new String [count];
              rs = st.executeQuery(sql);
              while(rs.next()){
                  arr[i]=rs.getString("name");
                  i++;
              }
              rs.close();
               list.removeAll();
          list.setListData(arr);
          list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          } catch (Exception e) {
              System.err.println(""+e);
          }
         st.close();
      }
      public String getPersonOrdered(int ID) throws Exception{
          String sql = "Select person from O_UNCLOSEDORDER where O_UNCLOSEDORDER_ID = "+ID;
          String person="";
          Statement st=Connector.start_Statment();
          ResultSet rs ;
          try {
              rs= st.executeQuery(sql);
              while(rs.next()){
                  person = rs.getString("person");
              }
               rs.close();
          } catch (Exception e) {
          }
          st.close();
         
          return person;
      }
     public void updateProductQTY(int ID,int newQTY,String P_or_M) throws Exception{
         // p for plus لما يكون بمسح حاجه من اوردر يبقي الكمية هترجع تاني المخزن و العكس
         String sql ="select QTYRemaning from product where product_ID = "+ID;
         int QTYRemaning =0;
         
         Statement st = Connector.start_Statment();
         ResultSet rs ;
         try {
             rs = st.executeQuery(sql);
             while (rs.next()){
                 QTYRemaning = rs.getInt("QTYRemaning");
             }
             rs.close();
         } catch (Exception e) {
         }
         String sqlP="update product set QTYRemaning = "+(QTYRemaning+newQTY)+" where product_ID ="+ID;
          String sqlM="update product set QTYRemaning = "+(QTYRemaning-newQTY)+" where product_ID ="+ID;
         if (P_or_M =="P"){
             try {
                 rs= st.executeQuery(sqlP);
                 rs.close();
             } catch (Exception e) {
                 System.err.println(""+e);
             }
         }else if (P_or_M=="M"){
              try {
                 rs= st.executeQuery(sqlM);
                 rs.close();
             } catch (Exception e) {
                 System.err.println(""+e);
             }
         }
     }
     public String getUserType(int ID) throws Exception{
         String sql = " select Type from users where users_ID = "+ID ;
         String Type="";
         Statement st = Connector.start_Statment();
         ResultSet rs ;
         try {
         rs = st.executeQuery(sql);
         while (rs.next()){
            Type=rs.getString("Type");
         }
         rs.close();
         } catch (Exception e) {
             System.err.println(""+e);
         }
         return Type;
     }
}
