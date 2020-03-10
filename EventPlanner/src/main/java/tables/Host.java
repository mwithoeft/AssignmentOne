/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Moritz Withoeft
 */
@Entity
@Table(name = "host")
public class Host implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column
    private String lastname;
    
    @Column
    private String firstname;
    
    @Column
    private String location;
    
    @Column
    private int eventsHosted;
    
    @OneToMany(mappedBy = "eventHost")
    private Set<Event> hostedEvents;
    
    public Host(){
        
    }
}
