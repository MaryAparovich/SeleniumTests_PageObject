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

public class InboxLettersPage extends AbstractPage {
	private final Logger logger = LogManager.getRootLogger();
	private WebDriverWait driverWait = driverWait = new WebDriverWait(driver, 200);
;

	@FindBy(xpath = "//span[text()='Входящие']")
	private List<WebElement> incomingLetters;

	@FindBy(xpath = "//div[@class='b-datalist__item__date']")
	private List<WebElement> incomingLetter;

	@FindBy(xpath = "//span[@class='b-contact-informer-target js-contact-informer']")
	private WebElement address;

	@FindBy(xpath = "//div[@class='b-contact-informer__email']")
	private WebElement emailFrom;

	@FindBy(xpath = "//div[@class='b-letter__head__subj']")
	private WebElement subject;

	@FindBy(xpath = "//div[@data-name='next']")
	private WebElement nextPageBtn;
	
	@FindBy(xpath = "//span[@class='b-datalist__item__status-unread']")
	private List<WebElement> unreadLetter;
	
	
	public InboxLettersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {

	}

	public List<WebElement> getListIncomingLetters() {
		List<WebElement> listIncomingLetters = incomingLetter;
		return listIncomingLetters;
	}

	public void viewIncomingLetters() {
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Входящие']"))).click();
	}

	public void checkAddress() {
		driverWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='b-contact-informer-target js-contact-informer']")))
				.click();
	}

	public String getEmailFrom() {
		return emailFrom.getText();
	}
	
	public List<WebElement> getUnread() {
		List<WebElement> listUnreadLetters = unreadLetter;
		return listUnreadLetters;	
		}

	public String getSubject() {
		return subject.getText();
	}
	
	public void nextPage() throws InterruptedException {
		nextPageBtn.click();
	}
}
