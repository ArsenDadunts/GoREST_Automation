package goRest.clients;

import goRest.common.Data;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static goRest.common.Constants.*;
import static io.restassured.RestAssured.given;

public class RESTClient extends BaseClass{
    RequestSpecification request = given().config(config);

    public void setRequestBody(String body){
        request.contentType(ContentType.JSON);
        request.body(body);
    }

    public void setPathParameter(String key, String value){
        request.pathParam(key, value);
    }

    public Response send(String methodPath, String methodType) {
        request.baseUri(BASE_URL).auth().oauth2(ACCESS_TOKEN);
        Response response = null;
        switch (methodType) {
            case GET:
                response = request.get(methodPath);
                break;
            case PUT:
                response = request.put(methodPath);
                break;
            case POST:
                response = request.post(methodPath);
                break;
            case DELETE:
                response = request.delete(methodPath);
                break;
            case PATCH:
                response = request.patch(methodPath);
                break;
            case OPTIONS:
                response = request.options(methodPath);
                break;
            default:
                throw new RuntimeException("MethodType is not specified for the API method: " + methodPath);
        }
        response.prettyPrint();
        putResponseToData(response.asString());
        putResponseCodeToData(response.getStatusCode());
        return response;
    }

    public void putResponseToData(String responseBody) {
        Data.put(API_RESPONSE_BODY, responseBody);
    }

    public void putResponseCodeToData(int responseCode) {
        Data.put(API_RESPONSE_CODE, responseCode);
    }
}
