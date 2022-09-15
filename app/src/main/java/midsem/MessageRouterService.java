package midsem;

import io.javalin.Javalin;
import kotlin.Pair;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

public class MessageRouterService {
    private final String host;
    private final int port;
    private final String dbURL;
    private final String logFilePath;
    private final Logger logr;

    public MessageRouterService(String configFilePath) throws IOException {
        JSONFileParser jParser = new JSONFileParser();
        JSONFileParser.ConfigInfo cI = jParser.jsonFileParser(configFilePath);
        host = cI.host;
        port = cI.port;
        dbURL = cI.dbURL;
        logFilePath = cI.logFilePath;
        Log obj = new Log();
        logr = obj.logCreator(logFilePath);
    }

    public void routeServiceHandler(){
        Javalin app = Javalin.create().start(port);
        DatabaseHandler dbHandler = new DatabaseHandler(dbURL, logr);
        app.post("/", ctx -> {
            RequestParser requestParser = new RequestParser(logr);
            List<String> parsedResult = requestParser.reqMessageParser(ctx.body());
            String sender = parsedResult.get(0);
            String messageType = parsedResult.get(1);

            Pair<Integer, String> res = dbHandler.destinationFinder(sender, messageType);
            dbHandler.insertInMessageLog(res.getFirst(), "RECEIVED");
            HTTPRequestForwarder rF = new HTTPRequestForwarder(logr);
            int statusCode =  rF.requestForwarder(res.getSecond(), ctx.body());
            dbHandler.insertInMessageLog(res.getFirst(), "SENT");
            ctx.status(statusCode);
            ctx.result("");
        });

    }
}
