package com.pageobjectlocators;

import org.openqa.selenium.By;

public class LoginPageLoctors {
	
	public By userNametxt = By.id("username");
	
	public By passwordtxt = By.id("password");
	
	public By loginBtn = By.id("log-in");
	
	public By alertPopup = By.xpath("//div[@class='alert alert-warning']");
	
	public By rememberMeCheckBox = By.xpath("//input[@class='form-check-input']");
	
	public By demoAppLogo = By.xpath("//img[@src='img/logo-big.png']");
	
	public By twitterFooterLink = By.xpath("//img[@src='img/twitter.png']");
	
	public By facebookFooterLink = By.xpath("//img[@src='img/facebook.png']");
	
	public By linkedinFooterLink = By.xpath("//img[@src='img/linkedin.png']");

}
