package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.entity.Category;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jiri Krepl
 */
public class CategoryDaoTest extends TestCase {

    private CategoryDao dao;
    private EntityManager em;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = new CategoryDaoImpl();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        dao.setEntityManager(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * test creation and storage of Category instance
     */
    public void testCreate() {
        System.out.println("testing method CREATE of CategoryDaoImpl class");
        // test for no argument creation
        try {
            em.getTransaction().begin();
            dao.create(null);
            em.getTransaction().commit();
            fail();
        } catch (IllegalArgumentException ex) {
        }
        // create category and store it into db
        Category category = TestUtils.createCategory("testCategory", "testDescription");

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        em.getTransaction().begin();
        dao.create(category);
        em.getTransaction().commit();
        assertNotNull(category);
        // retrieve category and test its properties
        Category retrievedCat = dao.findByName("testCategory");
        assertNotNull(retrievedCat.getId());
        assertEquals(retrievedCat.getName(), category.getName());
        assertEquals(retrievedCat.getDescription(), category.getDescription());
    }

    public void testUpdate() {
        System.out.println("testing method CREATE of CategoryDaoImpl class");
        // test for no argument creation
        try {
            em.getTransaction().begin();
            dao.update(null);
            em.getTransaction().commit();
            fail();
        } catch (IllegalArgumentException ex) {
        }

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        
        Category category = TestUtils.createCategory("testCategory", "testDescription");
        
        em.getTransaction().begin();
        dao.create(category);
        em.getTransaction().commit();

        Long id = category.getId();
        Category retrivedCat = dao.findById(id);

        retrivedCat.setName("newTestName");
        retrivedCat.setDescription("newTestDescription");

        em.getTransaction().begin();
        dao.update(category);
        em.getTransaction().commit();
        Category updatedRetrivedCat = dao.findById(id);
        assertEquals(updatedRetrivedCat.getName(), retrivedCat.getName());
        assertEquals(updatedRetrivedCat.getDescription(), retrivedCat.getDescription());
    }

    public void testDelete() {
        Category category = TestUtils.createCategory("testCategory", "testDescription");
        em.getTransaction().begin();
        dao.create(category);
        em.getTransaction().commit();
        assertNotNull(category);
        em.getTransaction().begin();
        dao.delete(category.getId());
        em.getTransaction().commit();

        List<Category> exptRslt = new ArrayList<Category>();

        List<Category> result = dao.findAll();

        assertEquals(exptRslt, result);
    }

    /**
     * test if empty db returns empty List of Category entities
     */
    public void testFindAll() {
        System.out.println("testing method FINDALL of CategoryDaoImpl class");
        List<Category> expResult = new ArrayList<Category>();
        List<Category> result = dao.findAll();
        assertEquals(expResult, result);
    }

    /**
     * test if, stored Category with name can be found by its name
     */
    public void testFindByName() {
        System.out.println("testing method 'findByName' of CategoryDaoImpl class");

        Category entity = new Category();
        entity.setName("testName");
        entity.setDescription("testDescription");
        em.getTransaction().begin();
        dao.create(entity);
        em.getTransaction().commit();
        assertNotNull(entity.getId());

        Category retrievedEntity = dao.findByName(entity.getName());
        assertEquals(entity, retrievedEntity);
    }

    public void testFindById() {
        try {
            dao.findById(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        System.out.println("testing method 'findById' of CategoryDaoImpl class");
        Category category = TestUtils.createCategory("testCategory", "testDescription");
        em.getTransaction().begin();
        dao.create(category);
        em.getTransaction().commit();

        assertNotNull(category.getId());
        Category retrievedCat = dao.findById(category.getId());
        assertEquals(category, retrievedCat);

    }
}
