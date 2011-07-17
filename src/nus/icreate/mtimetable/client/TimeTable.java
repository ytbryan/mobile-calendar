package nus.icreate.mtimetable.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
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
	final Label dateLabel = new Label();
	final DatePicker datePicker = new DatePicker();
	HorizontalPanel hp = new HorizontalPanel();
	Image smallspinner = new Image("img/spinnersmall.gif");
	HorizontalPanel hp2;
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final WebServiceAsync greetingService = GWT
	.create(WebService.class);

	public TimeTable()
	{
		dateLabel.setStyleName("DateLabel");
		String dateValue = DateTimeFormat.getMediumDateFormat().format(new Date());
		dateLabel.setText("Today is " + dateValue );
		ClickListener listener = new ClickListener()
		{
			public void onClick(Widget sender)
			{
				if(sender.getTitle().matches("next"))
				{
					Date currentDate = datePicker.getCurrentMonth();
					currentDate.setMonth(currentDate.getMonth()+1);
					datePicker.setCurrentMonth(currentDate);
					dateLabel.setText(DateTimeFormat.getMediumDateFormat().format(datePicker.getCurrentMonth()));
				}
				else if(sender.getTitle().matches("previous"))
				{
					Date currentDate = datePicker.getCurrentMonth();
					currentDate.setMonth(currentDate.getMonth()-1);
					datePicker.setCurrentMonth(currentDate);
					dateLabel.setText( DateTimeFormat.getMediumDateFormat().format(datePicker.getCurrentMonth()));
				}
				else 
				{
					//do nothing
				}
			}
		};

		datePicker.addValueChangeHandler(new ValueChangeHandler() 
		{
			public void onValueChange(ValueChangeEvent event) 
			{
				Date date = (Date) event.getValue();
				String dateString = DateTimeFormat.getMediumDateFormat().format(date);
				dateLabel.setText(dateString);
				hp2.add(smallspinner);
				sendNameToServer();
				
				greetingService.getTimeTableStudentServer(dateString,
						new AsyncCallback<String>() 
						{
							public void onFailure(Throwable caught) 
							{
								
							}

							public void onSuccess(String result) 
							{
								if(result==null)
								{
									
								}
								else{
									
								}

							}
						});
				 Timer t = new Timer() {
				      public void run() {
							hp2.remove(1);
				      }
				    };
				    // Schedule the timer to run once in 5 seconds.
				    t.schedule(2000);

			}
		});

		final Image next = new Image("img/next.png");
		next.setStyleName("button");
		next.addClickListener(listener);
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
		next.setTitle("next");

		final Image previous = new Image("img/previous.png");
		previous.setStyleName("button");
		previous.addClickListener(listener);
		previous.setTitle("previous");

		previous.addTouchStartHandler(new TouchStartHandler()
		{
			@Override
			public void onTouchStart(TouchStartEvent event) 
			{
				previous.setStyleName("button_pressed");
			}			   
		});

		previous.addTouchEndHandler(new TouchEndHandler()
		{
			@Override
			public void onTouchEnd(TouchEndEvent event) 
			{
				previous.setStyleName("button");
			}				   
		});

		hp2 = new HorizontalPanel();
		hp2.add(dateLabel);
		
		hp.add(hp2);
		hp.add(previous);
		hp.add(next);

		this.add(hp);
		this.add(datePicker);
	}

	private void sendNameToServer() 
	{
		String textToServer = "1231312";
				greetingService.authenticationServer(textToServer,
				new AsyncCallback<String>() 
				{
			public void onFailure(Throwable caught) 
			{
				hp.remove(3);
				dateLabel.setText("Failed");
			}

			public void onSuccess(String result) 
			{
				hp.remove(3);
				dateLabel.setText("Success");
			}
				});
	}

	public void setWidth(String width)
	{
		hp.setSize(width,"20px");
		datePicker.setSize(width, "160px");
	}

}
