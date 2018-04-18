package by.htp.library.mailpage.step;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import by.htp.library.example.mailru.SendLetterTest;
import by.htp.library.mailpage.driver.DriverSingleton;
import by.htp.library.mailpage.page.InboxLettersPage;
import by.htp.library.mailpage.page.LoginPage;
import by.htp.library.mailpage.page.NewLetterPage;
import by.htp.library.mailpage.page.UserPage;

public class Steps {

	private WebDriver driver;
	private UserPage userPage;
	private NewLetterPage newLetterPage;
	private InboxLettersPage inboxLettersPage;
	private WebDriverWait driverWait;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser() {
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver() {
		DriverSingleton.closeDriver();
	}

	public void loginMailRu(String login, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(login, password);
	}

	public String checkProfileName(String username) {
		LoginPage loginPage = new LoginPage(driver);
		String actualUsername = loginPage.getLoggedInUserName();
		return actualUsername;
	}

	public void writeNewLetter() {
		userPage = new UserPage(driver);
		userPage.createNewLetter();

	}

	public void sendEmail(String email, String subj, String textLetter) {
		newLetterPage = new NewLetterPage(driver);
		newLetterPage.writeLetter(email, subj, textLetter);

		userPage = new UserPage(driver);
		userPage.viewSentLetters();
	}

	public String checkSentEmail() {
		userPage = new UserPage(driver);
		List<WebElement> elements = userPage.getListSentLetters();
		String lastMail = null;
		for (WebElement wElement : elements) {
			if (wElement.isDisplayed()) {
				lastMail = wElement.getText();
				System.out.println(lastMail);
				break;
			}
		}
		return lastMail;
	}

	public boolean checkReceiveLetter(String emailReceiver, String subjectField, String textEmail) throws InterruptedException {
		inboxLettersPage = new InboxLettersPage(driver);
		inboxLettersPage.viewIncomingLetters();
		List<WebElement> elements = inboxLettersPage.getListIncomingLetters();
		for (int i = 0; i <=elements.size(); i++) {
			
			if (i == elements.size()) {
				nextPageLetters();
				elements = inboxLettersPage.getListIncomingLetters();
				i = 0;
			}

			if (elements.get(i).isDisplayed()) {
				elements.get(i).click();
				inboxLettersPage.checkAddress();

				if (inboxLettersPage.getEmailFrom().equals(emailReceiver)
						&& inboxLettersPage.getSubject().equals(subjectField)) {
					reply(textEmail);
					return true;

				} else {
					driver.navigate().back();
					elements = inboxLettersPage.getListIncomingLetters();
				}
			}
		}
		return false;
	}

	public void reply(String textEmail) {

		newLetterPage = new NewLetterPage(driver);
		newLetterPage.writeTextLetter(textEmail);
	}
	
	public void nextPageLetters() throws InterruptedException  {
		inboxLettersPage.nextPage();
		Thread.sleep(1000);
	}
}
