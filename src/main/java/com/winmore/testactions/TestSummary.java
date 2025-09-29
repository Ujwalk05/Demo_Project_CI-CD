package com.winmore.testactions;

public class TestSummary {
	
	static int totalTests;
	 static int passedTests;
	 static int failedTests;
	 static  int skippedTests;

    public TestSummary(int totalTests, int passedTests, int failedTests, int skippedTests) {
        TestSummary.totalTests = totalTests;
        TestSummary.passedTests = passedTests;
        TestSummary.failedTests = failedTests;
        TestSummary.skippedTests = skippedTests;
    }

    public static void incrementTotalTests() {
        totalTests++;
    }

    public static void incrementPassedTests() {
        passedTests++;
    }

    public static void incrementFailedTests() {
        failedTests++;
    }

    public static void incrementSkippedTests() {
        skippedTests++;
    }

}
