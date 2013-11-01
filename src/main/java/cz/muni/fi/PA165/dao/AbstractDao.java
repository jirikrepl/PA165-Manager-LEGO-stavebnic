package cz.muni.fi.PA165.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

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

    /**
     * costructor uses reflection to get proper entity class
     */
    public AbstractDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
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
            throw new IllegalArgumentException("Entity can not be NULL");
        }

        entityManager.persist(entity);
    }

    /**
     * generic method for updating entity in db
     * @param entity generic entity instance, which has to be updated
     */
    public void update(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be NULL");
        }
       entityManager.merge(entity);

    }

    /**
     * generic method of deleting entity from db
     * @param id id of entity which has to deleted
     */
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        E entity = findById(id);

        entityManager.remove(entity);

    }

    /**
     * find entity by given primary key (id)
     *
     * @param id find an instance of entity by its id
     * @return entity which was found
     */
    public E findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        E entity =  entityManager.find(entityClass, id);
        if (entity == null) {
            throw new IllegalArgumentException("Object not found");
        }
        return entity;
    }
}