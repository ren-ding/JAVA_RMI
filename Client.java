// title		Distributed System assignment2
// author	Ren DING
// ID		a1202524

/*
 * Client class: this class create a Stub object and a Scanner class for user input
 */
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

		Stub logger = new Stub();
		/** You will explicitly create a Stub rather than using
	    a registry **/

		//create a Scanner to read user's input
		Scanner in = new Scanner (System.in);
		boolean loop = true;
		String nextInput;
		
		System.out.println("The operation command are:");
		System.out.println("newlog : create a new log on the server.");
		System.out.println("readlog : get current entering from the server");
		System.out.println("stop : end this client");
		System.out.println("other string will add the message to the server");

		// In while the loop,different method calls depend on user's entering
		while (loop) {
			nextInput = in.next();
			if (nextInput.equals("newlog")) {
				//create a new log on the server
				System.out.println("Calling newlog command, a new log create on the server...");
				logger.createNewLog();
			} else if (nextInput.equals("readlog")) {
				//get read log result from the server 
				String[] strs = logger.readLog();
				System.out.println("Calling readlog command, your current log is below:");
				for (String s : strs) {
					System.out.println(s);
				}
			} else if (nextInput.equals("stop")) {
				// jump out of the loop
				System.out.println("Calling stop command, your client will be stop...");
				loop = false;
			} else {
				//add message to server
				logger.addLogMessage(nextInput);
			}
		}
    }
}
