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
    
     
        
        try{
           Class.forName("org.sqlite.JDBC");
           myConn = DriverManager.getConnection("jdbc:sqlite:database01.sqlite");
           
           myStat = myConn.createStatement();
           
           rs = myStat.executeQuery("select * from menu;");
           
           while(rs.next()){
            
               System.out.println(rs.getInt(1) + "," + rs.getString(2));
           }
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        LoginForm lf = new LoginForm();
        lf.setVisible(true);
        
        
    }
    
}
