package webdriver_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
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
    String javascriptPath, jqueryPath;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor javascriptExecutor;
    
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
		
		javascriptPath = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";
		jqueryPath = projectPath + "\\dragAndDrop\\jquery_load_helper.js";
		javascriptExecutor = (JavascriptExecutor) driver;
				
		
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
	//@Test
	public void TC_05_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(findXpath("//span[text()='right click me']")).perform();
		
		action.moveToElement(findXpath("//span[text()='Quit']")).perform();
		
		Assert.assertTrue(findXpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']").isDisplayed());
		
		action.click(findXpath("//span[text()='Quit']")).perform();
		
		Assert.assertEquals(driver.switchTo().alert().getText(),"clicked: quit");
		
		driver.switchTo().alert().accept();
	}
	//@Test
		public void TC_05_Drag_And_Drop() {
			driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
			String targetCircle = "//div[@id='droptarget']";
			String sourceCircle = "//div[@id='draggable']";
			
			action.dragAndDrop(findXpath(sourceCircle), findXpath(targetCircle)).perform();
			
			
			Assert.assertTrue(findXpath("//div[@id='droptarget' and text()='You did great!']").isDisplayed());
			
			
		}
	
	@Test
	public void TC_05_Drag_And_Drop_HTML5() throws InterruptedException, IOException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		String target = "#column-b";
		String source = "#column-a";
		
		String java_script = readFile(javascriptPath);
		//Inject Jquery lib to site
		//String jqueryscript = readFile(jqueryPath);
		//javascriptExecutor.executeScript(jqueryscript);
		
		
		
		java_script = java_script + "$(\"" + source + "\").simulateDragDrop({ dropTarget: \"" + target + "\"});";
		
		// A to B
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(findXpath("//div[@id='column-a']/header[text()='B']").isDisplayed());
		// B to A
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(findXpath("//div[@id='column-b']/header[text()='B']").isDisplayed());

		
		
		
	}
	@Test
	public void TC_07_DragDrop_HTML5_Offset() throws InterruptedException, IOException, AWTException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
	
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		Assert.assertTrue(findXpath("//div[@id='column-a']/header[text()='B']").isDisplayed());
		
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		Assert.assertTrue(findXpath("//div[@id='column-a']/header[text()='A']").isDisplayed());
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		Assert.assertTrue(findXpath("//div[@id='column-a']/header[text()='B']").isDisplayed());
		
	}
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	public WebElement findXpath(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}