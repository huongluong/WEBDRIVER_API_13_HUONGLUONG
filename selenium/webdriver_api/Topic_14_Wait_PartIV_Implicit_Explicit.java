package webdriver_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	//@Test
	public void TC_01_Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("TC01");
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
	//@Test
	public void TC_02_Implicit_Override() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("TC02");
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
    //@Test
	public void TC_03_Explicit_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("TC03");
		//Click Start button
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();
		
		//Loading icon hiển thị và biến mất sau 5s
		
		//Wait cho helloWorld được visible trước khi check displayed
		System.out.println("Start wait visible -" + getCurrentTime());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloworldTextBy));
		System.out.println("End wait visible -" + getCurrentTime() );
		
		System.out.println("Start displayed -" + getCurrentTime());
		Assert.assertTrue(driver.findElement(helloworldTextBy).isDisplayed());
		System.out.println("End displayed - " + getCurrentTime());
		
	}
    //@Test
   	public void TC_04_Explicit_InVisible() {
   		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
   		System.out.println("TC04");
   		//Click Start button
   		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
   		driver.findElement(startButtonBy).click();
   		
   		//Loading icon hiển thị và biến mất sau 5s
   		System.out.println("Start wait invisible -" + getCurrentTime());
   		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImageBy));
   		System.out.println("End wait invisible -" + getCurrentTime());
   		
   		//Wait cho helloWorld được visible trước khi check displayed
   		System.out.println("Start wait visible -" + getCurrentTime());
   		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldTextBy));
   		System.out.println("End wait visible -" + getCurrentTime() );
   		
   		System.out.println("Start displayed -" + getCurrentTime());
   		Assert.assertTrue(driver.findElement(helloworldTextBy).isDisplayed());
   		System.out.println("End displayed - " + getCurrentTime());
   		
   	}
    //@Test
	public void TC_05_Date_Implicit() {
   		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
   		
   		//In ra ngày được chọn: No Selected Dates to display.
   		WebElement dateSelectedText = driver.findElement((By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")));
   		
   		System.out.println("Date selected =" + dateSelectedText.getText());
   		Assert.assertEquals(dateSelectedText.getText(), "No Selected Dates to display.");
   		
   		
   		driver.findElement(By.xpath("//a[text()='7']")).click();
   		
   		//Check current dat = selected
   		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='7']")).isDisplayed());
   		
   		System.out.println("Date selected = " + dateSelectedText.getText());
   		Assert.assertEquals(dateSelectedText.getText(),"Tuesday, January 07, 2020");
   		
   		
   		
   	}
    
    @Test
   	public void TC_06_Date_Explicit() {
      		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
      		
      		//In ra ngày được chọn: No Selected Dates to display.
      		WebElement dateSelectedText = driver.findElement((By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")));
      	 	System.out.println("Date selected =" + dateSelectedText.getText());
      		Assert.assertEquals(dateSelectedText.getText(), "No Selected Dates to display.");
      		
      		//Click vào current day
      		driver.findElement(By.xpath("//a[text()='8']")).click();
      		
      		//Chờ cho loading icon biến mất
      		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style,'position: absolute;')]//div[@class='raDiv']")));
      
      		//Check current dat = selected
      		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='8']")).isDisplayed());
      		
      		//find lại lần nữa.
      		dateSelectedText = driver.findElement((By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")));
      		System.out.println("Date selected = " + dateSelectedText.getText());
      		Assert.assertEquals(dateSelectedText.getText(),"Wednesday, January 08, 2020");
      		
      		
      		
      	}
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}