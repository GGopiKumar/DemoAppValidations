package com.genericlibraries;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Utilities {
	
	private static String workBookPath = "src\\test\\Resources\\TestData.xlsx";

	//public final static String sheetName = "TestData";

	public static WebDriver Driver;
	public static ChromeOptions options;
	
	public static String getPageUrl () {
		return Driver.getCurrentUrl();
	}
	
	public static void invokingChromeBrowser() {
		
		options = new ChromeOptions();
		options.addArguments("use-fake-ui-for-media-stream");
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("user-data-dir=C:/temp/");
		//options.setCapability("chrome.binary", "C:\\Users\\Gopikumar\\eclipse-workspace\\DemoApp\\src\\test\\java\\Resources\\ChromeDriver\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "src\\test\\Resources\\chromedriver.exe");
		Driver = new ChromeDriver(options);
		
	}
	


	//enter
	public static void enter(By locator,String val) {
		Driver.findElement(locator).sendKeys(val);
	}

	//clear enter
	public static void clearEnter(By locator,String val) {
		Driver.findElement(locator).clear();
		Driver.findElement(locator).sendKeys(val);
	}

	//click
	public static void click(By locator) {
		Driver.findElement(locator).click();
	}

	//getwebtext
	public static String getWebtext(By locator) {
		return Driver.findElement(locator).getText().trim();
	}

	//getattribute
	public static String getAttributeValue(By locator,String attributeName) {
		return Driver.findElement(locator).getAttribute(attributeName).trim();
	}

	//gettitle
	public static String getPageTitle() {
		return Driver.getTitle().trim();
	}

	//switchtolatestwindow
	public static void switchToLatestWindow(String expTitle) {
		try {
			java.util.Set<String> set =   Driver.getWindowHandles();
			for(String winId : set) {
				Driver.switchTo().window(winId);
				if(getPageTitle().equals(expTitle)) {
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
	}

	//select
	public static Select performSelect(By locator) {
		Select  sel = new Select(Driver.findElement(locator));
		return sel;
	}

	//Actions
	public static Actions performActions() {
		Actions  act = new Actions(Driver);
		return act;
	}

	//alerts
	public static Alert performAlerts() {
		Alert alt = Driver.switchTo().alert();
		return alt;
	}

	//iframes
	public static void switchToFrames(String frameID) {
		Driver.switchTo().frame(frameID);
	}

	public static void switchBackToFrame() {
		Driver.switchTo().defaultContent();
	}

	public static int getRandomNum() {
		Random ran = new Random();
		return ran.nextInt(23764823);
	}

	//screenshots
	public static String captureScreenshots() {
		File dstIMG = null;
		try {
			EventFiringWebDriver  efd = new EventFiringWebDriver(Driver);
			File srcIMG = efd.getScreenshotAs(OutputType.FILE);
			dstIMG = new File("DemoAppValidations\\src\\test\\Resources\\ScreenShot\\"+getRandomNum()+".png");
			FileUtils.copyFile(srcIMG, dstIMG);
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return dstIMG.getPath().trim();
	}


	


	//readtestdata
	public static String getTestData(String sheetName,int rowNo,int celNo) {
		String val = null;
		try {

			java.io.FileInputStream  fis = new java.io.FileInputStream(workBookPath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet  sh = wb.getSheet(sheetName);
			Row rw = sh.getRow(rowNo);
			val = rw.getCell(celNo).getStringCellValue().trim();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}
	//writetestdata
	public static void setTestData(String sheetName,int rowNo,int celNo,String val) {
		try {
			FileInputStream  fis = new FileInputStream(workBookPath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet  sh = wb.getSheet(sheetName);
			Row rw = sh.getRow(rowNo);
			Cell  cel = rw.createCell(celNo);
			cel.setCellValue(val);
			FileOutputStream fos = new FileOutputStream(workBookPath);
			wb.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//explicitlty wait
	public static void waitForElement(Duration time,By locator) {
		WebDriverWait wait = new WebDriverWait(Driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	//Implictilty wait 
	public static void waitForPageLoad(int time) {
		Driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	//close
	public static void closeWindow() {
		Driver.close();
	}
	//quit
	public static void closeAllWindows() {
		Driver.quit();
		
	}



	//is dispalyed
	public static boolean isElementDisplayed(By locator) {
		return Driver.findElement(locator).isDisplayed();
	}

	//is selected
	public static boolean isElementSelected(By locator) {
		return Driver.findElement(locator).isSelected();
	}

	//is enabled
	public static boolean isElementEnabled(By locator) {
		return Driver.findElement(locator).isEnabled();
	}
	
	

}
