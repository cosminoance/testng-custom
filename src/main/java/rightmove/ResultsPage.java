package rightmove;

import org.openqa.selenium.By;
import org.testng.Assert;

import browser.Browser;
import browser.WildCardBy;
import page.Page;

public class ResultsPage implements Page {

	Browser _driver;
	
	public ResultsPage(Browser browser) {
		_driver = browser;
		System.out.println("Generating homepage");
		init();
	}
	
	@Override
	public void init() {
		System.out.println(this.getClass().getName() + " initialising elements");
	}
	
	//example of constants at page level
	public static enum Sorting {
		HighestPrice("2"),
		LowestPrice("1"),
		NewestListed("6"),
		OldestListed("10");
		private final String value;
		private Sorting(String value) {
			this.value= value;
		}
		public String value() {
			return this.value;
		}
	}
	
	public static enum ShowAs {
		Grid("Grid"),
		List("List");
		private final String value;
		private ShowAs(String value) {
			this.value= value;
		}
		public String value() {
			return this.value;
		}
	}
	
	//ELEMENTS
	By SelectSortBy = By.id("sortType");
	//example of wildcard by used in show as method
	String ButtonShowAs = "//div[@id='searchLayoutControls']/div//*[contains(text(),'%wildcard%')]/ancestor::div[1]";
	
	//select sort order
	//and use waitOnUrlContains for it to finish
	public void sortResultsBy(String sortOrder) {
		_driver.getSelect(SelectSortBy).selectByValue(sortOrder);
		if(!sortOrder.equals(Sorting.HighestPrice.value()))
			Assert.assertTrue(_driver.waitOnUrlContains("sortType="+sortOrder), "Sorting as finished working");
		else
			Assert.assertFalse(_driver.waitOnUrlContains("sortType="));
	}
	
	/**
	 * Switches Grid or List Layout
	 * @param as
	 */
	public void showResultsAs(String as) {
		_driver.clickElement(WildCardBy.build(ButtonShowAs, as));
	}

}
