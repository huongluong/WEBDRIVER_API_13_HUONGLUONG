package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class Topic_09_User_Interaction {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
    Actions action;
    
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		//FirefoxProfile profile = new FirefoxProfile();
		//profile.setPreference("dom.webnotifications.enabled", false);
		//driver = new FirefoxDriver(profile);
		//System.out.println(driver);
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	//@Test
	public void TC_01_moveMouseHover() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Discover']"))).perform();
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and  text()='American Eagle']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title' and text()='American Eagle']")).isDisplayed());
		
	}
    
	//@Test
	public void TC_02_Click_And_Hold() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		int numbersSize = numbers.size();
		System.out.println("Size before click and hosld:" + numbersSize); //12
		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(3)).release().perform();
		List<WebElement> selectedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("Size after click and hosld:" + selectedNumbers.size()); //4
		for(WebElement number: selectedNumbers) {
			System.out.println(number.getText());
		}
		Assert.assertEquals(selectedNumbers.size(),4);
		Thread.sleep(3000);
	}
	//@Test
	public void TC_03_Click_And_Hold_Random() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		int numbersSize = numbers.size();
		System.out.println("Size before click and hosld:" + numbersSize); //12
		
        action.keyDown(Keys.CONTROL).perform();
        action.click(numbers.get(0))
        .click(numbers.get(2))
        .click(numbers.get(5))
        .click(numbers.get(10))
        .perform();
        /*action.click(numbers.get(0)).perform();
        action.click(numbers.get(2)).perform();
        action.click(numbers.get(4)).perform();
        action.click(numbers.get(6)).perform();
        action.click(numbers.get(9)).perform();*/
        action.keyUp(Keys.CONTROL).perform();
        

		List<WebElement> selectedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("Size after click and hosld:" + selectedNumbers.size()); //4
		for(WebElement number: selectedNumbers) {
			System.out.println(number.getText());
		}
		Assert.assertEquals(selectedNumbers.size(),4);
		Thread.sleep(3000);
	}
	//@Test
	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[contains(text(),'Double click me')]"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
		
	}
	@Test
	public void TC_05_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(findXpath("//span[text()='right click me']")).perform();
		
		action.moveToElement(findXpath("//span[@text='Quit']")).perform();
		
		Assert.assertTrue(findXpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']").isDisplayed());
		
		action.click(findXpath("//span[@text='Quit']")).perform();
		
		Assert.assertEquals(driver.switchTo().alert().getText(),"clicked: quit");
		
		driver.switchTo().alert().accept();
	}
	
	public WebElement findXpath(String locator) {
		return driver.findElement(By.xpath(locator));
	}
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}