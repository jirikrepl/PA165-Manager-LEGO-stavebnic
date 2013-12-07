package cz.muni.fi.PA165;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    private static String entityArg = "";
    private static String operationArg = "";

    public static void main(String[] args) {

        List<String> arguments = new ArrayList<String>(Arrays.asList(args));

        if (arguments.size() == 0) {
            // show help table when called with no args
            Messages.printHelp();
            System.exit(1);

        } else if (arguments.size() < 2 || arguments.size() > 5) {
            // app called with bad number of arguments
            Messages.badNumberOfArgsMessage(arguments.size());
            System.exit(1);

        } else {
            // save first argument == entity name
            entityArg = arguments.get(0);
            operationArg = arguments.get(1);
        }


        if (entityArg.equals("brick")) {
            arguments.remove(0);
            arguments.remove(0);
            new BrickClient(operationArg, arguments);

        } else if (entityArg.equals("category")) {
            System.out.println("menu for category");

        } else {
            Messages.badFirstArgumentMessage();
            System.exit(1);
        }
    }


}
