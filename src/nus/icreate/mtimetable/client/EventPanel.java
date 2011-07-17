package nus.icreate.mtimetable.client;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EventPanel extends VerticalPanel
{
	public EventPanel()
	{
		HTMLPanel eachPanelEnd = new HTMLPanel("");
		eachPanelEnd.setSize("320px", "5px");
		eachPanelEnd.setStyleName("EachPanelEnd");		
		this.add(eachPanelEnd);
	}
	
	public void addEvent(Event event)
	{
		this.add(event);
	}
	
	public void addEnd()
	{
		HTMLPanel eachPanelEnd2 = new HTMLPanel("");
		eachPanelEnd2.setSize("320px", "5px");
		eachPanelEnd2.setStyleName("EachPanelEnd");
		this.add(eachPanelEnd2);
	}
}
