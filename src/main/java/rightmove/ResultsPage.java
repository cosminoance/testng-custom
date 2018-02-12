package rightmove;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
	By NonFeaturedResultSet = By.xpath("//div[contains(@class, 'searchResult') and not(div[contains(@class,'featured')])]");
	By NonPremiumResultSet = By.xpath("//div[contains(@class, 'searchResult') and not(div[contains(@class,'premium')])]");
	By SimpleResultSet = By.xpath("//div[contains(@class, 'searchResult') and not(div[contains(@class,'premium')]) and not(div[contains(@class,'featured')])]");
	By FeatureadResultSet = By.xpath("//div[contains(@class, 'searchResult') and (div[contains(@class,'featured')])]");
	By PremiumResultSet = By.xpath("//div[contains(@class, 'searchResult') and (div[contains(@class,'premium')])]");
	By SearchResultLoadingState = By.xpath("//div[contains(@class, 'searchLoading')]");
	//example of wildcard by used in show as method
	String ButtonShowAs = "//div[@id='searchLayoutControls']/div//*[contains(text(),'%wildcard%')]/ancestor::div[1]";
	
	//select sort order
	//and use waitOnUrlContains for it to finish
	public void sortResultsBy(String sortOrder) {
		_driver.getSelect(SelectSortBy).selectByValue(sortOrder);
		if(!sortOrder.equals(Sorting.HighestPrice.value()))
			Assert.assertTrue(_driver.waitOnUrlContains("sortType="+sortOrder), "Sorting as finished working");
		
	}
	
	/**
	 * Switches Grid or List Layout
	 * @param as
	 */
	public void showResultsAs(String as) {
		_driver.clickElement(WildCardBy.build(ButtonShowAs, as));
	}
	
	public List<WebElement> getNonSpecialResults(){
		return _driver.getElementsBy(SimpleResultSet);
	}
	
	/**
	 * Open a non special (non premium, non featured) listing
	 * @param index
	 */
	public void openNonSpecialListing(int index) {
		//keep this from opening listing before being available
		_driver.waitOnElementGone(SearchResultLoadingState, 3);
		//click on index from list
		getNonSpecialResults().get(index).click();
	}

}
