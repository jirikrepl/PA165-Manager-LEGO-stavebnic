package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.service.CategoryService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 11/21/13
 */
@UrlBinding("/categories/{$event}")
public class CategoryActionBean extends BaseActionBean {

    @SpringBean
    private CategoryService service;
    List<CategoryDto> categories;

    public List<CategoryDto> getCategories() {
        categories = service.findAll();
        return categories;
    }

    @ValidateNestedProperties(
            value = {
                    @Validate(on = {"add","openEditPage","updateCategory"}, field = "name", required = true, maxlength = 50)
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
        service.delete(categoryDto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

}