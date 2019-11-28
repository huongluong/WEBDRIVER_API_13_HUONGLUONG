package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_I {
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
	public void TC_01_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		if (emailTextbox.isDisplayed())
		{
			emailTextbox.sendKeys("Automation Testing");
		}
		
		WebElement age18Radio = driver.findElement(By.xpath("//input[@id='under_18']"));
		if (age18Radio.isDisplayed())
		{
			age18Radio.click();
		}
		
		WebElement educationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if (educationTextArea.isDisplayed())
		{
			educationTextArea.sendKeys("Automation Testing");
		}
	}

	@Test
	public void TC_02_isEnabled() {
		driver.get("");
	}

	
	@Test
	public void TC_02_isSelected() {
		driver.get("");
	}
	
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}