package cz.muni.fi.PA165.rest;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.service.BrickService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
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
    @Produces(MediaType.TEXT_HTML) // testovani v prohlizeci
    // @Produces(MediaType.APPLICATION_JSON)
    public String getPlain() {

        List<BrickDto> bricks = brickService.findAll();

        StringBuilder sb = new StringBuilder();

        for (BrickDto brick : bricks) {
            sb.append(brick.toString());
            sb.append("<BR/>"); // testovani v html
        }

        return sb.toString();
    }

    /**
     * @param id id of brick
     * @return brick with specified id
     */
    @GET
    @Path("{id}")
    // @Produces(MediaType.APPLICATION_JSON)
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
