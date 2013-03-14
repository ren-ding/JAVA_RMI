// title		Distributed System assignment2
// author	Ren DING
// ID		a1202524

/*
 *  This class is a method response class.
 *  the Method Response object will be sent from skeleton to stub
 */
import java.io.*;

public class MethodResponse implements Serializable {
	private int loggerID;
	private String methodName;
	private String[] log;
	
	//Constructor, it contains a loggerID, a String Object and a String array
	public MethodResponse(int loggerID, String methodName, String[] log) {
		this.loggerID = loggerID;
		this.methodName = methodName;
		this.log = log;
	}
	
	//get the LoggerID
	public int getLoggerID() {
		return loggerID;
	}
	
	//get the method name
	public String getMethodName() {
		return methodName;
	}
	
	//get the parameter(current log)
	public String[] getLog() {
		return log;
	}
}
