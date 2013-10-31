package cz.muni.fi.PA165.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import org.springframework.stereotype.Repository;

/**
 * general class for AbstractDao, specific entity AbstractDao classes subclass this class
 * this class contains basic CRUD operations
 *
 * @param <E> generic type for instance of entity class
 * @author jirikrepl
 * @author Martin Rumanek
 */

@Repository
public abstract class AbstractDao<E> {

    protected Class<E> entityClass;
    @PersistenceContext
    protected EntityManager entityManager;
    protected EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * costructor uses reflection to get proper entity class
     */
    public AbstractDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        entityManager = emf.createEntityManager();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * persists an entity
     *
     * @param entity some entity to be persisted
     */
    public void create(E entity) {
        if (entity == null) {
            throw new DaoException("Entity can not be NULL");
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * generic method for updating entity in db
     * @param entity generic entity instance, which has to be updated
     */
    public void update(E entity) {
        if (entity == null) {
            throw new DaoException("Entity can not be NULL");
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * generic method of deleting entity from db
     * @param id id of entity which has to deleted
     */
    public void delete(Long id) {
        if (id == null) {
            throw new DaoException("Id cannot be null");
        }
        E entity = findById(id);
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * find entity by given primary key (id)
     *
     * @param id find an instance of entity by its id
     * @return entity which was found
     */
    public E findById(Long id) {
        if (id == null) {
            throw new DaoException("ID cannot be null");
        }
        E entity =  entityManager.find(entityClass, id);
        if (entity == null) {
            throw new DaoException("Object not found");
        }
        return entity;
    }
}