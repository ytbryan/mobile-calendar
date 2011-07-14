package nus.icreate.mtimetable.client;

import org.adamtacy.client.ui.effects.impl.Fade;
import org.adamtacy.client.ui.effects.impl.SlideLeft;
import org.adamtacy.client.ui.effects.impl.SlideRight;

import nus.icreate.mtimetable.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;


public class MobileTimetable implements EntryPoint {
	
	Label updateDetail1 = new Label("mobile timetable v0.1a released!");
	Label updateDetail2 = new Label("We are taking part in iCreate 2011");
	Label updateDetail3 = new Label("//team alpha");
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final WebServiceAsync greetingService = GWT
			.create(WebService.class);


	 Timer t = new Timer() {
	   	  int counter = 0;
	      public void run() {
	    	  if(counter == 0){
	    		  counter++;
	    		  updateDetail1.setText("Share your timetable with your teammate!");
	    	  }
	    	  else if(counter == 1){
	    		  counter++;
	    		  updateDetail1.setText("View your schedule from any smart phone");
	    	  }
	    	  else if(counter == 2){
	    		  updateDetail1.setText("If you find this useful, vote for us!");
	    		  counter = 0;
	    	  }
	      }
	    };
	    
	public void onModuleLoad() 
	{
		//showLogin(false);
		showCalendar(true);
	}

	private void showLogin(boolean GoToRight)
	{
		   VerticalPanel vp = new VerticalPanel();
		   vp.setStylePrimaryName("Panel");
		   
		   //Image navBar = new Image("img/navBar.png");
		   FocusPanel row = new FocusPanel();
		   row.setStylePrimaryName("PanelRow");
		
		   vp.add(row);
		   Label userid = new Label("UserID:");
		   userid.setStyleName("GreyText");
		   vp.add(userid);
		   
		   TextBox idTextBox = new TextBox();
		   idTextBox.setHeight("20px");
		   idTextBox.setWidth("200px");
		   vp.add(idTextBox);
		   Label password = new Label("Password:");
		   password.setStyleName("GreyText");
		   vp.add(password);
		   PasswordTextBox pwTextBox = new PasswordTextBox();
		   pwTextBox.setHeight("20px");
		   pwTextBox.setWidth("200px");
		   vp.add(pwTextBox);
		  		   
		   Image login = new Image("img/login.png");
		   login.addClickListener(new ClickListener() 
		   {
			      public void onClick(Widget sender) 
			      {
			    	   RootPanel.get().remove(0);
			    	   showCalendar(true);
			    	   Window.scrollTo(Window.getScrollLeft(),Window.getScrollTop());
			      }
			});
		 
		   vp.add(login);
		   FocusPanel fp2 = new FocusPanel();
		   fp2.setHeight("20px");
		   vp.add(fp2);

		   //do some animation for this
		   Label update = new Label("Update:");
		   update.setStyleName("GreyText");

//		   final Fade theFade = new Fade();
//		   theFade.setEffectElement(updateDetail1.getElement());
//		   theFade.setDuration(3);
//		   theFade.setLooping(true);
//		   theFade.play();
//		   t.scheduleRepeating(3000);		   
//		   vp.add(update);
//		   vp.add(updateDetail1);
//		   vp.add(updateDetail2);
//		   vp.add(updateDetail3);

		  
		   FocusPanel fp = new FocusPanel();
		   fp.setHeight("170px");
		   vp.add(fp);
		   RootPanel.get().add(vp);
		   
		   if(GoToRight) 
			{
			   SlideRight rightSlide = new SlideRight();
			   rightSlide.setEffectElement(vp.getElement());
			   rightSlide.setDuration(0.5);
			   rightSlide.play(); 
			}

	}
	
