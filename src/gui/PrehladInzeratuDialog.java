/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import autobazar.*;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class PrehladInzeratuDialog extends javax.swing.JDialog {

    private Inzerat inzerat;
    /**
     * Creates new form PrehladInzeratuDialog
     */
    public PrehladInzeratuDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jLabel1.setIcon(new ImageIcon("./icons/detail.png"));
        jLabel17.setIcon(new ImageIcon("./icons/user.png"));
        jLabel24.setIcon(new ImageIcon("./icons/vozidlo.png"));
    }
    
    public void zobrazPrehlad(){
        Kategoria k = inzerat.getKategoria();
        Predajca p = inzerat.getPredajca();
        DefaultTableModel m = (DefaultTableModel)jTableInfo.getModel();
        m.setRowCount(0);
        String [] row = {inzerat.getID()+"", inzerat.getStav().name(), inzerat.getDatumVytvorenia().toString()};
        m.addRow(row);
        
        DefaultTableModel m2 = (DefaultTableModel)jTablePredavajuci.getModel();
        m2.setRowCount(0);
        String [] row2 = {p.getMeno(), p.getPriezvisko(), p.getEmail(), p.getTelefon(), p.getLokalita()};
        m2.addRow(row2);            
        
        DefaultTableModel zakladneInfoVozidlo = (DefaultTableModel)jTableZakladne.getModel();
            zakladneInfoVozidlo.setRowCount(0);
        
        DefaultTableModel vybavaTable = (DefaultTableModel)jTableKategoria.getModel();
            vybavaTable.setRowCount(0);
            vybavaTable.setColumnCount(0);
               
        switch(inzerat.getInstanceKategoria()){
            case 1:
               Automobil auto = (Automobil)k;  
               jLabelKategoria.setText("Automobil");
               jLabelCena.setText(auto.getCena()+"");
               String [] row3 = {auto.getZnacka(),
                   auto.getModel(), 
                   auto.getKaroseria(), 
                   auto.getRokVyroby()+"", 
                   auto.getNajazdeneKM()+"", 
                   auto.getVykon()+"", 
                   auto.getPalivo(), 
                   auto.getStav().name()};
               zakladneInfoVozidlo.addRow(row3);
        
               vybavaTable.addColumn("Počet dverí");
               vybavaTable.addColumn("Počet sedadiel");
               String [] row4 = {auto.getPocetDveri()+"",
                   auto.getPocetSedadiel()+""};
               vybavaTable.addRow(row4);               
               break;
            case 2:
                Autobus autobus = (Autobus)k;
                jLabelCena.setText(autobus.getCena()+"");
                jLabelKategoria.setText("Autobus");
                String [] row5 = {autobus.getZnacka(), 
                    autobus.getModel(), 
                    autobus.getDruh(), 
                    autobus.getRokVyroby()+"", 
                    autobus.getNajazdeneKM()+"", 
                    autobus.getVykon()+"", 
                    autobus.getPalivo(), 
                    autobus.getStav().name()};
                zakladneInfoVozidlo.addRow(row5);
                
                JCheckBox b1 = new JCheckBox("Ziadny");
                b1.setVisible(true);
                b1.setSelected(autobus.getBatozinovyPriestor().isZiadny());
                       
                
                vybavaTable.addColumn("Norma emisií");
                vybavaTable.addColumn("Batožinový priestor");
                vybavaTable.addColumn("Nadstavba");
                vybavaTable.addColumn("Počet miest na sedenie");
                vybavaTable.addColumn("Vlastnosti sedadiel");
                String [] row6 = {autobus.getNormaEmisii(),
                    autobus.getBatozinovyPriestor().vratRetazec(),
                    autobus.getNadstavba(),
                    autobus.getPocetMiestNaSedenie()+"",
                    autobus.getVlastnostiSedadiel().vratRetazec()};
                vybavaTable.addRow(row6);                 
                break;
            case 3:
                Motocykel moto = (Motocykel)k;
                jLabelKategoria.setText("Motocykel");
                jLabelCena.setText(moto.getCena()+"");
                String [] row7 = {moto.getZnacka(), 
                    moto.getModel(), 
                    moto.getDruh(), 
                    moto.getRokVyroby()+"", 
                    moto.getNajazdeneKM()+"", 
                    moto.getVykon()+"", 
                    moto.getPalivo(), 
                    moto.getStav().name()};
                zakladneInfoVozidlo.addRow(row7);
                
                vybavaTable.addColumn("Objem válcov");
                String [] row8 = {moto.getObjemValcov()+""};
                vybavaTable.addRow(row8); 
                
                break;
            case 4:
                NakladneAuto nakladne = (NakladneAuto)k;
                jLabelKategoria.setText("Nakladné auto");
                jLabelCena.setText(nakladne.getCena()+"");
                String [] row9 = {nakladne.getZnacka(), 
                    nakladne.getModel(), 
                    nakladne.getDruh(), 
                    nakladne.getRokVyroby()+"", 
                    nakladne.getNajazdeneKM()+"", 
                    nakladne.getVykon()+"", 
                    nakladne.getPalivo(), 
                    nakladne.getStav().name()};
                zakladneInfoVozidlo.addRow(row9);
                vybavaTable.addColumn("Norma emisií");
                vybavaTable.addColumn("Hmotsnoť vozidla");
                vybavaTable.addColumn("Nosnosť");
                String [] row10 = {nakladne.getNormaEmisii()+"",
                    nakladne.getHmotnostVozidla()+"",
                    nakladne.getNosnost()+""};
                vybavaTable.addRow(row10); 
                break;
        }        
    }

    public void setInzerat(Inzerat inzerat) {
        this.inzerat = inzerat;
    }  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePredavajuci = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableInfo = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableZakladne = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableKategoria = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelCena = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelKategoria = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Prehľad inzerátu");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\detail.png")); // NOI18N
        jLabel1.setText("Informácie o inzeráte: ");

        jLabel17.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\user.png")); // NOI18N
        jLabel17.setText("Informácie o predajcovi:");

        jLabel24.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\vozidlo.png")); // NOI18N
        jLabel24.setText("Informácie o vozidle");

        jButton1.setText("Zavrieť");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTablePredavajuci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Meno", "Priezvisko", "Email", "Telefón", "Lokalita"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablePredavajuci);

        jTableInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Stav", "Dátum pridania"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableInfo);

        jTableZakladne.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Značka", "Model", "Karoséria", "Rok výroby", "Najazdene KM", "Výkon (kW)", "Palivo", "Stav"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableZakladne);

        jTableKategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableKategoria);

        jLabel13.setText("Základné informácie");

        jLabel14.setText("Výbava");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Cena:");

        jLabelCena.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelCena.setText("jLabel36");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("€");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Kategória vozidla: ");

        jLabelKategoria.setText("jLabel16");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelCena)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelKategoria)
                        .addGap(126, 126, 126))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel2)
                    .addComponent(jLabelKategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCena)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabelCena;
    private javax.swing.JLabel jLabelKategoria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTableInfo;
    private javax.swing.JTable jTableKategoria;
    private javax.swing.JTable jTablePredavajuci;
    private javax.swing.JTable jTableZakladne;
    // End of variables declaration//GEN-END:variables
}
