package by.htp.library.googlepage.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends AbstractPage {
	private final Logger logger = LogManager.getRootLogger();
	private final String BASE_URL = "https://google.by";

	@FindBy(name = "q")
	private WebElement inputWord;
	

	public SearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override 
	public void openPage() {
		driver.navigate().to(BASE_URL);
		logger.info("Login page opened");
	}

	public void search(String word) {
		inputWord.sendKeys(word);
		inputWord.submit();
		logger.info("Login performed");
	}

	public String getWordOfSearch() {
		return inputWord.getAttribute("value");
	}

}
