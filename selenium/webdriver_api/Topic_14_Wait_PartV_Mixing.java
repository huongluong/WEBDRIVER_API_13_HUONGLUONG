package webdriver_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_PartV_Mixing {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
	WebDriverWait explicitWait;
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		//Explicit Wait (Element status)
		//explicitWait = new WebDriverWait(driver, 10);
		
		//Implicit wait (Find element/s)
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}

	
	//@Test
	public void TC_01_Found_Element() {
		driver.get("http://demo.guru99.com/");
		
		//Implicit
		System.out.println("----------STEP 01 - Start TC_01_Found_Element: " + new Date() + "------------");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='emailid']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("Switch to catch exception");
		}
		System.out.println("----------STEP 01 - End TC_01_Found_Element: " + new Date() + "--------------");
		
		//Explicit
		System.out.println("----------STEP 02 - Start TC_01_Found_Element: " + new Date() + "------------");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@name='emailid']")));
		} catch (Exception ex) {
			System.out.println("Switch to catch exception");
		}
		System.out.println("----------STEP 02 - End TC_01_Found_Element: " + new Date() + "--------------");

	}
	@Test
	public void TC_01_Not_Found_Element() {
		//Explicit Wait (Element status)
		explicitWait = new WebDriverWait(driver, 7);
				
		//Implicit wait (Find element/s)
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("http://demo.guru99.com/");
		
		
		//Implicit (Not found element)
		System.out.println("----------STEP 01 - Start Implicit: " + new Date() + "------------");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='automation-testing']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
			
		} catch (NoSuchElementException ex) {
			System.out.println("-------------STEP 01 - NHAY VAO CATCH ---------");
			System.out.println(ex.getMessage());
		}
		System.out.println("----------STEP 01 - End Implicit: " + new Date() + "------------");
		
		
		
		//Explicit (Not found - tham so la webelement)
		// Do có dùng driver.findElement nên sẽ dùng timeout của Implicit (5s)
		System.out.println("----------STEP 02 - Start Explicit (WebElement) :  " + new Date() + "------------");
		try {
			WebElement emailTextbox = explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='automation-testing']"))));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("-------------STEP 02 - NHAY VAO CATCH ---------");
			System.out.println(ex.getMessage());
			
		}
		System.out.println("----------STEP 02 - End Explicit (WebElement) : " + new Date() + "--------------");
		
		//Explicit (Not found - tham so la by)
		System.out.println("----------STEP 03 - Start Explicit (By) : " + new Date() + "------------");
		try {
			WebElement emailTextbox =  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='automation-testing']")));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("-------------STEP 03 - NHAY VAO CATCH ---------");
			System.out.println(ex.getMessage());
		}
		System.out.println("----------STEP 03 - End Explicit (By) : " + new Date() + "--------------");

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