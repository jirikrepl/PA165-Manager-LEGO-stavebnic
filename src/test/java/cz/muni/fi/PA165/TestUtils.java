package cz.muni.fi.PA165;

import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.BuildingKit;
import cz.muni.fi.PA165.domain.Color;
import java.math.BigDecimal;


/**
 * @author: Martin Rumanek
 * @author: Pavol Bako
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
    
    public static BuildingKit createBuildingKit(String name, String description, BigDecimal price, int year){
        BuildingKit buildingKit = new BuildingKit();
        buildingKit.setName(name);
        buildingKit.setDescription(description);
        buildingKit.setPrice(price);
        buildingKit.setYearFrom(year);
        
        return buildingKit;
    }

}
