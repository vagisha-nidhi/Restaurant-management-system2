/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadatabasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author vagisha
 */
public class DataService {

    private static Connection con;
    private static final String Driver = "org.sqlite.JDBC";
    private static final String ConnectionString = "jdbc:sqlite:database01.sqlite";

    public DataService() {
    }

    public static Connection loadDriver() throws SQLException {
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        con = DriverManager.getConnection(ConnectionString);
        return con;
    }
    
    public static ArrayList<String> getCategoryOrCuisine(int x){
         try {
            Connection con = loadDriver();
            ResultSet rs = null;
            Statement myStat = null;
            ArrayList<String> list = new ArrayList<>();
            myStat = con.createStatement();
            if(x == 1)
            rs = myStat.executeQuery("select distinct categoryName from category_list;");
            else if(x == 2)
                rs = myStat.executeQuery("select distinct cuisineName from cuisine_list;");
            System.out.println("Sqlite busy in getCategoryOrCuisine");

            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;

        } catch (Exception e) {
        } finally {
            try {
                con.close();
                System.out.println("Sqlite freed in getCAtegoryOrCuisine");
            } catch (Exception e) {
            }
        }

        return null;
    }

    public static TableModel getTableModelForMenuItemsAdmin(char x, String listItem) {

        Connection con = null;
        ResultSet rs = null;
        Statement myStat = null;
        try {
            con = loadDriver();
            myStat = con.createStatement();

            if (x == 'a') {
                rs = myStat.executeQuery("select * from menu");
                TableModel tb = DbUtils.resultSetToTableModel(rs);
                return tb;
            } else if (x == 'b') {
                rs = myStat.executeQuery("select * from menu where category = '" + listItem + "';");
                TableModel tb = DbUtils.resultSetToTableModel(rs);
                return tb;
            } else if (x == 'c') {
                rs = myStat.executeQuery("select * from menu where cuisine = '" + listItem + "';");
                TableModel tb = DbUtils.resultSetToTableModel(rs);
                return tb;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                //  con.close();
                rs.close();
                myStat.close();
                con.close();
                System.out.println("Sqlite freed in Update table");
            } catch (Exception e) {
            }
        }
        return null;

    }
    public static void updateIntoMenuForAdmin(String name, float unitPrice, String category, String cuisine, String image_url,int id){
     
                try {
            // myStat = myConn.createStatement();
            System.out.println("Sqlite busy in updateIntoMenuForAdmin");
            Connection con = loadDriver();
            ResultSet rs = null;
            PreparedStatement preparedStatement = null;
            preparedStatement = con.prepareStatement("update menu set product_name = ?,unit_price = ?,category = ?,image_url = ?,cuisine = ? where product_id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2,unitPrice);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, image_url);
            preparedStatement.setString(5, cuisine);
            preparedStatement.setInt(6, id);
            int x = preparedStatement.executeUpdate();
            System.out.println("Value of x : " + x);
            //   myStat.executeUpdate("Insert into ordered_menu(order_id,product_name,price) values("+order_id+",'"+product+"',"+price + ");");
            con.commit();

        } catch (Exception e) {
            System.out.println("Insert exception : " + e);
        } finally {
            try {
                con.close();
                System.out.println("Sqlite freed in updateIntoMenuForAdmin");

            } catch (Exception e) {
            }
        }
    
    }
    
    public static void insertIntoMenuForAdmin(String name, float unitPrice, String category, String cuisine, String image_url){
    
                try {
            // myStat = myConn.createStatement();
            System.out.println("Sqlite busy in insertIntoMenuForAdmin");
            Connection con = loadDriver();
            ResultSet rs = null;
            PreparedStatement preparedStatement = null;
            preparedStatement = con.prepareStatement("Insert into menu(product_name,unit_price,category,image_url,cuisine) values(?,?,?,?,?);");
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2,unitPrice);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, image_url);
            preparedStatement.setString(5, cuisine);
            int x = preparedStatement.executeUpdate();
            System.out.println("Value of x : " + x);
            //   myStat.executeUpdate("Insert into ordered_menu(order_id,product_name,price) values("+order_id+",'"+product+"',"+price + ");");
            con.commit();

        } catch (Exception e) {
            System.out.println("Insert exception : " + e);
        } finally {
            try {
                con.close();
                System.out.println("Sqlite freed in InsertInMenuForAdmin");

            } catch (Exception e) {
            }
        }
        
    }

    public static ArrayList<String> getListItemNamesForAdmin(char x) {

        ArrayList<String> list = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        Statement myStat = null;
        try {

            con = loadDriver();
            if (x == 'a') {

            } else if (x == 'b') {
                myStat = con.createStatement();
                rs = myStat.executeQuery("select distinct category from menu;");
                while (rs.next()) {
                    list.add(rs.getString(1));
                    System.out.println("In Data Service : " + rs.getString("category"));
                }
            } else if (x == 'c') {
                myStat = con.createStatement();
                rs = myStat.executeQuery("select distinct cuisine from menu;");
                while (rs.next()) {
                    list.add(rs.getString(1));
                }
                return list;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                System.out.println("Sqlite freed in getAllMainCourse");
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ArrayList<String> getCategoryWiseMenuItemName(int category) throws SQLException {
        try {
            Connection con = loadDriver();
            ResultSet rs = null;
            Statement myStat = null;
            ArrayList<String> list = new ArrayList<>();
            myStat = con.createStatement();
            if(category == 1)
            rs = myStat.executeQuery("select product_name from menu where category='Main Course';");
            else if(category == 2)
                rs = myStat.executeQuery("select product_name from menu where category='Starter';");
            else
                rs = myStat.executeQuery("select product_name from menu where category='Dessert';");
            System.out.println("Sqlite busy in getAllMainCourse");

            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;

        } catch (Exception e) {
        } finally {
            try {
                con.close();
                System.out.println("Sqlite freed in getAllMainCourse");
            } catch (Exception e) {
            }
        }

        return null;
    }

    public static TableModel UpdateTable(int order_id) {
        Statement myStat = null;
        ResultSet rs = null;
        try {
            Connection con = loadDriver();

            myStat = con.createStatement();
            System.out.println("Sqlite busy in Update table");
            rs = myStat.executeQuery("select sequence_number,product_name,quantity,price from ordered_menu where order_id = " + order_id + ";");
            TableModel tb = DbUtils.resultSetToTableModel(rs);
            return tb;

        } catch (Exception e) {
            System.out.println("Return Ordered_menu exception : " + e);
        } finally {
            try {
                //  con.close();
                rs.close();
                myStat.close();
                con.close();
                System.out.println("Sqlite freed in Update table");
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static void updateTableHeaders(JTable table) {

        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setHeaderValue("S.No");
        tcm.getColumn(1).setHeaderValue("Name");
        tcm.getColumn(2).setHeaderValue("Quantity");
        tcm.getColumn(3).setHeaderValue("Price");
        tcm.getColumn(0).setPreferredWidth(40);
        tcm.getColumn(1).setPreferredWidth(150);
        tcm.getColumn(2).setPreferredWidth(60);
        tcm.getColumn(3).setPreferredWidth(70);
    }

    public static float getPrice(String product, int qty) throws SQLException {
        float unit_price, price = 0;
        try {
            System.out.println("Sqlite busy in enterInOrderedMenu 1- getting unit price");
            Connection con = loadDriver();
            ResultSet rs = null;
            Statement myStat = null;
            myStat = con.createStatement();
            rs = myStat.executeQuery("select unit_price from menu where product_name = '" + product + "';");
            unit_price = rs.getFloat("unit_price");
            System.out.println("Value of unit_price : " + unit_price);
            price = unit_price * qty;
            System.out.println("Value of price : " + price);
            return price;
            // System.out.println("Value of order_id: " + order_id);
        } catch (Exception e) {
        } finally {
            try {
                con.close();
                System.out.println("Sqlite freed in getAllMainCourse");
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public static void insertInOrderedMenu(int order_id, int sequence, String product, float price, int qty) {
        try {
            // myStat = myConn.createStatement();
            System.out.println("Sqlite busy in IIOM");
            Connection con = loadDriver();
            ResultSet rs = null;
            PreparedStatement preparedStatement = null;
            preparedStatement = con.prepareStatement("Insert into ordered_menu values(?,?,?,?,?);");
            preparedStatement.setInt(4, sequence);
            preparedStatement.setInt(1, order_id);
            preparedStatement.setString(2, product);
            preparedStatement.setFloat(3, price);
            preparedStatement.setInt(5, qty);
            int x = preparedStatement.executeUpdate();
            System.out.println("Value of x : " + x);
            //   myStat.executeUpdate("Insert into ordered_menu(order_id,product_name,price) values("+order_id+",'"+product+"',"+price + ");");
            con.commit();

        } catch (Exception e) {
            System.out.println("Insert exception : " + e);
        } finally {
            try {
                con.close();
                System.out.println("Sqlite freed in InsertInOrderedMenu");

            } catch (Exception e) {
            }
        }
    }

    public static int getLastOrder() throws SQLException {
        int order_id = 0;
        ResultSet rs = null;
        Statement myStat = null;
        try {
            Connection con = loadDriver();

            myStat = con.createStatement();
            rs = myStat.executeQuery("select * from order_details;");
            System.out.println("Sqlite busy in getLastOrder");
            while (rs.next()) {
                order_id = rs.getInt("order_id");
            }
            return order_id;
        } catch (Exception e) {
        } finally {
            try {
                System.out.println("Sqlite freed in getLastOrder");
                rs.close();
                myStat.close();
                con.close();
            } catch (Exception e) {
            }
        }

        return 0;

    }

    public static void DeleteRows(int order_id, int sequence_num) {

        try {
            Connection con = loadDriver();

            // ResultSet rs = null;
            Statement myStat = null;
            myStat = con.createStatement();
            myStat.executeUpdate("delete from ordered_menu where order_id = " + order_id + " and sequence_number = " + sequence_num);
        } catch (SQLException ex) {
            System.out.println("Sqlite exception in DeleteRows");
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                System.out.println("Sqlite freed in DeleteRows");
                con.close();
            } catch (Exception e) {
            }
        }

    }
    public static void deleteRowsInMenuForAdmin(int menuId){
            try {
            Connection con = loadDriver();

            // ResultSet rs = null;
            Statement myStat = null;
            myStat = con.createStatement();
            myStat.executeUpdate("delete from menu where product_id = " + menuId);
        } catch (SQLException ex) {
            System.out.println("Sqlite exception in DeleteRowsInMenuForAdmin");
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                System.out.println("Sqlite freed in DeleteRowsInMenuForAdmin");
                con.close();
            } catch (Exception e) {
            }
        }
        
    }
    

    public static void DeleteRows(int order_id) {

        try {
            Connection con = loadDriver();

            // ResultSet rs = null;
            Statement myStat = null;
            myStat = con.createStatement();
            myStat.executeUpdate("delete from ordered_menu where order_id = " + order_id);
        } catch (SQLException ex) {
            System.out.println("Sqlite exception in DeleteRows");
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                System.out.println("Sqlite freed in DeleteRows");
                con.close();
            } catch (Exception e) {
            }
        }

    }

    public static String getImageUrl(String item_name) {

        String url = null;
        ResultSet rs = null;
        Statement myStat = null;
        try {
            Connection con = loadDriver();

            myStat = con.createStatement();
            rs = myStat.executeQuery("select image_url from menu where product_name = '" + item_name + "';");
            url = rs.getString("image_url");
            return url;
        } catch (Exception e) {
        } finally {
            try {
                System.out.println("Sqlite freed in getImageUrl");
                rs.close();
                myStat.close();
                con.close();
            } catch (Exception e) {
            }
        }

        return null;
    }

    public static String getAllCustomers(String loginUserName) throws SQLException {
        ResultSet rs = null;
        String password = null;
        try {
            Connection con = loadDriver();

            Statement myStat = null;
            myStat = con.createStatement();
            rs = myStat.executeQuery("select password from customer where username = '" + loginUserName + "';");
            if (rs.next()) {
                //System.out.println(rs.getString("password"));
                password = rs.getString("password");
            }

        } catch (Exception e) {
            System.out.println("Exception in getAllCustomer : " + e);
        } finally {
            try {
                con.close();
                System.out.println("Login form closed");
            } catch (Exception e) {
            }
        }
        return password;
    }

}
