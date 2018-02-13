package page;

import browser.Browser;

public abstract class PageTemplate implements Page{

	protected Browser _driver;
	
	public PageTemplate(Browser browser) {
		_driver = browser;
		System.out.println("Generating");
	}

}
