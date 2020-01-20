package webdriver_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_PartIV_Implicit_Explicit {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
	WebDriverWait waitExplicit;
	
    By startButtonBy = By.xpath("//button[text()='Start']");
    By loadingImageBy = By.xpath("//div[@id='loading']/img");
    By helloworldTextBy = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");
    
	
    //Pre - condition 
    
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//Rõ ràng
		waitExplicit = new WebDriverWait(driver,10);
		
		//Ngầm định không chờ cho element nào có trạng thái cụ thể -> đi tìm element
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	@Test
	public void TC_01_Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//Check element được hiển thị (visible)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		
		//Click vào Start button
		System.out.println("Start click - " + getCurrentTime());
		startButton.click();
		System.out.println("End click - " + getCurrentTime());
		
		//Loading icon hiển thị và mất tới 5s mới biến mất
		
		//Set lại 10s cho implicit wait (3s ko còn ý nghĩa) - bị ghi đè
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Check cho helloworld được hiển thị
		System.out.println("Start helloworld -" + getCurrentTime());
		WebElement helloworldText = driver.findElement(helloworldTextBy);
		System.out.println("End helloworld -" + getCurrentTime());
		
		System.out.println("Start display -" + getCurrentTime());
		Assert.assertTrue(helloworldText.isDisplayed());
		System.out.println("End displayed -" + getCurrentTime());
		
		
		
		
	}
    public String getCurrentTime() {
    	Date date = new Date();
    	return date.toString();
    }
	@Test
	public void TC_02_Implicit_Override() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//Check element được hiển thị (visible)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		
		//Click vào Start button
		System.out.println("Start click - " + getCurrentTime());
		startButton.click();
		System.out.println("End click - " + getCurrentTime());
		
		//Loading icon hiển thị và mất tới 5s mới biến mất
	
		//Check cho helloworld được hiển thị
		System.out.println("Start helloworld -" + getCurrentTime());
		WebElement helloworldText = driver.findElement(helloworldTextBy);
		System.out.println("End helloworld -" + getCurrentTime());
		
		System.out.println("Start display -" + getCurrentTime());
		Assert.assertTrue(helloworldText.isDisplayed());
		System.out.println("End displayed -" + getCurrentTime());
	}

	public void TC_02_Explicit_Visible() {
		
	}
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}