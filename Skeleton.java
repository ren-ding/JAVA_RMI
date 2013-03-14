// title		Distributed System assignment2
// author	Ren DING
// ID		a1202524

/*
 * 	Skeleton class: The Skeleton class that acts as receiver receive 
 * 	the remote object proxy.
 *  The run methods in this class are the methods that are responsible
 *  for performing the remote method invocation response, i.e. they 
 *  response a socket connection to be established with the Stub and 
 *  use the Marshalling class to send back the MethodResponse to the 
 *  Stub.
 */

import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class Skeleton {
	//the remote object reference table
    ArrayList<YourRemote> remoteObjects;
    
    String hostName = "localhost";
    int portNumber = 2000;
    boolean isFirstRemoteObject = true;	//mark the first remote object
    
    // create a skeleton - something to listen for
    // and interpret method invocation requests
    public Skeleton() {
	remoteObjects = new ArrayList<YourRemote>();
    }

    public void bind (YourRemote remoteObject) {
	remoteObjects.add(remoteObject);
	// You should extend this to check whether
	// the remote object has already been bound
    }

    public void run() {
		try {
			//create a ServerSocket to wait for connection
			ServerSocket serverSocket = new ServerSocket(portNumber);
	    		Marshalling marshaller = new Marshalling();
	           
	        // now go into a loop
	        while (true) {
	        		// accept connection requests
				// create a socket connection
	            	Socket clientSocket = serverSocket.accept();
	            	// read a MethodRequest and get the object from the object input stream
	            	ObjectInputStream objInputStream = new ObjectInputStream(clientSocket.getInputStream());
				Object obj = marshaller.unmarshalling(objInputStream);
				MethodRequest methodRequest;
				
				if(obj instanceof MethodRequest) {
					//get a methodRequest object
					methodRequest = (MethodRequest)obj;
					
					//get the detail about the object(YourRemoteRef, methodName and loggerID)
					String methodName = methodRequest.getMethodName();
					int loggerID = methodRequest.getLoggerID();
					
					// deal with the loggerID
					if(methodName == null && loggerID == 0 && isFirstRemoteObject) {
						//the first stub that connects the skeleton.
						//Doing nothing and return it back directly
						MethodResponse responseObj = new MethodResponse(0,null,null);
						ObjectOutputStream objOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
						marshaller.marshalling(responseObj,objOutputStream);
						
						//set isFirstRemoteObject flag false
						isFirstRemoteObject = false;
					} else if(methodName == null && loggerID == 0 && !isFirstRemoteObject) {
						//create a new Logger on server and 
						//add the remote object into the server's table.
						//Distribute the loggerID and return it back to the Stub
						Logger logRemoteObject = new Logger();
						this.bind(logRemoteObject);
						
						//set the remoteObjectRef's ID and send it back
						MethodResponse responseObj = new MethodResponse(remoteObjects.size() - 1,null,null);
						ObjectOutputStream objOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
						marshaller.marshalling(responseObj,objOutputStream);
					} else {
						// Geting the Logger(remote object) that has the same Id of the YourRemoteRef
						// perform the remote method invocation on the server
						YourRemote tmpLogger = remoteObjects.get(loggerID);
						
						if (methodName.equals("addLogMessage")) {
							//add the message to the logger.
							tmpLogger.addLogMessage(methodRequest.getMessage());
						} else if(methodName.equals("createNewLog")) {
							//create a new log, with previous messages stored in a file
							tmpLogger.createNewLog();
						} else if (methodName.equals("readLog")) {
							//return a String[] containing all of the messages contained within the current log.
							String[] log = tmpLogger.readLog();
							
							MethodResponse responseObj = new MethodResponse(loggerID,methodName,log);
							ObjectOutputStream objOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
							marshaller.marshalling(responseObj,objOutputStream);
						}
					}
					
					//close the client socket
					clientSocket.close();
				} else {
					System.out.println("Method request get an unmatched type");
					clientSocket.close();
					return;
				}
	        }
		} catch(SocketException se) {
			System.out.println("SOCKET: " +se.getMessage());
		} catch(IOException ioe) {
			System.out.println("IO - skeleton run: " + ioe.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		    System.out.println("Exception in Skeleton code: " + e.toString());
		}
    }

}
