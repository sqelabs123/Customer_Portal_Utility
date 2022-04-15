package ReUsable;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import ReUsable.repo_Test; //import ReUsable package

public class u_Test {
	
	protected WebDriver driver;
	 
	repo_Test action_obj; //create object of 'repo_Test' class under 'ReUsable' package.
	@BeforeClass //This method run before every class
	@Parameters("url")//Give parameter to URL
	public void Open_Browser(String u) throws InterruptedException, IOException {
 

		
		String exePath =  System.getProperty("user.dir") + "\\library_files\\chromedriver.exe";//chrome driver address
		
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
		
 		action_obj = new repo_Test(driver); // 'action_obj' is a object of 'repo_Test' class
		//Assertion_obj = new Assertion_Test(driver);// unwanted remove it 
 	 	driver.get(u);
 	    driver.manage().window().maximize();


	}
}