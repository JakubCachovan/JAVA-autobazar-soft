package gui;

import autobazar.NakladneAuto;
import autobazar.StavVozidla;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Dialogove okno pre vytvorenie nákladného auta.
 * @author Jakub Cachovan
 */
public class NovyNakladakDialog extends javax.swing.JDialog {
    private NakladneAuto nakladne;
    private boolean upravaVozidla = false;
    /**
     * Creates new form NovyNakladakDialog
     */
    public NovyNakladakDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        for (StavVozidla stav : StavVozidla.values()) {
            jComboBoxStav.addItem(stav.name());
        }  
        jButtonUlozitInzerat.setIcon(new ImageIcon("./icons/save.png"));
    }

    /**
     * Getter pre atribut nakladne.
     * @return NakladneAuto
     */
    public NakladneAuto getNakladne() {
        return nakladne;
    }

    /**
     * Setter pre objekt typu NakladneAuto.
     * @param nakladne 
     */
    public void setNakladne(NakladneAuto nakladne) {
        this.nakladne = nakladne;
    }
    
    /**
     * Indikátor úpravy nákaladného auta.
     * @return 
     */
    public boolean isUpravaVozidla() {
        return upravaVozidla;
    }

    /**
     * Setter pre indikátor úpravy nákladného auta.
     * @param upravaVozidla 
     */
    public void setUpravaVozidla(boolean upravaVozidla) {
        this.upravaVozidla = upravaVozidla;
    }
    
    /**
     *  Inicializácia dát do formulárov, v prípade že sa jedná o úpravu.
     */
    public void NaplnFormular(){
        jTextFieldCena.setText(nakladne.getCena()+"");
        jTextFieldZnacka.setText(nakladne.getZnacka());
        jTextFieldModel.setText(nakladne.getModel());       
        jComboBoxPalivo.setSelectedItem(nakladne.getPalivo());                               
        jTextFieldRokVyroby.setText(nakladne.getRokVyroby()+"");
        jComboBoxStav.setSelectedItem(nakladne.getStav().name());
        jTextFieldVykon.setText(nakladne.getVykon()+"");
        jTextFieldPocetKM.setText(nakladne.getNajazdeneKM()+"");      
        jTextAreaPopis.setText(nakladne.getPopis());
        
        jTextFieldDruh.setText(nakladne.getDruh());
        jComboBoxNormaEmisie.setSelectedItem(nakladne.getNormaEmisii());
        jTextFieldNosnost.setText(nakladne.getNosnost()+"");
        jTextFieldHmotnost.setText(nakladne.getHmotnostVozidla()+"");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldHmotnost = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPopis = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxPalivo = new javax.swing.JComboBox<>();
        jTextFieldModel = new javax.swing.JTextField();
        jComboBoxNormaEmisie = new javax.swing.JComboBox<>();
        jTextFieldVykon = new javax.swing.JTextField();
        jTextFieldCena = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldRokVyroby = new javax.swing.JTextField();
        jTextFieldPocetKM = new javax.swing.JTextField();
        jTextFieldZnacka = new javax.swing.JTextField();
        jComboBoxStav = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButtonUlozitInzerat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNosnost = new javax.swing.JTextField();
        jTextFieldDruh = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nové nákladné auto");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel11.setText("Výkon (kW) *");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Hmostnosť vozidla");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel12.setText("Palivo *");

        jTextFieldHmotnost.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jTextAreaPopis.setColumns(20);
        jTextAreaPopis.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextAreaPopis.setRows(5);
        jScrollPane1.setViewportView(jTextAreaPopis);

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel13.setText("Cena *");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel14.setText("Počet najazdených kilometrov *");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Druh/Karoséria");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel19.setText("Popis");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel15.setText("Rok výroby *");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Norma emisií");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel16.setText("Stav *");

        jComboBoxPalivo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jComboBoxPalivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "benzín", "diesel", "iné" }));

        jTextFieldModel.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jComboBoxNormaEmisie.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jComboBoxNormaEmisie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Euro2", "Euro3", "Euro4", "Euro5", "Euro6" }));
        jComboBoxNormaEmisie.setSelectedIndex(-1);

        jTextFieldVykon.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextFieldVykon.setToolTipText("kW");

        jTextFieldCena.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel9.setText("Značka *");

        jTextFieldRokVyroby.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jTextFieldPocetKM.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jTextFieldZnacka.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jComboBoxStav.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel10.setText("Model *");

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel17.setText("€");

        jButtonUlozitInzerat.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonUlozitInzerat.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\save.png")); // NOI18N
        jButtonUlozitInzerat.setText("Uložiť inzerát");
        jButtonUlozitInzerat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUlozitInzeratActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("Nostnosť");

        jTextFieldNosnost.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jTextFieldDruh.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldHmotnost, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jComboBoxNormaEmisie, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldCena, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))
                            .addComponent(jLabel13)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonUlozitInzerat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16)
                                    .addComponent(jTextFieldVykon, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextFieldZnacka, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jLabel11)
                                    .addComponent(jComboBoxStav, 0, 200, Short.MAX_VALUE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextFieldNosnost)
                                    .addComponent(jTextFieldDruh))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jTextFieldModel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel12)
                                            .addComponent(jTextFieldRokVyroby)
                                            .addComponent(jComboBoxPalivo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jTextFieldPocetKM, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)
                                    .addComponent(jScrollPane1))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldZnacka, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldModel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRokVyroby, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldVykon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPocetKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPalivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxStav, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNosnost, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDruh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxNormaEmisie, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldHmotnost, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCena, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addGap(18, 18, 18)
                .addComponent(jButtonUlozitInzerat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Validácia formuláru pre úpravu nákladného auta.
     * @return 
     */
    public boolean validaciaFormularu(){
        if(jTextFieldCena.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nezadaná cena");
            return false;
        }else if(jTextFieldZnacka.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nezadaná značka");
            return false;
        }else if(jTextFieldModel.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nezadaný model");
            return false;
        }else if(jTextFieldRokVyroby.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nezadaný rok výroby");
            return false;
        }else if(jTextFieldVykon.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nezadaný výkon");
            return false;
        }else if(jTextFieldPocetKM.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nezadaný počet kilometrov");
            return false;
        }else if(jTextFieldDruh.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nezadaný druh");
            return false;
        }else if(jComboBoxPalivo.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Nezadaný typ paliva");
            return false;
        }
        return true;
    }
    
    /**
     * Uloženie kategorie vozidla pre inzerát.
     * @param evt 
     */
    private void jButtonUlozitInzeratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUlozitInzeratActionPerformed
        if(validaciaFormularu()){
            int cena = Integer.parseInt(jTextFieldCena.getText());
            String znacka = jTextFieldZnacka.getText();
            String model = jTextFieldModel.getText();
            int rokVyroby = Integer.parseInt(jTextFieldRokVyroby.getText());
            String palivo;
            StavVozidla stavVozidla = StavVozidla.valueOf(jComboBoxStav.getSelectedItem().toString());
            int vykon = Integer.parseInt(jTextFieldVykon.getText());
            int pocetKm = Integer.parseInt(jTextFieldPocetKM.getText());
            String druh = jTextFieldDruh.getText();
            int nosnost = Integer.parseInt(jTextFieldNosnost.getText());
            String popis = "";
            int hmotnostVozidla = Integer.parseInt(jTextFieldHmotnost.getText());
            String normaEmisii = jComboBoxNormaEmisie.getSelectedItem().toString();
            if(jComboBoxPalivo.getSelectedIndex() == 2){
                //ine
                palivo = JOptionPane.showInputDialog("Zadaj názov paliva");
            }else{
                palivo = jComboBoxPalivo.getSelectedItem().toString();
            }
            popis = jTextAreaPopis.getText();
            
            if(upravaVozidla){
                nakladne.setZnacka(znacka);
                nakladne.setModel(model);
                nakladne.setCena(cena);
                nakladne.setDruh(druh);
                nakladne.setNajazdeneKM(pocetKm);
                nakladne.setPalivo(palivo);                
                nakladne.setPopis(popis);
                nakladne.setStav(stavVozidla);
                nakladne.setVykon(vykon);
                nakladne.setRokVyroby(rokVyroby);
                
                nakladne.setHmotnostVozidla(hmotnostVozidla);
                nakladne.setNormaEmisii(normaEmisii);
                nakladne.setNosnost(nosnost);
                JOptionPane.showMessageDialog(null, "Informácie o nákladnom aute boli obnovené", "Upozornenie", JOptionPane.INFORMATION_MESSAGE);   
                dispose();
            }else{
                nakladne = new NakladneAuto(druh, hmotnostVozidla, normaEmisii, nosnost, cena, model, pocetKm, palivo, rokVyroby, stavVozidla, vykon, znacka, popis);
                dispose();
            }
        }

    }//GEN-LAST:event_jButtonUlozitInzeratActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonUlozitInzerat;
    private javax.swing.JComboBox<String> jComboBoxNormaEmisie;
    private javax.swing.JComboBox<String> jComboBoxPalivo;
    private javax.swing.JComboBox<String> jComboBoxStav;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaPopis;
    private javax.swing.JTextField jTextFieldCena;
    private javax.swing.JTextField jTextFieldDruh;
    private javax.swing.JTextField jTextFieldHmotnost;
    private javax.swing.JTextField jTextFieldModel;
    private javax.swing.JTextField jTextFieldNosnost;
    private javax.swing.JTextField jTextFieldPocetKM;
    private javax.swing.JTextField jTextFieldRokVyroby;
    private javax.swing.JTextField jTextFieldVykon;
    private javax.swing.JTextField jTextFieldZnacka;
    // End of variables declaration//GEN-END:variables
}
