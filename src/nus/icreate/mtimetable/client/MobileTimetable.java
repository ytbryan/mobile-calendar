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
	Image spinner = new Image("img/spinner.png");
	HorizontalPanel box;

	/*
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
		showLogin(true);
	}

	private void showLogin(boolean GoToRight)
	{
		final VerticalPanel vp = new VerticalPanel();
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
		idTextBox.setText("U0904804");
		vp.add(idTextBox);
		Label password = new Label("Password:");
		password.setStyleName("GreyText");
		vp.add(password);
		PasswordTextBox pwTextBox = new PasswordTextBox();
		pwTextBox.setHeight("20px");
		pwTextBox.setWidth("280px");
		pwTextBox.setStyleName("TextBox");
		pwTextBox.setText("hahahahaha");


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
			public void onMouseDown(Widget sender, int x, int y) 
			{
				login.setUrl("img/login_pressed.png");
				box.add(spinner);
				greetingService.authenticationServer("123456",
				new AsyncCallback<String>() 
				{
					public void onFailure(Throwable caught) 
					{
						box.add(new Label("Try Again"));
					}

					public void onSuccess(String result) 
					{
						if(result==null)
						{
							box.remove(1);
							box.add(new Label("Try Again"));
						}
						else{
							box.remove(1);
							RootPanel.get().remove(0);
							showCalendar(true);
						}
					}
				});
			}

			@Override
			public void onMouseUp(Widget sender, int x, int y) 
			{
				login.setUrl("img/login.png");	
			}

			@Override
			public void onMouseEnter(Widget sender) 
			{
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
		box = new HorizontalPanel();
		box.add(login);
		
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
			public void onMouseDown(Widget sender, int x, int y) 
			{
				img.setUrl("img/backbutton_pressed.png");
			}
		
			@Override
			public void onMouseUp(Widget sender, int x, int y) 
			{
				img.setUrl("img/backbutton3.png");
				
				RootPanel.get().remove(0);
				showLogin(true);					
			}
			
			@Override
			public void onMouseEnter(Widget sender) 
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseLeave(Widget sender) 
			{
				// TODO Auto-generated method stub

			}
			@Override
			public void onMouseMove(Widget sender, int x, int y) 
			{
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

		ClickListener listener = new ClickListener()
		{
			public void onClick(Widget sender)
			{
				RootPanel.get().remove(0);
				showEvent(true);
			}
		};
		
		EventPanel vp2 = new EventPanel();
		Event event = new Event(true,listener,"Lecture", "CG3002,1000-1200,LT4");
		Event event2 = new Event(false,listener,"Tutorial", "CG3002,1200-1300,SOC1-202");
		Event event3 = new Event(true,listener,"Labs", "CS1231,1300-1400,SOC1-244");
		Event event4 = new Event(false,listener,"Labs", "CS1280,1400-1500,SOC1-101");
		Event event5 = new Event(true,listener,"Labs", "CS2100,1500-1600,SOC1-122");
		Event event6 = new Event(false,listener,"Labs", "CS2102,1500-1600,SA102-212");

		vp2.add(event);
		vp2.add(event2);
		vp2.add(event3);
		vp2.add(event4);
		vp2.add(event5);
		vp2.add(event6);
		
		sp.add(vp2);
		
		FocusPanel spacePanel = new FocusPanel();
		spacePanel.setHeight("100px");
		spacePanel.setWidth("320px");
		
		Label nextLesson = new Label();
		nextLesson.setText("Next Up MA1101,1500-1600,LT4");
		nextLesson.setStyleName("DateLabel");
				
		final Image next = new Image("img/star.png");
		next.setStyleName("StarButton");
		next.addClickListener(new ClickListener() 
		{
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
		
		HorizontalPanel row2 = new HorizontalPanel();
		row2.setStyleName("NewPanel");
		HTMLPanel p = new HTMLPanel("");
		p.setSize("285px", "2px");
		p.add(nextLesson);
		row2.add(p);
		row2.add(next);
		vp.add(row2);
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
			public void onMouseEnter(Widget sender) {}
			@Override
			public void onMouseLeave(Widget sender) {}
			@Override
			public void onMouseMove(Widget sender, int x, int y) {}
		});

		row.add(img);
		vp.add(row);
		HTMLPanel event = new HTMLPanel("Location:");
		FocusPanel html = new FocusPanel();
		html.setStyleName("DetailPanel");
		html.add(event);

		HTMLPanel event2 = new HTMLPanel("Time:");
		FocusPanel html2 = new FocusPanel();
		html2.setStyleName("DetailPanel");
		html2.add(event2);

		Label event3 = new Label("Module:");
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
			public void onMouseEnter(Widget sender) 
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseLeave(Widget sender) 
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseMove(Widget sender, int x, int y) 
			{
				// TODO Auto-generated method stub
			}
		});

		row.add(img);
		vp.add(row);
		
		final Image login = new Image("img/login.png");
		login.setStyleName("ToTheLeft");
		login.addMouseListener(new MouseListener() 
		{
			@Override
			public void onMouseDown(Widget sender, int x, int y) 
			{
				login.setUrl("img/login_pressed.png");
			}
			
			@Override
			public void onMouseUp(Widget sender, int x, int y) 
			{
				login.setUrl("img/login.png");
				RootPanel.get().remove(0);
				showCombinedTimeTable(true);					
			}
			@Override
			public void onMouseEnter(Widget sender) 
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseLeave(Widget sender) 
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseMove(Widget sender, int x, int y) 
			{
				// TODO Auto-generated method stub
			}
		});
		vp.add(login);
		
		//user1
		Label userid = new Label("User1 ID:");
		userid.setStyleName("GreyText");
		vp.add(userid);
		TextBox idTextBox = new TextBox();
		idTextBox.setHeight("20px");
		idTextBox.setWidth("280px");
		idTextBox.setStyleName("TextBox");
		idTextBox.setText("U0904804");
		vp.add(idTextBox);
		Label password = new Label("User Password:");
		password.setStyleName("GreyText");
		vp.add(password);
		PasswordTextBox pwTextBox = new PasswordTextBox();
		pwTextBox.setHeight("20px");
		pwTextBox.setWidth("280px");
		pwTextBox.setStyleName("TextBox");
		pwTextBox.setText("hahahhaha");
		vp.add(pwTextBox);
		
		//user2
		Label userid2 = new Label("User2 UserID:");
		userid2.setStyleName("GreyText");
		vp.add(userid2);
		TextBox idTextBox2 = new TextBox();
		idTextBox2.setHeight("20px");
		idTextBox2.setWidth("280px");
		idTextBox2.setStyleName("TextBox");		
		idTextBox2.setText("U0904123");

		vp.add(idTextBox2);
		Label password2 = new Label("User2 Password:");
		password2.setStyleName("GreyText");
		vp.add(password2);
		PasswordTextBox pwTextBox2 = new PasswordTextBox();
		pwTextBox2.setHeight("20px");
		pwTextBox2.setWidth("280px");
		pwTextBox2.setStyleName("TextBox");
		pwTextBox2.setText("hahahhaha");

		vp.add(pwTextBox2);
		
		//user3
		Label userid3 = new Label("User3 ID:");
		userid3.setStyleName("GreyText");
		vp.add(userid3);
		TextBox idTextBox3 = new TextBox();
		idTextBox3.setHeight("20px");
		idTextBox3.setWidth("280px");
		idTextBox3.setStyleName("TextBox");
		idTextBox3.setText("U0904234");

		vp.add(idTextBox3);
		Label password3 = new Label("User3 Password:");
		password3.setStyleName("GreyText");
		vp.add(password3);
		PasswordTextBox pwTextBox3 = new PasswordTextBox();
		pwTextBox3.setHeight("20px");
		pwTextBox3.setWidth("280px");
		pwTextBox3.setStyleName("TextBox");
		pwTextBox3.setText("hahahhaha");
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
			public void onMouseEnter(Widget sender) 
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseLeave(Widget sender) 
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void onMouseMove(Widget sender, int x, int y) 
			{
				// TODO Auto-generated method stub
			}
		});

		row.add(img);
		vp.add(row);
		HTMLPanel event = new HTMLPanel("3pm, MA1201, LT14");
		FocusPanel html = new FocusPanel();
		html.setStyleName("DetailPanel");
		html.add(event);

		HTMLPanel event2 = new HTMLPanel("5pm, EN2244, LT8");
		FocusPanel html2 = new FocusPanel();
		html2.setStyleName("DetailPanel");
		html2.add(event2);

		Label event3 = new Label("6pm, MT1234,LT4");
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
}
