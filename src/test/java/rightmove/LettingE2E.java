package rightmove;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import constants.AddedSince;
import constants.Pages;
import constants.PropertyType;
import constants.Radius;
import page.PageCreate;
import rightmove.SearchFilterPage;
import rightmove.HomePage;
import rightmove.ListingPage;
import rightmove.ResultsPage;
import rightmove.RightMoveSeleniumTest;

public class LettingE2E extends RightMoveSeleniumTest {

	private HomePage _homePage;
	private SearchFilterPage _filterPage;
	private ResultsPage _resultsPage;
	private ListingPage _listingPage;

	@Test
	public void navigatePerformSearch() {
		// create a homepage
		_homePage = (HomePage) PageCreate.create(Pages.HomePage, browser);
		_filterPage = _homePage.performSearchForRent("NW3");
		// it can also be done directly
		// _filterPage = (ForSaleFilterPage) PageCreate.create(Pages.ForSaleFilterPage,
		// browser);
	}

	@Test(dependsOnMethods = { "navigatePerformSearch" })
	@Parameters({ "minPrice", "maxPrice" })
	public void addFilters(String minPrice, String maxPrice) {
		// example of using constants
		// these could be set within files, like json
		// then loaded at runtime in case of internationalization
		_filterPage.selectRadius(Radius.Within3Mile);
		// done with send keys
		_filterPage.selectMinPrice(minPrice);
		// done with select by value
		_filterPage.selectMaxPrice(maxPrice);
		_filterPage.selectMinBedrooms("1");
		_filterPage.selectMaxBedrooms("3");
		_filterPage.selectPropertyType(PropertyType.Houses);
		// used to much time to find a solution for this, not working
		_filterPage.selectAddedToSite(AddedSince.Last3Days);
		_filterPage.toggleIncludeLetAgreed();
		// submit form
		_resultsPage = _filterPage.SubmitSearch();
	}

	@Test(dependsOnMethods = { "addFilters" })
	public void changeSortOrder() {
		_resultsPage.sortResultsBy(ResultsPage.Sorting.LowestPrice.value());

		//
		// Assert that properties on first page are listed in order based on price field
	}

	@Test(dependsOnMethods = { "changeSortOrder" })
	public void openFirstNonFeatured() {
		_listingPage = _resultsPage.openNonSpecialListing(0);
	}

	@Test(dependsOnMethods = { "openFirstNonFeatured" })
	public void assertListingPrice() throws InterruptedException {
		int price = _listingPage.getPriceRegion();
		Assert.assertTrue((price > 150) && (price < 20000), "Price of property is withing boundaries");

		//
		// Other asserts here
	}
}
