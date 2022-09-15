package midsem;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JSONFileParser {

    public static class ConfigInfo{
        String host;
        int port;
        String dbURL;
        String logFilePath;
    }
    public ConfigInfo jsonFileParser(String configFilePath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(configFilePath));
        StringBuilder fileContent = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            fileContent.append(line);
        }
        JSONObject jsonObject = new JSONObject(fileContent.toString());
        ConfigInfo cI = new ConfigInfo();
        cI.host = jsonObject.getString("host");
        cI.port = jsonObject.getInt("port");
        cI.dbURL = jsonObject.getString("db_url");
        cI.logFilePath = jsonObject.getString("log_file");
        return cI;
    }
}
