package testng_framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Part_II_Group {
	  //Pre-conditon : new driver, new browser, new data test....
	  WebDriver driver;
	  @BeforeClass
	  public void beforeClass() {
		  System.out.println("beforeClass");
		  
		  //New driver
		  driver = new FirefoxDriver();
		  //New browser
		  
		  //New Data set
		  Assert.assertTrue(false);
	  }
	  //Test case
	  @Test(groups = "user")
	  public void TC_01() {
		  System.out.println("Run test 01");
	  }
	  @Test(groups = "user")
	  public void TC_02() {
		  System.out.println("Run test 02");
	  }
	  @Test(groups = "pay")
	  public void TC_03() {
		  System.out.println("Run test 03");
	  }
	  @Test(groups = "pay")
	  public void TC_04() {
		  System.out.println("Run test 04");
	  }
	  @Test(groups = "shop")
	  public void TC_05() {
		  System.out.println("Run test 05");
	  }
	  @Test(groups = "shop")
	  public void TC_06() {
		  System.out.println("Run test 06");
		  Assert.assertTrue(false);
	  }
	  //Post-Condition : Close browser/clone driver/clean data test/...
	  @AfterClass(alwaysRun = true)
	  public void afterClass() {
		  System.out.println("afterClass");
		  driver.quit();
	  } 
}
