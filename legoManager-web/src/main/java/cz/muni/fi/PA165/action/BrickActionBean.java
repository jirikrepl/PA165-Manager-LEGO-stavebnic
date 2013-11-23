package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 * brick actionbean
 *
 * @author Jiri Krepl
 */
// tohle url by melo byt jine nez url slozky z jsp, jinak to dela bordel
// predtim tam bylo "/brick/{event}" -- nefungovalo volani jine metody nez list s @defaultHandlerem
// ted je tu: "brick/{event}" -- funguje i pro delete ^_^
@UrlBinding("/bricks/{$event}")
public class BrickActionBean extends BaseActionBean implements ValidationErrorHandler{

    @SpringBean
    private BrickService brickService;
    private List<BrickDto> bricks;
    @SpringBean
    private BuildingKitService buildingKitService;

    @ValidateNestedProperties(
            value = {
                @Validate(on = {"createBrick"}, field = "name", required = true, maxlength = 50)
            }
    )
    private BrickDto brick; //one brick used for some operations (deletition, edit)

    /**
     * return all books for table in some jsp
     *
     * @return
     */
    public List<BrickDto> getBricks() {
        return bricks;
    }

    public BrickDto getBrick() {
        return brick;
    }

    /**
     * without setter, posting brick forms wouldnt work
     *
     * @param brick
     */
    public void setBrick(BrickDto brick) {
        this.brick = brick;
    }

    /**
     * downloads all bricks and redirect to brick/list.jsp list.jsp uses this
     * action bean = uses data and its methods
     *
     * @return resolution Redirects to list
     */
    @DefaultHandler
    public Resolution list() {
        bricks = brickService.findAll();
        return new ForwardResolution("/brick/brickList.jsp");
    }

    /**
     * method which deletes the brick
     *
     * @return resolution Redirects to list
     */
    public Resolution delete() {
        brick = brickService.findById(brick.getId());
        boolean canDelete = true;
        
        List<BuildingKitDto> buildingKitDtoList = buildingKitService.findAll();
        
        for(BuildingKitDto b : buildingKitDtoList) {
            Map<BrickDto, Integer> map = b.getBricks();
            if(map.containsKey(brick)) {
                canDelete = false;
            }
        }
        
        if(canDelete) {
            brickService.delete(brick.getId());
        } else {
            getContext().getMessages().add(new LocalizableError("brick.delete.contains"));
        }

        // returns back to list (in fact call lis() method from this class)
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     * creates brick
     *
     * @return
     */
    public Resolution createBrick() {
        brickService.create(brick);
        return new ForwardResolution(this.getClass(), "list");
    }

    /**
     * redirects to brick edit page
     *
     * @return
     */
    public Resolution openEditPage() {
        return new ForwardResolution("/brick/brickEdit.jsp");
    }

    /**
     * updated edited values of brick; after submitting edit form correct values
     * of brick are setted automatically from form
     *
     * @return
     */
    public Resolution updateBrick() {
        brickService.update(brick);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        bricks = brickService.findAll();
        return null;
    }
}
