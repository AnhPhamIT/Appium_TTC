package native_app_demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class PlaystoreApp_Demo {
	AndroidDriver<MobileElement> driver;

	@Before
	public void setup() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung G 7");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
				"com.android.vending.AssetBrowserActivity");
		URL seleniumGridURL = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(seleniumGridURL, capabilities);
	}

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


	public void PlayStore_gestureHandling() throws InterruptedException {
		TouchAction touchAction = new TouchAction(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("STEP 1: Open PlayStore");
		Thread.sleep(8000);

		System.out.println("STEP 2: Select \"Top Charts\"");
		MobileElement topCharts = driver.findElementByXPath("//*[@text='Top charts']");
		touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(topCharts))).perform();
		Thread.sleep(5000);

		System.out.println("STEP 3: Select the first app in \"Top Free Apps\" list");
		MobileElement firstApp = driver.findElementByXPath(
				"//*[@resource-id='com.android.vending:id/play_card'][@index=2]");
		firstApp.click();

		System.out.println("STEP 4. select \"Rating and reviews\" details");
		MobileElement ratingReviews = (MobileElement) driver
				.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
						+ ".resourceId(\"com.android.vending:id/recycler_view\")).scrollIntoView("
						+ "new UiSelector().text(\"Ratings and reviews\"));");
		ratingReviews.click();

		System.out.println("STEP 5. Sort reviews by Most Recent and Latest");
		Thread.sleep(2000);
		MobileElement sort_ele = driver
				.findElementByXPath("//android.widget.TextView[@resource-id='com.android.vending:id/sort_label']");
		sort_ele.click();

		Thread.sleep(2000);
		MobileElement mostRecent_ele = driver.findElementByXPath("//android.widget.RadioButton[@text='Most recent']");
		mostRecent_ele.click();

		MobileElement latestVersion_ele = driver.findElementByXPath(
				"//android.widget.CheckBox[@resource-id='com.android.vending:id/filter_by_version']");
		latestVersion_ele.click();

		MobileElement apply_ele = driver.findElementByXPath("//android.widget.TextView[@text='APPLY']");
		apply_ele.click();

		System.out.println("STEP 6: Print first comment, date, author for One star");
		MobileElement oneStars_ele = (MobileElement) driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()" + ".resourceId(\"com.android.vending:id/chip\")).scrollIntoView("
						+ "new UiSelector().text(\"1\"));");
		oneStars_ele.click();

		Thread.sleep(2000);
		List<MobileElement> firstReview_ele = driver.findElementsByXPath(
				"//android.widget.LinearLayout[@resource-id='com.android.vending:id/review_item_container']");
		MobileElement firstAuthor_ele = firstReview_ele.get(0).findElementById("com.android.vending:id/review_author");
		System.out.println("--- First Critial Author ----" + firstAuthor_ele.getText());
		MobileElement firstReviewDate_ele = firstReview_ele.get(0)
				.findElementById("com.android.vending:id/review_date");
		System.out.println("--- First Critial Review Date ----" + firstReviewDate_ele.getText());

		try {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("com.android.vending:id/review_content")));
			System.out.println("--- First negative Content ----"
					+ firstReview_ele.get(0).findElementById("com.android.vending:id/review_content").getText());
		} catch (Exception e) {
			System.out.println("First positive Content: no any comment");
		}

		System.out.println("STEP 7: Get product rating in Review and Rating screen");
		MobileElement reviewRating_ele = driver.findElementById("com.android.vending:id/li_rating");
		String expectedRating = (reviewRating_ele.getAttribute("content-desc").split(":")[1]).trim();
		System.out.println("Rating in Review and Rating screen: " + expectedRating);

		System.out.println("STEP 6: Go back to Product details and compare product average rating with previous step");
		MobileElement navigateBack_ele = driver.findElementByAccessibilityId("Navigate up");
		navigateBack_ele.click();

		Thread.sleep(2000);
		MobileElement averageRating_ele = driver.findElementById("com.android.vending:id/average_value");
		String actualRating = averageRating_ele.getText();
		System.out.println("Rating in App details screen: " + actualRating);

		System.out.println(
				"Verify Product Average Rating in Review screen should be matched with the one in Product details");
		Assert.assertTrue(actualRating.equals(expectedRating));

	}

	@Test
	public void PlayStore_gestureHandling_Enhanced() throws InterruptedException {
		TouchAction touchAction = new TouchAction(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("STEP 1: Open PlayStore");
		
		System.out.println("STEP 2: Select \"Top Charts\"");
		MobileElement topCharts = (MobileElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Top charts']")));
		touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(topCharts))).perform();
		
		System.out.println("STEP 3: Select the first app in \"Top Free Apps\" list");
		MobileElement firstApp = (MobileElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.android.vending:id/play_card'][@index=2]/*[@resource-id='com.android.vending:id/li_title']")));
		firstApp.click();
		

		System.out.println("STEP 4. select \"Rating and reviews\" details");
		MobileElement ratingReviews = (MobileElement) driver
		.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".resourceId(\"com.android.vending:id/recycler_view\")).scrollIntoView("
				+ "new UiSelector().text(\"Ratings and reviews\"));");
		ratingReviews.click();
		
		System.out.println("STEP 5. Sort reviews by Most Recent and Latest");
		MobileElement sort_ele = (MobileElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@resource-id='com.android.vending:id/sort_label']")));
		sort_ele.click();

		MobileElement mostRecent_ele = (MobileElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.RadioButton[@text='Most recent']")));
		mostRecent_ele.click();

		MobileElement latestVersion_ele = driver.findElementByXPath(
				"//android.widget.CheckBox[@resource-id='com.android.vending:id/filter_by_version']");
		latestVersion_ele.click();

		MobileElement apply_ele = driver.findElementByXPath("//android.widget.TextView[@text='APPLY']");
		apply_ele.click();
		
		System.out.println("STEP 6: Print first comment, date, author for One star");
		MobileElement oneStars_ele = (MobileElement) driver
		.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".resourceId(\"com.android.vending:id/chip\")).scrollIntoView("
				+ "new UiSelector().text(\"1\"));");
		oneStars_ele.click();

		List<MobileElement> firstReview_ele = driver.findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.android.vending:id/review_item_container']");
		MobileElement firstAuthor_ele = firstReview_ele.get(0).findElementById("com.android.vending:id/review_author");
		System.out.println("--- First Critial Author ----" + firstAuthor_ele.getText());
		MobileElement firstReviewDate_ele = firstReview_ele.get(0).findElementById("com.android.vending:id/review_date");
		System.out.println("--- First Critial Review Date ----" + firstReviewDate_ele.getText());
		System.out.println("--- First negative Content ----"
				+ firstReview_ele.get(0).findElementById("com.android.vending:id/review_content").getText());
		
		System.out.println("STEP 7: Get product rating in Review and Rating screen");
		MobileElement reviewRating_ele = driver.findElementById("com.android.vending:id/li_rating");
		String expectedRating = (reviewRating_ele.getAttribute("content-desc").split(":")[1]).trim();
		System.out.println("Rating in Review and Rating screen: " + expectedRating);
		
		
		System.out.println("STEP 8: Go back to Product details and compare product average rating with previous step");
		MobileElement navigateBack_ele = driver.findElementByAccessibilityId("Navigate up");
		navigateBack_ele.click();
		
		
		MobileElement averageRating_ele = (MobileElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.vending:id/average_value")));
		String actualRating = averageRating_ele.getText();
		System.out.println("Rating in App details screen: " + actualRating);
		
		System.out.println("Verify Product Average Rating in Review screen should be matched with the one in Product details");
		Assert.assertTrue(actualRating.equals(expectedRating));
		
	}
	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
}
