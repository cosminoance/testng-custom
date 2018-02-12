package browser;

import java.util.ArrayList;

import org.openqa.selenium.By;

/**
 * This class will be used to generate wild card by-s
 *
 */
public class WildCardBy {
	
	
	//example //div[@id='searchLayoutControls']/div//*[contains(text(),'%wildcard%')]/ancestor::div[1]
	public static By build(String locator, String wildcard) {
			return By.xpath(locator.replace("%wildcard%", wildcard));		
	}
}
