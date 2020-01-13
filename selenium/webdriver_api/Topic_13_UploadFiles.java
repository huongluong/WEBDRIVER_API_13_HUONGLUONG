package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    String testCompletePath = projectPath + "\\\\uploadFiles\\\\TestComplete.JPG";
    		
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

	
	@Test
	public void TC_01_SendKeys() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFile = find("//input[@type='file']");
		uploadFile.sendKeys(appiumPath + "\n" + seleniumPath + "\n" + specflowPath + "\n" + testCompletePath);
		Thread.sleep(4000);
		find("//div[@class='row fileupload-buttonbar']//button[@class='btn btn-primary start']").click();
		Assert.assertTrue(find("//p[@class='name']//a[@title='Appium.JPG']").isDisplayed());
		
		
		
	}

	//@Test
	public void TC_02_() {
		driver.get("");
	}

	public WebElement find(String locator) {
		return driver.findElement(By.xpath(locator));
	}
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}