package nus.icreate.mtimetable.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface WebService extends RemoteService 
{
	String getMyOrganizerEventServer(String name) throws IllegalArgumentException;
	String getTimeTableStudentServer(String name) throws IllegalArgumentException;
	String authenticationServer(String name) throws IllegalArgumentException;

}
