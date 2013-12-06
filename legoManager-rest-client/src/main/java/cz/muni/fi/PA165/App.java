package cz.muni.fi.PA165;

public class App {
    private static String entityArg = "";
    private static String operationArg = "";

    public static void main(String[] args) {

//        Client client = ClientBuilder.newClient();
//        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/hello");
//
//        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
//        invocationBuilder.header("accept", "text/plain");
//
//        Response response = invocationBuilder.get();
//        System.out.println(response.readEntity(String.class));


        if (args.length == 0) {
            // show help table when called with no args
            Messages.printHelp();
            System.exit(1);

        } else if (args.length < 2 || args.length > 5) {
            // app called with bad number of arguments
            Messages.badNumberOfArgsMessage(args.length);
            System.exit(1);

        } else {
            // save first argument == entity name
            entityArg = args[0];
            operationArg = args[1];
        }


        if (entityArg.equals("brick")) {
            // test if first entity argument is correct
            new BrickClient(operationArg, args);

        } else if (entityArg.equals("category")) {
            System.out.println("menu for category");

        } else {
            Messages.badFirstArgumentMessage();
            System.exit(1);
        }
    }


}
