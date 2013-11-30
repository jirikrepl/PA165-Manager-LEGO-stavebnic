/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.BuildingKit;
import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author pc
 */
public class BuildingKitDaoImpl extends AbstractDao<BuildingKit>
    implements BuildingKitDao {

    public List<BuildingKit> findAll() {
        Query q = entityManager.createQuery(
                "SELECT b FROM BuildingKit b", BuildingKit.class);
        return (List<BuildingKit>) q.getResultList();
    }

    public List<BuildingKit> findByPrice(BigDecimal price) {
        Query q = entityManager.createQuery("SELECT b FROM " + 
                "BuildingKit b WHERE price <= :price",
                BuildingKit.class);
        q.setParameter("price", price);
        return (List<BuildingKit>) q.getResultList();
    }

    public List<BuildingKit> findByYearFrom(int yearFrom) {
        Query q = entityManager.createQuery("SELECT b FROM " + 
                "BuildingKit b WHERE yearFrom >= :yearFrom",
                BuildingKit.class);
        q.setParameter("yearFrom", yearFrom);
        return (List<BuildingKit>) q.getResultList();
    }

//    public List<BuildingKit> findByBrick(Brick brick) {
//        if (brick == null) {
//            throw new IllegalArgumentException("Brick cannot be null.");
//        }
//        Query q = entityManager.createQuery("SELECT DISTINCT b FROM BuildingKit b " +
//                "JOIN b.bricks br "+
//                "WHERE :brick IN br",
//                BuildingKit.class);
//        q.setParameter("brick", brick.getId());
//        return (List<BuildingKit>) q.getResultList();
//    }

    public List<BuildingKit> findByCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null.");
        }
        Query q = entityManager.createQuery("SELECT b FROM " +
                "BuildingKit b WHERE category = :category",
                BuildingKit.class);
        q.setParameter("category", category);
        return (List<BuildingKit>) q.getResultList();
    }

    public List<BuildingKit> findByThemeSet(ThemeSet themeSet) {
        if (themeSet == null) {
            throw new IllegalArgumentException("Theme set cannot be null.");
        }
        Query q = entityManager.createQuery("SELECT b FROM " +
                "BuildingKit b WHERE themeSet = :themeSet",
                BuildingKit.class);
        q.setParameter("themeSet", themeSet);
        return (List<BuildingKit>) q.getResultList();
    }

}
