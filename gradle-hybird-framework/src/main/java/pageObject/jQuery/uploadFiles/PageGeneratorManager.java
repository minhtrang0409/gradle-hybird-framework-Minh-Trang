package pageObject.jQuery.uploadFiles;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageObject getHomePageObject (WebDriver driver) {
		return new HomePageObject(driver);
	}
}