package com.matschie.servicenow.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/com/matschie/servicenow/projectday/features/Incident.feature"},
		          glue = {"com.matschie.servicenow.projectday.step.defs"},
		          dryRun = false,
		          plugin = {
		        		  "pretty",
		        		  "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
		          }
		        )
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {

}