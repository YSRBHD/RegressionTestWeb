package pageObject;

import java.util.List;
import org.openqa.selenium.By;
import org.testng.Assert;
import driversFactory.DriverFactory;

public class SubscriptionPageObject extends DriverFactory {

	// Object repository
	
	private By englishButton = By.xpath("//*[@id='translation-btn']");
	private By countryButton = By.xpath("//*[@id='country-btn']");
	private By countryButtonOman = By.xpath("//*[@id='om']");
	private By countryButtonAlgeria = By.xpath("//*[@id='dz']");
	private By countryButtonTunisia = By.xpath("//*[@id='tn']");
	private By countryButtonLebanon = By.xpath("//*[@id='lb']");
	private By countryButtonUAE = By.xpath("//*[@id='ae']");
	private By planTypesLite = By.xpath("//*[@id='currency-lite']");
	private By planTypesClassic = By.xpath("//*[@id='currency-classic']");
	private By planTypesPremium = By.xpath("//*[@id='currency-premium']");


	// Page actions
	public void navigateTo() throws Exception {
		String environment = getData("config.url");
		System.out.println("Navigating to : " + environment + "\n");
		goToUrl(environment);
	}
	
	public void validateSubscriptionOman() throws Exception {
		clickOnElement(englishButton);
		clickOnElement(countryButton);
		clickOnElement(countryButtonOman);
		
		List<String> expectedTexts = List.of(getData("data.omanPlanTypeLite"), getData("data.omanPlanTypeClassic"), getData("data.omanPlanTypePremium"));
		System.out.println("Expected values : " + expectedTexts);
		
		List<String> actualTexts = List.of(getElementText(planTypesLite), getElementText(planTypesClassic), getElementText(planTypesPremium));
		System.out.println("Actual values : " + actualTexts);
		
		Assert.assertEquals(actualTexts, expectedTexts, "Texts do not match!");
	}
	
	public void validateSubscriptionAlgeria() throws Exception {
		clickOnElement(englishButton);
		clickOnElement(countryButton);
		clickOnElement(countryButtonAlgeria);
		
		List<String> expectedTexts = List.of(getData("data.algeriaPlanTypeLite"), getData("data.algeriaPlanTypeClassic"), getData("data.algeriaPlanTypePremium"));
		System.out.println("Expected values Algeria : " + expectedTexts);
		
		List<String> actualTexts = List.of(getElementText(planTypesLite), getElementText(planTypesClassic), getElementText(planTypesPremium));
		System.out.println("Actual values Algeria : " + actualTexts);
		
		Assert.assertEquals(actualTexts, expectedTexts, "Texts do not match!");
	}
	
	public void validateSubscriptionTunisia() throws Exception {
		clickOnElement(englishButton);
		clickOnElement(countryButton);
		clickOnElement(countryButtonTunisia);
		
		List<String> expectedTexts = List.of(getData("data.tunisiaPlanTypeLite"), getData("data.tunisiaPlanTypeClassic"), getData("data.tunisiaPlanTypePremium"));
		System.out.println("Expected values Tunisia : " + expectedTexts);
		
		List<String> actualTexts = List.of(getElementText(planTypesLite), getElementText(planTypesClassic), getElementText(planTypesPremium));
		System.out.println("Actual values Tunisia : " + actualTexts);
		
		Assert.assertEquals(actualTexts, expectedTexts, "Texts do not match!");
	}
	
	public void validateSubscriptionLebanon() throws Exception {
		clickOnElement(englishButton);
		clickOnElement(countryButton);
		clickOnElement(countryButtonLebanon);
		
		List<String> expectedTexts = List.of(getData("data.lebanonPlanTypeLite"), getData("data.lebanonPlanTypeClassic"), getData("data.lebanonPlanTypePremium"));
		System.out.println("Expected values Lebanon : " + expectedTexts);
		
		List<String> actualTexts = List.of(getElementText(planTypesLite), getElementText(planTypesClassic), getElementText(planTypesPremium));
		System.out.println("Actual values Lebanon : " + actualTexts);
		
		Assert.assertEquals(actualTexts, expectedTexts, "Texts do not match!");
	}
	
	public void validateSubscriptionUAE() throws Exception {
		clickOnElement(englishButton);
		clickOnElement(countryButton);
		clickOnElement(countryButtonUAE);
		
		List<String> expectedTexts = List.of(getData("data.UAEPlanTypeLite"), getData("data.UAEPlanTypeClassic"), getData("data.UAEPlanTypePremium"));
		System.out.println("Expected values EAU : " + expectedTexts);
		
		List<String> actualTexts = List.of(getElementText(planTypesLite), getElementText(planTypesClassic), getElementText(planTypesPremium));
		System.out.println("Actual values EAU : " + actualTexts);
		
		Assert.assertEquals(actualTexts, expectedTexts, "Texts do not match!");
	}

}
