package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_08_Buton_Radio_Checkbox_Alert_1 {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
    Select select;
    JavascriptExecutor javascript;
    Action action;
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		javascript = (JavascriptExecutor) driver;

		
	}

	
	//@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Button
		elementEnabled("//button[@id='button-disabled']");
		
		//Checkbox
		elementEnabled("//input[@id='development']");
		elementSelected("//input[@id='development']");
		
		//radio button
		elementEnabled("//input[@id='under_18']");
		elementSelected("//input[@id='under_18']");
	}

	@Test
	public void TC_02_ClickByJS() {
		driver.get("https://demo.nopcommerce.com/");
		javascript.executeScript("arguments[0].click()",driver.findElement(By.xpath("//a[text()='Books '] ")));
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Books']")).isDisplayed());
		
		
	}
	
	public void TC_03_ClickByJS() {
		
	}
    public void elementEnabled(String locator) {
    	WebElement element = driver.findElement(By.xpath(locator));
    	if(element.isEnabled()) {
    		System.out.println("Element is enabled");
    	} else {
    		System.out.println("Element is disabled");
    	}
    }
    
    public void elementSelected(String locator) {
    	WebElement element = driver.findElement(By.xpath(locator));
    	if(element.isSelected()) {
    		System.out.println("Element is selected");
    	} else {
    		System.out.println("Element is deselected");
    	}
    }
		
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}