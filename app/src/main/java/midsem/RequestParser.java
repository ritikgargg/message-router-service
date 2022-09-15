package midsem;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestParser {
    Logger logr;

    public RequestParser(Logger logr) {
        this.logr = logr;
    }

    public List<String> reqMessageParser(String msg) {
        try {
            List<String> parsedResult = new ArrayList<>(2);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(msg)));
            document.getDocumentElement().normalize();
            Node sender = document.getElementsByTagName("Sender").item(0);
            Node messageType = document.getElementsByTagName("MessageType").item(0);
            parsedResult.add(sender.getTextContent());
            parsedResult.add(messageType.getTextContent());
            return parsedResult;
        }catch (ParserConfigurationException e){
            logr.log(Level.WARNING, "ParserConfigurationException occurred", e);
        }catch (IOException e){
            logr.log(Level.WARNING, "IOException occurred", e);
        }catch(SAXException e){
            logr.log(Level.WARNING, "SAXException occurred", e);
        }
        return null;
    }
}
