package tables;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Andreas Bitzan, Moritz Withoeft
 */
@Entity
@Table(name = "hosts")
@NamedQueries({
    @NamedQuery(name="CustomHost.findAll", query="SELECT a FROM CustomHost a")
})
public class CustomHost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    @Column
    private String lastname;
    
    @Column
    private String firstname;
    
    @Column
    private String location;
    
    @Column
    private int eventsHosted;
    
    @OneToMany(
            mappedBy = "eventHost",
            cascade = CascadeType.MERGE
    )
    private Set<CustomEvent> hostedEvents;
    
    @Transient
    private boolean selfInitialized;
    
    public CustomHost(){
        this.selfInitialized = false;
    }
    
    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLocation() {
        return location;
    }

    public int getEventsHosted() {
        return eventsHosted;
    }
    public Set<CustomEvent> getHostedEvents() {
        return hostedEvents;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventsHosted(int eventsHosted) {
        this.eventsHosted = eventsHosted;
    }

    public void setHostedEvents(Set<CustomEvent> hostedEvents) {
        this.hostedEvents = hostedEvents;
    }
    
    public void setSelfInitialized(boolean selfInitialized) {
        this.selfInitialized = selfInitialized;
    }
    public boolean getSelfInitialized(){
        return this.selfInitialized;
    }

    @Override
    public String toString() {
        return this.firstname+ " "+this.lastname; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
