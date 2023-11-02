package TestCasesDefinations;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.genericlibraries.Utilities;
import com.pageobjectlocators.HomePageLocators;
import com.pageobjectlocators.LoginPageLoctors;

import org.testng.annotations.BeforeSuite;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.testng.annotations.AfterSuite;

public class DemoAppTestCasesExecution extends Utilities{
	
	DemoAppTestCasesExecution demo ;
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark;
	ExtentTest Test ;
	LoginPageLoctors lpl = new LoginPageLoctors();
	HomePageLocators hpl = new HomePageLocators();



	@BeforeSuite
	public void beforeSuite() {
		spark = new ExtentSparkReporter("target\\Spark.html");
		extent.attachReporter(spark);
		demo.invokingChromeBrowser();
	}


	@Test
	public void testCase001() {
		Test =  extent.createTest("Test Case 1 : Navigation to Login Page and Verify the WebElements in the Page");
		Test.log(Status.INFO, "This is a logging event for LoginPageUIValidation , and it is in Progress");
		Driver.navigate().to(demo.getTestData("TestData",1, 0));
		
		String expectedTitle = demo.getTestData("TestData", 1, 1);
		String actualTitle = Driver.getTitle().trim();
		assertEquals(actualTitle, expectedTitle);
		Test.log(Status.PASS,"Properly Navigated to Login.html");
		
		assertTrue(Driver.findElement(lpl.userNametxt).isDisplayed(), "UserName TextBox is Displaying Properly");
		Test.log(Status.PASS, "Username Text Box is displaying Proper");
		
		assertTrue(Driver.findElement(lpl.passwordtxt).isDisplayed(), "PassWord TextBox is Displaying Properly");
		Test.log(Status.PASS, "Password Text Box is displaying Proper");
		
		assertTrue(Driver.findElement(lpl.loginBtn).isDisplayed(), "Login Button is Displaying Properly");
		assertTrue(Driver.findElement(lpl.rememberMeCheckBox).isDisplayed(), "RemeberMe CheckBox is Displaying Properly");
		Test.log(Status.PASS, "Login Button and RemeberMe CheckBox is displaying Proper");
		
		assertTrue(Driver.findElement(lpl.twitterFooterLink).isDisplayed(), "Twitter Footer Link is Displaying Properly");
		assertTrue(Driver.findElement(lpl.facebookFooterLink).isDisplayed(), "FaceBook Footer Link is Displaying Properly");
		assertTrue(Driver.findElement(lpl.linkedinFooterLink).isDisplayed(), "Linkendin Footer Link is Displaying Properly");
		assertTrue(Driver.findElement(lpl.demoAppLogo).isDisplayed(), "LOGO is Displaying Properly");
		//Test.log(Status.PASS, "All the Footer Links and companyLogo displaying Proper");
		Test.addScreenCaptureFromPath(demo.captureScreenshots());
			
		if(demo.isElementDisplayed(lpl.demoAppLogo)) {
			Test.log(Status.PASS, "All the Footer Links and companyLogo displaying Proper");
		}else {
			Test.log(Status.FAIL, "DemoApp Page is not displaying");
		}
		
	}

