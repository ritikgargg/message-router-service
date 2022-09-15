package midsem;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class RequestParserTest {

    @Test
    public void reqMessageParser(){
        Logger logger = Logger.getLogger("MyLog");
        RequestParser obj = new RequestParser(logger);

        String msg = "<Message>\n" +
                "   <Sender>http://127.0.0.1:23001/foo</Sender>\n" +
                "   <MessageType>VALIDATE_PAN</MessageType>\n" +
                "   <MessageUUID>573fbfa0-97e7-11ec-8fbc-bf1589110003</MessageUUID>\n" +
                "   <Body>\n" +
                "       <![CDATA[\n" +
                "       GOOG,INFY,AAPL\n" +
                "       ]]>\n" +
                "   </Body>\n" +
                "</Message>";

        List<String> result = obj.reqMessageParser(msg);
        assertEquals("http://127.0.0.1:23001/foo", result.get(0));
        assertEquals("VALIDATE_PAN", result.get(1));


    }
}
