package rightmove;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import browser.Browser;
import page.Page;

public class ForSaleFilterPage implements Page {

	private Browser _driver;

	// ELEMENTS
	By SelectRadius = By.id("radius");
	By SelectMinPrice = By.id("minPrice");
	By MaxPrice = By.id("maxPrice");
	By SelectMinBedrooms = By.id("minBedrooms");
	By SelectMaxBedrooms = By.id("maxBedrooms");
	By SelectPropertyType = By.id("displayPropertyType");
	By SelectAddedToSite = By.id("maxDaysSinceAdded");
	By CheckBoxIncludeSSTC = By.id("includeSSTC");
	By NonActing = By.id("headerTitle");

	By ButtonSubmitSearchForm = By.xpath("//div[contains(@class, 'submit' )and .//button[@id='submit']]");

	public ForSaleFilterPage(Browser browser) {
		_driver = browser;
	}

	public void init() {
		System.out.println(this.getClass().getName() + " initialising elements");
	}

	public void selectRadius(String radius) {
		_driver.getSelect(SelectRadius).selectByVisibleText(radius);
	}

	public void selectMinPrice(String minPrice) {
		// _driver.getBrowser().findElement(By.xpath("//div[@class='touchsearch-selectwrapper
		// touchsearch-minselectwrapper
		// touchsearch-searchcriteria-selectwrapper']/select[@id='minPrice']/option[@value='"+minPrice+"']")).click();
		// _driver.getSelect(MinPrice).selectByValue(minPrice);
		_driver.getElement(SelectMinPrice).sendKeys(minPrice);
	}

	public void selectMaxPrice(String maxPrice) {
		// _driver.getSelect(MaxPrice).selectByValue(maxPrice);
		_driver.getElement(MaxPrice).sendKeys(maxPrice);
	}

	public void selectMinBedrooms(String minBedrooms) {
		// _driver.getSelect(MinBedrooms).selectByVisibleText(minBedrooms);
		_driver.getElement(SelectMinBedrooms).sendKeys(minBedrooms);
	}

	public void selectMaxBedrooms(String maxBedrooms) {
		// _driver.getSelect(MaxBedrooms).selectByVisibleText(maxBedrooms);
		_driver.getElement(SelectMaxBedrooms).sendKeys(maxBedrooms);
	}

	public void SubmitSearch() {
		_driver.clickElement(ButtonSubmitSearchForm);
	}

	public void selectPropertyType(String propertyType) {
		_driver.getElement(SelectPropertyType).sendKeys(propertyType);

	}

	//not reliable
	//too much time being spent on this
	public void selectAddedToSite(String added) {
		_driver.getElement(SelectAddedToSite).sendKeys("Last" + Keys.SPACE + added);

	}

}
