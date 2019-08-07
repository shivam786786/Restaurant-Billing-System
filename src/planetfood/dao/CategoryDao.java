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
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Cat;

/**
 *
 * @author Shivam
 */
public class CategoryDao {
    public static HashMap<String,String> getAllCategory() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from categories");
        HashMap<String,String> categories = new HashMap<>();
        while(rs.next()) {
            String catId = rs.getString(1);
            String catName = rs.getString(2);
            categories.put(catName, catId);
            
        }
        return categories;
    }
    public static String getNewId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        int id = 101;
        ResultSet rs = st.executeQuery("select count(*) from categories");
        if(rs.next()) {
            id = id + rs.getInt(1);
        }
        return "C" + id;
    }
    public static boolean addCategory(Cat c)throws SQLException {
        Connection conn = DBConnection.getConnection(); 
        PreparedStatement ps = conn.prepareStatement("insert into categories values(?,?)");
        ps.setString(1, c.getCatId());
        ps.setString(2, c.getCatName());
        int count = ps.executeUpdate();
        if(count == 1)
            return true;
        else
            return false;

    }
    public static boolean updateCategory(Cat c)throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update categories set cat_name = ? where cat_id = ?");
        ps.setString(1, c.getCatName());
        ps.setString(2, c.getCatId());
        int count = ps.executeUpdate();
        if(count == 1)
            return true;
        else
            return false;

    }
    public static String getCatBYId(String catId) throws SQLException {
        String cat_name = null;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select cat_name from categories where cat_id = ?");
        ps.setString(1, catId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            cat_name = rs.getString("cat_name");
        }
        return cat_name;
    }
      
}
