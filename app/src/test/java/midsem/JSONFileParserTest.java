package midsem;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class JSONFileParserTest {
    @Test
    public void jsonFileParser() throws IOException {
        String directory = "";
        String configFilePathTest = "src\\test\\resources\\configTest.json";
        JSONFileParser jParser = new JSONFileParser();
        JSONFileParser.ConfigInfo cI = jParser.jsonFileParser(directory + configFilePathTest);
        assertEquals("127.0.0.1", cI.host);
        assertEquals(7000, cI.port);
        assertEquals("jdbc:sqlite:C:\\Users\\ritik\\sqliteDB\\mydatabase.db", cI.dbURL);
        assertEquals("C:\\Users\\ritik\\CS305\\midsem\\app\\src\\main\\resources\\msg_router.log", cI.logFilePath);
    }
}
