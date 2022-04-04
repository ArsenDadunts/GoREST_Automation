package goRest.clients;
import java.sql.*;

import static goRest.common.Constants.*;

public class SQLClient {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public void connect(String base, String host, String port, String dbName, String username, String password) {
        try {
            connection = DriverManager.getConnection(String.format("%s://%s:%s/%s", base, host, port, dbName), username, password);
            statement = connection.createStatement();
            System.out.println(statement.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void disconnect() throws Exception {
        try {
            if (null != resultSet) {
                resultSet.close();
                resultSet = null;
            }
            if (null != statement) {
                statement.close();
                statement = null;
            }
            if (null != connection) {
                connection.close();
                connection = null;
            }
        } catch (Exception e) {
            throw new Exception("Disconnection is failed ... " + e.getMessage());
        }
    }

    public void insertDataToTable(String tableName, String keys, String values) {
        String query = "INSERT INTO " + tableName + " ("+keys+") VALUES ("+values+");";
        System.out.println(query);
        try {
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void getAllDataFromTable(String tableName) throws Exception {
        String query = "SELECT * FROM " + tableName;
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(
                        resultSet.getString(ID)+", " +
                        resultSet.getString(NAME)+","+
                        resultSet.getString(EMAIL)+","+
                        resultSet.getString(GENDER)+","+
                        resultSet.getString(STATUS));
            }
        } catch (Exception e) {
            throw new Exception("Could not get data from the database ..." + e.getMessage() + " Query is: " + query);
        }
    }

    public void getDataFromTableByKey(String tableName, String key, String value) throws Exception {
        String query = "SELECT * FROM " + tableName + " WHERE " + key + "='" + value + "'";
        try {
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            throw new Exception("Could not get data from the database ..." + e.getMessage() + " Query is: " + query);
        }
    }
}
