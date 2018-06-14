package design;

import static design.ClickerFrame.saveVermoegen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import static java.lang.Boolean.TRUE;
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
    public static int price1, autoclick;
    public static int price2, superclick;
    public static int price3, vermoegen, offlineproduction;
    private File speicherort;
    private JButton btAutoClick;
    private JButton btSuperClick;
    private JButton btofflineProduction;
    private JButton btBacktoClicker;
    private JButton btToSlot;
    private JPanel plLabels;
    private JPanel plNavigator;
    public static JLabel lbAuto = new JLabel();
    public static JLabel lbOffline = new JLabel();
    public static JLabel lbSuper = new JLabel();
    private JLabel lbCredits;
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
        plNavigator = new JPanel();
        btBacktoClicker = new JButton();
        btToSlot = new JButton();
        lbCredits = new JLabel();
        plNavigator.setLayout(new GridLayout(1, 3));
        plNavigator.setPreferredSize(new Dimension(0, 30));

        lbCredits.setText(" " + vermoegen + " Credits");
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
            SlotFrame slotframe = new SlotFrame("Slot Machine", speicherort, autoclick, superclick, offlineproduction, vermoegen);
        });

        plNavigator.add(btBacktoClicker);
        plNavigator.add(btToSlot);
        plNavigator.add(lbCredits);
        return plNavigator;
    }

    private JPanel initplLabels() {
        //Initialisierungen
        plLabels = new JPanel(new BorderLayout());
        lbAuto = new JLabel();
        btAutoClick = new JButton();
        lbSuper = new JLabel();
        btSuperClick = new JButton();
        lbOffline = new JLabel();
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
            if (Options.variante1 == TRUE) {
                lbAuto.setText("Kaufe Autoclick");
            } else if (Options.variante2 == TRUE) {
                lbAuto.setText("Buy Autoclick");
            } else if (Options.variante3 == TRUE) {
                lbAuto.setText("Comprare Autoclick");
            }

        } else {
            if (Options.variante1 == TRUE) {
                lbAuto.setText("Kaufe Autoclick (Level: " + autoclick + ")");
            } else if (Options.variante2 == TRUE) {
                lbAuto.setText("Buy Autoclick (Level: " + autoclick + ")");
            } else if (Options.variante3 == TRUE) {
                lbAuto.setText("Comprare Autoclick (Level: " + autoclick + ")");
            }

        }
        if (superclick == 0) {
            if (Options.variante1 == TRUE) {
                lbSuper.setText("Aktiviere Superclick");
            } else if (Options.variante2 == TRUE) {
                lbSuper.setText("Activate Superclick");
            } else if (Options.variante3 == TRUE) {
                lbSuper.setText("Accendere Autoclick");
            }

        } else {
            if (Options.variante1 == TRUE) {
                lbSuper.setText("Aktiviere Superlick (Aktivierungen: " + superclick + ")");
            } else if (Options.variante2 == TRUE) {
                lbSuper.setText("Activate Superlick (Aktivierungen: " + superclick + ")");
            } else if (Options.variante3 == TRUE) {
                lbSuper.setText("Accendere Superlick (Aktivierungen: " + superclick + ")");
            }

        }
        if (offlineproduction == 0) {
            if (Options.variante1 == TRUE) {
                lbOffline.setText("Kaufe Offlineproduction");
            } else if (Options.variante2 == TRUE) {
                lbOffline.setText("Buy Offlineproduction");
            } else if (Options.variante3 == TRUE) {
                lbOffline.setText("Comprare Offlineproduction");
            }

        } else {
            if (Options.variante1 == TRUE) {
                lbOffline.setText("Kaufe Offlineproduction (Level: " + autoclick + ")");
            } else if (Options.variante2 == TRUE) {
                lbOffline.setText("Buy Offlineproduction (Level: " + autoclick + ")");
            } else if (Options.variante3 == TRUE) {
                lbOffline.setText("Comprare Offlineproduction (Level: " + autoclick + ")");
            }

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
        if (vermoegen >= price1) {
            autoclick++;
            vermoegen = vermoegen - price1;
            price1 = (int) (500 + Math.pow(2, autoclick));
            btAutoClick.setText(price1 + "");
            lbAuto.setText("Buy Autoclick (Level: " + autoclick + ")");
            refreshCredits();
        }

    }

    private void onSuperClick(java.awt.event.ActionEvent evt) {
        if (vermoegen >= price2) {
            superclick++;
            price2 = (int) (1000 + Math.pow(3, superclick));
            btSuperClick.setText("" + price2);
            vermoegen = vermoegen - price2;
            lbSuper.setText("Activate Superlick (Activations: " + superclick + ")");
            refreshCredits();
        }
    }

    private void onOfflinePro(java.awt.event.ActionEvent evt) {
        if (vermoegen >= price3) {
            offlineproduction++;
            vermoegen = vermoegen - price3;
            price3 = (int) (100 + 110 * offlineproduction);
            btofflineProduction.setText("" + price3);
            lbOffline.setText("Buy Offlineproduction (Level: " + offlineproduction + ")");
            refreshCredits();
        }
    }

    private void refreshCredits() {
        lbCredits.setText(" " + vermoegen + " Credits");
        ClickerFrame.saveVermoegen(speicherort, vermoegen, autoclick, superclick, offlineproduction);
    }

    public int getVermoegen() {
        return vermoegen;
    }

    private void close() {
        dispose();
    }

}
