package cz.muni.fi.PA165;

public class App {
    private static String entityArg = "";

    public static void main(String[] args) {


        if (args.length == 0) {
            // show help table when called with no args
            Messages.printHelp();
            System.exit(1);

        } else {
            // save first argument == entity name
            entityArg = args[0];
        }


        if (entityArg.equals("brick")) {
            // test if first entity argument is correct
            new BrickClient(args);

        } else if (entityArg.equals("category")) {
            System.out.println("menu for category");

        } else {
            Messages.badFirstArgumentMessage();
            System.exit(1);
        }
    }


}
