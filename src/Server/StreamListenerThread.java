package Server;

import java.io.IOException;
import java.util.StringTokenizer;

public class StreamListenerThread extends Thread {
	
	private ServerThread serverThread;
	private StreamListener streamListener;
	
	public StreamListenerThread(ServerThread st, StreamListener sl){
		this.serverThread = st;
		this.streamListener = sl;
	}

	@Override
	public void run() {
		try{
			while(serverThread.serverThreadActive){
				serverThread.messageFromClient = new StringTokenizer(serverThread.in.readLine(), "|");
				streamListener.OnEvent();
				System.out.println("log");
			}	
		}catch(IOException e){
			System.err.println("ERROR: geting message from server");
		}
	}

}
