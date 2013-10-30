package cz.muni.fi.PA165.mockDao;

import cz.muni.fi.PA165.dao.BrickDao;
import cz.muni.fi.PA165.entity.Brick;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


public class BrickDaoMock {
    
    private BrickDao mockedDao;

    public BrickDaoMock() {
        this.mockedDao = mock(BrickDao.class);
        mockFindAll();
    }

    public BrickDao getMockedDao() {
        return mockedDao;
    }
    
    /**
     * mock findAll method 
     */
    private void mockFindAll(){
        Brick first = new Brick();
        Brick second = new Brick();
        List<Brick> bricks = new ArrayList<Brick>();
        bricks.add(first);
        bricks.add(second);
        
        when(mockedDao.findAll()).thenReturn(bricks);
    }
}
