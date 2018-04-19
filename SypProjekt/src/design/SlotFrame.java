
package design;

import static design.ClickerFrame.saveVermoegen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SlotFrame extends JFrame
{
    private Container c;
    private JPanel plNavigator, plSlotMachine, controlpanel, plInnerSlot;
    
    private JLabel lbVermoegen, lbSeven, lbBell, lbErdbeere, lbKirsche, lbMelone, lbPfirsich, lbPflaume, lbTraube, lbZitrone;
    private JLabel[][] fields = new JLabel[3][3];
    
    private JButton btClicker, btShop;
    private JButton btStartRound;
    private BufferedImage[] slotImages = new BufferedImage[9];
    
    private JLabel template_shootingStar;
    private int vermoegen = 0;
    
    private int initCounter = 0;
                               
    public SlotFrame(String title, int vermoegen)
    {
        super(title);
        this.vermoegen = vermoegen;
        initComponents();
    }
    
    private void initComponents()
    {
        try
        {
            initImages();
            initNavigator();
            initControl();
        }
        catch(IOException ex)
        {
            System.out.println("image unloadable"); 
        }
        
        initFrame();
        this.add(plSlotMachine, BorderLayout.CENTER);
        this.add(plNavigator, BorderLayout.NORTH);
        this.add(controlpanel, BorderLayout.SOUTH);    
    }

    private void initImages() throws FileNotFoundException, IOException
    {
      plSlotMachine = new JPanel(new BorderLayout());  
      plInnerSlot = new JPanel(new GridLayout(3, 3));
      
      lbSeven = new JLabel("", SwingConstants.CENTER);
      lbBell = new JLabel("", SwingConstants.CENTER);
      lbErdbeere = new JLabel("", SwingConstants.CENTER);
      lbKirsche = new JLabel("", SwingConstants.CENTER);
      lbMelone = new JLabel("", SwingConstants.CENTER);
      lbPfirsich = new JLabel("", SwingConstants.CENTER);
      lbPflaume = new JLabel("", SwingConstants.CENTER);
      lbTraube = new JLabel("", SwingConstants.CENTER);
      lbZitrone = new JLabel("", SwingConstants.CENTER);
      
      fields[0][0] = lbSeven;
      fields[0][1] = lbBell;
      fields[0][2] = lbErdbeere;
      fields[1][0] = lbKirsche;
      fields[1][1] = lbMelone;
      fields[1][2] = lbPfirsich;
      fields[2][0] = lbPflaume;
      fields[2][1] = lbTraube;
      fields[2][2] = lbZitrone;
      
      slotImages[0] = ImageIO.read(new File("src/res/Slot Machine/7.png"));
      slotImages[1] = ImageIO.read(new File("src/res/Slot Machine/Bell.png"));
      slotImages[2] = ImageIO.read(new File("src/res/Slot Machine/Erdbeere.png"));
      slotImages[3] = ImageIO.read(new File("src/res/Slot Machine/Kirsche.png"));
      slotImages[4] = ImageIO.read(new File("src/res/Slot Machine/Melone.png"));
      slotImages[5] = ImageIO.read(new File("src/res/Slot Machine/Pfirsich.png"));
      slotImages[6] = ImageIO.read(new File("src/res/Slot Machine/Pflaume.png"));
      slotImages[7] = ImageIO.read(new File("src/res/Slot Machine/Traube.png"));
      slotImages[8] = ImageIO.read(new File("src/res/Slot Machine/Zitrone.png"));
              
      for(int i = 0; i < 3; i++)
      {
          for(int j = 0; j < 3; j++)
          {
             fields[i][j].setIcon(new ImageIcon(slotImages[initCounter]));
             fields[i][j].setOpaque(true);
             fields[i][j].setBackground(Color.WHITE);
             fields[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
             plInnerSlot.add(fields[i][j]);
             initCounter++;
          }
      }
      
        template_shootingStar = new JLabel("", SwingConstants.CENTER);
        try 
        {
            BufferedImage shootingstar = ImageIO.read(new File("src/res/Slot Machine/background_shootingstars.png"));
            template_shootingStar.setIcon(new ImageIcon(shootingstar));
            template_shootingStar.setOpaque(true);
            template_shootingStar.setBackground(Color.BLACK);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(SlotFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      plSlotMachine.add(template_shootingStar, BorderLayout.NORTH);
      plSlotMachine.add(plInnerSlot, BorderLayout.CENTER);
    }
    
    private void initNavigator()
    {
        plNavigator = new JPanel(new GridLayout());
        plNavigator.setPreferredSize(new Dimension(0, 30));
        
        lbVermoegen = new JLabel("Vermögen: " + vermoegen + " Credits", SwingConstants.CENTER);
        lbVermoegen.setBackground(new Color(123, 171, 247));
        lbVermoegen.setOpaque(true);
        lbVermoegen.setFont(new Font("Arial", Font.BOLD, 11));
        lbVermoegen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               
        btClicker = new JButton("Clicker");
        btClicker.setBackground(new Color(123, 171, 247));
        btClicker.setOpaque(true);
        btClicker.setFont(new Font("Arial", Font.BOLD, 11));
        btClicker.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btClicker.setFocusable(false);
        
        btClicker.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
               dispose();
               new ClickerFrame("Slot Machine").setVisible(true);
            }
        });
        
        btShop = new JButton("Shop");
        btShop.setBackground(new Color(123, 171, 247));
        btShop.setOpaque(true);
        btShop.setFont(new Font("Arial", Font.BOLD, 11));
        btShop.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btShop.setFocusable(false);
          
        plNavigator.add(btClicker);
        plNavigator.add(btShop);
        plNavigator.add(lbVermoegen);
    }
    
    public void initControl()
    {
       controlpanel = new JPanel(new GridLayout());
       controlpanel.setPreferredSize(new Dimension(0, 70));
       
       btStartRound = new JButton("Start");
       btStartRound.addActionListener(new ActionListener() 
       {
           @Override
           public void actionPerformed(ActionEvent e) 
           {
               
           }
       });
    }
    private void initFrame()
    {
        this.setLayout(new BorderLayout());
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
    }

    public void setVermoegen(int vermoegen) 
    {
        this.vermoegen = vermoegen;
    } 
}
