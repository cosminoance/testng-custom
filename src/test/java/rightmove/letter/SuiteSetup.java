package rightmove.letter;

import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.BeforeSuite;

import constants.Pages;
import page.PageCreate;
import rightmove.HomePage;

//used to add needed pages before tests in the suite start
public class SuiteSetup{
	@BeforeSuite
	public void setup() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		PageCreate.register(Pages.HomePage, HomePage.class);
		
	}
}
