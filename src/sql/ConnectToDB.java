package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Konektor do databázy.
 * @author Jakub Cachovan
 */
public class ConnectToDB {
    
    /**
     * Statická metóda pre pripojenie do databázy.
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
