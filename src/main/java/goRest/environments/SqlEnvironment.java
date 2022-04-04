package goRest.environments;
import static goRest.common.Constants.*;

public class SqlEnvironment {
    private final String baseHost = BASE_HOST;
    private final String host = HOST;
    private final String port = PORT;
    private final String username = SQL_USERNAME;
    private final String password = SQL_PASSWORD;

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
