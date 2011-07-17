package nus.icreate.mtimetable.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface WebServiceAsync 
{
	void getMyOrganizerEventServer(String input, AsyncCallback<String> callback)
	throws IllegalArgumentException;
	void getTimeTableStudentServer(String input, AsyncCallback<String> callback)
	throws IllegalArgumentException;
	void authenticationServer(String input, AsyncCallback<String> callback)
	throws IllegalArgumentException;
}
