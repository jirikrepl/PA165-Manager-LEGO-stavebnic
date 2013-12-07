package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import cz.muni.fi.PA165.api.service.CategoryService;
import cz.muni.fi.PA165.api.service.ThemeSetService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import java.util.List;

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

    @SpringBean
    protected BuildingKitService buildingKitService;

    private List<ThemeSetDto> themeSets;
    private List<CategoryDto> categories;

    @ValidateNestedProperties(
    value= {@Validate(on={"createThemeSet", "updateThemeSet"}, field="name", required = true),
            @Validate(on={"createThemeSet", "updateThemeSet"}, field="price", required = true, minvalue = 0)})
    private ThemeSetDto themeSetDto;
    private Long categoryId;

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
        themeSetService.create(themeSetDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution openEditPage() {
        themeSetDto = themeSetService.findById(themeSetDto.getId());
        return new ForwardResolution("/themeset/edit.jsp");
    }

    public Resolution deleteThemeSet() {
        // prevent removal of themeset, which includes some building kits
        List<BuildingKitDto> kitList = buildingKitService.findByThemeSet(themeSetDto);

        // list is empty => delete themeSet
        if (kitList.isEmpty()) {
            themeSetService.delete(themeSetDto.getId());

        } else {
         // list is not empty == theme set is used by some building kit
            StringBuilder sb = new StringBuilder();
            sb.append("<ul>");
            for (BuildingKitDto kit : kitList) {
                sb.append("<li>");
                sb.append(kit.getName());
                sb.append("</li>");
            }
            sb.append("</ul>");

            getContext().getMessages().add(new LocalizableMessage("brick.delete.contains", sb.toString()));
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    private CategoryDto category;

    public CategoryDto getCategoryDto() {
        return category;
    }

    public void setCategoryDto(CategoryDto category) {
        this.category = category;
    }

    public Resolution updateThemeSet() {
        themeSetService.update(themeSetDto);
        return new RedirectResolution(this.getClass(), "list");
    }

}
