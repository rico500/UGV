   import java.awt.event.*;
   import javax.swing.*;
  
   public class InterfaceGraphiqueTest_1 extends JFrame
   { 

    JTextField pressedKey;
	JLabel keyState;
    JButton quit;
    JButton connect;
    char send;
    String adresseIp = "";
    String psk = "";
    String username = "";
      public InterfaceGraphiqueTest_1()
      {
         super("Comunication");
        
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(400, 300);
      
        pressedKey = new JTextField();
      	pressedKey.setEditable(false);
		pressedKey.addKeyListener(new KeyboardListener());
		
		keyState = new JLabel("Key State");
			
        quit = new JButton("Quit");
        quit.addActionListener(new ButtonListener());
        connect = new JButton("Connect");
        connect.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		JOptionPane connectionPane = new JOptionPane();
        		adresseIp = connectionPane.showInputDialog(null, "Veuillez donner une adresse ip", JOptionPane.QUESTION_MESSAGE);
        		
        		JOptionPane userName = new JOptionPane();
        		username = userName.showInputDialog(null, "Veuillez donner le nom d'utilisateur", JOptionPane.QUESTION_MESSAGE);
        		
        		JOptionPane password = new JOptionPane();
        		psk = password.showInputDialog(null, "Mot de passe", JOptionPane.QUESTION_MESSAGE);
        	}
        	
        });

        Box VertBox = Box.createVerticalBox();
        VertBox.add(pressedKey);
		VertBox.add(keyState);
		VertBox.add(connect);
        VertBox.add(quit);
       
      
        getContentPane().add(VertBox);
      }
   
      private class ButtonListener implements ActionListener
      {
      
         public void actionPerformed(ActionEvent event)
         {
            System.exit(0);
         }
      
      }
   
      private class KeyboardListener implements KeyListener
      {
      
         public void keyPressed(KeyEvent e)
         {
            System.out.println("Key Pressed!!!");
         	pressedKey.setText("Key " + e.getKeyChar()+ " is pressed");
         	send = e.getKeyChar();
         }
      
         public void keyReleased(KeyEvent e)
         {
            System.out.println("Key Released!!!");
            pressedKey.setText("Key " + e.getKeyChar()+ " was released");         
         }
         
         public void keyTyped(KeyEvent e){}
         
      }
   
      public static void main(String args[])
      {
         InterfaceGraphiqueTest_1 keyListener = new InterfaceGraphiqueTest_1();
         keyListener.setVisible(true);
      } 
   
   }