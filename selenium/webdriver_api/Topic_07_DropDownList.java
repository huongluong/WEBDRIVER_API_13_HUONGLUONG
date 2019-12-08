package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_DropDownList {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
	Select dropdown1;
	Select dropdown2;
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	//@Test
	public void TC_01_DropDownList_1() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		dropdown1 = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
        Assert.assertFalse(dropdown1.isMultiple());
        dropdown1.selectByVisibleText("Mobile Testing");
        Thread.sleep(2000);
        Assert.assertEquals(dropdown1.getFirstSelectedOption().getText(),"Mobile Testing");
        dropdown1.selectByValue("manual");
        Thread.sleep(2000);
        Assert.assertEquals(dropdown1.getFirstSelectedOption().getText(),"Manual Testing");
        dropdown1.selectByIndex(9);
        Thread.sleep(2000);
        Assert.assertEquals(dropdown1.getFirstSelectedOption().getText(),"Functional UI Testing");
        
        //Kiểm tra list có đủ 10 giá trị
        Assert.assertEquals(dropdown1.getOptions().size(),10);
       
        dropdown2 = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
        Assert.assertTrue(dropdown2.isMultiple());
        dropdown2.selectByVisibleText("Automation");
        Thread.sleep(2000);
        dropdown2.selectByValue("mobile");
        Thread.sleep(2000);
        dropdown2.selectByIndex(4);
        Thread.sleep(2000);
        List <WebElement> optionSelected = dropdown2.getAllSelectedOptions();
        List <String> arraySelected = new ArrayList<String>();
        for(WebElement select:optionSelected) {
        	System.out.println(select.getText());
        	arraySelected.add(select.getText());
        	
        }
        Assert.assertTrue(arraySelected.contains("Automation"));
        Assert.assertTrue(arraySelected.contains("Mobile"));
        Assert.assertTrue(arraySelected.contains("Desktop"));
        
        dropdown2.deselectAll();
        Thread.sleep(2000);
        List <WebElement> optionUnSelected = dropdown2.getAllSelectedOptions();
        Assert.assertEquals(optionUnSelected.size(), 0);
	}
	
    	
	@Test
	public void TC_02_DropdownList_2() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("FC");
		Select dayDropDown; 
		Select monthDropDown;
		Select yearDropDown;
		dayDropDown = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		dayDropDown.selectByVisibleText("1");
		Assert.assertEquals(dayDropDown.getOptions().size(),32);
		
		monthDropDown = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		monthDropDown.selectByVisibleText("May");
		Assert.assertEquals(monthDropDown.getOptions().size(),13);
		
		yearDropDown = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		yearDropDown.selectByVisibleText("1980");
		Assert.assertEquals(yearDropDown.getOptions().size(),112);
		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("abc" + randomNumber() + "@gmail.com");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123123");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123123");
		
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='header-links']//a[text()='My account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed());
		
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