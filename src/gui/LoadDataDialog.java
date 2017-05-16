package gui;

import autobazar.Autobazar;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Dialogove okno pre získanie cesty z zdrojovému súboru pre aplikáciu.
 * @author Jakub Cachovan
 */
public class LoadDataDialog extends javax.swing.JDialog {

    private String path;
    private File file;
    static boolean isFromDB = false;
    static boolean isFromFile = false;
    /**
     * Creates new form LoadDataDialog
     */
    public LoadDataDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jButtonFromDB.setIcon(new ImageIcon("./icons/database.png"));
        jButtonFromFile.setIcon(new ImageIcon("./icons/file.png"));
        jButtonVyber.setIcon(new ImageIcon("./icons/more.png"));
        jButtonFromDB.setEnabled(false);
        jButtonFromFile.setEnabled(false);
        if(new File("./autobazar.sqlite").exists()){
            jButtonFromDB.setEnabled(true);
        }
        if(new File("./autobazar.txt").exists()){
            jButtonFromFile.setEnabled(true);
        }
    }
    
    /**
     * Getter pre cestu k zdrojovému súboru.
     * @return 
     */
    public String getPath() {
        return path;
    }

    /**
     * Getter pre zdrojový súbor.
     * @return 
     */
    public File getFile() {
        return file;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonFromFile = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButtonVyber = new javax.swing.JButton();
        jButtonNacitaj = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonFromDB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Načítanie dát");

        jButtonFromFile.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonFromFile.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\file.png")); // NOI18N
        jButtonFromFile.setText("From file");
        jButtonFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFromFileActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jButtonVyber.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\more.png")); // NOI18N
        jButtonVyber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVyberActionPerformed(evt);
            }
        });

        jButtonNacitaj.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonNacitaj.setText("Načítaj výber");
        jButtonNacitaj.setEnabled(false);
        jButtonNacitaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNacitajActionPerformed(evt);
            }
        });

        jButtonFromDB.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonFromDB.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\database.png")); // NOI18N
        jButtonFromDB.setText("From DB");
        jButtonFromDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFromDBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonFromDB, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTextField1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonVyber, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButtonNacitaj, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonFromFile, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButtonFromDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVyber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonNacitaj, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Vyvolanie okna pre výber zdrojového súboru.
     * @param evt 
     */
    private void jButtonVyberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVyberActionPerformed
        try {
            JFileChooser chooser = new JFileChooser("./");
            chooser.showOpenDialog(null);            
            file = chooser.getSelectedFile();
            path = file.getAbsolutePath();
            jTextField1.setText(path);
            jButtonNacitaj.setEnabled(true);
        } catch (NullPointerException e) {
            //JOptionPane.showMessageDialog(null, "Nebol vybratý žiadny súbor!", "Chyba", JOptionPane.ERROR_MESSAGE);
        }   
    }//GEN-LAST:event_jButtonVyberActionPerformed

    /**
     * Načítanie dát zo zdrojového súboru.
     * Overuje príponu súboru. Povolene su len TXT subory alebo SQLITE databázy
     * @param evt 
     */
    private void jButtonNacitajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNacitajActionPerformed
        Pattern p = Pattern.compile("(.sqlite)");
        Pattern p2 = Pattern.compile("(.txt)");
        Matcher m = p.matcher(path);   
        Matcher m2 = p2.matcher(path); 
        if(m.find()){
            //from DB
            isFromDB = true;
            dispose();
        }else if(m2.find()){
            //from file
            isFromFile = true;
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Nepodporovaný typ súboru !");
        }
    }//GEN-LAST:event_jButtonNacitajActionPerformed

    /**
     * Rýchle načítanie zo súboru.
     * @param evt 
     */
    private void jButtonFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFromFileActionPerformed
        path = "./autobazar.txt";
        file = new File(path);
        if(file.exists() && !file.isDirectory()) { 
            isFromFile = true;
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Toto načítavanie nie je dostupné pre toto PC", "Chyba!",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonFromFileActionPerformed

    /**
     * Rýchle načítanie z databázy.
     * @param evt 
     */
    private void jButtonFromDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFromDBActionPerformed
        path = "./autobazar.sqlite";
        file = new File(path);
        if(file.exists() && !file.isDirectory()) { 
            isFromDB = true;
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Toto načítavanie nie je dostupné pre toto PC", "Chyba!",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonFromDBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFromDB;
    private javax.swing.JButton jButtonFromFile;
    private javax.swing.JButton jButtonNacitaj;
    private javax.swing.JButton jButtonVyber;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
