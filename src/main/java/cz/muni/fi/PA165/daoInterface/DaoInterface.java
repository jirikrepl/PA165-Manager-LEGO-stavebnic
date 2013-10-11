/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.daoInterface;

/**
 *
 * @author Jiri Krepl
 */
public interface DaoInterface<E> {

    void close();

    /**
     * persists an entity
     *
     * @param entity some entity to be persisted
     */
    void store(E entity);

    /**
     * remove entity from context
     *
     * @param entity entity which has to be removed
     */
    public void delete(E entity);

    /**
     * find entity by given primary key (id)
     *
     * @param id find an instance of entity by its id
     * @return entity which was found
     */
    public E retrieveById(Long id);
}
