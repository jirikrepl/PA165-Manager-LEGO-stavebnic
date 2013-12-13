package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import cz.muni.fi.PA165.api.service.CategoryService;
import cz.muni.fi.PA165.api.service.ThemeSetService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: Martin Rumanek
 * @author Jiri Krepl
 * @version: 11/21/13
 */
@UrlBinding("/buildingKits/{$event}/{buildingKit.id}/{brick.id}")
public class BuildingKitActionBean extends BaseActionBean {

    @SpringBean
    private BuildingKitService service;
    @SpringBean
    private ThemeSetService themeSetService;
    @SpringBean
    private CategoryService categoryService;
    @SpringBean
    private BrickService brickService;

    private List<BuildingKitDto> buildingKits;
    private BrickDto brick;
    private Long categoryId;
    private Long themeSetId;


    @Validate(on = {"saveBrickCount"}, required = true, minvalue = 1, maxvalue = Integer.MAX_VALUE)
    private Integer brickCount;

    @ValidateNestedProperties(
            value = {
                    @Validate(on = {"createBuildingKit", "updateBuildingKit"}, field = "name", required = true, maxlength = 50),
                    @Validate(on = {"createBuildingKit", "updateBuildingKit"}, field = "yearFrom", required = true, minvalue = 0, maxvalue = 100),
                    @Validate(on = {"createBuildingKit", "updateBuildingKit"}, field = "price", required = true, minvalue = 0, maxvalue = 1000000),
            }
    )
    private BuildingKitDto buildingKit;

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setThemeSetId(Long themeSetId) {
        this.themeSetId = themeSetId;
    }

    public List<BuildingKitDto> getBuildingKits() {
        buildingKits = service.findAll();
        return buildingKits;
    }

    public List<CategoryDto> getCategories() {
        return categoryService.findAll();
    }

    public List<ThemeSetDto> getThemeSets() {
        return themeSetService.findAll();
    }

    public BuildingKitDto getBuildingKit() {
        return buildingKit;
    }

    public void setBuildingKit(BuildingKitDto buildingKitDto) {
        this.buildingKit = buildingKitDto;
    }

    public BrickDto getBrick() {
        return brick;
    }

    public void setBrick(BrickDto brick) {
        this.brick = brick;
    }

    public Integer getBrickCount() {
        return brickCount;
    }

    public void setBrickCount(Integer brickCount) {
        this.brickCount = brickCount;
    }


    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/buildingKit/buildingKitList.jsp");
    }

    public Resolution updateBuildingKit() {
        CategoryDto category = categoryService.findById(categoryId);
        buildingKit.setCategory(category);
        ThemeSetDto themeSet = themeSetService.findById(themeSetId);
        buildingKit.setThemeSet(themeSet);
        service.update(buildingKit);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution createBuildingKit() {
        CategoryDto category = categoryService.findById(categoryId);
        buildingKit.setCategory(category);
        ThemeSetDto themeSet = themeSetService.findById(themeSetId);
        buildingKit.setThemeSet(themeSet);
        service.create(buildingKit);
        return new RedirectResolution("/buildingKit/buildingKitList.jsp");
    }

    /**
     * get all brick from db
     *
     * @return List<BrickDto>
     */
    public List<BrickDto> getBricks() {
        // get all bricks in db
        List<BrickDto> allBricks = brickService.findAll();

        // get all bricks used by this building kit
        Map<BrickDto, Integer> usedBricks = getBricksSaved();
        Set<BrickDto> usedBricksSet = usedBricks.keySet();
        //List<BrickDto> usedBricksList = new ArrayList<BrickDto>(usedBricksSet);

        // remove all used bricks
        allBricks.removeAll(usedBricksSet);

        return allBricks;
    }

    /**
     * get all bricks from building kit
     *
     * @return Map<BrickDto, Integer>
     */
    public Map<BrickDto, Integer> getBricksSaved() {
        long idBuildingKit = buildingKit.getId();
        BuildingKitDto buildingKitDto = service.findById(idBuildingKit);
        return buildingKitDto.getBricks();
    }

    /**
     * add count of bricks to building kit
     *
     * @return buildingKitManageBrick.jsp
     */
    public Resolution saveBrickCount() {
        if (brick == null) {
            return new ForwardResolution("/buildingKit/buildingKitManageBrick.jsp");
        }
        BuildingKitDto buildingKitDto = service.findById(buildingKit.getId());
        BrickDto brickDto = brickService.findById(brick.getId());

        // get bricks of some building kit
        Map<BrickDto, Integer> bricks = buildingKitDto.getBricks();

        bricks.put(brickDto, brickCount);
        service.update(buildingKitDto);
        brickCount = null;
        return new RedirectResolution(this.getClass(), "openManageBrickPage").addParameter("buildingKit.id", buildingKit.getId());
    }

    /**
     * opens building kit edit page
     *
     * @return ForwardResolution to edit.jsp
     */
    public Resolution openEditPage() {
        buildingKit = service.findById(buildingKit.getId());
        return new ForwardResolution("/buildingKit/edit.jsp");
    }

    /**
     * removes brick from building kit
     *
     * @return to buildingKitManageBrick.jsp
     */
    public Resolution deleteBrick() {
        long idBuildingKit = buildingKit.getId();
        BuildingKitDto buildingKit = service.findById(idBuildingKit);
        BrickDto brickDto = brickService.findById(brick.getId());
        Map<BrickDto, Integer> bricks = buildingKit.getBricks();
        bricks.remove(brickDto);
        service.update(buildingKit);

        return new RedirectResolution(this.getClass(), "openManageBrickPage").addParameter("buildingKit.id", buildingKit.getId());
    }

    /**
     * deletes building kit
     *
     * @return return to list of building kits
     */
    public Resolution delete() {
        long idBuildingKit = buildingKit.getId();
        service.delete(idBuildingKit);
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     * opens brick management page
     * link to this page is 'Brick Management' (in building kit table)
     *
     * @return buildingKitManageBrick.jsp
     */
    public Resolution openManageBrickPage() {
        this.buildingKit = service.findById(buildingKit.getId());
        return new ForwardResolution("/buildingKit/buildingKitManageBrick.jsp");
    }

    /**
     * opens page to edit bricks count
     *
     * @return buildingKitBrickCountEdit.jsp
     */
    public Resolution openBrickCountEdit() {
        buildingKit = service.findById(buildingKit.getId());
        for (BrickDto brickDto : buildingKit.getBricks().keySet()) {
            if (brick.getId().equals(brickDto.getId())) {
                this.brickCount = buildingKit.getBricks().get(brickDto);
                break;
            }
        }

        return new ForwardResolution("/buildingKit/buildingKitBrickCountEdit.jsp");
    }


}