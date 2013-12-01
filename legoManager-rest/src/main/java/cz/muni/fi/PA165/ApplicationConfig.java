package cz.muni.fi.PA165;

import cz.muni.fi.PA165.rest.ServiceResource;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author: Martin Rumanek
 * @version: 11/26/13
 */
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        register(ServiceResource.class);
    }
}
