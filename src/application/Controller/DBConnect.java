package application.Controller;
import static java.lang.Math.log;
import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



/**
 *
 * @author House
 */

public class DBConnect {
    static String url = "jdbc:mysql://localhost:3306/ecole-1";
     static String login = "root";
     static String pwd = "";
    public  static DBConnect db;
    public Connection con;
    public DBConnect() {

    }
    
    public static Connection  getConnection() throws SQLException
    {
        try
        {
    return DriverManager.getConnection(DBConnect.url, DBConnect.login, DBConnect.pwd);
    
        }catch (SQLException ex) {
           System.out.println(ex);
         
    }     
        return null;
    }
    
    
           
     
 
}