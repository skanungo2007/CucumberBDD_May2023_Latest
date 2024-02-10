Feature: Smoke Test

	Background:
		Given I access the link and verify the homepage
		When I verify the banner
		Then I enter the credentials and click login button


	@Smoke
	Scenario: Check Login Function
		And I check that a welcome message is shown to the user
		And I verify that the manager id is displayed
		And I logout from the account