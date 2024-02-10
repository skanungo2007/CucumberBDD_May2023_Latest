package pages;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;
import base.BaseClass;
import utility.HelperClass;

public class HomePage extends BaseClass {

	@FindBy(how=How.NAME, using="uid")
	@CacheLookup
	WebElement userName;
	
	@FindBy(how=How.NAME, using="password")
	@CacheLookup
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//h2")
	WebElement banner;
	
	
	private static Logger log = LogManager.getLogger(HomePage.class);
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyHomePage() {
		
		log.info(driver.getCurrentUrl());
		log.info(driver.getTitle());
		log.info("Home page verified");
	}
	
	public String getBanner() {
		
		return banner.getText();
	}
	
	public void enterCredentials() throws IOException, InterruptedException, ExecutionException {
		
		String id = HelperClass.readProperty("username");
		String pwd = HelperClass.decode("password");
		userName.sendKeys(id);
		password.sendKeys(pwd);
		loginBtn.click();
		HelperClass.doPause(500);

	}
	
	
}
