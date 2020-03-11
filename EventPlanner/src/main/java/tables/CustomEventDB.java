package tables;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
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
    
    public CustomEvent update(CustomEvent event){
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

    public void delete(CustomEvent event) {
        try {
            this.utx.begin();
        } catch (NotSupportedException | SystemException ex) {
            System.err.println("Error starting the transaction: " + ex);
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

    public List<CustomEvent> findAll() {
        return em.createNamedQuery("CustomEvent.findAll", CustomEvent.class).getResultList();
    }

    @PreDestroy
    public void destruct() {
        em.close();
    }

}
