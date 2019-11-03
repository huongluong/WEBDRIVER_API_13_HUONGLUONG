package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPath_Part_I {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
    String username = "mngr231005";
    String password = "duvabyq";
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	//@Test
	public void TC_01_() {
		
		System.out.println("Mở ra trang UAT");
		driver.get("http://live.demoguru99.com");
		
		System.out.println("Click vào My Account link ở dưới header");
		//driver.findElement(By.xpath("//span[text()='Account']")).click();
		//driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
		
		System.out.println("Click vào My Account link ở dưới footer");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		
	}

	@Test
	public void TC_02_Start_With() {
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		String welcomeMsgID = driver.findElement(By.xpath("//td[starts-with(text(),'Manger Id')]")).getText();
		System.out.println("Text = " + welcomeMsgID);
		Assert.assertTrue(welcomeMsgID.contains(username));
		//or use Text()= to check value 
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + username + "']")).isDisplayed());
		
		
		
	}

		
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}