package page;

import browser.Browser;

/**
 * Every component can be a page or a component on a page
 * They all have their own instance of browser driver
 * @author Cosmin Oance: cosminoance@icloud.com / cosmin.oance@gmail.com / cosmin_oance@yahoo.com
 *
 */
public abstract class Component implements Page{

	protected Browser _driver;
	
	public Component(Browser browser) {
		_driver = browser;
		System.out.println("Generating");
	}

}
