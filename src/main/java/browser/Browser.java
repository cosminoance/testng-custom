package browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {
	RemoteWebDriver browser;

	public RemoteWebDriver getBrowser() {
		return browser;
	}

	public Browser(RemoteWebDriver driver) {
		browser = driver;
	}

	public Browser(String driverName, boolean b) {
		browser = BrowserCreate.create(driverName, b);
		browser.manage().window().maximize();
	}

	public void get(String url) {
		browser.get(url);
	}

	public void close() {
		browser.close();
		browser.quit();
	}

	public WebElement getElement(String def, String locator) {
		By by;
		switch (def) {
		case "id":
			by = By.id(locator);
			break;

		default:
			by = By.xpath(locator);
			break;
		}
		WebDriverWait wait = new WebDriverWait(browser, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return browser.findElement(by);
	}

	public WebElement getElement(By by) {
		WebDriverWait wait = new WebDriverWait(browser, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return browser.findElement(by);
	}

	public Select getSelect(String def, String locator) {
		return new Select(getElement(def, locator));
	}

	public WebElement getElementById(String id) {
		return getElement("id", id);
	}

	public Select getSelectById(String id) {
		return getSelect("id", id);
	}

	public Select getSelect(By by) {
		return new Select(getElement(by));
	}
	
	public void clickElement(By by) {
		getElement(by).click();
	}

	public void setCheckbox(By checkbox, boolean b) {
		WebElement checkBox = getElement(checkbox);
		if(checkBox.isSelected() != b)
			checkBox.click();				
	}

	public boolean isSelected(By by) {
		WebElement el = getElement(by);
		return  el.isSelected();
		
	}
	
	public boolean waitOnUrlContains(String toContain) {
		WebDriverWait wait = new WebDriverWait(browser, 10);
		wait.until(new ExpectedCondition() {
			@Override
			public Object apply(Object arg0) {
				if(browser.getCurrentUrl().contains(toContain))
					return true;
				return false;
			}
		});
		return true;
	}

}
