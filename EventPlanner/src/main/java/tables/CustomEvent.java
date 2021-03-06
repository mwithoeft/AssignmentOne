package tables;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

/**
 * Event Entity that will be persistent in database. Each instance represents
 * one table row in database.
 *
 * @author Andreas Bitzan, Moritz Withoeft
 */
@Entity
@Table(name = "events")
@NamedQueries({
    @NamedQuery(name = "CustomEvent.findAll", query = "SELECT a FROM CustomEvent a")
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "host")
    private CustomHost eventHost;

    @Transient
    private boolean selfInitialized;

    public CustomEvent() {
        this.selfInitialized = false;
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

    public void setSelfInitialized(boolean selfInitialized) {
        this.selfInitialized = selfInitialized;
    }

    public boolean getSelfInitialized() {
        return this.selfInitialized;
    }

    /**
     * Formats startTime to String, to display in JSP.
     *
     * @return startTime as String in HTML format
     */
    public String getStartTimeString() {
        if (startTime != null) {
            return new SimpleDateFormat("HH:mm").format(startTime);
        } else {
            return "";
        }
    }

    /**
     * Formats endTime to String, to display in JSP.
     *
     * @return endTime as String in HTML format
     */
    public String getEndTimeString() {
        if (endTime != null) {
            return new SimpleDateFormat("HH:mm").format(endTime);
        } else {
            return "";
        }
    }

    /**
     * Formats startDate to String, to display in JSP.
     *
     * @return startDate as String in HTML format
     */
    public String getStartDateString() {
        if (startDate != null) {
            return new SimpleDateFormat("YYYY-MM-dd").format(startDate);
        } else {
            return "";
        }
    }

    /**
     * Formats endDate to String, to display in JSP.
     *
     * @return endDate as String in HTML format
     */
    public String getEndDateString() {
        if (endDate != null) {
            return new SimpleDateFormat("YYYY-MM-dd").format(endDate);
        } else {
            return "";
        }
    }

    /**
     * Formats startTime for proper display on the Website.
     *
     * @return formatted String
     */
    public String displayStartTime() {
        Locale loc = new Locale("en", "US");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);
        return dateFormat.format(this.startDate);
    }

}
