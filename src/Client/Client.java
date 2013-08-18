package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;


public class Client {
	
	public boolean clientActive = true;
	public static PrintStream out;
	public static BufferedReader in;
	public static boolean isConnected = false; //is the client connnected?
	
	private static Socket serverSocket;

	//connet to localhost on port 4444
	public static void connect(String host, int port) {
		
		 try {
			serverSocket = new Socket(host, port);// Socket pour le transfert de donnees
			isConnected = true;
		} catch (UnknownHostException e) {
			isConnected = false;
			System.err.println("ERROR: connecting to "+host+":"+port+".\nThe host does not exist or is unreachable.");
		} catch(ConnectException e){
			isConnected = false;
			System.err.println("ERROR: connecting to "+host+":"+port+".\nCoud not connect to given address or port.");
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
		 
	}
	
	public static String getMessage() throws IOException{
		
		return in.readLine();
	}
	
	public static void sendMessage(String message){
		
		out.println(message);
	}
	
	public static void main(String[] args){
		System.out.println("test");
		
		   InterfaceGraphiqueTest_1 mainFrame = new InterfaceGraphiqueTest_1();
		   mainFrame.setVisible(true);
		   mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
