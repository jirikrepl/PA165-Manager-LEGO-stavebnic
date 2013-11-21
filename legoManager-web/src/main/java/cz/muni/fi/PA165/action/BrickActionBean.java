package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.service.BrickService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 * brick actionbean
 * 
 * @author Jiri Krepl
 */

// tohle url by melo byt jine nez url slozky z jsp, jinak to dela bordel
// predtim tam bylo "/brick/{event}" -- nefungovalo volani jine metody nez list s @defaultHandlerem
// ted je tu: "brick/{event}" -- funguje i pro delete ^_^
@UrlBinding("/bricks/{$event}")
public class BrickActionBean extends BaseActionBean {
    
    @SpringBean
    private BrickService brickService;
    private List<BrickDto> bricks;
    private BrickDto brick; //one brick used for some operations (deletition, edit)
    
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
     * @return resolution Redirects to list 
     */
    @DefaultHandler
    public Resolution list() {
        bricks = brickService.findAll();
        return new ForwardResolution("/brick/brickList.jsp");
    }
    
    /**
     * method which deletes the brick
     * @return resolution Redirects to list
     */
    public Resolution delete() {
        // get id from form in jsp
        String id = getContext().getRequest().getParameter("brick.id");
        brick = brickService.findById(Long.parseLong(id)); // find this brickDto by that and deletes it
        brickService.delete(brick.getId());
        // getContext().getMessages().add(new LocalizableMessage("book.delete.message",escapeHTML(book.getTitle()),escapeHTML(book.getAuthor())));
        
        // returns back to list (in fact call lis() method from this class)
        return new RedirectResolution(this.getClass(), "list");
    }
    
    /**
     * 
     * @return 
     */
    public Resolution brickCreate() {
        return new ForwardResolution("/brick/brickCreate.jsp");
    }
}
