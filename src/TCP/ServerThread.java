package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/***************************************************************************
 * Once the Server welcomes a client it starts this thred which will deal with
 * him.
 * 
 * @author Eric Brunner
 *  
 * inspired by tcp server "Surnoms Database" by Dominski Julien & 
 * Gioan-Mariani Cedric
 *
 ***************************************************************************/

public class ServerThread extends Thread {

	/*----------- PRIVATE INSTANCES ------------*/
	
	private Socket clientSocket; // Socket client
    private boolean activThread;
    
    private InputStream in;
    private OutputStream out;
    
    public SerialCommunication serialCom;
    
    
    /**
     * Constructeur de ServerThread
     * @param clientSocket - pour le transfert de donnees
     */

    public ServerThread(Socket clientSocket ){
    	this.clientSocket = clientSocket;
    	activThread=true;
    }
    
    public void run(){
    	while(activThread){
    		System.out.println("-----> Le client " + clientSocket.getInetAddress().getHostName() 
    				+" est connecte <------");
    		
    		serialCom = new SerialCommunication();
    		
    		serialCom.connect("/dev/ttyS33", 9600);
    		
    		try {
				in = clientSocket.getInputStream();
				out = clientSocket.getOutputStream();
				
				//Welcome text
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				
				final String WELCOME = new String("Welcome commander!\n"
						+ "Connection established on "+ dateFormat.format(date)+"\n"
								+ "Have lots of fun!");
				out.write(WELCOME.getBytes());
				
				
				while(true){
					
					byte[] inBuffer = new byte[1024]; 
					
					Thread.sleep(1000);
					out.write(new String("beat").getBytes());
					
					if(in.available() > 0){
						in.read(inBuffer);
						System.out.println( "------> Le client " + clientSocket.getInetAddress().getHostName() 
				    			+ " a envoye un message <-----");
						
						String message = new String(inBuffer);
						
						serialCom.write(message);
								
						}
					}
					
				}catch (IOException e) {
				if(activThread) end();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    }
    
    /**
     * 
     * Close the thread
     * 
     */
   
    public void end(){
    	System.out.println( "------> Le client " + clientSocket.getInetAddress().getHostName() 
    			+ " s'est deconnecte <------ ");
    	
    	 activThread=false;
    	 
    	 serialCom.closePort();
    	 try {
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
    	
    }
    
}
