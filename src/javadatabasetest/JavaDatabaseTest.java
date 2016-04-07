/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javadatabasetest;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author vagisha
 */
public class JavaDatabaseTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
   
        
    Connection myConn = null;
    Statement myStat = null;
    ResultSet rs = null;
    
    String user = "root";
    String pass = "reliable";
        
        try{
           // Class.forName("com.mysql.jdbc.Driver");
           myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Restaurant Managenment System", user, pass);
           
           myStat = myConn.createStatement();
           
           rs = myStat.executeQuery("select * from menu;");
           
           while(rs.next()){
            
               System.out.println(rs.getInt(1) + "," + rs.getString(2));
           }
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
