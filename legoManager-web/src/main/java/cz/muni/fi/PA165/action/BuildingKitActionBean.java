package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import cz.muni.fi.PA165.api.service.CategoryService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import java.util.List;

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

    public List<BrickDto> getBricks() {
        return brickService.findAll();
    }

    private BuildingKitDto buildingKitDto;

    private BrickDto brick;

    public BrickDto getBrick() {
        return brick;
    }

    public void setBrick(BrickDto brick) {
        this.brick = brick;
    }

    public BuildingKitDto getBuildingKit() {
        return buildingKitDto;
    }

    public void setBuildingKit(BuildingKitDto buildingKitDto) {
        this.buildingKitDto = buildingKitDto;
    }

    public Resolution addBuildingKit() {
        service.create(buildingKitDto);
        return new ForwardResolution("/buildingKit/buildingKitCreate.jsp");
    }

    public Resolution addBrick() {

        return new RedirectResolution("/buildingKit/buildingKitManageBrick.jsp");
    }

    public Resolution openManageBrickPage() {
        return new ForwardResolution("/buildingKit/buildingKitManageBrick.jsp");
    }


    public Resolution save() {
        service.update(buildingKitDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/buildingKit/buildingKitList.jsp");
    }

    public Resolution createBuildingKit() {
        service.create(buildingKitDto);
        return new RedirectResolution("/buildingKit/buildingKitList.jsp");
    }
}