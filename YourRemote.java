// title		Distributed System assignment2
// author	Ren DING
// ID		a1202524

/*
 *	YourRemote interface, the stub, the remote object and the remote object reference
 *	shall implements from this interface 
 */

public interface YourRemote {
	public void addLogMessage( String message);
	
    public void createNewLog();
	
    public String[] readLog();	
}
