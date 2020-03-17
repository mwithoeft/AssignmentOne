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
public class CustomHostDB {
    
    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction utx;
    
    public CustomHostDB(){
    }
    
    public void create(CustomHost host) {
        try {
            this.utx.begin();
        } catch (NotSupportedException | SystemException ex) {
            System.err.println("Error starting the transaction: " + ex);
        }
        em.persist(host);
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
    
    public CustomHost update(CustomHost host){
        try {
            this.utx.begin();
        } catch (NotSupportedException | SystemException ex) {
            System.err.println("Error starting the transaction: " + ex);
        }
        host = em.merge(host);
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
        return host;
    }
    
    public CustomHost reattach(CustomHost host) {
        if (!em.contains(host)) {
            host = em.merge(host);
        }
        return host;
    }
    
    public void delete(CustomHost host) {
        try {
            this.utx.begin();
        } catch (NotSupportedException | SystemException ex) {
            System.err.println("Error starting the transaction: " + ex);
        }
        if (!em.contains(host)) {
            host = em.merge(host);
        }
        em.remove(host);
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

    public List<CustomHost> findAll() {
        return em.createNamedQuery("CustomHost.findAll", CustomHost.class).getResultList();
    }
    
    @PreDestroy
    public void destruct() {
        em.close();
    }
    
}
