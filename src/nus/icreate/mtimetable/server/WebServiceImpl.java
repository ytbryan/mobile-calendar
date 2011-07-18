package nus.icreate.mtimetable.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nus.icreate.mtimetable.client.Result;
import nus.icreate.mtimetable.client.WebService;
import nus.icreate.mtimetable.shared.FieldVerifier;

//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonParser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WebServiceImpl extends RemoteServiceServlet implements
		WebService {
	
	String apikey = "FH3S42OIEnEyN1tEgHs7m";
	String authtoken ="";
	
	public String authenticationServer(String input) throws IllegalArgumentException {
		
		//retrieve the 416-characters token
		authtoken = input.substring((input.length()-416),input.length());

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		
		String inputLine = "";
        String answer ="";
		 try {
			
	            URL yahoo = new URL("https://ivle.nus.edu.sg/api/Lapi.svc/Validate?APIKey=FH3S42OIEnEyN1tEgHs7m&Token=" + authtoken +"&output=json");

	            URLConnection yc = yahoo.openConnection();
	            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	            while ((inputLine = in.readLine()) != null) 
	            {
	            	answer +=inputLine;
	                System.out.println(inputLine);
	            }
	            in.close();
	            
	        } 
		 	catch (MalformedURLException e) 
	        {
	            e.printStackTrace();
	            return null;
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            return null;
	        }
	        
		return answer;
	}

	public String getMyOrganizerEventServer(String input) throws IllegalArgumentException 
	{
		authtoken = input.substring((input.length()-416),input.length());

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		
		 String inputLine = "";
         String answer ="";

	        try {
	            //new code
	            URL yahoo = new URL("https://ivle.nus.edu.sg/api/Lapi.svc/MyOrganizer_Events?APIKey="+apikey+"&AuthToken="+authtoken+"&StartDate=19/06/2011&EndDate=30/07/2011&output=json");

	            URLConnection yc = yahoo.openConnection();
	            BufferedReader in = new BufferedReader(
	                                    new InputStreamReader(
	                                    yc.getInputStream()));

	            while ((inputLine = in.readLine()) != null) {
	            	answer +=inputLine;
	                System.out.println(inputLine);
	            }

	            in.close();
	            
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	  return answer;
	}
	
	public String getTimeTableStudentServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello getTimeTableStudentServer"+ getTimeTableStudent();
	}
	
	public String getMyOrganizerEvent()
	{
        String inputLine = "";
        String answer ="";

        try {
            //new code
            URL yahoo = new URL("https://ivle.nus.edu.sg/api/Lapi.svc/MyOrganizer_Events?APIKey="+apikey+"&AuthToken="+authtoken+"&StartDate=19/06/2011&EndDate=30/07/2011&output=json");

            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    yc.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
            	answer +=inputLine;
                System.out.println(inputLine);
            }

            in.close();
            
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer;
	}
	
	public String getTimeTableStudent()
	{
        String inputLine = "";
        String answer ="";

        try {
            //new code
            URL yahoo = new URL("https://ivle.nus.edu.sg/api/Lapi.svc/Timetable_Student?APIKey="+apikey+"&AuthToken="+authtoken+"&AcadYear=2010/2011&Semester=1&output=json");

            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    yc.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
            	answer +=inputLine;
                System.out.println(inputLine);
            }

            in.close();
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer;
	}

	public String greetServer(String input) throws IllegalArgumentException 
	{
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) 
		{
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		
		return callOut();
	}
	
	public String callOut()
	{
        String inputLine = "";
        String answer ="";

        try {
            //new code
            URL yahoo = new URL("https://ivle.nus.edu.sg/api/Lapi.svc/MyOrganizer_Events?APIKey=FH3S42OIEnEyN1tEgHs7m&AuthToken=D7819B738AC42B63F98B2D7E83E7235338E9873446AC7A611B8B46B13B22344405028D76E26E11B26CF54A0E8DA9400E79C6D7D0667A2353D0C012E0B0F4728D21DF0DAECB43304B188C3E8803DF5387517BCE0378C54459829D512793A9E345DDBAAD38EF76C86C09B387CA8360255B5F9F50322513EEFFCBC4DA277DDADF13EA3409FED839D7B3BE99605B18775B747B42287B7A522C69A6A0FEC44FE1D754AD7E22C7F37F83D2B24E7346CEDD16396D6127744394C3399A9183D840E1C853278594A06D6F9D7289F40C5450AA7270&StartDate=19/06/2011&EndDate=30/07/2011&output=json");

            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    yc.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
            	answer +=inputLine;
                System.out.println(inputLine);
            }

            in.close();
            
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer;
	}
	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escapez
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
