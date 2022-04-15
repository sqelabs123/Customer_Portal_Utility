package Customer_Portal;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import ReUsable.assertion_Test;
import ReUsable.repo_Test;
import ReUsable.u_Login;
import ReUsable.u_Login_1;

public class WorkFlowSetting extends u_Login_1 {
	
	repo_Test action_obj;// repo_Test class object
	assertion_Test Assertion_obj;// assertion_Test class object
	JavascriptExecutor executor; // JavascriptExecutor object
	@Test
  public void WorkFlow_Enable_OR_Disable() throws Exception {
		action_obj = new repo_Test(driver);
		Assertion_obj = new assertion_Test(driver);
		executor = (JavascriptExecutor) driver;
		Thread.sleep(10000);
		//click on Admin menu
		action_obj.admiN_maiN_menU().click();
		//Click on Customer Portal Settings link
		action_obj.customeR_portaL_settingS().click();
		Thread.sleep(6000);
			//Scroll down
	    executor.executeScript("window,scrollBy(0,1000)", "");
		
		action_obj.WorkFlowSetting().click();	
		
		Assertion_obj.WorkFSetting();
		
		//add Priority
	Select priority=new Select(action_obj.Contact_priority());
	priority.selectByVisibleText("Medium");	
	Thread.sleep(1000);
	//Action Days
	Select actions=new Select(action_obj.contactActionDays());
	actions.selectByVisibleText("3");
	// Contact User click fields
	 action_obj.contact_User().click();
	 action_obj.contact_User_option().click();
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
	// Contact Enable/Disable
	 action_obj.ContactEnable().click();
	 
	 //save_contact 
	 action_obj.save_contact().click();
	Assertion_obj.WorkFlow_Under_ContactTab_save();
	action_obj.close_contact().click();
	Thread.sleep(2000);
	executor.executeScript("window,scrollBy(0,-1000)", "");
	  
	  
  }
}
