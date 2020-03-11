package tables;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Andreas Bitzan, Moritz Withoeft
 */
@Entity
@Table(name = "events")
@NamedQueries({
    @NamedQuery(name="CustomEvent.findAll", query="SELECT a FROM CustomEvent a")
})
public class CustomEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String eventname;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column
    @Temporal(TemporalType.TIME)
    private Date startTime;
    
    @Column
    @Temporal(TemporalType.TIME)
    private Date endTime;
    
    @Column
    private String shortDescription;
    
    @Column
    private String longDescription;
    
    @ManyToOne
    @JoinColumn(name ="host")
    private CustomHost eventHost;
    
    
    public CustomEvent(){
        
    }
    
    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEventname() {
        return eventname;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public CustomHost getEventHost() {
        return eventHost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setEventHost(CustomHost eventHost) {
        this.eventHost = eventHost;
    }
    
}
