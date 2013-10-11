package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.Color;
import junit.framework.TestCase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author: Martin Rumanek
 * @version: 10/10/13
 */
public class BrickDaoTest extends TestCase {

    private BrickDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        dao = new BrickDaoImpl();
        dao.setEntityManager(emf.createEntityManager());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testStoreBrick() {
        System.out.println("create");

        try {
            dao.create(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Brick brick =  TestUtils.createBrick("TestBrick", Color.BLACK, "Some description");
        dao.create(brick);
        assertNotNull(brick.getId());
        assertEquals(brick.getName(), "TestBrick");
        assertEquals(brick.getColor(), Color.BLACK);
        assertEquals(brick.getDescription(), "Some description");

    }

    public void testDeleteBrick() {
        System.out.println("delete");

        try {
            dao.retrieveById(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Brick brick =  TestUtils.createBrick("TestBrick", Color.BLACK, "Some description");
        dao.create(brick);
        dao.delete(brick.getId());

        try {
            Brick brickDeleted = dao.retrieveById(brick.getId());
            fail();
        } catch (IllegalArgumentException ex) {
        }


        try {
            dao.delete(new Long(-1L));
            fail();
        } catch (IllegalArgumentException ex) {
        }



    }

    public void testRetrieveById() {
        System.out.println("get by id");

        try {
            dao.retrieveById(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Brick brick =  TestUtils.createBrick("TestBrick", Color.BLACK, "Some description");
        dao.create(brick);
        assertNotNull(brick.getId());
        dao.retrieveById(brick.getId());

        assertNotNull(brick.getId());
        assertEquals(brick.getName(), "TestBrick");
        assertEquals(brick.getColor(), Color.BLACK);
        assertEquals(brick.getDescription(), "Some description");
    }

    public void testFindAll() {

    }

    public void testFindByColor() {



    }

    public void testFindByName() {

    }
}
