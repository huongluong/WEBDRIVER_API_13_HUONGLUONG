package webdriver_api;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Topic_07_CustomDropdownList {
	//Khai báo 1 cái biến driver đại diện cho Selenium Webdriver
	WebDriver driver;
    WebDriverWait waitExplicit;
    By numberAllItems = By.xpath("//ul[@id='number-menu']/li");
    JavascriptExecutor javascript;
    Actions action;
	//Pre - condition 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		javascript = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}	
		

	
	//@Test
	public void TC_01_JQuery() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//Click vào kiểm tra nó được chọn thành công
		SelectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "19");
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class ='ui-selectmenu-text' and text()='19']")));
		Thread.sleep(5000);
		
		SelectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "5");
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class ='ui-selectmenu-text' and text()='5']")));
		Thread.sleep(5000);
		
		SelectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "10");
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class ='ui-selectmenu-text' and text()='10']")));
		Thread.sleep(5000);
		
		
	}
	
	//@Test
	public void TC_02_Angular() throws InterruptedException {
		
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		//Click vào kiểm tra nó được chọn thành công
		SelectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Football");
		Thread.sleep(2000);
		String selectedValue = getTextByJS("#games_hidden > option");
		Assert.assertEquals(selectedValue, "Football");
		
	}
	
	//@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		//Click vào Christian và kiểm tra giá trị được chọn thành công
		SelectItemInCustomDropdown("//i", "//div[@role='option']/span", "Christian");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='text' and text()='Christian']")));
		
		//Click vào Jenny Hess và kiểm tra giá trị được chọn thành công
		SelectItemInCustomDropdown("//i", "//div[@role='option']/span", "Jenny Hess");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='text' and text()='Jenny Hess']")));

		//Click vào Justen Kitsune và kiểm tra giá trị được chọn thành công
		SelectItemInCustomDropdown("//i", "//div[@role='option']/span", "Justen Kitsune");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='text' and text()='Justen Kitsune']")));

	}
	
	
	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		//Click vào [Second option] và kiểm tra giá trị được chọn thành công
		SelectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(string(),'Second Option')]")));
		
		
		//Click vào [First Option] và kiểm tra giá trị được chọn thành công
		SelectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(string(),'First Option')]")));
				
		//Click vào [Third Option] và kiểm tra giá trị được chọn thành công
		SelectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(string(),'Third Option')]")));
			

	}
	
	//@Test
	public void TC_03_EditableDropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		//Click vào [American Samoa] và kiểm tra giá trị được chọn thành công
		inputItemCustomDropdown("//div[contains(@class,'search selection dropdown')]//i[@class='dropdown icon']","//input[@class='search']","American Samoa");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='text' and text()='American Samoa']")));
		
		//Click vào [Afghanistan] và kiểm tra giá trị được chọn thành công
		inputItemCustomDropdown("//div[contains(@class,'search selection dropdown')]//i[@class='dropdown icon']","//input[@class='search']","Afghanistan");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='text' and text()='Afghanistan']")));
		
		//Click vào [Benin] và kiểm tra giá trị được chọn thành công
		inputItemCustomDropdown("//div[contains(@class,'search selection dropdown')]//i[@class='dropdown icon']","//input[@class='search']","Benin");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='text' and text()='Benin']")));
	

	}
		
		
	
    public void inputItemCustomDropdown(String xpathDropdown,String inputXpath, String expectedItem) {
    	//1.Click vao the chua drop down list de no xổ ra hết tất cả các item 
    	driver.findElement(By.xpath(xpathDropdown)).click();
    	
    	//2. Nhập giá trị vào thẻ input
    	driver.findElement(By.xpath(inputXpath)).sendKeys(expectedItem);
    	
    	//3. Truyền phím ENTER vào
    	action.sendKeys(driver.findElement(By.xpath(inputXpath)),Keys.ENTER).perform();
    	
    	 
    }

    public void SelectItemInCustomDropdown(String xpathDropdown, String xpathAllItem,String selectItem) {
        driver.findElement(By.xpath(xpathDropdown)).click();
		
		//2. Khai báo list webelement để chứa tất cả các item
		List <WebElement> allItems = driver.findElements(By.xpath(xpathAllItem));
		
		
		//3. Wait cho tất cả item được xuất hiện ở trong DOM (không bắt buộc hiển thị ở UI)
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathAllItem)));
		
		//4. Get text từng item trong list và so sánh với item mình chọn
		for(WebElement item:allItems){
			System.out.println(item.getText());
			//5. Kiểm tra item nào đúng với item mình cần chọn thì click vào
			if(item.getText().equals(selectItem)) {
				item.click();
				break;
			}
		}
    }
    public String getTextByJS(String locator) {
    	return (String) javascript.executeScript("return document.querySelector('" + locator + "').text");
    	
    }
    
    public boolean isElementDisplayed(By  by)
	{
		WebElement element = driver.findElement(by);
		if(element.isDisplayed()){
			return true;
		}else {
			return false;
		}
	}
	
	public String getTextElement(By by) {
		WebElement element = driver.findElement(by);
		return element.getText();
	}
    // Post - Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.close();
	}

}