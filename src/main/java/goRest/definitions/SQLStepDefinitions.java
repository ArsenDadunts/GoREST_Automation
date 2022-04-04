package goRest.definitions;

import goRest.Utils;
import goRest.clients.SQLClient;
import goRest.environments.SqlEnvironment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

public class SQLStepDefinitions {
    SQLClient sqlClient = new SQLClient();
    SqlEnvironment sqlConf = new SqlEnvironment();
    private static Response response;
    Utils utils = new Utils();

    @Given("I connect to {string} Sql")
    public void connectToSql(String tableName) {
        sqlClient.connect(sqlConf.getBaseHost(), sqlConf.getHost(), sqlConf.getPort(), tableName, sqlConf.getUsername(), sqlConf.getPassword());
    }

    @Given("^I disconnect from Sql")
    public void disconnectSql() {
        try {
            sqlClient.disconnect();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Then("I store response data in {string} Sql")
    public void storeDataInSql(String tableName) {
        sqlClient.insertDataToTable(tableName, utils.convertResKeysToSqlStatement(response), utils.convertResValuesToSqlStatement(response));
    }

    @Then("I get all data from {string} Sql")
    public void getDataFromSql(String tableName) {
        try {
            sqlClient.getAllDataFromTable(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
