/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import tables.CustomEvent;

/**
 *
 * @author Andreas Bitzan
 */
public class EventBean implements Serializable{
    private List<CustomEvent> eventList;
    
    public EventBean(){
        this.eventList=new List<CustomEvent>();
    }
    public EventBean(List<CustomEvent> eventlist){
        this.eventList= eventlist;
    }
    
    public List<CustomEvent> getEventList(){
        return this.eventList;
    }
    public void setHostList(List<CustomEvent> elist){
        this.eventList=elist;
    }
    public void addEvent(CustomEvent e){
        this.eventList.add(e);
    }
    public void removeEvent(CustomEvent e){
        this.eventList.remove(e);
    }
    public boolean containsEvent(CustomEvent e){
        return this.eventList.contains(e);
    }
    public CustomEvent getEvent(int id){
        for(CustomHost host : this.eventList){
          if(host.getId()==id){
              return host;
          }
        }
    }
}
