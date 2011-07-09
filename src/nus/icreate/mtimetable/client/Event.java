package nus.icreate.mtimetable.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class Event extends HorizontalPanel
{
	
	Button button = new Button("Go To Event");
	Label label = new Label("Description: This is a description");
	
	public Event()
	{
		   button.setHeight("40px");
		   button.setWidth("120px");
		this.add(button);
		this.add(label);
	}


	public void addListener(ClickListener listener) {
		this.button.addClickListener(listener);
	}

}
