package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.entity.Account;
import junit.framework.TestCase;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author Tomas Kopecky
 */
public class AccountDaoTest extends TestCase {
    private AccountDao dao;
    private EntityManager em;

    public AccountDaoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        dao = new AccountDaoImpl();
        dao.setEntityManager(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateAccount() {
        System.out.println("TEST Create Account");

        try {
            dao.create(null);
            fail("creating NULL account");
        } catch (IllegalArgumentException ex) {

        }

        em.getTransaction().begin();
        Account ac1 = TestUtils.createAccount("josef", "pepa", false);
        dao.create(ac1);
        em.getTransaction().commit();

        Long id = ac1.getId();
        Account ac2 = dao.findById(id);

        assertEquals(ac1.getId(), ac2.getId());
        assertEquals(ac1.getName(), ac2.getName());
        assertEquals(ac1.getPassword(), ac2.getPassword());
        assertEquals(ac1.getIsAdmin(), ac2.getIsAdmin());
    }

    public void testRemoveAccount() {
        System.out.println("TEST Delete Account");

        try {
            dao.delete(null);
            fail("removing NULL account");
        } catch (IllegalArgumentException ex) {
        }

        Account account = TestUtils.createAccount("josef", "pepa", false);
        em.getTransaction().begin();
        dao.create(account);
        em.getTransaction().commit();
        assertNotNull(account);
        em.getTransaction().begin();
        dao.delete(account.getId());
        em.getTransaction().commit();

        List<Account> result = dao.findAll();

        assertTrue(result.isEmpty());
    }

    public void testUpdateAccount() {
        System.out.println("TEST Update Account");

        try {
            dao.update(null);
            fail("updating NULL account");
        } catch (IllegalArgumentException ex) {

        }

        Account account = TestUtils.createAccount("josef", "pepa", false);

        em.getTransaction().begin();
        dao.create(account);
        em.getTransaction().commit();

        account.setName("newName");
        account.setPassword("nwe");
        account.setIsAdmin(true);

        dao.update(account);

        assertNotNull(account.getId());
        assertEquals(account.getName(), "newName");
        assertEquals(account.getPassword(), "nwe");
        assertEquals(account.getIsAdmin(), Boolean.TRUE);
    }

    public void testFindAll() {
        System.out.println("TEST Find All");

        Account ac1 = TestUtils.createAccount("josef", "pepa", false);
        Account ac2 = TestUtils.createAccount("jaroslav", "jarda", true);

        em.getTransaction().begin();
        dao.create(ac1);
        dao.create(ac2);
        em.getTransaction().commit();

        List<Account> accountList = dao.findAll();
        assertTrue(accountList.size() == 2);
        assertTrue(accountList.contains(ac1));
        assertTrue(accountList.contains(ac2));
    }

    public void testFindById() {
        System.out.println("TEST find by id");

        try {
            dao.findById(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Account account = TestUtils.createAccount("josef", "pepa", false);
        em.getTransaction().begin();
        dao.create(account);
        em.getTransaction().commit();
        assertNotNull(account.getId());
        account = dao.findById(account.getId());

        assertEquals(account.getName(), "josef");
        assertEquals(account.getPassword(), "pepa");
        assertEquals(account.getIsAdmin(), Boolean.FALSE);
    }

    public void testFindByName() {
        System.out.println("TEST find by name");

        try {
            dao.findByName(null);
            fail();
        } catch (DataAccessException ex) {
        }

        Account account = TestUtils.createAccount("josef", "pepa", false);
        em.getTransaction().begin();
        dao.create(account);
        em.getTransaction().commit();
        account = dao.findByName(account.getName());

        assertEquals(account.getName(), "josef");
        assertEquals(account.getPassword(), "pepa");
        assertEquals(account.getIsAdmin(), Boolean.FALSE);
    }
}
