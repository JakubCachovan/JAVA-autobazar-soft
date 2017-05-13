package gui;

import autobazar.*;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import sql.*;

/**
 * 
 * @author Jakub Cachovan
 */
public final class Aplikacia extends javax.swing.JFrame {
    static String DbPath = null;
    static String FilePath = null;
    private Autobazar _autobazar = new Autobazar();
    /**
     * Creates new form Aplikacia
     */
    public Aplikacia() {
        initComponents();
        initialIcons();
        initSystemTray();
        LoadDataDialog loadData = new LoadDataDialog(this, true);
        loadData.setIconImage(Toolkit.getDefaultToolkit().getImage("./icons/spinner.png"));
        loadData.setLocationRelativeTo(null);
        loadData.setVisible(true);
        if(LoadDataDialog.isFromDB){
            DbPath = loadData.getPath();
            Autobazar nacitanyAutobazar = LoaderDB.LoadFromDB(DbPath);
            if(nacitanyAutobazar != null){
                _autobazar = nacitanyAutobazar;
                jButtonSynchronize.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null, "Načítanie z databázy zlyhalo !", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        }else if(LoadDataDialog.isFromFile){
            FilePath = loadData.getPath(); 
            Autobazar nacitanyAutobazar = Autobazar.loadFromFile(loadData.getFile());
            if(nacitanyAutobazar != null){
                _autobazar = nacitanyAutobazar;                 
            }else{
                JOptionPane.showMessageDialog(null, "Načítanie zo súboru zlyhalo !", "Chyba", JOptionPane.ERROR_MESSAGE);          
            }           
        }else{
            Runtime.getRuntime().exit(0);
        }  
        
        ObnovitVsetkyTabulky();
        jTableInzeraty.setAutoCreateRowSorter(true);
        jTableAutobusy.setAutoCreateRowSorter(true);
        jTableAutomobily.setAutoCreateRowSorter(true);
        jTableInzeratyPredajcu.setAutoCreateRowSorter(true);
        jTableMotocykle.setAutoCreateRowSorter(true);
        jTableNakladne.setAutoCreateRowSorter(true);
        jTablePredajcovia.setAutoCreateRowSorter(true);
    }
    
    public void initSystemTray(){
        TrayIcon trayIcon = null;
        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            SystemTray tray = SystemTray.getSystemTray();
            // load an image
            Image image = Toolkit.getDefaultToolkit().getImage("./icons/car-compact2.png");
            // create a action listener to listen for default action executed on the tray icon
            ActionListener listenerForNew = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Thread vlakno = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            new Aplikacia().setVisible(true);
                        }
                    });
                    vlakno.start();
                }
            };
            ActionListener listenerForUkoncit = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Runtime.getRuntime().exit(0);
                }
            };
            // create a popup menu
            PopupMenu popup = new PopupMenu();
            // create menu item for the default action
            MenuItem novy = new MenuItem("Nové okno");
            MenuItem ukoncit = new MenuItem("Exit");
            novy.addActionListener(listenerForNew);
            ukoncit.addActionListener(listenerForUkoncit);
            popup.add(novy); 
            popup.addSeparator();
            popup.add(ukoncit);
            /// ... add other items
            // construct a TrayIcon
            trayIcon = new TrayIcon(image, "Autobazar TRAY", popup);
            // set the TrayIcon properties
            trayIcon.addActionListener(listenerForNew);
            trayIcon.addActionListener(listenerForUkoncit);
            // ...
            // add the tray image
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
            // ...
        } else {
            // disable tray option in your application or
            // perform other actions
        }
    }
    
    public void initialIcons(){
        jButtonAktivny.setIcon(new ImageIcon("./icons/aktivne.png"));
        jButtonFilter.setIcon(new ImageIcon("./icons/filter.png"));
        jButtonNacitajPredajcov.setIcon(new ImageIcon("./icons/obnovit.png"));
        jButtonPredane.setIcon(new ImageIcon("./icons/neaktivne.png"));
        jButtonSaveToFile.setIcon(new ImageIcon("./icons/save.png"));
        jButtonNovyInzerat.setIcon(new ImageIcon("./icons/pridat.png"));
        jButtonUpravitInzerat.setIcon(new ImageIcon("./icons/upravit.png"));
        jButtonVymazatInzerat.setIcon(new ImageIcon("./icons/vymazat.png"));
        jButtonUpravitPredajcu.setIcon(new ImageIcon("./icons/upravit.png"));
        jButtonVymazatPredajcu.setIcon(new ImageIcon("./icons/vymazat.png"));
        jButtonZobrazInzerat.setIcon(new ImageIcon("./icons/detail.png"));
    }
    
    public void ObnovitVsetkyTabulky(){
        FillTableInzeraty();
        FillTableAutomobily();
        FillTableMotocykle();
        FillTableNakladne();
        FillTableAutobusy();
        FillTablePredajcovia();
    }
    
    public void FillTableInzeraty(){
        jLabelHodnota.setText(_autobazar.hodnotaVsetkychVozidiel()+" €");
        
        DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
        m.setRowCount(0);
        for (Inzerat inzerat : _autobazar.getZoznamInzeratov()) {     
             String [] riadok = {""+inzerat.getID()+"",
                 inzerat.getDatumVytvorenia().toString(), 
                 inzerat.getKategoriaInstanceName(), 
                 inzerat.getKategoria().getZnacka(), 
                 inzerat.getKategoria().getModel(), 
                 inzerat.getPredajca().getEmail(), 
                 inzerat.getStav().name(),
                 inzerat.getKategoria().getCena()+""};
             m.addRow(riadok);
        }       
    }
    
    public void FillTableInzeraty(ArrayList<Inzerat> inzeraty){   
        DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
        m.setRowCount(0);
        for (Inzerat inzerat : inzeraty) {     
             String [] riadok = {""+inzerat.getID()+"",
                 inzerat.getDatumVytvorenia().toString(), 
                 inzerat.getKategoriaInstanceName(), 
                 inzerat.getKategoria().getZnacka(), 
                 inzerat.getKategoria().getModel(), 
                 inzerat.getPredajca().getEmail(), 
                 inzerat.getStav().name(),
                 inzerat.getKategoria().getCena()+""};
             m.addRow(riadok);
        }       
    }
    
    public void FillTableAutomobily(){
        DefaultTableModel m = (DefaultTableModel)jTableAutomobily.getModel();
        m.setRowCount(0);
        for (Inzerat inzerat : _autobazar.getZoznamInzeratov()) {     
            if(inzerat.getInstanceKategoria() == 1){
                Automobil auto = (Automobil)inzerat.getKategoria();
                String [] riadok = {
                    auto.getZnacka(),
                    auto.getModel(),
                    auto.getRokVyroby()+"",
                    auto.getNajazdeneKM()+"",
                    auto.getPalivo(),
                    auto.getStav().name(),
                    auto.getVykon()+"",               
                    auto.getKaroseria(),
                    auto.getPocetDveri()+"",
                    auto.getPocetSedadiel()+"",
                    auto.getPopis(),
                    auto.getCena()+""};
             m.addRow(riadok);
            } 
        }       
    }
    
    public void FillTableMotocykle(){
        DefaultTableModel m = (DefaultTableModel)jTableMotocykle.getModel();
        m.setRowCount(0);
        for (Inzerat inzerat : _autobazar.getZoznamInzeratov()) {     
            if(inzerat.getInstanceKategoria() == 3){
                Motocykel moto = (Motocykel)inzerat.getKategoria();
                String [] riadok = {
                    moto.getZnacka(),
                    moto.getModel(),
                    moto.getRokVyroby()+"",
                    moto.getNajazdeneKM()+"",
                    moto.getPalivo(),
                    moto.getStav().name(),
                    moto.getVykon()+"",               
                    moto.getDruh(),
                    moto.getObjemValcov()+"",
                    moto.getPopis(),
                    moto.getCena()+""};
             m.addRow(riadok);
            } 
        }       
    }
    
    public void FillTableNakladne(){
        DefaultTableModel m = (DefaultTableModel)jTableNakladne.getModel();
        m.setRowCount(0);
        for (Inzerat inzerat : _autobazar.getZoznamInzeratov()) {     
            if(inzerat.getInstanceKategoria() == 4){
                NakladneAuto nakladne = (NakladneAuto)inzerat.getKategoria();
                String [] riadok = {
                    nakladne.getZnacka(),
                    nakladne.getModel(),
                    nakladne.getRokVyroby()+"",
                    nakladne.getNajazdeneKM()+"",
                    nakladne.getPalivo(),
                    nakladne.getStav().name(),
                    nakladne.getVykon()+"",               
                    nakladne.getDruh(),
                    nakladne.getHmotnostVozidla()+"",
                    nakladne.getNormaEmisii()+"",
                    nakladne.getNosnost()+"",
                    nakladne.getPopis(),
                    nakladne.getCena()+""};
             m.addRow(riadok);
            } 
        }       
    }
    
    public void FillTableAutobusy(){
        DefaultTableModel m = (DefaultTableModel)jTableAutobusy.getModel();
        m.setRowCount(0);
        for (Inzerat inzerat : _autobazar.getZoznamInzeratov()) {     
            if(inzerat.getInstanceKategoria() == 2){
                Autobus autobus = (Autobus)inzerat.getKategoria();
                String [] riadok = {
                    autobus.getZnacka(),
                    autobus.getModel(),
                    autobus.getRokVyroby()+"",
                    autobus.getNajazdeneKM()+"",
                    autobus.getPalivo(),
                    autobus.getStav().name(),
                    autobus.getVykon()+"",               
                    autobus.getDruh(),
                    autobus.getNormaEmisii()+"",
                    autobus.getBatozinovyPriestor().vratRetazec()+"",
                    autobus.getNadstavba()+"",
                    autobus.getPopis(),
                    autobus.getCena()+"",
                    autobus.getPocetMiestNaSedenie()+"",
                    autobus.getVlastnostiSedadiel().vratRetazec()};
             m.addRow(riadok);
            } 
        }       
    }
    
    public void FillTablePredajcovia(){
        DefaultTableModel m = (DefaultTableModel)jTablePredajcovia.getModel();
        m.setRowCount(0);
        for (Predajca p : _autobazar.getZoznamPredajcov()) {
            String [] row = {p.getMeno(), p.getPriezvisko(), p.getEmail(),p.getTelefon(), p.getLokalita()};
            m.addRow(row);
        }   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelPrehlad = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableInzeraty = new javax.swing.JTable();
        jButtonNovyInzerat = new javax.swing.JButton();
        jButtonPredane = new javax.swing.JButton();
        jButtonZobrazInzerat = new javax.swing.JButton();
        jButtonUpravitInzerat = new javax.swing.JButton();
        jButtonVymazatInzerat = new javax.swing.JButton();
        jButtonSaveToFile = new javax.swing.JButton();
        jRadioButtonVsetky = new javax.swing.JRadioButton();
        jRadioButtonAktivne = new javax.swing.JRadioButton();
        jRadioButtonNeaktivne = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jButtonAktivny = new javax.swing.JButton();
        jTextFieldFastSearch = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabelHodnota = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jButtonFilter = new javax.swing.JButton();
        jButtonSynchronize = new javax.swing.JButton();
        jPanelPredajcovia = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePredajcovia = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableInzeratyPredajcu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButtonNacitajPredajcov = new javax.swing.JButton();
        jButtonUpravitPredajcu = new javax.swing.JButton();
        jButtonVymazatPredajcu = new javax.swing.JButton();
        jPanel_Automobily = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAutomobily = new javax.swing.JTable();
        jPanelMotocykle = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableMotocykle = new javax.swing.JTable();
        jPanelAutobusy = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableAutobusy = new javax.swing.JTable();
        jPanelNakladne = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableNakladne = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autobazaris");
        setBackground(java.awt.Color.lightGray);

        jTabbedPane1.setBackground(java.awt.Color.lightGray);

        jPanelPrehlad.setBackground(java.awt.Color.lightGray);
        jPanelPrehlad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanelPrehladKeyPressed(evt);
            }
        });

        jTableInzeraty.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTableInzeraty.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Dátum vytvorenia", "Vozdilo", "Značka", "Model", "Predajca", "Stav", "Cena (€)"
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
        jTableInzeraty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableInzeratyMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableInzeraty);
        if (jTableInzeraty.getColumnModel().getColumnCount() > 0) {
            jTableInzeraty.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        jButtonNovyInzerat.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonNovyInzerat.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\pridat.png")); // NOI18N
        jButtonNovyInzerat.setText("Nový inzerát");
        jButtonNovyInzerat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovyInzeratActionPerformed(evt);
            }
        });

        jButtonPredane.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonPredane.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\neaktivne.png")); // NOI18N
        jButtonPredane.setText("Deaktivovať");
        jButtonPredane.setEnabled(false);
        jButtonPredane.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPredaneActionPerformed(evt);
            }
        });

        jButtonZobrazInzerat.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonZobrazInzerat.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\detail.png")); // NOI18N
        jButtonZobrazInzerat.setText("Zobraz detail");
        jButtonZobrazInzerat.setEnabled(false);
        jButtonZobrazInzerat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZobrazInzeratActionPerformed(evt);
            }
        });

        jButtonUpravitInzerat.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonUpravitInzerat.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\upravit.png")); // NOI18N
        jButtonUpravitInzerat.setText("Upraviť inzerát");
        jButtonUpravitInzerat.setEnabled(false);
        jButtonUpravitInzerat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpravitInzeratActionPerformed(evt);
            }
        });

        jButtonVymazatInzerat.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonVymazatInzerat.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\vymazat.png")); // NOI18N
        jButtonVymazatInzerat.setText("Vymazať inzerát");
        jButtonVymazatInzerat.setEnabled(false);
        jButtonVymazatInzerat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVymazatInzeratActionPerformed(evt);
            }
        });

        jButtonSaveToFile.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonSaveToFile.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\save.png")); // NOI18N
        jButtonSaveToFile.setText("Uložiť do súboru");
        jButtonSaveToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveToFileActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonVsetky);
        jRadioButtonVsetky.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jRadioButtonVsetky.setSelected(true);
        jRadioButtonVsetky.setText("Všetky");
        jRadioButtonVsetky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonVsetkyActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonAktivne);
        jRadioButtonAktivne.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jRadioButtonAktivne.setText("Aktívne");
        jRadioButtonAktivne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAktivneActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonNeaktivne);
        jRadioButtonNeaktivne.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jRadioButtonNeaktivne.setText("Neaktívne");
        jRadioButtonNeaktivne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNeaktivneActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\filter.png")); // NOI18N
        jLabel3.setText("Filter zobrazenia:");

        jButtonAktivny.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonAktivny.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\aktivne.png")); // NOI18N
        jButtonAktivny.setText("Aktivovať");
        jButtonAktivny.setToolTipText("");
        jButtonAktivny.setEnabled(false);
        jButtonAktivny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAktivnyActionPerformed(evt);
            }
        });

        jTextFieldFastSearch.setBackground(java.awt.Color.lightGray);
        jTextFieldFastSearch.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextFieldFastSearch.setText("Zadajte kľúčové slovo");
        jTextFieldFastSearch.setToolTipText("Zadajte kľúčové slovo");
        jTextFieldFastSearch.setBorder(null);
        jTextFieldFastSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldFastSearchMouseClicked(evt);
            }
        });
        jTextFieldFastSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldFastSearchKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel4.setText("Celková hodnota vozidiel:");

        jLabelHodnota.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabelHodnota.setText("0 €");

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\search.png")); // NOI18N

        jButtonFilter.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonFilter.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\filter.png")); // NOI18N
        jButtonFilter.setText("Filter");
        jButtonFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFilterActionPerformed(evt);
            }
        });

        jButtonSynchronize.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonSynchronize.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\obnovit.png")); // NOI18N
        jButtonSynchronize.setText("Synch s DB");
        jButtonSynchronize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSynchronizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPrehladLayout = new javax.swing.GroupLayout(jPanelPrehlad);
        jPanelPrehlad.setLayout(jPanelPrehladLayout);
        jPanelPrehladLayout.setHorizontalGroup(
            jPanelPrehladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrehladLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPrehladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNovyInzerat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVymazatInzerat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonUpravitInzerat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSaveToFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButtonNeaktivne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButtonAktivne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButtonVsetky, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelHodnota)
                    .addComponent(jLabel4)
                    .addComponent(jButtonFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSynchronize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanelPrehladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanelPrehladLayout.createSequentialGroup()
                        .addComponent(jButtonZobrazInzerat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPrehladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldFastSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 624, Short.MAX_VALUE)
                        .addComponent(jButtonPredane, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAktivny, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelPrehladLayout.setVerticalGroup(
            jPanelPrehladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrehladLayout.createSequentialGroup()
                .addGroup(jPanelPrehladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrehladLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jTextFieldFastSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrehladLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelPrehladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPrehladLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPrehladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonNovyInzerat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonPredane, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonZobrazInzerat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonAktivny, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(10, 10, 10)
                .addGroup(jPanelPrehladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrehladLayout.createSequentialGroup()
                        .addComponent(jButtonUpravitInzerat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonVymazatInzerat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonVsetky)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonAktivne)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonNeaktivne)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHodnota)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSynchronize, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSaveToFile, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Správa inzerátov", jPanelPrehlad);

        jPanelPredajcovia.setBackground(java.awt.Color.lightGray);

        jTablePredajcovia.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTablePredajcovia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Meno", "Priezvisko", "Email", "Telefónne číslo", "Lokalita"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePredajcovia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePredajcoviaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTablePredajcovia);

        jTableInzeratyPredajcu.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTableInzeratyPredajcu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "dátum pridania", "stav", "Kategória"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableInzeratyPredajcu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableInzeratyPredajcuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableInzeratyPredajcu);

        jLabel1.setText("Inzeráty predajcu");

        jButtonNacitajPredajcov.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonNacitajPredajcov.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\obnovit.png")); // NOI18N
        jButtonNacitajPredajcov.setText("Obnoviť");
        jButtonNacitajPredajcov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNacitajPredajcovActionPerformed(evt);
            }
        });

        jButtonUpravitPredajcu.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonUpravitPredajcu.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\upravit.png")); // NOI18N
        jButtonUpravitPredajcu.setText("Upraviť");
        jButtonUpravitPredajcu.setEnabled(false);
        jButtonUpravitPredajcu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpravitPredajcuActionPerformed(evt);
            }
        });

        jButtonVymazatPredajcu.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButtonVymazatPredajcu.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Documents\\NetBeansProjects\\Autobazar\\icons\\vymazat.png")); // NOI18N
        jButtonVymazatPredajcu.setText("Vymazať");
        jButtonVymazatPredajcu.setEnabled(false);
        jButtonVymazatPredajcu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVymazatPredajcuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPredajcoviaLayout = new javax.swing.GroupLayout(jPanelPredajcovia);
        jPanelPredajcovia.setLayout(jPanelPredajcoviaLayout);
        jPanelPredajcoviaLayout.setHorizontalGroup(
            jPanelPredajcoviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPredajcoviaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPredajcoviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPredajcoviaLayout.createSequentialGroup()
                        .addComponent(jButtonNacitajPredajcov, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUpravitPredajcu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonVymazatPredajcu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPredajcoviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                    .addGroup(jPanelPredajcoviaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelPredajcoviaLayout.setVerticalGroup(
            jPanelPredajcoviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPredajcoviaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPredajcoviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPredajcoviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonNacitajPredajcov, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonUpravitPredajcu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonVymazatPredajcu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPredajcoviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Prehľad predajcov", jPanelPredajcovia);

        jPanel_Automobily.setBackground(java.awt.Color.lightGray);

        jTableAutomobily.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTableAutomobily.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Značka", "Model", "Rok výroby", "Počet KM", "Palivo", "Stav", "Výkon", "Karoséria", "Počet dverí", "Počet sedadiel", "Popis", "Cena"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableAutomobily);

        javax.swing.GroupLayout jPanel_AutomobilyLayout = new javax.swing.GroupLayout(jPanel_Automobily);
        jPanel_Automobily.setLayout(jPanel_AutomobilyLayout);
        jPanel_AutomobilyLayout.setHorizontalGroup(
            jPanel_AutomobilyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AutomobilyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1433, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_AutomobilyLayout.setVerticalGroup(
            jPanel_AutomobilyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AutomobilyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Automobily", jPanel_Automobily);

        jPanelMotocykle.setBackground(java.awt.Color.lightGray);

        jTableMotocykle.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTableMotocykle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Značka", "Model", "Rok výroby", "Počet KM", "Palivo", "Stav", "Výkon", "Druh", "Objem válcov", "Popis", "Cena"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableMotocykle);

        javax.swing.GroupLayout jPanelMotocykleLayout = new javax.swing.GroupLayout(jPanelMotocykle);
        jPanelMotocykle.setLayout(jPanelMotocykleLayout);
        jPanelMotocykleLayout.setHorizontalGroup(
            jPanelMotocykleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMotocykleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1433, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMotocykleLayout.setVerticalGroup(
            jPanelMotocykleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMotocykleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Motocykle", jPanelMotocykle);

        jPanelAutobusy.setBackground(java.awt.Color.lightGray);

        jTableAutobusy.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTableAutobusy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Značka", "Model", "Rok výroby", "Počet KM", "Palivo", "Stav", "Výkon", "Druh", "Norma emisií", "Batožinový priestor", "Nadstavba", "Popis", "Cena", "Počet miest", "Vlastnosti sedadiel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTableAutobusy);

        javax.swing.GroupLayout jPanelAutobusyLayout = new javax.swing.GroupLayout(jPanelAutobusy);
        jPanelAutobusy.setLayout(jPanelAutobusyLayout);
        jPanelAutobusyLayout.setHorizontalGroup(
            jPanelAutobusyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAutobusyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1433, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelAutobusyLayout.setVerticalGroup(
            jPanelAutobusyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAutobusyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Autobusy", jPanelAutobusy);

        jPanelNakladne.setBackground(java.awt.Color.lightGray);

        jTableNakladne.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTableNakladne.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Značka", "Model", "Rok výroby", "Počet KM", "Palivo", "Stav", "Výkon", "Druh", "Hmotnosť vozidla", "Norma emisií", "Nosnosť", "Popis", "Cena"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTableNakladne);

        javax.swing.GroupLayout jPanelNakladneLayout = new javax.swing.GroupLayout(jPanelNakladne);
        jPanelNakladne.setLayout(jPanelNakladneLayout);
        jPanelNakladneLayout.setHorizontalGroup(
            jPanelNakladneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNakladneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1433, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelNakladneLayout.setVerticalGroup(
            jPanelNakladneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNakladneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nákladné vozidlá", jPanelNakladne);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNacitajPredajcovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNacitajPredajcovActionPerformed
        DefaultTableModel m = (DefaultTableModel)jTableInzeratyPredajcu.getModel();
        m.setRowCount(0);
        ObnovitVsetkyTabulky();
        jButtonUpravitPredajcu.setEnabled(false);
        jButtonVymazatPredajcu.setEnabled(false);
    }//GEN-LAST:event_jButtonNacitajPredajcovActionPerformed

    private void jTablePredajcoviaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePredajcoviaMouseClicked
        // TODO add your handling code here:
        int selectedRow = -1;
        selectedRow = jTablePredajcovia.getSelectedRow();
        DefaultTableModel mPredajcovia = (DefaultTableModel)jTablePredajcovia.getModel();
        DefaultTableModel m = (DefaultTableModel)jTableInzeratyPredajcu.getModel();
        m.setRowCount(0);
        if(selectedRow != -1){
            for (Inzerat inzerat : _autobazar.getZoznamInzeratov()) {
                if(inzerat.getPredajca().getEmail().equalsIgnoreCase(mPredajcovia.getValueAt(selectedRow, 2).toString())){
                    String [] row = {inzerat.getID()+"", inzerat.getDatumVytvorenia().toString(), inzerat.getStav().name(), inzerat.getKategoriaInstanceName()};
                    m.addRow(row);
                }               
            }
            jButtonUpravitPredajcu.setEnabled(true);
            jButtonVymazatPredajcu.setEnabled(true);
        }
    }//GEN-LAST:event_jTablePredajcoviaMouseClicked

    private void jButtonUpravitPredajcuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpravitPredajcuActionPerformed
        int selectedRow = -1;
        selectedRow = jTablePredajcovia.getSelectedRow();
        if(selectedRow != -1){
            DefaultTableModel m = (DefaultTableModel)jTablePredajcovia.getModel(); 
            NovyPredajcaDialog predajcaDialog = new NovyPredajcaDialog(null, true);
            predajcaDialog.setLocationRelativeTo(null);            
            Predajca predajca = _autobazar.najdiPredajcu(m.getValueAt(selectedRow, 2).toString());          
            predajcaDialog.setPredajca(predajca);
            predajcaDialog.NaplnFormular();
            predajcaDialog.setUpravaPredajcu(true);
            predajcaDialog.setTitle("Úprava predajcu");
            predajcaDialog.setVisible(true);
            
            Predajca upravenyPredajca = predajcaDialog.getPredajca();
            if(DbPath != null){
                if(!Update.upravitPredajcu(upravenyPredajca, DbPath)){
                    JOptionPane.showMessageDialog(null, "Úprava predajcu v databáze zlyhala!", "Chyba!", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(FilePath != null){
                try {
                    _autobazar.saveToFile(new File(FilePath));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e, "Chyba", JOptionPane.ERROR);
                }
            }
        }
        ObnovitVsetkyTabulky();
        
    }//GEN-LAST:event_jButtonUpravitPredajcuActionPerformed

    private void jButtonVymazatPredajcuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVymazatPredajcuActionPerformed
        int selectedRow = -1;
        selectedRow = jTablePredajcovia.getSelectedRow();
        DefaultTableModel m = (DefaultTableModel)jTablePredajcovia.getModel();       
        if(selectedRow != -1){
            int vysl = JOptionPane.showConfirmDialog(null, "Naozaj chcete vymazat predajcu?", "Posledna šanca", JOptionPane.YES_NO_OPTION);
            if(vysl == 0){  
                for (Predajca p : _autobazar.getZoznamPredajcov()) {
                    if(p.getEmail().equalsIgnoreCase(m.getValueAt(selectedRow, 2).toString())){
                        String email = p.getEmail();
                        if(_autobazar.vymazatPredajcu(p.getEmail())){
                            JOptionPane.showMessageDialog(null, "Predajca vymazaný", "Upozornenie!", JOptionPane.WARNING_MESSAGE);
                            m.removeRow(selectedRow);
                            DefaultTableModel m2 = (DefaultTableModel)jTableInzeratyPredajcu.getModel();  
                            m2.setRowCount(0);
                            if(DbPath != null){
                                //vymazat z DB
                                DbDelete.vymazatInzeratyPredajcu(email, DbPath);
                                DbDelete.vymazPredajcu(email, DbPath);
                            }
                            if(FilePath != null){
                                try {
                                    _autobazar.saveToFile(new File(FilePath));
                                } catch (IOException e) {
                                    JOptionPane.showMessageDialog(null, e, "Chyba", JOptionPane.ERROR);
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Pri vymazávaní nastala chyba", "Chyba!", JOptionPane.ERROR);
                        }
                    }                  
                }
                jButtonUpravitPredajcu.setEnabled(false);
                jButtonVymazatPredajcu.setEnabled(false);
                ObnovitVsetkyTabulky();
            }
        }
        
        
    }//GEN-LAST:event_jButtonVymazatPredajcuActionPerformed

    private void jPanelPrehladKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanelPrehladKeyPressed
        JOptionPane.showMessageDialog(null, "Key pressed");
    }//GEN-LAST:event_jPanelPrehladKeyPressed

    private void jTextFieldFastSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFastSearchKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jButtonUpravitInzerat.setEnabled(false);
            jButtonVymazatInzerat.setEnabled(false);
            jButtonAktivny.setEnabled(false);
            jButtonPredane.setEnabled(false);
            jButtonZobrazInzerat.setEnabled(false);
            String hladanyText = "";
            hladanyText = jTextFieldFastSearch.getText();
            if(!hladanyText.equalsIgnoreCase("")){
                //vysledok hladania
                ArrayList<Inzerat> najdeneInzeraty = _autobazar.keyWordSearch(hladanyText);
                FillTableInzeraty(najdeneInzeraty);
            }else{
                ObnovitVsetkyTabulky();
            }
        }
    }//GEN-LAST:event_jTextFieldFastSearchKeyPressed

    private void jTextFieldFastSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldFastSearchMouseClicked
        jTextFieldFastSearch.selectAll();
        jButtonUpravitInzerat.setEnabled(false);
        jButtonVymazatInzerat.setEnabled(false);
        jButtonAktivny.setEnabled(false);
        jButtonPredane.setEnabled(false);
        jButtonZobrazInzerat.setEnabled(false);
    }//GEN-LAST:event_jTextFieldFastSearchMouseClicked

    private void jButtonAktivnyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAktivnyActionPerformed
        int selectedRow = -1;
        selectedRow = jTableInzeraty.getSelectedRow();
        if(selectedRow != -1){
            try {
                DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
                String idIzeratu = m.getValueAt(selectedRow, 0).toString();
                if(!_autobazar.getAktivne(Integer.parseInt(idIzeratu))){
                    _autobazar.setAktivne(Integer.parseInt(idIzeratu));
                    if(DbPath != null){
                        if(!sql.Update.upravitInzerat(_autobazar.najdiInzerat(Integer.parseInt(idIzeratu)), DbPath)){
                            JOptionPane.showMessageDialog(null, "Chyba pri upraveni v DB");
                        }
                    }
                    if(FilePath != null){
                        try {
                            _autobazar.saveToFile(new File(FilePath));
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, e, "Chyba", JOptionPane.ERROR);
                        }
                    }
                    FillTableInzeraty();
                    JOptionPane.showMessageDialog(null, "Inzerát "+idIzeratu+" bol označený ako aktívny.","Upozornenie", JOptionPane.INFORMATION_MESSAGE);
                    jButtonAktivny.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Inzerát je stále aktívny!", "Upozornenie", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButtonAktivnyActionPerformed

    private void jRadioButtonNeaktivneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNeaktivneActionPerformed
        DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
        m.setRowCount(0);
        for (Inzerat inzerat : _autobazar.getZoznamInzeratov()) {
            if(inzerat.getStav().equals(StavInzeratu.Predane)){
                String [] row = {inzerat.getID()+"", inzerat.getDatumVytvorenia().toString(), inzerat.getKategoriaInstanceName()
                    ,inzerat.getKategoria().getZnacka(), inzerat.getKategoria().getModel(), inzerat.getPredajca().getEmail(), inzerat.getStav().name(), inzerat.getKategoria().getCena()+""};
                m.addRow(row);
            }
        }
    }//GEN-LAST:event_jRadioButtonNeaktivneActionPerformed

    private void jRadioButtonAktivneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAktivneActionPerformed
        DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
        m.setRowCount(0);
        for (Inzerat inzerat : _autobazar.getZoznamInzeratov()) {
            if(inzerat.getStav().equals(StavInzeratu.Aktivne)){
                String [] row = {inzerat.getID()+"", inzerat.getDatumVytvorenia().toString(), inzerat.getKategoriaInstanceName()
                    ,inzerat.getKategoria().getZnacka(), inzerat.getKategoria().getModel(), inzerat.getPredajca().getEmail(), inzerat.getStav().name(), inzerat.getKategoria().getCena()+""};
                m.addRow(row);
            }
        }
    }//GEN-LAST:event_jRadioButtonAktivneActionPerformed

    private void jRadioButtonVsetkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonVsetkyActionPerformed
        FillTableInzeraty();
    }//GEN-LAST:event_jRadioButtonVsetkyActionPerformed

    private void jButtonSaveToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveToFileActionPerformed
        try {
            JFileChooser fileChooser = new JFileChooser("./");
            fileChooser.setSelectedFile(new File("autobazar.txt"));
            if (fileChooser.showSaveDialog(this)== JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if(file.exists() && !file.isDirectory()) { 
                    
                }
                if(_autobazar.saveToFile(file)){
                    JOptionPane.showMessageDialog(null, "Uloženie úspešné!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Chyba pri ukladaní do súboru", "Chyba", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonSaveToFileActionPerformed

    private void jButtonVymazatInzeratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVymazatInzeratActionPerformed
        int selectedRow = -1;
        selectedRow = jTableInzeraty.getSelectedRow();
        if(selectedRow != -1){
            DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
            String idIzeratu = m.getValueAt(selectedRow, 0).toString();
            int vysl = JOptionPane.showConfirmDialog(null, "Naozaj chcete vymazat inzerát?", "Posledna šanca", JOptionPane.YES_NO_OPTION);
            if(vysl == 0){
                _autobazar.vymazatInzerat(Integer.parseInt(idIzeratu));
                if(DbPath != null){
                    //vymazanie z DB
                    DbDelete.vymazatInzerat(Integer.parseInt(idIzeratu), DbPath);
                }
                if(FilePath != null){
                    try {
                        _autobazar.saveToFile(new File(FilePath));
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e, "Chyba", JOptionPane.ERROR);
                    }
                }
                jButtonVymazatInzerat.setEnabled(false);
                jButtonUpravitInzerat.setEnabled(false);
                jButtonZobrazInzerat.setEnabled(false);
                jButtonPredane.setEnabled(false);
                jButtonAktivny.setEnabled(false);
                ObnovitVsetkyTabulky();
            }
        }

    }//GEN-LAST:event_jButtonVymazatInzeratActionPerformed

    private void jButtonUpravitInzeratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpravitInzeratActionPerformed
        int selectedRow = -1;
        selectedRow = jTableInzeraty.getSelectedRow();
        if(selectedRow != -1){
            DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
            String idInzeratu = m.getValueAt(selectedRow, 0).toString();
            Inzerat inzerat = _autobazar.najdiInzerat(Integer.parseInt(idInzeratu));
            if(inzerat != null){
                UpravitInzeratDialog edit = new UpravitInzeratDialog(this, true);
                edit.setLocationRelativeTo(null);
                edit.setInzerat(inzerat);
                edit.setVisible(true);
                if(DbPath != null && (edit.isUpravaVozidla() || edit.isUpravaPredajcu())){
                    if(!Update.upravitInzerat(inzerat, DbPath))
                    JOptionPane.showMessageDialog(null, "Chyba pri upravovani inzeratu v DB", "Chyba", JOptionPane.ERROR_MESSAGE);
                }
                if(FilePath != null && (edit.isUpravaVozidla() || edit.isUpravaPredajcu())){
                    try {
                        _autobazar.saveToFile(new File(FilePath));
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e, "Chyba", JOptionPane.ERROR);
                    }
                }
            }
            ObnovitVsetkyTabulky();
            jButtonVymazatInzerat.setEnabled(false);
            jButtonUpravitInzerat.setEnabled(false);
            jButtonZobrazInzerat.setEnabled(false);
            jButtonPredane.setEnabled(false);
            jButtonAktivny.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonUpravitInzeratActionPerformed

    private void jButtonZobrazInzeratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZobrazInzeratActionPerformed
        int selectedRow = -1;
        selectedRow = jTableInzeraty.getSelectedRow();
        if(selectedRow != -1){
            DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
            String idInzeratu = m.getValueAt(selectedRow, 0).toString();
            Inzerat inzerat = _autobazar.najdiInzerat(Integer.parseInt(idInzeratu));
            if(inzerat != null){
                PrehladInzeratuDialog prehlad = new PrehladInzeratuDialog(this, false);
                prehlad.setLocationRelativeTo(null);
                prehlad.setInzerat(inzerat);
                prehlad.zobrazPrehlad();
                prehlad.setVisible(true);
            }
        }
    }//GEN-LAST:event_jButtonZobrazInzeratActionPerformed

    private void jButtonPredaneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPredaneActionPerformed
        int selectedRow = -1;
        selectedRow = jTableInzeraty.getSelectedRow();
        if(selectedRow != -1){
            try {
                DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
                String idIzeratu = m.getValueAt(selectedRow, 0).toString();
                if(!_autobazar.getPredane(Integer.parseInt(idIzeratu))){
                    _autobazar.setPredane(Integer.parseInt(idIzeratu));
                    if(DbPath != null){
                        if(!sql.Update.upravitStavInzeratu(idIzeratu, DbPath)){
                            JOptionPane.showMessageDialog(null, "Chyba pri upraveni v DB");
                        }
                    }
                    if(FilePath != null){
                    try {
                        _autobazar.saveToFile(new File(FilePath));
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e, "Chyba", JOptionPane.ERROR);
                    }
                }
                    FillTableInzeraty();
                    JOptionPane.showMessageDialog(null, "Inzerát "+idIzeratu+" bol označený ako predaný.","Upozornenie", JOptionPane.INFORMATION_MESSAGE);
                    jButtonPredane.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Inzerát už bol označený ako predaný!", "Upozornenie", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButtonPredaneActionPerformed

    private void jButtonNovyInzeratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovyInzeratActionPerformed
        VytvoritInzeratDialog vid = new VytvoritInzeratDialog(this, true);
        vid.setLocationRelativeTo(null);
        vid.setExistujuciPredajcovia(_autobazar.getZoznamPredajcov());
        vid.NaplnTableExistujucichPredajcov();
        vid.setVisible(true);
        Predajca predajca = null;
        Kategoria kategoria = null;
        if (vid.getPredajca() != null)
        predajca = vid.getPredajca();
        else return;
        if(vid.getKategoria() != null)
        kategoria = vid.getKategoria();
        else return;
        Inzerat novyInzerat = _autobazar.VytvoritInzerat(kategoria, predajca);
        if(DbPath != null) {
            if(sql.InsertIntoDB.insertInzerat(novyInzerat, DbPath)){
                JOptionPane.showMessageDialog(null, "Inzerat pridany do DB");
            }else{
                JOptionPane.showMessageDialog(null, "Chyba pri ukladani do DB", "Chyba", JOptionPane.ERROR);
            }
        }
        if(FilePath != null){
            try {
                _autobazar.saveToFile(new File(FilePath));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e, "Chyba", JOptionPane.ERROR);
            }
        }
        FillTableInzeraty();
        FillTableAutomobily();
        FillTableMotocykle();
        FillTableNakladne();
        FillTableAutobusy();
    }//GEN-LAST:event_jButtonNovyInzeratActionPerformed

    private void jTableInzeratyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableInzeratyMouseClicked
        if(SwingUtilities.isLeftMouseButton(evt)){
            jButtonZobrazInzerat.setEnabled(true);
            jButtonVymazatInzerat.setEnabled(true);
            jButtonUpravitInzerat.setEnabled(true);

            int selectedRow = jTableInzeraty.getSelectedRow();
            DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
            String stav = m.getValueAt(selectedRow, 6).toString();
            if(stav.equalsIgnoreCase("Predane")){
                jButtonPredane.setEnabled(false);
                jButtonAktivny.setEnabled(true);
            }else if(stav.equalsIgnoreCase("Aktivne")){
                jButtonPredane.setEnabled(true);
                jButtonAktivny.setEnabled(false);
            }
        }
        //JOptionPane.showMessageDialog(null, evt.getButton());
        if(evt.getClickCount() == 2 && evt.getButton() == 1){
            int selectedRow = jTableInzeraty.getSelectedRow();
            DefaultTableModel m = (DefaultTableModel)jTableInzeraty.getModel();
            String idInzeratu = m.getValueAt(selectedRow, 0).toString();
            Inzerat inzerat = _autobazar.najdiInzerat(Integer.parseInt(idInzeratu));
            if(inzerat != null){
                PrehladInzeratuDialog prehlad = new PrehladInzeratuDialog(this, false);
                prehlad.setLocationRelativeTo(null);
                prehlad.setInzerat(inzerat);
                prehlad.zobrazPrehlad();
                prehlad.setVisible(true);
            }
        }
        if(/*evt.getButton() == 3 &&*/ SwingUtilities.isRightMouseButton(evt)){
            final RowPopup pop = new RowPopup(jTableInzeraty, _autobazar);
            pop.show(evt.getComponent(), evt.getX(), evt.getY());
            FillTableInzeraty();
        }

    }//GEN-LAST:event_jTableInzeratyMouseClicked

    private void jButtonFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFilterActionPerformed
        FilterDialog fd = new FilterDialog(this, true);
        fd.setLocationRelativeTo(null);
        ArrayList<Inzerat> filtrovaneInzeraty = new ArrayList<>();
        filtrovaneInzeraty = _autobazar.getZoznamInzeratov();
        fd.setInzeraty(filtrovaneInzeraty);
        fd.NaplnUdaje();
        fd.setVisible(true);
        FillTableInzeraty(fd.getInzeraty());
        jButtonUpravitInzerat.setEnabled(false);
        jButtonVymazatInzerat.setEnabled(false);
        jButtonAktivny.setEnabled(false);
        jButtonPredane.setEnabled(false);
        jButtonZobrazInzerat.setEnabled(false);
        
    }//GEN-LAST:event_jButtonFilterActionPerformed

    private void jTableInzeratyPredajcuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableInzeratyPredajcuMouseClicked
        if(evt.getClickCount() == 2 && evt.getButton() == 1){
            int selectedRow = jTableInzeratyPredajcu.getSelectedRow();
            DefaultTableModel m = (DefaultTableModel)jTableInzeratyPredajcu.getModel();
            String idInzeratu = m.getValueAt(selectedRow, 0).toString();
            Inzerat inzerat = _autobazar.najdiInzerat(Integer.parseInt(idInzeratu));
            if(inzerat != null){
                PrehladInzeratuDialog prehlad = new PrehladInzeratuDialog(this, true);
                prehlad.setLocationRelativeTo(null);
                prehlad.setInzerat(inzerat);
                prehlad.zobrazPrehlad();
                prehlad.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTableInzeratyPredajcuMouseClicked

    /**
     * Akcia pre synchronizáiu dát v databáte v prípade, že boli načítané dáta pre aplikáciu zo súboru.
     * @param evt 
     */
    private void jButtonSynchronizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSynchronizeActionPerformed
        // TODO add your handling code here:
        SynchronizeDbJDialog sd = new SynchronizeDbJDialog(this, true);
        sd.setLocationRelativeTo(null);
        sd.setAutobazar(_autobazar);
        sd.setVisible(true);
    }//GEN-LAST:event_jButtonSynchronizeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Aplikacia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplikacia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplikacia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplikacia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Aplikacia aplikacia = new Aplikacia();
                aplikacia.setIconImage(Toolkit.getDefaultToolkit().getImage("./icons/car-compact.png"));
                aplikacia.setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAktivny;
    private javax.swing.JButton jButtonFilter;
    private javax.swing.JButton jButtonNacitajPredajcov;
    private javax.swing.JButton jButtonNovyInzerat;
    private javax.swing.JButton jButtonPredane;
    private javax.swing.JButton jButtonSaveToFile;
    private javax.swing.JButton jButtonSynchronize;
    private javax.swing.JButton jButtonUpravitInzerat;
    private javax.swing.JButton jButtonUpravitPredajcu;
    private javax.swing.JButton jButtonVymazatInzerat;
    private javax.swing.JButton jButtonVymazatPredajcu;
    private javax.swing.JButton jButtonZobrazInzerat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelHodnota;
    private javax.swing.JPanel jPanelAutobusy;
    private javax.swing.JPanel jPanelMotocykle;
    private javax.swing.JPanel jPanelNakladne;
    private javax.swing.JPanel jPanelPredajcovia;
    private javax.swing.JPanel jPanelPrehlad;
    private javax.swing.JPanel jPanel_Automobily;
    private javax.swing.JRadioButton jRadioButtonAktivne;
    private javax.swing.JRadioButton jRadioButtonNeaktivne;
    private javax.swing.JRadioButton jRadioButtonVsetky;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAutobusy;
    private javax.swing.JTable jTableAutomobily;
    private javax.swing.JTable jTableInzeraty;
    private javax.swing.JTable jTableInzeratyPredajcu;
    private javax.swing.JTable jTableMotocykle;
    private javax.swing.JTable jTableNakladne;
    private javax.swing.JTable jTablePredajcovia;
    private javax.swing.JTextField jTextFieldFastSearch;
    // End of variables declaration//GEN-END:variables
}
