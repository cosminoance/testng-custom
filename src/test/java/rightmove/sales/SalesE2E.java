package rightmove.sales;

import java.lang.reflect.InvocationTargetException;
import org.testng.Assert;
import org.testng.annotations.Test;

import constants.AddedSince;
import constants.Pages;
import constants.PropertyType;
import constants.Radius;
import page.PageCreate;
import rightmove.ForSaleFilterPage;
import rightmove.HomePage;
import rightmove.ResultsPage;
import rightmove.RightMoveSeleniumTest;

public class SalesE2E extends RightMoveSeleniumTest {

	private HomePage _homePage;
	private ForSaleFilterPage _filterPage;
	private ResultsPage _results;

	@Test
	public void navigateToLandingPage() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// create a homepage
		_homePage = (HomePage) PageCreate.create(Pages.HomePage, browser);
		_homePage.performSearchForSale("NW3");
		_filterPage = (ForSaleFilterPage) PageCreate.create(Pages.ForSaleFilterPage, browser);
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
		_filterPage.SubmitSearch();
	}

	@Test(dependsOnMethods = { "navigateToLandingPage" })
	public void changeSortOrder() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
		// pass control to results page
		_results = (ResultsPage) PageCreate.create(Pages.ResultsPage, browser);
		_results.sortResultsBy(ResultsPage.Sorting.LowestPrice.value());
		_results.showResultsAs(ResultsPage.ShowAs.Grid.value());
		// for observation purposes
		// Thread.sleep(10000);
		_results.showResultsAs(ResultsPage.ShowAs.List.value());
		_results.sortResultsBy(ResultsPage.Sorting.HighestPrice.value());
		// for observation purposes
		// Thread.sleep(10000);

	}

	@Test(dependsOnMethods = { "changeSortOrder" })
	public void openFirstNonFeatured() throws InterruptedException {
		_results.openNonSpecialListing(0);
		// for observation purposes
		Thread.sleep(10000);
	}
}
