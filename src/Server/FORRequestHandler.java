package Server;

public class FORRequestHandler implements RequestHandler {

	@Override
	public void handleRequest() {
		Server.serial.write("f,140|");

	}

}
