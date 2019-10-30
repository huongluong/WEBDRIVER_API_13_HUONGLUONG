package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;

	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		//Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//phóng to trình duyệt
		driver.manage().window().maximize();
		//mở ra trang web (AUT: Application under test)
		driver.get("http://demo.guru99.com/v4/");
	}

	
	@Test
	public void TC_01_ValidateCurrentUrl() {
		// Lấy ra Url của page hiện tại và gán nó vào biến loginPageUrl
		String loginPageUrl = driver.getCurrentUrl();
		
		// In ra console cho người dùng (Coder) thấy được kết quả chạy test
		System.out.println(loginPageUrl);
		
		// Các hàmd dể verify dữ liệu của TestNG (true/false/equals)
		Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_02_ValidatePageTitle() {
		//Lấy ra title của page hiện tại và gán vào biến loginPageTitle
		String loginPageTitle = driver.getTitle();
		//Verify dữ liệu của biên loginPageTitle này bằng với giá trị mong muốn
		Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		// Verify login form được hiển thị ở trang login
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}
	
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}