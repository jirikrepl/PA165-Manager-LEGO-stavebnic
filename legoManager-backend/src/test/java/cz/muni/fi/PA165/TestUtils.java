package cz.muni.fi.PA165;

import cz.muni.fi.PA165.api.service.Color;
import cz.muni.fi.PA165.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


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
    
    public static BuildingKit createBuildingKit(String name, String description, BigDecimal price, int year, Map<Brick, Integer> mapBricks){
        BuildingKit buildingKit = new BuildingKit();
        buildingKit.setName(name);
        buildingKit.setDescription(description);
        buildingKit.setPrice(price);
        buildingKit.setYearFrom(year);
        buildingKit.setBricks(mapBricks);

        return buildingKit;
    }
    

    public static ThemeSet createThemeSet(String name,
            String description, BigDecimal price,
            List<BuildingKit> listKits, Category category){
        ThemeSet themeSet = new ThemeSet();
        themeSet.setDescription(description);
        themeSet.setPrice(price);
        return themeSet;
    }

    public static Category createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        
        return category;

    }

    public static Account createAccount(String name, String password, Boolean isAdmin) {
        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
        account.setIsAdmin(isAdmin);

        return account;

    }

}
