/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Shivam
 */
public class DBConnection {
    private static Connection conn;
    private static final String driverLoad = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@//DESKTOP-GFP2I2D:1521/XE";
    private static final String DB = "myproject";
    private static final String PASS = "shivam";
    static {
        try {
            Class.forName(driverLoad);
            conn = DriverManager.getConnection(URL,DB,PASS);
            JOptionPane.showMessageDialog(null,"Connected Successfully to the database","SUCCESS!",JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Error loading Driver class"+ex,"Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
         catch(SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error in Connection"+ex,"Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return conn;
    }
    public static void closeConnection() {
        try {
            conn.close();
             JOptionPane.showMessageDialog(null,"Connection closed Successfully","SUCCESS!",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error in closing Connection"+ex,"Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
}
