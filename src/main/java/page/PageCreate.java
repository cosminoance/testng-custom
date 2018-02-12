package page;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import browser.Browser;

public class PageCreate {

	private static HashMap<String, Class<?>> pages= new HashMap<String, Class<?>>();

	public PageCreate() {
		System.out.println("Starting Page Factory");
	}
	/**
	 * Registers a page object to the page repo (<code>HashMap< String, Page ></code>)
	 * @param name
	 * @param className
	 */
	public static void register(String name, Class<?> className) {
		pages.put(name, className);
		System.out.println("Registered product of"+className);
	}
	
	
	/**
	 * Will create a page based on name and it will also pass the
	 * browser to the constructor 
	 * @param pageName <code>String</code> Name of page
	 * @param browser <code>RemoteWebDriver</code> browser instance
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Page create(String pageName, Browser browser) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Class<?> pageClass = pages.get(pageName);
		System.out.print("Class to instantiate "+pageClass + "out of "+ pages.toString());
		Constructor<?> ctr = pageClass.getDeclaredConstructor(new Class[] {Browser.class  });
		return (Page) ctr.newInstance(browser);
	}

}
