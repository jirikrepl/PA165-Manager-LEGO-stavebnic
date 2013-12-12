package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.fi.PA165.api.service.Color;
import cz.muni.fi.PA165.dao.BrickDao;
import cz.muni.fi.PA165.daoDtoConversion.BrickConversion;
import cz.muni.fi.PA165.entity.Brick;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author: Martin Rumanek
 * @version: 1/11/13
 */
public class BrickServiceTest {

    private BrickService brickService;
    private BrickDao brickDao;

    @Before
    public void setUp() throws Exception {
        brickDao = mock(BrickDao.class);
        BrickServiceImpl brickServiceImpl = new BrickServiceImpl();
        brickServiceImpl.setBrickDao(brickDao);
        brickService = brickServiceImpl;
    }

    @Test
    public void testCreate() {
        try {
            brickService.create(null);
            fail();
        } catch (DataAccessException ex) {
        }

        BrickDto brickDto = new BrickDto("TestBrick", Color.BLACK, "Some description");
        doNothing().when(brickDao).update(BrickConversion.convertToEntity(brickDto));


        brickService.create(brickDto);
        verify(brickDao).create(BrickConversion.convertToEntity(brickDto));
        verify(brickDao, never()).update(any(Brick.class));
        verify(brickDao, never()).delete(anyLong());
    }

    @Test
    public void testUpdate() {
        try {
            brickService.update(null);
            fail();
        } catch (DataAccessException ex) {
        }

        BrickDto brickDto = new BrickDto("TestBrick", Color.BLACK, "Some description");
        doNothing().when(brickDao).update(BrickConversion.convertToEntity(brickDto));

        brickService.update(brickDto);

        verify(brickDao).update(BrickConversion.convertToEntity(brickDto));
        verify(brickDao, never()).delete(anyLong());
        verify(brickDao, never()).create(any(Brick.class));
    }

    @Test
    public void testDelete() {
        try {
            brickService.delete(null);
            fail();
        } catch (DataAccessException ex) {
        }

        brickService.delete(new Long(1));

        verify(brickDao).delete(new Long(1));
        verify(brickDao, never()).update(any(Brick.class));
        verify(brickDao, never()).create(any(Brick.class));
    }

    @Test
    public void testFindById() {
        doThrow(new DataAccessException("Object not found") {}).when(brickDao).findById(new Long(-1));
        try {
            brickService.findById(null);
            fail();
        } catch (DataAccessException e) {
        }

        try {
            brickService.findById(new Long(-1));
            fail();
        } catch (DataAccessException e) {
        }

        BrickDto brickDto = new BrickDto("TestBrick", Color.BLACK, "Some description");
        when(brickDao.findById(new Long(1l))).thenReturn(BrickConversion.convertToEntity(brickDto));
        assertEquals(brickDto, brickService.findById(new Long(1l)));

        verify(brickDao).findById(1l);
        verify(brickDao,never()).create(BrickConversion.convertToEntity(brickDto));
        verify(brickDao,never()).update(BrickConversion.convertToEntity(brickDto));
    }

    @Test
    public void testFindAll() {
        when(brickDao.findAll()).thenReturn(new ArrayList<Brick>());
        ArrayList<BrickDto> list = new ArrayList<BrickDto>();
        assertEquals(brickService.findAll().size(), 0);

        BrickDto[] bricksDto = new BrickDto[4];
        Brick[] bricks = new Brick[4];
        bricksDto[0] =  new BrickDto("TestBrick", Color.BLACK, "Some description");
        bricksDto[1] =  new BrickDto("TestBrick", Color.BRIGHTBLUE, "Some description");
        bricksDto[2] =  new BrickDto("TestBrick", Color.MEDIUMLILAC, "Some description");
        bricksDto[3] =  new BrickDto("TestBrick", Color.BLACK, "Some description");

        for (int i=0; i<bricksDto.length; i++) {
            bricks[i] = BrickConversion.convertToEntity(bricksDto[i]);
        }

        when(brickDao.findAll()).thenReturn(Arrays.asList(bricks));

        List<BrickDto> listBricks = brickService.findAll();

        for (int i=0; i<bricksDto.length; i++) {
            assertTrue(listBricks.contains(bricksDto[i]));
        }
        assertEquals(listBricks.size(), 4);
    }

    @Test
    public void testFindByName() {
        try {
            brickService.findByName(null);
            fail();
        } catch (DataAccessException e) {
        }

        BrickDto brickDto =  new BrickDto("TestBrick", Color.BLACK, "Some description");
        List bricks = new ArrayList<Brick>();
        bricks.add(BrickConversion.convertToEntity(brickDto));

        when(brickDao.findByName("TestBrick")).thenReturn(bricks);
        when(brickDao.findByName("Brick404")).thenReturn(new ArrayList<Brick>());

        assertTrue(brickService.findByName("TestBrick").contains(brickDto));
        assertEquals(brickService.findByName("TestBrick").size(), 1);
        assertEquals(brickService.findByName("Brick404").size(), 0);
    }

    @Test
    public void testFindByColor() {
        try {
            brickService.findByColor(null);
            fail();
        } catch (DataAccessException e) {
        }

        BrickDto brickDto =  new BrickDto("TestBrick", Color.BLACK, "Some description");
        List bricks = new ArrayList<Brick>();
        bricks.add(BrickConversion.convertToEntity(brickDto));

        when(brickDao.findByColor(Color.BLACK)).thenReturn(bricks);
        when(brickDao.findByColor(Color.BRICKYELLOW)).thenReturn(new ArrayList<Brick>());

        assertTrue(brickService.findByColor(Color.BLACK).contains(brickDto));
        assertEquals(brickService.findByColor(Color.BLACK).size(), 1);
        assertEquals(brickService.findByColor(Color.BRICKYELLOW).size(), 0);
    }






}
