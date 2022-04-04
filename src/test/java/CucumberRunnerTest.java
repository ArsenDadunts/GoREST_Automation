import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = "src/test/resources/features", glue = "goRest")

public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

}