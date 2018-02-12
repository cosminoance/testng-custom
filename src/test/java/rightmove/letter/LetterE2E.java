package rightmove.letter;

import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.Test;

import constants.Pages;
import page.PageCreate;
import rightmove.HomePage;
import rightmove.RightMoveSeleniumTest;



public class LetterE2E extends RightMoveSeleniumTest {
	
	HomePage homePage;
	@Test
	public void setup() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		
		//page factory can make tests look cleaner
		//and easier to write
		homePage = (HomePage)PageCreate.create(Pages.HomePage, browser);
		//homePage.saySomething();
	}
}
