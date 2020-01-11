/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTables;

import DBConnect.Connector;
import java.io.Serializable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;


import javax.swing.JTable;


import javax.swing.table.DefaultTableModel;



/**
 *
 * @author MR
 */

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private int productId;
    private String name;
    private Date datecreated;
    private int createdby;
    private double price;
    private int uom;
    private int productgroupId;
    private String code;
    private String description;
    private int saleUOM_ID ;
    private double salePrice;
    private double QTY;
     private double QTYWH;

      private double QTYRemaining;

  
    
   
 CurrentSession cur;
    Statement st;
    
    
    public Product(int productId,String name,Date datecreated,int createdby,double price,int uom,int productgroupId,String Code,int SaleUOM_ID,double SalePrice,String Description,double QTY,double QTYWH , double QTYRemaining) throws Exception
    {
        this.productId = productId;
        this.name = name;
        this.datecreated = datecreated;
         this.createdby = createdby;
         this.price = price;
         this.uom = uom;
          this.productgroupId = productgroupId;
         this.code=Code;
         this.description =Description;
         this.saleUOM_ID = SaleUOM_ID;
         this.salePrice = SalePrice;
         this.QTY = QTY;
         this.QTYWH = QTYWH;
         this.QTYRemaining = QTYRemaining;
        cur=new CurrentSession();
    }

    public Product() throws Exception {
         st = new Connector().start_Statment();
          cur=new CurrentSession();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public int getCreatedby() {
        return createdby;
    }

    public void setCreatedby(int createdby) {
        this.createdby = createdby;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUom() {
        return uom;
    }

    public void setUom(int uom) {
        this.uom = uom;
    }

    public int getProductgroupId() {
        return productgroupId;
    }

    public void setProductgroupId(int productgroupId) {
        this.productgroupId = productgroupId;
    }
     public String getCode() {
        return code;
    }

    public void setCode(String Code) {
        this.code = Code;
    }
 public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     public void setPrice(double price) {
        this.price = price;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getSaleUOM_ID() {
        return saleUOM_ID;
    }

    public double getSalePrice() {
        return salePrice;
    }
    public void setQTY(double QTY) {
        this.QTY = QTY;
    }

    public double getQTY() {
        return QTY;
    }
  public void setQTYWH(double QTYWH) {
        this.QTYWH = QTYWH;
    }

    public double getQTYWH() {
        return QTYWH;
    }

    
    public double getQTYRemaining() {
        return QTYRemaining;
    }

    public void setQTYRemaining(double QTYRemaining) {
        this.QTYRemaining = QTYRemaining;
    }
 

    @Override
    public boolean equals(Object object) {
       
        
        return true;
    }

    @Override
    public String toString() {
        return "cafe.DBTables.Product[ productId=" + productId + " ]";
    }
    
    public ArrayList<Product> getList () throws Exception{
    ArrayList<Product> List = new ArrayList<>();
  
    String sql ="select * from Product order by productgroup_ID ";
    
    ResultSet rs = null;
    try {
     
     rs= st.executeQuery(sql);
        while (rs.next()) {
           Product product = new Product(rs.getInt("PRODUCT_ID"),rs.getString("name"), rs.getDate("datecreated"), rs.getInt("createdby"), rs.getDouble("price"), rs.getInt("uom_ID"), rs.getInt("productgroup_Id"),rs.getString("Code"),rs.getInt("saleUOM_ID"),rs.getDouble("salePrice"),rs.getString("Description"),rs.getDouble("QTY"),rs.getDouble("QTYWH"),rs.getDouble("QTYREMANING"));
            System.out.println(rs.getInt("Product_ID"));
           List.add(product);
            
        }
        rs.close();
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
    
}
    public void Show_user_inTable(JTable Table) throws Exception{
    ArrayList<Product> list = getList();
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    TableINF inf = new TableINF();
    Object [] row = new Object[12];
    for(int i = 0 ; i<list.size();i++){
      
      
        row[11] = i+1;
        row[10] =inf.getProductGroupName(list.get(i).getProductgroupId()) ;
        row[9] =list.get(i).getCode();
        row[8] =list.get(i).getName();
        row[7] =list.get(i).getPrice();
        
        row[6] =inf.getNameForTRX(list.get(i).getUom(), "UOM");
        if (list.get(i).getQTYWH()== 0){
            row[5]=0;
        }else {
         row[5] =list.get(i).getQTYWH();
        }
       
        if (inf.getNameForTRX(list.get(i).getSaleUOM_ID(), "UOM") == null){
            
        }else {
        row[4] =inf.getNameForTRX(list.get(i).getSaleUOM_ID(), "UOM");
        }
          row[3] =list.get(i).getSalePrice();
        row[2] =list.get(i).getQTY();
        
         row[1] =list.get(i).getQTYRemaining();
        
        
         if (list.get(i).getDescription() == null){
             row[0] ="";
         }else
         {        row[0] =list.get(i).getDescription();}
        
     
        
        model.addRow(row);
    }
    
}
     public ArrayList<Product> getSearchList (int PGID ,String LikeUouMustWriteAnd) throws Exception{
    ArrayList<Product> List = new ArrayList<>();
  
    String sql ="select * from Product where productgroup_ID = "+PGID+" "+LikeUouMustWriteAnd+" order by productgroup_ID ";
    
    ResultSet rs = null;
    try {
     
     rs= st.executeQuery(sql);
        while (rs.next()) {
           Product product = new Product(rs.getInt("PRODUCT_ID"),rs.getString("name"), rs.getDate("datecreated"), rs.getInt("createdby"), rs.getDouble("price"), rs.getInt("uom_ID"), rs.getInt("productgroup_Id"),rs.getString("Code"),rs.getInt("saleUOM_ID"),rs.getDouble("salePrice"),rs.getString("Description"),rs.getDouble("QTY"),rs.getDouble("QTYWH"),rs.getDouble("QTYREMANING"));
            System.out.println(rs.getInt("Product_ID"));
           List.add(product);
            
        }
        rs.close();
    } catch (Exception e) {
        System.err.println("Error "+ e);
    }
    return List;
     }
        public void Show_SearcedPG_inTable(int ID,String LikeUouMustWriteAnd,JTable Table) throws Exception{
    ArrayList<Product> list = getSearchList(ID,LikeUouMustWriteAnd);
    DefaultTableModel model = (DefaultTableModel)Table.getModel();
    model.setRowCount(0);
    TableINF inf = new TableINF();
    Object [] row = new Object[12];
    for(int i = 0 ; i<list.size();i++){
      
        row[11] = i+1;
        row[10] =inf.getProductGroupName(list.get(i).getProductgroupId()) ;
        row[9] =list.get(i).getCode();
        row[8] =list.get(i).getName();
        row[7] =list.get(i).getPrice();
        
        row[6] =inf.getNameForTRX(list.get(i).getUom(), "UOM");
        if (list.get(i).getQTYWH()== 0){
            row[5]=0;
        }else {
         row[5] =list.get(i).getQTYWH();
        }
       
        if (inf.getNameForTRX(list.get(i).getSaleUOM_ID(), "UOM") == null){
            
        }else {
        row[4] =inf.getNameForTRX(list.get(i).getSaleUOM_ID(), "UOM");
        }
          row[3] =list.get(i).getSalePrice();
        row[2] =list.get(i).getQTY();
        
         row[1] =list.get(i).getQTYRemaining();
        
        
         if (list.get(i).getDescription() == null){
             row[0] ="";
         }else
         {        row[0] =list.get(i).getDescription();}
        
     
        
        model.addRow(row);
    }
    
}
   
   public void executeQuery(String Query, String Message,JTable table) throws Exception{
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
             JOptionPane.showMessageDialog(null, "Data Not "+Message+" Succesfully ");
        }
    }
   public void addProduct(int PGID ,String Pname,double price,int UOM_ID,String Code,String Description,int SalesUOM_ID,double PriceSales,double QTY,double QTYWH,double QTYRemaining,JTable table) throws Exception{
      TableINF inf =new TableINF();
        try {//Insert into O_UNCLOSEDORDER values ()
            String sql = "Insert into product  values ("+ inf.getNewID("product")+","+PGID+",'"+Pname+"',sysdate,"+cur.getCurrentUserID()+","+price+","+UOM_ID+",'"+Code+"','"+Description+"',"+SalesUOM_ID+","+PriceSales+","+QTY+","+QTYWH+","+QTYRemaining+")";
            this.executeQuery(sql, "Insert",table);
        } catch (SQLException ex) {
        
        } catch (Exception ex) {
           //Logger.getLogger(OpenOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public void deleteProduct(int ID ,JTable table) throws Exception{
       String sql = "delete from product where product_ID = "+ID;
       Statement st = Connector.start_Statment();
       try{
       st.executeQuery(sql);
      
           Show_user_inTable(table);
            JOptionPane.showMessageDialog(table, "Product Deleted Sucessfully ");
       } catch (Exception e ){
           System.out.println(""+e);
       }
       
   }
   public void updateProduct(int OldID,String NewName ,String Code, double Price , int UOMID , double PriceSale , int UOMsaleID,String Description,double QTY,double QTYWH,double QTYRemaining,JTable table) throws Exception{
       String sql = "update product set name = '"+NewName+"',code = '"+Code+"', PRICE = "+Price+" ,UOM_ID = "+UOMID+" ,salePrice = "+PriceSale+" ,saleUOM_ID ="+UOMsaleID+" ,Description = '"+Description+"' ,QTY = "+QTY+"  , QTYWH = "+QTYWH+" , QTYREMANING = "+QTYRemaining+" where Product_ID = "+OldID+"";
   Statement st = Connector.start_Statment();
   try{
   st.executeQuery(sql);
      Show_user_inTable(table);
   JOptionPane.showMessageDialog(null, "Product Updated Sucessfully ");
    
   }catch (Exception e){
       System.err.println(""+e);
   }
   
   }
}