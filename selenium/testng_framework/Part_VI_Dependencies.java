package testng_framework;

import org.junit.Assert;
import org.testng.annotations.Test;



public class Part_VI_Dependencies {
	 
	  @Test()
	  public void Priority_01_Create_New_Customer() {
		  System.out.println("Run test 01");
	  }
	  @Test(dependsOnMethods = "Priority_01_Create_New_Customer")
	  public void Priority_02_Edit_Customer() {
		  System.out.println("Run test 02");
	  }
	  @Test(dependsOnMethods = "Priority_02_Edit_Customer")
	  public void Priority_03_Create_New_Account() {
		  System.out.println("Run test 03");
		  Assert.assertTrue(false);
	  }
	  @Test(dependsOnMethods = "Priority_03_Create_New_Account")
	  public void Priority_04_Edit_Account() {
		  System.out.println("Run test 04");
	  }
	  @Test(dependsOnMethods = "Priority_04_Edit_Account")
	  public void Priority_05_Delete_Account() {
		  System.out.println("Run test 05");
	  }
	  @Test(dependsOnMethods = "Priority_05_Delete_Account")
	  public void Priority_06_Delete_Customer() {
		  System.out.println("Run test 06");
	  }
	 
	
}

