package Client;

import java.util.StringTokenizer;

public class SESRequestHandler implements ClientRequestHandler {

	@Override
	public void handleRequest(String s) {
		StringTokenizer st = new StringTokenizer(s, ",");
		
		Client.sensor1 = Integer.parseInt(st.nextToken());
		Client.sensor2 = Integer.parseInt(st.nextToken());
		Client.sensor3 = Integer.parseInt(st.nextToken());
		
		System.out.println("Sensor UP: "+Client.sensor1+", "+Client.sensor2+", "+Client.sensor3);
	}

}
