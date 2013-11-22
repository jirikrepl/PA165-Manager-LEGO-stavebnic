package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.ThemeSetDto;
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

//podla odporucania Jiriho
@UrlBinding("/themeset/{$event}")
public class ThemeSetActionBean extends BaseActionBean{
    
    //nainjektovana spring beana do tohoto ActionBeanu
    @SpringBean
    protected ThemeSetService service;
    private List<ThemeSetDto> themeSets;
    
    private ThemeSetDto dto;
    
    @DefaultHandler
    public Resolution list(){
       
        return new ForwardResolution("/themeset/list.jsp");
    }
    
    public Resolution add(){
        service.create(dto);
        return new ForwardResolution("/themeset/list.jsp");
    }

    public List<ThemeSetDto> getThemeSets() {
        themeSets = service.findAll();
        return themeSets;
    }   
    public Resolution openEditPage(){
        dto = service.findById(dto.getId());
        return new ForwardResolution("/themeset/edit.jsp");
    }
    public Resolution delete(){
        service.findById(dto.getId());
        return new RedirectResolution(this.getClass(), "list");
    }
    
}