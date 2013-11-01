package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.dao.BrickDao;
import cz.muni.fi.PA165.daoDtoConversion.BrickConversion;
import cz.muni.fi.PA165.daoDtoConversion.ThemeSetConversion;
import cz.muni.fi.PA165.dto.BrickDto;
import cz.muni.fi.PA165.dto.ThemeSetDto;
import cz.muni.fi.PA165.entity.Color;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class BrickServiceTest {

    private BrickService brickService;
    private BrickDao brickDao;

    @Before
    public void setUp() throws Exception {
        brickDao = mock(BrickDao.class);
        brickService = new BrickServiceImpl();
        brickService.setBrickDao(brickDao);
    }

    @Test
    public void testCreate() {
        try {
            brickService.create(null);
            fail();
        } catch (DataAccessException ex) {
        }
        verify(brickDao, never()).create(null);
        verify(brickDao, never()).update(null);

        BrickDto brickDto = TestUtils.createBrickDto("TestBrick", Color.BLACK, "Some description");;
        doNothing().when(brickDao).update(BrickConversion.convertToEntity(brickDto));


        brickService.create(brickDto);
        verify(brickDao).create(BrickConversion.convertToEntity(brickDto));
        verify(brickDao, never()).delete(anyLong());
    }

    @Test
    public void testUpdate() {
        try {
            brickService.update(null);
            fail();
        } catch (DataAccessException ex) {
        }
        //verify(dao).update(null);
        //verify(brickDao)

    }


}
