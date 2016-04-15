/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javadatabasetest;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;
/**
 *
 * @author vagisha
 */
public class JavaDatabaseTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
   
        
         
        try { 
    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
    //  UIManager.setLookAndFeel(new SynthLookAndFeel());
} catch (Exception ex) { 
    ex.printStackTrace(); 
}
    
     
       
        LoginForm lf = new LoginForm();
        lf.setVisible(true);
        
        
    }
    
}
