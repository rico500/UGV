package Server;

public class BAKRequestHandler implements RequestHandler {

	@Override
	public void handleRequest() {
		Server.serial.write("b,140|");
	}

}
