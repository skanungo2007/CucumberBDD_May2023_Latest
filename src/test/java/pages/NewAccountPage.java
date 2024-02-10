package pages;

import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import base.BaseClass;
import utility.HelperClass;

public class NewAccountPage extends BaseClass {

	
	@FindBy(name="cusid")
	WebElement custId;
	
	@FindBy(name="selaccount")
	WebElement accountType;
	
	@FindBy(name="inideposit")
	WebElement depositAmount;
	
	@FindBy(xpath="//input[@value='submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//table[@id='account']/tbody")
	WebElement accountDetails;
	
	@FindBy(xpath="//table[@id='account']//tr[4]/td[2]")
	WebElement accountId;
	
	@FindBy(xpath="//table[@id='account']//tr[4]/td[1]")
	WebElement accountTypeName;
	
	@FindBy(xpath="//a[text()='Home']")
	WebElement continueToDashboard;
	
	
	private static Logger log = LogManager.getLogger(NewAccountPage.class);
	
	public NewAccountPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyNewAccountPage() {
		
		String expectedTitle = "Guru99 bank add new account";
		String actualTitle = driver.getTitle().trim();
		Assert.assertEquals(expectedTitle, actualTitle);
		log.info("New Account Page verified");
	}
	
	public void createAccount(String id, String type, String amount) throws InterruptedException, ExecutionException {
		
		custId.sendKeys(id);
		Select dropdown = new Select(accountType);
		dropdown.selectByVisibleText(type);
		depositAmount.sendKeys(amount);
		
		submitBtn.click();
		HelperClass.doPause(1000);
	}
	
	public String[] getAccountDetails() {
		
		log.info(accountDetails.getText());
		
		String acntDetails[] = {accountTypeName.getText(), accountId.getText()};
		return acntDetails;
	}
	
	public void navigateToDashboard() throws InterruptedException, ExecutionException {
		
		HelperClass.moveToElement(continueToDashboard);
		HelperClass.doPause(500);
		HelperClass.clickElement(continueToDashboard);
		HelperClass.doPause(1000);
	}
	
}
