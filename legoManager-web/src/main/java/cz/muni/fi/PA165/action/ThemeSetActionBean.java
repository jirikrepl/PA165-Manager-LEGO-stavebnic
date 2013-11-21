package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.api.service.ThemeSetService;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 *
 * @author Pavol Bako
 */

@UrlBinding("/themeset/{event}/{themeset.id}")
public class ThemeSetActionBean extends BaseActionBean implements ValidationErrorHandler{
    
    //nainjektovana spring beana do tohoto ActionBeanu
    @SpringBean
    protected ThemeSetService service;
    private ThemeSetDto dto;
    private List<ThemeSetDto> themeSets;
    
    @DefaultHandler
    public Resolution list(){
        themeSets = service.findAll();
        return new ForwardResolution("/themeset/list.jsp");
    }
    
    public Resolution add(){
        service.create(dto);
        return new ForwardResolution("/themeset/list.jsp");
    }

    public List<ThemeSetDto> getThemeSets() {
        return themeSets;
    }

    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //fill up the data for the table if validation errors occured
        themeSets = service.findAll();
        //return null to let the event handling continue
        return null;
    }
            
    
    
    
    
}