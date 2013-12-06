package cz.muni.fi.PA165;

/**
 * Created by Jiri Krepl on 12/6/13.
 * this class includes messages of rest-client
 */
public class Messages {

    /**
     * prints help table
     */
    public static void printHelp() {
        System.out.println("usage: rest-client <entity> [commands]\n\n" +
                "objects are: brick, category\n\n" +

                "commands for <brick> are: \n" +
                "\tlist                               prints out all bricks\n" +
                "\tcreate <color> <name>              creates new brick\n" +
                "\tupdate <id> <newName> <newColor>   updates brick by given id\n" +
                "\tdelete <id>                        deletes brick by given id\n" +
                "\tfind <id | color | name>           finds brick by id, color or name\n\n" +

                "commands for <category> are: \n" +
                "\tlist                               prints out all categories\n" +
                "\tcreate <name> <description>        creates new category\n" +
                "\tupdate <id> <name> <description>   updates category by given id\n" +
                "\tdelete <id>                        deletes category by given id\n" +
                "\tfind <id | name>                   finds category by id or name\n");
    }

    /**
     * prints error message if case of some unknown operation
     * @param operation string of operation
     */
    public static void unknownOperationMessage(String operation) {
        System.out.println("Unknown operation: " + operation + "\n");
        printHelp();
    }

    public static void badNumberOfArgsMessage(int argsCount) {
        System.out.println("Bad number of arguments.");
        System.out.println("You entered: " + argsCount + " argument(s).");
    }

    public static void badFirstArgumentMessage() {
        System.out.println("first argument has to be <brick> or <category>");
    }
}
