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
	public void TC_01_Static() throws Exception {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		System.out.println("Start sleep - " + getCurrentTime());
		//1000ms = 1s
		//Case 1- Nếu như page được load xong trong 3s thì sẽ mất 2s bị lãng phí
		//Case 2- Nếu như page được load xong trong 10s hoặc hơn thì bị thiếu thời gian -> fail testcase
		// Ko flexible
		// 1 testcase = 30 steps x 1s = 30s
		// 300 testcases x 30 steps x 1s = 9000s = 150m = 2.5hrs
		// 300 testcases x 3 browsers = 2.5hrs x 3 = 7.5hrs
		// Special case : Internet Explorer
		Thread.sleep(5000);
		System.out.println("End sleep - " + getCurrentTime());
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

	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}