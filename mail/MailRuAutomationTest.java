package by.htp.library.mailpage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.library.mailpage.step.Steps;

public class MailRuAutomationTest {
	private Steps steps;
	private final String login = "marusy.net@mail.ru";
	private final String password = "vfiekbnrf1893335";
	private final String emailReceiver = "altprint19@mail.ru";
	private final String subjectField = "Automatic test";
	private final String textEmail = "Hello!";
	private final String textAnswer = "Hello! I got your letter!";


	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void testAuthorization()  
	{
		steps.loginMailRu(login, password);
		String actualProfile = steps.checkProfileName(login);
		Assert.assertEquals(actualProfile, login);
	}
	 
	@Test 
	public void testSendEmail() 
	{
		steps.loginMailRu(login, password);
		steps.writeNewLetter();
		steps.sendEmail(emailReceiver, subjectField, textEmail);
		String actualSentLetter = steps.checkSentEmail();
		Assert.assertEquals(actualSentLetter, emailReceiver);
	}
	
	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}
}
