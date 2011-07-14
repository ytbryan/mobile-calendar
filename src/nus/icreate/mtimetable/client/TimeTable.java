package nus.icreate.mtimetable.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

public class TimeTable extends VerticalPanel
{
	final Label date2 = new Label();
	final DatePicker dp = new DatePicker();
	HorizontalPanel hp = new HorizontalPanel();
	
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	public TimeTable()
	{
		//turn off the stuffs;
		 date2.setText(dp.getCurrentMonth().toString());
		   ClickListener listener = new ClickListener()
		   {
		       public void onClick(Widget sender)
		       {
		    	   if(sender.getTitle().matches("next"))
		    	   {
		    		   Date currentDate = dp.getCurrentMonth();
				      currentDate.setMonth(currentDate.getMonth()+1);
			    	  dp.setCurrentMonth(currentDate);
			    	  date2.setText(dp.getCurrentMonth().toString());
		    	   }
		    	   else if(sender.getTitle().matches("previous"))
		    	   {
		    		  Date currentDate = dp.getCurrentMonth();
				      currentDate.setMonth(currentDate.getMonth()-1);
			    	  dp.setCurrentMonth(currentDate);
			    	  date2.setText(dp.getCurrentMonth().toString());
		    	   }
		    	   else 
		    	   {
		    		   //do nothing
		    	   }
		       }
		   };
		  
		   dp.addValueChangeHandler(new ValueChangeHandler() {
			      public void onValueChange(ValueChangeEvent event) {
			        Date date = (Date) event.getValue();
			        String dateString = DateTimeFormat.getMediumDateFormat().format(date);
			        date2.setText(dateString);
			        sendNameToServer();
			        //change the content

			      }
			    });
		   
		   Image next = new Image("img/next.png");
		   next.setStyleName("button");
		   next.addClickListener(listener);
		   next.setTitle("next");
		   
		   Image previous = new Image("img/previous.png");
		   previous.setStyleName("button");
		   previous.addClickListener(listener);
		   previous.setTitle("previous");
		   
		   hp.add(date2);
		   hp.add(previous);
		   hp.add(next);
		   
			this.add(hp);
			this.add(dp);
	}
	
	   private void sendNameToServer() {
			String textToServer = "1231312";
			hp.add(new Image("img/spinner.gif"));
			greetingService.greetServer(textToServer,
					new AsyncCallback<String>() 
					{
						public void onFailure(Throwable caught) {
							hp.remove(3);
							date2.setText("Failed");
//							// Show the RPC error message to the user
//							dialogBox
//									.setText("Remote Procedure Call - Failure");
//							serverResponseLabel
//									.addStyleName("serverResponseLabelError");
//							serverResponseLabel.setHTML(SERVER_ERROR);
//							dialogBox.center();
//							closeButton.setFocus(true);
						}

						public void onSuccess(String result) {
							hp.remove(3);
							date2.setText("Success");

//							dialogBox.setText("Remote Procedure Call");
//							serverResponseLabel
//									.removeStyleName("serverResponseLabelError");
//							serverResponseLabel.setHTML(result);
//							dialogBox.center();
//							closeButton.setFocus(true);
						}
					});
		}
	
	public void setWidth(String width)
	{
		hp.setSize(width,"20px");
		dp.setSize(width, "160px");
	}
	  
	
}
