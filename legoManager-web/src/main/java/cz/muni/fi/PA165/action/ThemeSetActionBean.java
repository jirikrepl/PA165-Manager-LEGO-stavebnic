package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.api.service.CategoryService;
import cz.muni.fi.PA165.api.service.ThemeSetService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author Pavol Bako
 */
@UrlBinding("/themeset/{$event}")
public class ThemeSetActionBean extends BaseActionBean {

    @SpringBean
    protected ThemeSetService themeSetService;
    
    @SpringBean
    protected CategoryService categoryService;
    
    private List<ThemeSetDto> themeSets;
    private List<CategoryDto> categories;
    
    private ThemeSetDto themeSetDto;
    
    /**
     * get list of all theme sets
     * @return 
     */
    public List<ThemeSetDto> getThemeSets() {
        themeSets = themeSetService.findAll();
        return themeSets;
    }

    public List<CategoryDto> getCategories() {
        categories = categoryService.findAll();
        return categories;
    }
    
    /**
     * gets one theme set
     * @return 
     */
    public ThemeSetDto getThemeSetDto() {
        return themeSetDto;
    }

    /**
     * sets one theme set
     * @param themeSetDto 
     */
    public void setThemeSetDto(ThemeSetDto themeSetDto) {
        this.themeSetDto = themeSetDto;
    }

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/themeset/list.jsp");
    }

    public Resolution add() {
        themeSetService.create(themeSetDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution openEditPage() {
        //dto = service.findById(dto.getId());
        return new ForwardResolution("/themeset/edit.jsp");
    }

    public Resolution delete() {
        themeSetDto = themeSetService.findById(themeSetDto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

}
