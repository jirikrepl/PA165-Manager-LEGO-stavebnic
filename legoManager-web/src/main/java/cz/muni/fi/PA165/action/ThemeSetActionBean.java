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
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 *
 * @author Pavol Bako
 */
@UrlBinding("/themesets/{$event}")
public class ThemeSetActionBean extends BaseActionBean {

    @SpringBean
    protected ThemeSetService themeSetService;

    @SpringBean
    protected CategoryService categoryService;

    private List<ThemeSetDto> themeSets;
    private List<CategoryDto> categories;

    @ValidateNestedProperties(
    value= {@Validate(on={"createThemeSet", "updateThemeSet"}, field="name", required = true),
            @Validate(on={"createThemeSet", "updateThemeSet"}, field="price", required = true, minvalue = 0)})
    private ThemeSetDto themeSetDto;
    private Long categoryId;
    private Long themeSetId;

    public void setThemeSetId(Long themeSetId) {
        this.themeSetId = themeSetId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * get list of all theme sets
     *
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
     *
     * @return
     */
    public ThemeSetDto getThemeSetDto() {
        return themeSetDto;
    }

    /**
     * sets one theme set
     *
     * @param themeSetDto
     */
    public void setThemeSetDto(ThemeSetDto themeSetDto) {
        this.themeSetDto = themeSetDto;
    }

    @DefaultHandler
    public Resolution list() {
        return new RedirectResolution("/themeset/list.jsp");
    }

    public Resolution createThemeSet() {
        CategoryDto category = categoryService.findById(categoryId);
        themeSetDto.setCategoryDto(category);
        themeSetService.create(themeSetDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution openEditPage() {
        return new ForwardResolution("/themeset/edit.jsp");
    }

    public Resolution deleteThemeSet() {
        themeSetService.delete(themeSetId);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution updateThemeSet() {
        CategoryDto category = categoryService.findById(categoryId);
        themeSetDto.setCategoryDto(category);
        themeSetService.update(themeSetDto);
        return new RedirectResolution(this.getClass(), "list");
    }

}
