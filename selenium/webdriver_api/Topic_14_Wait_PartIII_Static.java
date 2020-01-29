package webdriver_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_PartIII_Static {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;

	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//apply để chờ Element hiển thị , tương tác vào -> findElement, findElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//apply để chờ cho page được load xong
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
    
	
	@Test
	public void TC_01_Static() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		System.out.println("Start sleep : " + getCurrentTime());
		
		Thread.sleep(5000);
		System.out.println("End sleep : " + getCurrentTime()) ;
		driver.findElement(By.id("search_query_top")).sendKeys("Automation");
	}
    public String getCurrentTime() {
    	Date date = new Date();
    	return date.toString();
    }
	//@Test
	public void TC_02_() {
		driver.get("");
	}

		
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}