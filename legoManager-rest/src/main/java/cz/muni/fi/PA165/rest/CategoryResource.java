package cz.muni.fi.PA165.rest;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Path("categories")
public class CategoryResource {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BuildingKitService buildingKitService;
    @Autowired
    ThemeSetService themeSetService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CategoryDto category) {
        categoryService.create(category);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") Long id, CategoryDto category) {
        //TODO CO KDYZ ID NEEXISTUJE?
        category.setId(id);
        categoryService.update(category);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("name") String name) {
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        if (name != null) {
            CategoryDto category = categoryService.findByName(name);
            if (category != null)
            {
                categoryDtoList.add(category);
            }
        } else {
            categoryDtoList = categoryService.findAll();
        }

        GenericEntity<List<CategoryDto>> categoryEntity = new GenericEntity<List<CategoryDto>>(categoryDtoList) {};
        return Response.status(Response.Status.OK).entity(categoryEntity).build();
    }


    /**
     * delete brick
     *
     * @param id Long id of building kit
     * @return String confirmation of deleted kit or it cant be deleted because
     * brick is used by some of building kits or theme sets, returns names of this building kits or theme sets
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {

        CategoryDto category = categoryService.findById(id);

        //TODO problem na servisni vrstve
        if(category == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        // find out if category is used by some building kit first
        // otherwise removal of used category would violate the constraint
        List<BuildingKitDto> buildingKitDtoList = buildingKitService.findByCategory(category);

        // list is empty, category is not contained in any building kit => delete category
        if (buildingKitDtoList.isEmpty()) {
            categoryService.delete(id);
            return Response.status(Response.Status.OK).build();
        }

        // list is not empty == category is used by some building kit
        // list that building kits
        GenericEntity<List<BuildingKitDto>> genericEntityList = new GenericEntity<List<BuildingKitDto>>(buildingKitDtoList) {
        };
        return Response.status(Response.Status.CONFLICT).entity(genericEntityList).build();

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        CategoryDto categoryDto = categoryService.findById(id);
        return Response.status(Response.Status.OK).entity(categoryDto).build();
    }
}
