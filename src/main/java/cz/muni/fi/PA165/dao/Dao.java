package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.daoInterface.DaoInterface;
import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * general class for Dao, specific entity Dao classes subclass this class
 * this class contains basic CRUD operations 
 *
 * @author jirikrepl
 * @param <E> generic type for instance of entity class
 */
public abstract class Dao<E> implements DaoInterface<E>{

    protected Class<E> entityClass;
    @PersistenceContext
    protected static EntityManager entityManager;

    /**
     * costructor uses reflection to get proper entity class
     */
    public Dao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        Dao.entityManager = emf.createEntityManager();
    }
    
    public void close() {
        entityManager.close();
    }

    /**
     * persists an entity
     *
     * @param entity some entity to be persisted
     */
    public void store(E entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * remove entity from context
     *
     * @param entity entity which has to be removed
     */
    public void delete(E entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        
    }

    /**
     * find entity by given primary key (id)
     *
     * @param id find an instance of entity by its id
     * @return entity which was found
     */
    public E retrieveById(Long id) {
        return entityManager.find(entityClass, id);
    }
}