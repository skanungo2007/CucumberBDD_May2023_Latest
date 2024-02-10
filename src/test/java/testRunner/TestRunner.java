package testRunner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//To generate Cucumber Report first convert to TestNG and create TestNG.xml file
//Then right click Project -> Run As -> Maven Install  - mvn clean install


@RunWith(Cucumber.class)
@CucumberOptions(
		
			features = {"src/test/resources/features/SmokeTest.feature", "src/test/resources/features/RegressionTest.feature"},
			glue = {"hooks","stepDefinitions"},
			plugin = {"pretty", "html:target/html_report", "json:target/cucumber-reports/JSONReport.json", "junit:target/cucumber-reports/XMLReport.xml"},
			monochrome = true,
			dryRun = false,
			tags = "@Smoke or @Regression"
		)


public class TestRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel=false)
	public Object[][] scenarios() {
		
		return super.scenarios();
	}

}
