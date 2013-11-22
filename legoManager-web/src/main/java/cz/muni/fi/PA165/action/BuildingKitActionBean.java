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
        return new ForwardResolution("/buildingKit/buildingKitCreate.jsp");
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

    public List<BrickDto> getBricks() {
        return brickService.findAll();
    }

    public Resolution addBrick() {
        long idBuildingKit = buildingKit.getId();
        long idBricksKit = brick.getId();
        BuildingKitDto buildingKit = service.findById(idBuildingKit);
        BrickDto brickDto = brickService.findById(idBricksKit);
        Map<BrickDto, Integer> bricks = buildingKit.getBricks();
        bricks.put(brickDto, 1);

        return new RedirectResolution("/buildingKit/buildingKitManageBrick.jsp");
    }

    public Resolution openManageBrickPage() {
        this.buildingKit = service.findById(brick.getId());
        return new ForwardResolution("/buildingKit/buildingKitManageBrick.jsp");
    }


}