	@Test
	public void testCase002() {
		Test =  extent.createTest("Test Case 2 : Validation of Login Functionality with Multiple Username and Password");
		Test.log(Status.INFO, "This is a logging event for Login Functionality Validation , and it is in Progress");
		Driver.findElement(lpl.userNametxt).sendKeys(demo.getTestData("TestData", 3, 0));
		Driver.findElement(lpl.passwordtxt).sendKeys(demo.getTestData("TestData", 3, 1));
		Test.log(Status.INFO, "Trying to Login with Alphabet Formatted Username and Password");
		Test.addScreenCaptureFromPath(demo.captureScreenshots());
		Driver.findElement(lpl.loginBtn).click();
		Test.log(Status.PASS, "Login Succesfull with Alphabet Formatted Username and Password ");
		assertTrue(Driver.findElement(hpl.amountHeader).isDisplayed(), "Header is not Displaying Properly");
		
		
		Driver.navigate().back();
	    demo.clearEnter(lpl.userNametxt, demo.getTestData("TestData", 4, 0));
	    demo.clearEnter(lpl.userNametxt, demo.getTestData("TestData", 4, 1));
	    Test.log(Status.INFO, "Trying to Login with AlphaNumeric Formatted Username and Password");
		//Test.addScreenCaptureFromPath(demo.captureScreenshots());
		Driver.findElement(lpl.loginBtn).click();
		Test.log(Status.PASS, "Login Succesfull with AlphabetNUmeric Formatted Username and Password ");
	    assertTrue(Driver.findElement(hpl.amountHeader).isDisplayed(), "Header is not Displaying Properly");
		
		Driver.navigate().back();
	    demo.clearEnter(lpl.userNametxt, demo.getTestData("TestData", 5, 0));
	    demo.clearEnter(lpl.userNametxt, demo.getTestData("TestData", 5, 1));
	    Test.log(Status.INFO, "Trying to Login with Same Username and Password");
		//Test.addScreenCaptureFromPath(demo.captureScreenshots());
		Driver.findElement(lpl.loginBtn).click();
		Test.log(Status.PASS, "Login Succesfull with Same Username and Password ");
	    assertTrue(Driver.findElement(hpl.amountHeader).isDisplayed(), "Header is not Displaying Properly");
		
		Driver.navigate().back();
	    demo.clearEnter(lpl.userNametxt, demo.getTestData("TestData", 6, 0));
	    demo.clearEnter(lpl.userNametxt, demo.getTestData("TestData", 6, 1));
	    Test.log(Status.INFO, "Trying to Login with Special Character formatted Username and Password");
		//Test.addScreenCaptureFromPath(demo.captureScreenshots());
		Driver.findElement(lpl.loginBtn).click();
		
	    assertTrue(Driver.findElement(hpl.amountHeader).isDisplayed(), "Header is not Displaying Properly");
	    Test.addScreenCaptureFromPath(demo.captureScreenshots());
		
	    if(demo.isElementDisplayed(hpl.amountHeader)) {
	    	Test.log(Status.PASS, "Login Succesfull with Special Character formatted Username and Password ");
		}else {
			Test.log(Status.FAIL, "DemoApp Page is not displaying");
		}
	    
	    Driver.navigate().back();
	}

	@Test
	public void testCase003() {
		Test =  extent.createTest("Test Case 3 : Try to Login the DemoApp with Password as Empty");
		Test.log(Status.INFO, "This is a logging event for AlertValidationWhenPasswordIsEmpty , and it is in Progress");
		Driver.findElement(lpl.passwordtxt).clear();
		demo.clearEnter(lpl.userNametxt, demo.getTestData("TestData", 4, 0));
		Driver.findElement(lpl.loginBtn).click();
		Test.addScreenCaptureFromPath(demo.captureScreenshots());
		String expectedAlert = demo.getTestData("TestData", 1, 2).trim();
		String actualAlert = demo.getWebtext(lpl.alertPopup);
		assertEquals(actualAlert, expectedAlert);
		if(demo.isElementDisplayed(lpl.alertPopup)) {
			Test.log(Status.PASS, "Alert is displaying Properly");
		}else {
			Test.log(Status.FAIL, "Alert is not displaying");
		}
				
		
		
	}

	@Test
	public void testCase004() {
		Driver.navigate().refresh();
		Test =  extent.createTest("Test Case 4 : Try to Login the DemoApp with username as Empty");
		Test.log(Status.INFO, "This is a logging event for AlertValidationWhenUserNameIsEmpty , and it is in Progress");
		Driver.findElement(lpl.userNametxt).clear();
		demo.clearEnter(lpl.passwordtxt, demo.getTestData("TestData", 4, 1));
		Driver.findElement(lpl.loginBtn).click();
		Test.addScreenCaptureFromPath(demo.captureScreenshots());
		String expectedAlert = demo.getTestData("TestData", 1, 3).trim();
		String actualAlert = demo.getWebtext(lpl.alertPopup);
		assertEquals(actualAlert, expectedAlert);
		
		
		if(demo.isElementDisplayed(lpl.alertPopup)) {
			Test.log(Status.PASS, "Alert is displaying Properly");
		}else {
			Test.log(Status.FAIL, "Alert is not displaying");
		}
	}

