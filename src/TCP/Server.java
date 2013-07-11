package TCP;
import java.io.*;
import java.net.*;

public class Server {
	
	/*----------- MAIN METHOD------------*/ 
	
	public static void main(String[] args){
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
