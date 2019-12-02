package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_I {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
	By emailTextboxBy = By.xpath("//input[@id='mail']");
	By age18RadioBy = By.xpath("//input[@id='under_18']");
	By educationTextAreaBy = By.xpath("//textarea[@id='edu']");
	By jobRole1By = By.xpath("//select[@id='job1']");
	By jobRole3By = By.xpath("//select[@id='job3']");
	By IntDevelopmentBy = By.xpath("//input[@id='development']");
	By Slider01By	= By.xpath("//input[@id='slider-1']");
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
		
		if (isElementDisplayed(emailTextboxBy))
		{
			sendKeyToElement(emailTextboxBy,"Automation Testing");
		}
		
		
		if (isElementDisplayed(age18RadioBy))
		{
			clickToElement(age18RadioBy);
		}
		
		
		if (isElementDisplayed(educationTextAreaBy))
		{
			sendKeyToElement(educationTextAreaBy,"Automation Testing");
		}
	}

	@Test
	public void TC_02_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnabled(emailTextboxBy));
		Assert.assertTrue(isElementEnabled(age18RadioBy));
		Assert.assertTrue(isElementEnabled(educationTextAreaBy));
		Assert.assertTrue(isElementEnabled(IntDevelopmentBy));
		Assert.assertTrue(isElementEnabled(jobRole1By));
		Assert.assertFalse(isElementEnabled(jobRole3By));
		Assert.assertTrue(isElementEnabled(Slider01By));
		
	}

	
	@Test
	public void TC_03_isSelected() {
		//chua click chon nen un-select
		driver.navigate().refresh();
		Assert.assertFalse(isElementSelected(age18RadioBy));
		Assert.assertFalse(isElementSelected(IntDevelopmentBy));
				
		//click de chon
		clickToElement(age18RadioBy);
		clickToElement(IntDevelopmentBy);
		
		//selected
		Assert.assertTrue(isElementSelected(age18RadioBy));
		Assert.assertTrue(isElementSelected(IntDevelopmentBy));
		
		//click de bo chon
		clickToElement(IntDevelopmentBy);
		
		Assert.assertTrue(isElementSelected(age18RadioBy));
		Assert.assertFalse(isElementSelected(IntDevelopmentBy));
	}
	
	public boolean isElementDisplayed(By  by)
	{
		WebElement element = driver.findElement(by);
		if(element.isDisplayed()){
			return true;
		}else {
			return false;
		}
	}
	
	public void sendKeyToElement(By by,String value)
	{
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}
	
	public void clickToElement(By by)
	{
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	public boolean isElementEnabled(By by)
	{
		WebElement element = driver.findElement(by);
		if(element.isEnabled()){
			System.out.println("Element [" + by + "] is enabled.");
			return true;
		}else{
			System.out.println("Element [" + by + "] is disabled.");
			return false;
	    }
	}
	
	public boolean isElementSelected(By by)
	{
		WebElement element = driver.findElement(by);
		if(element.isSelected()){
			return true;
		}else{
			return false;
	    }
	}
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}