package goRest.definitions;

import goRest.utilities.Utils;
import goRest.clients.SQLClient;
import goRest.common.Data;
import goRest.environments.SqlEnvironment;
import io.cucumber.java.en.Given;
import org.testng.Assert;

import static goRest.common.Constants.API_RESPONSE_BODY;

public class SQLStepDefinitions {
    SQLClient sqlClient = new SQLClient();
    SqlEnvironment sqlConf = new SqlEnvironment();
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

    @Given("I store response data in {string} Sql")
    public void storeDataInSql(String tableName) {
        sqlClient.insertDataToTable(tableName,
                utils.convertResKeysToSqlStatement(Data.get(API_RESPONSE_BODY).toString()),
                utils.convertResValuesToSqlStatement(Data.get(API_RESPONSE_BODY).toString()));
    }

    @Given("I get all data from {string} Sql")
    public void getDataFromSql(String tableName) {
        try {
            sqlClient.getAllDataFromTable(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
