package cz.muni.fi.PA165;

import cz.muni.fi.PA165.api.service.Color;

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
                "\tlist                               print out all bricks\n" +
                "\tcreate <name> <color>              create new brick\n" +
                "\tupdate <id> <newName> <newColor>   update brick by given id\n" +
                "\tdelete <id>                        delete brick by given id\n" +
                "\tfindbyid <id>                      find brick by id\n" +
                "\tfindbyname <name>                  find brick by name \n" +
                "\tfindbycolor <color>                find brick by color \n\n" +

                "commands for <category> are: \n" +
                "\tlist                               print out all categories\n" +
                "\tcreate <name> <description>        create new category\n" +
                "\tupdate <id> <name> <description>   update category by given id\n" +
                "\tdelete <id>                        delete category by given id\n" +
                "\tfindbyid <id>                      find category by id\n" +
                "\tfindbyname <name>                  find category by name\n");
    }

    /**
     * prints error message if case of some unknown operation
     *
     * @param operation string of operation
     */
    public static void unknownOperationMessage(String operation) {
        System.out.println("Unknown operation: " + operation + "\n");
        printHelp();
    }

    public static void badNumberOfArgsMessage(int inputedArgs, String operation, String requiredArgs) {
        inputedArgs -= 2;
        System.out.println("\nBad number of arguments. You entered only: " + inputedArgs + " argument(s)\n" +
                "Operation " + operation + " requires arguments: " + requiredArgs + "\n");
    }

    public static void badNumberOfArgsMessage(int inputedArgs) {
        System.out.println("Bad number of arguments. You entered " + inputedArgs + "\n");
        printHelp();
    }

    public static void badFirstArgumentMessage() {
        System.out.println("first argument has to be <brick> or <category>");
    }

    /**
     * prints message of all possible colors
     */
    public static void printAllColors() {
        StringBuilder sb = new StringBuilder();
        sb.append("Error: unknown color \nPossible colors are: \n\n");

        for (Color color : Color.values()) {
            sb.append(color.getColorString());
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
