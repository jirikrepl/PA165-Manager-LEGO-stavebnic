package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dto.BrickDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Jiri Krepl on 12/5/13.
 * this class is responsible for handling brick operations
 */
public class BrickClient {
    private final String listOperation = "list";
    private final String createOperation = "create";
    private final String updateOperation = "update";
    private final String deleteOperation = "delete";
    private final String findByIdOperation = "findbyid";
    private final String findByColorOperation = "findbycolor";
    private final String findByNameOperation = "findbyname";


    /**
     * handles various operations of brick
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]   args[3]   args[4]
     *             <brick>  <op>      <arg1>    <arg2>    <arg3>
     *             brick    update    id        name      color
     */
    //TODO nehlida se jestli neni nahodou zadano vice argumentu
    //TODO url restu by mohlo byt konfigurovatelne
    //TODO osetreni chybovych stavu restu
    //TODO chyby na err vystup (system.err.println)
    //TODO validace cisel
    public BrickClient(String[] args) {

        // test bad number of arguments
        if (args.length < 2) {
            Messages.badNumberOfArgsMessage(args.length);
            System.exit(1);
        }

        String operation = args[1];

        switch (operation) {

            case listOperation:
                // list ... no arguments
                handleListOperation();
                break;

            case createOperation:
                // create <color> <name>

                handleCreateOperation(args);
                break;

            case updateOperation:
                // update <id> <newName> <newColor>
                handleUpdateOperation(args);
                break;

            case deleteOperation:
                handleDeleteOperation(args);
                break;

            // find <id>
            case findByIdOperation:
                handleFindById(args);
                break;

            // find <name>
            case findByNameOperation:
                handleFindByName(args);
                break;

            // find <color>
            case findByColorOperation:
                handleFindByColor(args);
                break;

            default:
                Messages.unknownOperationMessage(operation);
                System.exit(1);
        }
    }

    /**
     * handles 'list' console command
     */
    private void handleListOperation() {

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/bricks/");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            List<BrickDto> brickDtoList = response.readEntity(new GenericType<List<BrickDto>>() {
            });

            for(BrickDto b : brickDtoList) {
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
    //TODO arguments description?
    private void handleCreateOperation(String args[]) {
        if (args.length < 4) {
            String requiredArgs = "<name> <color>";
            Messages.badNumberOfArgsMessage(args.length, createOperation, requiredArgs);
            System.exit(1);
        }

        // set arguments from command line to variables
        String name = args[2];
        String colorString = args[3];

        // find out if string of color has its Enum
        Color colorEnum = null;
        for (Color c : Color.values()) {
            if(c.equalsName(colorString)) {
                colorEnum = c;
                break;
            }
        }

        // bad color argument, print list of colors and exit
        if(colorEnum == null) {
            Messages.printAllColors();
            System.exit(1);
        }

        // everything is ok, create new brick
        BrickDto brickDto = new BrickDto(name, colorEnum, "");

        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/bricks/");
        Response response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(brickDto, MediaType.APPLICATION_JSON_TYPE));

        // print out some response
        // successful response code is 201 == CREATED
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            System.out.println("Brick with name '" + name + "' and color '" + colorString + "' was created");
        } else {
            System.out.println("Error code:" + response.getStatus());
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
            Messages.badNumberOfArgsMessage(args.length, updateOperation, requiredArgs);
            System.exit(1);
        }

        System.out.println("updated brick with id: " + args[2] +
                "\nsetting new name to: " + args[3] +
                "\nand new color to: " + args[4]);
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
            Messages.badNumberOfArgsMessage(args.length, deleteOperation, requiredArgs);
            System.exit(1);
        }

        System.out.println("deleted brick with id: " + args[2]);
    }

    /**
     * find brick by its id
     *
     * @param args brick
     */
    private void handleFindById(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<id>";
            Messages.badNumberOfArgsMessage(args.length, findByIdOperation, requiredArgs);
            System.exit(1);
        }

        Long id = Long.parseLong(args[2]);
        Client client = ClientBuilder.newBuilder().build();
        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/bricks/" + id.toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            BrickDto brickDto = response.readEntity(BrickDto.class);
            System.out.println(brickDto);
        } else {
            System.err.println("Error on server, server returned " + response.getStatus());
        }
    }

    /**
     * find brick by its name
     *
     * @param args name of brick
     */
    private void handleFindByName(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<id>";
            Messages.badNumberOfArgsMessage(args.length, findByNameOperation, requiredArgs);
            System.exit(1);
        }

        System.out.println("find brick by its name: " + args[2]);
    }

    /**
     * find brick by its color
     *
     * @param args color of brick
     */
    private void handleFindByColor(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<id>";
            Messages.badNumberOfArgsMessage(args.length, findByColorOperation, requiredArgs);
            System.exit(1);
        }

        System.out.println("find brick by its color: " + args[2]);
    }

}
