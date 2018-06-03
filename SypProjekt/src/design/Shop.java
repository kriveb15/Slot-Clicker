package design;

import static design.ClickerFrame.saveVermoegen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author flori
 */
public class Shop extends JFrame {
    //Variablen
    private int     price1, autoclick;
    private int     price2, superclick;
    private int     price3,vermoegen, offlineproduction;
    private File    speicherort;
    private JButton btAutoClick;
    private JButton btSuperClick;
    private JButton btofflineProduction;
    private JButton btBacktoClicker;
    private JButton btToSlot;
    private JPanel  plLabels;
    private JPanel  plNavigator;
    private JLabel  lbAuto;
    private JLabel  lbOffline;
    private JLabel  lbSuper;
    private JLabel  lbCredits;
    private Font inscription = new Font("Arial", Font.BOLD, 11);


    

    /*Konstruktor, bekommt von ClickerFrame Vermögen plus die 
     *Levels der verschiedenen Kaufoptionen
     */
    public Shop(File f, int vermoegen, int autoclick, int superclick, int offlineproduction) {
        this.vermoegen = vermoegen;
        this.autoclick = autoclick;
        this.superclick = superclick;
        this.speicherort = f;
        this.offlineproduction = offlineproduction;
        initComponents();
        
        
    }
    //Grundlegende JFrame Einstellungen
    private void initComponents() {       
        //Schließoption und Cursor
        this.setLayout(new BorderLayout());
        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
        //Die Panels zum Frame hinzufügen
        getContentPane().add(initplNavigator(), BorderLayout.NORTH);
        getContentPane().add(initplLabels(), BorderLayout.CENTER);
               
    }                        


    private JPanel initplNavigator() {
        //Initialisierungen
        plNavigator      = new JPanel();
        btBacktoClicker  = new JButton();
        btToSlot         = new JButton();
        lbCredits        = new JLabel();
        plNavigator.setLayout(new GridLayout(1,3));
        plNavigator.setPreferredSize(new Dimension(0, 30));
        
        
        lbCredits.setText(" "+vermoegen+" Credits");        
        lbCredits.setBackground(new Color(123, 171, 247));
        lbCredits.setOpaque(true);
        lbCredits.setFont(inscription);
        lbCredits.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        btBacktoClicker.setText("Clicker");
        btBacktoClicker.setBackground(new Color(123, 171, 247));
        btBacktoClicker.setOpaque(true);
        btBacktoClicker.setFont(inscription);
        btBacktoClicker.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btBacktoClicker.setFocusable(false);
        
        btToSlot.setText("Slot-Machine");
        btToSlot.setBackground(new Color(123, 171, 247));
        btToSlot.setOpaque(true);
        btToSlot.setFont(inscription);
        btToSlot.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btToSlot.setFocusable(false);
        
        btBacktoClicker.addActionListener((ActionEvent e) -> {
            dispose();
            new ClickerFrame("Slot Clicker").setVisible(true);
        });
        btToSlot.addActionListener((ActionEvent e) -> {
            dispose();
            ClickerFrame.saveVermoegen(speicherort, vermoegen, autoclick, superclick, offlineproduction);
            SlotFrame slotframe = new SlotFrame("Slot Machine",speicherort, autoclick, superclick, offlineproduction, vermoegen);
        });
        
        plNavigator.add(btBacktoClicker);
        plNavigator.add(btToSlot);
        plNavigator.add(lbCredits);
        return plNavigator;
    }

