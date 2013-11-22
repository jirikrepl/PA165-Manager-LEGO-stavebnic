package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import cz.muni.fi.PA165.api.service.CategoryService;
import cz.muni.fi.PA165.entity.BuildingKit;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

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
    List<BuildingKitDto> buildingKits;

    @SpringBean
    private BrickService brickService;

    public List<BuildingKitDto> getBuildingKits() {
        buildingKits = service.findAll();
        return buildingKits;
    }



    private BuildingKitDto buildingKit;

    public BuildingKitDto getBuildingKit() {
        return buildingKit;
    }

    public void setBuildingKit(BuildingKitDto buildingKitDto) {
        this.buildingKit = buildingKitDto;
    }

    public Resolution addBuildingKit() {
        service.create(buildingKit);
        return new ForwardResolution("/buibuildingldingKit/buildingKitCreate.jsp");
    }




    public Resolution save() {
        service.update(buildingKit);
        return new RedirectResolution(this.getClass(), "list");
    }

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/buildingKit/buildingKitList.jsp");
    }

    public Resolution createBuildingKit() {
        service.create(buildingKit);
        return new RedirectResolution("/buildingKit/buildingKitList.jsp");
    }



    //sprava kosticek
    private BrickDto brick;

    public BrickDto getBrick() {
        return brick;
    }

    public void setBrick(BrickDto brick) {
        this.brick = brick;
    }

    public Integer getBrickIdDelete() {
        return brickIdDelete;
    }

    public List<BrickDto> getBricks() {
        return brickService.findAll();
    }

    public Map<BrickDto, Integer> getBricksSaved() {
        long idBuildingKit = buildingKit.getId();
        BuildingKitDto buildingKitDto = service.findById(idBuildingKit);
        return buildingKitDto.getBricks();
    }

    private Integer brickIdDelete;
    

    public void setBrickIdDelete(Integer brickIdDelete) {
        this.brickIdDelete = brickIdDelete;
    }

    private Integer brickCount;

    public Integer getBrickCount() {
        return brickCount;
    }

    public void setBrickCount(Integer brickCount) {
        this.brickCount = brickCount;
    }

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
     * redirects to kit edit page
     * @return ForwardResolution
     */
    public Resolution openEditPage() {
        buildingKit = service.findById(buildingKit.getId());
        return new ForwardResolution("/buildingKit/buildingKitManagerBrick.jsp");
    }

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

    public Resolution delete() {
        long idBuildingKit = buildingKit.getId();
        service.delete(idBuildingKit);
        return new ForwardResolution("/buildingKit/buildingKitList.jsp");
    }

    public Resolution openManageBrickPage() {
        this.buildingKit = service.findById(brick.getId());
        return new ForwardResolution("/buildingKit/buildingKitManageBrick.jsp");
    }


}