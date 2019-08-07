/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Product;

/**
 *
 * @author Shivam
 */
public class ProductDao {
    public static String getNewId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        int id = 101;
        ResultSet rs = st.executeQuery("select count(*) from products");
        if(rs.next()) {
            id = id + rs.getInt(1);
        }
        return "P" + id;
    }
    public static boolean addProduct(Product p)throws SQLException {
Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement("insert into products values(?,?,?,?,?)");
ps.setString(1,p.getProdId());
ps.setString(2, p.getCatId());
ps.setString(3, p.getProdName());
ps.setDouble(4, p.getProdprice());
ps.setString(5, p.getIsActive());
int count = ps.executeUpdate();
if(count == 1)
    return true;
else
    return false;

}
    public static ArrayList<Product> getAllProduct() throws SQLException {
        ArrayList<Product> ProductList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from products");
        while(rs.next()) {
            Product p = new Product();
            p.setProdId(rs.getString(1));
            p.setCatId(rs.getString(2));
            p.setProdName(rs.getString(3));
            p.setProdprice(rs.getDouble(4));
            p.setIsActive(rs.getString(5));
            ProductList.add(p);
            
        }
        return ProductList;
    }
    public static ArrayList<String> getAllId() throws SQLException {
         ArrayList<String> Id_List = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String qry = "select * from products";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(qry);
          while(rs.next()) {
               Id_List.add(rs.getString(1));
        }
    return Id_List;

    }
      public static boolean updateProduct(Product p)throws SQLException {
Connection conn = DBConnection.getConnection();
String qry = "Update products set cat_id = ?,prod_name = ?, product_price = ?, active = ? where prod_id = ?";
PreparedStatement ps = conn.prepareStatement(qry);
ps.setString(5,p.getProdId());
ps.setString(1, p.getCatId());
ps.setString(2, p.getProdName());
ps.setDouble(3, p.getProdprice());
ps.setString(4, p.getIsActive());
int count = ps.executeUpdate();
if(count == 1)
    return true;
else
    return false;

}
    public static HashMap<String, Product> getProductByCategory(String catId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "select * from products where cat_id = ? and active = 'Y'";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, catId);
        HashMap<String, Product> ProductList = new HashMap<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Product p = new Product();
            p.setCatId(catId);
            p.setProdId(rs.getString("prod_id"));
            p.setProdName(rs.getString("prod_name"));
            p.setProdprice(rs.getDouble("product_price"));
            p.setIsActive(rs.getString("active"));
            ProductList.put(p.getProdId(), p);
        }
        return ProductList;
    }
      public static boolean removeProduct(String s, String r) throws SQLException {
           Connection conn = DBConnection.getConnection();
           String qry = "delete from products where prod_id = ? and cat_id = ?";
           PreparedStatement ps = conn.prepareStatement(qry);
           ps.setString(1, s);
           ps.setString(2, r);
           int count = ps.executeUpdate();
           if(count == 1)
               return true;
           else 
               return false;
     }
      public static Product getProductById(String id) throws SQLException {
          Connection conn = DBConnection.getConnection();
          String qry = "select * from products where prod_id = ?";
           PreparedStatement ps = conn.prepareStatement(qry);
           ps.setString(1, id);
           ResultSet rs = ps.executeQuery();
           if(rs.next()) {
               Product p = new Product();
            p.setCatId(rs.getString("cat_id"));
            p.setProdId(rs.getString("prod_id"));
            p.setProdName(rs.getString("prod_name"));
            p.setProdprice(rs.getDouble("product_price"));
            p.setIsActive(rs.getString("active"));
            return p;
           }
        return null;
          
      }
       public static ArrayList<Product> getMenuProduct() throws SQLException {
        ArrayList<Product> ProductList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        String qry = "select * from products where active = 'Y'";
        ResultSet rs = st.executeQuery(qry);
        while(rs.next()) {
            Product p = new Product();
            p.setProdId(rs.getString(1));
            p.setCatId(rs.getString(2));
            p.setProdName(rs.getString(3));
            p.setProdprice(rs.getDouble(4));
            p.setIsActive(rs.getString(5));
            ProductList.add(p);
            
        }
        return ProductList;
    }
        public static HashMap<String, Product> getSingleProduct(String catId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "select * from products where cat_id = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, catId);
        HashMap<String, Product> ProductList = new HashMap<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Product p = new Product();
            p.setCatId(catId);
            p.setProdId(rs.getString("prod_id"));
            p.setProdName(rs.getString("prod_name"));
            p.setProdprice(rs.getDouble("product_price"));
            p.setIsActive(rs.getString("active"));
            ProductList.put(p.getProdId(), p);
        }
        return ProductList;
    }
}
