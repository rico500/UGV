package Client;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class UGVKeyListener extends JTextField {
	
	public UGVKeyListener(String text){
		super(text);
		this.addUpKeyHandler();
		this.addDownKeyHandler();
	}
	
	private void addUpKeyHandler(){
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed UP"), "UPpressed");
		this.getActionMap().put("UPpressed", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("UP pressed!");
				Client.sendMessage("|FOR|");
			}
			
		});
		
		this.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "UPreleased");
		this.getActionMap().put("UPreleased", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("UP released!");
				Client.sendMessage("|STO|");
			}
			
			
		});
	}
	
	private void addDownKeyHandler(){
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed DOWN"), "DOWNpressed");
		this.getActionMap().put("DOWNpressed", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("DOWN pressed!");
				Client.sendMessage("|BAK|");
			}
			
		});
		
		this.getInputMap().put(KeyStroke.getKeyStroke("released DOWN"), "DOWNreleased");
		this.getActionMap().put("DOWNreleased", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("DOWN released!");
				Client.sendMessage("|STO|");
			}
			
			
		});
	}
	
	private void addAKeyHandler(){
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed a"), "Apressed");
		this.getActionMap().put("Apressed", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("DOWN pressed!");
				Client.sendMessage("|BAK|");
			}
			
		});
		
	}
}
