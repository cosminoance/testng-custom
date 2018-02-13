package rightmove;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import browser.Browser;
import constants.Pages;
import page.PageCreate;
import page.PageTemplate;


public class HomePage extends PageTemplate {

	public HomePage(Browser browser) {
		super(browser);
		init();
	}

	//I would create a custom how 
	//this means that the factory would be able to wait on the elements being created
	//lack of time means that for further page objects, I will use a custom page object implementation
	//that I developed over the years
	@FindBy(id="searchLocation")
	WebElement InputSearhBox;
	@FindBy(id="buy")
	WebElement ButtonSearchForSale;
	@FindBy(id="rent")
	WebElement ButtonSearchForRent;

	/**
	 * Used to initialise FindBy elements
	 */
	public void init() {
		System.out.println(this.getClass().getName() + " initialising elements");
		//using PageFactory
		PageFactory.initElements(_driver.getBrowser(), this);

	}
	
	/**
	 * Perform a for sale search based on the input string
	 * @param srcValue input to search for
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public SearchFilterPage performSearchForSale(String srcValue) {
		InputSearhBox.clear();
		InputSearhBox.sendKeys(srcValue);
		ButtonSearchForSale.click();
		//wait on filter page to appear
		//this wouldn't be needed if I had the time to create a custom how locator
		//to use with FindBy
		return (SearchFilterPage)PageCreate.create(Pages.ForSaleFilterPage, _driver);
		
	}

	public SearchFilterPage performSearchForRent(String srcValue) {
		InputSearhBox.clear();
		InputSearhBox.sendKeys(srcValue);
		ButtonSearchForRent.click();
		//wait on filter page to appear
		//this wouldn't be needed if I had the time to create a custom how locator
		//to use with FindBy
		return (SearchFilterPage)PageCreate.create(Pages.ForSaleFilterPage, _driver);
	}

}
