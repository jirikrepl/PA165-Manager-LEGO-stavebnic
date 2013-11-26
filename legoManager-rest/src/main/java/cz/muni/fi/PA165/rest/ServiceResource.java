package cz.muni.fi.PA165.rest;

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

    @Context
    private UriInfo context;

    public ServiceResource() {
    }

    @GET
    @Produces("text/plain")
    public String getText() {
        return "hello!";
    }
}
