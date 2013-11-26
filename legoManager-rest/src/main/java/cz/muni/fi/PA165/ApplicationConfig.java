package cz.muni.fi.PA165;

import cz.muni.fi.PA165.rest.ServiceResource;

import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * @author: Martin Rumanek
 * @version: 11/26/13
 */
@javax.ws.rs.ApplicationPath("/rest")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(cz.muni.fi.PA165.rest.ServiceResource.class);
    }
}
