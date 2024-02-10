package hooks;

import java.io.IOException;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseClass {

	
	
	@Before
	public void openBrowser() throws IOException {
		
		BaseClass.setupBrowser();
	}
	
	
	@After
	public void quitBrowser() {
		
		BaseClass.close();
	}
}
