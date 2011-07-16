package nus.icreate.mtimetable.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface WebServiceAsync 
{
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
