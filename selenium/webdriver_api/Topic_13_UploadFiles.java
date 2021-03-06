package webdriver_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_UploadFiles {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
    String appiumPath =  projectPath + "\\uploadFiles\\Appium.JPG";
    String seleniumPath = projectPath + "\\uploadFiles\\Selenium.JPG";
    String specflowPath = projectPath + "\\uploadFiles\\Specflow.JPG";
    String testCompletePath = projectPath + "\\uploadFiles\\TestComplete.JPG";
    	
    String fireFoxAutoIT = projectPath + "\\uploadAutoIT\\firefox.exe";
    String chromeAutoIT = projectPath + "\\uploadAutoIT\\chrome.exe";  
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		//Firefox lastest
		System.setProperty("webdriver.gecko.driver", projectPath + "\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Chrome
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\libraries\\chromedriver.exe");
		//driver = new ChromeDriver();
				
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	//@Test
	public void TC_01_SendKeys() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFile = find("//input[@type='file']");
		uploadFile.sendKeys(appiumPath + "\n" + seleniumPath + "\n" + specflowPath + "\n" + testCompletePath);
		Thread.sleep(4000);
		//find("//div[@class='row fileupload-buttonbar']//button[@class='btn btn-primary start']").click();
		//table//
		
		List <WebElement> startButton = finds("//table//button[@class='btn btn-primary start']");
		for(WebElement start : startButton) {
			start.click();
			Thread.sleep(2000);
		}
		Assert.assertTrue(find("//p[@class='name']//a[@title='Appium.JPG']").isDisplayed());
		Assert.assertTrue(find("//p[@class='name']//a[@title='Selenium.JPG']").isDisplayed());
		Assert.assertTrue(find("//p[@class='name']//a[@title='Specflow.JPG']").isDisplayed());
		Assert.assertTrue(find("//p[@class='name']//a[@title='TestComplete.JPG']").isDisplayed());	
	}

	//@Test
	public void TC_02_AutoIT() throws IOException, InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFile.click();
		Thread.sleep(2000);
		
	
		//execute runtime file (.exe/ .msi/ .bat/ .sh/)
		if (driver.toString().contains("firefox")){
			Runtime.getRuntime().exec(new String[] { fireFoxAutoIT, appiumPath});
		} else {
			Runtime.getRuntime().exec(new String[] { chromeAutoIT, appiumPath});
		}
		Thread.sleep(4000);
		find("//table//button[@class='btn btn-primary start']").click();
		Thread.sleep(2000);
		
		Assert.assertTrue(find("//p[@class='name']//a[@title='Appium.JPG']").isDisplayed());
				
	}
	@Test
	public void TC_03_Robot() throws IOException, InterruptedException, AWTException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFile.click();
		Thread.sleep(2000);
		
		//Specify the file location with extension
		StringSelection select = new StringSelection(appiumPath);
		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		/*if(driver.toString().contains("chrome") || driver.toString().contains("firefox")) {
			WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
			uploadFile.click();
			Thread.sleep(1000);
		} else {
			System.out.println("Go to IE");
			WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
			clickToElementByJS(uploadFile);
			Thread.sleep(1000);
		}*/
		Robot robot = new Robot();
		Thread.sleep(1000);
		// Nhấn phím Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Nhấn xuống Ctrl - V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Nhã Ctrl - V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		//Nhấn Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		Thread.sleep(4000);
		find("//table//button[@class='btn btn-primary start']").click();
		Thread.sleep(2000);
		
		Assert.assertTrue(find("//p[@class='name']//a[@title='Appium.JPG']").isDisplayed());
	}
	//public void clickToElementByJS(String locator) {
	//	element = driver.findElement(By.xpath(locator));
	//	jsExecutor.executeScript("arguments[0].click();", element);
	//}

	public WebElement find(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	public List <WebElement> finds(String locator) {
		return driver.findElements(By.xpath(locator));
	}
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}