	private void showCalendar(boolean GoToLeft)
	{
		   VerticalPanel vp = new VerticalPanel();
		   vp.setStylePrimaryName("Panel");
		   
		   FocusPanel row = new FocusPanel();
		   row.setStylePrimaryName("PanelRow");

		   Image img = new Image("img/backbutton3.png");
		   img.addClickListener(new ClickListener() 
		   {
			      public void onClick(Widget sender) 
			      {
			    	  RootPanel.get().remove(0);
			    	   showLogin(true);
			      }
			    });
		 
		   row.add(img);
		   vp.add(row);
		   final DatePicker dp = new DatePicker();
		   TimeTable tt = new TimeTable();
		   tt.setWidth("320px");
		   dp.setHeight("160px");
		   dp.setWidth("320px");
		   vp.add(tt);
		   ScrollPanel sp = new ScrollPanel();
		   sp.setTouchScrollingDisabled(false);
		   sp.setWidth("320px");
		   sp.setHeight("120px");
		   VerticalPanel vp2 = new VerticalPanel();
		   
		   ClickListener listener = new ClickListener()
		   {
		       public void onClick(Widget sender)
		       {
			      RootPanel.get().remove(0);
		    	  showEvent(true);
		       }
		   };
		   Event event = new Event();
		   event.addListener(listener);

		   Button h2 = new Button("Go to Event2", listener);
		   Button h3= new Button("Go to Event3", listener);
		   Button h4 = new Button("Go to Event4", listener);
		   Button h5 = new Button("Go to Event5", listener);
		   Button h6 = new Button("Go to Event6", listener);

		   vp2.add(event);
		   vp2.add(h2);
		   vp2.add(h3);
		   vp2.add(h4);
		   vp2.add(h5);
		   vp2.add(h6);

		   sp.add(vp2);
		   FocusPanel spacePanel = new FocusPanel();
		   spacePanel.setHeight("100px");
		   spacePanel.setWidth("320px");
		   vp.add(sp);
		   vp.add(spacePanel);

		   RootPanel.get().add(vp);

		   Fade theFade = new Fade(vp.getElement());
		   slide(GoToLeft, vp);

	}
	
	private void showEvent(boolean GoToLeft)
	{
		   VerticalPanel vp = new VerticalPanel();
		   vp.setStylePrimaryName("EventDetailsPanel");
		   
		   FocusPanel row = new FocusPanel();
		   row.setStylePrimaryName("PanelRow");

		   Image img = new Image("img/backbutton3.png");
		   img.addClickListener(new ClickListener() 
		   {
			      public void onClick(Widget sender) 
			      {
			    	  RootPanel.get().remove(0);
			    	  showCalendar(false); // false to turn left
			      }
		   });
		 
		   row.add(img);
		   vp.add(row);
		   HTMLPanel event = new HTMLPanel("This is Event Details");
		   FocusPanel html = new FocusPanel();
		   html.setStyleName("DetailPanel");
		   html.add(event);
		   
		   HTMLPanel event2 = new HTMLPanel("This is Event Details");
		   FocusPanel html2 = new FocusPanel();
		   html2.setStyleName("DetailPanel");
		   html2.add(event2);
		   
		   Label event3 = new Label("This is Event Details");
		   FocusPanel html3 = new FocusPanel();
		   html3.setStyleName("DetailPanel");
		   html3.add(event3);
		   
		   vp.add(html);
		   vp.add(html2);
		   vp.add(html3);
		   
		   ScrollPanel sp = new ScrollPanel();
		   sp.setWidth("320px");
		   sp.setHeight("200px");
		   VerticalPanel vp2 = new VerticalPanel();
		  		  
		   RootPanel.get().add(vp);

		   Fade theFade = new Fade(vp.getElement());
		   slide(GoToLeft, vp);
		  
	}
	
	//show preference to set your settings
	private void showSetting()
	{
		   VerticalPanel vp = new VerticalPanel();
		   vp.setStylePrimaryName("SettingPanel");
		   vp.add(new Label("Setting"));

//		   FocusPanel row = new FocusPanel();
//		   row.setStylePrimaryName("PanelRow");
//		   Button backButton = new Button("Logout");
//		   row.add(backButton);
//		   vp.add(row);
//		   DatePicker dp = new DatePicker();
//		   dp.setHeight("180px");
//		   dp.setWidth("320px");
//		   vp.add(dp);
//		   ScrollPanel sp = new ScrollPanel();
//		   sp.setWidth("320px");
//		   sp.setHeight("200px");
//		   VerticalPanel vp2 = new VerticalPanel();
//		   sp.add(vp2);
//		   vp.add(sp);
		   
		   RootPanel.get().add(vp);
		   //  Add a Fade effect to the button
		   Fade theFade = new Fade(vp.getElement());
		   
		   SlideLeft theSlide = new SlideLeft(vp.getElement());
		   //theSlide.setYValue(-450);
		  //theSlide.setXValue(-450);

		   theSlide.setUpEffect();
//		   theSlide.setDisplayOutsideBounds(false);
		   
		   //theSlide.setLooping(true);
		   //theSlide.resumeBackwards();
		   theSlide.setDuration(0.5);
		   // Fade the button
		   theSlide.play();
	}

