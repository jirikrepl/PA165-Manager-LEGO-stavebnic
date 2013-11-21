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

@UrlBinding("/brick/{$event}/{brick.id}")
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
        return new ForwardResolution("/brick/list.jsp");
    }
    
    /**
     * method which deletes the brick
     * @return resolution Redirects to list
     */
    public Resolution delete() {
        //only id is filled by the form
//        book = bookLibrary.getBook(book.getId());
//        bookLibrary.deleteBook(book.getId());
//        getContext().getMessages().add(new LocalizableMessage("book.delete.message",escapeHTML(book.getTitle()),escapeHTML(book.getAuthor())));
//        return new RedirectResolution(this.getClass(), "list");
        
        brick = brickService.findById(brick.getId());
        brickService.delete(brick.getId());

        // getContext().getMessages().add(new LocalizableMessage("book.delete.message",escapeHTML(book.getTitle()),escapeHTML(book.getAuthor())));
        
        // returns back to list (in fact call lis() method from this class)
        return new RedirectResolution(this.getClass(), "list");
    }
}
