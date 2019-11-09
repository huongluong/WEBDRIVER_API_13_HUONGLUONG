package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_03_XPath_Part1_Ex {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
    String WEBSITE_URL = "http://live.demoguru99.com";
    //Xpath constant
    String XPATH_FOOTER_MY_ACCOUNT = "//div[@class='footer']//a[@title='My Account']";
    String XPATH_EMAIL = "//input[@id='email']";
    String XPATH_PASS = "//input[@id='pass']";
    String XPATH_LOGIN_BUTTON = "//button[@id='send2']";
    String XPATH_EMPTY_EMAIL_MSG = "//div[@id='advice-required-entry-email']";
    String XPATH_EMPTY_PASS_MSG = "//div[@id='advice-required-entry-pass']";
    String XPATH_INVALID_EMAIL_MSG = "//div[@id='advice-validate-email-email']";
    String XPATH_PASS_LESSTHAN_6CHARS = "//div[@id='advice-validate-password-pass']";
    String XPATH_INVALID_PASS = "//li[@class='error-msg']//span";
 
    
    String ERROR_EMPTY_EMAIL_MSG = "This is a required field.";
    String ERROR_EMPTY_PASS_MSG = "This is a required field.";
    String ERROR_INVALID_EMAIL_MSG = "Please enter a valid email address. For example johndoe@domain.com.";
    String ERROR_PASS_LESSTHAN_6CHARS_MSG = "Please enter 6 or more characters without leading or trailing spaces.";
    String ERROR_INVALID_PASS_MSG = "Invalid login or password.";
    
    String INPUT_INVALID_EMAIL = "12345@1235.1233";
    String INPUT_EMPTY= "";
    String INPUT_VALID_EMAIL = "automation_" + randomNumber() + "@gmail.com";
    String INPUT_PASS_LESSTHAN_6CHARS = "123";
    String INPUT_INVALID_PASS = "12345678";
    String INPUT_VALID_PASS = "123123";
    String FIRST_NAME = "Selenium";
    String LAST_NAME = "Advance";
    
	//Pre - condition 
	@BeforeClass(description = "Chạy 1 lần duy nhất cho tất cả các test bên dưới")
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
    @BeforeMethod(description = "Chạy trước cho mỗi test bên dưới")
    public void beforeMethod() {
    	driver.get(WEBSITE_URL);
    	System.out.println("Click vào My Account link ở dưới footer");
		driver.findElement(By.xpath(XPATH_FOOTER_MY_ACCOUNT)).click();
    }
	
	//@Test
	public void TC_01_LoginWithEmailAndPasswordEmpty() {
		driver.findElement(By.xpath(XPATH_EMAIL)).sendKeys(INPUT_EMPTY);
		driver.findElement(By.xpath(XPATH_PASS)).sendKeys(INPUT_EMPTY);
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		
		String errorEmailMsg = driver.findElement(By.xpath(XPATH_EMPTY_EMAIL_MSG)).getText();
		Assert.assertEquals(errorEmailMsg,ERROR_EMPTY_EMAIL_MSG);
		
		String errorPassMsg = driver.findElement(By.xpath(XPATH_EMPTY_PASS_MSG)).getText();
		Assert.assertEquals(errorPassMsg,ERROR_EMPTY_PASS_MSG);
	}

	//@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.findElement(By.xpath(XPATH_EMAIL)).sendKeys(INPUT_INVALID_EMAIL);
		driver.findElement(By.xpath(XPATH_PASS)).sendKeys(INPUT_EMPTY);
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		
		String errorInvalidEmailMsg = driver.findElement(By.xpath(XPATH_INVALID_EMAIL_MSG)).getText();
		Assert.assertEquals(errorInvalidEmailMsg,ERROR_INVALID_EMAIL_MSG);
		
		String errorPassMsg = driver.findElement(By.xpath(XPATH_EMPTY_PASS_MSG)).getText();
		Assert.assertEquals(errorPassMsg,ERROR_EMPTY_PASS_MSG);
	}

	//@Test
	public void TC_03_LoginWithPasswordLessThan6Chars() {
		driver.findElement(By.xpath(XPATH_EMAIL)).sendKeys(INPUT_VALID_EMAIL);
		driver.findElement(By.xpath(XPATH_PASS)).sendKeys(INPUT_PASS_LESSTHAN_6CHARS);
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		
		String errorInvalidPassMsg = driver.findElement(By.xpath(XPATH_PASS_LESSTHAN_6CHARS)).getText();
		Assert.assertEquals(errorInvalidPassMsg,ERROR_PASS_LESSTHAN_6CHARS_MSG);
		
		
	}
	//@Test
	public void TC_04_LoginWithPasswordInvalid() {
		driver.findElement(By.xpath(XPATH_EMAIL)).sendKeys(INPUT_VALID_EMAIL);
		driver.findElement(By.xpath(XPATH_PASS)).sendKeys(INPUT_INVALID_PASS);
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		
		String errorMsg = driver.findElement(By.xpath(XPATH_INVALID_PASS)).getText();
		Assert.assertEquals(errorMsg,ERROR_INVALID_PASS_MSG);
		
	}

	@Test
	public void TC_05_CreateNewAccount()
	{
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(FIRST_NAME);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(LAST_NAME);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(INPUT_VALID_EMAIL);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(INPUT_VALID_PASS);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(INPUT_VALID_PASS);
		driver.findElement(By.xpath("//button[@title='Register']")).click();		
		
	}
	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		driver.findElement(By.xpath(XPATH_EMAIL)).sendKeys(INPUT_VALID_EMAIL);
		driver.findElement(By.xpath(XPATH_PASS)).sendKeys(INPUT_VALID_PASS);
		driver.findElement(By.xpath(XPATH_LOGIN_BUTTON)).click();
		
		//text cố định thì nên dùng hàm getText và assertEquals
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(),"MY DASHBOARD");
		
		// text ko cố định thì dùng hàm isDisplayed
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='hello']//strong[text()='Hello, " + FIRST_NAME + " " + LAST_NAME + "!']")).isDisplayed());
				
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'"+ FIRST_NAME + " " + LAST_NAME + "')]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'" + INPUT_VALID_EMAIL + "')]")).isDisplayed());
		
		
	}	
	public int randomNumber(){
		Random ran = new Random();
		return ran.nextInt(100000);
		
	}
	
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}