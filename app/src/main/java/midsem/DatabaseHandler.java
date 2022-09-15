package midsem;

import kotlin.Pair;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler {
    private Connection connection;
    private String routingTableName = "routing_table";
    private String messageLogsTableName = "message_logs";
    private Logger logr;
    public DatabaseHandler(String dbURL, Logger logr){
       this.logr = logr;
       try{
           connection = DriverManager.getConnection(dbURL);
       }catch (SQLException e){
           logr.log(Level.SEVERE, "Database not found!", e);
       }
    }

    public Pair<Integer, String> destinationFinder(String sender, String messageType) {
        try {
            Statement statement = connection.createStatement();
            /*Assumption*/
            String queryString = "SELECT RouteId, Destination FROM " + routingTableName + " WHERE Sender = '" + sender + "' AND MessageType = '" + messageType + "'";
            ResultSet resultSet = statement.executeQuery(queryString);
            return new Pair<>(resultSet.getInt(1), resultSet.getString(2));
        }catch (SQLException e){
            logr.log(Level.WARNING, "SQL exception!", e);
        }
        return null;
    }

    public int insertInMessageLog(int routeId, String eventType) {
        try{
            Statement statement = connection.createStatement();
            /*Assumption*/
            String queryString = "INSERT INTO " + messageLogsTableName +"(RouteId, EventType) VALUES(" + routeId + ", '" + eventType + "')";
            return statement.executeUpdate(queryString);
        }catch(SQLException e){
            logr.log(Level.WARNING, "SQL exception!", e);
        }
        return -1;
    }
}
