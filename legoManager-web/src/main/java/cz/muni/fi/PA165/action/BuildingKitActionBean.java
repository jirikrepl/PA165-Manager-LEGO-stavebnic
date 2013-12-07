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
    private Long categoryId;
    private Long themesetId;
    private BrickDto brick;
    private Integer brickIdDelete;
    private Integer brickCount;

    @ValidateNestedProperties(
            value = {
                    @Validate(on = {"createBuildingKit", "updateBuildingKit"}, field = "name", required = true, maxlength = 50),
                    @Validate(on = {"createBuildingKit", "updateBuildingKit"}, field = "yearFrom", required = true, minvalue = 0, maxvalue = 100),
                    @Validate(on = {"createBuildingKit", "updateBuildingKit"}, field = "price", required = true, minvalue = 0)
            }
    )
    private BuildingKitDto buildingKit;

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setThemesetId(Long themesetId) {
        this.themesetId = themesetId;
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

    public Integer getBrickIdDelete() {
        return brickIdDelete;
    }

    public void setBrickIdDelete(Integer brickIdDelete) {
        this.brickIdDelete = brickIdDelete;
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
        ThemeSetDto themeSet = themeSetService.findById(themesetId);
        buildingKit.setThemeSet(themeSet);
        service.update(buildingKit);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution createBuildingKit() {
        CategoryDto category = categoryService.findById(categoryId);
        buildingKit.setCategory(category);
        ThemeSetDto themeSet = themeSetService.findById(themesetId);
        buildingKit.setThemeSet(themeSet);
        service.create(buildingKit);
        return new RedirectResolution("/buildingKit/buildingKitList.jsp");
    }

    /**
     * get all brick from db
     * @return List<BrickDto>
     */
    public List<BrickDto> getBricks() {
        return brickService.findAll();
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
     * add brick to building kit
     *
     * @return buildingKitManageBrick.jsp
     */
    public Resolution addBrick() {
        long idBuildingKit = buildingKit.getId();
        long idBricksKit = brick.getId();
        BuildingKitDto buildingKit = service.findById(idBuildingKit);
        BrickDto brickDto = brickService.findById(idBricksKit);
        Map<BrickDto, Integer> bricks = buildingKit.getBricks();

        Integer count;
        if (bricks.containsKey(brickDto)) {
            count = bricks.get(brickDto);
        } else {
            count = 0;
        }

        bricks.put(brickDto, count + brickCount);
        service.update(buildingKit);
        brickIdDelete = null;
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
        long idBricksKit = new Long(brickIdDelete);
        BuildingKitDto buildingKit = service.findById(idBuildingKit);
        BrickDto brickDto = brickService.findById(idBricksKit);
        Map<BrickDto, Integer> bricks = buildingKit.getBricks();
        bricks.remove(brickDto);
        service.update(buildingKit);

        return new ForwardResolution("/buildingKit/buildingKitManageBrick.jsp");
    }

    /**
     * deletes building kit
     *
     * @return return to list of buildink kits
     */
    public Resolution delete() {
        long idBuildingKit = buildingKit.getId();
        service.delete(idBuildingKit);
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     * opens brick management page
     *
     * @return buildingKitManageBrick.jsp
     */
    public Resolution openManageBrickPage() {
        this.buildingKit = service.findById(buildingKit.getId());
        return new ForwardResolution("/buildingKit/buildingKitManageBrick.jsp");
    }


    /**
     * opens brick edit page
     *
     * @return buildingKitBrickCountEdit.jsp
     */
    public Resolution openBrickCountEdit() {
        return new ForwardResolution("/buildingKit/buildingKitBrickCountEdit.jsp");
    }


}