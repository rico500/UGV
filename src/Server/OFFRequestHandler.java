package Server;


public class OFFRequestHandler implements RequestHandler {

	public OFFRequestHandler(){
	}
		
	public void handleRequest() {
		Server.serial.write("o");
	}

}
