import gnu.io.;

import java.io.;
import java.lang.;
import java.util.;

public class SerialCommunication implements SerialPortEventListener {
	
	String portName;
	int baudRate;
	
	SerialPort serialPort;
	
	InputStream in;
	OutputStream out;

		public SerialCommunication(){

		}
		
		
		--------PUBLIC METHODS--------
		 Finds all available ports of Serial type and tests them. The ports where connection
	     was successful are than stored in a HashSet.
	     	
	     @return	A HashSet containing the CommPortIdentifier for all serial 
	     			ports that are not currently being used.
	    
	    public HashSetCommPortIdentifier getAvailableSerialPorts() {
	        HashSetCommPortIdentifier h = new HashSetCommPortIdentifier();
	        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
	        while (thePorts.hasMoreElements()) {
	            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
	            switch (com.getPortType()) {
	            case CommPortIdentifier.PORT_SERIAL
	                try {
	                    CommPort thePort = com.open(CommUtil, 50);
	                    thePort.close();
	                    h.add(com);
	                    System.out.println(Port,  + com.getName()+ , is available.);
	                } catch (PortInUseException e) {
	                    System.out.println(Port,   + com.getName() + , is in use.);
	                } catch (Exception e) {
	                    System.err.println(Failed to open port  +  com.getName());
	                    e.printStackTrace();
	                }
	            }
	        }
	        return h;
	    }

	    
	     
	      Establishes serial connection on given port at given baudRate
	      
	      @author httprxtx.qbang.orgwikiindex.phpDiscovering_available_comm_ports
	      @param portName - 	name of one of the available ports. Ex devttyS33
	      @param baudRate - 	desired baudRate. baud is a unit meaning symbols per second,
	      						or in this case bits per second.
	      
	    public void connect(String portName, int baudRate){
	    	try{
	    	CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
	    	CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
	    	System.out.println(Connection was successful!);
	    	this.portName = portName;
	    	
	    	serialPort = (SerialPort) commPort;
	    	serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
	    	
	    	out = serialPort.getOutputStream();
	    	in = serialPort.getInputStream();
	    	
	    	serialPort.addEventListener(this);
	    	serialPort.notifyOnDataAvailable(true);
	    	
	    	
	    	}catch(NoSuchPortException nspe){
	    		System.err.println(Port dev+ portName+  could not be found. nExceptionn+ nspe+ n);
	    		
	    	}catch(PortInUseException piue){
	    		System.err.println(Port dev+ portName+  is in use. nExceptionn+piue+n);
	    		
	    	} catch (UnsupportedCommOperationException ucoe) {
	    		System.err.println(Opperation on port dev+ portName+  could not be executed. nExceptionn+ucoe+n);
			
	    	} catch (TooManyListenersException tmle) {
	    		System.err.println(There are too many listeners on port dev+ portName+ nExceptionn+tmle+n);

			} catch (IOException e) {
				 TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    
	      Simple method to write a String to the device. Can only be called after connection is
	      established using the connect() method.
	      @author Eric Brunner
	      @param message - desired message to be sent
	      
	    public void write(String message){
	    	try{
	    		byte[] buffer = message.getBytes();
	    		
	    		out.write(buffer, 0, buffer.length);
	    	
	    	}catch(IOException e){
	    		e.printStackTrace();
	    		
	    	}
	    	System.out.println(A message was sentn+ message);
	    }
	    
	    
	    
	      Close serial port.
	      
	    public void closePort(){
	    	serialPort.close();
	    	
	    }
	    
	    
	      Handle serial events.
	      
	      If one wishes to handle a SerialPortEvent of a different type a switch case coul be added.
	      Refer to httpen.wikibooks.orgwikiSerial_ProgrammingSerial_Java#Simple_Reading_of_Data_.28Polling.29
	      for more information.
	      
	      @author Eric Brunner
	      @param event - The serial event
	     
	    @Override
	    public void serialEvent(SerialPortEvent event){
	    	System.out.println(SerialPortEvent!);
	    	byte[] buffer = new byte[1024];
	    	try {
				int length = in.available();
				
				in.read(buffer, 0, length);
				System.out.println(Message + new String(buffer));
			} catch (IOException e) {
				 TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	
	
	
	---------MAIN METHOD---------

	public static void main (String[] args) throws IOException, InterruptedException{
		
		SerialCommunication serialCommunication = new SerialCommunication();
		
		get the available ports in case they would be needed
	    HashSetCommPortIdentifier availableSerialPorts = new HashSetCommPortIdentifier();
	    availableSerialPorts = serialCommunication.getAvailableSerialPorts();
	    
	    establish connection
	    serialCommunication.connect(devttyS33, 9600);
	    
	    write a test String
	    serialCommunication.write(Test);
	    
	    keep the app alive for 10 sec so SerialPortEvents can be captured
	    Thread.sleep(10000);
	    
	    close port before shutdown
	    serialCommunication.closePort();
	    }
	
}