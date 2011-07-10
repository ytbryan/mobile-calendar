package nus.icreate.mtimetable.client;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
			        
			        //change the content

			      }
			    });
		   Button next = new Button(">>", listener);
		   next.setTitle("next");
		   Button previous = new Button("<<", listener);
		   previous.setTitle("previous");
		   
		   hp.add(previous);
		   hp.add(date2);
		   hp.add(next);
		   
			this.add(hp);
			this.add(dp);
	}
	
	public void setWidth(String width)
	{
		hp.setSize(width,"20px");
		dp.setSize(width, "160px");
	}
	  
	
}
