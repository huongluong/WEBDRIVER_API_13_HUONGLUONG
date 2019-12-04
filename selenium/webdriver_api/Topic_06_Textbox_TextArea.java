package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
	String customerID;
    String username = "mngr233468";
    String password = "tYqAhaq";
    
    //Input in New Customer form
    String customerName = "Selenium Online";
    String gender = "male";
    String dateOfBirth = "2000-10-01";
    String address="123 Address";
    String city = "Ho Chi Minh";
    String state = "Thu Duc";
    String PIN = "123456";
    String mobileNumber = "0123456789";
    String email = "selenium" + randomNumber()  + "@gmail.com";
    
    //Input in Edit Customer form
    String editAddress="234 Edit Address";
    String editcity = "Edit Ho Chi Minh";
    String editstate = "Edit Thu Duc";
    String editPIN = "654321";
    String editMobileNumber = "9876543210";
    String editemail = "selenium" + randomNumber()  + "@gmail.com";

    //Locator for new and edit Customer
    By nameTextboxBy = By.name("name");
    By genderRadioBy = By.xpath("//input[@value ='m']");
    By genderTextboxBy = By.name("gender");
    By dateOfBirthTextboxBy = By.name("dob");
    By addressTextAreaBy = By.name("addr");
    By cityTextboxBy = By.name("city");
    By stateTextboxBy = By.name("state");
    By pinTextboxBy = By.name("pinno");
    By mobileNumberTextboxBy = By.name("telephoneno");
    By emailTextboxBy = By.name("emailid");
    By passwordTextboxBy = By.name("password");
    By submitBtnBy = By.name("sub");
    
    
    
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		String homePageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homePageWelcomeMsg,"Welcome To Manager's Page of Guru99 Bank");
		
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + username +"']")).isDisplayed());
		
	}

	
	@Test
	public void TC_01_NewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(nameTextboxBy).sendKeys(customerName);
		driver.findElement(genderRadioBy).click();
		driver.findElement(dateOfBirthTextboxBy).sendKeys(dateOfBirth);
		driver.findElement(addressTextAreaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(PIN);
		driver.findElement(mobileNumberTextboxBy).sendKeys(mobileNumber);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(submitBtnBy).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		// Check output data = input data
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(PIN, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(mobileNumber, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
		
		//Get Customer ID
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_EditCustomer() {
		
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//Verify Name/Gender/Date of birth is disabled
		Assert.assertFalse(driver.findElement(nameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(dateOfBirthTextboxBy).isEnabled());
		
		//Verify output = input
		Assert.assertEquals(customerName, driver.findElement(nameTextboxBy).getAttribute("value"));
		Assert.assertEquals(gender, driver.findElement(genderTextboxBy).getAttribute("value"));
		Assert.assertEquals(dateOfBirth, driver.findElement(dateOfBirthTextboxBy).getAttribute("value"));
		Assert.assertEquals(address, driver.findElement(addressTextAreaBy).getText());
		Assert.assertEquals(city, driver.findElement(cityTextboxBy).getAttribute("value"));
		Assert.assertEquals(state, driver.findElement(stateTextboxBy).getAttribute("value"));
		Assert.assertEquals(PIN, driver.findElement(pinTextboxBy).getAttribute("value"));
		Assert.assertEquals(mobileNumber, driver.findElement(mobileNumberTextboxBy).getAttribute("value"));
		Assert.assertEquals(email, driver.findElement(emailTextboxBy).getAttribute("value"));
		
		//Edit some field
		driver.findElement(addressTextAreaBy).clear();
		driver.findElement(addressTextAreaBy).sendKeys(editAddress);
		
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(cityTextboxBy).sendKeys(editcity);
		
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(stateTextboxBy).sendKeys(editstate);
		
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(pinTextboxBy).sendKeys(editPIN);
		
		driver.findElement(mobileNumberTextboxBy).clear();
		driver.findElement(mobileNumberTextboxBy).sendKeys(editMobileNumber);
	
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(editemail);

		driver.findElement(submitBtnBy).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());
		
		Assert.assertEquals(customerID, driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText());
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(editAddress, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(editcity, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(editstate, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(editPIN, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(editMobileNumber, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(editemail, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
		
		
		
		
		
		
		
	}

	public int randomNumber()
	{
		Random rand = new Random();
		return rand.nextInt(100000);
		
	}	
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}