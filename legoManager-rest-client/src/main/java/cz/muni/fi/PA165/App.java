package cz.muni.fi.PA165;

public class App {
    public static void main(String[] args) {

//        Client client = ClientBuilder.newClient();
//        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/hello");
//
//        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
//        invocationBuilder.header("accept", "text/plain");
//
//        Response response = invocationBuilder.get();
//        System.out.println(response.readEntity(String.class));

        String firstArg = "";

        // show help table when called with no args
        if (args.length == 0) {
            System.out.println("usage: rest-client <entity> [commands]\n" +
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
                        "\tupdate <id> <name> <description>   updates category by given id\n"+
                        "\tdelete <id>                        deletes category by given id\n" +
                        "\tfind <id | name>                   finds category by id or name\n");

            System.exit(1);
        } else {
            // save first argument == entity name
            firstArg = args[0];
        }


        if (firstArg.equals("brick")) {
            System.out.println("menu for brick");

        } else if (firstArg.equals("category")) {
            System.out.println("menu for category");

        } else {
            System.out.println("first argument has to be <brick> or <category>");
            System.exit(1);
        }
    }
}
