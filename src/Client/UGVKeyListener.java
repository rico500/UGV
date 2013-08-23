package Client;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class UGVKeyListener extends JTextField {
	
	public UGVKeyListener(String text){
		super(text);
		this.addUpKeyHandler();
	}
	
	private void addUpKeyHandler(){
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed UP"), "UPpressed");
		this.getActionMap().put("UPpressed", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("UP pressed!");
			}
			
		});
		
		this.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "UPreleased");
		this.getActionMap().put("UPreleased", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("UP released!");
			}
			
			
		});
	}
}
