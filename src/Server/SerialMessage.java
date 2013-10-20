package Server;

import java.io.IOException;

public class SerialMessage {
	
	public String serialMessage;
	
	public SerialMessage(){}
	
	public String getSerialMessage(){
		
		serialMessage = null;
		System.out.println("Serial port event!");
    	byte[] buffer = new byte[1024];
    	try{
			int length = Server.serial.in.available();
			
			Server.serial.in.read(buffer, 0, length);
			serialMessage = new String(buffer);
			System.out.println("Serial message: "+ serialMessage);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
    	
    	return serialMessage;
	}
	
	
	public void dispatchMessage(String s){
		
		char command = s.charAt(0);
		
		switch(command){
		case 'p':
			System.out.println("Sending message to client: BUT|");
			//TODO: tell all clients
			Server.sendMessage(Server.serverThreads.get(0).out, "BUT|0");
		}
	}
}
