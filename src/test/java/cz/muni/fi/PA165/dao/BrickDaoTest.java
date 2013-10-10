package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.dao.BrickDao;
import cz.muni.fi.PA165.domain.Color;
import junit.framework.TestCase;

/**
 * @author: Martin Rumanek
 * @version: 10/10/13
 */
public class BrickDaoTest extends TestCase {

    private BrickDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        dao = new BrickDao();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testStoreBrick() {
        System.out.println("create");

        try {
            dao.storeBrick(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testRemoveBrick() {

    }

    public void testFindByColor() {

    }

    public void findByName() {

    }
}
