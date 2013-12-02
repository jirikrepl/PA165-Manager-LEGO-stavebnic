package cz.muni.fi.PA165.rest;

import cz.muni.fi.PA165.api.service.BrickService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @author: Martin Rumanek
 * @version: 11/26/13
 */
@Path("/hello")
public class ServiceResource {

    @Autowired
    BrickService brickService;

    @Context
    private UriInfo context;

    public ServiceResource() {
    }

    @GET
    @Produces("text/plain")
    public String getText() {
        if (brickService != null) {
            return "injekce se povedla";
        } else {
            return "injekce nefunguji";
        }
    }
}
