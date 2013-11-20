package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.muni.PA165.api.dto.BrickDto;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

@UrlBinding("/brick/list.jsp")
public class BrickActionBean extends BaseActionBean {
    
    @SpringBean
    private BrickService brickService;
    
    private List<BrickDto> bricks; 
    
    @DefaultHandler
    public Resolution list() {
        //bricks = brickService.findAll();
        return new ForwardResolution("/brick/list.jsp");
    }

}
