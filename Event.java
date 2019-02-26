/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupProject;

public class Event {
    public String eventName;
    public String eventLocation;
    public int eventTime;
    public String eventInfo;
    public Volunteer volunteersWorking;
    
    public Event(String eventName, String eventLocation, int eventTime, String eventInfo)
    {
        this.eventInfo = eventInfo;
        this.eventLocation = eventLocation;
        this.eventName = eventName;
        this.eventTime = eventTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public int getEventTime() {
        return eventTime;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    public Volunteer getVolunteersWorking() {
        return volunteersWorking;
    }

    public void setVolunteersWorking(Volunteer volunteersWorking) {
        this.volunteersWorking = volunteersWorking;
    }
    
    
            
}
