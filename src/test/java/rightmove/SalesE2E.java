package rightmove;

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

public class SalesE2E extends RightMoveSeleniumTest {

	private HomePage _homePage;
	private SearchFilterPage _filterPage;
	private ResultsPage _resultsPage;
	private ListingPage _listingPage;

	@Test
	public void navigateToLandingPage(){
		// create a homepage
		_homePage = (HomePage) PageCreate.create(Pages.HomePage, browser);
		_filterPage = _homePage.performSearchForSale("NW3");
		//it can also be done directly
		//_filterPage = (ForSaleFilterPage) PageCreate.create(Pages.ForSaleFilterPage, browser);
		
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
		_resultsPage = _filterPage.SubmitSearch();
	}

	@Test(dependsOnMethods = { "navigateToLandingPage" })
	public void changeSortOrder() {
		//demo
		_resultsPage.sortResultsBy(ResultsPage.Sorting.LowestPrice.value());
		_resultsPage.showResultsAs(ResultsPage.ShowAs.Grid.value());
		_resultsPage.showResultsAs(ResultsPage.ShowAs.List.value());
		_resultsPage.sortResultsBy(ResultsPage.Sorting.HighestPrice.value());
		
		//actual step
		_resultsPage.sortResultsBy(ResultsPage.Sorting.NewestListed.value());
	}

	@Test(dependsOnMethods = { "changeSortOrder" })
	public void openFirstNonFeatured(){
		_listingPage = _resultsPage.openNonSpecialListing(0);		
	}
	
	@Test(dependsOnMethods = { "openFirstNonFeatured" })
	public void assertListingPrice() throws InterruptedException {
		int price = _listingPage.getPriceRegion();
		Assert.assertTrue((price>50000) && (price<10000000),
				"Price of property is withing boundaries");
	}
	
	
}
