package by.htp.library.mailpage.page;

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

public class LoginPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();
	private final String BASE_URL = "https://mail.ru";
	private WebDriverWait driverwait;

	@FindBy(id = "mailbox:login")
	private WebElement inputLogin;

	@FindBy(id = "mailbox:password")
	private WebElement inputPassword;

	@FindBy(id = "mailbox:submit")
	private WebElement buttonSubmit;
	
	@FindBy(id = "PH_user-email")
	private WebElement linkLoggedInUser;

	public LoginPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
		logger.info("Login page opened");
	}

	public void login(String login, String password) 
	{ 
		inputLogin.sendKeys(login);
		inputPassword.sendKeys(password);
		buttonSubmit.click();
	
		driverwait = new WebDriverWait(driver, 200);
		driverwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PH_user-email")));
		
		logger.info("Login performed");

	}

	public String getLoggedInUserName()
	{
		return linkLoggedInUser.getText();
	}

}
