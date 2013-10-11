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
        //dao.close();

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


    }

    public void retrieveById() {
        System.out.println("get by id");

        Brick brick =  TestUtils.createBrick("TestBrick", Color.BLACK, "Some description");
        dao.create(brick);





    }

    public void testFindByColor() {

    }

    public void findByName() {

    }
}
