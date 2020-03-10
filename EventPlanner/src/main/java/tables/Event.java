package tables;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "events")
public class Event implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    
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
    private Host eventHost;
    
    public Event(){
        
    }
    
    
}
