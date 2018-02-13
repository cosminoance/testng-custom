package rightmove.letting;

import org.testng.Assert;
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
	public void navigateToLandingPage(){
		// create a homepage
		_homePage = (HomePage) PageCreate.create(Pages.HomePage, browser);
		_filterPage = _homePage.performSearchForRent("NW3");
		//it can also be done directly
		//_filterPage = (ForSaleFilterPage) PageCreate.create(Pages.ForSaleFilterPage, browser);
		
		// example of using constants
		// these could be set within files, like json
		// then loaded at runtime in case of internationalization
		_filterPage.selectRadius(Radius.Within3Mile);
		// done with send keys
		_filterPage.selectMinPrice("150");
		// done with select by value
		_filterPage.selectMaxPrice("20000");
		_filterPage.selectMinBedrooms("1");
		_filterPage.selectMaxBedrooms("3");
		_filterPage.selectPropertyType(PropertyType.Houses);
		// used to much time to find a solution for this, not working
		_filterPage.selectAddedToSite(AddedSince.Last3Days);
		_filterPage.toggleIncludeLetAgreed();
		// submit form
		_resultsPage = _filterPage.SubmitSearch();
	}

	@Test(dependsOnMethods = { "navigateToLandingPage" })
	public void changeSortOrder() {		
		_resultsPage.sortResultsBy(ResultsPage.Sorting.LowestPrice.value());
	}

	@Test(dependsOnMethods = { "changeSortOrder" })
	public void openFirstNonFeatured(){
		_listingPage = _resultsPage.openNonSpecialListing(0);		
	}
	
	@Test(dependsOnMethods = { "openFirstNonFeatured" })
	public void assertListingPrice() throws InterruptedException {
		int price = _listingPage.getPriceRegion();
		Assert.assertTrue((price>150) && (price<20000),
				"Price of property is withing boundaries");
	}
	
	
}
