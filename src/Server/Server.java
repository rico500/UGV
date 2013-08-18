package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;

import Serial.SerialCommunication;


public class Server {
	
	public static SerialCommunication serial = new SerialCommunication();
	public static HashMap<String, RequestHandler> RequestHandlers = new HashMap<String, RequestHandler>(); 
	
	//get message from Client and returns StringTokenizer
	public static StringTokenizer getMessage(BufferedReader in) throws IOException{
		return new StringTokenizer(in.readLine(), "|");
		
	}
		
	//send message to Client
	public static void sendMessage(PrintStream out, String message){
		out.println(message);
			
	}
	
	public static boolean isRequest(String request){
		
		if(RequestHandlers.containsKey(request)){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	//TODO handle speed option (second token)
	public static void dispatchRequest(String request){
		
		RequestHandler handler = (RequestHandler)RequestHandlers.get(request);
		
		handler.handleRequest();
	}
	
	
	
	private static void init(){
		serial.connect("/dev/ttyS33", 9600);
		
		RequestHandlers.put("ON", new ONRequestHandler());
		RequestHandlers.put("OFF", new OFFRequestHandler());
		RequestHandlers.put("FOR", new FORRequestHandler());
		RequestHandlers.put("BAK", new BAKRequestHandler());
	}

	public static void main(String[] args){
		init();
		
		try {
			ServerSocket welcomeSocket = new ServerSocket(4444);
			
		
		
		System.out.println("Server is running...");
		
		while (true){
			final Socket clientSocket = welcomeSocket.accept(); // Socket de transfert de donnees
			
			ServerThread st = new ServerThread( clientSocket ); // Creation d'un nouveau Thread
			st.start();
			
		    }
		
		} catch (IOException e) {
		    System.err.println(e.getMessage());

		}
	}
	
}
