package by.htp.library.googlepage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.library.googlepage.step.Steps;

public class GoogleTest {

	private Steps steps;
	private String text = "Java";

	@BeforeMethod(description = "Init browser")
	public void setUp()
	{ 
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void testGoogleSearch()  {

		steps.google(text);
		String actualText = steps.find(text);
		assertEquals(actualText, text);
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}
}
