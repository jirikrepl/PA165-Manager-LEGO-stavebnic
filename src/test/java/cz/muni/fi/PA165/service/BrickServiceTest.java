package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.AbstractIntegrationTest;
import cz.muni.fi.PA165.dao.BrickDao;
import cz.muni.fi.PA165.dao.DaoException;
import cz.muni.fi.PA165.dto.BrickDto;
import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.Color;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BrickServiceTest extends AbstractIntegrationTest {

    private BrickService brickService;
    private BrickDao brickDao;

    @Override
    protected void setUp() throws Exception {
        brickDao = mock(BrickDao.class);
        brickService = new BrickServiceImpl();
        brickService.setBrickDao(brickDao);
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateBrick() {
      System.out.println("testing createBrick on Service layer");  
      doThrow(new DaoException("error - brick cannot be null")).when(brickDao).create(null);
      
      try {
            brickService.create(null);
            fail();
        } catch (DaoException ex) {
        }
    }
//
//    public void testUpdateBrick() {
//    }

    public void testFindAll() {
        System.out.println("testing findAll on Service layer");
        List<Brick> bricks = new ArrayList<Brick>();
        when(brickDao.findAll()).thenReturn(bricks);
        
        List<BrickDto> mockedList = brickService.findAll();
        assertNotNull(mockedList);
    }

    public void testFindByColor() {
        System.out.println("Testing findByColor on Service layer");
        
        when(brickDao.findByColor(null)).thenThrow(new DaoException("Color cannot be null"));
        Brick blackBrick = new Brick();
        blackBrick.setColor(Color.BLACK);
        List<Brick> bricks = new ArrayList<Brick>();
        bricks.add(blackBrick);
        when(brickDao.findByColor(Color.BLACK)).thenReturn(bricks);
        
        bricks = new ArrayList<Brick>();
        when(brickDao.findByColor(Color.WHITE)).thenReturn(bricks);
        
        try {
            brickService.findByColor(null);
            fail();
        } catch (DaoException ex) {
        }

        assertNotNull(brickService.findByColor(Color.WHITE));
        assertEquals(brickService.findByColor(Color.BLACK).size(), 1);
    }
}
