package web_app;

import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TravelWithUs_demo {
	AndroidDriver<MobileElement> driver;
	String tripTitle = "Trip 01";
	String tripPlace = "Place 01";
	String memberNo = "5";

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S6");
		capabilities.setCapability("clearSystemFiles", true);
		URL SeleniumGridURL = new URL("http://localhost:4723/wd/hub");
		this.driver = new AndroidDriver<MobileElement>(SeleniumGridURL, capabilities);
	}


	public void login() throws InterruptedException {
		String appURL = "http://travelwithus.asia/";
		System.out.println("STEP 1: Navigate to URL " + appURL);
		driver.get(appURL);

		//Thread.sleep(5000);
		WebDriverWait wait=new WebDriverWait(driver, 30);
		System.out.println("STEP 2. Select \"Sign In\"");
		MobileElement menuEle=(MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Menu']")));
		//MobileElement menuEle = driver.findElementByXPath("//button[normalize-space()='Menu']");
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
		
		Thread.sleep(2000);
		menuEle = driver.findElementByXPath("//button[normalize-space()='Menu']");
		menuEle.click();

		MobileElement welcomeEle = driver.findElementByXPath("//a[normalize-space()='Welcome ptvanh@mailinator.com']");
		Assert.assertTrue(welcomeEle.isDisplayed());
	}

	public void a_createTrip() throws InterruptedException {

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

		System.out.println("STEP 1: Select Harmburger menu > Create Trip");
		menuEle = driver.findElementByXPath("//button[normalize-space()='Menu']");
		menuEle.click();

		Thread.sleep(3000);
		MobileElement createTripELe = driver.findElementByXPath("//a[text()='Create Trip']");
		createTripELe.click();

		System.out.println("STEP 2: Input trip information");
		Thread.sleep(3000);
		System.out.println("Input Trip title");
		MobileElement tripTitleEle = driver.findElementById("title");
		tripTitleEle.sendKeys(tripTitle);

		System.out.println("Input Trip Place");
		MobileElement placeEle = driver.findElementById("place");
		placeEle.sendKeys(tripPlace);

		System.out.println("Input Trip Start Date");
		Thread.sleep(3000);
		MobileElement startDateEle = driver.findElementById("start_date_val");
		startDateEle.click();
		MobileElement selectedStartDateEle = driver.findElementByXPath(
				"//div[contains(@class,'datetimepicker ')][1]/descendant::td[contains(@class,'day')][text()='15']");
		selectedStartDateEle.click();

		System.out.println("Input Trip End Date");
		Thread.sleep(3000);
		MobileElement endDateEle = driver.findElementById("end_date_val");
		endDateEle.click();
		MobileElement selectedEndDateEle = driver.findElementByXPath(
				"//div[contains(@class,'datetimepicker ')][2]/descendant::td[contains(@class,'day')][text()='29']");
		selectedEndDateEle.click();

		MobileElement memberEle = driver.findElementById("members");
		memberEle.sendKeys(memberNo);

		MobileElement submitEle = driver
				.findElementByXPath("//form[@name='tripForm']//descendant::button[text()='Submit']");
		submitEle.click();

		Thread.sleep(3000);
		Alert alertTrip = driver.switchTo().alert();
		System.out.println("-------------------------" + alertTrip.getText());
		Assert.assertTrue(alertTrip.getText().equals("A new trip has been created!"));
		alertTrip.accept();

		Thread.sleep(3000);
		MobileElement newAddedTrip = driver.findElement(By.xpath("//tr[td[1][text()='" + tripTitle
				+ "'] and td[2][text()='" + tripPlace + "'] and td[5][text()='" + memberNo + "']]"));
		Assert.assertTrue("New added trip has not been displayed in Trip table", newAddedTrip.isDisplayed());

	}

	public void b_EditTrip() throws InterruptedException {
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

		// way 1:
		// Actions actions = new Actions(driver);
		// actions.moveToElement(editTrip);
		// actions.perform();

		// way 2:
		Thread.sleep(3000);
		MobileElement editTrip = driver.findElementByXPath("//tr[td[1][text()='" + tripTitle + "'] and td[2][text()='"
				+ tripPlace + "']]/descendant::button[@title='Edit']");
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({behavior: 'auto',block: 'center',inline: 'center'});", editTrip);
		editTrip.click();

		tripTitle = "Trip 01 edited";
		tripPlace = "Place 01 edited";
		memberNo = "3";

		Thread.sleep(3000);
		System.out.println("Input Trip title");
		MobileElement tripTitleEle = driver.findElementById("title");
		tripTitleEle.clear();
		tripTitleEle.sendKeys(tripTitle);

		System.out.println("Input Trip Place");
		MobileElement placeEle = driver.findElementById("place");
		placeEle.clear();
		placeEle.sendKeys(tripPlace);

		System.out.println("Input Trip Start Date");
		MobileElement startDateEle = driver.findElementById("start_date_val");
		startDateEle.click();
		MobileElement selectedStartDateEle = driver.findElementByXPath(
				"//div[contains(@class,'datetimepicker ')][1]/descendant::td[contains(@class,'day')][text()='10']");
		selectedStartDateEle.click();

		System.out.println("Input Trip End Date");
		MobileElement endDateEle = driver.findElementById("end_date_val");
		endDateEle.click();
		MobileElement selectedEndDateEle = driver.findElementByXPath(
				"//div[contains(@class,'datetimepicker ')][2]/descendant::td[contains(@class,'day')][text()='30']");
		selectedEndDateEle.click();

		MobileElement memberEle = driver.findElementById("members");
		memberEle.sendKeys(memberNo);

		MobileElement submitEle = driver
				.findElementByXPath("//form[@name='tripForm']//descendant::button[text()='Submit']");
		submitEle.click();

		Alert alertTrip = driver.switchTo().alert();
		System.out.println("-------------------------" + alertTrip.getText());
		Assert.assertTrue(alertTrip.getText().equals("The trip has been updated!"));
		alertTrip.accept();

		Thread.sleep(3000);
		MobileElement newAddedTrip = driver.findElement(By.xpath("//tr[td[1][text()='" + tripTitle
				+ "'] and td[2][text()='" + tripPlace + "'] and td[5][text()='" + memberNo + "']]"));
		Assert.assertTrue("Got error when editting trip", newAddedTrip.isDisplayed());
	}
	@Test
	public void c_deleteTrip() throws InterruptedException{
		tripTitle="Trip 01 edited";
		tripPlace="Place 01 edited";
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
		
		Thread.sleep(3000);
		MobileElement deleteTrip = driver.findElementByXPath("//tr[td[1][text()='"+tripTitle+"'] and td[2][text()='"+tripPlace+"']]/descendant::button[@title='Delete']");
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({behavior: 'auto',block: 'center',inline: 'center'});", deleteTrip);
		deleteTrip.click();
		
		MobileElement okEle = driver.findElementByXPath("//button[text()='OK']");
		okEle.click();
		
		Alert alertTrip =driver.switchTo().alert();
		System.out.println("-------------------------"+alertTrip.getText());
		Assert.assertTrue(alertTrip.getText().equals("The trip has been deleted!"));
		alertTrip.accept();
		
		Thread.sleep(3000);
		java.util.List<MobileElement> newAddedTrip= driver.findElements(By.xpath("//tr[td[1][text()='"+tripTitle+"'] and td[2][text()='"+tripPlace+"'] and td[5][text()='"+memberNo+"']]"));
		if(newAddedTrip.size()<1)
			System.out.println("The trip has been deleted successfully");
		
	}

	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

}
