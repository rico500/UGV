import gnu.io.*;

import java.io.*;
import java.lang.*;
import java.util.*;

public class SerialCommunication implements SerialPortEventListener {
	
	String portName;
	int baudRate;
	
	SerialPort serialPort;
	
	InputStream in;
	OutputStream out;

		public SerialCommunication(){

		}
		
		
	/*--------PUBLIC METHODS--------*/
	   /**
	     * @return	A HashSet containing the CommPortIdentifier for all serial 
	     * 			ports that are not currently being used.
	     */
	    public HashSet<CommPortIdentifier> getAvailableSerialPorts() {
	        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
	        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
	        while (thePorts.hasMoreElements()) {
	            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
	            switch (com.getPortType()) {
	            case CommPortIdentifier.PORT_SERIAL:
	                try {
	                    CommPort thePort = com.open("CommUtil", 50);
	                    thePort.close();
	                    h.add(com);
	                    System.out.println("Port, " + com.getName()+ ", is available.");
	                } catch (PortInUseException e) {
	                    System.out.println("Port, "  + com.getName() + ", is in use.");
	                } catch (Exception e) {
	                    System.err.println("Failed to open port " +  com.getName());
	                    e.printStackTrace();
	                }
	            }
	        }
	        return h;
	    }

	    
	    /* Establishes serial connection on given port at given baudRate
	     * @param portName - name of one of the available ports. Ex: "/dev/ttyS33"
	     * */
	    public void connect(String portName, int baudRate){
	    	try{
	    	CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
	    	CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
	    	System.out.println("Connection was successful!");
	    	this.portName = portName;
	    	
	    	serialPort = (SerialPort) commPort;
	    	serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
	    	
	    	out = serialPort.getOutputStream();
	    	in = serialPort.getInputStream();
	    	
	    	serialPort.addEventListener(this);
	    	serialPort.notifyOnDataAvailable(true);
	    	
	    	
	    	}catch(NoSuchPortException nspe){
	    		System.err.println("Port /dev/"+ portName+ " could not be found. \nException:\n"+ nspe+ "\n");
	    		
	    	}catch(PortInUseException piue){
	    		System.err.println("Port /dev/"+ portName+ " is in use. \nException:\n"+piue+"\n");
	    		
	    	} catch (UnsupportedCommOperationException ucoe) {
	    		System.err.println("Opperation on port /dev/"+ portName+ " could not be executed. \nException:\n"+ucoe+"\n");
			
	    	} catch (TooManyListenersException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    
	    public void write(String message){
	    	try{
	    		byte[] buffer = message.getBytes();
	    		
	    		out.write(buffer, 0, buffer.length);
	    	
	    	}catch(IOException e){
	    		e.printStackTrace();
	    		
	    	}
	    	System.out.println("A message was sent:\n"+ message);
	    }
	    
	
	    
	    public void closePort(){
	    	serialPort.close();
	    	
	    }
	    
	    /**
	     * Handle serial events. Dispatches the event to event-specific
	     * methods.
	     * @param event The serial event
	     */
	    @Override
	    public void serialEvent(SerialPortEvent event){
	    	System.out.println("SerialPortEvent!");
	    	byte[] buffer = new byte[1024];
	    	try {
				int length = in.available();
				
				in.read(buffer, 0, length);
				System.out.println("Message: "+ new String(buffer));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    /*--------STATIC METHODS---------*/
	
	private static String askForPort() throws IOException{
	    	
	    	//Ask which port the user would like to use.
		    System.out.println("Enter your choice of available serial ports:");
		    BufferedReader dataIn = new BufferedReader( new InputStreamReader(System.in) );
		    String port = new String(dataIn.readLine());
		    return port;
	    }

	
	
	
	/*---------MAIN METHOD---------*/

	public static void main (String[] args) throws IOException, InterruptedException{
		SerialCommunication serialCommunication = new SerialCommunication();
		
	    HashSet<CommPortIdentifier> availableSerialPorts = new HashSet<CommPortIdentifier>();
	    
	    availableSerialPorts = serialCommunication.getAvailableSerialPorts();
	    
	    serialCommunication.connect("/dev/ttyS33", 9600);
	   
	    serialCommunication.write("Test");
	    
	    Thread.sleep(10000);
	    
	    serialCommunication.closePort();
	    }
	
}