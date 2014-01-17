package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.api.service.CategoryService;
import cz.muni.fi.PA165.api.service.ThemeSetService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 11/21/13
 */
@UrlBinding("/categories/{$event}")
public class CategoryActionBean extends BaseActionBean implements ValidationErrorHandler{

    @SpringBean
    private CategoryService service;

    @SpringBean
    private ThemeSetService themeSetService;

    List<CategoryDto> categories;

    public List<CategoryDto> getCategories() {
        categories = service.findAll();
        return categories;
    }

    @ValidateNestedProperties(
            value = {
                @Validate(on = {"add", "updateCategory"}, field = "name", required = true, maxlength = 50),
                @Validate(on = {"add", "updateCategory"}, field = "description", required = true, maxlength = 50)
            }
    )
    private CategoryDto categoryDto;

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/category/list.jsp");
    }

    public Resolution add() {
        service.create(categoryDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution openEditPage() {
        return new ForwardResolution("/category/edit.jsp");
    }

    public Resolution updateCategory() {
        service.update(categoryDto);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        List<ThemeSetDto> themeSetList = themeSetService.findAll();
        for (ThemeSetDto themeSetDto : themeSetList) {

            if (themeSetDto.getCategoryDto().getId().equals(categoryDto.getId())) {
                getContext().getMessages().add(new LocalizableError("category.delete.contains"));
                return new RedirectResolution(this.getClass(), "list");
            }
        }

        service.delete(categoryDto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //fill up the data for the table if validation errors occured
        categories = service.findAll();
        //return null to let the event handling continue
        return null;
    }

}
