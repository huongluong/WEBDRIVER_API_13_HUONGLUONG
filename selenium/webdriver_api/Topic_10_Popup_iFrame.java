package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Popup_iFrame {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;

	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
	}

	
	@Test
	public void TC_01_() throws InterruptedException {
		driver.get("https://kyna.vn/");
		Thread.sleep(3000);
		//Case 1- Co popup xuất hiện
		//Case 2- Ko có popup xuất hiện
		List <WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		System.out.println("Number of popup = " + fancyPopup.size());
		
		if(fancyPopup.size()>0)
		{
		       System.out.println("Step 3 - Check popup is displayed. Close popup");
		       Assert.assertTrue(fancyPopup.get(0).isDisplayed());
		       driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		//boolean fancyPopup = driver.findElement(By.xpath("//div[@class='fancybox-inner']")).isDisplayed();
		//System.out.println("Fancy popup display = " + fancyPopup);
		//Assert.assertTrue(fancyPopup);
		//driver.findElement(By.cssSelector(".fancybox-close")).click();
		
		
		//Step 4
		//Switch vào Iframe trước thì mới tương tác với các element trong Iframe đó
		//Index
		//driver.switchTo().frame(1);
		
		// Name or Id
		//driver.switchTo().frame("");
		
		// Web Element
		System.out.println("Step 4 - Switch vào iframe facebook");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
		
		boolean facebookIframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
		System.out.println("Facebook iframe displayed = " + facebookIframe);
		Assert.assertTrue(facebookIframe);
		
		String facebookLikes = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div//following-sibling::div")).getText();
		System.out.println("Facebook like number = " + facebookLikes);
		
		Assert.assertEquals(facebookLikes, "170K likes");
		
		//Switch về Main Page lại
		driver.switchTo().defaultContent();
		
		driver.findElement(By.className("button-login")).click();
		driver.findElement(By.id("user-login")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.id("user-password")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.id("btn-submit-login")).click();
		
		WebElement userLogin = driver.findElement(By.xpath("//li[@class='account dropdown wrap']//span[@class='user']"));
		
		Assert.assertTrue(userLogin.isDisplayed());
		Assert.assertEquals(userLogin.getText(),"Automation FC");
		
	}

	@Test
	public void TC_02_() {
		driver.get("");
	}

		
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}