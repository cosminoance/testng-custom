package rightmove;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import browser.Browser;
import constants.Pages;
import page.Page;
import page.PageCreate;


public class HomePage implements Page {

	private Browser _driver;

	//I would create a custom how 
	//this means that the factory would be able to wait on the elements being created
	//lack of time means that for further page objects, I will use a custom page object implementation
	//that I developed over the years
	@FindBy(id="searchLocation")
	WebElement InputSearhBox;
	@FindBy(id="buy")
	WebElement ButtonSearchForSale;
	
	public HomePage(Browser browser) {
		_driver = browser;
		System.out.println("Generating homepage");
		init();
	}

	/**
	 * Used to initialise FindBy elements
	 */
	public void init() {
		System.out.println(this.getClass().getName() + " initialising elements");
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
	public ForSaleFilterPage performSearchForSale(String srcValue) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		InputSearhBox.sendKeys(srcValue);
		ButtonSearchForSale.click();
		//wait on filter page to appear
		//this wouldn't be needed if I had the time to create a custom how locator
		//to use with FindBy
		return (ForSaleFilterPage)PageCreate.create(Pages.ForSaleFilterPage, _driver);
		
	}

}
