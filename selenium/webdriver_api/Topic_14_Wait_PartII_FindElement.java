package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_PartII_FindElement {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;

	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	@Test
	public void TC_01_FindElement() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		//Case 1 : Không tìm thấy Element nào hết
		
		//Case 2 : Tìm thấy duy nhất 1 element
		
		//Case 3 : Tìm thấy nhiều hơn 1 element (node) 
	}
	

	@Test
	public void TC_02_FindElements() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
	}

		
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}