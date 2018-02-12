package browser;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserCreate {

	public BrowserCreate() {
		System.out.println("Testing:Starting Browser Factory");
	}

	// noob factory
	// no reflection
	public static RemoteWebDriver create(String browserName, boolean local) {
		RemoteWebDriver driver;
		if (local)
			switch (browserName) {
			case "chrome":
				// this area should have additional code in order
				// to allow remote web connection
				System.setProperty("webdriver.chrome.driver", "chromedriver");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();

				ChromeDriverService service = new ChromeDriverService.Builder()
				                            .usingAnyFreePort()
				                            .build();
				ChromeOptions options = new ChromeOptions();
				options.merge(capabilities);    
				 driver = new ChromeDriver(service, options);
				return driver;
			case "safari":
				 driver = new SafariDriver();
				return driver;
			default:
				System.out.println("Unrecognised browser");
				break;
			}
		return null;
	}

}
