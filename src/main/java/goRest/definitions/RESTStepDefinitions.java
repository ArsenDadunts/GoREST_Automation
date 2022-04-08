package goRest.definitions;

import goRest.utilities.Utils;
import goRest.clients.RESTClient;
import goRest.common.Data;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class RESTStepDefinitions {
    RESTClient restClient = new RESTClient();
    Utils utils = new Utils();
    static Response response;
    static String httpMethod, endpoint, body;

    @Given("I create HTTP {string} request to Users API with {string} endpoint")
    public void updateUser(String method, String path) {
        httpMethod = method;
        endpoint = path;
    }

    @Given("I set {string} as request body")
    public void setRequestBody(String requestBody){
        body = requestBody;
    }

    @Given("I set {string} stored data as {string} path segment")
    public void setStoredDataAsPathSegment(String dataKey, String path){
        restClient.setPathParameter(path, Data.get(dataKey).toString());
    }

    @Given("I set {string} as {string} path segment")
    public void setAsPathSegment(String dataKey, String path){
        restClient.setPathParameter(path, dataKey);
    }

    @Given("I store {string} value data with {string} key")
    public void storeKey(String responseKey, String dataKey) {
        Data.put(dataKey, response.path(responseKey).toString());
    }

    @Given("I set {string} as {string} retrieved from stored data")
    public void setBodyParamRetrievedFromStoredData(String key, String dataKey) {
        body = utils.putKeyToJsonString(body, key, Data.get(dataKey).toString());
    }

    @Given("I set {string} as random generated email")
    public void setBodyParamAsRandomEmail(String key) {
        body = utils.putKeyToJsonString(body, key, utils.generateEmail());
    }

    @Given("I send the request")
    public void sendRequest(){
        if (body != null) restClient.setRequestBody(body);
        response = restClient.send(endpoint, httpMethod);
    }
}
