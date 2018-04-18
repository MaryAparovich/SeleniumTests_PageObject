package by.htp.library.mailpage.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();
	private WebDriverWait driverwait = driverwait = new WebDriverWait(driver, 200);
;
	
	@FindBy(linkText = "Написать письмо")
	private WebElement writeLetter;

	@FindBy(xpath = "//span[text()='Отправленные']")
	private WebElement sentLettersButton;
	
	@FindBy(xpath = "//div[@class='b-datalist__item__addr']")
	private List<WebElement> sentLetters;
	
	public UserPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	@Override
	public void openPage() {
		
	}
	 
	public void createNewLetter()
	{
		writeLetter.click();
	}
	
	public void viewSentLetters() 
	{
		driverwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Отправленные']"))).click();
	}
	
	public List<WebElement> getListSentLetters()
	{
		List<WebElement> listSentLetters = sentLetters;
		return listSentLetters;
	}

}
