package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;


public class ServerThread extends Thread {

	private Socket clientSocket; // Socket client
	
	public boolean serverThreadActive;
	public PrintStream out;
	public BufferedReader in;
	
	public StringTokenizer messageFromClient;
	public String message;
	public String messageToClient;
	
	
	public ServerThread(Socket clientSocket ){
    	this.clientSocket = clientSocket;
    	serverThreadActive = true;
    }
	
	
	public void run(){
		System.out.println("-----> Le client " + clientSocket.getInetAddress().getHostName() 
				+" est connecte <------");
		
		try {
			
			out = new PrintStream(clientSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		} catch (IOException e) {
			System.err.println("ERROR: could not open communication streams with client.");
		}
		
		StreamListenerThread slt = new StreamListenerThread(this, new StreamListener(){

			@Override
			public void OnEvent() {
				
				System.out.println("-----> Le client " + clientSocket.getInetAddress().getHostName() 
						+" a envoyé un message<------");
				
				//Get message out of StringTokenizer. The first message will be the request.
				message = messageFromClient.nextToken();
				System.out.println(message);
				
				
				if(Server.RequestHandlers.containsKey(message)){
					Server.dispatchRequest(message);
				}
			}
			
		}); 
		
		slt.start();
			
	}
}
