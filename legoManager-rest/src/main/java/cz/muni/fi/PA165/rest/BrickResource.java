package cz.muni.fi.PA165.rest;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.fi.PA165.api.service.Color;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author: Jiri Krepl
 * @version: 11/26/13
 */
@Path("bricks")
public class BrickResource {

    @Autowired
    BrickService brickService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(BrickDto brick) {
        brickService.create(brick);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") Long id, BrickDto brick) {
        brick.setId(id);
        brickService.update(brick);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("color") Color color, @QueryParam("name") String name) {
        List<BrickDto> brickDtoList = null;
        if (color != null) {
            brickDtoList = brickService.findByColor(color);

        } else if (name != null) {
            brickDtoList = brickService.findByName(name);
        } else if (color == null && name == null) {
            brickDtoList = brickService.findAll();
        }

        if (brickDtoList != null) {
            GenericEntity<List<BrickDto>> brickEntity = new GenericEntity<List<BrickDto>>(brickDtoList) {};

            return Response.status(Response.Status.OK).entity(brickEntity).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        brickService.delete(id);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        BrickDto brickdDto = brickService.findById(id);
        return Response.status(Response.Status.OK).entity(brickdDto).build();
    }

}
