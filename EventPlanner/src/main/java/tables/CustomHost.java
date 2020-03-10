package tables;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Moritz Withoeft
 */
@Entity
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
    
    @OneToMany(mappedBy = "eventHost")
    private Set<CustomEvent> hostedEvents;
    
    
    public CustomHost(){
        
    }
    

    public Long getId() {
        return id;
    }
    
}
