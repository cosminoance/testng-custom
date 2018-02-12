package rightmove;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import browser.Browser;
import browser.BrowserCreate;
import constants.Constants;
import test.BasicSeleniumTest;

//additional annotations/attributes could be created
//to allow for parameters for custom browser (not the global maven/jenkins browser)
//for a specific test out of the suite
//maybe through extending DataProvider?
public class RightMoveSeleniumTest extends BasicSeleniumTest {

	@BeforeClass
	public void SetUp() {
		browser = new Browser("chrome", true);
		
		// we can wrap the browser instance in a new class with specific actions
		// but this was faster to write
		browser.get(Constants.LandingPage);

	}

	@AfterClass
	public void CleanUp() {
		browser.close();
	}

}
