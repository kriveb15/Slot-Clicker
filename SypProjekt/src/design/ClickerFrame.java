package design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ClickerFrame extends JFrame
{
    
    private JLabel lbVermoegen, imageHolder;
    private JPanel plNavigator;
    private JButton btShop, btSlotMachine;
    private Container c;
    private static int vermoegen;
    private static File f;
    private Font inscription = new Font("Arial", Font.BOLD, 11);
    
    public ClickerFrame(String title)
    {
        super(title);
        f = new File("src/res/save_file_vermoegen.txt");
        try 
        {
            loadVermoegen(f);
        } 
        catch (IOException ex) 
        {
            System.out.println("unreadable");
        }
        initComponents();       
    }
    
    private void initComponents()
    {
        c = this.getContentPane();
        try 
        {
            initImage();
            initNavigator();
        } 
        catch(IOException ex) 
        {
            System.out.println("x");
        }
        initFrame();
        this.add(plNavigator, BorderLayout.NORTH);
        this.add(imageHolder, BorderLayout.SOUTH);
    }
   
    private void initNavigator()
    {
        plNavigator = new JPanel(new BorderLayout());
        plNavigator.setPreferredSize(new Dimension(0, 30));
        
        lbVermoegen = new JLabel("Vermögen: " + vermoegen + " Credits", SwingConstants.CENTER);
        lbVermoegen.setBackground(new Color(123, 171, 247));
        lbVermoegen.setOpaque(true);
        lbVermoegen.setFont(inscription);
        lbVermoegen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        btSlotMachine = new JButton("Slot Machine");
        btSlotMachine.setBackground(new Color(123, 171, 247));
        btSlotMachine.setOpaque(true);
        btSlotMachine.setFont(inscription);
        btSlotMachine.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btSlotMachine.setFocusable(false);
        
        btSlotMachine.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
               dispose();
               saveVermoegen(f);
               SlotFrame slotframe = new SlotFrame("Slot Machine", vermoegen);
               //slotframe.setVermoegen(vermoegen);
            }
        });
        
        btShop = new JButton("Shop");
        btShop.setFont(inscription);
        btShop.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btShop.setBackground(new Color(123, 171, 247));
        btShop.setOpaque(true);
        btShop.setFocusable(false);
        
        plNavigator.add(lbVermoegen, BorderLayout.EAST);
        plNavigator.add(btSlotMachine, BorderLayout.WEST);
        plNavigator.add(btShop, BorderLayout.CENTER);
    }
    
    private void initImage() throws IOException
    {
        File file = new File("src/res/Clicker/slot7_reworked.png");
        BufferedImage image = ImageIO.read(file);        
        ImageIcon activeImage = new ImageIcon(image);      
        Image regImage = activeImage.getImage();
        Image scaledImage = regImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon activeImage2 = new ImageIcon(scaledImage);
        
        imageHolder = new JLabel(activeImage);

        Graphics2D g2d = image.createGraphics();
        
        imageHolder.addMouseListener(new MouseAdapter() 
        {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    System.out.println("clicked");
                    vermoegen++;
                    lbVermoegen.setText("Vermögen: "+vermoegen+" Credits");
                    imageHolder.setIcon(activeImage);                       
                }

            @Override
            public void mousePressed(MouseEvent e) 
            {               
                imageHolder.setIcon(activeImage2);
            }
                
        });               
    }
    
    public void loadVermoegen(File f) throws IOException
    {
        System.out.println("Versuche Speicherfile zu erreichen...");
        try 
        {
            System.out.println("Speicherfile erreicht!");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            vermoegen = Integer.parseInt(line);
            
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("Speicherfile nicht erreicht!");
        }
        
    }
    
    public static void saveVermoegen(File f) 
    {
       try
       {
          PrintWriter pw = new PrintWriter(f);
          pw.write(""+vermoegen);
          pw.close();
       }
       catch(FileNotFoundException ex)
       {
           System.out.println("Kein File zum Speichern gefunden");
       }
       
    }
    
    private void initFrame()
    {
        this.setLayout(new BorderLayout());
        this.setSize(350, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        ClickerFrame clicker = new ClickerFrame("Slot Clicker");
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
               saveVermoegen(f);
            }
        }));
    }

    public JLabel getLbVermoegen() 
    {
        return lbVermoegen;
    }

    public void setLbVermoegen(JLabel lbVermoegen) 
    {
        this.lbVermoegen = lbVermoegen;
    }

    public JButton getBtShop() 
    {
        return btShop;
    }

    public void setBtShop(JButton btShop) 
    {
        this.btShop = btShop;
    }

    public JButton getBtSlotMachine() 
    {
        return btSlotMachine;
    }

    public void setBtSlotMachine(JButton btSlotMachine) 
    {
        this.btSlotMachine = btSlotMachine;
    }

    public static int getVermoegen() 
    {
        return vermoegen;
    }

    public static void setVermoegen(int vermoegen) 
    {
        ClickerFrame.vermoegen = vermoegen;
    }

    public Font getInscription() 
    {
        return inscription;
    }

    public void setInscription(Font inscription) 
    {
        this.inscription = inscription;
    }
    
}