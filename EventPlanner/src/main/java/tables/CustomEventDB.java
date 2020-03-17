package tables;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Database connector for the events. Uses EntityManager for persistence.
 *
 * @author Andreas Bitzan, Moritz Withoeft
 */
public class CustomEventDB {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    public CustomEventDB() {
    }

    /**
     * Adds event to persistence context and database.
     *
     * @param event Event to be added to persistence context
     */
    public void create(CustomEvent event) {
        try {
            this.utx.begin();
        } catch (NotSupportedException | SystemException ex) {
            System.err.println("Error starting the transaction: " + ex);
        }
        em.persist(event);
        try {
            this.utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException ex) {
            System.err.println("Error committing the transaction: " + ex);
            System.err.println("Attempting rollback");
            try {
                this.utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                System.err.println("Rollback failed: " + ex);
            }
        }
    }

    /**
     * Updates event in database.
     *
     * @param event Event to be updated
     * @return Returns reference to merged event after update
     */
    public CustomEvent update(CustomEvent event) {
        try {
            this.utx.begin();
        } catch (NotSupportedException | SystemException ex) {
            System.err.println("Error starting the transaction: " + ex);
        }
        event = em.merge(event);
        try {
            this.utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException ex) {
            System.err.println("Error committing the transaction: " + ex);
            System.err.println("Attempting rollback");
            try {
                this.utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                System.err.println("Rollback failed: " + ex);
            }
        }
        return event;
    }

    /**
     * Deletes event from database and persistence context.
     *
     * @param event Event to be deleted from persistence context and database
     */
    public void delete(CustomEvent event) {
        try {
            this.utx.begin();
        } catch (NotSupportedException | SystemException ex) {
            System.err.println("Error starting the transaction: " + ex);
        }
        if (!em.contains(event)) {
            event = em.merge(event);
        }
        em.remove(event);
        try {
            this.utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException ex) {
            System.err.println("Error committing the transaction: " + ex);
            System.err.println("Attempting rollback");
            try {
                this.utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                System.err.println("Rollback failed: " + ex);
            }
        }
    }

    /**
     * Searches for all events upcoming.
     *
     * @return List of all found events
     */
    public List<CustomEvent> findAll() {
        return em.createNamedQuery("CustomEvent.findAll", CustomEvent.class).getResultList();
    }

    /**
     * Searches for an event with a certain id.
     *
     * @param id ID of the entity to be searched
     * @return Reference to event found or null
     */
    public CustomEvent findById(long id) {
        Query q = em.createQuery("SELECT a FROM CustomEvent a WHERE a.id = :id");
        q.setParameter("id", id);
        q.setMaxResults(1);
        return (CustomEvent) q.getSingleResult();
    }

    @PreDestroy
    public void destruct() {
        em.close();
    }

}
