package goRest.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static goRest.common.Constants.*;
import static io.restassured.RestAssured.given;

public class RESTClient extends BaseClass{


    public Response getUsers() {
        Response response = given().config(config)
                .baseUri(BASE_URL)
                .get(USERS);
        response.prettyPrint();
        return response;
    }

    public Response getUser(String userId) {
        Response response = given().config(config)
                .baseUri(BASE_URL)
                .auth().oauth2(ACCESS_TOKEN)
                .pathParam(USER_ID, userId)
                .get(USER_BY_ID);
        response.prettyPrint();
        return response;
    }
    public Response createNewUser(Object body) {
        Response response = given().config(config)
                .baseUri(BASE_URL)
                .auth().oauth2(ACCESS_TOKEN)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .post(USERS);
        response.prettyPrint();
        return response;
    }

    public Response updateUser(String userId, Object body) {
        Response response = given().config(config)
                .baseUri(BASE_URL)
                .auth().oauth2(ACCESS_TOKEN)
                .pathParam(USER_ID, userId)
                .body(body)
                .put(USER_BY_ID);
        response.prettyPrint();
        return response;
    }

    public Response deleteUser(String userId) {
        Response response = given().config(config)
                .baseUri(BASE_URL)
                .auth().oauth2(ACCESS_TOKEN)
                .pathParam(USER_ID, userId)
                .delete(USER_BY_ID);
        response.prettyPrint();
        return response;
    }
}
