package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class Topic_09_User_Interaction {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
    Actions action;
    
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		System.out.println(driver);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	@Test
	public void TC_01_moveMouseHover() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Discover']"))).perform();
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and  text()='American Eagle']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title' and text()='American Eagle']")).isDisplayed());
		
	}
    
	@Test
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