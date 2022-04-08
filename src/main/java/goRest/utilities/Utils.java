package goRest;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Random;

public class Utils {

    public String convertResKeysToSqlStatement(String response){
        JSONObject object = new JSONObject(response);
        String statement = "";
        Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!keys.hasNext()) statement += "`" + key + "`";
            else statement += "`" + key + "`, ";
        }
        return statement;
    }

    public String convertResValuesToSqlStatement(String response){
        JSONObject object = new JSONObject(response);
        String statement = "";
        Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!keys.hasNext()) statement += "'" + object.get(key) + "'";
            else statement += "'" + object.get(key) + "', ";
        }
        return statement;
    }

    public String putKeyToJsonString(String json, String key, String value){
        JSONObject obj = new JSONObject(json);
        obj.put(key, value);
        return obj.toString();
    }

    public String generateEmail() {
        return "test" + new Random().nextInt(999999999) + "@yopmail.com";
    }

}
