package testSuites;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import driversFactory.DriverFactory;
import io.qameta.allure.Description;
import pageObject.SubscriptionPageObject;
import utils.listeners.TestListner;

@Listeners(TestListner.class)
public class SubscriptionSuite extends SubscriptionPageObject {

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		DriverFactory df = new DriverFactory();
		driver = df.initializeDriver();
		navigateTo();
	}
	
	@Test(groups = {"CountryValidation"})
	@Description("Validate the Subscription Packages for Oman")
	public void validateSubscriptionPackageOman() throws Exception {
		validateSubscriptionOman();
	}
	
	@Test(groups = {"CountryValidation"})
	@Description("Validate the Subscription Packages for Algeria")
	public void validateSubscriptionPackageAlgeria() throws Exception {
		validateSubscriptionAlgeria();
	}
	
	@Test(groups = {"CountryValidation"})
	@Description("Validate the Subscription Packages for Tunisia")
	public void validateSubscriptionPackageTunisia() throws Exception {
		validateSubscriptionTunisia();
	}
	
	@Test(groups = {"CountryValidation"})
	@Description("Validate the Subscription Packages for Lebanon")
	public void validateSubscriptionPackageLebanon() throws Exception {
		validateSubscriptionLebanon();
	}
	
	@Test(groups = {"CountryValidation"})
	@Description("Validate the Subscription Packages for UAE")
	public void validateSubscriptionPackageUAE() throws Exception {
		validateSubscriptionUAE();
	}


	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
