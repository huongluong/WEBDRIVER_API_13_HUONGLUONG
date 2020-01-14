package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_Part1_Element_Status {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
	WebDriverWait waitExplicit;
	
    boolean status;
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver,5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	//@Test
	public void TC_01_ElementDisplayedOrVisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//DDK1 - Element có hiển thị trên UI và có trong DOM
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));//Chờ cho element display/visible : PASS
		status = driver.findElement(By.xpath("//button[@id='SubmitLogin']")).isDisplayed(); //Kiểm tra Element display 
		System.out.println("Element có hiển thị trên UI + có trong DOM = " + status);
		
		//DDK2 - Element ko hiển thị trên UI nhưng vẫn có trong DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']"))); //Chờ cho element không hiển thị : PASS
		status = driver.findElement(By.xpath("//div[@id='create_account_error']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI + có trong DOM = " + status);
		
				
		//DDK3 - Element không hiển thị trên UI và không có trong DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']"))); //chờ cho element ko hiển thị : FAIL
		status = driver.findElement(By.xpath("//input[@id='id_order']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI + không có trong DOM = " + status);
	}

	@Test
	public void TC_02_ElementUndisplayedOrInvisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//DDK1 - Element có hiển thị trên UI và có trong DOM
		//waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));//Chờ cho element display/visible : FAIL
		
		//DDK2 - Element ko hiển thị trên UI nhưng vẫn có trong DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']"))); //Chờ cho element không hiển thị : PASS
		
		//DDK3 - Element không hiển thị trên UI và không có trong DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']"))); //chờ cho element ko hiển thị : PASS
	}
	@Test
	public void TC_03_ElementPresence() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//DDK1 - Element có hiển thị trên UI và có trong DOM
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));//Chờ cho element display/visible : PASS
		//DDK2 - Element ko hiển thị trên UI nhưng vẫn có trong DOM
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']"))); //Chờ cho element không hiển thị : PASS
		//DDK3 - Element không hiển thị trên UI và không có trong DOM 
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='id_order']")));//chờ cho element ko hiển thị : FAIL
	}
		
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}