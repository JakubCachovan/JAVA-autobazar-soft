/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class ConnectToDB {
    /**
     * @param dbPath
     * @return
     */
    public static Connection ConnectDB(String dbPath) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = null;
            con = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
}
