// title		Distributed System assignment2
// author	Ren DING
// ID		a1202524

/*
 * Stub class: The Stub class that acts as a proxy for the remote object.
 *  The methods in this class are proxy methods that are responsible
 *  for performing the remote method invocation requests, i.e. they 
 *  request a socket connection to be established with the skeleton
 *  using the YourRemoteRef class and use the Marshalling class to send the
 *  Method request to the Skeleton, and then read any return result.
 */

import java.net.*;
import java.io.*;

public class Stub implements YourRemote {
    private YourRemoteRef remoteObject;// the Remote object reference
    
    /** The contact details of the YourRemoteRe remote
	object reference for your assignment will be hardcoded
	into the YourRemoteRef class.
    **/

    private String hostName = "localhost";
    private int portNumber = 2000;
    private Socket s;
    
    private Marshalling marshaller;
    
    public Stub() {
    		try {
        		s = new Socket(hostName,portNumber);	
        		remoteObject = new YourRemoteRef();
        		
        		//sent a remoteObject reference to skeleton, call by reference
        		marshaller = new Marshalling();
        		marshaller.marshalling(remoteObject, new ObjectOutputStream(s.getOutputStream()));
        		
        		//get a remoteObjectID from skeleton and update the remoteObjectID
        		ObjectInputStream objInputStream = new ObjectInputStream(s.getInputStream());
    			MethodResponse response = (MethodResponse)(marshaller.unmarshalling(objInputStream));
    			remoteObject.setID( response.getLoggerID() );
    		} catch(IOException ioe) {
    			System.out.println("cannot create a stub socket, IO: " + ioe.getMessage());
    		} catch(ClassNotFoundException cnfe) {
    			System.out.println("cannot get a remoteRef ID ");
    		}
    }
    
    /*
     * The three methods below shall call the YourRemoteRef's methods, 
    	 * which means that it calls the remote object reference's method.
	 * In other words, it can indirectly call the remote object's method
     */

    public void addLogMessage( String message) {
	/** remote method invocation to Skeleton **/
    		remoteObject.addLogMessage(message);
    }

    public void createNewLog() {
	/** remote method invocation to Skeleton **/
    		remoteObject.createNewLog();
    }

    public String[] readLog() {
	/** remote method invocation to Skeleton **/
    		return remoteObject.readLog();
    }
}
