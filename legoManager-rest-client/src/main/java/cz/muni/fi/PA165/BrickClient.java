package cz.muni.fi.PA165;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Jiri Krepl on 12/5/13.
 */
public class BrickClient {

    public BrickClient() {
        //  prompt the user to enter their name
        System.out.print("Enter your name: ");

        //  open up standard input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String userName = null;

        //  read the username from the command-line; need to use try/catch with the
        //  readLine() method
        try {
            userName = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your name!");
            System.exit(1);
        }

        System.out.println("Thanks for the name, " + userName);

    }
}
