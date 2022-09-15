package midsem;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPRequestForwarder implements RequestForwarder{
    Logger logr;
    public HTTPRequestForwarder(Logger logr) {
        this.logr = logr;
    }
    @Override
    public int requestForwarder(String destination, String msg) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            /*TODO:: Change URL*/
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(destination))
                    .POST(HttpRequest.BodyPublishers.ofString(msg))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        }catch (IOException e){
            logr.log(Level.WARNING, "IOException occurred!", e);
        }catch(InterruptedException e){
            logr.log(Level.WARNING, "InterruptedException occurred!", e);
//ctx.status
//        ctx.result
        }
        return -1;
    }
}
