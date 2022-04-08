import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "",
        features = "src/test/resources/features",
        glue = "goRest",
        monochrome = true,
        plugin= {"pretty","json:target/cucumber/cucumber.json"})

public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

}