	@Test
	public void testCase005() {
		Driver.navigate().refresh();
		Test =  extent.createTest("Test Case 5 : Try to Login the DemoApp with both username and password as Empty");
		Test.log(Status.INFO, "This is a logging event for AlertValidationWhenUserNameAndPasswordIsEmpty , and it is in Progress");
		Driver.findElement(lpl.userNametxt).clear();
		Driver.findElement(lpl.passwordtxt).clear();
		Driver.findElement(lpl.loginBtn).click();
		Test.addScreenCaptureFromPath(demo.captureScreenshots());
		String expectedAlert = demo.getTestData("TestData", 1, 4).trim();
		String actualAlert = demo.getWebtext(lpl.alertPopup);
		assertEquals(actualAlert, expectedAlert);
		
		if(demo.isElementDisplayed(lpl.alertPopup)) {
			Test.log(Status.PASS, "Alert is displaying Properly");
		}else {
			Test.log(Status.FAIL, "Alert is not displaying");
		}

	}
	@Test
	public void testCase006() {
		Test =  extent.createTest("Test Case 6 : Validation of RemeberMe Functionality on DemoApp");
		Test.log(Status.INFO, "This is a logging event for RemeberMeFunctionalityValidation , and it is in Progress");
		Driver.navigate().refresh();
		demo.waitForElement(Duration.ofSeconds(2000), lpl.demoAppLogo);
		demo.clearEnter(lpl.userNametxt, demo.getTestData("TestData", 4, 0));
	    demo.clearEnter(lpl.passwordtxt, demo.getTestData("TestData", 4, 1));
	    demo.click(lpl.rememberMeCheckBox);
	    demo.click(lpl.loginBtn);
	    assertTrue(Driver.findElement(hpl.amountHeader).isDisplayed(), "Header is not Displaying Properly");
	    Driver.close();
	    demo.invokingChromeBrowser();
	    Driver.navigate().to(demo.getTestData("TestData",1, 0));
	    Test.addScreenCaptureFromPath(demo.captureScreenshots());
	    
		if(demo.isElementDisplayed(lpl.userNametxt)) {
			Test.log(Status.FAIL, "RememberMe functionality Not  working ");	
		}else {
			Test.log(Status.PASS, "RememberMe functionality is working Properly");
		}
		Driver.close();
			
	}
	@Test
	public void testCase007() {
		Test =  extent.createTest("Test Case 7 : Home Page Account Header Sorting Validation on DemoApp");
		Test.log(Status.INFO, "This is a logging event for Home Page Account Header Sorting Validation , and it is in Progress");
		demo.invokingChromeBrowser();
		Driver.navigate().to(demo.getTestData("TestData",1, 0));
		demo.waitForElement(Duration.ofSeconds(2000), lpl.demoAppLogo);
		demo.clearEnter(lpl.userNametxt, demo.getTestData("TestData", 4, 0));
	    demo.clearEnter(lpl.passwordtxt, demo.getTestData("TestData", 4, 1));
	    demo.click(lpl.rememberMeCheckBox);
	    demo.click(lpl.loginBtn);
	    demo.waitForElement(Duration.ofSeconds(2000), hpl.amountHeader);
	    demo.click(hpl.amountHeader);
	    double[] arr = new double[6];
	    arr[0] = Double.parseDouble(demo.getWebtext(hpl.amountHeaderFirstValue).replaceAll("[a-zA-Z#$% ]", "").replaceAll(" ", ""));
	    arr[1] = Double.parseDouble(demo.getWebtext(hpl.amountHeaderSecondValue).replaceAll("[a-zA-Z#$% ]", "").replaceAll(" ", ""));
	    arr[2] = Double.parseDouble(demo.getWebtext(hpl.amountHeaderThirdValue).replaceAll("[a-zA-Z#$% ]", "").replaceAll(" ", ""));
	    arr[3] = Double.parseDouble(demo.getWebtext(hpl.amountHeaderFourthValue).replaceAll("[a-zA-Z#$% ]", "").replaceAll(" ", ""));
	    arr[4] = Double.parseDouble(demo.getWebtext(hpl.amountHeaderFifthValue).replaceAll("[a-zA-Z#$% ]", "").replaceAll(" ", ""));
	    arr[5] = Double.parseDouble(demo.getWebtext(hpl.amountHeaderSixthValue).replaceAll("[a-zA-Z#$% ]", "").replaceAll(" ", "").replaceAll(",",""));
	    Test.addScreenCaptureFromPath(demo.captureScreenshots());
	    for(int i=0; i<arr.length-1; i++) {
	        if(arr[i]>arr[i+1]) {
	        	Test.log(Status.FAIL, "Sorting Failed");
	        }
	        
	      }
	    Test.log(Status.PASS, "Home Page Account Header Sorting Validation is completed , and TestCase is Passed");
			
	}
	


	@AfterSuite
	public void afterSuite() {
		Driver.quit();
		extent.flush();
	}

}
