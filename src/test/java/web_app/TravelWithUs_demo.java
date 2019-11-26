package web_app;

import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TravelWithUs_demo {
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
	public void test() throws InterruptedException{
		String appURL = "http://travelwithus.asia/";
		System.out.println("STEP 1: Navigate to URL " + appURL);
		driver.get(appURL);
		
		Thread.sleep(5000);
		System.out.println("STEP 2. Select \"Sign In\"");
		MobileElement menuEle = driver.findElementByXPath("//button[normalize-space()='Menu']");
		menuEle.click();
		
		Thread.sleep(2000);
		MobileElement signInEle = driver.findElementByXPath("//a[normalize-space()='Sign In']");
		signInEle.click();
		
		Thread.sleep(2000);
		System.out.println("STEP 3. Input email and password");
		MobileElement emailEle = driver.findElement(By.id("email"));
		emailEle.sendKeys("ptvanh@mailinator.com");
		
		Thread.sleep(2000);
		MobileElement passEle = driver.findElementById("pwd");
		passEle.sendKeys("123456");
		
		System.out.println("STEP 4. select Login button");
		MobileElement loginELe = driver.findElementByXPath("//button[text()='Login']");
		loginELe.click();
		
		menuEle = driver.findElementByXPath("//button[normalize-space()='Menu']");
		menuEle.click();
		
		MobileElement welcomeEle = driver.findElementByXPath("//a[normalize-space()='Welcome ptvanh@mailinator.com']");
		Assert.assertTrue(welcomeEle.isDisplayed());
	}
	
	@After
	public void tearDown() throws InterruptedException{
		Thread.sleep(5000);
		driver.quit();
	}
	
}
