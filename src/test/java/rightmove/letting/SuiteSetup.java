package rightmove.letting;

import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.BeforeSuite;

import constants.Pages;
import page.PageCreate;
import rightmove.SearchFilterPage;
import rightmove.HomePage;
import rightmove.ListingPage;
import rightmove.ResultsPage;

//used to add needed pages before tests in the suite start
public class SuiteSetup {
	@BeforeSuite
	public void setup() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// we need to register the pages in the factory before using them in the test
		PageCreate.register(Pages.HomePage, HomePage.class);
		PageCreate.register(Pages.ForSaleFilterPage, SearchFilterPage.class);
		PageCreate.register(Pages.ResultsPage, ResultsPage.class);
		PageCreate.register(Pages.ListingPage, ListingPage.class);

	}
}
