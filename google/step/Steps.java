package by.htp.library.googlepage.step;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import by.htp.library.git.page.LoginPage;
import by.htp.library.googlepage.driver.DriverSingleton;
import by.htp.library.googlepage.page.SearchPage;

public class Steps {

	private WebDriver driver;
	SearchPage page;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser()
	{
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver()
	{
		DriverSingleton.closeDriver();
	}

	public void google(String word)
	{ 
		page = new SearchPage(driver);
		page.openPage();
		page.search(word);
	}
	
	public String find(String word)
	{
		page = new SearchPage(driver);
		String actualWord = page.getWordOfSearch();
		logger.info("Actual word: " + actualWord);
		return actualWord;
	}
}
