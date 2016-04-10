/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadatabasetest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author vagisha
 */
public class MenuItemDAO {

     Connection myConn = null;
    Statement myStat = null;
    ResultSet rs = null;
    
    
    public MenuItemDAO() throws SQLException, ClassNotFoundException {
        
        Class.forName("org.sqlite.JDBC");
           myConn = DriverManager.getConnection("jdbc:sqlite:database01.sqlite");
           
    }
    
    public ArrayList<MenuItem> getAllMainCourse() throws SQLException{
     
        ArrayList<MenuItem> list = new ArrayList<>();
        myStat = myConn.createStatement();
        
        rs = myStat.executeQuery("select * from menu where category='main_course';");
        
        
        
        while(rs.next()){
            MenuItem tempObject = new MenuItem();
            tempObject.setItem_name(rs.getString("product_name"));
            tempObject.setItem_unit_price(rs.getFloat("unit_price"));
            //System.out.println(tempObject.getItem_name().toString());
            list.add(tempObject);
        }
        
       return list;
    }

    @Override
    protected void finalize() throws Throwable {
        myConn.close();
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
