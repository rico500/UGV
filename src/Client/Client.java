package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.JFrame;


public class Client {
	
	public boolean clientActive = true;
	public static PrintStream out;
	public static BufferedReader in;
	public static boolean isConnected = false; //is the client connnected?
	
	private static Socket serverSocket;
	
	public static StringTokenizer messageFromServer; 
	
	public static HashMap<String, ClientRequestHandler> RequestHandlers = new HashMap<String, ClientRequestHandler>(); 
	
	
	//fill RequestHandlers HashMap
	private static void init(){
		
		System.out.println("Starting client application");

		//HashMap of request Handlers
		RequestHandlers.put("WEL", new WELRequestHandler());
		RequestHandlers.put("BUT", new BUTRequestHandler());
		//Start Graphical user interface
		System.out.println("Launching GUI...");
		GUI mainFrame = new GUI();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//connet to user selected host on port user selected port (default: localhost, 4444)
	public static void connect(String host, int port) {
		
		 try {
			serverSocket = new Socket(host, port);// Socket pour le transfert de donnees
			isConnected = true;
		} catch (UnknownHostException e) {
			isConnected = false;
			System.err.println("ERROR: connecting to "+host+":"+port+".\nThe host does not exist or is unreachable.");
			GUI.connectionMessage.setText("<html>ERROR: connecting to "+host+":"+port+".\nThe host does not exist or is unreachable.<br>"
					+ "Check if server is running and try again</p><html>");
		} catch(ConnectException e){
			isConnected = false;
			System.err.println("ERROR: connecting to "+host+":"+port+".\nCoud not connect to given address or port.");
			GUI.connectionMessage.setText("<html>ERROR: connecting to "+host+":"+port+".\nCoud not connect to given address or port.<br>"
					+ "Check if server is running and try again</p><html>");
			
		} catch (IOException e) {
			isConnected = false;
			e.printStackTrace();
		} 
		
		 try {
			out = new PrintStream(serverSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		} catch (IOException e) {
			System.err.println("ERROR: could not open communication streams with client.");
			e.printStackTrace();
		}
		 
		 //Start StreamListenerThread
		 ClientStreamListenerThread cslt = new ClientStreamListenerThread();
		 cslt.start();
	}
	
	public static String getMessage() throws IOException{
		
		return in.readLine();
	}
	
	public static void sendMessage(String message){
		
		out.println(message);
	}
	
	//Dispatch server Requests
	public static void DispatchServerRequest(String request){
		
		
	}
	
	public static void main(String[] args){
		init();
		
		System.out.println("test");
		
		  
	}
	
}
