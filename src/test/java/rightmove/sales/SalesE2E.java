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
import rightmove.RightMoveSeleniumTest;


public class SalesE2E extends RightMoveSeleniumTest {
	
	private HomePage _homePage;
	private ForSaleFilterPage _filterPage;
	
	@Test
	public void navigateToLandingPage() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {	
		
		//create a homepage
		_homePage = (HomePage)PageCreate.create(Pages.HomePage, browser);
		_homePage.performSearchForSale("NW3");
		_filterPage = (ForSaleFilterPage)PageCreate.create(Pages.ForSaleFilterPage, browser);
		//example of using constants
		//these could be set within files, like json
		//then loaded at runtime in case of internationalization
		_filterPage.selectRadius(Radius.Within20Mile);
		//done with send keys
		_filterPage.selectMinPrice("50000");
		//done with select by value
		_filterPage.selectMaxPrice("10000000");
		_filterPage.selectMinBedrooms("2");
		_filterPage.selectMaxBedrooms("4");
		_filterPage.selectPropertyType(PropertyType.Houses);
		//used to much time to find a solution for this, not working
		_filterPage.selectAddedToSite(AddedSince.Last14Days);
		_filterPage.toggleIncludeUnderSSTC();
		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_filterPage.SubmitSearch();
		
		
	}
}
