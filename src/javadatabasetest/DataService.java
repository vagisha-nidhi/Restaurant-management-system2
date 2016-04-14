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

    public static ArrayList<MenuItem> getAllMainCourse() throws SQLException {
        try {
            Connection con = loadDriver();
            ResultSet rs = null;
            Statement myStat = null;
            ArrayList<MenuItem> list = new ArrayList<>();
            myStat = con.createStatement();

            rs = myStat.executeQuery("select * from menu where category='main_course';");
            System.out.println("Sqlite busy in getAllMainCourse");

            while (rs.next()) {
                MenuItem tempObject = new MenuItem();
                tempObject.setItem_name(rs.getString("product_name"));
                tempObject.setItem_unit_price(rs.getFloat("unit_price"));
                //System.out.println(tempObject.getItem_name().toString());
                list.add(tempObject);
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
            rs = myStat.executeQuery("select * from ordered_menu where order_id = " + order_id + ";");
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

    public static void insertInOrderedMenu(int order_id, int sequence, String product, float price) {
        try {
            // myStat = myConn.createStatement();
            System.out.println("Sqlite busy in IIOM");
            Connection con = loadDriver();
            ResultSet rs = null;
            PreparedStatement preparedStatement = null;
            preparedStatement = con.prepareStatement("Insert into ordered_menu values(?,?,?,?);");
            preparedStatement.setInt(4, sequence);
            preparedStatement.setInt(1, order_id);
            preparedStatement.setString(2, product);
            preparedStatement.setFloat(3, price);
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
