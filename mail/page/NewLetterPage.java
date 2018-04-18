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

public class NewLetterPage extends AbstractPage {
	private final Logger logger = LogManager.getRootLogger();

	@FindBy(xpath = "//textarea[@data-original-name='To']")
	private WebElement emailReceiver;

	@FindBy(name = "Subject")
	private WebElement subjectField;

	@FindBy(xpath = "//iframe[@scrolling = 'auto']")
	private WebElement textFrame;

	@FindBy(xpath = "//body[@id='tinymce']")
	private WebElement textEmail;

	@FindBy(xpath = "//div[@data-name='send']")
	private WebElement sendLetterButton;

	@FindBy(xpath = "//span[@data-compose-act='reply']")
	private WebElement replyBtn;

	public NewLetterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {

	}

	public void writeLetter(String email, String subj, String textLetter) {

		emailReceiver.sendKeys(email);
		subjectField.sendKeys(subj);

		driver.switchTo().frame(textFrame);
		textEmail.sendKeys(textLetter);
		driver.switchTo().defaultContent();
		sendLetterButton.click();
	}

	public void writeTextLetter(String textLetter) {
		replyBtn.click();
		driver.switchTo().frame(textFrame);
		textEmail.sendKeys(textLetter);
		driver.switchTo().defaultContent();
		sendLetterButton.click();
	}

}
