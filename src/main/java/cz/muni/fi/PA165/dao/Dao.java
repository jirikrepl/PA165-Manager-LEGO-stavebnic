package cz.muni.fi.PA165.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * general class for Dao, specific entity Dao classes subclass this class
 * 
 * @author jirikrepl
 * @param <E> generic type for instance of entity class
 * @param <K> generic type for key variable
 */
public class Dao<E, K extends Serializable> {

    protected Class entityClass;
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * costructor uses reflection to get proper entity class
     */
    public Dao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];
    }

    /**
     * persists an entity 
     * 
     * @param entity some entity to be persisted
     */
    public void persist(E entity) {
        entityManager.persist(entity);
    }

    /**
     * remove entity from context
     * 
     * @param entity entity which has to be removed
     */
    public void remove(E entity) {
        entityManager.remove(entity);
    }

    /**
     * find entity by given primary key (id)
     * 
     * @param id find an instance of entity by its id
     * @return entity which was found
     */
    public E findById(K id) {
        return (E) entityManager.find(entityClass, id);
    }
}
