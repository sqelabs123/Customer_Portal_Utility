package ReUsable;



import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import ReUsable.assertion_Test;
import ReUsable.repo_Test;

public class u_Login {

	protected WebDriver driver;

	public static DesiredCapabilities capability;

	public repo_Test action_obj;
	public assertion_Test Assertion_obj;
	
	@BeforeClass
	@Parameters({"userId","password","url"})
	public void Login(String userId,String password,String url) throws Exception {
		
		action_obj = new repo_Test(driver);


		 	String exePath =  System.getProperty("user.dir") + "\\library_files\\chromedriver1.exe";//chrome driver address		
		 	System.setProperty("webdriver.chrome.driver", exePath);
		 	driver = new ChromeDriver();
		
		action_obj = new repo_Test(driver); // object of 'repo_Test' class
		Assertion_obj = new assertion_Test(driver);// object of 'repo_Test' class

		System.out.println("---------------------------------------------------------------------------------------------------------------");
		//Open URL
		 System.out.println("@URL Opening......."+url);
		driver.get(url);
		driver.manage().window().maximize();		
		action_obj.logiN_usernamE().sendKeys(userId);//call with rapo_test class object to login username text field with username id with passing parameter through xml
		action_obj.logiN_passworD().sendKeys(password);//same as logiN_usernamE
		action_obj.logiN_buttoN().click();//same as logiN_usernamE

	}
	@AfterClass
	public void logout() throws InterruptedException, MalformedURLException {
	Thread.sleep(23584);	
	Thread.sleep(1500);
		List<WebElement> lout=(List<WebElement>) driver.findElements(By.xpath(".//*[@title='User'] "));
		if(lout.size()==1)
		{
	    System.out.println("---------------------------------------------------------------------------------------------------------------");
		System.out.println("@#############################################################");
		JavascriptExecutor executor=(JavascriptExecutor) driver;
		executor.executeScript("scrollBy(0,-2000)", "");		
		  action_obj.logouT_useR_icoN().click();//user icon click 
		  action_obj.logouT_icoN().click();// //logout icon click
		  
		  	//validate by Login text on label
			Thread.sleep(6000);
			String validation1 = driver.findElement(By.xpath(".//b[text()='Login']")).getText();			 
			Assert.assertEquals("Login", validation1);
			System.out.println("Customer logout successfully");
		} 
	}
}
