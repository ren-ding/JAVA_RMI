// title		Distributed System assignment2
// author	Ren DING
// ID		a1202524

/*
 * Logger class: The remote object.
 */

import java.util.*;

public class Logger implements YourRemote {

    ArrayList<String> log;
    static int number;

    public Logger() {
        log = new java.util.ArrayList<String>();
        number = 0;
    }

    //add the message to the logger log.
    public void addLogMessage(String message) {
        System.out.println("Log: " + message);
        log.add(message);
    }

    //create a new log, with previous messages stored in a file
    public void createNewLog() {
        System.out.println("Create a new log");
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter("log" + number + ".log");
            number++;
            for (String s : log)
                writer.println(s);
            writer.close();
            log = new java.util.ArrayList<String>();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    //return a String[] containing all of the messages contained within the current log.
    public String[] readLog() {
        System.out.println("Returning the log");
		String[] currentLog = new String[log.size()];
        currentLog = log.toArray(currentLog);
		return currentLog;
    }
}
