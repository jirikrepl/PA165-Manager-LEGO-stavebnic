package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.Category;

import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/8/13
 */
public interface CategoryDao {

    public void createCategory(Category category);

    public void removeCategory(Category category);

    public void updateCategory(Category category);

    public List<Category> findAll();

}
