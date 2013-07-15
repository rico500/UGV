import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;

public class InterfaceGraphiqueTest_1 extends JFrame
{  
   private JTabbedPane tabs;
   private JPanel connectionTab;
   private JPanel controlTab;
   private JPanel optionTab;
   private JPanel helpTab;
   private JPanel quitTab;
   int keyOnPress, keyOnRelease;
   char frontCarChar, backCarChar, leftCarChar, rightCarChar;
   
   public InterfaceGraphiqueTest_1()
   {
      setTitle("IHM travail de matu");
      setSize(500, 350);
      setBackground(Color.gray);
      
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
      
      JLabel adresseIpLabel = new JLabel("Adresse IP");
      adresseIpLabel.setBounds(10, 15, 150 ,20);
      connectionTab.add(adresseIpLabel);
      
      JTextField adresseIpTextField = new JTextField();
      adresseIpTextField.setBounds(150, 15, 150, 20);
      connectionTab.add(adresseIpTextField);
      
      JLabel portLabel = new JLabel("Port");
      portLabel.setBounds(10, 35, 150, 20);
      connectionTab.add(portLabel);
      
      JTextField portTextField = new JTextField();
      portTextField.setBounds(150, 35, 150, 20);
      connectionTab.add(portTextField);
      
      JLabel usernameLabel = new JLabel("Nom d'utilisateur");
      usernameLabel.setBounds(10, 55, 150, 20);
      connectionTab.add(usernameLabel);
      
      JTextField usernameTextField = new JTextField();
      usernameTextField.setBounds(150, 55, 150, 20);
      connectionTab.add(usernameTextField);
      
      JLabel pskLabel = new JLabel("Mot de Passe");
      pskLabel.setBounds(10, 75, 150, 20);
      connectionTab.add(pskLabel);            
      
      JTextField pskTextField = new JTextField();
      pskTextField.setBounds(150, 75, 150, 20);
      connectionTab.add(pskTextField);
   } 
   
   public void createControlTab()
   {
      controlTab = new JPanel();
      controlTab.setLayout(null);
      
      Box videoBox = Box.createHorizontalBox();
      JLabel waitForVideo = new JLabel("attente de la vidéo");
      videoBox.add(waitForVideo);
      controlTab.add(videoBox);
      
   }
   
   public void createOptionTab()
   {
      optionTab = new JPanel();
      optionTab.setLayout(new BorderLayout());
      
      JButton frontCarButton = new JButton("Touche avancer");
      JButton rightCarButton = new JButton("Touche droite");
      JButton leftCarButton = new JButton("Touche gauche");
      JButton backCarButton = new JButton("Touche reculer");
      JLabel carPanelLabel = new JLabel(new ImageIcon("voiture-panel.jpg"));
      
      frontCarButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            JOptionPane frontCarOptionPane = new JOptionPane();
            frontCarChar = frontCarOptionPane.showInputDialog(null, "Choisissez un touche pour avancer", JOptionPane.QUESTION_MESSAGE);
         }
      });
      
      leftCarButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            JOptionPane leftCarOptionPane = new JOptionPane();
            leftCarChar = leftCarOptionPane.showInputDialog(null, "Choisissez un touche pour gauche", JOptionPane.QUESTION_MESSAGE);
         }
      });
      
      rightCarButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            JOptionPane rightCarOptionPane = new JOptionPane();
            rightCarChar = rightCarOptionPane.showInputDialog(null, "Choisissez un touche pour droite", JOptionPane.QUESTION_MESSAGE);
         }
      });
      
      backCarButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            JOptionPane backCarOptionPane = new JOptionPane();
            backCarChar = backCarOptionPane.showInputDialog(null, "Choisissez un touche pour reculer", JOptionPane.QUESTION_MESSAGE);
         }
      });


      
      
      optionTab.add(frontCarButton, BorderLayout.NORTH);
      optionTab.add(rightCarButton, BorderLayout.EAST);
      optionTab.add(leftCarButton, BorderLayout.WEST);
      optionTab.add(backCarButton, BorderLayout.SOUTH);
      optionTab.add(carPanelLabel, BorderLayout.CENTER);
      
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
         keyOnPress = e.getKeyCode();
      }
      
      public void keyReleased(KeyEvent e)
      {
         System.out.println("Key Released!");
         keyOnRelease = e.getKeyCode();
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
   public static void main(String args[])
   {
      InterfaceGraphiqueTest_1 mainFrame = new InterfaceGraphiqueTest_1();
      mainFrame.setVisible(true);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }   
      
}         
  
