package native_app_demo;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class PlaystoreApp_Demo {
	AndroidDriver<MobileElement> driver;
	
	@Before
	public void setup() throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung G 7");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
				"com.android.vending.AssetBrowserActivity");
		URL seleniumGridURL = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(seleniumGridURL, capabilities);
	}

	@Test
	public void PlayStore() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("STEP 1: Click on search bar");
		MobileElement searchBar = driver.findElementById("com.android.vending:id/search_bar_hint");
		searchBar.click();

		System.out.println("STEP 2: input search text ");
		Thread.sleep(3000);
		MobileElement searchInput = driver.findElementById("com.android.vending:id/search_bar_text_input");
		searchInput.sendKeys("skype - free");

		Thread.sleep(5000);
		System.out.println("STEP 3: click on the first result");
		MobileElement firstEle = driver.findElementByXPath(
						"//android.widget.LinearLayout[@index=0]/android.widget.LinearLayout[@resource-id='com.android.vending:id/text_frame']");
		firstEle.click();

		Thread.sleep(3000);
		System.out.println("verify if the first result is Skype app");
		MobileElement appTitle = driver.findElementById("com.android.vending:id/title_title");

		System.out.println("App title " + appTitle.getText());
		Assert.assertTrue("Failed to verify App title", appTitle.getText().equals("Skype - free IM & video calls"));
	}
	
	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
}
