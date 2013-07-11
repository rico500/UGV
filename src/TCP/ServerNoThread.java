package TCP;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ServerNoThread {
	
	private static BufferedReader in;
	private static PrintStream out;
	
	private static ServerSocket welcomeSocket;
	
	private static boolean ServerActiv = true;
	
	
		
/*----------- MAIN METHOD------------*/ 
	
	
	
	public static void main(String[] args) throws InterruptedException{
		
		try {
			welcomeSocket = new ServerSocket(4444);
			
			System.out.println("Server is running...");
		
			final Socket clientSocket = welcomeSocket.accept();
			
			System.out.println("-----> Le client " + clientSocket.getInetAddress().getHostName() 
    				+" est connecte <------");
			
			 in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			 out = new PrintStream(clientSocket.getOutputStream());
			
			
			//welcome text
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			final String WELCOME = new String("Welcome commander!\n"
					+ "Connection established on "+ dateFormat.format(date)+"\n	Have lots of fun!");
			out.write(WELCOME.getBytes(), 0, WELCOME.length());
			
			
			while(true){
				//get message from Client
				String message = in.readLine();
				
				//tell client the message was received
				out.println("Got message! " + message);
				
			}
			
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("TEST");
			
			try {
					welcomeSocket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
