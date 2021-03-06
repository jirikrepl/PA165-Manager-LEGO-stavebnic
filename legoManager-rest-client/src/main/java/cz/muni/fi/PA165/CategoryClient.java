package cz.muni.fi.PA165;

import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.conflictDto.CategoryConflictDto;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class CategoryClient {
    private static final String LIST_OPERATION = "list";
    private static final String CREATE_OPERATION = "create";
    private static final String UPDATE_OPERATION = "update";
    private static final String DELETE_OPERATION = "delete";
    private static final String FIND_BY_ID_OPERATION = "findbyid";
    private static final String FIND_BY_NAME_OPERATION = "findbyname";
    private static final HttpAuthenticationFeature auth = HttpAuthenticationFeature.basic("rest", "rest");

    public CategoryClient(String[] args) {

        // test bad number of arguments
        if (args.length < 2) {
            Messages.badNumberOfArgsMessage(args.length);
            System.exit(1);
        }

        String operation = args[1];

        try {
        switch (operation) {

            case LIST_OPERATION:
                // list ... no arguments
                handleListOperation();
                break;

            case CREATE_OPERATION:
                // create <name> <description>
                handleCreateOperation(args);
                break;

            case UPDATE_OPERATION:
                // update <id> <newName> <newDescription>
                handleUpdateOperation(args);
                break;

            case DELETE_OPERATION:
                handleDeleteOperation(args);
                break;

            // find <id>
            case FIND_BY_ID_OPERATION:
                handleFindById(args);
                break;

            // find <name>
            case FIND_BY_NAME_OPERATION:
                handleFindByName(args);
                break;

            default:
                Messages.unknownOperationMessage(operation);
                System.exit(1);
        }
        } catch (ProcessingException e) {
            System.out.println("Error on server, server is not available");
        }
    }

    /**
     * handles 'list' console command
     */
    private void handleListOperation() {

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/categories/");
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            List<CategoryDto> categoryDtoList = response.readEntity(new GenericType<List<CategoryDto>>(){});

            for (CategoryDto c : categoryDtoList) {
                System.out.println(c);
            }
            if (categoryDtoList.isEmpty()) {
                System.out.println("No category exists.");
            }
        } else {
            System.out.println("Error code:" + response.getStatus());
        }
    }

    /**
     * handles create category operation
     * console command is: category create <name> <description>
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]   args[3]
     *             category    create    name      description
     */
    private void handleCreateOperation(String args[]) {
        if (args.length < 4) {
            String requiredArgs = "<name> <description>";
            Messages.badNumberOfArgsMessage(args.length, CREATE_OPERATION, requiredArgs);
            System.exit(1);
        }

        // set arguments from command line to variables
        String name = args[2];
        String description = args[3];

        // everything is ok, create new category
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(name);
        categoryDto.setDescription(description);

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/categories/");
        webTarget.register(auth);
        Response response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(categoryDto, MediaType.APPLICATION_JSON_TYPE));

        // print out some response
        // successful response code is 201 == CREATED
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            System.out.println("Category with name '" + name + "' and description '" + description + "' was created");
        } else {
            System.out.println("Error code:" + response.getStatus());
        }
    }


    /**
     * find category by its id
     *
     * @param args category id
     */
    private void handleFindById(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<id>";
            Messages.badNumberOfArgsMessage(args.length, FIND_BY_ID_OPERATION, requiredArgs);
            System.exit(1);
        }

        Long id = null;
        try {
            id = Long.parseLong(args[2]);
        } catch (NumberFormatException ex) {
            System.out.println("Id is not valid.");
            System.exit(1);
        }

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/categories/" + id.toString());
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            CategoryDto categoryDto = response.readEntity(CategoryDto.class);
            System.out.println(categoryDto);
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            System.out.println("Category with this id was not found.");
        } else {
            System.err.println("Error on server, server returned " + response.getStatus());
        }
    }

    /**
     * find category by its name
     *
     * @param args name of category
     */
    private void handleFindByName(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<name>";
            Messages.badNumberOfArgsMessage(args.length, FIND_BY_NAME_OPERATION, requiredArgs);
            System.exit(1);
        }

        String name = args[2];
        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/categories").queryParam("name", name);
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request();
        invocationBuilder.header("accept", MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            List<CategoryDto> categoryDtoList = response.readEntity(new GenericType<List<CategoryDto>>() {});
            for (CategoryDto c : categoryDtoList) {
                System.out.println(c);
            }
        } else {
            if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                System.out.println("No category with this name exists");
            }
            else {
                System.out.println("Error code:" + response.getStatus());
            }
        }
    }

    /**
     * handle update operation of category
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]   args[3]   args[4]
     *             category    update    id        newName   newDescription
     */
    private void handleUpdateOperation(String[] args) {
        if (args.length < 5) {
            String requiredArgs = "<id> <newName> <newDescription>";
            Messages.badNumberOfArgsMessage(args.length, UPDATE_OPERATION, requiredArgs);
            System.exit(1);
        }

        Long id = null;
        try {
            id = Long.parseLong(args[2]);
        } catch (NumberFormatException ex) {
            System.out.println("Id is not valid.");
            System.exit(1);
        }
        String name = args[3];
        String description = args[4];

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(name);
        categoryDto.setDescription(description);

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/categories/" + id.toString());
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", MediaType.APPLICATION_JSON);
        Response response = webTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(categoryDto, MediaType.APPLICATION_JSON_TYPE));

        //successful update
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("updated category with id: " + args[2] +
                    "\nsetting new name to: " + args[3] +
                    "\nand new description to: " + args[4]);
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            System.out.println("Category with this id doesn't exist.");
        } else {
            System.out.println("Error code:" + response.getStatus());
        }
    }

    /**
     * handles removal of category entity, command name 'delete'
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]
     *             category    delete    id
     */
    private void handleDeleteOperation(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<id>";
            Messages.badNumberOfArgsMessage(args.length, DELETE_OPERATION, requiredArgs);
            System.exit(1);
        }

        Long id = null;
        try {
            id = Long.parseLong(args[2]);
        } catch (NumberFormatException ex) {
            System.out.println("Id is not valid.");
            System.exit(1);
        }
        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/categories/" + id.toString());
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.delete();

        // in case of successful removal of category
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Category successfully deleted");

        } else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode()) {
            // in case of building kit or theme set conflict
            CategoryConflictDto conflicts = response.readEntity(new GenericType<CategoryConflictDto>() {
            });

            //write building kits in conflict
            List<BuildingKitDto> kitConflicts = conflicts.getBuildingKitDtoList();
            if (!kitConflicts.isEmpty()) {
                System.out.println("Cannot delete this category, because it is contained in these building kits:");
                for (BuildingKitDto b : kitConflicts) {
                    System.out.println(b);
                }
            }

            //write theme sets in conflict
            List<ThemeSetDto> setConflicts = conflicts.getThemeSetDtoList();
            if (!setConflicts.isEmpty()) {
                System.out.println("Cannot delete this category, because it is contained in these theme sets:");
                for (ThemeSetDto s : setConflicts) {
                    System.out.println(s);
                }
            }
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()){
            System.out.println("Category with this id doesn't exist.");
        } else {
            System.out.println("Error code: " + response.getStatus());
        }
    }

}
