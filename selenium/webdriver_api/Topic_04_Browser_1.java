package webdriver_api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;


public class Topic_04_Browser_1 {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;

	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test - Do not run this case, it use to reference.
	public void TC_00_ExampleBrowserAPI() throws Exception
	{
		
			// Mở cái AUT (Application Under Test) -> required: http:// hoặc https://
			driver.get("http://live.demoguru99.com/index.php"); // (**)

			// Đóng browser -> Handle Windows/ Tabs
			// driver.close();

			// Đóng browser
			// driver.quit(); // (**)

			// Trả về Url của page hiện tại
			String homePageUrl = driver.getCurrentUrl(); // (*)
			System.out.println(homePageUrl);

			Assert.assertEquals(homePageUrl, "http://live.demoguru99.com/index.php");

			// Trả về title của page hiện tại
			Assert.assertEquals(driver.getTitle(), "Home page");  // (*)

			// Trả về source code của page hiện tại
			Assert.assertTrue(driver.getPageSource().contains("2015 Magento Demo Store. All Rights Reserved."));

			// Trả về cái Windows GUID (Handle Windows) của windows/ tab hiện tại
			String homePageTabID = driver.getWindowHandle(); // (**)
			System.out.println("Windows ID = " + homePageTabID);

			// Trả về Windows GUID của all tab/ windows đang có
			// Set<String> allWindowsID = driver.getWindowHandles(); // (**)
			//
			// for (int i = 0; i <= allWindowsID.size(); i++) {
			// System.out.println(i);
			// }
			//
			// for (String id : allWindowsID) {
			// System.out.println(id);
			// }
			
			// Khai báo 1 biến element là email textbox
			// WebElement emailTextbox = driver.findElement(By.xpath("")); // (**)

			// Khai báo 1 biến để lấy ra tất cả các link trên page hiện tại
			// List <WebElement> links = driver.findElements(By.xpath("a")); // (**)
			
			// Get ra các log của tab Network
			System.out.println(driver.manage().logs().get(LogType.PERFORMANCE));
			
			// Chờ cho element được stable để thao tác lên nó trong khoảng time bao nhiêu -> WebDriverWait
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // (**)
			
			// Chờ cho 1 page được load thành công trong khoảng time bao nhiêu
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS); // (*)
			
			// Dùng cho Javascript Executor (Asynch) -> Sync
			// driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
			
			// F11
			// driver.manage().window().fullscreen();
			
			// Get ra vị trí
			System.out.println(driver.manage().window().getPosition());
			
			// Set vị trí
			Point point = new Point(100, 100);
			driver.manage().window().setPosition(point);
			
			// Get ra chiều rộng/ cao (size)
			System.out.println(driver.manage().window().getSize());
			
			Dimension dimension = new Dimension(1920, 1080);
			driver.manage().window().setSize(dimension);
			
			// Giống User sử dụng
			driver.manage().window().maximize(); // (**)
			
			driver.navigate().back();
			driver.navigate().forward();
			driver.navigate().refresh();
			
			// Tracking history/ gps/ location
			driver.navigate().to("http://live.demoguru99.com/index.php/customer/account/");
			
			// Alert/ Windows/ Frame (Iframe)
			
			// Alert // (**)
			driver.switchTo().alert().accept();
			driver.switchTo().alert().dismiss();
			driver.switchTo().alert().getText();
			driver.switchTo().alert().sendKeys("");
			
			// Windows // (**)
			driver.switchTo().window("Windows GUID");
			
			// Iframe/ frame // (**)
			driver.switchTo().frame(driver.findElement(By.xpath("")));
		
	}
	
	@Test
	public void TC_01_CheckUrl() {
		System.out.println("Step 1 - Open Url");
		driver.get("http://live.demoguru99.com");
		
		System.out.println("Step 2 - Click on 'My Account' link.");
		driver.findElement(By.xpath("//div[@class = 'footer']//a[text()='My Account']")).click();
		
		
		System.out.println("Step 3 - Verify Login Page Url.");
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/login/", loginPageUrl);
		
		
		System.out.println("Step 4 - Click 'CREATE AN ACCOUNT' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 5 - Verify Register Page Url");
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/create/", registerPageUrl);
		
		
		
	}

	@Test
	public void TC_02_CheckTitle() {
		System.out.println("Step 1 - Open Url");
		driver.get("http://live.demoguru99.com");
		
		System.out.println("Step 2 - Click on 'My Account' link.");
		driver.findElement(By.xpath("//div[@class = 'footer']//a[text()='My Account']")).click();
		
		
		System.out.println("Step 3 - Verify Login Page Title.");
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals("Customer Login", loginPageTitle);
		
		
		System.out.println("Step 4 - Click 'CREATE AN ACCOUNT' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 5 - Verify Register Page Title");
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals("Create New Customer Account", registerPageTitle);
		
	}

	@Test
	public void TC_03_CheckNavigate() {
		System.out.println("Step 1 - Open Url");
		driver.get("http://live.demoguru99.com");
		
		System.out.println("Step 2 - Click on 'My Account' link.");
		driver.findElement(By.xpath("//div[@class = 'footer']//a[text()='My Account']")).click();
		
		System.out.println("Step 3 - Click 'CREATE AN ACCOUNT' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 4 - Verify Register Page Url");
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/create/", registerPageUrl);
		
		System.out.println("Step 5 - Back to Login page");
		driver.navigate().back();

		System.out.println("Step 6 - Verify Login page Url");
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/login/", loginPageUrl);
		
		System.out.println("Step 7 - Forward to Register page");
		driver.navigate().forward();
		
		System.out.println("Step 8 - Verify Register Page Title");
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals("Create New Customer Account", registerPageTitle);
	}

	@Test
	public void TC_04_CheckPageSource() {
		System.out.println("Step 1 - Open Url");
		driver.get("http://live.demoguru99.com");
		
		System.out.println("Step 2 - Click on 'My Account' link.");
		driver.findElement(By.xpath("//div[@class = 'footer']//a[text()='My Account']")).click();
		
		System.out.println("Step 3 - Verfiy Page Source Login page contains 'Login or Create an Account'");
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		                                                   		
		System.out.println("Step 4 - Click 'CREATE AN ACCOUNT' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 5 - Verfiy Page Source Register page contains 'Create an Account'");
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
	}
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}