// title		Distributed System assignment2
// author	Ren DING
// ID		a1202524

/*
 *  Logging Server creates a new Logger remote object,
 *  and a skeleton that can receive remote logging
 *  requests from clients.
 */

public class LoggingServer {
    
    public LoggingServer() {
		/** create a Logger remote object **/
		Logger logRemoteObject = new Logger();

		/** Create a skeleton **/
		Skeleton skel = new Skeleton();

		/** register the remote object with the skeleton
		 so that it can pass on remote method invocation
		 requests to it.
		 **/
		skel.bind(logRemoteObject);

		/** start the skeleton running, so that it can not
		 receive method invocation requests.
		 **/
		skel.run();
    }
}
    
