// title		Distributed System assignment2
// author	Ren DING
// ID		a1202524

import java.io.*;
import java.lang.reflect.*;
/*
 * This class is responsible for marshalling and unmashalling
 * The marshalled Objects could be methodRequest, methodResponse, or YourRemoteRef
 * The YourRemoteRef is a reference of the remote object which implements YourRemote,
 * therefore, it is passed by reference.
 * Both methodRequest and methodResponse implement from Serializable, so they are
 * passed by value
 */

public class Marshalling {
	// write the object into the output stream
	public void marshalling(Object obj,ObjectOutputStream out) throws IOException {	
		Class<?>[] aaa = obj.getClass().getInterfaces();
		for	(Class<?> c:aaa) {
			// check if the object implements from Serializable or not
			if( c.equals(YourRemote.class) ) {
				//call by reference
				YourRemoteRef tmp = (YourRemoteRef)obj;
				MethodRequest methodrqt = new MethodRequest(tmp.getID(),null,null);
				out.writeObject(methodrqt);
			} else {
				//call by value
				out.writeObject(obj);
			}
		}
	}

	//read the serializable object from the input stream
	public Object unmarshalling(ObjectInputStream in) throws IOException, ClassNotFoundException {
		return in.readObject();
	}
}
