package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.AbstractIntegrationTest;
import cz.muni.fi.PA165.dao.BrickDao;
import cz.muni.fi.PA165.dto.BrickDto;
import cz.muni.fi.PA165.mockDao.BrickDaoMock;
import java.util.List;
import static org.junit.Assert.*;

public class BrickServiceTest extends AbstractIntegrationTest {

    private BrickService brickService;

    @Override
    protected void setUp() throws Exception {
        BrickDaoMock mock = new BrickDaoMock();
        BrickDao brickDao = mock.getMockedDao();

        brickService = new BrickServiceImpl();
        brickService.setBrickDao(brickDao);
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

//    public void testCreateBrick() {
//    }
//
//    public void testUpdateBrick() {
//    }
    
    public void testFindAll() {
        System.out.println("find all");
        List<BrickDto> mockedList = brickService.findAll();
        assertNotNull(mockedList);
    }
}
