package cz.muni.fi.PA165;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.service.Color;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jiri Krepl on 12/5/13.
 * this class is responsible for handling brick operations
 */
public class BrickClient {
    private static final String LIST_OPERATION = "list";
    private static final String CREATE_OPERATION = "create";
    private static final String UPDATE_OPERATION = "update";
    private static final String DELETE_OPERATION = "delete";
    private static final String FIND_BY_ID_OPERATION = "findbyid";
    private static final String FIND_BY_COLOR_OPERATION = "findbycolor";
    private static final String FIND_BY_NAME_OPERATION = "findbyname";
    private static final HttpAuthenticationFeature auth = HttpAuthenticationFeature.basic("rest", "rest");

    /**
     * handles various operations of brick
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]   args[3]   args[4]
     *             <brick>  <op>      <arg1>    <arg2>    <arg3>
     *             brick    update    id        name      color
     */
    public BrickClient(String[] args) {

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
                // create <color> <name>

                handleCreateOperation(args);
                break;

            case UPDATE_OPERATION:
                // update <id> <newName> <newColor>
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

            // find <color>
            case FIND_BY_COLOR_OPERATION:
                handleFindByColor(args);
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
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/bricks/");
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            List<BrickDto> brickDtoList = response.readEntity(new GenericType<List<BrickDto>>() {
            });
            System.out.println("Number of bricks returned: " + brickDtoList.size());

            for (BrickDto b : brickDtoList) {
                System.out.println(b);
            }
        } else {
            System.out.println("Error code:" + response.getStatus());
        }
    }

    /**
     * handles create brick operation
     * console command is: brick create <name> <color>
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]   args[3]
     *             brick    create    name      color
     */
    private void handleCreateOperation(String args[]) {
        if (args.length < 4) {
            String requiredArgs = "<name> <color>";
            Messages.badNumberOfArgsMessage(args.length, CREATE_OPERATION, requiredArgs);
            System.exit(1);
        }

        // set arguments from command line to variables
        String name = args[2];
        String colorString = args[3];
        // convert string to color
        Color colorEnum = null;
        try {
            colorEnum = Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {
            Messages.printAllColors();
            System.exit(1);
        }

        // everything is ok, create new brick
        BrickDto brickDto = new BrickDto(name, colorEnum, "");

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/bricks/");
        webTarget.register(auth);
        Response response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(brickDto, MediaType.APPLICATION_JSON_TYPE));

        // print out some response
        // successful response code is 201 == CREATED
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            System.out.println("Brick with name '" + name + "' and color '" + colorString + "' was created");
        } else {
            System.out.println("Error on server, server returned " + response.getStatus());
        }
    }

    /**
     * find brick by its id
     *
     * @param args brick
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
        } catch (NumberFormatException e) {
            System.out.println("Error - argument '" + args[2] + "' must be a number");
            System.exit(1);
        }

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/bricks/" + id.toString());
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            BrickDto brickDto = response.readEntity(BrickDto.class);
            System.out.println("Brick found. " + brickDto);
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            System.out.println("Error - brick was not found, wrong id: " + id + " Server returned: " + response.getStatus());
        } else {
            System.out.println("Error on server, server returned " + response.getStatus());
        }
    }

    /**
     * handle findByName operation on brick
     *
     * @param args command line arguments
     *             args[0]   args[1]      args[2]
     *             brick     findbyname   name
     */
    private void handleFindByName(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<name>";
            Messages.badNumberOfArgsMessage(args.length, FIND_BY_NAME_OPERATION, requiredArgs);
            System.exit(1);
        }
        String name = args[2];

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/bricks").queryParam("name", name);
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request();
        invocationBuilder.header("accept", MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            // return list
            List<BrickDto> brickDtoList = response.readEntity(new GenericType<List<BrickDto>>() {
            });
            System.out.println("Number of bricks returned: " + brickDtoList.size());

            for (BrickDto b : brickDtoList) {
                System.out.println(b);
            }
        } else {
            // server error
            System.out.println("Error on server, server returned " + response.getStatus());
        }
    }

    /**
     * handle findByColor operation on brick
     *
     * @param args command line arguments
     *             args[0]    args[1]        args[2]
     *             brick      findbycolor    color
     */
    private void handleFindByColor(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<color>";
            Messages.badNumberOfArgsMessage(args.length, FIND_BY_COLOR_OPERATION, requiredArgs);
            System.exit(1);
        }

        Color color = null;
        String stringColor = args[2];
        try {
            color = Color.parseColor(stringColor);
        } catch (IllegalArgumentException e) {
            // in case of unsupported color, print all supported colors and end here
            Messages.printAllColors();
            System.exit(1);
        }


        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/bricks").queryParam("color", color.getColorString());
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", MediaType.APPLICATION_JSON);


        Response response = invocationBuilder.get();


        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            List<BrickDto> brickDtoList = response.readEntity(new GenericType<List<BrickDto>>() {
            });

            System.out.println("Number of bricks returned: " + brickDtoList.size());
            for (BrickDto b : brickDtoList) {
                System.out.println(b);
            }
        } else {
            System.out.println("Server error - Error code: " + response.getStatus());
        }
    }

    /**
     * handle update operation of brick
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]   args[3]   args[4]
     *             brick    update    id        newName   newColor
     */
    private void handleUpdateOperation(String[] args) {
        if (args.length < 5) {
            String requiredArgs = "<id> <newName> <newColor>";
            Messages.badNumberOfArgsMessage(args.length, UPDATE_OPERATION, requiredArgs);
            System.exit(1);
        }
        String name = args[3];
        String colorString = args[4];

        // try to parse id argument
        Long id = null;
        try {
            id = Long.parseLong(args[2]);
        } catch (NumberFormatException e) {
            System.err.println("Argument (id): " + args[2] + " must be a number");
            System.exit(1);
        }

        // convert string to color enum, if this fails --> exit
        Color colorEnum = null;
        try {
            colorEnum = Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {
            Messages.printAllColors();
            System.exit(1);
        }

        // everything is ok, create new brick
        BrickDto brickDto = new BrickDto(name, colorEnum, "");
        brickDto.setId(id);

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/bricks/" + id.toString());
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", MediaType.APPLICATION_JSON);

        Response response = webTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(brickDto, MediaType.APPLICATION_JSON_TYPE));

        // in case of successful removal of brick
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Brick successfully updated");
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            System.out.println("Error - brick was not found, wrong id: " + id + " Server returned: " + response.getStatus());
        } else {
            System.out.println("Error on server, server returned " + response.getStatus());
        }
    }

    /**
     * handles removal of brick entity, command name 'delete'
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]
     *             brick    delete    id
     */
    private void handleDeleteOperation(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<id>";
            Messages.badNumberOfArgsMessage(args.length, DELETE_OPERATION, requiredArgs);
            System.exit(1);
        }

        Long id = Long.parseLong(args[2]);
        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/bricks/" + id.toString());
        webTarget.register(auth);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.delete();

        // in case of successful removal of brick
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Brick successfully deleted");

        } else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode()) {
            // in case of building kit conflict
            // list building kits that contain this brick
            List<BuildingKitDto> brickDtoList = response.readEntity(new GenericType<List<BuildingKitDto>>() {
            });

            System.out.println("Cannot delete this brick, because it is contained in this building kits:");
            for (BuildingKitDto b : brickDtoList) {
                System.out.println(b.getName());
            }

        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            System.out.println("Error - brick was not found, wrong id: " + id + " Server returned: " + response.getStatus());
        } else {
            System.out.println("Error on server, server returned " + response.getStatus());
        }
    }

}
