package goRest.environments;

public class SqlEnvironment {
    private final String baseHost = "jdbc:mysql";
    private final String host = "localhost";
    private final String port = "3306";
    private final String username = "root";
    private final String password = "Test1234";

    public String getBaseHost(){
        return baseHost;
    }
    public String getHost(){
        return host;
    }


    public String getPort(){
        return port;
    }

    public String getUsername(){
        return username;
    }


    public String getPassword(){
        return password;
    }
}
