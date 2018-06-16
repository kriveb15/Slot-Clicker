package design;

import design.ClickerFrame;
import static design.ClickerFrame.btShop;
import static design.ClickerFrame.vermoegen;
import static design.Shop.lbAuto;
import static design.Shop.lbSuper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Options extends JFrame {

    public static boolean variante1 = TRUE;
    public static boolean variante2 = FALSE;
    public static boolean variante3 = FALSE;
    private JButton btExit, btSelect;
    private JLabel lbLanguages;
    private JRadioButton rbItaly, rbEnglish, rbGerman;
    private Font inscription = new Font("Arial", Font.BOLD, 11);
    private JPanel plNavigator, plLanugages;
    //private ClickerFrame clickerFrame = new ClickerFrame("");

    public Options() {
        this.setSize(new Dimension(300, 150));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initComponents();
    }

    private void initComponents() {
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
     //   c.setLayout(new GridLayout(2, 1));
        try {
            c.add(initPanelNavigator(), BorderLayout.NORTH);
            c.add(initLanguages(), BorderLayout.SOUTH);
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pack();
    }

    private JPanel initPanelNavigator() {
        plNavigator = new JPanel();
        plNavigator.setLayout(new BorderLayout());
        
        btExit = new JButton();
        if(variante1 == TRUE)
        {
            btExit.setText("Schließen");
        }
        else if(variante2 == TRUE)
        {
            btExit.setText("Exit");
        }
        
        else if(variante3 == TRUE)
        {
            btExit.setText("Chiudere");
        }
        
        btExit.setFont(inscription);
        btExit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btExit.setBackground(new Color(123, 171, 247));
        btExit.setOpaque(true);
        btExit.setFocusable(false);
        btExit.setPreferredSize(new Dimension(120,40));
        btExit.addActionListener(e -> onClose(e) );
        plNavigator.add(btExit, BorderLayout.WEST);

        btSelect = new JButton();
        if(variante1 == TRUE)
        {
            btSelect.setText("Auswählen");
        }
        else if(variante2 == TRUE)
        {
            btSelect.setText("Select");
        }
        
        else if(variante3 == TRUE)
        {
            btSelect.setText("Scegliere");
        }
        btSelect.setFont(inscription);
        btSelect.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btSelect.setOpaque(true);
        btSelect.setBackground(new Color(123, 171, 247));
        btSelect.setFocusable(false);
        btSelect.setPreferredSize(new Dimension(120,40));
        btSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLanguage(rbGerman.isSelected(), rbEnglish.isSelected(), rbItaly.isSelected());
                setVisible(true);
            }
        });
        plNavigator.add(btSelect, BorderLayout.EAST);
        return plNavigator;
    }

    private JPanel initLanguages() throws IOException {
        plLanugages = new JPanel();
        plLanugages.setLayout(new GridLayout(4, 2));
        lbLanguages = new JLabel();
        if(variante1 == TRUE)
        {
             lbLanguages.setText("Sprache auswählen: ");
        }
        
        else if(variante2 == TRUE)
        {
            lbLanguages.setText("Choose Language: ");
        }
        
        else if(variante3 == TRUE)
        {
            lbLanguages.setText("Lingua scegliere: ");
        }
       
        JLabel lbPlatzhalter = new JLabel("");

        rbGerman = new JRadioButton("Deutsch");
        rbGerman.setSelected(true);

        JLabel lbGermanIcon = new JLabel();
        File file = new File("src/res/Options/deutschland.png");
        BufferedImage image = ImageIO.read(file);
        ImageIcon activeImage = new ImageIcon(image);
        Image regImage = activeImage.getImage();
        Image scaledImage = regImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon activeImage2 = new ImageIcon(scaledImage);
        lbGermanIcon = new JLabel(activeImage2);

        rbItaly = new JRadioButton("Italienisch");
        rbItaly.setSelected(false);

        JLabel lbItalyIcon = new JLabel();
        File fileItaly = new File("src/res/Options/italien.png");
        BufferedImage imageItaly = ImageIO.read(fileItaly);
        ImageIcon activeImageItaly = new ImageIcon(imageItaly);
        Image regImageItaly = activeImageItaly.getImage();
        Image scaledImageItaly = regImageItaly.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon activeImageItaly2 = new ImageIcon(scaledImageItaly);
        lbItalyIcon = new JLabel(activeImageItaly2);

        rbEnglish = new JRadioButton("Englisch");
        rbEnglish.setSelected(false);

        JLabel lbEnglishIcon = new JLabel();
        File fileEnglish = new File("src/res/Options/amerika.png");
        BufferedImage imageEnglish = ImageIO.read(fileEnglish);
        ImageIcon activeImageEnglish = new ImageIcon(imageEnglish);
        Image regImageEnglish = activeImageEnglish.getImage();
        Image scaledImageEnglish = regImageEnglish.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon activeImageEnglish2 = new ImageIcon(scaledImageEnglish);
        lbEnglishIcon = new JLabel(activeImageEnglish2);

        //only one can be selected
        ButtonGroup group = new ButtonGroup();
        group.add(rbEnglish);
        group.add(rbGerman);
        group.add(rbItaly);

        plLanugages.add(lbLanguages);
        plLanugages.add(lbPlatzhalter);
        plLanugages.add(rbGerman);
        plLanugages.add(lbGermanIcon);
        plLanugages.add(rbItaly);
        plLanugages.add(lbItalyIcon);
        plLanugages.add(rbEnglish);
        plLanugages.add(lbEnglishIcon);

        return plLanugages;
    }

    private void changeLanguage(boolean german, boolean english, boolean italy) {
        if (german == true) {
            System.out.println("german");
            variante2 = FALSE;
            variante3 = FALSE;
            variante1 = Boolean.TRUE;
            lbLanguages.setText("Sprache auswählen: ");
            btSelect.setText("Auswählen");
            btExit.setText("Schließen");
            ClickerFrame.btOptions.setText("Optionen");
            ClickerFrame.lbVermoegen.setText("Vermögen : " + vermoegen + " Credits");
            ClickerFrame.btShop.setText("Geschäft");
        } else if (english == true) {
            variante1 = FALSE;
            variante3 = FALSE;
            System.out.println("englisch");
           variante2 = Boolean.TRUE;
           lbLanguages.setText("Choose Language: ");
           btSelect.setText("Select");
           btExit.setText("Exit");
            ClickerFrame.btOptions.setText("Options");
            ClickerFrame.lbVermoegen.setText("Balance : " + vermoegen + " Credits");
            ClickerFrame.btShop.setText("Shop");
        } else if (italy == true) {
            variante1 = FALSE;
             variante2 = FALSE;
            System.out.println("italy");
            variante3 = Boolean.TRUE;
            lbLanguages.setText("Lingua scegliere: ");
            btSelect.setText("Scegliere");
            btExit.setText("Chiudere");
            ClickerFrame.btOptions.setText("Opzioni");
            ClickerFrame.lbVermoegen.setText("Patrimonio : " + vermoegen + " Credits");
            ClickerFrame.btShop.setText("Negozio");
        }
    }

    private void onClose(ActionEvent e) 
    {
        this.setVisible(false);
    }
}
