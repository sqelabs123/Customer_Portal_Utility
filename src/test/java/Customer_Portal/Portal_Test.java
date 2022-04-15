package Customer_Portal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import ReUsable.assertion_Test;
import ReUsable.repo_Test;
import ReUsable.u_Login;

public class Portal_Test extends u_Login
{
		repo_Test action_obj;// repo_Test class object
		assertion_Test Assertion_obj;// assertion_Test class object
		JavascriptExecutor executor; // JavascriptExecutor object
		
		String electPlan="Electricity Residential plan";   //Elec Plan name
		String waterPlan="Water Residential plan(Flat)";  // Water plan name
		static String ele="";
		
		public String customerNumber;
		public String customerCurrentAmount;
		public String customerStatementNumber;
		public String MeterNumber;
		public String portalpassword = "123456";
		public String MeterNumber1;
		boolean bValue = false;
		
	@Test(priority = 0)
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
		
		// Enter Company Name
				driver.findElement(By.id("companyName")).sendKeys("Company soman");
		//Enter First name
		action_obj.customeR_firsT_namE().sendKeys("ellen");
		//Enter Last name
		action_obj.customeR_lasT_namE().sendKeys("stewart");
		//Enter Phone number
		action_obj.customeR_phonE().sendKeys("9876543210");
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
		//select1.selectByIndex(0);
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
	}														 
	@Test(priority = 1)
	public void Add_Services() throws Exception
	{
		
		Thread.sleep(2000);//sleep time 
		//click on overview sub menu of customer
		action_obj.customeR_overvieW().click();
		//Click on Add icon of Electricity service
		action_obj.electricitY_adD_icoN().click();
		//Electricity page header assertion
		Assertion_obj.Electricity_service_page_header_validation();
		
		//Select plan from plan drop-down
		Select select1 = new Select(action_obj.customeR_plaN());//drop-down code // Reusing this address from customer function(Create_Customer)
		select1.selectByVisibleText(electPlan); //Electricity Residential plan is the option in plan drop-down Declared string in top
		
		
		//Set date and time format here to get the different meter number every time 
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		ele=cal.getTime().toString(); 
		System.out.println(ele=ele.substring(11,19));
		ele=ele.replaceAll(":","");			
		// Enter Meter number of Electricity service
		action_obj.customeR_elecT_meteR_numbeR().sendKeys(ele); 
		
		// Select Meter configuration of Electricity service
		Select select2= new Select(action_obj.customeR_elecT_meteR_confiG());
		select2.selectByVisibleText("Flat Rate");
		// Select Meter Read Type of Electricity service
		Select select3= new Select(action_obj.customeR_elecT_reaD_typE());
		select3.selectByVisibleText("Reads");
		
		//scroll down the page
		executor.executeScript("window,scrollBy(0,1000)", "");
		
		Thread.sleep(5000);//Sleep time 
		
		// Save Electricity service// Click on Create service button
		action_obj.customeR_elecT_creatE_servicE().click();
		// Click on OK button of Confirmation
		action_obj.customeR_oK_confirmatioN().click();/// Reusing this address from customer function(Create_Customer)
		
		//After saving service //utilities page header assertion
		Assertion_obj.utilities_header_validation();
		
		//get Meter number
		MeterNumber=driver.findElement(By.xpath(".//td[@class='sorting_1']")).getText();
	}
	@Test (priority=3)
	public void Add_Reads() throws Exception
	{
	
		Thread.sleep(32984);
		Thread.sleep(5000);
		//click on Meter Reads sub menu of customer
		action_obj.customeR_meteR_readS().click();
		//Select Customer  meter no.		
		Select select4 = new Select(action_obj.customeR_meteR_numbeR());	
		Thread.sleep(4000);
		select4.selectByIndex(1);
		
		//Assertion for view reads
		Assertion_obj.viewRead_Text_validation();
		
		//Click on Add Read button	 ******************for	Intial Read**********
		Thread.sleep(3000);
		action_obj.customeR_adD_reaD().click();
		
		//Add_MeterRead_PopupText assertion
		Assertion_obj.AddMeterReadPopupText_validation();
		
		//Select Read Type		
		Select select5 = new Select(action_obj.customeR_reaD_typE());
		Thread.sleep(2000);
		
		select5.selectByVisibleText("Initial");
		//sleep time 
		Thread.sleep(5000);
		
		//Select Read Date date-picker	
	              	action_obj.customeR_reaD_datE().click();             
		
		Thread.sleep(5000);
		//sleep time 
		Thread.sleep(5000);
		//Select Today date
		action_obj.selecT_todaY_datE().click();
		//Enter Meter Read(Flat)
		action_obj.customeR_reaD_flaT().sendKeys("10");  
		
		Thread.sleep(25000);
		//Enter comment
		action_obj.customeR_commenT().sendKeys("This is for Testing.(Intial)");
		//Click on 'Save' button
		Thread.sleep(4000);
		action_obj.customeR_read_savE().click();
		//String Initialreads =driver.findElement(By.xpath(".//*[contains(text(),'Initial Read')]")).getText();
		//Assert.assertEquals(Initialreads, "Initial Read");

		//Click on Add Read button	 ******************for	Actual Read**********
		Thread.sleep(5000);
		action_obj.customeR_adD_reaD().click();
		
		//Add_MeterRead_PopupText assertion
		Assertion_obj.AddMeterReadPopupText_validation();
				
		//Select Read Type		
		Select select6 = new Select(action_obj.customeR_reaD_typE());
		Thread.sleep(2000);
		select6.selectByVisibleText("Actual");
		//sleep time 
		Thread.sleep(5000);
		//Select Read Date date-picker	
		action_obj.customeR_reaD_datE().click();
		//sleep time 
		Thread.sleep(3000);
		//Click on next button of calendar 
		action_obj.nexT_buttoN_calandeR().click();
		//Select End date in calendar
		action_obj.selecT_enT_datE_aS().click(); 
		 
		
		//Enter Meter Read(Flat)
		action_obj.customeR_reaD_flaT().sendKeys("100");
		//Enter comment
		action_obj.customeR_commenT().sendKeys("This is for Testing.(Actual)");
		//Click on 'Save' button
		action_obj.customeR_read_savE().click();
		
		//String ActualReads =driver.findElement(By.xpath(".//*[contains(text(),'Actual')]")).getText();
		//Assert.assertEquals(ActualReads, "Actual");
		
		// Extra Sleep time
		Thread.sleep(3000);
		
	}
	@Test(priority=4)
	public void Bill_Run_Cycle() throws InterruptedException
	{
		
		Thread.sleep(5000);
		//click on Admin menu
		action_obj.admiN_maiN_menU().click();
		//Click on Bill run cycle
		action_obj.bilL_ruN_cyclE().click();
		//Click on Create new button.
		action_obj.adD_bilL_ruN_cyclE().click();
		//Enter Cycle name as customer number
		action_obj.cyclE_namE().sendKeys(customerNumber); //pass string to cycle name
	
		action_obj.FilterAllSelectCustomer().sendKeys(customerNumber);
		//Select Search Customer.
		action_obj.selectcustomerinCycle().click();
		
		//Click on Save button
		action_obj.cyclE_savE().click();
		
		
		
	}
		
		@Test(priority=5)
		public void Bill_Run() throws InterruptedException
		{		
		driver.navigate().refresh();
			Thread.sleep(3000);
			
			//Click on Bill run menu		
			action_obj.bilL_ruN().click();   
			
			
			//click on run the bills button				
			executor.executeScript("javascript:skipMeterBulkImport()", action_obj.ruN_thE_billS());
			
			// Sleep time
			Thread.sleep(2000);
			// Select Recurring Charge Date 
			action_obj.recurrinG_chargE_datE().click();
			//Select Today date
			action_obj.selecT_todaY_datE().click();
			
			Thread.sleep(5000);
			// Issue-date Date picker
			action_obj.billruN_issuE_datE().click();
			//Select Today date
			action_obj.selecT_todaY_datE().click();
			
			Thread.sleep(5000);		
			// Due-date Date picker
			action_obj.billruN_duE_datE().click(); 
			//Select Today date
			action_obj.selecT_todaY_datE().click();
			
			Thread.sleep(3000);			
			action_obj.bilL_ruN_cyclE_dropdowN().sendKeys(customerNumber); //pass string to cycle name
			
			//click on Run bill button				
			executor.executeScript("javascript:runBill()", action_obj.ruN_bilL());
		    Thread.sleep(20000);
		    // Click on 'View' button
			action_obj.vieW_buttoN().click();
			
			Thread.sleep(5000);
			//Close icon
			action_obj.closE_icoN().click();
		    // Click on Yes button of close pop-up
			action_obj.yeS_confirmatioN().click();
		// Send message button
			
			
			//****************************************************
			//Enter customer no. in search box
			driver.findElement(By.xpath(".//*[@id='search_input']")).sendKeys(customerNumber);
			Thread.sleep(4000);
			//Click on 'search button.
			driver.findElement(By.xpath(".//*[@id='btn_search']")).click();
			
			//Click on statements tab
			Thread.sleep(4000);
			driver.findElement(By.xpath(".//*[text()=' Statements']")).click();
			
			//Get the statement no.
			Thread.sleep(10000);
			customerStatementNumber = driver.findElement(By.xpath(".//*[@id='DataTables_Table_0']/tbody/tr/td[1]")).getText();
			
		 
	}
		
		@Test(priority=6)
		public void enablE_Customer_Portal_Services() throws InterruptedException //changes settingS to services
		{
		Thread.sleep(5000);
		//click on Admin menu
		action_obj.admiN_maiN_menU().click();
		//Click on Customer Portal Settings link
		action_obj.customeR_portaL_settingS().click();
		Thread.sleep(5000);
			//Scroll down
			executor.executeScript("window,scrollBy(0,800)", "");
			
			List<WebElement> checkbox = driver.findElements(By.xpath(".//*[@id='divCol1']/div/div/div[2]/form/div/div[8]/div[2]/div/div/label/span[1]"));
			
			bValue = checkbox.get(0).isSelected();
			if(bValue = true){
				System.out.println("Click on true value");
				checkbox.get(0).click();
				 
			 }else{
				 System.out.println("Click on false value");
				 	// checkbox.get(1).click();
				 
			 } 
			
			
			
			Thread.sleep(2000);	
			//Enable Services Edit
			/*action_obj.enablE_servicE_ediT().click();
			
			 Try to double click but not works
			 Actions action = new Actions(driver);
			WebElement element=action_obj.enablE_servicE_ediT();   
			//Double click
			action.doubleClick(element).perform();
			------hide 
			
			
			Thread.sleep(2000);	
			//Enable Services Edit
			action_obj.enablE_servicE_ediT().click();
			 			
			Thread.sleep(2000);	
			//Enable Account Details Edit
			action_obj.enablE_accounT_detailS_ediT().click();
			
			Thread.sleep(2000);	
			//Enable Service Settings Edit			
			action_obj.enablE_servicE_settingS_ediT().click();
			
			//Again click on 'Service Edit' toggle button
			Thread.sleep(2000);	
			//Enable Services Edit
			action_obj.enablE_servicE_ediT().click();		
			*/
			
			 Thread.sleep(2000);	
			  
				//Enable Services Edit
				if(action_obj.enablE_servicE_ediT().isEnabled())
				{
					action_obj.enablE_servicE_ediT().click();
				}
				 			
				Thread.sleep(2000);	
				//Enable Account Details Edit
				
				if(action_obj.enablE_accounT_detailS_ediT().isEnabled())
				{
					action_obj.enablE_accounT_detailS_ediT().click();
				}
				
				Thread.sleep(2000);	
				//Enable Service Settings Edit			
				if(action_obj.enablE_servicE_settingS_ediT().isEnabled())
				{
					action_obj.enablE_servicE_settingS_ediT().click();
				}
				
			//Scroll down
			//executor.executeScript("window,scrollBy(0,600)", "");
			
			Thread.sleep(18000);	
			//Click on 'Save' button.			
			action_obj.savE_portaL_settingS().click();	
			//Thread.sleep(15000);	
			
			//validate by success! text
			Thread.sleep(4000);
			 String validation = driver.findElement(By.xpath(".//*[text()='Success!']")).getText();			 
			  Assert.assertEquals("Success!", validation);// assert command
			
			  Thread.sleep(4000);
			//Click on Go to customer portal button
			driver.findElement(By.xpath(".//*[@id='CPButton']")).click();
			Thread.sleep(2000);
			
			
			
		}
			
			@Test(priority=7)
			public void Login_Customer_Portal() throws InterruptedException
			{
			
		//Login Customer Portal
			Thread.sleep(5000);
			
			//Window focus to new tab
			ArrayList<String> window_number = new ArrayList<String> (driver.getWindowHandles());
		     driver.switchTo().window(window_number.get(1));

		   //validate by Login text on label
				Thread.sleep(4000);
				 String validation1 = driver.findElement(By.xpath(".//b[text()='Login']")).getText();			 
				  Assert.assertEquals("Login", validation1);// assert command
				  System.out.println("Customer Login screen appears successfully");
				  
			//Login Customer Portal
				  
			    action_obj.customeR_usernamE().sendKeys(customerNumber); 
			    action_obj.customeR_passworD().sendKeys(portalpassword);   
			    
				Thread.sleep(500);
				action_obj.customeR_gO().click();
				
				driver.navigate().refresh();
				Thread.sleep(1500);
		//Validate by 'Welcome to your portal!' text.  //.//*[text()='Monthly Statements']
				Thread.sleep(5000);
				String obj = driver.findElement(By.xpath(".//span[text()='Welcome to your portal!']|.//*[ng-blind='customername']|.//*[@class='hello']")).getText();
				Assert.assertEquals("Welcome to your portal!", obj);// assert command
				 Thread.sleep(5000);
			}
			@Test(priority=8)
			public void Customer_Services() throws InterruptedException
			{
			
				Thread.sleep(2000);
				//Click on Services tab      
				action_obj.customeR_serviceS().click();
				
				//  Validate by 'Services' text.			   
				Thread.sleep(5000);
				//String obj1 = driver.findElement(By.xpath(".//*[@id='content']/div[2]/div/div/div[1]/h2")).getText();
				String obj1 = driver.findElement(By.xpath(".//*[@id='content']/div[2]/div/div/div[1]/h2|.//*[text()='Services']")).getText();
				
				 Assert.assertEquals("Services", obj1);// assert command
				 Thread.sleep(5000);
//				MeterNumber1= driver.findElement(By.xpath(".//*[@id='DataTables_Table_3']/tbody/tr/td[1]")).getText();
				 
				// move-out                  
				driver.findElement(By.xpath(".//*[@id='DataTables_Table_0']/tbody/tr/td[6]/a/i")).click();
				// Meter details page opens up.	Validate by 'Meter Details' text.
				Thread.sleep(2000);
				// meter details page submit button.
				driver.findElement(By.xpath(".//*[@ng-click='ctrl.moveOutMeter(ctrl.read)']")).click();
				
				Thread.sleep(5000);
				String obj2 = driver.findElement(By.xpath(".//*[text()='Success! This meter number is now disconnected.")).getText();
						 
				Assert.assertEquals("Success! This meter number is now disconnected.", obj2);
				
				// Have to check  again Its for temp solution
				
				Thread.sleep(2000);
			}	
			@Test(priority= 9)
			public void Customer_Charges() throws InterruptedException
			{ //Click on charges tab
				 Thread.sleep(2000);
				action_obj.customeR_chargeS().click();
								
				// validate by 'Charges' text.
				Thread.sleep(4000);
				String obj2 = driver.findElement(By.xpath(".//h2[text()='Charges']")).getText();				 
				Assert.assertEquals("Charges", obj2);// assert command
				
				
				
				//Get the Statement number text
				Thread.sleep(2000);
				 String	StatementNumberText=driver.findElement(By.xpath(".//*[@class='sorting_1']")).getText();
			   	 Assert.assertEquals(customerStatementNumber,StatementNumberText  );// assert command  
				 
				
			 
			}
			
			@Test(priority= 10)
			public void Customer_Statements() throws InterruptedException
			{
//Click on statements tab
				 Thread.sleep(2000);
				action_obj.customeR_statementS().click();
				
				
				// validate by 'Consumption' text.
				Thread.sleep(4000);
				String obj2 = driver.findElement(By.xpath(".//h2[text()='Statements']")).getText();				 
				Assert.assertEquals("Statements", obj2);// assert command
				
				 Thread.sleep(2000);				  
				  String StatementNumberText=driver.findElement(By.xpath(".//*[@class='sorting_1']")).getText();
				  Assert.assertEquals(customerStatementNumber,StatementNumberText  );// assert command
				  
				  Thread.sleep(2000);
				  driver.findElement(By.xpath(".//*[@title='View statement']")).click();
				  
				
				// Store the current window handle
				  String winHandleBefore = driver.getWindowHandle();

				  // Perform the click operation that opens new window

				  // Switch to new window opened
				  for(String winHandle : driver.getWindowHandles()){
				      driver.switchTo().window(winHandle);
				  }

				  
				  
				  
				  
				  
				  
				  Thread.sleep(2000);
				  driver.findElement(By.xpath(".//*[@id='downloadButton']")).click();
				  
				  // Perform the actions on new window

				  // Close the new window, if that window no more required
				  driver.close();

				  // Switch back to original browser (first window)
				  driver.switchTo().window(winHandleBefore);

				  // Continue with original browser (first window)
		 
			
			}
			  
			@Test(priority= 11)
			public void Customer_Transactions() throws InterruptedException
			{
		
		  action_obj.customeR_transactionS().click();
				 Thread.sleep(1000);
				 
				//Thread.sleep(6523);
			}	
			
			@Test(priority=12)
			public void Customer_Usage_Profile() throws InterruptedException
			{
            	//Click on  Usage profile tab
				action_obj.usagE_profilE().click();
				
				Thread.sleep(4000);
				// validate by 'Consumption' text.
				String obj2 = driver.findElement(By.xpath(".//*[text()='Consumption']")).getText();				 
				Assert.assertEquals("Consumption", obj2);// assert command
				
				
				// All Meters drop-down
				Select select6 = new Select(action_obj.alL_meterS());
				Thread.sleep(2000);
				select6.selectByIndex(1);
				Thread.sleep(2000);
				
				//String obj4 = driver.findElement(By.xpath(".//*[@ng-model='selectedMeter'] ")).getText();				 
				//Assert.assertEquals(MeterNumber1, obj4);// assert command
				//Thread.sleep(2000);
				
				
				// All years drop-down
				Select select7 = new Select(action_obj.alL_yearS());
				Thread.sleep(2000);
				select7.selectByIndex(1); 
				Thread.sleep(2000);
				
				// All Months drop-down
				Select select8 = new Select(action_obj.alL_monthS());
				Thread.sleep(2000);
				select8.selectByIndex(1);  
				Thread.sleep(2000);
				
				//Click on Get Usage button
				action_obj.get_usagE().click();
				
				//Validate by chart Usgae chart.
				
			}
			
			@Test(priority=13)
			public void Customer_Account_Details() throws InterruptedException
			{
				//Thread.sleep(12300);
	            //Click on Account Details tab
	           action_obj.customeR_accounT_detailS().click();
				
				// Account Details page opens up. Validate by 'Personal Information' entered in Customer form. ********************* validate from admin
				Thread.sleep(5000);				
				String obj1 = driver.findElement(By.xpath(".//*[text()='Personal Information']")).getText();			 
				Assert.assertEquals("Personal Information", obj1);// assert command	
				

				 // Thread.sleep(2000);
				//	action_obj.customeR_PI_FirstName().clear();
					Thread.sleep(3000);
	//				action_obj.customeR_PI_FirstName().sendKeys("ellenn");
					//click on save changes
	//				action_obj.customeR_PI_save().click(); 
					
	//				Thread.sleep(5000);
					//validate by 'Success!'  text
	//				String obj2 = driver.findElement(By.xpath(".//strong[text()='Success!']")).getText();				 
	//				Assert.assertEquals("Success!", obj2);// assert command
					
					// Click on change password button	
					action_obj.customeR_changE_passworD().click();
					Thread.sleep(2000);
					action_obj.customeR_neW_passworD().clear();
					Thread.sleep(2000);
					action_obj.customeR_neW_passworD().sendKeys("12345678");
					action_obj.customeR_confirM_passworD().sendKeys("12345678");
					Thread.sleep(2000);
					// Click on submit button.
					action_obj.customeR_submiT_passworD().click();	
					Thread.sleep(5000);
					//validate by 'Success!'  text		
					String obj2 = driver.findElement(By.xpath(".//strong[text()='Success!']")).getText();
					Assert.assertEquals("Success!", obj2);
					Thread.sleep(3000);
				//Logout the customer portal
					action_obj.customeR_logouT().click();
					Thread.sleep(2000);
					
					//Login the customer portal with the new password
					Thread.sleep(2000);
					
					    action_obj.customeR_usernamE().sendKeys(customerNumber);                         
					    action_obj.customeR_passworD().sendKeys("12345678");   
					    action_obj.customeR_gO().click();
						Thread.sleep(500);
						
				//Validate by 'Welcome to your portal!' text.  //.//*[text()='Monthly Statements']
						Thread.sleep(5000);
						String obj = driver.findElement(By.xpath(".//span[text()='Welcome to your portal!']|.//*[ng-blind='customername']|.//*[@class='hello']")).getText();
						Assert.assertEquals("Welcome to your portal!", obj);// assert command
						 Thread.sleep(5000);
					
		// Thread.sleep(65037);
			 
			}
			@Test(priority=14)
			public void Customer_Service_Settings() throws InterruptedException
			{
	           // Click on service settings page											 
				action_obj.customeR_servicE_settingS().click();
				
				Thread.sleep(4000);
				// validate by 'Consumption' text.
				String obj2 = driver.findElement(By.xpath(".//*[text()='Bill Options']")).getText();				 
				Assert.assertEquals("Bill Options", obj2);// assert common
									
				//click  on toggle button     
				 driver.findElement(By.xpath(".//*[@id='content']/div[1]/div[3]/div/div[2]/div[1]/div/div/label/span[1]")).click();
	 
			
			
		}
			
			 
			@Test(priority=15)
			public void Help_And_FAQ() throws InterruptedException
			{
			 	Thread.sleep(10965);
            //click on Help and FAQ tab
				action_obj.helP_anD_FAQ().click();
				
 			// validate by 'FAQ'  text.  
			Thread.sleep(5000);
		 	//String obj = driver.findElement(By.xpath(".//h2[text()='FAQ']")).getText(); 
		 	String obj = driver.findElement(By.xpath("//span[@class='break'] ")).getText();
		 	
		 	//Assert.assertEquals("FAQ", obj);// assert command				
				
			Thread.sleep(2000);
			System.out.println(); 
				//Thread.sleep(8987);
				
			}
					
			@Test(priority=16)
			public void Contact_US() throws InterruptedException
			{
              // Click on Contact us tab
	            action_obj.contact_uS().click();
				//Validate by 'Send Us a Message' text.
				Thread.sleep(5000);
				
			//	Thread.sleep(5000);
				// select Message Type from drop-down
		 		Select select6 = new Select(action_obj.messagE_typE());
				Thread.sleep(5000);
			//	select6.selectByVisibleText("Audit Request");
	 			select6.selectByIndex(1);
				
				Thread.sleep(4000);
				
				//Message box 
				action_obj.messagE_boX().sendKeys("This is for Testing");
				//ggggg
				Thread.sleep(5000); 
  		  //validate by Success! Message successfully sent. text
 		//	 	String obj1 = driver.findElement(By.xpath(".//strong[text()='Success!']")).getText(); //get the text which you want to assert
 		//	 	Assert.assertEquals("Success!", obj1);// assert command		
		
 	  
	}	
			
			@Test(priority=17)
			public void DisablE_Customer_Portal_Services() throws InterruptedException
			{    
	//Window focus to new tab ... Go admin tab gain
				ArrayList<String> window_number = new ArrayList<String> (driver.getWindowHandles());
			     driver.switchTo().window(window_number.get(0));
			     
			     
			     List<WebElement> checkbox = driver.findElements(By.xpath(".//*[@id='divCol1']/div/div/div[2]/form/div/div[8]/div[2]/div/div/label/span[1]"));
					
					bValue = checkbox.get(0).isSelected();
					if(bValue = true){
						System.out.println("Click on true value");
						checkbox.get(0).click();
						 
					 }else{
						 System.out.println("Click on false value");
						 	//checkbox.get(1).click();
						 
					 } 
					Thread.sleep(2000);	
					//Enable Services Edit
					action_obj.enablE_servicE_ediT().click();
					 			
					Thread.sleep(2000);	
					//Enable Account Details Edit
					action_obj.enablE_accounT_detailS_ediT().click();
					
					Thread.sleep(2000);	
					//Enable Service Settings Edit			
					action_obj.enablE_servicE_settingS_ediT().click();
					
					//Scroll down
					//executor.executeScript("window,scrollBy(0,600)", "");
					Thread.sleep(18000);	
					//Click on 'Save' button.			
					action_obj.savE_portaL_settingS().click();	
					Thread.sleep(15000);
					
					//Window focus to new tab
					ArrayList<String> window_number1 = new ArrayList<String> (driver.getWindowHandles());
				     driver.switchTo().window(window_number1.get(1));
				     
				     driver.navigate().refresh();
				     
					//Login Customer Portal automatically
					Thread.sleep(4000);
					driver.navigate().refresh();
					
					//Validate by 'Welcome to your portal!' text.  //.//*[text()='Monthly Statements']
					Thread.sleep(5000);
					String obj = driver.findElement(By.xpath(".//span[text()='Welcome to your portal!']|.//*[ng-blind='customername']|.//*[@class='hello']")).getText();
					Assert.assertEquals("Welcome to your portal!", obj);// assert command
					 Thread.sleep(5000);
					
					
//**********************Click on Services tab   
					 
					 action_obj.customeR_serviceS().click();
						
					 
					//Validate by 'Services' text.			   
						Thread.sleep(5000);
						String obj1 = driver.findElement(By.xpath(".//*[@id='content']/div[2]/div/div/div[1]/h2|.//*[text()='Services']")).getText();
								 
						Assert.assertEquals("Services", obj1);// assert command
						 Thread.sleep(5000);
					 
					  
						
						 				
						  // Check  button is enabled or disabled		  
						   boolean Move_out = driver.findElement(By.xpath(".//*[@id='DataTables_Table_0']/tbody/tr/td[6]/a/i")).isEnabled();		  
						  
						  WebElement Move_out1 = driver.findElement(By.xpath(".//*[@id='DataTables_Table_0']/tbody/tr/td[6]/a/i"));
						  //Verify First name text box is enabled or not and then print related message.
						  if(Move_out == true)
						  {
						   System.out.println("***************************Save changes button is Enabled***************************");			   
						  }
						  else
						  {
						   System.out.println("***************************Save changes button is Disabled***************************");
						  }
						  Thread.sleep(2000);
					 
//**********************Click on Account Details tab
					 action_obj.customeR_accounT_detailS().click();
						
						Thread.sleep(5000);
						
						String PInfo = driver.findElement(By.xpath(".//*[text()='Personal Information']")).getText();			 
						Assert.assertEquals("Personal Information", PInfo);// assert command					
						  // Check  button is enabled or disabled		  
						   boolean Savebutton = driver.findElement(By.xpath(".//*[@id='content']/div/div[2]/button")).isEnabled();		  
						  
						  WebElement Savebutton1 = driver.findElement(By.xpath(".//*[@id='content']/div/div[2]/button"));
						  //Verify First name text box is enabled or not and then print related message.
						  if(Savebutton == true)
						  {
						   System.out.println("***************************Save changes button is Enabled***************************");			   
						  }
						  else
						  {
						   System.out.println("***************************Save changes button is Disabled***************************");
						  }
						  Thread.sleep(2000);
						  
//**********************Click on Service settings tab						 							 
						  action_obj.customeR_servicE_settingS().click();
							
							Thread.sleep(5000);					
							  // Check  button is enabled or disabled		  
							   boolean saveChanges = driver.findElement(By.xpath(".//*[@id='content']/div[2]/button")).isEnabled();		  
							  
							  WebElement saveChanges1 = driver.findElement(By.xpath(".//*[@id='content']/div[2]/button"));
							  //Verify First name text box is enabled or not and then print related message.
							  if(saveChanges == true)
							  {
							   System.out.println("***************************save changes  out button is Enabled***************************");			   
							  }
							  else
							  {
							   System.out.println("***************************save changes button is Disabled***************************");
							  }
							  //Validate by 'Bill Options' text.
							  Thread.sleep(4000);
							  String ServiceSettings = driver.findElement(By.xpath(".//*[text()='Bill Options']")).getText();			 
							  Assert.assertEquals("Bill Options", ServiceSettings);// assert command							
										
			    
			}
			
			@Test(priority=18)
			public void Customer_Portal_Menu() throws Exception
			{
		
		ArrayList<String> window_number1 = new ArrayList<String> (driver.getWindowHandles());
			     driver.switchTo().window(window_number1.get(0));
			     
					Actions builder = new Actions(driver);
				
				WebElement CustomerPortalMEnu=action_obj.Customer_menu_option();
				CustomerPortalMEnu.click();
				Thread.sleep(2000);
				action_obj.FAQ_option().click();
				Thread.sleep(2000);
				//save Enable and disable
				action_obj.Done().click();
				Thread.sleep(2000);
				//Click on 'Save' button.			
				action_obj.savE_portaL_settingS().click();	
				//Thread.sleep(15000);	
				
				//validate by success! text
				Thread.sleep(4000);
				 String validation = driver.findElement(By.xpath(".//*[text()='Success!']")).getText();			 
				  Assert.assertEquals("Success!", validation);// assert command
				
				  Thread.sleep(4000);
				//Click on Go to customer portal button
				driver.findElement(By.xpath(".//*[@id='CPButton']")).click();
				Thread.sleep(2000);
				
								
	             	ArrayList<String> window_number11 = new ArrayList<String> (driver.getWindowHandles());
			        driver.switchTo().window(window_number11.get(1));
			        Assertion_obj.customer_Portalhome_validation(); 
			   
				
				    if(action_obj.helP_anD_FAQ().isDisplayed())
					{  
				        //code if condition is true  
						System.out.println("Toggle Button is working and 'FAQ' tab is not display ");
						
				        }else{  
				        //code if condition is false  
				        	System.out.println("Toggle Button is not working and 'FAQ' tab is display ");	
				        }  		
					
			}
			
			
			
// String obj = driver.findElement(By.xpath(".//*[@id='isvalidate']")).getText(); //get the text which you want to assert
//  Assert.assertEquals("Sign-up Successfully.Please check your Email for activating your Account.", obj);// assert command
			
			
			
		@AfterClass
		public void Logout() throws InterruptedException{	
	       Thread.sleep(2000);
			action_obj.customeR_logouT().click();
			Thread.sleep(2000);	
		}
		
		
		
}
		
		
	
