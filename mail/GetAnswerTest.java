package by.htp.library.mailpage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.library.mailpage.step.Steps;

public class GetAnswerTest {
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
	public void testGetAnswer() throws InterruptedException 
	{
		steps.loginMailRu(login, password);
		Assert.assertTrue(steps.checkReceiveLetter(emailReceiver, subjectField, textAnswer));
	}
	
	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}
}
