package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.AbstractIntegrationTest;
import cz.muni.fi.PA165.dao.BrickDao;
import cz.muni.fi.PA165.entity.Brick;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

public class BrickServiceTest extends AbstractIntegrationTest {

//    @Autowired
//    private BrickDao brickDao;

    @Test
    public void testCreateBrick() {
        Brick blah = new Brick();
//        Customer customer = bankService.createNewCustomer("Firstname", "Lastname");
//        assertNotNull(customer.getId());
        assertNotNull(blah);
    }

    @Test
    public void testUpdateBrick() {
        int x = 1;
//        bankService.createNewCustomer("Firstname1", "Lastname1");
//        bankService.createNewCustomer("Firstname2", "Lastname2");
//        
//        List<Customer> customersList = bankService.getAllCustomers();
//        assertEquals(2, customersList.size());
    }
}
