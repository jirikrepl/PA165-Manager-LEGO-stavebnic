package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.Color;
import junit.framework.TestCase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Martin Rumanek
 * @version: 10/10/13
 */
public class BrickDaoTest extends TestCase {

    private BrickDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        dao = new BrickDaoImpl();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
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
            dao.findById(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Brick brick =  TestUtils.createBrick("TestBrick", Color.BLACK, "Some description");
        dao.create(brick);
        dao.delete(brick.getId());

        try {
            dao.findById(brick.getId());
            fail();
        } catch(IllegalArgumentException ex) {
        }

        try {
            Brick brickDeleted = dao.findById(brick.getId());
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
            dao.findById(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Brick brick =  TestUtils.createBrick("TestBrick", Color.BLACK, "Some description");
        dao.create(brick);
        assertNotNull(brick.getId());
        dao.findById(brick.getId());

        assertNotNull(brick.getId());
        assertEquals(brick.getName(), "TestBrick");
        assertEquals(brick.getColor(), Color.BLACK);
        assertEquals(brick.getDescription(), "Some description");
    }

    public void testFindAll() {
        System.out.println("find all");

        assertNotNull(dao.findAll());

        assertEquals(dao.findAll().size(), 0);
        Brick[] bricks = new Brick[4];
        bricks[0] =  TestUtils.createBrick("TestBrick", Color.BLACK, "Some description");
        bricks[1] =  TestUtils.createBrick("TestBrick", Color.BRIGHTBLUE, "Some description");
        bricks[2] =  TestUtils.createBrick("TestBrick", Color.MEDIUMLILAC, "Some description");
        bricks[3] =  TestUtils.createBrick("TestBrick", Color.BLACK, "Some description");

        Set<Brick> setOfBricks = new HashSet<Brick>();
        setOfBricks.addAll(Arrays.asList(bricks));

        for (Brick br : setOfBricks) {
            dao.create(br);
        }

        assertEquals(new HashSet(dao.findAll()), setOfBricks);
    }

    public void testFindByColor() {
        System.out.println("find by color");

        try {
            dao.findByColor(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        assertNotNull(dao.findByColor(Color.BLACK));
        assertEquals(dao.findByColor(Color.BLACK).size(), 0);

        Brick[] bricks = new Brick[4];
        bricks[0] =  TestUtils.createBrick("TestBrick1", Color.BLACK, "Some description");
        bricks[1] =  TestUtils.createBrick("TestBrick2", Color.BRIGHTBLUE, "Some description");
        bricks[2] =  TestUtils.createBrick("TestBrick3", Color.MEDIUMLILAC, "Some description");
        bricks[3] =  TestUtils.createBrick("TestBrick4", Color.BLACK, "Some description");
        Set<Brick> setOfBricks = new HashSet<Brick>();
        setOfBricks.addAll(Arrays.asList(bricks));

        for (Brick br : setOfBricks) {
            dao.create(br);
        }

        List<Brick> brickList = dao.findByColor(Color.BLACK);

        assertTrue(brickList.contains(bricks[0]));
        assertTrue(brickList.contains(bricks[3]));
        assertEquals(brickList.size(), 2);
    }

    public void testFindByName() {
        System.out.println("find all");

        try {
            dao.findByColor(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        assertNotNull(dao.findByColor(Color.BLACK));
        assertEquals(dao.findByColor(Color.BLACK).size(), 0);

        Brick[] bricks = new Brick[4];
        bricks[0] =  TestUtils.createBrick("TestBrick", Color.BLACK, "Some description");
        bricks[1] =  TestUtils.createBrick("TestBrick2", Color.BRIGHTBLUE, "Some description");
        bricks[2] =  TestUtils.createBrick("TestBrick3", Color.MEDIUMLILAC, "Some description");
        bricks[3] =  TestUtils.createBrick("TestBrick", Color.DARKBROWN, "Some description");
        Set<Brick> setOfBricks = new HashSet<Brick>();
        setOfBricks.addAll(Arrays.asList(bricks));

        for (Brick br : setOfBricks) {
            dao.create(br);
        }

        List<Brick> brickList = dao.findByName("TestBrick");

        assertTrue(brickList.contains(bricks[0]));
        assertTrue(brickList.contains(bricks[3]));
        assertEquals(brickList.size(), 2);

    }
}
