package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class GUI extends JFrame
{  
   private JTabbedPane tabs;
   private JPanel connectionTab;
   private JPanel controlTab;
   private JPanel optionTab;
   private JPanel helpTab;
   private JPanel quitTab;
   private JLabel ok, ok1, ok2, ok3;
   private JLabel noOk, noOk1, noOk2, noOk3;
   
   private String upString = "w";
   private String downString = "s";
   private String leftString = "a";
   private String rightString = "d";
   private String increaseMaxSpeedString = "w";
   private String decreaseMaxSpeedString = "s";
   private String increaseMaxBreakString = "d";
   private String decreaseMaxBreakString = "a";
   
   public int port;
   public String ipAddress;
   JTextField ipAddressTextField = new JTextField("localhost");
   JTextField portTextField = new JTextField("4444");
   
   //shows connection message
   public static JLabel connectionMessage = new JLabel("Waiting for Connection...");
   
   //Slider indicating relative speed (pwm)
   public static JSlider SpeedSlider = new JSlider(JSlider.VERTICAL, 0, 150, Client.speed);
   public static JSlider rotationSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, Client.rotation);
   
   public static boolean debugging = false; //Disable this boolean to disable the debugs messages
   boolean connectionTabBool = true;
   Matcher checkAddress;
   
   public GUI()
   {
      setTitle("IHM travail de matu");
      setSize(500, 350);
      setBackground(Color.WHITE);
      System.out.println(System.getProperty("user.dir"));
      JPanel topPanel = new JPanel();
      topPanel.setLayout(new BorderLayout());
      getContentPane().add(topPanel);
      
      createConnectionTab();
      createControlTab();
      createOptionTab();
      createHelpTab();
      createQuitTab();
      
      tabs = new JTabbedPane();
      tabs.addTab("Connection", connectionTab);
      tabs.addTab("Controle", controlTab);
      tabs.addTab("Param�tres", optionTab); 
      tabs.addTab("Aide", helpTab);
      tabs.addTab("Quitter", quitTab);
      topPanel.add(tabs, BorderLayout.CENTER);
   }
	   
   
   public void createConnectionTab() 
   {
      
      
	  connectionTab = new JPanel();
     connectionTab.setLayout(null);
     
       JLabel ipAddressLabel = new JLabel("Adresse IP");
	   ipAddressLabel.setBounds(10, 15, 150 ,20);
	   connectionTab.add(ipAddressLabel);
	   
	   ipAddressTextField.setBounds(150, 15, 150, 20);
	   connectionTab.add(ipAddressTextField);
      
	   JLabel portLabel = new JLabel("Port");
	   portLabel.setBounds(10, 35, 150, 20);
	   connectionTab.add(portLabel);
	     
	   portTextField.setBounds(150, 35, 150, 20);
	   connectionTab.add(portTextField);
	   
	   JButton connectButton = new JButton("CONNECT");
	   connectButton.setBounds(150 , 95, 100, 40);
	   connectionTab.add(connectButton);
       connectButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			ipAddress = ipAddressTextField.getText();
			port = Integer.parseInt(portTextField.getText());
			
			Client.connect(ipAddress, port);
		}
    	   
       });
       
       connectionMessage.setBounds(150, 155, 300, 80);
       connectionTab.add(connectionMessage);
       connectionMessage.setVisible(true);	   
      
   }
   

   public void createControlTab()
   {
	  
      controlTab = new JPanel();
      controlTab.setLayout(null);
      Thread video = new Thread(new VideoReceiver());
      
      video.start();
      
      JTextField controlField = new UGVKeyListener("Click here to start driving!");
      controlField.setEditable(false);
      controlField.setBounds(150, 130, 170, 50);
      controlTab.add(controlField);
      
      SpeedSlider.setMajorTickSpacing(20);
      SpeedSlider.setPaintTicks(true);
      SpeedSlider.setPaintLabels(true);
      SpeedSlider.setBounds(20, 50, 100, 200);
      controlTab.add(SpeedSlider);
      
      rotationSlider.setMajorTickSpacing(15);
      rotationSlider.setPaintTicks(true);
      rotationSlider.setPaintLabels(true);
      rotationSlider.setBounds(130, 200, 300, 100);
      controlTab.add(rotationSlider);
   }
   
   class VideoReceiver implements Runnable
   {
	   public void run()
	   {
		   
	   }
   }
   
   public void createOptionTab()
   {
	  optionTab = new JPanel();
      optionTab.setLayout(new GridBagLayout());
      
      
      //Initializing the buttons

      JButton frontCarButton = new JButton(upString);
      JButton rightCarButton = new JButton(rightString);
      JButton leftCarButton = new JButton(leftString);
      JButton backCarButton = new JButton(downString);
      JButton turnUp = new JButton("aug. Bra.");
      JButton turnDown = new JButton("dim. Bra");
      JButton speedUp = new JButton("aug. vit");
      JButton speedDown = new JButton("dim. vit");
      
      final JLabel upArrow = new JLabel(new ImageIcon("arrow-up.png"));
      final JLabel downArrow = new JLabel(new ImageIcon("arrow-down.png"));
      final JLabel rightArrow = new JLabel(new ImageIcon("arrow-right.png"));
      final JLabel leftArrow = new JLabel(new ImageIcon("arrow-left.png"));
      final JLabel labelUpSpeed = new JLabel(increaseMaxSpeedString);
      final JLabel labelDownSpeed = new JLabel(decreaseMaxSpeedString);
      final JLabel labelUpBreak = new JLabel(increaseMaxBreakString);
      final JLabel labelDownBreak = new JLabel(decreaseMaxBreakString);

      
      //GridBagLayout placement
      
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.gridy = 0;
      gbc.gridx = 2;
      optionTab.add(frontCarButton, gbc);
      
      gbc.gridy = 1;
      gbc.gridx = 2;
      optionTab.add(upArrow, gbc);
      
      gbc.gridy = 2;
      gbc.gridx = 0;
      optionTab.add(leftCarButton, gbc);
      
      gbc.gridy = 2;
      gbc.gridx = 1;
      optionTab.add(leftArrow, gbc);
      
      gbc.gridy = 2;
      gbc.gridx = 3;
      optionTab.add(rightArrow, gbc);
      
      gbc.gridy = 2;
      gbc.gridx = 4;
      optionTab.add(rightCarButton, gbc);
      
      gbc.gridy = 5;
      gbc.gridx = 0;
      optionTab.add(turnUp, gbc);
      
      gbc.gridy = 5;
      gbc.gridx = 1;
      optionTab.add(turnDown, gbc);
      
      gbc.gridy = 3;
      gbc.gridx = 2;
      optionTab.add(downArrow, gbc);
      
      gbc.gridy = 4;
      gbc.gridx = 2;
      optionTab.add(backCarButton, gbc);
      
      gbc.gridy = 5;
      gbc.gridx = 3;
      optionTab.add(speedUp, gbc);
      
      gbc.gridy = 5;
      gbc.gridx = 4;
      optionTab.add(speedDown, gbc);
      
      gbc.gridy = 6;
      gbc.gridx = 0;
      optionTab.add(labelUpBreak, gbc);
     
      gbc.gridy = 6;
      gbc.gridx = 1;
      optionTab.add(labelDownBreak, gbc);

      gbc.gridy = 6;
      gbc.gridx = 3;
      optionTab.add(labelUpSpeed, gbc);

      gbc.gridy = 6;
      gbc.gridx = 4;
      optionTab.add(labelDownSpeed, gbc);

      
      
      
   }
   
   public void createHelpTab()
   {
	  helpTab = new JPanel();
      helpTab.setLayout(null);
   }
   
   public void createQuitTab()
   {  
	  quitTab = new JPanel();
      quitTab.setLayout(new BorderLayout());
      Thread quit = new Thread(new Quittingpopup());
      quit.start();
      Font warningFont = new Font("Seriaf", Font.PLAIN, 36);
            
      JLabel warningQuit = new JLabel("Attention!!!", JLabel.CENTER);

      
      warningQuit.setFont(warningFont);
      
      
      quitTab.add(warningQuit, BorderLayout.CENTER);
      
   }
   
   class Quittingpopup implements Runnable
   {
	   public void run()
	   {
		   JButton quitButton = new JButton("Quitter");
		   quitButton.addActionListener(new QuitButtonListener());
		   quitButton.setIcon(new ImageIcon("dialog-error.png"));
		   quitTab.add(quitButton, BorderLayout.SOUTH);
	   }
   }
   
   //TODO make keyListener in control pane work!!
   private class KeyboardListener implements KeyListener
   {
      public void keyPressed(KeyEvent e)
      {  
         System.out.println("Key Pressed!");
        
      }
      
      public void keyReleased(KeyEvent e)
      {
         System.out.println("Key Released!");
         
      }
      
      public void keyTyped(KeyEvent e)
      {
      }
      
   }
   
   public class QuitButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         int replyOnQuit = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?","ATTENTION", JOptionPane.YES_NO_OPTION);
         if(replyOnQuit == JOptionPane.YES_OPTION)
         {
            System.exit(0);
         }
         
         else
         {
            
         }
      }
   }
   
   public static class Debugging
   {
      private Debugging()
      {
      }
      public static void log(String toLog)
      {
         if(debugging==true)
         {
            System.out.println(toLog);
         }
         else
         {
         }
      }
   } 
     
     
     
   
}         

/*Debugging class allow you to print all the debugs informations*/



