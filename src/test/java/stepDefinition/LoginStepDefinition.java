package stepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import base.TestBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.AdminLoginPage;
import utility.TestUtil;
import utility.WebEventListener;

public class LoginStepDefinition extends TestUtil{
	public LoginStepDefinition() throws FileNotFoundException, IOException {
	//	super();
		logger=Logger.getLogger("OrangeHRM");
	    PropertyConfigurator.configure("log4j.properties");
	        prop=new Properties();
	    File f=new File("E:\\Eclipse All versions\\Workspace\\CucumberTestNG\\log4j.properties");
	        FileInputStream fis=new FileInputStream(f);
	    prop.load(fis);
		
	}
	public static Properties prop;
	public AdminLoginPage adminLogin; 
	public static WebDriver driver;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Logger logger;

		
	 @Given("^user is already on Login Page$")
	 public void user_already_on_login_page() throws IOException{
	 System.setProperty("webdriver.chrome.driver","E:\\Eclipse All versions\\Workspace\\CucumberTestNG\\Drivers\\chromedriver.exe");
	 driver = new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
	 driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);

	 e_driver=new EventFiringWebDriver(driver);
	    eventListener=new WebEventListener();
	    e_driver.register(eventListener);
	    driver=e_driver;
	   driver.get("https://opensource-demo.orangehrmlive.com/");
//		 initialization();
//		 adminLogin=new AdminLoginPage(driver);

	 }
	
	
	 @When("^title of login page is Orange HRM$")
	 public void title_of_login_page_is_Orange_HRM() throws Throwable {
	 String title = driver.getTitle();
	 System.out.println(title);
	
	 }
	 @Then("^user enters \"(.*)\" and \"(.*)\"$")
	 public void user_enters_username_and_password(String username, String password) throws IOException{
	 driver.findElement(By.name("txtUsername")).sendKeys(username);
	 logger.info("Username:::Entered");
	 driver.findElement(By.name("txtPassword")).sendKeys(password);
	 logger.info("Password:::Entered");
	
	 //hard code to take screenshot
//	        File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//	        String currentDir = System.getProperty("user.dir");
//	        FileUtils.copyFile(srcfile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	    
	 
	 //	 adminLogin.enteruserName(prop.getProperty("username"));
 
//     adminLogin.enteruserPassword(prop.getProperty("password"));
     
     

	 
	 }
	
	 @Then("^user clicks on login button$")
	 public void user_clicks_on_login_button() throws IOException {
		 driver.findElement(By.id("btnLogin")).click();
		// adminLogin.clickButton();
	     logger.info("Button::::Clicked");
	     System.out.println("Credentials Passed");
	   
	 }
	
	
	 @Then("^user is on home page$")
	 public void user_is_on_hopme_page(){
	 String title = driver.getTitle();
	 System.out.println("Home Page title ::"+ title);
	 }
	 
	 @Then("^Close the browser$")
	 public void close_the_browser(){
		 driver.quit();
	 }
}
