package pages;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;
import utility.HelperClass;

public class FundTransferPage extends BaseClass {

	
	@FindBy(name="payersaccount")
	WebElement fromAccount;
	
	@FindBy(name="payeeaccount")
	WebElement beneficiary;
	
	@FindBy(name="ammount")
	WebElement amount;
	
	@FindBy(name="desc")
	WebElement description;
	
	@FindBy(xpath="//h2//following::table[2]")
	WebElement transferDetails;
	
	@FindBy(xpath="//input[@value='Submit']")
	WebElement submitBtn;
	
	
	private static Logger log = LogManager.getLogger(FundTransferPage.class);
	
	public FundTransferPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyFundTransferPage() {
		
		String expectedTitle = "Guru99 Bank Fund Transfer Entry Page";
		String actualTitle = driver.getTitle().trim();
		Assert.assertEquals(expectedTitle, actualTitle);
		log.info("Fund Transfer Page verified");
		
	}
	
	public void initiateTransfer(String frmAct, List<Map<String,String>> map) throws InterruptedException, ExecutionException {
		
		fromAccount.sendKeys(frmAct);
		
		for(Map<String, String> m: map) {
			
			beneficiary.sendKeys(m.get("BeneficiaryAccount"));
			amount.sendKeys(m.get("Amount"));
			description.sendKeys(m.get("Description"));
		}
		
		submitBtn.click();
		HelperClass.doPause(2000);
	}
	
	public void getTransferDetails() {
		
		log.info("Fund Transfer Details");
		log.info(transferDetails.getText());
	}
}
