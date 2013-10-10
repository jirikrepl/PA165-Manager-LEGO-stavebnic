package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/8/13
 */
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createCategory(Category category) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeCategory(Category category) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateCategory(Category category) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Category> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
