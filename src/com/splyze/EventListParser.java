package com.splyze;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EventListParser extends DefaultHandler {
	public ArrayList<EventBriefInfo> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<EventBriefInfo> eventList) {
		this.eventList = eventList;
	}

	private ArrayList<EventBriefInfo> eventList;
	private EventBriefInfo eventBriefInfo;
	private StringBuilder builder;

	@Override
	public void startDocument() throws SAXException {
		eventList = new ArrayList<EventBriefInfo>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (localName.equalsIgnoreCase("eventlist")){
			eventBriefInfo = new EventBriefInfo();
		}
	}
	
	@Override
	public void endElement( String uri, String localName, String qName ) throws SAXException {
		if( localName.toLowerCase().equals("eventlist")){
			this.eventList.add(eventBriefInfo);
		}
		else if(localName.toLowerCase().equals("eventid")){
			eventBriefInfo.setEventID(Integer.parseInt(builder.toString()));
		}
		else if(localName.toLowerCase().equals("eventname")){
			eventBriefInfo.setEventName(builder.toString());
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String tempString = new String(ch, start, length);
		builder = new StringBuilder();
		builder.append(tempString);
	}
}
