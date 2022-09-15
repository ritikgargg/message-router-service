package midsem;

import kotlin.Pair;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseHandlerTest {

    @Test
    public void insertInMessageLogAndDestinationFinder(){
        String dbURL = "jdbc:sqlite:C:\\Users\\ritik\\sqliteDB\\mydatabase.db";
        Logger logger = Logger.getLogger("MyLog");
        DatabaseHandler obj = new DatabaseHandler(dbURL, logger);
        int c = obj.insertInMessageLog(100, "RECEIVED");
        assertEquals(1, c);

        /*Assuming the first entry in the assignment example is present in the routing table on which you are
        * running the tests*/

        Pair<Integer, String> p = obj.destinationFinder("http://127.0.0.1:23001/foo", "VALIDATE_PAN");
        assertEquals(1, p.getFirst());
        assertEquals("http://127.0.0.1:44001/check_pan", p.getSecond());
    }
}
