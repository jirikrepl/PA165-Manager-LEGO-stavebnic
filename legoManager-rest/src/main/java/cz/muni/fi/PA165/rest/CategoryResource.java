package cz.muni.fi.PA165.rest;

import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import cz.muni.fi.PA165.api.service.CategoryService;
import cz.muni.fi.PA165.api.service.ThemeSetService;
import cz.muni.fi.PA165.rest.conflictDto.CategoryConflictDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

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
import java.util.ArrayList;
import java.util.List;

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
        try {
            category.setId(id);
            categoryService.update(category);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("name") String name) {
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        if (name != null) {
            try {
                CategoryDto category = categoryService.findByName(name);
                categoryDtoList.add(category);
            } catch (DataAccessException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
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
        try {
        CategoryDto category = categoryService.findById(id);

        // find out if category is used by some building kit first
        // otherwise removal of used category would violate the constraint
        List<BuildingKitDto> buildingKitDtoList = buildingKitService.findByCategory(category);
        List<ThemeSetDto> themeSetDtoList = themeSetService.findByCategory(category);

        if (!buildingKitDtoList.isEmpty() || !themeSetDtoList.isEmpty()) {
            CategoryConflictDto categoryConflictDto = new CategoryConflictDto();
            categoryConflictDto.setBuildingKitDtoList(buildingKitDtoList);
            categoryConflictDto.setThemeSetDtoList(themeSetDtoList);

            return Response.status(Response.Status.CONFLICT).entity(categoryConflictDto).build();
        }

        categoryService.delete(id);
        return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
        CategoryDto categoryDto = categoryService.findById(id);

            return Response.status(Response.Status.OK).entity(categoryDto).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
