package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.fi.PA165.api.service.CategoryService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 11/21/13
 */
@UrlBinding("/category/{$event}/{category.id}")
public class CategoryActionBean extends BaseActionBean {
    @SpringBean
    private CategoryService service;

    private List<CategoryDto> categories;

    private  CategoryDto category;

    public CategoryDto getCategory() {
        return category;
    }

    public void setBook(CategoryDto categery) {
        this.category = categery;
    }

    @DefaultHandler
    public Resolution list() {
        categories = service.findAll();
        return new ForwardResolution("/category/list.jsp");
    }

    public Resolution add() {
        service.create(category);
        return new ForwardResolution("/category/list.jsp");
        //return new RedirectResolution(this.getClass(), "list");
    }
}
