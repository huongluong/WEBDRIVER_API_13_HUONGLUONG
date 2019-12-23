package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
    Alert alert;
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

	//@Test
	public void TC_02_ClickByJS() {
		driver.get("https://demo.nopcommerce.com/");
		javascript.executeScript("arguments[0].click()",driver.findElement(By.xpath("//a[text()='Books '] ")));
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Books']")).isDisplayed());
		
		
	}
	//@Test
	public void TC_03_Checkbox_ClickbyJS() throws InterruptedException{
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		String checkBoxInput= "//input[@id='eq5']";
		//Click chọn checkbox Dual-zone air conditioning
		clickByJS(checkBoxInput);
		//Kiểm tra check box được chọn
		Assert.assertTrue(isElementSelected(checkBoxInput));
		Thread.sleep(3000);
		//Click không chọn check box Dual-zone air conditioning
		clickByJS(checkBoxInput);
		//Kiểm tra check box không được chọn
		Assert.assertFalse(isElementSelected(checkBoxInput));
		Thread.sleep(3000);
	}
	//@Test
	public void TC_04_CheckAndUncheckToCheckbox() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		String newLetterCheckbox = "//input[@id='Newsletter']";
		checkToCheckbox(newLetterCheckbox);
		checkToCheckbox(newLetterCheckbox);
		checkToCheckbox(newLetterCheckbox);
		checkToCheckbox(newLetterCheckbox);
		Assert.assertTrue(isElementSelected(newLetterCheckbox));
		
		uncheckToCheckbox(newLetterCheckbox);
		uncheckToCheckbox(newLetterCheckbox);
		uncheckToCheckbox(newLetterCheckbox);
		uncheckToCheckbox(newLetterCheckbox);
		Assert.assertFalse(isElementSelected(newLetterCheckbox));
	}
	
	//@Test
	public void TC_05_HandleAlert() throws InterruptedException {
		String fullName = "Automation Testing";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Thread.sleep(2000);
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(),"I am a JS Alert");	
		//get message Text in alert
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked an alert successfully ']")).isDisplayed());
		
		//cancel alert
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Thread.sleep(2000);
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"I am a JS Confirm");
		alert.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked: Cancel']")).isDisplayed());
		
		//input to aler
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Thread.sleep(2000);
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"I am a JS prompt");
		alert.sendKeys(fullName);
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You entered: " + fullName +"']")).isDisplayed());
		
		
	}
	
	public void TC_06_authenticationAlert() {
		String userNameAndPass = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		url = "http://" + userNameAndPass + ":" + userNameAndPass + "@the-internet.herokuapp.com/basic_auth";
		driver.get(url);
	}
	@Test
	public void TC_07_authenticationAlert() {
		String userName = "admin";
		String passWord = "admin";
		driver.get("http://the-internet.herokuapp.com/");
		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println("Url = " + url);
		driver.get(byPassAuthenticationAlert(url, userName, passWord));
		driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed();
	}
	
	public String byPassAuthenticationAlert(String url, String userName, String passWord){
		System.out.println("Old url =" + url);
		//http://the-internet.herokuapp.com/basic_auth
		String[] parselink = url.split("//");
		// 0: http
		// 1: the-internet.herokuapp.com/basic_auth
		url = parselink[0] + "//" + userName + ":" + passWord + "@" + parselink[1];
		System.out.println("New url = " + url);
		return url;
	}
	public void clickByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javascript.executeScript("arguments[0].click()", element);
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
    
    public boolean isElementSelected(String locator) {
    	WebElement element = driver.findElement(By.xpath(locator));
    	if(element.isSelected()) {
    		return true;
    	} else {
    		return false;
    	}
    }
		
    public void checkToCheckbox(String locator) {
    	WebElement element = driver.findElement(By.xpath(locator));
    	if(!element.isSelected()) {
    		element.click();
    	}
    }
    
    public void uncheckToCheckbox(String locator) {
    	WebElement element = driver.findElement(By.xpath(locator));
    	if(element.isSelected()) {
    		element.click();
    	}
    }
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}