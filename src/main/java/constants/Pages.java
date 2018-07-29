package constants;

import page.PageCreate;

public class Pages {
	
	public static void addPage (String pageName, Class pageClass) {
		PageCreate.register(pageName, pageClass);
	}
}
