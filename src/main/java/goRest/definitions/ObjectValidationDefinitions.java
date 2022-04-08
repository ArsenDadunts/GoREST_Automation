package goRest.definitions;

import goRest.common.Data;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static goRest.common.Constants.*;

public class ObjectValidationDefinitions {
    private static JsonPath js;

    @Then("The {string} key is not null or empty")
    public void verifyNotNullOrEmpty(String key) {
        Assert.assertNotNull(getJsonPath(String.valueOf(Data.get(API_RESPONSE_BODY)), key));
        Assert.assertFalse(getJsonPath(String.valueOf(Data.get(API_RESPONSE_BODY)), key).toString().isEmpty());
    }

    @Then("The {string} key is equal to {string}")
    public void verifyIsEqualTo(String key, String expected) {
        Assert.assertEquals(expected, getJsonPath(String.valueOf(Data.get(API_RESPONSE_BODY)), key));
    }

    @Then("The {string} key is equal to stored data with {string} key")
    public void verifyIsEqualToStoredData(String key, String storedKey) {
        Assert.assertEquals(Data.get(storedKey), getJsonPath(String.valueOf(Data.get(API_RESPONSE_BODY)), key));
    }

    @Then("The response code should be equal to {int}")
    public void verifyResponseCode(int value) {
        Assert.assertEquals(Data.get(API_RESPONSE_CODE), value);
    }

    @Then("{string} is equal to {string}")
    public void objectHasKey(String key, String value) {
        Assert.assertEquals(getJsonPath(Data.get(API_RESPONSE_BODY).toString(), key), value);
    }

    @Then("{string} exists")
    public void objectHasKey(String key) {
        getJsonPath(String.valueOf(Data.get(API_RESPONSE_BODY)), key);
    }

    public static Object getJsonPath(String responseBody, String key) {
        js = new JsonPath(responseBody);
        return js.get(key);
    }
}
