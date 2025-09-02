package com.winmore.utils;

import com.winmore.base.TestBase;
import com.winmore.exceptions.AutomationException;
import com.winmore.testingdata.PropertyDataHandler;

public class TestEnvironmentData {
	
	private static String environment;

    public static String getEnv() {
        if (environment == null) {
            // Check system property first, with 'qaprod' as the default fallback
            environment = System.getProperty("environment", TestBase.environment).toLowerCase();
        }
        return environment;
    }

    public static String getTestDataBasedOnEnvironment(String testData) throws AutomationException {
        String testDataValue = null;
        try {
            // Get the current environment dynamically
            String currentEnv = getEnv();
            testDataValue = PropertyDataHandler.fetchPropertyValue(currentEnv, testData);

            if (testDataValue == null) {
                throw new AutomationException("❌ Key not found in environment file for '" + currentEnv + "': " + testData);
            }
        } catch (Exception e) {
            throw new AutomationException("❌ Invalid environment name or property missing: ", e);
        }
        return testDataValue;
    }


}
