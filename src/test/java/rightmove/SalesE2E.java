package rightmove;

import java.lang.reflect.InvocationTargetException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import browser.Browser;
import constants.AddedSince;
import constants.Constants;
import constants.Pages;
import constants.PropertyType;
import constants.Radius;
import page.PageCreate;
import rightmove.SearchFilterPage;
import rightmove.HomePage;
import rightmove.ListingPage;
import rightmove.ResultsPage;

/**
 * EXAMPLE OF ALL IN ONE TEST WITH DATA PROVIDER THIS WOULD BE BETTER SUITED FOR
 * MULTI VERIFICATION EASY TESTS
 */
public class SalesE2E {

	@Test(dataProvider = "AreaCodes")
	public void searchAndApplyFilters(String areaCode) {		
		Browser browser = new Browser("chrome", true);
		browser.get(Constants.LandingPage);
		// create a homepage
		HomePage _homePage = (HomePage) PageCreate.create(Pages.HomePage, browser);
		SearchFilterPage _filterPage = _homePage.performSearchForSale(areaCode);
		// it can also be done directly
		// _filterPage = (ForSaleFilterPage) PageCreate.create(Pages.ForSaleFilterPage,
		// browser);

		// example of using constants
		// these could be set within files, like json
		// then loaded at runtime in case of internationalization
		_filterPage.selectRadius(Radius.Within20Mile);
		// done with send keys
		_filterPage.selectMinPrice("50000");
		// done with select by value
		_filterPage.selectMaxPrice("10000000");
		_filterPage.selectMinBedrooms("2");
		_filterPage.selectMaxBedrooms("4");
		_filterPage.selectPropertyType(PropertyType.Houses);
		// used to much time to find a solution for this, not working
		_filterPage.selectAddedToSite(AddedSince.Last14Days);
		_filterPage.toggleIncludeUnderSSTC();
		// submit form
		ResultsPage _resultsPage = _filterPage.SubmitSearch();
		// demo
		_resultsPage.sortResultsBy(ResultsPage.Sorting.LowestPrice.value());
		_resultsPage.showResultsAs(ResultsPage.ShowAs.Grid.value());
		_resultsPage.showResultsAs(ResultsPage.ShowAs.List.value());
		_resultsPage.sortResultsBy(ResultsPage.Sorting.HighestPrice.value());
		// actual step
		_resultsPage.sortResultsBy(ResultsPage.Sorting.NewestListed.value());
		//
		// Assert that properties on first page are listed in order based on Added On
		// field
		ListingPage _listingPage = _resultsPage.openNonSpecialListing(0);
		int price = _listingPage.getPriceRegion();
		Assert.assertTrue((price > 50000) && (price < 10000000), "Price of property is withing boundaries");
		//close browser
		browser.close();
	}

	/**
	 * EXAMPLE OF DATA PROVIDER
	 * @return
	 */
	@DataProvider(name = "AreaCodes" , parallel = true)
	public static Object[][] data() {
		return new Object[][] { { "NW3" }, { "NW4" } };
	}

	/**
	 * We need to add the pages to the factory, easiest way to do it was here
	 * WE CAN SETUP A FLAG AND LOCK I THINK THAT ONLY REQUIRES THIS ONCE
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@BeforeClass
	public void setupSuite() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// we need to register the pages in the factory before using them in the test
		PageCreate.register(Pages.HomePage, HomePage.class);
		PageCreate.register(Pages.ForSaleFilterPage, SearchFilterPage.class);
		PageCreate.register(Pages.ResultsPage, ResultsPage.class);
		PageCreate.register(Pages.ListingPage, ListingPage.class);

	}

}
