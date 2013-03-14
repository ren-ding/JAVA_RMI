// title	Distributed System assignment2
// author	Ren DING
// ID		a1202524

/*
 *  This class is a method request class.
 *  the MethodRequest object will be sent from stub to skeleton
 */
import java.io.*;

public class MethodRequest implements Serializable {
	private int loggerID;
	private String methodName;
	private String message;
	
	//Constructor, it contains a loggerID and two String Object
	public MethodRequest(int loggerID, String methodName, String message) {
		this.loggerID = loggerID;
		this.methodName = methodName;
		this.message = message;
	}
	
	//get the LoggerID
	public int getLoggerID() {
		return this.loggerID;
	}
	
	//get method name
	public String getMethodName() {
		return methodName;
	}
	
	//get the parameter(client message)
	public String getMessage() {
		return message;
	}
}
