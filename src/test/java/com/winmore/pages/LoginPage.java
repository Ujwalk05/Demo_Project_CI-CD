package com.winmore.pages;

public class LoginPage{

	public String sighInButton = "//button[@type='submit']";
	public String sighInButton_Description = "SignIn Button";

	public String email = "//input[@type='email']";
	public String email_Description = "Email input field";

	public String password = "//input[@type='password']";
	public String password_Description = "Password input field";

	public String selectOrgElement = "//h3[normalize-space()='Select an organization']";
	public String selectOrgElement_Description = "Select an organization heading";

	public String engineeringAutomation = "//li[@class='organization']//button//span[contains(text(),'Engineering Automation')]";
	public String engineeringAutomation_Description = "Engineering Automation organization option";
	
	public String automationExtension = "//li[@class='organization']//button//span[contains(text(),'Automating Extension')]";
	public String automationExtension_Description = "Automation Extension organization option";
	
	public String OrganizationSelection_RateAIAutomationBtn = "//li[@class='organization']//button//span[contains(text(),'RateAI Automation')]";
	public String OrganizationSelection_RateAIAutomationBtn_description = "Button for RateAI Automation org";

	public String userProfileImage = "//div[contains(@class, 'nav-header__button') and contains(@class, 'nav-header__button--avatar')]//div[contains(@class, '-u-avatar') and contains(@class, '-u-avatar--initials')]";
	public String userProfileImage_Description = "User profile avatar image/button";

	public String logoutButton = "//span[contains(text(),'Logout')]";
	public String logoutButton_Description = "Logout button";

}
