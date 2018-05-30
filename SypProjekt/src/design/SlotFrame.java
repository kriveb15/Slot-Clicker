package design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SlotFrame extends JFrame
{

    private Container c;
    private JPanel plNavigator, plSlotMachine, controlpanel, plStartStopCollect, plInnerSlot, plSettings;
    private JPanel plBackground, plSettingsContainer;
    private JLabel lbVermoegen, lbSeven, lbBell, lbErdbeere, lbKirsche, lbMelone, lbPfirsich, lbPflaume, lbTraube, lbZitrone;
    private JLabel lbRoundResult;
    private JTextField tfWager;
    private JLabel[][] fields = new JLabel[3][3];

    private JButton btClicker, btShop;
    private JButton btStartRound, btCollect, btPlus, btMinus;
    private BufferedImage[] slotImages = new BufferedImage[9];

    private JLabel template_shootingStar;
    private int vermoegen = 0;

    private int initCounter = 0;

    private boolean started = false;

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
            initSlot();
            initNavigator();
            initControl();
        } catch (IOException ex)
        {
            System.out.println("image unloadable");
        }

        initFrame();
        this.add(plBackground, BorderLayout.CENTER);
        this.add(plNavigator, BorderLayout.NORTH);
        this.add(controlpanel, BorderLayout.SOUTH);
    }

    private void initSlot() throws FileNotFoundException, IOException
    {
        plBackground = new JPanel(new GridBagLayout());
        plBackground.setPreferredSize(new Dimension(600, 570));
        plSlotMachine = new JPanel();
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

        slotImages[0] = ImageIO.read(new File("src/res/Slot Machine/7_.png"));
        slotImages[1] = ImageIO.read(new File("src/res/Slot Machine/Bell_.png"));
        slotImages[2] = ImageIO.read(new File("src/res/Slot Machine/Erdbeere_.png"));
        slotImages[3] = ImageIO.read(new File("src/res/Slot Machine/Kirsche_.png"));
        slotImages[4] = ImageIO.read(new File("src/res/Slot Machine/Melone.png"));
        slotImages[5] = ImageIO.read(new File("src/res/Slot Machine/Pfirisich_.png"));
        slotImages[6] = ImageIO.read(new File("src/res/Slot Machine/Pflaume_.png"));
        slotImages[7] = ImageIO.read(new File("src/res/Slot Machine/Traube_.png"));
        slotImages[8] = ImageIO.read(new File("src/res/Slot Machine/Zitrone_.png"));

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                fields[i][j].setIcon(new ImageIcon(slotImages[initCounter]));
                fields[i][j].setOpaque(true);
                fields[i][j].setBackground(Color.WHITE);
                fields[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                plInnerSlot.add(fields[i][j]);
                initCounter++;
            }
        }

        plInnerSlot.setOpaque(true);
        plInnerSlot.setPreferredSize(new Dimension(420, 300));
        plBackground.add(plInnerSlot);
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

        lbRoundResult = new JLabel();
        plNavigator.add(btClicker);
        plNavigator.add(btShop);
        plNavigator.add(lbVermoegen);
    }

    public void initControl()
    {
        controlpanel = new JPanel(new BorderLayout());
        controlpanel.setPreferredSize(new Dimension(0, 100));
        plStartStopCollect = new JPanel(new BorderLayout());
        plStartStopCollect.setPreferredSize(new Dimension(150, 0));
        plSettings = new JPanel(new GridLayout());
        plSettings.setPreferredSize(new Dimension(450, 70));
        plSettingsContainer = new JPanel(new BorderLayout());
        plSettingsContainer.setPreferredSize(new Dimension(450, 0));

        btStartRound = new JButton("Start");
        btStartRound.setBackground(new Color(123, 171, 247));
        btStartRound.setOpaque(true);
        btStartRound.setFont(new Font("Arial", Font.BOLD, 11));
        btStartRound.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btStartRound.setFocusable(false);
        btStartRound.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!started)
                {
                    started = true;
                    btStartRound.setText("Stop");
                    startSlotProcess();
                } else
                {
                    started = false;
                    btStartRound.setText("Start");
                }
            }
        });

        btCollect = new JButton("Collect");
        btCollect.setBackground(new Color(123, 171, 247));
        btCollect.setOpaque(true);
        btCollect.setFont(new Font("Arial", Font.BOLD, 11));
        btCollect.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btCollect.setFocusable(false);
        btCollect.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });

        lbRoundResult = new JLabel("mmmmmmmmm", SwingConstants.CENTER);
        lbRoundResult.setPreferredSize(new Dimension(450, 30));

        btStartRound.setPreferredSize(new Dimension(0, 60));
        btCollect.setPreferredSize(new Dimension(0, 20));
        plStartStopCollect.add(btStartRound, BorderLayout.NORTH);
        plStartStopCollect.add(btCollect, BorderLayout.SOUTH);
        controlpanel.add(plStartStopCollect, BorderLayout.EAST);

        plSettingsContainer.add(lbRoundResult, BorderLayout.NORTH);
        plSettingsContainer.add(plSettings, BorderLayout.SOUTH);
        controlpanel.add(plSettingsContainer, BorderLayout.WEST);

    }

    private void startSlotProcess()
    {
        Random position = new Random();
        //int counter = 0;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int pos = position.nextInt(9);

                fields[i][j].setIcon(new ImageIcon(slotImages[pos]));
            }
        }
    }

    private void initFrame()
    {
        this.setLayout(new BorderLayout());
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
    }

    public void setVermoegen(int vermoegen)
    {
        this.vermoegen = vermoegen;
    }
}
