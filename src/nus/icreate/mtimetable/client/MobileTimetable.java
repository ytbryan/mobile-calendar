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
		showLogin(false);
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
//		  
//			t.scheduleRepeating(3000);
//		   
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
		   img.addClickListener(new ClickListener() {
			      public void onClick(Widget sender) {
			    	  RootPanel.get().remove(0);
			    	   showLogin(true);
			      }
			    });
		 
		   row.add(img);
		   vp.add(row);
		   final DatePicker dp = new DatePicker();
		   
		   dp.setHeight("160px");
		   dp.setWidth("320px");
		   vp.add(dp);
		   ScrollPanel sp = new ScrollPanel();
		   sp.setWidth("320px");
		   sp.setHeight("200px");
		   VerticalPanel vp2 = new VerticalPanel();
		   
		   ClickListener listener = new ClickListener()
		   {
		       public void onClick(Widget sender)
		       {
//		          dp.setCurrentMonth(new Date(2011-1900,10,7));
			      RootPanel.get().remove(0);
		    	  showEvent(true);
		       }
		   };
		   Button h = new Button("Go to Event", listener);
		   vp2.add(h);
		   sp.add(vp2);
		   vp.add(sp);
		   RootPanel.get().add(vp);

		   Fade theFade = new Fade(vp.getElement());
		   
		   if(GoToLeft){
			   SlideLeft theSlide = new SlideLeft(vp.getElement());			   
			   theSlide.setDuration(0.5);
			   theSlide.play();
		   }
		   else {
			   SlideRight theSlide = new SlideRight(vp.getElement());			   
			   theSlide.setDuration(0.5);
			   theSlide.play();
		   }

	}
	
	private void showEvent(boolean GoToLeft)
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
			    	  showCalendar(false); // false to turn left
			      }
		   });
		 
		   row.add(img);
		   vp.add(row);
		   Label event = new Label("This is Event Details");
		   vp.add(event);
		   ScrollPanel sp = new ScrollPanel();
		   sp.setWidth("320px");
		   sp.setHeight("200px");
		   VerticalPanel vp2 = new VerticalPanel();
		  		  
		   RootPanel.get().add(vp);

		   Fade theFade = new Fade(vp.getElement());
		   
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


}
