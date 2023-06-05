package driversFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Utility;
import utils.listeners.WebDriverListener;

public class DriverFactory extends Utility {


	public WebDriver driver = null;
	public static WebDriverWait wait;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static JavascriptExecutor js;
	public static EventFiringWebDriver eDriver;
	public static WebDriverListener ecapture;
	

	public WebDriver initializeDriver() throws Exception {
		
		String browser = getData("config.browser.name");

		if (browser.equals("chrome")) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			eDriver = new EventFiringWebDriver(driver);
			ecapture = new WebDriverListener();
			eDriver.register(ecapture);
		}
		
		
		else if (browser.equals("chromeheadless")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--allow-insecure-localhost");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("start-maximized");
			options.addArguments("enable-automation");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-browser-side-navigation");
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");
			options.addArguments("--force-device-scale-factor=1");
			options.addArguments("--lang=es-ES");
			options.addArguments("--whitelist-ip *");
			options.addArguments("--proxy-server='direct://'");
			options.addArguments("--proxy-bypass-list=*");
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			eDriver = new EventFiringWebDriver(driver);
			ecapture = new WebDriverListener();
			eDriver.register(ecapture);
		}
		
		else if (browser.equals("chromehub")) {
		ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        //options.addArguments("--no-sandbox");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-dev-shm-usage");
		//options.addArguments("--whitelisted-ips=");
        //options.addArguments("--start-fullscreen");
        options.addArguments("start-maximized");
		options.setCapability("se:recordVideo", true);
		options.setCapability("se:screenResolution", "1920x1080");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("intl.accept_languages", "es-ES,es");
        options.setExperimentalOption("prefs", chromePrefs);
        
		driver = (WebDriver) new RemoteWebDriver(new URL("http://127.0.0.1:4446/wd/hub"), options);
		
		eDriver = new EventFiringWebDriver(driver);
		ecapture = new WebDriverListener();
		eDriver.register(ecapture);
	}

		else if (browser.equals("firefox")) {
			FirefoxProfile options = new FirefoxProfile();
			options.setPreference("permissions.default.desktop-notification", 1);
			options.setPreference("browser.cache.disk.enable", false);
			options.setPreference("browser.cache.memory.enable", false);
			options.setPreference("browser.cache.offline.enable", false);
			options.setPreference("network.http.use-cache", false);
			FirefoxOptions ffOpts = new FirefoxOptions();
			ffOpts.setProfile(options);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(ffOpts);
			eDriver = new EventFiringWebDriver(driver);
			ecapture = new WebDriverListener();
			eDriver.register(ecapture);
		}
		
		else if (browser.equals("safari")) {
			driver = new SafariDriver();
			eDriver = new EventFiringWebDriver(driver);
			ecapture = new WebDriverListener();
			eDriver.register(ecapture);
		}

		else if (browser.equals("egde")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			eDriver = new EventFiringWebDriver(driver);
			ecapture = new WebDriverListener();
			eDriver.register(ecapture);
		}
		
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		
		tlDriver.set(driver);
		
		return getDriver();
		
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	public void goToUrl(String url) {
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(url);
		getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
	}

}
