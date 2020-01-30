package testng_framework;

import org.testng.annotations.Test;



public class Part_III_Priority {
	 
	  @Test(description = "Create New Customer in bank system")
	  public void Priority_01_Create_New_Customer() {
		  System.out.println("Run test 01");
	  }
	  @Test(description = "Edit Customer function")
	  public void Priority_02_Edit_Customer() {
		  System.out.println("Run test 02");
	  }
	  @Test(enabled = false)
	  public void Priority_03_Create_New_Account() {
		  System.out.println("Run test 03");
	  }
	  @Test()
	  public void Priority_04_Edit_Account() {
		  System.out.println("Run test 04");
	  }
	  @Test()
	  public void Priority_05_Delete_Account() {
		  System.out.println("Run test 05");
	  }
	  @Test()
	  public void Priority_06_Delete_Customer() {
		  System.out.println("Run test 06");
	  }
	 
	
}

