package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_JavascriptExecutor {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	WebElement element;
	String username = "mngr238966";
	String password = "emYmEqe";
	
	//Input in New Customer form
	String customerName = "Selenium Online";
	String gender = "male";
	String dateOfBirth = "2000-10-01";
	String address="123 Address";
	String city = "Ho Chi Minh";
	String state = "Thu Duc";
	String PIN = "123456";
	String mobileNumber = "0123456789";
	String email = "selenium" + randomNumber()  + "@gmail.com";
	
	 //Locator for new and edit Customer
    By nameTextboxBy = By.name("name");
    By genderRadioBy = By.xpath("//input[@value ='m']");
    By genderTextboxBy = By.name("gender");
    By dateOfBirthTextboxBy = By.name("dob");
    By addressTextAreaBy = By.name("addr");
    By cityTextboxBy = By.name("city");
    By stateTextboxBy = By.name("state");
    By pinTextboxBy = By.name("pinno");
    By mobileNumberTextboxBy = By.name("telephoneno");
    By emailTextboxBy = By.name("emailid");
    By passwordTextboxBy = By.name("password");
    By submitBtnBy = By.name("sub");
		@BeforeClass
		public void beforeClass() {
			//driver = new FirefoxDriver();
			// Set driver for JE lib
			System.setProperty("webdriver.chrome.driver", projectPath + "\\libraries\\chromedriver.exe");
			driver = new ChromeDriver();
			jsExecutor = (JavascriptExecutor) driver;

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		//@Test
		public void TC_01_JS() throws InterruptedException {
			navigateToUrlByJS("http://live.guru99.com/");
			
			String liveDomain = (String) executeForBrowser("return document.domain");
			Assert.assertEquals(liveDomain,"live.demoguru99.com");
			
			String liveUrl = (String) executeForBrowser("return document.URL");
			Assert.assertEquals(liveUrl,"http://live.demoguru99.com/");
			
			highlightElement("//div[@id='header-nav']//a[text()='Mobile']");
			clickToElementByJS("//div[@id='header-nav']//a[text()='Mobile']");
			
			highlightElement("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button[@title='Add to Cart']");
			clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button[@title='Add to Cart']");
			
			//Assert.assertTrue(verifyTextInInnerText("Samsung Galaxy was added to your shopping cart."));
			//or
			String pageInnerText = (String) executeForBrowser("return document.documentElement.innerText");
			Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));
			
			highlightElement("//a[text()='Customer Service']");
			clickToElementByJS("//a[text()='Customer Service']");
			String customerServiceTitle = (String) executeForBrowser("return document.title");
			Assert.assertEquals(customerServiceTitle,"Customer Service");
			
			highlightElement("//span[text()='Newsletter']");
			scrollToElement("//span[text()='Newsletter']");
			Thread.sleep(3000);
			
			pageInnerText = (String) executeForBrowser("return document.documentElement.innerText");
			Assert.assertTrue(pageInnerText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
			
			navigateToUrlByJS("http://demo.guru99.com/v4/");
			String demoDomain = (String) executeForBrowser("return document.domain");
			Assert.assertEquals(demoDomain,"demo.guru99.com");
		}

		//@Test
		public void TC_02_RemoveAttribute() throws InterruptedException {
			driver.get("http://demo.guru99.com/v4");
			
			//Login
			driver.findElement(By.name("uid")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.name("btnLogin")).click();
			
			String homePageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
			Assert.assertEquals(homePageWelcomeMsg,"Welcome To Manager's Page of Guru99 Bank");
			
			Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + username +"']")).isDisplayed());
			
			//Create new customer
			driver.findElement(By.xpath("//a[text()='New Customer']")).click();
			driver.findElement(nameTextboxBy).sendKeys(customerName);
			driver.findElement(genderRadioBy).click();
			
			//remove attribute type = 'date'
			removeAttributeInDOM("//input[@id='dob']", "type");
			Thread.sleep(3000);
			driver.findElement(dateOfBirthTextboxBy).sendKeys(dateOfBirth);
			driver.findElement(addressTextAreaBy).sendKeys(address);
			driver.findElement(cityTextboxBy).sendKeys(city);
			driver.findElement(stateTextboxBy).sendKeys(state);
			driver.findElement(pinTextboxBy).sendKeys(PIN);
			driver.findElement(mobileNumberTextboxBy).sendKeys(mobileNumber);
			driver.findElement(emailTextboxBy).sendKeys(email);
			driver.findElement(passwordTextboxBy).sendKeys(password);
			driver.findElement(submitBtnBy).click();
			
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
			
			// Check output data = input data
			Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
			Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
			Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
			Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
			Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
			Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
			Assert.assertEquals(PIN, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
			Assert.assertEquals(mobileNumber, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
			Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
			
		}
		@Test
		public void TC_03_CreateNewAccount() throws InterruptedException {
			navigateToUrlByJS("http://live.guru99.com/");
			clickToElementByJS("//a[@title = 'My Account']");
			clickToElementByJS("//a[@title='Create an Account']");
			sendkeyToElementByJS("//input[@id='firstname']", "Automation");
			sendkeyToElementByJS("//input[@id='lastname']", "FC");
			sendkeyToElementByJS("//input[@id='email_address']", "automationfc" + randomNumber() + "@gmail.com");
			sendkeyToElementByJS("//input[@id='password']", "123654");
			sendkeyToElementByJS("//input[@id='confirmation']", "123654");
			clickToElementByJS("//button//span[text()='Register']");
			
			String pageInnerText = (String) executeForBrowser("return document.documentElement.innerText");
			Assert.assertTrue(pageInnerText.contains("Thank you for registering with Main Website Store."));
			
			clickToElementByJS("//div[@id='header-account']//a[text()='Log Out']");
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
			
		}
		// Browser
		public Object executeForBrowser(String javaSript) {
			return jsExecutor.executeScript(javaSript);
		}

		public boolean verifyTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		// Element
		public void highlightElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}

		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		}

		public void scrollToElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void sendkeyToElementByJS(String locator, String value) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		}
		
		public int randomNumber()
		{
			Random rand = new Random();
			return rand.nextInt(100000);
			
		}	
		// Post - Condition
		@AfterClass
		public void afterClass() {
			// Tắt trình duyệt
			driver.quit();
		}
}