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

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class InterfaceGraphiqueTest_1 extends JFrame
{  
   private JTabbedPane tabs;
   private JPanel connectionTab;
   private JPanel controlTab;
   private JPanel optionTab;
   private JPanel helpTab;
   private JPanel quitTab;
   private JLabel ok;
   private JLabel noOk;
   
   private String upString = "w";
   private String downString = "s";
   private String leftString = "a";
   private String rightString = "d";
   private String increaseMaxSpeedString = "r";
   private String decreaseMaxSpeedString = "f";
   private String increaseMaxBreakString = "t";
   private String decreaseMaxBreakString = "g";
   public char upChar;
   public char downChar;
   public char leftChar;
   public char rightChar;
   public char increaseMaxSpeedChar;
   public char decreaseMaxSpeedChar;
   public char increaseMaxBreakChar;
   public char decreaseMaxBreakChar;
   public String port;
   public String ipAddress;
   public String userName;
   public String password;
   
   
   JTextField ipAddressTextField = new JTextField("192.168.1.2");
   JTextField portTextField = new JTextField("888");
   JTextField userNameTextField = new JTextField("pi");
   JTextField pskTextField = new JTextField("raspberry");
   
   public static boolean debugging = true; //Disable this boolean to disable the debugs messages
   boolean connectionTabBool = true;
   Matcher checkAddress;
   
   public InterfaceGraphiqueTest_1()
   {
      setTitle("IHM travail de matu");
      setSize(500, 350);
      setBackground(Color.WHITE);
      
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
      tabs.addTab("Paramètres", optionTab); 
      tabs.addTab("Aide", helpTab);
      tabs.addTab("Quitter", quitTab);
      topPanel.add(tabs, BorderLayout.CENTER);
   }
	   
   
   public void createConnectionTab() 
   {
      
      
	  connectionTab = new JPanel();
     connectionTab.setLayout(null);
     Thread regex;
      
     regex = new Thread(new Juste());
     
      
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
	    
	   JLabel usernameLabel = new JLabel("Nom d'utilisateur");
	   usernameLabel.setBounds(10, 55, 150, 20);
	   connectionTab.add(usernameLabel);
	      
	   userNameTextField.setBounds(150, 55, 150, 20);
	   connectionTab.add(userNameTextField);

	   JLabel pskLabel = new JLabel("Mot de Passe");
	   pskLabel.setBounds(10, 75, 150, 20);
	   connectionTab.add(pskLabel);            
	      
	   pskTextField.setBounds(150, 75, 150, 20);
	   connectionTab.add(pskTextField);
      
      regex.start();
      
      
   }
   
   class Juste implements Runnable
   {
      public void run()
      {	 
      	 Debugging.log("thread");

         String compareAddress = "[0-9]{1,3}(?:.[0-9]{1,3}){3}";

         while(connectionTabBool)
         {
        	 ipAddress = ipAddressTextField.getText();
            try
            {  Debugging.log("try");
               
               //Pattern p = Pattern.compile(compareAddress);
               // checkAddress = p.matcher(ipAddress);
               
               if(compareAddress.matches(ipAddress)== false)
	            {
            	   noOk = new JLabel(new ImageIcon("faux.png"));
                   noOk.setBounds(325, 15, 20, 20);
                  /*ok.setVisible(false);
                  noOk.setVisible(true);*/
                  connectionTab.add(noOk);
                  Debugging.log("Ip address is false!");
             }
	         if(compareAddress.find(ipAddress)== true)
	         {
	              ok = new JLabel(new ImageIcon("juste.png"));
	              ok.setBounds(375, 15, 20, 20);
                  connectionTab.add(ok);
                  Debugging.log("Ip address is right");
   	         }
               Debugging.log("Second thread");
               Thread.sleep(10);
            }
      	   catch(InterruptedException e)
            {
               Debugging.log("Catch ERROR!");
            }
         }//end of while  
      }//end of run()

   }//end of class Juste   
   
	   
      
      
      
      /*   
	   port = portTextField.getText();
	   if(check == false)
	   {
		   noOk = new JLabel(new ImageIcon("faux.png"));
		   noOk.setBounds(325, 35, 20, 20);
		   connectionTab.add(noOk);
	   }
	   else
	   {
		   ok = new JLabel(new ImageIcon("juste.png"));
		   ok.setBounds(325, 35, 20, 20);
		   connectionTab.add(ok);
	   }
		   
	   userName = userNameTextField.getText();
	   if(check == false)
	   {
		   noOk = new JLabel(new ImageIcon("faux.png"));
		   noOk.setBounds(325, 55, 20, 20);
		   connectionTab.add(noOk);
	   }
	   else
	   {
		   ok = new JLabel(new ImageIcon("juste.png"));
		   ok.setBounds(325, 55, 20, 20);
		   connectionTab.add(ok);
	   }
		   
	   password = pskTextField.getText();
	   if(check == false)
	   {
		   noOk = new JLabel(new ImageIcon("faux.png"));
		   noOk.setBounds(325, 75, 20, 20);
		   connectionTab.add(noOk);
	   }
	   else
	   {
		   ok = new JLabel(new ImageIcon("juste.png"));
		   ok.setBounds(325, 75, 20, 20);
		   connectionTab.add(ok);
	   }*/
	   
	  /*JButton accept = new JButton("OK");
	  accept.setBounds(125, 100, 100, 50);
	  accept.addActionListener(new AcceptListener());
	  connectionTab.add(accept);*/
   

   public void createControlTab()
   {
	  
      controlTab = new JPanel();
      controlTab.setLayout(new FlowLayout());
      
      
      
      /*convert the Strings commands to Char commands*/
      
      upChar = upString.charAt(0);
      downChar = downString.charAt(0);
      leftChar = leftString.charAt(0);
      rightChar = rightString.charAt(0);
      increaseMaxSpeedChar = increaseMaxSpeedString.charAt(0);
      decreaseMaxSpeedChar = decreaseMaxSpeedString.charAt(0);
      increaseMaxBreakChar = increaseMaxBreakString.charAt(0);
      decreaseMaxBreakChar = decreaseMaxBreakString.charAt(0);
      
      
      Box videoBox = Box.createHorizontalBox();
      JLabel waitForVideo = new JLabel("attente de la vidéo");
      videoBox.add(waitForVideo);
      controlTab.add(videoBox);
      
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

      Font warningFont = new Font("Seriaf", Font.PLAIN, 36);
            
      JLabel warningQuit = new JLabel("Attention!!!", JLabel.CENTER);

      JButton quitButton = new JButton("Quitter");
      quitButton.setIcon(new ImageIcon("dialog-error.png"));
      warningQuit.setFont(warningFont);
      
      quitButton.addActionListener(new QuitButtonListener());
      quitTab.add(warningQuit, BorderLayout.CENTER);
      quitTab.add(quitButton, BorderLayout.SOUTH);
   }
   
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

   public static void main(String args[])
   {
      InterfaceGraphiqueTest_1 mainFrame = new InterfaceGraphiqueTest_1();
      mainFrame.setVisible(true);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }   
     
     
     
   
}         

/*Debugging class allow you to print all the debugs informations*/



