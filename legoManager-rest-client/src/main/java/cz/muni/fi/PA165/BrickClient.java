package cz.muni.fi.PA165;

import cz.muni.fi.PA165.dto.BrickDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    final Client client = ClientBuilder.newBuilder().build();

    public BrickClient(String operation, List<String> arguments) {


        if (operation.equals(listOperation)) {

        } else if (operation.equals(createOperation)) {

        } else if (operation.equals(updateOperation)) {

        } else if (operation.equals(deleteOperation)) {

        } else if (operation.equals(findOperation)) {
            int id = Integer.parseInt(arguments.get(0));
            StringBuilder stringBuilder = new StringBuilder("http://localhost:8080/pa165/rest/bricks/").append(id);
            WebTarget webTarget = client.target(stringBuilder.toString());

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            invocationBuilder.header("accept", MediaType.APPLICATION_JSON);

            Response response = invocationBuilder.get();
            System.out.println(response.readEntity(BrickDto.class));
        } else {
            Messages.unknownOperationMessage(operation);
            System.exit(1);
        }
    }
}

