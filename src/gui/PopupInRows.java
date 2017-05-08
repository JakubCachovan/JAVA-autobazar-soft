/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import autobazar.Autobazar;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class PopupInRows extends JFrame{
    public PopupInRows(){
        //Set title
        super("Popup In Rows");
    }
}

class RowPopup extends JPopupMenu
{
    public RowPopup(JTable table, Autobazar autobazar){
        JMenuItem predane = new JMenuItem("Označiť ako predané");
        JMenuItem aktivne = new JMenuItem("Označiť ako aktívne");
        //MenuItem delete = new JMenuItem("Vymazať");
        
        predane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {   
                int selectedRow = -1;
                selectedRow = table.getSelectedRow();
                if(selectedRow != -1){
                    try {
                        DefaultTableModel m = (DefaultTableModel)table.getModel();
                        String idIzeratu = m.getValueAt(selectedRow, 0).toString();
                        if(!autobazar.getPredane(Integer.parseInt(idIzeratu))){
                            autobazar.setPredane(Integer.parseInt(idIzeratu));  
                            JOptionPane.showMessageDialog(null, "Inzerát "+idIzeratu+" bol označený ako predaný.","Upozornenie", JOptionPane.INFORMATION_MESSAGE);                            
                        }else{
                            JOptionPane.showMessageDialog(null, "Inzerát už bol označený ako predaný!", "Upozornenie", JOptionPane.WARNING_MESSAGE);
                        }                
                    } catch (NumberFormatException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }                
            }
        });
        
        aktivne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedRow = -1;
                selectedRow = table.getSelectedRow();
                if(selectedRow != -1){
                    try {
                        DefaultTableModel m = (DefaultTableModel)table.getModel();
                        String idIzeratu = m.getValueAt(selectedRow, 0).toString();
                        if(autobazar.getPredane(Integer.parseInt(idIzeratu))){
                            autobazar.setAktivne(Integer.parseInt(idIzeratu));  
                            JOptionPane.showMessageDialog(null, "Inzerát "+idIzeratu+" bol označený ako aktívny.","Upozornenie", JOptionPane.INFORMATION_MESSAGE);                            
                        }else{
                            JOptionPane.showMessageDialog(null, "Inzerát už je aktívny!", "Upozornenie", JOptionPane.WARNING_MESSAGE);
                        }                
                    } catch (NumberFormatException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }      
            }
        });
        /*delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Deleted");
            }
        });*/
        
        add(predane);
        add(aktivne);
        //add(new JSeparator());
        //add(delete);
    }
}
