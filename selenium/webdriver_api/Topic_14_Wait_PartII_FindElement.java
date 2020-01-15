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
import java.util.List;
public class Topic_14_Wait_PartII_FindElement {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
    List <WebElement> elements;
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	//@Test
	public void TC_01_FindElement() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		//Case 1 : Không tìm thấy Element nào hết
		//driver.findElement(By.xpath("//input[@id=id_order]")).sendKeys("123456");
		//Trước khi đánh fail luôn tìm element theo chu kì 0.5s tìm một lần cho đên hết timeout của implicit
		//Kết quả: Fail and throw NoSuchElement exception
		
		//Case 2 : Tìm thấy duy nhất 1 element
		//case 1 fail thì case này ko chạy nữa
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("email@gmail.com");
		
		//Case 3 : Tìm thấy nhiều hơn 1 element (node) => luôn thao tác element tìm thấy đầu tiên
		//case 1 fail thì case này ko chạy nữa
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	

	@Test
	public void TC_02_FindElements() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//Case 1 : Không tìm thấy Element nào hết
		//Tìm element theo chu kì 0.5s tìm một lần cho đên hết timeout của implicit
		//Kết quả: Pass and trả về empty list
		elements = driver.findElements(By.xpath("//input[@id=id_order]"));
	    System.out.println("Size of list = " + elements.size());
	    Assert.assertTrue(elements.isEmpty());
	    Assert.assertEquals(elements.size(), 0);
	    //Trước khi đánh fail luôn tìm element theo chu kì 0.5s tìm một lần cho đên hết timeout của implicit
		//Kết quả: Pass and trả về empty list
		
		//Case 2 : Tìm thấy duy nhất 1 element
	    elements = driver.findElements(By.xpath("//input[@id='email']"));
	    System.out.println("Size of list = " + elements.size());
	    Assert.assertFalse(elements.isEmpty());
	    Assert.assertEquals(elements.size(), 1);
	    elements.get(0).sendKeys("email@gmail.com");
	    
		//Case 3 : Tìm thấy nhiều hơn 1 element (node)
	    elements = driver.findElements(By.xpath("//button[@type='submit']"));
	    System.out.println("Size of list = " + elements.size());
	    Assert.assertFalse(elements.isEmpty());
	    Assert.assertEquals(elements.size(), 4);
	    
	}

		
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}