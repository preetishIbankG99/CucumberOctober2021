package stepDefinition;


import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DataDrivenMapStepDefinitions {
	public static WebDriver driver;
	
	@Given("^User is  on loginpage$")
	public void user_is_already_on_loginpage()  {
		System.setProperty("webdriver.chrome.driver", "E:\\Eclipse All versions\\Workspace\\CucumberTestNG\\Drivers\\chromedriver.exe");
		   driver=new ChromeDriver();
		   driver.manage().window().maximize();
		   driver.get("https://opensource-demo.orangehrmlive.com/");
		
	}

	@When("^Title is OrangeHRM$")
	public void title_is_OrangeHRM()  {
		String title=driver.getTitle();
		if(title.equals("OrangeHRM")) {
			System.out.println("Title Verified");
		}
	    
	}

	@Then("^User enters  valid username and valid password$")
	public void user_enters_valid_username_and_valid_password(DataTable mapcredentials) throws InterruptedException  {
	    for(Map<String,String>data1:mapcredentials.asMaps(String.class, String.class)) {
	    	driver.findElement(By.name("txtUsername")).sendKeys(data1.get("username"));
	    	Thread.sleep(4000);
	    	driver.findElement(By.name("txtPassword")).sendKeys(data1.get("password"));
	    	Thread.sleep(4000);
	    	driver.findElement(By.name("txtUsername")).clear();
	    	driver.findElement(By.name("txtPassword")).clear();
	    }
	}

	@Then("^Closing browser$")
	public void tear_browser()  {
	  driver.close();
	}
}
