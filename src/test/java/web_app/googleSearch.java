package web_app;

import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class googleSearch {
	AndroidDriver<MobileElement> driver;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S6");
		capabilities.setCapability("clearSystemFiles", true);
		URL SeleniumGridURL = new URL("http://localhost:4723/wd/hub");
		this.
		driver = new AndroidDriver<MobileElement>(SeleniumGridURL, capabilities);
	}
	
	@Test
	public void googleSearchTest() throws Exception {
		String googleURL ="https://google.com.vn";
		System.out.println("STEP 1: Navigate to URL " + googleURL);
		driver.get(googleURL);

		System.out.println("STEP 2: Search for \"appium\"");
		driver.findElement(By.name("q")).sendKeys("appium");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		System.out.println("STEP 3: click on the first link");
		WebElement firstLink = driver.findElement(By.xpath("//*[text()='Search Results']/following::a[1]"));
		firstLink.click();
		Thread.sleep(5000);
		
		String expectedURL = "http://appium.io/";
		String actualURL = driver.getCurrentUrl();
		System.out.println("STEP 4: Verify the URL should be " + actualURL);
		Assert.assertTrue("Actual: " + actualURL + " is different from " + expectedURL,actualURL.contains(expectedURL));
		Thread.sleep(5000);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
