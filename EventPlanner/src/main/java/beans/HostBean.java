/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Andreas Bitzan
 */
public class HostBean implements Serializable {
    
    private List<CustomHost> hostlist;
    
    public HostBean(){
        this.hostlist=new List<CustomHost>();
    }
    public HostBean(List<CustomHost> hlist){
        this.hostlist= hlist;
    }
    
    public List<CustomHost> getHostList(){
        return this.hostlist;
    }
    public void setHostList(List<CustomHost> hlist){
        this.hostlist=hlist;
    }
    public void addHost(CustomHost h){
        this.hostlist.add(h);
    }
    public void removeHost(CustomHost h){
        this.hostlist.remove(h);
    }
    public boolean containsHost(CustomHost h){
        return this.hostlist.contains(h);
    }
}
