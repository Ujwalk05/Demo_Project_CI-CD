package com.winmore.utils;

public class CommonUtils {
	
	/**
     * Sleeps for the given number of milliseconds.
     * Wraps Thread.sleep in try/catch so you don’t need to handle it everywhere.
     *
     * @param millis the time to sleep in milliseconds
     */
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.err.println("⚠️ Thread was interrupted while sleeping: " + e.getMessage());
            Thread.currentThread().interrupt(); // restore interrupt status
        }
    }

}
