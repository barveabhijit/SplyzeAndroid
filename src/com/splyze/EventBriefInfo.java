package com.splyze;

public class EventBriefInfo {
    String eventName = null;
    int eventID = 0;
    
    public EventBriefInfo() {
        super();
    }
    
    public EventBriefInfo( String eventName, int eventID){
        super();
        this.eventName = eventName;
        this.eventID = eventID;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getEventID() {
        return eventID;
    }

}
