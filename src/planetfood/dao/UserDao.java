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
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Employee;
import planetfood.pojo.User;

/**
 *
 * @author Shivam
 */
public class UserDao {
    public static String validateUser(User user) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "select * from users where userid = ? and Password = ? and usertype = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1,user.getUserId().trim());
        ps.setString(2,user.getPassword().trim());
        ps.setString(3,user.getUsertype().trim());
        ResultSet rs = ps.executeQuery();
        String username = null;
        if(rs.next() == true) {
             username = rs.getString("USERNAME");
            return username;
             
        }
           
        else {
           return username;
        }    
    }
    public static boolean updateUser(User user) throws SQLException {
         Connection conn = DBConnection.getConnection();
         String qry = "update users set password = ? where username = ? and usertype = ? and userid = ?";
         PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1,user.getPassword().trim());
        ps.setString(2,user.getUsername().trim());
        ps.setString(3,user.getUsertype().trim());
        ps.setString(4, user.getUserId().trim());
        int count = ps.executeUpdate();
        if(count == 1)
            return true;
        else
            return false;
      
         
    }
    public static ArrayList<User> getUser() throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
         Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery("select * from users where usertype = 'Cashier'");
         
      while(rs.next()) {
          User user = new User();
        user.setUserId(rs.getString(1));
        user.setUsername(rs.getString(2));
        user.setPassword(rs.getString(3));
        user.setEmpId(rs.getString(4));
        user.setUsertype(rs.getString(5));
         // System.out.println(user);
        userList.add(user);
      }
      return userList;
    } 
     public static ArrayList<Employee> getEmp() throws SQLException {
        ArrayList<Employee> EmpList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from employees where job = 'Cashier'");
        while(rs.next()) {
            Employee e = new Employee();
            e.setEmpId(rs.getString(1));
            e.setEmpName(rs.getString(2));
            e.setJob(rs.getString(3));
            e.setSal(rs.getDouble(4));
            
            EmpList.add(e);
            
        }
        return EmpList;
    }
     public static boolean registerUser(User user) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "insert into users values(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, user.getUserId().trim());
        ps.setString(2, user.getUsername().trim());
        ps.setString(3, user.getPassword().trim());
        ps.setString(4, user.getEmpId().trim());
        ps.setString(5, user.getUsertype().trim());
        int count = ps.executeUpdate();
        if(count == 1)
            return true;
        else
            return false;
        
    }
    public static boolean deleteUser(String userId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from users where userid = ?");
        ps.setString(1, userId);
       int count = ps.executeUpdate();
       if(count==0) {
          return false; 
    }
       else 
           return true;
    }
     public static ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from Users");
        while(rs.next()) {
            User u = new User();
          u.setUserId(rs.getString(1));
          u.setUsername(rs.getString(2));
          u.setPassword(rs.getString(3));
          u.setEmpId(rs.getString(4));
          u.setUsertype(rs.getString(5));
          userList.add(u);
          
            
        }
        return userList;
    }
     public static User getCashierbyId(String UserId) throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from users where usertype = ? and userid = ?");
        ps.setString(1, "Cashier");
        ps.setString(2, UserId);
        ResultSet rs = ps.executeQuery();
        User user = null;
        if(rs.next()) {
        user = new User();
        user.setUserId(rs.getString(1));
        user.setUsername(rs.getString(2));
        user.setPassword(rs.getString(3));
        user.setEmpId(rs.getString(4));
        user.setUsertype(rs.getString(5));
         // System.out.println(user);
        
      }
      return user;
     }

    
    
}
