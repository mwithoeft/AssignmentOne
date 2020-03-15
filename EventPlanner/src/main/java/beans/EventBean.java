/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import tables.CustomEvent;

/**
 *
 * @author Andreas Bitzan
 */
public class EventBean implements Serializable {

    private List<CustomEvent> eventList;

    public EventBean() {
        this.eventList = new ArrayList<>();
    }

    public EventBean(List<CustomEvent> eventlist) {
        this.eventList = eventlist;
    }

    public List<CustomEvent> getEventList() {
        return this.eventList;
    }

    public void setHostList(List<CustomEvent> elist) {
        this.eventList = elist;
    }

    public void addEvent(CustomEvent e) {
        this.eventList.add(e);
    }

    public void removeEvent(CustomEvent e) {
        this.eventList.remove(e);
    }

    public boolean containsEvent(CustomEvent e) {
        return this.eventList.contains(e);
    }

    public CustomEvent getEvent(int id) {
        for (CustomEvent event : this.eventList) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return this.eventList.isEmpty();
    }
}
