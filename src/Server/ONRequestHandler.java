package Server;


public class ONRequestHandler implements RequestHandler {

	public ONRequestHandler() {
	}
	
	public void handleRequest() {
		Server.serial.write("i");
	}

}
