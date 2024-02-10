package pages;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import base.BaseClass;
import jdk.internal.org.jline.utils.Log;
import org.testng.Assert;
import utility.HelperClass;

public class DashboardPage extends BaseClass {

	
	@FindBy(how=How.XPATH, using="//marquee")
	WebElement welcomeMsg;
	
	@FindBy(how=How.XPATH, using="//table//following::table//tr[3]")
	WebElement mid;
	
	@FindBy(linkText="New Customer")
	WebElement customerCreation;
	
	@FindBy(linkText="New Account")
	WebElement accountCreation;
	
	@FindBy(linkText="Fund Transfer")
	WebElement fundTransfer;
	
	@FindBy(linkText="Log out")
	WebElement logOut;
	
	
	private static Logger log = LogManager.getLogger(DashboardPage.class);
	
	public DashboardPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void showWelcomeMessage() {
		
		log.info(welcomeMsg.getText());
	}
	
	public String getManagerId() {
		
		log.info(mid.getText());
		Assert.assertTrue(mid.isDisplayed(), "ID is not displayed");
		String id = HelperClass.getSubStringBySpace(mid.getText(), 3);
		return id;
		
	}
	
	public void verifyLinks() {
		
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		
		int i=0;
		for(WebElement e: allLinks) {
			
			e.isDisplayed();
			i++;
		}
		
		log.info("Links are enabled");
	}
	
	public void navigateToCustomerPage() {
		
		customerCreation.click();
	}
	
	public void navigateToAccountPage() {
		
		accountCreation.click();
	}
	
	public void navigateToFundTransferPage() {
		
		fundTransfer.click();
	}


	public void logout() throws InterruptedException, ExecutionException {
		
		logOut.click();
		Alert alert = driver.switchTo().alert();
		log.info(alert.getText());
		alert.accept();
		HelperClass.doPause(2000);
	}
}
