package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage {

	@FindBy(id="txtUsername")
	WebElement userText;
	
	
	@FindBy(id="txtPassword")
	WebElement passwordText;

	
	@FindBy(id="btnLogin")
	WebElement clickBtn;
	
	public AdminLoginPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	
	

	public void enteruserName(String uname) {
		userText.sendKeys(uname);
	}
	
	public void enteruserPassword(String upwd) {
		passwordText.sendKeys(upwd);
	}

   public void clickButton() {
	clickBtn.click();
    }



}
