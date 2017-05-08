/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class DbDelete {
    
    public static void vymazPredajcu(String email, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                       
            Statement state = con.createStatement();   
            String sql = "DELETE FROM Predajca WHERE email=\""+email+"\";";
            state.executeUpdate(sql);
            con.close();
            //deleteVozidlo(idVozidla, dbPath);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } 
    }  
    
    public static void vymazatInzeratyPredajcu(String email, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            Statement state = con.createStatement();   
            String sql = "DELETE FROM Inzerat WHERE email=\""+email+"\";";   
            state.executeUpdate(sql);
            con.close();
            vymazatVozidlaVymazanychInzeratov(dbPath);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
    }
    
    public static void vymazatVozidlaVymazanychInzeratov(String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            Statement state = con.createStatement();   
            String sql = "DELETE from Vozidlo WHERE NOT EXISTS(SELECT 'x' from Inzerat WHERE Inzerat.id_vozidla=Vozidlo.id_vozidla);";   
            state.executeUpdate(sql);
            con.close();
            //deleteVozidlo(idVozidla, dbPath);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
    }
    
    public static void vymazatInzerat(int idInzeratu, String dbPath) {
        int idVozidla = LoaderDB.zistiIdVozidla(idInzeratu, dbPath);
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            Statement state = con.createStatement();   
            String sql = "DELETE FROM Inzerat WHERE id_inzeratu=\""+idInzeratu+"\";";   
            state.executeUpdate(sql);
            con.close();
            deleteVozidlo(idVozidla, dbPath);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
    }
    
    public static void deleteVozidlo(int idVozidla, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){           
            Statement state = con.createStatement();   
            String sql = "DELETE FROM Vozidlo WHERE id_vozidla=\""+idVozidla+"\";";   
            state.executeUpdate(sql);
            con.close();               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
