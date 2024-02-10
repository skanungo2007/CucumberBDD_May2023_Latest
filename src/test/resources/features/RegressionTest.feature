Feature: Regression Test

	Background:
		Given I access the link and verify the homepage
		When I verify the banner
		Then I enter the credentials and click login button
		
	@Regression	
	Scenario: Validate the links
		And I take a list of all the links and verify that links are enabled
		And I logout from the account
		
		
	@Regression
	Scenario: Create new customers
		And I click on New Customer Link and verify the page
		And I fill up all the details like name, gender, dob, address, city, state, pin, mobile, email, password and then click submit button
			|	Carolina Watson	|	Female	|	12051992	|	1444 Srinagar Main Road	|	Kolkata	|	West Bengal	|	700084	|	9477007860	|	caroline@gmail.com	|	123*4	|
		And I fetch all the details and note the customer id
		And I continue to dashboard page
		And I logout from the account
	
	@Regression	
	Scenario Outline: Add accounts
		And I then click New Account link and verify the page
		And I fill in the details like Customer Id, "<AccountType>" and "<Deposit>" and click on submit button
		And I fetch all the details and note the account id
		And I again continue to dashboard page
		And I logout from the account
		
		Examples:
		|	AccountType	|	Deposit	|
		|	Savings		|	50000	|
		|	Current		|	200000	|
		
		
	@Regression
	Scenario: Fund Transfer
		And I click on Fund Transfer Link and verify the page
		And I fill up the details FromAccount, BeneficiaryAccount, Amount and Description and click on Submit button
			|	BeneficiaryAccount	| 	Amount	|	Description	|	
			|	132026				|	300		|	Deposit		|
		And I fetch all the details 
		And I logout from the account