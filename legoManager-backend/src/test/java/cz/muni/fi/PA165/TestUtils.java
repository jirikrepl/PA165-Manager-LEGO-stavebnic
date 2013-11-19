package cz.muni.fi.PA165;

import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.BuildingKit;
import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.api.service.Color;
import cz.muni.fi.PA165.entity.ThemeSet;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author: Martin Rumanek
 * @author: Pavol Bako
 * @author: Tomas Kopecky
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
    
    public static BuildingKit createBuildingKit(String name, String description, BigDecimal price, int year, List<Brick> listBricks){
        BuildingKit buildingKit = new BuildingKit();
        buildingKit.setName(name);
        buildingKit.setDescription(description);
        buildingKit.setPrice(price);
        buildingKit.setYearFrom(year);
        buildingKit.setBricks(listBricks);

        return buildingKit;
    }
    

    public static ThemeSet createThemeSet(String name,
            String description, BigDecimal price,
            List<BuildingKit> listKits, Category category){
        ThemeSet themeSet = new ThemeSet();
        //themeSet.setName(name);
        themeSet.setDescription(description);
        themeSet.setPrice(price);
        //themeSet.setBuildingKits(listKits);
        //themeSet.setCategory(category);

        return themeSet;
    }

    public static Category createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        
        return category;

    }

}
