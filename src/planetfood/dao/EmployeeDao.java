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
import planetfood.pojo.Employee;

/**
 *
 * @author Shivam
 */
public class EmployeeDao {
     public static String getNewId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        int id = 101;
        ResultSet rs = st.executeQuery("select count(*) from Employees");
        if(rs.next()) {
            id = id + rs.getInt(1);
        }
        return "E" + id;
    }
      public static boolean addEmployee(Employee E)throws SQLException {
Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement("insert into employees values(?,?,?,?)");
ps.setString(1,E.getEmpId());
ps.setString(2, E.getEmpName());
ps.setString(3, E.getJob());
ps.setDouble(4, E.getSal());
int count = ps.executeUpdate();
if(count == 1)
    return true;
else
    return false;

}
       public static ArrayList<String> getAllId() throws SQLException {
         ArrayList<String> Id_List = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select empid from Employees");
          while(rs.next()) {
               Id_List.add(rs.getString(1));
        }
    return Id_List;

    }
        public static boolean updateEmployee(Employee E)throws SQLException {
Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement("update employees set ename = ?,job = ?,sal = ? where empid = ?");
ps.setString(1,E.getEmpName());
ps.setString(2, E.getJob());
ps.setDouble(3, E.getSal());
ps.setString(4, E.getEmpId());
int count = ps.executeUpdate();
if(count == 1)
    return true;
else
    return false;

}
        public static ArrayList<Employee> getAllEmployee() throws SQLException {
        ArrayList<Employee> EmpList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from employees");
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
         public static boolean removeEmployee(String eid, String ename) throws SQLException {
       Connection conn = DBConnection.getConnection();  
       PreparedStatement ps = conn.prepareStatement("delete from employees where empid = ? and ename = ?");
       ps.setString(1, eid);
       ps.setString(2, ename);
       int count = ps.executeUpdate();
       if(count == 1)
           return true;
       else
           return false;
      }
         public static Employee getEmployeeById(String empid) throws SQLException {
             Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from employees where empid = ?");
             ps.setString(1, empid);
             ResultSet rs=  ps.executeQuery();
              Employee e = new Employee();
             if(rs.next()){
               
                e.setEmpId(rs.getString(1));
                e.setEmpName(rs.getString(2));
                e.setJob(rs.getString(3));
                e.setSal(rs.getDouble(4));
                 
             }
             return e;
         }
         public static boolean deleteEmpFromUser(String empid) throws SQLException {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("delete from users where empid = ?");
            ps.setString(1, empid);
            int count = ps.executeUpdate();
            if(count == 1)
                return true;
            else
                return false;
        }
         public static HashMap<String, Employee> getEmpByJob(String job) throws SQLException {
             HashMap<String, Employee> empList = new HashMap<>();
             Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from employees where job = ?");
            ps.setString(1, job);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Employee e = new Employee();
                e.setEmpId(rs.getString(1));
                e.setEmpName(rs.getString(2));
                e.setJob(rs.getString(3));
                e.setSal(rs.getDouble(4));
                empList.put( e.getEmpId(), e);
 
            }
            return empList;
         }
        
}

