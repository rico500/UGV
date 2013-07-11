package TCP;
import java.io.*;
import java.net.*;


public class Client {
	
	public static PrintStream out;
	public static BufferedReader in, keyboard;
	
	public static boolean clientActiv = true;
	
	public static void main(String[] args) throws Exception{
		
		//make connection
		try{
		if ( args.length == 0 ) throw new Exception("SYNTAXE : java Client <ServerHostName>");
	    
	    final Socket clientSocket = new Socket(args[0],4444); // Socket pour le transfert de donnees	
		
	    out = new PrintStream(clientSocket.getOutputStream());
	    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
	    
	    
	    String message = "";
	    String lastMessage = "";
	    
		//get user command
		keyboard = new BufferedReader(new InputStreamReader(System.in));
		String command;
	
		
		while(clientActiv){
			
			//wait for user input
			System.out.println("Enter command (ON or OFF or QUIT):");
			command = keyboard.readLine();
			
			if(command.equals("ON") || command.equals("OFF")){
				out.println(command);
				System.out.println("A message was sent:" + command );
				
			}else if(command.equals("QUIT")){
				end();
				
			}else
				System.err.println("SYNTAXE: message can only be ON or OFF in capital letters");
		}
		
		//closing Client
		clientSocket.close();
		System.out.println("Closing client...");
		System.exit(0);
		
		}catch(IOException e){
			e.printStackTrace();
			System.err.println("Connection at "+ args[0] +" on port 4444 could not be established");
			
		}
	}
	
	public static void end(){
		
		try {
			in.close();
			keyboard.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("ERROR: when closing input streams");
		}
		out.close();
		
		clientActiv = false;
	}
}