    private JPanel initplLabels() {
        //Initialisierungen
        plLabels            = new JPanel(new BorderLayout());
        lbAuto              = new JLabel();
        btAutoClick         = new JButton();
        lbSuper             = new JLabel();
        btSuperClick        = new JButton();
        lbOffline           = new JLabel();
        btofflineProduction = new JButton();
        plLabels.setLayout(new GridLayout(3, 2));
        
        //Design Änderungen Buttons
        
        lbAuto.setOpaque(true);
        lbAuto.setBackground(Color.BLACK);
        lbAuto.setForeground(Color.RED);
        lbAuto.setBorder(BorderFactory.createLineBorder(new Color(123, 171, 247)));
        
        lbSuper.setOpaque(true);
        lbSuper.setBackground(Color.BLACK);
        lbSuper.setForeground(Color.RED);
        lbSuper.setBorder(BorderFactory.createLineBorder(new Color(123, 171, 247)));
        
        lbOffline.setOpaque(true);
        lbOffline.setBackground(Color.BLACK);
        lbOffline.setForeground(Color.RED);
        lbOffline.setBorder(BorderFactory.createLineBorder(new Color(123, 171, 247)));
        
        btAutoClick.setOpaque(true);
        btAutoClick.setBackground(new Color(123, 171, 247));
        btAutoClick.setFont(inscription);
        btAutoClick.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btAutoClick.setFocusable(false);
        
        btSuperClick.setBackground(new Color(123, 171, 247));
        btSuperClick.setOpaque(true);
        btSuperClick.setFont(inscription);
        btSuperClick.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btSuperClick.setFocusable(false);
        
        btofflineProduction.setBackground(new Color(123, 171, 247));
        btofflineProduction.setOpaque(true);
        btofflineProduction.setFont(inscription);
        btofflineProduction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btofflineProduction.setFocusable(false);
        
        
        //Action Listener auf den Buttons
        btAutoClick.addActionListener((ActionEvent evt) -> {
            onAutoClick(evt);
          
        });
        btSuperClick.addActionListener((java.awt.event.ActionEvent evt) -> {
            onSuperClick(evt);
        });
        btofflineProduction.addActionListener((java.awt.event.ActionEvent evt) -> {
            onOfflinePro(evt);
        });
        
        //Wertzuweisungen bei öffnen
        if (autoclick == 0) {
            lbAuto.setText("Buy Autoclick");
        } else {
            lbAuto.setText("Buy Autoclick (Level: " + autoclick + ")");
        }
        if (superclick == 0) {
            lbSuper.setText("Activate Superclick");
        } else {
            lbSuper.setText("Activate Superlick (Activations: " + superclick + ")");
        }
        if (offlineproduction == 0) {
            lbOffline.setText("Buy Offlineproduction");
        } else {
            lbOffline.setText("Buy Offlineproduction (Level: " + autoclick + ")");
        }
        price1 = (int) (500 + Math.pow(2, autoclick));
        btAutoClick.setText(price1 + "");
        price2 = (int) (1000 + Math.pow(3, superclick));
        btSuperClick.setText("" + price2);
        price3 = (int) (100 + 110 * offlineproduction);
        btofflineProduction.setText("" + price3);
        
        //Adden aller Komponenten zum Panel
        plLabels.add(lbAuto);
        plLabels.add(btAutoClick);
        plLabels.add(lbSuper);
        plLabels.add(btSuperClick);
        plLabels.add(lbOffline);
        plLabels.add(btofflineProduction);
        
        //Return des JPanels zum hinzufügen zum JFrame
        return plLabels;
    }
    
    private void onAutoClick(java.awt.event.ActionEvent evt) {
        if(vermoegen >= price1)
        {
            autoclick++;
            price1 = (int) (500 + Math.pow(2, autoclick));
            vermoegen = vermoegen-price1;
            btAutoClick.setText(price1 + "");
            lbAuto.setText("Buy Autoclick (Level: " + autoclick + ")");
            refreshCredits();
        }

    }

    private void onSuperClick(java.awt.event.ActionEvent evt) {
        if(vermoegen >= price2)
        {
            superclick++;
            price2 = (int) (1000 + Math.pow(3, superclick));
            btSuperClick.setText("" + price2);
            vermoegen = vermoegen-price2;
            lbSuper.setText("Activate Superlick (Activations: " + superclick + ")");
            refreshCredits();
        }
    }
    
    private void onOfflinePro(java.awt.event.ActionEvent evt) {
        if(vermoegen >= price3)
        {
            offlineproduction++;
            price3 = (int) (100 + 110 * offlineproduction);
            btofflineProduction.setText("" + price3);
            vermoegen = vermoegen-price3;
            lbOffline.setText("Buy Offlineproduction (Level: " + autoclick + ")");
            refreshCredits();
        }
    }
    private void refreshCredits()
    {
        lbCredits.setText(" "+vermoegen+" Credits");
        ClickerFrame.saveVermoegen(speicherort, vermoegen, autoclick, superclick, offlineproduction);
    }

    public int getVermoegen() {
        return vermoegen;
    }

    private void close() {
        dispose();
    }
    
    
}
