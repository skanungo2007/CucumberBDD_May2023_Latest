package stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import base.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.FundTransferPage;
import pages.HomePage;
import pages.NewAccountPage;
import pages.NewCustomerPage;

public class Steps extends BaseClass {
	
	
	private static Logger log = LogManager.getLogger(Steps.class);
	private static String managerId;
	private static String customerId;
	private static String accountType;
	private static String accountNumber;
	
	
	private HomePage getHomePage() {
		
		HomePage home = new HomePage(driver);
		return home;
	}
	
	private DashboardPage getDashboardPage() {
		
		DashboardPage dashboard = new DashboardPage(driver);
		return dashboard;
	}
	
	private NewCustomerPage getNewCustomerPage() {
		
		NewCustomerPage customer = new NewCustomerPage(driver);
		return customer;
	}
	
	
	private NewAccountPage getNewAccountPage() {
		
		NewAccountPage account = new NewAccountPage(driver);
		return account;
	}
	
	
	private FundTransferPage getFundTransferPage() {
		
		FundTransferPage fund = new FundTransferPage(driver);
		return fund;
	}
	
	
	
	
	
	
	
	//----------Background Steps----------------
	
	@Given("I access the link and verify the homepage")
	public void i_access_the_link_and_verify_the_homepage() {
	   
		getHomePage().verifyHomePage();
		
	}
	
	@When("I verify the banner")
	public void i_verify_the_banner() {
	   
		log.info(getHomePage().getBanner());
	}
	
	@Then("I enter the credentials and click login button")
	public void i_enter_the_credentials_and_click_login_button() throws IOException, InterruptedException, ExecutionException {
	   
		getHomePage().enterCredentials();
		
	}
	
	
	
	//------------------- Smoke Test Steps ------------------------
	
	
	@And("I check that a welcome message is shown to the user")
	public void i_check_that_a_welcome_message_is_shown_to_the_user() {
	    
		getDashboardPage().showWelcomeMessage();
	}
	
	
	@And("I verify that the manager id is displayed")
	public void i_verify_that_the_manager_id_is_displayed() {
	   
		managerId = getDashboardPage().getManagerId();
		log.info("ID: " + managerId);
	}
	
	
	@And("I logout from the account")
	public void i_logout_from_the_account() throws InterruptedException, ExecutionException {
	    
		getDashboardPage().logout();
	}


	//--------------- 1st Regression Steps ------------------------
	

	@And("I take a list of all the links and verify that links are enabled")
	public void i_take_a_list_of_all_the_links_and_verify_that_links_are_enabled() {
	    
		getDashboardPage().verifyLinks();
	}
	
	
	
	//----------------- 2nd Regression Steps -----------------------
	
	
	@And("I click on New Customer Link and verify the page")
	public void i_click_on_new_customer_link_and_verify_the_page() throws InterruptedException, ExecutionException {
	    
		getDashboardPage().navigateToCustomerPage();
		getNewCustomerPage().verifyCustomerPage();
	}
	
	
	
	@And("I fill up all the details like name, gender, dob, address, city, state, pin, mobile, email, password and then click submit button")
	public void i_fill_up_all_the_details_like_name_gender_dob_address_city_state_pin_mobile_email_password_and_then_click_submit_button(DataTable dataTable) {
	   
		List<List<String>> data = dataTable.asLists(String.class);
		
		getNewCustomerPage().createNewCustomer(data.get(0).get(0), data.get(0).get(1), data.get(0).get(2), data.get(0).get(3), data.get(0).get(4), data.get(0).get(5), data.get(0).get(6), data.get(0).get(7), data.get(0).get(8), data.get(0).get(9));
	}
	
	
	
	@And("I fetch all the details and note the customer id")
	public void i_fetch_all_the_details_and_note_the_customer_id() {
	   
		System.out.println();
		customerId = getNewCustomerPage().getAccountDetails();
		log.info("Customer ID: " + customerId);
	}
	
	
	
	@And("I continue to dashboard page")
	public void i_continue_to_dashboard_page() throws InterruptedException, ExecutionException {
	   
		getNewCustomerPage().navigateToDashboard();
	}

	
	//------------------ 3rd Regression Steps -----------------------
	
	
	@And("I then click New Account link and verify the page")
	public void i_then_click_new_account_link_and_verify_the_page() {
	    
		getDashboardPage().navigateToAccountPage();
	}
	
	
	@And("I fill in the details like Customer Id, {string} and {string} and click on submit button")
	public void i_fill_in_the_details_like_customer_id_and_and_click_on_submit_button(String type, String amount) throws InterruptedException, ExecutionException {
	   
		getNewAccountPage().createAccount(customerId, type, amount);
	}
	
	
	@And("I fetch all the details and note the account id")
	public void i_fetch_all_the_details_and_note_the_account_id() {
	    
		String accountDetails[] = getNewAccountPage().getAccountDetails();
		
		accountType = accountDetails[0];
		accountNumber = accountDetails[1];
		
		log.info(accountType + " : " + accountNumber);
	}
	
	
	@And("I again continue to dashboard page")
	public void i_again_continue_to_dashboard_page() throws InterruptedException, ExecutionException {
	   
		getNewAccountPage().navigateToDashboard();
	   
	}
	
	
	//----------------- 4th Regression Steps -------------------------
	
	
	@And("I click on Fund Transfer Link and verify the page")
	public void i_click_on_fund_transfer_link_and_verify_the_page() {
	    
		getDashboardPage().navigateToFundTransferPage();
		getFundTransferPage().verifyFundTransferPage();
		
	}
	
	
	@And("I fill up the details FromAccount, BeneficiaryAccount, Amount and Description and click on Submit button")
	public void i_fill_up_the_details_from_account_beneficiary_account_amount_and_description_and_click_on_submit_button(DataTable dataTable) throws InterruptedException, ExecutionException {
	    
		List<Map<String,String>> map = dataTable.asMaps(String.class,String.class);
		getFundTransferPage().initiateTransfer(accountNumber, map);
	}
	
	
	@And("I fetch all the details")
	public void i_fetch_all_the_details() {
	    
		getFundTransferPage().getTransferDetails();
	}

	
	
}
