package Customer_Portal;

 

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import ReUsable.assertion_Test;
import ReUsable.repo_Test;
import ReUsable.u_Login;

public class admin_trial{
	 
	 
			repo_Test action_obj;// repo_Test class object
			assertion_Test Assertion_obj;// assertion_Test class object
			JavascriptExecutor executor; // JavascriptExecutor object
			
			String electPlan="Electricity Residential plan";   //Elec Plan name
			String waterPlan="Water Residential plan(Flat)";  // Water plan name
			static String ele="";
			WebDriver driver;
			@Test(priority=0)
			public void enablE_Customer_Portal_Services() throws InterruptedException //changes settingS to services
, IOException
			{
				action_obj = new repo_Test(driver);

				String exePath =  System.getProperty("user.dir") + "\\library_files\\chromedriver1.exe";//chrome driver address
			// 	String exePath =  System.getProperty("user.dir") + "\\library_files\\chromedriver.exe";//chrome driver address		
			 		System.setProperty("webdriver.chrome.driver", exePath);
			 	driver = new ChromeDriver();
			
			action_obj = new repo_Test(driver); // object of 'repo_Test' class
			Assertion_obj = new assertion_Test(driver);// object of 'repo_Test' class
				
				 driver.get("https://go.utilitybilling.com/billing/SrvCustomerPortal/");
				  

				   //validate by Login text on label
						Thread.sleep(4000);
						 String validation1 = driver.findElement(By.xpath(".//b[text()='Login']")).getText();			 
						  Assert.assertEquals("Login", validation1);// assert command
						  System.out.println("Customer Login screen appears successfully");
						  
					//Login Customer Portal
					Thread.sleep(2000);
					    action_obj.customeR_usernamE().sendKeys("165767");                         
					    action_obj.customeR_passworD().sendKeys("12345678");   
					    action_obj.customeR_gO().click();
						Thread.sleep(500);    
			     
			
						
						String MeterNumber= "111545";
						
						action_obj.usagE_profilE().click();
						
						Thread.sleep(4000);
						// validate by 'Consumption' text.
						String obj2 = driver.findElement(By.xpath(".//*[text()='Consumption']")).getText();				 
						Assert.assertEquals("Consumption", obj2);// assert command
						
						
						// All Meters drop-down
						Select select6 = new Select(action_obj.alL_meterS());
						Thread.sleep(2000);
						select6.selectByIndex(1);
						
						Thread.sleep(4000);
						
						
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
							
							
							
					 
						}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		/*	Thread.sleep(5000);
			 
			  driver.findElement(By.xpath(".//*[@id='navbar-menu']/div/ul/li[5]")).click();
			  
			  Thread.sleep(4000);
			  
			  driver.findElement(By.xpath(".//*[@id='content']/div[2]/div[2]/div/div[3]/div/div[2]/div[3]/a")).click();
			  
			  
			
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
				Thread.sleep(15000);
			  
			  
			  
			  
			  JavascriptExecutor executor = (JavascriptExecutor)driver; 
			executor.executeScript("window,scrollBy(0,1000)", "");
				Thread.sleep(4000);
			  driver.findElement(By.xpath(".//*[@id='save_portal_settings']")).click();
			  
			//validate by success! text
				Thread.sleep(4000);
				 String validation = driver.findElement(By.xpath(".//*[text()='Success!']")).getText();			 
				  Assert.assertEquals("Success!", validation);// assert command

			  
			  driver.findElement(By.xpath(".//*[@id='CPButton']")).click();
			  
			//Window focus to new tab
				ArrayList<String> window_number = new ArrayList<String> (driver.getWindowHandles());
			     driver.switchTo().window(window_number.get(1)); */

			  
						  
						
