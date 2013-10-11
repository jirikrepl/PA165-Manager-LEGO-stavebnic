package cz.muni.fi.PA165;

import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.Color;


/**
 * @author: Martin Rumanek
 * @version: 10/11/13
 */
public class TestUtils {
    public static Brick createBrick(String name, Color color, String description) {
        Brick brick = new Brick();
        brick.setName(name);
        brick.setColor(color);
        brick.setDescription(description);
        return brick;
    }

}
