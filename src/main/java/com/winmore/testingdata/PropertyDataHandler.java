package com.winmore.testingdata;

import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;

public class PropertyDataHandler {
    public static String fetchPropertyValue(String fileName, String strVarName) {
        String propertyValue = null;
        try {
            Properties properties = new Properties();
            ClassLoader classLoader = PropertyDataHandler.class.getClassLoader();
            InputStream input = classLoader.getResourceAsStream(fileName + ".properties");

            if (input == null) {
                Assert.fail("Could not find properties file: " + fileName + ".properties");
            }

            properties.load(input);
            propertyValue = properties.getProperty(strVarName);

            if (propertyValue == null) {
                Assert.fail("Property is not defined for: " + strVarName);
            }

        } catch (Exception e) {
            Assert.fail("Failed to load properties: " + e.getMessage());
        }
        return propertyValue;
    }
}
