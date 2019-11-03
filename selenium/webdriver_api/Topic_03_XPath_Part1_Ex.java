package webdriver_api;

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

	//Pre - condition 
	@BeforeClass(description = "Chạy 1 lần duy nhất cho tất cả các test bên dưới")
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
    @BeforeMethod(description = "Chạy trước cho mỗi test bên dưới")
    public void beforeMethod() {
    	driver.get("http://live.demoguru99.com");
    	System.out.println("Click vào My Account link ở dưới footer");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    }
	
	//@Test
	public void TC_01_LoginWithEmailAndPasswordEmpty() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String errorEmailMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(errorEmailMsg,"This is a required field.");
		
		String errorPassMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(errorPassMsg,"This is a required field.");
	}

	//@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12345@1235.1233");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String errorInvalidEmailMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(errorInvalidEmailMsg,"Please enter a valid email address. For example johndoe@domain.com.");
		
		String errorPassMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(errorPassMsg,"This is a required field.");
	}
	//@Test
	public void TC_03_LoginWithPasswordLessThan6Chars() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String errorInvalidPassMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(errorInvalidPassMsg,"Please enter 6 or more characters without leading or trailing spaces.");
		
		
	}
	//@Test
	public void TC_04_LoginWithPasswordInvalid() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345678");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String errorMsg = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(errorMsg,"Invalid login or password.");
		
	}

	@Test
	public void TC_05_LoginWithValidEmailAndPassword() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='hello']//strong[text()='Hello, Automation Testing!']")).isDisplayed());
				
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'Automation Testing')]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'automation_13@gmail.com')]")).isDisplayed());
		
		
	}	
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}