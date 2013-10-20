package Server;

public class STORequestHandler implements RequestHandler {

	@Override
	public void handleRequest() {
		Server.serial.write("s|");

	}

}
