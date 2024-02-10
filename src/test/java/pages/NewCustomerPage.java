package pages;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import base.BaseClass;
import io.cucumber.datatable.DataTable;
import jdk.internal.org.jline.utils.Log;
import utility.HelperClass;

public class NewCustomerPage extends BaseClass {
	
	
	@FindBy(name="name")
	WebElement customerName;
	
	@FindBy(xpath="//input[@value='m']")
	WebElement male;
	
	@FindBy(xpath="//input[@value='m']")
	WebElement female;

	@FindBy(id="dob")
	WebElement dob;
	
	@FindBy(name="addr")
	WebElement address;
	
	@FindBy(name="city")
	WebElement city;
	
	@FindBy(name="state")
	WebElement state;
	
	@FindBy(name="pinno")
	WebElement pin;
	
	@FindBy(name="telephoneno")
	WebElement mobile;
	
	@FindBy(name="emailid")
	WebElement emailId;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@value='Submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//table[@id='customer']/tbody")
	WebElement registrationDetails;
	
	@FindBy(xpath="//table[@id='customer']//tr[4]/td[2]")
	WebElement custId;
	
	@FindBy(xpath="//a[text()='Home']")
	WebElement continueToDashboard;
	
	
	private static Logger log = LogManager.getLogger(NewCustomerPage.class);

	public NewCustomerPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyCustomerPage() throws InterruptedException, ExecutionException {
		
		HelperClass.doPause(1000);
		String expectedTitle = "Guru99 Bank New Customer Entry Page";
		String actualTitle = driver.getTitle().trim();
		Assert.assertEquals(expectedTitle, actualTitle);
		log.info("New Customer Page verified");
	}
	
	public void createNewCustomer(String name, String gen, String date, String adr, String cty, String st, String zip, String phn, String email, String pwd) {
		
		Random rand = new Random();
		int randNum = rand.nextInt(100000);
		String symbol = "@";
		String randomEmail = HelperClass.getSubStringBySymbol(email, symbol, 0) + randNum + "@" + HelperClass.getSubStringBySymbol(email, symbol, 1);
		
		
		customerName.sendKeys(name);
		if(gen.equalsIgnoreCase("female"))
			female.click();
		dob.sendKeys(date);
		address.sendKeys(adr);
		city.sendKeys(cty);
		state.sendKeys(st);
		pin.sendKeys(zip);
		mobile.sendKeys(phn);
		emailId.sendKeys(randomEmail);
		password.sendKeys(pwd);
		
		submitBtn.click();
		
		
	}
	
	public String getAccountDetails() {
		
		log.info(registrationDetails.getText());
		return custId.getText();
		
	}
	
	public void navigateToDashboard() throws InterruptedException, ExecutionException {
		
		HelperClass.moveToElement(continueToDashboard);
		HelperClass.doPause(500);
		HelperClass.clickElement(continueToDashboard);
		HelperClass.doPause(1000);
	}
}
