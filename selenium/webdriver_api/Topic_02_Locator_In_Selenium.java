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

public class Topic_02_Locator_In_Selenium {
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
	public void TC_01_Locator() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		//<input 
		//type="email" 
		//title="Email Address" 
		//class="input-text required-entry validate-email"
		//id="email"
		//value=""
		//name="login[username]"
		//spellcheck="false"
		//autocorrect="off"
		//autocapitalize="off">
		
		// ID | CLASSNAME | NAME | LINKTEXT | PARTIAL LINKTEXT | CSS SELECTOR | XPATH
		// Thao tác vs lại field email address
		//ID
		driver.findElement(By.id("email")).sendKeys("id@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		//NAME
		driver.findElement(By.name("send")).click();
				
		//CLASS (Newsletter)
		driver.findElement(By.className("validate-email")).clear();
		driver.findElement(By.className("validate-email")).sendKeys("classname@gmail.com");
		
		//TAGNAME (Tìm xem có bao nhiêu đường link và in ra giá trị
		//Đếm (Count) có bao nhiêu element ở trên page?
		List <WebElement> links = driver.findElements(By.tagName("a"));
		int linkNumber = links.size();
		System.out.println("Tong so link = " + linkNumber);
		for(WebElement link: links) {
			System.out.println("Value =" + link.getAttribute("href"));
		}
		
		//LINK TEXT (Link)
		driver.findElement(By.linkText("ORDERS AND RETURNS")).click();
		
		// PARTIAL LINK TEXT (Link)
		driver.findElement(By.partialLinkText("ORDERS AND ")).click();
		
			
		// CSS
		driver.findElement(By.cssSelector("#oar_order_id")).sendKeys("123456");
		driver.findElement(By.cssSelector(".input-text.required-entry")).sendKeys("987654");
		driver.findElement(By.cssSelector("input[name='oar_email']")).sendKeys("css_name@gmail.com");
		
		System.out.println("The a dung css = " + driver.findElements(By.cssSelector("a")).size());
		driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/sales/guest/form/']"));
		
		// XPATH
		driver.findElement(By.xpath("//input[@id='oar_order_id']")).sendKeys("xpath_id");
		driver.findElement(By.xpath("//input[@class='input-text required-entry']")).sendKeys("xpath_class");
		driver.findElement(By.xpath("//input[@name='oar_email']")).sendKeys("xpath_name@gmail.com");
		System.out.println("The a dung xpath = " + driver.findElements(By.xpath("//a")).size());
		driver.findElement(By.xpath("//a[text()='Advanced Search']"));
		
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