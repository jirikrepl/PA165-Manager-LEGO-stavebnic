package cz.muni.fi.PA165;

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
        System.out.println("listing all bricks");
    }

    /**
     * handles create brick operation
     * console command is <entity> create <color> <name>
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]   args[3]
     *             brick    create    name      color
     */
    private void handleCreateOperation(String args[]) {
        if (args.length < 4) {
            String requiredArgs = "<name> <color>";
            Messages.badNumberOfArgsMessage(args.length, createOperation, requiredArgs);
            System.exit(1);
        }
        System.out.println("creating brick" +
                "\nname: " + args[2] +
                "\ncolor: " + args[3]);
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
     * @param id id of brick
     */
    private void handleFindById(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<id>";
            Messages.badNumberOfArgsMessage(args.length, findByIdOperation, requiredArgs);
            System.exit(1);
        }

        System.out.println("find brick by its id: " + args[2]);
    }

    /**
     * find brick by its name
     *
     * @param name name of brick
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
     * @param color color of brick
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


//        //  prompt the user to enter their name
//        System.out.print("Enter your name: ");
//
//        //  open up standard input
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String userName = null;
//
//        //  read the username from the command-line; need to use try/catch with the
//        //  readLine() method
//        try {
//            userName = br.readLine();
//        } catch (IOException ioe) {
//            System.out.println("IO error trying to read your name!");
//            System.exit(1);
//        }
//
//        System.out.println("Thanks for the name, " + userName);
