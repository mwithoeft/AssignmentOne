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
 * Database connector for the hosts. Uses EntityManager for persistence.
 *
 * @author Andreas Bitzan, Moritz Withoeft
 */
public class CustomHostDB {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    public CustomHostDB() {
    }

    /**
     * Adds host to persistence context and database.
     *
     * @param host Event to be added to persistence context
     */
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

    /**
     * Updates host in database.
     *
     * @param host Event to be updated
     * @return Returns reference to merged host after update
     */
    public CustomHost update(CustomHost host) {
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

    /**
     * Deletes host from database and persistence context.
     *
     * @param host Event to be deleted from persistence context and database
     */
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

    /**
     * Searches for a host with a certain id.
     *
     * @param id ID of the entity to be searched
     * @return Reference to host found or null
     */
    public CustomHost findById(long id) {
        Query q = em.createQuery("SELECT a FROM CustomHost a WHERE a.id = :id");
        q.setParameter("id", id);
        q.setMaxResults(1);
        return (CustomHost) q.getSingleResult();
    }

    /**
     * Searches for all hosts.
     *
     * @return List of all found hosts
     */
    public List<CustomHost> findAll() {
        return em.createNamedQuery("CustomHost.findAll", CustomHost.class).getResultList();
    }

    @PreDestroy
    public void destruct() {
        em.close();
    }

}
