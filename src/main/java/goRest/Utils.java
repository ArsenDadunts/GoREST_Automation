package goRest;

import io.restassured.response.Response;
import org.json.*;

import java.util.Iterator;

public class Utils {

    public String convertResKeysToSqlStatement(Response response){
        JSONObject object = new JSONObject(response.prettyPrint());
        String statement = "";
        Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!keys.hasNext()) statement += "`" + key + "`";
            else statement += "`" + key + "`, ";
        }
        return statement;
    }

    public String convertResValuesToSqlStatement(Response response){
        JSONObject object = new JSONObject(response.prettyPrint());
        String statement = "";
        Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!keys.hasNext()) statement += "'" + object.get(key) + "'";
            else statement += "'" + object.get(key) + "', ";
        }
        return statement;
    }
}
