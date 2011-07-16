package nus.icreate.mtimetable.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class Event extends HorizontalPanel
{
	Button button = new Button("Lecture");
	Label label = new Label("MA1101, 11- 12, LT18");//subject, time, location

	public Event(Boolean grey)
	{
		button.setHeight("40px");
		button.setWidth("90px");
		label.setStyleName("dateLabel");
		if(grey) this.setStyleName("Grey");
		else this.setStyleName("DarkGrey");
		this.add(button);
		this.add(label);
	}

	public void addListener(ClickListener listener) 
	{
		this.button.addClickListener(listener);
	}
	
	public void setContent(String type,String description)
	{
		if(type!=null)
			button.setText(type);
		if(description!=null)
			label.setText(description);		
	}
}
