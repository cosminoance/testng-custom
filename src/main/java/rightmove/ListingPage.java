package rightmove;

import org.openqa.selenium.By;

import browser.Browser;
import page.PageTemplate;

public class ListingPage extends PageTemplate {

	public ListingPage(Browser browser) {
		super(browser);
	}	
	
	//ELEMENTS
	By LabelHeaderPrice = By.xpath("//p[@id='propertyHeaderPrice']//strong");
	
	
	
	
	public int getPriceRegion() {
		String price = _driver.getElement(LabelHeaderPrice).getText().replace("£", "").replace(",", "");
		return Integer.parseInt(price);
	}



	
	public void init() {
		System.out.println(this.getClass().getName() + " initialising elements");
	}

}
