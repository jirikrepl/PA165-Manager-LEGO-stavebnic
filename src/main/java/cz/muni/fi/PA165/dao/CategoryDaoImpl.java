package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.Category;

import javax.persistence.Query;
import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/8/13
 */
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {


    @Override
    public void storeCategory(Category category) {
        store(category);
    }

    @Override
    public void removeCategory(Category category) {
        delete(category);
    }

    @Override
    public List<Category> findAll() {
        Query q = entityManager.createQuery(
                "SELECT b FROM Category b", Brick.class);
        return (List<Category>) q.getResultList();
    }

    @Override
    public List<Category> findByName(String name) {
        return null;
    }
}
