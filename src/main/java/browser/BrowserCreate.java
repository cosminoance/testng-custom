package browser;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import bsh.org.objectweb.asm.Constants;

public class BrowserCreate {

	public BrowserCreate() {
		System.out.println("Testing:Starting Browser Factory");
	}

	// noob factory
	// no reflection
	public static RemoteWebDriver create(String browserName, boolean local) {
		RemoteWebDriver driver;
		if (local) {
			switch (browserName) {
			case "chrome":
				// this area should have additional code in order
				// to allow remote web connection
				if(!constants.Constants.OS.equals("win"))
					System.setProperty("webdriver.chrome.driver", "chromedriver");
				else
					System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();

				ChromeDriverService service = new ChromeDriverService.Builder()
				                            .usingAnyFreePort()
				                            .build();
				ChromeOptions options = new ChromeOptions();
				options.merge(capabilities);    
				 driver = new ChromeDriver(service, options);
				return driver;			
			default:
				System.out.println("Unrecognised browser");
				break;
			}
		}
		else { //if remote
			//code for remote webdriver here
			//return new RemoteWebDriver(serviceAddress, DesiredCapabilities);
		}
		return null;
	}

}
