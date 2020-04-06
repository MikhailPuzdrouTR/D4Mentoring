package com.mp.d4mentroing.module2;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.TestNGCucumberRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/functional-test/features")
public class CucumberTest {

    @Test()
    public void runCukes() {
        new TestNGCucumberRunner(getClass()).runCukes();
    }
}
