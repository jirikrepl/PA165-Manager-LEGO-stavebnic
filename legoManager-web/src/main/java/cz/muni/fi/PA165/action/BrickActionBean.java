package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.service.BrickService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

@UrlBinding("/brick/{event}/{brick.id}")
public class BrickActionBean extends BaseActionBean {
    
    @SpringBean
    private BrickService brickService;
    private List<BrickDto> bricks;
    
    /**
     * return all books for table in some jsp
     * @return 
     */
    public List<BrickDto> getBricks() {
        return bricks;
    }

    /**
     * downloads all bricks and redirect to brick/list.jsp
     * list.jsp uses this action bean = uses data and its methods
     * @return 
     */
    @DefaultHandler
    public Resolution list() {
        bricks = brickService.findAll();
        return new ForwardResolution("/brick/list.jsp");
    }
}
