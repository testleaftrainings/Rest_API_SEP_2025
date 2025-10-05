package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/IncidentRecord.feature:10"},
		          glue = {"step.definitions"},
		          dryRun = false,
		          plugin = {
		        		  "pretty",
		        		  "html:cucumber-reports/result.html"
		          },
		          publish = true
		        )
public class TestNGRunner extends AbstractTestNGCucumberTests {

}