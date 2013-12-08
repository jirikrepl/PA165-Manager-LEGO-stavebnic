package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.api.service.*;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import java.util.*;

/**
 * @author: Martin Rumanek
 * @version: 11/21/13
 */
@UrlBinding("/buildingKits/{$event}")
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
    private Long deletedBrickId;
    private Integer brickCount;

    @ValidateNestedProperties(
            value = {
                    @Validate(on = {"createBuildingKit", "updateBuildingKit"}, field = "name", required = true, maxlength = 50),
                    @Validate(on = {"createBuildingKit", "updateBuildingKit"}, field = "yearFrom", required = true, minvalue = 0, maxvalue = 100),
                    @Validate(on = {"createBuildingKit", "updateBuildingKit"}, field = "price", required = true, minvalue = 0),
                    @Validate(on = {"saveBrickCount"}, field = "brickCount", required = true, minvalue = 0)
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

    public Long getDeletedBrickId() {
        return deletedBrickId;
    }

    public void setDeletedBrickId(Long deletedBrickId) {
        this.deletedBrickId = deletedBrickId;
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
        deletedBrickId = null;
        brickCount = null;
        return new ForwardResolution("/buildingKit/buildingKitManageBrick.jsp");
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
        BrickDto brickDto = brickService.findById(deletedBrickId);
        Map<BrickDto, Integer> bricks = buildingKit.getBricks();
        bricks.remove(brickDto);
        service.update(buildingKit);

        return new ForwardResolution("/buildingKit/buildingKitManageBrick.jsp");
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
        return new ForwardResolution("/buildingKit/buildingKitBrickCountEdit.jsp");
    }


}