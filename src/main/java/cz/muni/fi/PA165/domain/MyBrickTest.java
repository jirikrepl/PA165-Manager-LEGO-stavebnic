package cz.muni.fi.PA165.domain;

import cz.muni.fi.PA165.dao.BrickDao;
import java.util.List;

public class MyBrickTest {

    /**
     * example and test of brick entity
     * just for test purposes
     */
    public static void brickTest() {
        Brick b1 = new Brick();
        b1.setColor(Color.WHITE);
        b1.setName("Plate 1 x 2");
        b1.setDescription("Plate");

        Brick b2 = new Brick();
        b2.setColor(Color.BLACK);
        b2.setName("Plate 1 x 2");
        b2.setDescription("Plate");

        Brick b3 = new Brick();
        b3.setColor(Color.BRIGHTBLUE);
        b3.setName("Plate 1 x 2");
        b3.setDescription("Plate");

        Brick b4 = new Brick();
        b4.setColor(Color.WHITE);
        b4.setName("Plate 1 x 4");
        b4.setDescription("Plate");

        Brick b5 = new Brick();
        b5.setColor(Color.LIGHTPURPLE);
        b5.setName("Plate 1 x 4");
        b5.setDescription("Plate");

        Brick b6 = new Brick();
        b6.setColor(Color.COOLYELLOW);
        b6.setName("Plate 1 x 4");
        b6.setDescription("Plate");

        Brick b7 = new Brick();
        b7.setColor(Color.WHITE);
        b7.setName("Slope 45 1 x 2");
        b7.setDescription("Slope");

        Brick b8 = new Brick();
        b8.setColor(Color.NOUGAT);
        b8.setName("Slope 45 1 x 2");
        b8.setDescription("Slope");

        Brick b9 = new Brick();
        b9.setColor(Color.WHITE);
        b9.setName("Brick Round 1 x 1");
        b9.setDescription("Brick Round 1 x 1 with Open Stud");

        Brick b10 = new Brick();
        b10.setColor(Color.NOUGAT);
        b10.setName("Brick Round 1 x 1");
        b10.setDescription("Brick Round 1 x 1 with Open Stud");

        BrickDao brickDao = new BrickDao();

        brickDao.storeBrick(b1);
        brickDao.storeBrick(b2);
        brickDao.storeBrick(b3);
        brickDao.storeBrick(b4);
        brickDao.storeBrick(b5);
        brickDao.storeBrick(b6);
        brickDao.storeBrick(b7);
        brickDao.storeBrick(b8);
        brickDao.storeBrick(b9);
        brickDao.storeBrick(b10);

        List<Brick> bList = brickDao.findAll();
        System.err.println("\nFind all bricks in db table: ");
        for (int i = 0; i < bList.size(); i++) {
            System.err.println(bList.get(i).toString());
        }
        
        bList = brickDao.findByColor(Color.WHITE);
        System.err.println("\nFind all brick by desired COLOR: ");
        for (int i = 0; i < bList.size(); i++) {
            System.err.println(bList.get(i).toString());
        }
        
        bList = brickDao.findByName("Plate 1 x 2");
        System.err.println("\nFind all brick by desired NAME: ");
        for (int i = 0; i < bList.size(); i++) {
            System.err.println(bList.get(i).toString());
        }
        
        brickDao.close();
    }
}
