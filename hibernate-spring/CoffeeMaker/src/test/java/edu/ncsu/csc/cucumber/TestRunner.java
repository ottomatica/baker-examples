package edu.ncsu.csc.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * TestRunner class for the Cucumber tests. Adjust the "features" parameter
 * above as necessary to run just a subset of the tests. The body of the class
 * should be blank -- the annotations are all that is required
 *
 * @author Kai Presler-Marshall
 * @author Sarah Elder
 *
 */
@RunWith ( Cucumber.class )
@CucumberOptions ( features = "src/test/resources/basic/",
        plugin = { "pretty", "junit:\\CoffeeMaker_JUnit_Cucumber\\cucumberTest.xml" } )
public class TestRunner {

}
