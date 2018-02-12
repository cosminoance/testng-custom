package test;

import org.openqa.selenium.remote.RemoteWebDriver;

import browser.Browser;

//we want a selenium test to have a browser instance
//RemoteWebDriver used in case we want to use Grid, Browserstack or other remote delegation
public abstract class BasicSeleniumTest implements BasicTest {
	
	//browser instance will be tied to test instance
	public Browser browser;
	
}
