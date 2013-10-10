package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dao.BrickDao;
import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.Color;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {      
        // test
        Brick b = new Brick();
        b.setColor(Color.WHITE);
        b.setName("kosticka");
        b.setDescription("nejaka bila kosticka");
        
        BrickDao bDao = new BrickDao();
        bDao.createBrick(b);
    }
}
