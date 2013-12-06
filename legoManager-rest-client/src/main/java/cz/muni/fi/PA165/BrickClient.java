package cz.muni.fi.PA165;

/**
 * Created by Jiri Krepl on 12/5/13.
 * this class is responsible for handling brick operations
 */
public class BrickClient {
    private String listOperation = "list";
    private String createOperation = "create";
    private String updateOperation = "update";
    private String deleteOperation = "delete";
    private String findOperation = "find";

    public BrickClient(String operation, String args[]) {

        if (operation.equals(listOperation)) {

        } else if (operation.equals(createOperation)) {

        } else if (operation.equals(updateOperation)) {

        } else if (operation.equals(deleteOperation)) {

        } else if (operation.equals(findOperation)) {

        } else {
            Messages.unknownOperationMessage(operation);
            System.exit(1);
        }
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
