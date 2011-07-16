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
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
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
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;


public class MobileTimetable implements EntryPoint 
{

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


	Timer t = new Timer() 
	{
		int counter = 0;
		public void run() 
		{
			if(counter == 0)
			{
				counter++;
				updateDetail1.setText("Share your timetable with your teammate!");
			}
			else if(counter == 1)
			{
				counter++;
				updateDetail1.setText("View your schedule from any smart phone");
			}
			else if(counter == 2)
			{
				updateDetail1.setText("If you find this useful, vote for us!");
				counter = 0;
			}
		}
	};

	public void onModuleLoad() 
	{
		showCalendar(true);
	}

	private void showLogin(boolean GoToRight)
	{
		VerticalPanel vp = new VerticalPanel();
		vp.setStylePrimaryName("Panel");

		FocusPanel row = new FocusPanel();
		row.setStylePrimaryName("PanelRow");

		vp.add(row);
		Label userid = new Label("UserID:");
		userid.setStyleName("GreyText");
		vp.add(userid);

		TextBox idTextBox = new TextBox();
		idTextBox.setHeight("20px");
		idTextBox.setWidth("280px");
		idTextBox.setStyleName("TextBox");
		vp.add(idTextBox);
		Label password = new Label("Password:");
		password.setStyleName("GreyText");
		vp.add(password);
		PasswordTextBox pwTextBox = new PasswordTextBox();
		pwTextBox.setHeight("20px");
		pwTextBox.setWidth("280px");
		pwTextBox.setStyleName("TextBox");

		vp.add(pwTextBox);

		final Image login = new Image("img/login.png");

		login.addTouchStartHandler(new TouchStartHandler()
		{
			@Override
			public void onTouchStart(TouchStartEvent event) 
			{
				login.setUrl("img/login_pressed.png");
			}			   
		});

		login.addTouchEndHandler(new TouchEndHandler()
		{
			@Override
			public void onTouchEnd(TouchEndEvent event) 
			{
				login.setUrl("img/login.png");
			}				   
		});

		login.addMouseListener(new MouseListener() 
		{
			@Override
			public void onMouseDown(Widget sender, int x, int y) {
				login.setUrl("img/login_pressed.png");
			}

			@Override
			public void onMouseUp(Widget sender, int x, int y) {
				login.setUrl("img/login.png");
				RootPanel.get().remove(0);
				showCalendar(true);
				Window.scrollTo(Window.getScrollLeft(),Window.getScrollTop());				
			}

			@Override
			public void onMouseEnter(Widget sender) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onMouseLeave(Widget sender) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onMouseMove(Widget sender, int x, int y) {
				// TODO Auto-generated method stub
				
			}
		});
		HorizontalPanel box = new HorizontalPanel();
		box.add(login);
		Image spinner = new Image("img/spinner.png");
		box.add(spinner);
		
		vp.add(box);
		FocusPanel fp2 = new FocusPanel();
		fp2.setHeight("20px");
		vp.add(fp2);

		//do some animation for this
		Label update = new Label("Update:");
		update.setStyleName("GreyText");

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

		final Image img = new Image("img/backbutton3.png");
		img.addTouchStartHandler(new TouchStartHandler()
		{
			@Override
			public void onTouchStart(TouchStartEvent event) 
			{
				img.setUrl("img/backbutton_pressed.png");
			}				   
		});

		img.addTouchEndHandler(new TouchEndHandler()
		{
			@Override
			public void onTouchEnd(TouchEndEvent event) 
			{
				img.setUrl("img/backbutton.png");
			}				   
		});

		img.addMouseListener(new MouseListener() 
		{
			@Override
			public void onMouseDown(Widget sender, int x, int y) {
				img.setUrl("img/backbutton_pressed.png");

			}
		
			@Override
			public void onMouseUp(Widget sender, int x, int y) {
				img.setUrl("img/backbutton3.png");
				RootPanel.get().remove(0);
				showLogin(true);					
			}
			
			@Override
			public void onMouseEnter(Widget sender) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseLeave(Widget sender) 
			{
				// TODO Auto-generated method stub

			}
			@Override
			public void onMouseMove(Widget sender, int x, int y) {
				// TODO Auto-generated method stub

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
				//showTeamLogin(true);
			}
		};
		HTMLPanel eachPanelEnd = new HTMLPanel("");
		eachPanelEnd.setSize("320px", "5px");
		eachPanelEnd.setStyleName("EachPanelEnd");
		
		HTMLPanel eachPanelEnd2 = new HTMLPanel("");
		eachPanelEnd2.setSize("320px", "5px");
		eachPanelEnd2.setStyleName("EachPanelEnd");
		
		Event event = new Event();
		event.addListener(listener);
		event.setContent("Tutorial", "IT1002,3-4pm,LT4");
		Event event2 = new Event();
		event2.addListener(listener);
		event2.setContent("Lecture", "IT1002,3-4pm,LT4");

		Event event3 = new Event();
		event3.addListener(listener);
		event3.setContent("Labs", "MA5001,4-6pm,SA102-212");

		Event event4 = new Event();
		event4.addListener(listener);
		event3.setContent("Labs", "MA5001,4-6pm,SA102-212");

		
		Event event5 = new Event();
		event5.addListener(listener);
		event3.setContent("Labs", "MA5001,4-6pm,SA102-212");

		
		Event event6 = new Event();
		event6.addListener(listener);
		event3.setContent("Labs", "MA5001,4-6pm,SA102-212");

		vp2.add(eachPanelEnd);
		vp2.add(event);
		vp2.add(event2);
		vp2.add(event3);
		vp2.add(event4);
		vp2.add(event5);
		vp2.add(event6);
		vp2.add(eachPanelEnd2);
		
		sp.add(vp2);
		FocusPanel spacePanel = new FocusPanel();
		spacePanel.setHeight("100px");
		spacePanel.setWidth("320px");
		
		final Image next = new Image("img/next.png");
		next.setStyleName("button");
		next.addClickListener(new ClickListener() {
			
			@Override
			public void onClick(Widget sender) 
			{
				RootPanel.get().remove(0);
				showTeamLogin(true);
			}
		});
		next.addTouchStartHandler(new TouchStartHandler()
		{
			@Override
			public void onTouchStart(TouchStartEvent event) 
			{
				next.setStyleName("button_pressed");
			}			   
		});

		next.addTouchEndHandler(new TouchEndHandler()
		{
			@Override
			public void onTouchEnd(TouchEndEvent event) 
			{
				next.setStyleName("button");
			}				   
		});
		
		vp.add(next);
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

		final Image img = new Image("img/backbutton3.png");
		img.addTouchStartHandler(new TouchStartHandler(){

			@Override
			public void onTouchStart(TouchStartEvent event) 
			{
				img.setUrl("img/backbutton_pressed.png");
			}


		});

		img.addTouchEndHandler(new TouchEndHandler()
		{
			@Override
			public void onTouchEnd(TouchEndEvent event) 
			{
				img.setUrl("img/backbutton.png");
			}				   
		});


		img.addMouseListener(new MouseListener() 
		{
			@Override
			public void onMouseDown(Widget sender, int x, int y) {
				img.setUrl("img/backbutton_pressed.png");

			}
			
			@Override
			public void onMouseUp(Widget sender, int x, int y) 
			{
				img.setUrl("img/backbutton3.png");
				RootPanel.get().remove(0);
				showCalendar(true);					
			}
			@Override
			public void onMouseEnter(Widget sender) {}
			@Override
			public void onMouseLeave(Widget sender) {}
			@Override
			public void onMouseMove(Widget sender, int x, int y) {}
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

	private void showTeamLogin(boolean GoToLeft)
	{
		VerticalPanel vp = new VerticalPanel();
		vp.setStylePrimaryName("EventDetailsPanel");

		FocusPanel row = new FocusPanel();
		row.setStylePrimaryName("PanelRow");

		final Image img = new Image("img/backbutton3.png");
		img.addMouseListener(new MouseListener() 
		{
			@Override
			public void onMouseDown(Widget sender, int x, int y) 
			{
				img.setUrl("img/backbutton_pressed.png");
			}
			
			@Override
			public void onMouseUp(Widget sender, int x, int y) 
			{
				img.setUrl("img/backbutton3.png");
				RootPanel.get().remove(0);
				showCalendar(true);					
			}
			@Override
			public void onMouseEnter(Widget sender) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseLeave(Widget sender) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseMove(Widget sender, int x, int y) {
				// TODO Auto-generated method stub
			}
		});

		row.add(img);
		vp.add(row);
		
		//user1
		Label userid = new Label("User1 ID:");
		userid.setStyleName("GreyText");
		vp.add(userid);
		TextBox idTextBox = new TextBox();
		idTextBox.setHeight("20px");
		idTextBox.setWidth("280px");
		idTextBox.setStyleName("TextBox");
		vp.add(idTextBox);
		Label password = new Label("User Password:");
		password.setStyleName("GreyText");
		vp.add(password);
		PasswordTextBox pwTextBox = new PasswordTextBox();
		pwTextBox.setHeight("20px");
		pwTextBox.setWidth("280px");
		pwTextBox.setStyleName("TextBox");
		vp.add(pwTextBox);
		
		//user2
		Label userid2 = new Label("User2 UserID:");
		userid2.setStyleName("GreyText");
		vp.add(userid2);
		TextBox idTextBox2 = new TextBox();
		idTextBox2.setHeight("20px");
		idTextBox2.setWidth("280px");
		idTextBox2.setStyleName("TextBox");
		vp.add(idTextBox2);
		Label password2 = new Label("User2 Password:");
		password2.setStyleName("GreyText");
		vp.add(password2);
		PasswordTextBox pwTextBox2 = new PasswordTextBox();
		pwTextBox2.setHeight("20px");
		pwTextBox2.setWidth("280px");
		pwTextBox2.setStyleName("TextBox");
		vp.add(pwTextBox2);
		
		//user3
		Label userid3 = new Label("User3 ID:");
		userid3.setStyleName("GreyText");
		vp.add(userid3);
		TextBox idTextBox3 = new TextBox();
		idTextBox3.setHeight("20px");
		idTextBox3.setWidth("280px");
		idTextBox3.setStyleName("TextBox");
		vp.add(idTextBox3);
		Label password3 = new Label("User3 Password:");
		password3.setStyleName("GreyText");
		vp.add(password3);
		PasswordTextBox pwTextBox3 = new PasswordTextBox();
		pwTextBox3.setHeight("20px");
		pwTextBox3.setWidth("280px");
		pwTextBox3.setStyleName("TextBox");
		vp.add(pwTextBox3);
		
		RootPanel.get().add(vp);

		Fade theFade = new Fade(vp.getElement());
		slide(GoToLeft, vp);

	}

	private void showCombinedTimeTable(boolean GoToLeft)
	{
		VerticalPanel vp = new VerticalPanel();
		vp.setStylePrimaryName("EventDetailsPanel");

		FocusPanel row = new FocusPanel();
		row.setStylePrimaryName("PanelRow");

		final Image img = new Image("img/backbutton3.png");
		img.addMouseListener(new MouseListener() 
		{
			@Override
			public void onMouseDown(Widget sender, int x, int y) {
				img.setUrl("img/backbutton_pressed.png");
			}
			
			@Override
			public void onMouseUp(Widget sender, int x, int y) {
				img.setUrl("img/backbutton3.png");
				RootPanel.get().remove(0);
				showLogin(true);					
			}
			@Override
			public void onMouseEnter(Widget sender) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseLeave(Widget sender) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseMove(Widget sender, int x, int y) {
				// TODO Auto-generated method stub
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
		theSlide.setUpEffect();
		theSlide.setDuration(0.5);
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
		if(GoToLeft)
		{
			SlideLeft theSlide = new SlideLeft(vp.getElement());		   
			theSlide.setDuration(0.5);
			theSlide.play();
		}
		else
		{
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
