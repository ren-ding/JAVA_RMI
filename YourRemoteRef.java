// title		Distributed System assignment2
// author	Ren DING
// ID		a1202524

/*
 *  YourRemoteRef class, This class is a referene of the remote object
 *  it is used to represent a remote object connection and so will 
 *  have to encapsulate all of the information needed to identify a remote connection,
 *  i.e. a hostname, port number and a class name. 
 *  More advanced systems, i.e. that use a registry will also use a unique identifier 
 *  for each remote object. 
 */

import java.net.*;
import java.io.*;

public class YourRemoteRef implements YourRemote {
	
	private int id;// each id Correspond to a remote object
	private String hostName = "localhost";
	private int portNumber = 2000;
	
	public YourRemoteRef() {
		id = 0;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	//send request to execute addLogMessage method on the server part
	//on the server, the message will be added to the logger(the remote object)
	public void addLogMessage( String message) {
		try {
			Socket s = new Socket(hostName,portNumber);
			
			MethodRequest request = new MethodRequest(this.id,"addLogMessage",message);
			ObjectOutputStream objOutputStream = new ObjectOutputStream(s.getOutputStream());
			Marshalling marshaller = new Marshalling();
			marshaller.marshalling(request,objOutputStream);
			
		} catch(IOException ioe) {
			System.out.println("IO - addLogMessage: " + ioe.getMessage());
		}
	}
	
	//send request to execute createNewLog method on the server part
    public void createNewLog() {
    		try {
			Socket s = new Socket(hostName,portNumber);
			
			MethodRequest request = new MethodRequest(this.id,"createNewLog",null);
			ObjectOutputStream objOutputStream = new ObjectOutputStream(s.getOutputStream());
			Marshalling marshaller = new Marshalling();
			marshaller.marshalling(request,objOutputStream);
		} catch(IOException ioe) {
			System.out.println("IO - createNewLog: " + ioe.getMessage());
		}
    }
	
    //send request to execute readLog method on the server part
    public String[] readLog() {
		try {
			Socket s = new Socket(hostName,portNumber);
			
			MethodRequest request = new MethodRequest(this.id,"readLog",null);
			ObjectOutputStream objOutputStream = new ObjectOutputStream(s.getOutputStream());
			Marshalling marshaller = new Marshalling();
			marshaller.marshalling(request,objOutputStream);
		
    			ObjectInputStream objInputStream = new ObjectInputStream(s.getInputStream());
			MethodResponse response = (MethodResponse)(marshaller.unmarshalling(objInputStream));
			return response.getLog();
		} catch(IOException ioe) {
			System.out.println("IO - readLog: " + ioe.getMessage());
			return null;
		} catch(ClassNotFoundException cnfe) {
			System.out.println("Class not found in readLog function");
			return null;
		}
    }
}