	//dashboard to other applications
	private void showDashBoard()
	{
		//TODO: other applications can include, 
	}

	//the slide animation
	private void slide(boolean GoToLeft, VerticalPanel vp)
	{
		if(GoToLeft){
			   SlideLeft theSlide = new SlideLeft(vp.getElement());		   
			   theSlide.setDuration(0.5);
			   theSlide.play();
		   }
		   else{
			   SlideRight theSlide = new SlideRight(vp.getElement());		   
			   theSlide.setDuration(0.5);
			   theSlide.play(); 
		   }
		
	}

//    public void onModuleLoad() {
//	final Button sendButton = new Button("Send");
//	final Button sendButton2 = new Button("Click me");
//
//	final TextBox nameField = new TextBox();
//	nameField.setText("GWT User");
//	final Label errorLabel = new Label();
//
//	// We can add style names to widgets
//	sendButton.addStyleName("sendButton");
//
//	// Add the nameField and sendButton to the RootPanel
//	// Use RootPanel.get() to get the entire body element
//	RootPanel.get("nameFieldContainer").add(nameField);
//	RootPanel.get("sendButtonContainer").add(sendButton);
//	RootPanel.get("sendButtonContainer").add(sendButton2);
//
//	RootPanel.get("errorLabelContainer").add(errorLabel);
//
//	// Focus the cursor on the name field when the app loads
//	nameField.setFocus(true);
//	nameField.selectAll();
//
//	// Create the popup dialog box
//	final DialogBox dialogBox = new DialogBox();
//	dialogBox.setText("Remote Procedure Call");
//	dialogBox.setAnimationEnabled(true);
//	final Button closeButton = new Button("Close");
//	// We can set the id of a widget by accessing its Element
//	closeButton.getElement().setId("closeButton");
//	final Label textToServerLabel = new Label();
//	final HTML serverResponseLabel = new HTML();
//	VerticalPanel dialogVPanel = new VerticalPanel();
//	dialogVPanel.addStyleName("dialogVPanel");
//	dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//	dialogVPanel.add(textToServerLabel);
//	dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
//	dialogVPanel.add(serverResponseLabel);
//	dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//	dialogVPanel.add(closeButton);
//	dialogBox.setWidget(dialogVPanel);
//
//	// Add a handler to close the DialogBox
//	closeButton.addClickHandler(new ClickHandler() {
//		public void onClick(ClickEvent event) {
//			dialogBox.hide();
//			sendButton.setEnabled(true);
//			sendButton.setFocus(true);
//		}
//	});
//
//	// Create a handler for the sendButton and nameField
//	class MyHandler implements ClickHandler, KeyUpHandler {
//		/**
//		 * Fired when the user clicks on the sendButton.
//		 */
//		public void onClick(ClickEvent event) {
//			sendNameToServer();
//		}
//
//		/**
//		 * Fired when the user types in the nameField.
//		 */
//		public void onKeyUp(KeyUpEvent event) {
//			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//				sendNameToServer();
//			}
//		}
//
//		/**
//		 * Send the name from the nameField to the server and wait for a response.
//		 */
//		private void sendNameToServer() {
//			// First, we validate the input.
//			errorLabel.setText("");
//			String textToServer = nameField.getText();
//			if (!FieldVerifier.isValidName(textToServer)) {
//				errorLabel.setText("Please enter at least four characters");
//				return;
//			}
//
//			// Then, we send the input to the server.
//			sendButton.setEnabled(false);
//			textToServerLabel.setText(textToServer);
//			serverResponseLabel.setText("");
//			greetingService.greetServer(textToServer,
//					new AsyncCallback<String>() 
//					{
//						public void onFailure(Throwable caught) {
//							// Show the RPC error message to the user
//							dialogBox
//									.setText("Remote Procedure Call - Failure");
//							serverResponseLabel
//									.addStyleName("serverResponseLabelError");
//							serverResponseLabel.setHTML(SERVER_ERROR);
//							dialogBox.center();
//							closeButton.setFocus(true);
//						}
//
//						public void onSuccess(String result) {
//							dialogBox.setText("Remote Procedure Call");
//							serverResponseLabel
//									.removeStyleName("serverResponseLabelError");
//							serverResponseLabel.setHTML(result);
//							dialogBox.center();
//							closeButton.setFocus(true);
//						}
//					});
//		}
//	}
//
//	// Add a handler to send the name to the server
//	MyHandler handler = new MyHandler();
//	sendButton.addClickHandler(handler);
//	nameField.addKeyUpHandler(handler);
//}


}
