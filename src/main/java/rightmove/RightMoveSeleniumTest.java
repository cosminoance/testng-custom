package rightmove;

import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import browser.Browser;
import constants.Constants;
import constants.Pages;
import page.PageCreate;
import test.BasicSeleniumTest;

//additional annotations/attributes could be created
//to allow for parameters for custom browser (not the global maven/jenkins browser)
//for a specific test out of the suite
//maybe through extending DataProvider?
public class RightMoveSeleniumTest extends BasicSeleniumTest {
	
	@BeforeSuite
	public void setupSuite() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// we need to register the pages in the factory before using them in the test
		PageCreate.register(Pages.HomePage, HomePage.class);
		PageCreate.register(Pages.ForSaleFilterPage, SearchFilterPage.class);
		PageCreate.register(Pages.ResultsPage, ResultsPage.class);
		PageCreate.register(Pages.ListingPage, ListingPage.class);

	}
	
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
