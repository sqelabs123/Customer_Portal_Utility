package Customer_Portal;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import ReUsable.assertion_Test;
import ReUsable.repo_Test;
import ReUsable.u_Login;

public class CustomerPortalMenu extends u_Login {
	 repo_Test action_obj;// repo_Test class object
		assertion_Test Assertion_obj;// assertion_Test class object
		JavascriptExecutor executor; // JavascriptExecutor object
		
		String electPlan="Electricity Residential plan";   //Elec Plan name
		String waterPlan="Water Residential plan(Flat)";  // Water plan name
		static String ele="";
		
		public String url;
		public String customerNumber;
		public String customerCurrentAmount;
		public String customerStatementNumber;
		public String MeterNumber;
		public String portalpassword = "123456";
		public String MeterNumber1;
		boolean bValue = false;
		public String email_id="test.tester27@yahoo.com";
	

		
	@Test(priority = 1)
	public void Create_Customer() throws Exception
	{
		
		System.out.println(".............................ElectCreateCustomer_test class.............................");
		action_obj = new repo_Test(driver);
		Assertion_obj = new assertion_Test(driver);
		executor = (JavascriptExecutor) driver;
		
		//Click on customer menu
		Thread.sleep(2000);
		action_obj.creatE_customeR().click();
	
		
		//.............................Fill all mandatory fields of customer.............................//
		System.out.println(".............................Fill all mandatory fields of customer.............................");
		//Select any option from customer category drop-down
		Select select = new Select(action_obj.customeR_categorY());//drop-down code
		select.selectByVisibleText("Business"); //Business is the option in category drop-down 
		//Enter First name
		action_obj.customeR_firsT_namE().sendKeys("ellen");
		//Enter Last name
		action_obj.customeR_lasT_namE().sendKeys("stewart");
		//Enter Phone number
		action_obj.customeR_phonE().sendKeys("9876543210");
		//Enter Email 
		action_obj.Email().sendKeys(email_id);
		//Enter Physical address
		action_obj.customeR_physicaL_addresS().sendKeys("New York, Abc");
		//scroll down the page
		executor.executeScript("window,scrollBy(0,500)", "");
		//Click on Billing address toggle button
		action_obj.customeR_billing_addresS().click();
		
		//scroll down the page
		executor.executeScript("window,scrollBy(0,1000)", "");
		
		//Select any option from customer Plan drop-down
		Select select1 = new Select(action_obj.customeR_plaN());//drop-down code
		select1.selectByVisibleText("Electricity Residential plan"); //electPlan is the option in plan drop-down 
		//Click on customer portal toggle button		
		action_obj.customeR_portaL_accesS_togglE().click();//Enable first 		
		// Enter Customer portal password
		action_obj.customeR_portaL_passworD().sendKeys("123456");
		
		//scroll down the page
		executor.executeScript("window,scrollBy(0,1200)", "");
		
		Thread.sleep(20000);
		// Click on 'Save' customer button
		action_obj.savE_customeR().click();
		
		
	    
		// Click on OK button of Confirmation
		action_obj.customeR_oK_confirmatioN().click();
		
		//Save customer validation message		
		Assertion_obj.sucessfully_saved_customer_message_validation();
		
		//get customer number
		customerNumber=driver.findElement(By.xpath(".//*[@id='nav']/div/div/div[1]/div[2]/div[1]/label/a")).getText();
		//get customer Current Amount
		 customerCurrentAmount=driver.findElement(By.xpath(".//*[@id='nav']/div/div/div[1]/div[2]/div[5]/div/span")).getText();
        // detail button click 
		 action_obj.customeR_Detailss().click();
		 
	     //scroll down
		 executor.executeScript("window,scrollBy(0,500)", "");
		 
		 
		 //From Detail page Click and swap Customer Portal Side
		 
		 // Click Go Customer Portal Tab
		action_obj.GoToCustomerPortal().click();
		//assertion On Home Page On Custoemr Portal Is Open  
		Assertion_obj.customer_Portalhome_validation();
	}}
