package com.winmore.pages;

public class LoginPage{
	public String sighInButton = "//button[@type='submit']";
	public String sighInButton_Description = "SignIn Button";
	
	public String email = "//input[@type='email']";
	
	public String password = "//input[@type='password']";
	
	public String selectOrgElement = "//h3[normalize-space()='Select an organization']";
	
	public String engineeringAutomation = "//li[@class='organization']//button//span[contains(text(),'Engineering Automation')]";
	
	public String userProfileImage = "//div[contains(@class, 'nav-header__button') and contains(@class, 'nav-header__button--avatar')]//div[contains(@class, '-u-avatar') and contains(@class, '-u-avatar--initials')]";
	
	public String logoutButton = "//span[contains(text(),'Logout')]";
}
