package webdriver_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_14_Wait_PartVI_Fluent {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
  //  WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//explicitWait = new WebDriverWait(driver, 5);
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//	driver.manage().window().maximize();
		
	}

	
	//@Test
	public void TC_01_Fluent_WebDriver() {
		driver.get("http://demo.guru99.com/");
		//Tổng thời gian cần check
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
		// Tần số mỗi 1s check 1 lần
		.pollingEvery(1, TimeUnit.SECONDS)
		//Nếu gặp exception là find ko thấy element sẽ bỏ qua
		.ignoring(NoSuchElementException.class);
		
		WebElement submitButton = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>(){
			@Test
			@Override
			public WebElement apply(WebDriver driver) {
				System.out.println(new Date());
				return driver.findElement(By.xpath("//input[@name='Login']"));
			}
			
		});
		submitButton.click();
	}

	@Test
	public void TC_02_Fluent_WebElement() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		
		fluentElement = new FluentWait<WebElement>(countdown);
		
		fluentElement.withTimeout(15, TimeUnit.SECONDS)
		 //tần số mỗi 1 giây check 1 lần
		.pollingEvery(100,TimeUnit.MILLISECONDS)
		//Nếu gặp exception là find ko thấy element sẽ bỏ qua
		.ignoring(NoSuchElementException.class);
		
		boolean status = (Boolean) fluentElement.until(new Function<WebElement, Boolean>(){
			@Override
			public Boolean apply(WebElement element) {
				//Kiểm tra điều kiện countdown  = 00
				boolean flag = element.getText().endsWith("01");
				System.out.println("Time = " + element.getText());
				//return giá trị cho function apply
				return flag;
			}
			
		});
		
		System.out.println("Status = " + status);
		Assert.assertTrue(status);
		
	}

		
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}