package cz.muni.fi.PA165.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for brick
 *
 * @author jirikrepl
 */
public class BrickDAO {

    public void createBrick(Brick brick) {
    }

    public void removeBrick(Brick brick) {
    }

    public void updateBrick(Brick brick) {
    }
    
    public List<Brick> findAll() {
        List<Brick> bricks = new ArrayList<Brick>();
        return bricks;
    }
    
    public List<Brick> findByColor(Color color) {
        List<Brick> bricks = new ArrayList<Brick>();
        return bricks;
    }
    
    public Brick findByName(String name) {
        return new Brick();
    }
}
