import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="features/test.feature", glue = {"StepDefs"})
public class TestClass extends AbstractTestNGCucumberTests {

}