package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import tables.CustomHost;

/**
 *
 * @author Andreas Bitzan, Moritz Withoeft
 */
public class HostBean implements Serializable {

    private List<CustomHost> hostlist;

    public HostBean() {
        this.hostlist = new ArrayList<>();
    }

    public HostBean(List<CustomHost> hlist) {
        this.hostlist = hlist;
    }

    public List<CustomHost> getHostList() {
        return this.hostlist;
    }

    public void setHostList(List<CustomHost> hlist) {
        this.hostlist = hlist;
    }

    public void addHost(CustomHost h) {
        this.hostlist.add(h);
    }

    public void removeHost(CustomHost h) {
        this.hostlist.remove(h);
    }

    public boolean containsHost(CustomHost h) {
        return this.hostlist.contains(h);
    }

    public CustomHost getHost(int id) {
        for (CustomHost host : this.hostlist) {
            if (host.getId() == id) {
                return host;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return this.hostlist.isEmpty();
    }
}
