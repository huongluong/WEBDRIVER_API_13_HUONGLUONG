package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Window_Tab {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//use Chrome
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\libraries\\chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--lang=vi");
		//driver = new ChromeDriver(options);
	}

	
	//@Test
	public void TC_01_Windows() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[contains(@href,'google')]")).click();
		String parentID = driver.getWindowHandle();
		System.out.println("ParentID = " + parentID);
		switchToWindowByID(parentID);
		
		Assert.assertEquals(driver.getTitle(),"Google");	
		Thread.sleep(2000);
		
		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//a[contains(@href,'facebook')]")).click();
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(),"Facebook - Đăng nhập hoặc đăng ký");	
		Thread.sleep(2000);
		
		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//a[contains(@href,'tiki')]")).click();
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(),"Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");	
		Thread.sleep(2000);
		
		closeAllWindowWithoutParent(parentID);
	}

	//@Test
	public void TC_02_Windows() throws InterruptedException {
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
		String parentID = driver.getWindowHandle();
		
		//1. CLick vào facebook và kiểm tra định danh bằng url
		System.out.println("1. CLick vào facebook và kiểm tra định danh bằng url");
		driver.findElement(By.xpath("//*[@id='k-footer']//img[@alt='facebook']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
		driver.switchTo().window(parentID);
		
		//2. Click vào youtube và kiểm trả định danh bằng url
		System.out.println("2. Click vào youtube và kiểm trả định danh bằng url");
		driver.findElement(By.xpath("//*[@id='k-footer']//img[@alt='youtube']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/user/kynavn");
		driver.switchTo().window(parentID);
		
		//3. Click vào zalo và kiểm tra định dang bằng url
		System.out.println("3. Click vào zalo và kiểm tra định dang bằng url");
		driver.findElement(By.xpath("//*[@id='k-footer']//img[@alt='zalo']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn");
		Assert.assertEquals(driver.getCurrentUrl(),"https://zalo.me/1985686830006307471");
		driver.switchTo().window(parentID);
		
		//4. Click vào apple app icon và kiểm tra định dang bằng label "App Store" hiển thị
		System.out.println("4. Click vào apple app icon và kiểm tra định dang bằng label \"App Store\" hiển thị");
		driver.findElement(By.xpath("//*[@id='k-footer']//img[@alt='apple-app-icon']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("‎KYNA on the App Store");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='App Store']")).isDisplayed());
		driver.switchTo().window(parentID);
		
		//5. Click vào android app icon và kiểm tra định dang bằng label "" hiển thị
		System.out.println("5. Click vào android app icon và kiểm tra định dang bằng label \"\" hiển thị");
		driver.findElement(By.xpath("//*[@id='k-footer']//img[@alt='android-app-icon']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("KYNA - Học online cùng chuyên gia - Apps on Google Play");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='KYNA - Học online cùng chuyên gia']")).isDisplayed());
		driver.switchTo().window(parentID);
		
		//6 Click vào iframe facebook
		System.out.println("6. Click vào iframe facebook");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
		
		driver.findElement(By.xpath("//a[text()='Kyna.vn']")).click();
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
		driver.switchTo().window(parentID);
		
		//7 Click vào  sản phẩm Kyna group kid
		System.out.println("7 Click vào  sản phẩm Kyna group kid");
		driver.findElement(By.xpath("//div[@id='kyna-group']//img[@alt='kynaforkids.vn']")).click();
		switchToWindowByTitle("Kynaforkids.vn trường học trực tuyến cho trẻ");
		Assert.assertEquals(driver.getCurrentUrl(),"https://kynaforkids.vn/");
		driver.switchTo().window(parentID);
		
		//8 Click vào  sản phẩm Kyna group biz
		System.out.println("8 Click vào  sản phẩm Kyna group biz");
		driver.findElement(By.xpath("//div[@id='kyna-group']//img[@alt='kynabiz.vn']")).click();
		switchToWindowByTitle("Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn");
		Assert.assertEquals(driver.getCurrentUrl(),"https://kynabiz.vn/");
		driver.switchTo().window(parentID);
		
		closeAllWindowWithoutParent(parentID);
		
	}
	@Test
	public void TC_03_Windows(){
		driver.get("http://live.guru99.com/index.php/");
		driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Mobile']")).click();
		
		//Add sản phẩm Sony Xperia to compare
		driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		//Add sản phẩm Samsung Galaxy to compare
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		//Click vào button Compare
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		String parentID = driver.getWindowHandle();
		
		switchToWindowByID(parentID);
		Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
		
		driver.close();
		closeAllWindowWithoutParent(parentID);
		driver.switchTo().window(parentID);
		
		//Click vào button Clear all
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"Are you sure you would like to remove all products from your comparison?");	
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
	}
    public void switchToWindowByID(String parentID) {
    	//Lấy ra tất cả ID 
    	Set <String> allWindows =driver.getWindowHandles();
    	
    	//Dùng vòng lặp duyệt qua từng ID
    	for(String termID : allWindows) {
    	
    		//Kiểm tra cái ID nào khác với parent ID thì switch qua
    		if(!termID.equals(parentID)){
    		
    			//Swith cho that ID
    			driver.switchTo().window(termID);
    			break;
    		}
        }
    }
    public void switchToWindowByTitle(String expectedTitle){
    	//Lấy ra tất cả ID
    	Set <String> allWindows =driver.getWindowHandles();
    	//System.out.println("Có tất cả : " + allWindows.size());
    	
    	//Dùng vòng lặp duyệt qua từng ID
    	for(String termID : allWindows) {
    		//switch vào từng ID trước
    		//System.out.println("ID =" + termID);
    		driver.switchTo().window(termID);
    		
    		//get ra title đang có
    		String currentTitle = driver.getTitle();
    		//System.out.println("Title " + currentTitle);
    		
    		//Title nào bằng với title expected thì break khỏi vòng lặp
    		if(currentTitle.equals(expectedTitle)) {
    			//Thoát khỏi vòng lặp - Không duyệt những cái tiếp theo
    			break;
    		}
    			
    	}
    }
    public void closeAllWindowWithoutParent(String parentID) {
    	//Lấy ra tất cả ID
    	Set <String> allWindows =driver.getWindowHandles();
    	    	
    	//Dùng vòng lặp duyệt qua từng ID
    	for(String termID : allWindows) {
    		if(!termID.equals(parentID)) {
    			driver.switchTo().window(termID);
    			driver.close();
    		}
    		
    	}
    	driver.switchTo().window(parentID);
   
    }
    		
    	
 
    

  // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}