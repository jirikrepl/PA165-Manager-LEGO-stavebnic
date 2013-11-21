package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.service.BuildingKitService;
import cz.muni.fi.PA165.dto.BuildingKitDto;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author Tomas Kopecky
 */
@UrlBinding("/buildingkit/list.jsp")
public class BuildingKitActionBean extends BaseActionBean {
    
    @SpringBean
    private BuildingKitService buildingKitService;
    
    private List<BuildingKitDto> buildingKits; 
    
    @DefaultHandler
    public Resolution list() {
        //buildingKits = buildingKitService.findAll();
        return new ForwardResolution("/buildingkit/list.html");
    }
    
    public List<BuildingKitDto> getBuildingKits() {
        return buildingKits;
    }
}
