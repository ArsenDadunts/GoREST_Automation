package goRest.clients;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;

import static java.lang.System.setProperty;

public class BaseClass {

    public RestAssuredConfig config;

    public BaseClass() {
        setProperty("http.maxConnections", "100");
        config = RestAssuredConfig.config()
                .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        //curl-logger config
        config = CurlLoggingRestAssuredConfigFactory.updateConfig(config,
                Options.builder().updateCurl(curl -> curl
                        .removeHeader("Host")
                        .removeHeader("User-Agent")
                        .removeHeader("Connection")
                        .removeHeader("Content-Length")
                        .setCompressed(false)
                        .setInsecure(false)
                        .setVerbose(false)
                ).useShortForm().build());
    }
}