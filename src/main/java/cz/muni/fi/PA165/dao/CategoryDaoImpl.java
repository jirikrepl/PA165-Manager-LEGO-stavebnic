package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.Category;

import javax.persistence.Query;
import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/8/13
 */
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {


    @Override
    public List<Category> findAll() {
        Query q = entityManager.createQuery(
                "SELECT c FROM Category c", Category.class);
        return (List<Category>) q.getResultList();
    }

    @Override
    public Category findByName(String name) {
        Query q = entityManager.createQuery(
                "SELECT c FROM Category c WHERE name = :category_name", Category.class);
        q.setParameter("category_name", name);
        Category c = (Category) q.getSingleResult();
        //return (Category) q.getSingleResult();
        return c;
    }
}
