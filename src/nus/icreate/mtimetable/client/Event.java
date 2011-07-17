package nus.icreate.mtimetable.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class Event extends HorizontalPanel
{
	Button button = new Button();
	Label label = new Label();//subject, time, location

	public Event(Boolean grey, ClickListener listener, String type, String description)
	{
		button.setHeight("40px");
		button.setWidth("90px");
		button.setStyleName("EventButton");
		label.setStyleName("DateLabel");
		if(grey) this.setStyleName("Grey");
		else this.setStyleName("DarkGrey");
		this.add(button);
		this.add(label);
		this.button.addClickListener(listener);
		if(type!=null)
			button.setText(type);
		if(description!=null)
			label.setText(description);		
	}
	
//	public getModule()
//	{
//		return "MA1"
//	}
	
	

	public void addListener(ClickListener listener) 
	{
	}
	
	public void setContent(String type,String description)
	{
		
	}
}
