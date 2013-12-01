package cz.muni.fi.PA165;


import org.glassfish.jersey.client.JerseyClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class App {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("http://localhost:8080/pa165/rest/hello");

        Invocation.Builder invocationBuilder =
                webTarget.request(MediaType.TEXT_PLAIN);
        invocationBuilder.header("accept", "text/plain");

        Response response = invocationBuilder.get();

        System.out.println(response.readEntity(String.class));
    }
}
