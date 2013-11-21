package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import cz.muni.fi.PA165.api.service.CategoryService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
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

    public List<BuildingKitDto> getCategories() {
        buildingKits = service.findAll();
        return buildingKits;
    }

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/buildingKit/list.jsp");
    }

    public Resolution add() {
        return new ForwardResolution("/buildingKit/list.jsp");
    }
}