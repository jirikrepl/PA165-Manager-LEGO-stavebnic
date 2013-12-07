package cz.muni.fi.PA165.rest;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.fi.PA165.api.service.Color;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @author: Jiri Krepl
 * @version: 11/26/13
 */
@Path("/bricks")
public class BrickResource {

    @Autowired
    BrickService brickService;

    @Context
    private UriInfo context;


    /**
     * gets all bricks
     *
     * @return all bricks
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BrickDto> getPlain() {
        return brickService.findAll();
    }

    /**
     * @param id id of brick
     * @return brick with specified id
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BrickDto getBrick(@PathParam("id") Long id) {
        return brickService.findById(id);
    }

    /**
     * creates brick
     *
     * @param brick brick dto
     * @return confirmation string
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createBrick(BrickDto brick) {
        brickService.create(brick);
        return "Created brick " + brick.getName();
    }
}
