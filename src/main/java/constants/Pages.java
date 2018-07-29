package constants;

import page.PageCreate;
import rightmove.HomePage;

public class Pages {
	
	public static void addPage (String pageName, Class pageClass) {
		PageCreate.register(pageName, pageClass);
	}
}
