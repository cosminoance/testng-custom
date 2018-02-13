package rightmove;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import browser.Browser;
import constants.Pages;
import page.PageCreate;
import page.PageTemplate;

public class ForSaleFilterPage extends PageTemplate {

	public ForSaleFilterPage(Browser browser) {
		super(browser);
		// TODO Auto-generated constructor stub
	}

	// ELEMENTS
	By SelectRadius = By.id("radius");
	By SelectMinPrice = By.id("minPrice");
	By MaxPrice = By.id("maxPrice");
	By SelectMinBedrooms = By.id("minBedrooms");
	By SelectMaxBedrooms = By.id("maxBedrooms");
	By SelectPropertyType = By.id("displayPropertyType");
	By SelectAddedToSite = By.id("maxDaysSinceAdded");
	By CheckBoxIncludeSSTC = By.xpath("//label[@for='includeSSTC']");
	By NonActing = By.id("headerTitle");

	By ButtonSubmitSearchForm = By.xpath("//div[contains(@class, 'submit' )and .//button[@id='submit']]");



	public void init() {
		System.out.println(this.getClass().getName() + " initialising elements");
	}

	public void selectRadius(String radius) {
		_driver.getSelect(SelectRadius).selectByValue(radius);
	}

	public void selectMinPrice(String minPrice) {
		_driver.getSelect(SelectMinPrice).selectByValue(minPrice);
		// _driver.getElement(SelectMinPrice).sendKeys(minPrice);
	}

	public void selectMaxPrice(String maxPrice) {
		_driver.getSelect(MaxPrice).selectByValue(maxPrice);
		// for safari
		// _driver.getElement(MaxPrice).sendKeys(maxPrice);
	}

	public void selectMinBedrooms(String minBedrooms) {
		 _driver.getSelect(SelectMinBedrooms).selectByValue(minBedrooms);
		//_driver.getElement(SelectMinBedrooms).sendKeys(minBedrooms);
	}

	public void selectMaxBedrooms(String maxBedrooms) {
		_driver.getSelect(SelectMaxBedrooms).selectByValue(maxBedrooms);
		//_driver.getElement(SelectMaxBedrooms).sendKeys(maxBedrooms);
	}

	public ResultsPage SubmitSearch() {
		_driver.clickElement(ButtonSubmitSearchForm);
		return (ResultsPage) PageCreate.create(Pages.ResultsPage, _driver);
	}

	public void selectPropertyType(String propertyType) {
		_driver.getSelect(SelectPropertyType).selectByValue(propertyType);
		//_driver.getElement(SelectPropertyType).sendKeys(propertyType);
	}
	
	public void selectAddedToSite(String added) {
		_driver.getSelect(SelectAddedToSite).selectByValue(added);
		
	}
			
	/**
	 * toggles the Include Under Offer, Sold STC... checkbox
	 * @param checked
	 */
	public void toggleIncludeUnderSSTC() {
		WebElement el = _driver.getElement(CheckBoxIncludeSSTC);
		el.click();
		//some tries based on another xpath
//		if(el.isSelected()!=checked) {
//			el.findElement(By.xpath("following-sibling::label")).click();
//		}
	}

}
