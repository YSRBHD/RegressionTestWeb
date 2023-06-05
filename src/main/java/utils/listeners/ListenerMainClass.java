package utils.listeners;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import driversFactory.DriverFactory;

public class ListenerMainClass extends DriverFactory {
	EventFiringWebDriver eventHandler = new EventFiringWebDriver(getDriver());
	WebDriverListener eCapture = new WebDriverListener();
}
