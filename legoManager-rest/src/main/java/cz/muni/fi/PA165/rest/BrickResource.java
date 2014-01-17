package cz.muni.fi.PA165.rest;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.service.BrickService;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import cz.muni.fi.PA165.api.service.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Jiri Krepl
 * @author: Martin Rumanek
 * @version: 11/26/13
 */
@Path("bricks")
public class BrickResource {

    @Autowired
    BrickService brickService;
    @Autowired
    BuildingKitService buildingKitService;

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
        try {
            brickService.findById(id);
            brickService.update(brick);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("color") String color, @QueryParam("name") String name) {
        List<BrickDto> brickDtoList;

        if (color != null & name != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else if (color != null) {
            // find by color
            Color colorEnum = Color.parseColor(color);
            brickDtoList = brickService.findByColor(colorEnum);

        } else if (name != null) {
            //find by name
            brickDtoList = brickService.findByName(name);

        } else {
            // return all bricks (clean url: rest/bricks)
            brickDtoList = brickService.findAll();
        }
        GenericEntity<List<BrickDto>> brickEntity = new GenericEntity<List<BrickDto>>(brickDtoList) {
        };
        return Response.status(Response.Status.OK).entity(brickEntity).build();
    }


    /**
     * delete brick
     *
     * @param id Long id of brick
     * @return String confirmation of deleted brick or it cant be deleted because
     * brick is used by some of building kits, returns names of this building kits
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        try {
            BrickDto brick = brickService.findById(id);
            List<BuildingKitDto> buildingKitDtoList = buildingKitService.findByBrick(brick);
            // find out if brick is used by some building kit first
            // otherwise removal of used brick would violate the constraint

            // list is empty, brick is not contained in any building kit => delete brick
            if (buildingKitDtoList.isEmpty()) {
                brickService.delete(id);
                return Response.status(Response.Status.OK).build();
            }

            // list is not empty == brick is used by some building kit
            // list that building kits
            GenericEntity<List<BuildingKitDto>> genericEntityList = new GenericEntity<List<BuildingKitDto>>(buildingKitDtoList) {
            };
            return Response.status(Response.Status.CONFLICT).entity(genericEntityList).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
            BrickDto brickDto = brickService.findById(id);
            return Response.status(Response.Status.OK).entity(brickDto).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
