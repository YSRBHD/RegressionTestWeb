package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import driversFactory.DriverFactory;

public class Utility {

	public static void encodeToBase64Format(String inputFile, String outputFile) throws IOException {
		Path inPath = Paths.get(inputFile);
		Path outPath = Paths.get(outputFile);
		try (OutputStream out = Base64.getEncoder().wrap(Files.newOutputStream(outPath))) {
			Files.copy(inPath, out);
		}
	}

	public static void decodeFromBase64Format(String encodedfilecontent, String decodedfile) throws IOException {
		Path inPath = Paths.get(encodedfilecontent);
		Path outPath = Paths.get(decodedfile);
		try (InputStream in = Base64.getDecoder().wrap(Files.newInputStream(inPath))) {
			Files.copy(in, outPath);
		}
	}

	public static long generateRandomNumber(long min, long max) {
		long lowerLimit = min;
		long upperLimit = max;
		Random r = new Random();
		long number = lowerLimit + ((long) (r.nextDouble() * (upperLimit - lowerLimit)));
		return number;
	}

	public static String getTextFromTxtFile(String url) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(url)));
		return content;
	}

	public static String getTodaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String todaysdate = dateFormat.format(date);
		return todaysdate;
	}

	public static String getTodaysDateForXMLFile() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String todaysdate = dateFormat.format(date);
		return todaysdate;
	}

	public static String getDateAfterXDaysFromToday(int days) {
		return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

	public static int daysBetweenTwoDates(String startDateStr, String endDateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate startDate = LocalDate.parse(startDateStr, formatter);
		LocalDate endDate = LocalDate.parse(endDateStr, formatter);
		return (int) ChronoUnit.DAYS.between(startDate, endDate);
	}

	public static String getNumbersFromText(String str) {
		StringBuilder sb = new StringBuilder();
		for (char c : str.toCharArray()) {
			if (Character.isDigit(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public List<String> getDataFromTable(WebElement table) throws Throwable {
		List<String> value = new ArrayList<String>();
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				value.add(cell.getText());
			}
		}
		System.out.println("\n - Actual values : " + value);
		return value;
	}

	public String getDataFromField(By Element) {
		return DriverFactory.getDriver().findElement(Element).getAttribute("value");
	}

	public String getDataFromCombobox(By Element) {
		// identify element
		WebElement comboBox = DriverFactory.getDriver().findElement(Element);
		// Select class for dropdown
		Select select = new Select(comboBox);
		// get selected option with getFirstSelectedOption() with its text
		WebElement selectedOption = select.getFirstSelectedOption();
		String value = selectedOption.getText();
		return value;
	}
	
	public String getElementText(By Element) {
        WebElement element = DriverFactory.getDriver().findElement(Element);
        return element.getText();
    }

	public static List<String> addFieldsDataToList(String... actualValues) {
		List<String> value = new ArrayList<>();
		for (String string : actualValues) {
			value.add(string);
		}
		System.out.println("\n - Actual values : " + value);
		return value;
	}

	public List<String> addPropertiesDataToList(String... expectedValues) throws Throwable {
		List<String> expected = Arrays.asList(expectedValues);
		System.out.println("\n - Expect values : " + expected + "\n");
		return expected;
	}

	public static String getData(String valueName) throws Exception {
		String[] propertiesFiles = {
				"src" + File.separator + "main" + File.separator + "resources" + File.separator + "config"
						+ File.separator + "config.properties",
				"src" + File.separator + "test" + File.separator + "resources" + File.separator + "data"
						+ File.separator + "data.properties",};

		for (String propertiesFile : propertiesFiles) {
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(propertiesFile);
			prop.load(in);
			in.close();
			String value = prop.getProperty(valueName);
			if (value != null) {
				return value; // Return the value if it is found
			}
		}
		System.out.println("Error : The value of " + valueName + " not found in properties file ");
		return null; // Return null if the value is not found in any of the properties files
	}

	public static String getCellText(By tableElement, By cellElement) {
	    WebElement table = DriverFactory.eDriver.findElement(tableElement);
	    WebElement cell = table.findElement(cellElement);
	    String cellText = cell.getText();
	        
	        if (cellText == null || cellText.equals(""))
	        cellText = cell.getAttribute("value");
	    
	    return cellText;
	}

	public void clickOnElement(By Element) {
		DriverFactory.wait.until(ExpectedConditions.elementToBeClickable(Element));
		DriverFactory.eDriver.findElement(Element).click();
	}

	public void selectComboBoxValue(By Element, String value) {
		DriverFactory.wait.until(ExpectedConditions.elementToBeClickable(Element));
		Select dropValue = new Select(DriverFactory.eDriver.findElement(Element));
		dropValue.selectByVisibleText(value);
	}

	public void sendKeys(By Element, String text) {
		DriverFactory.wait.until(ExpectedConditions.visibilityOfElementLocated(Element));
		DriverFactory.eDriver.findElement(Element).sendKeys(text);
	}

	public boolean linkIsReachable(final String address) {
		try {
			final URL url = new URL(address);
			final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setConnectTimeout(1000 * 10); // Timeout is in seconds
			final long startTime = System.currentTimeMillis();
			urlConn.connect();
			final long endTime = System.currentTimeMillis();
			if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println("Time (ms) : " + (endTime - startTime));
				System.out.println("Ping to " + address + " was success");
				return true;
			}
		} catch (final MalformedURLException e1) {
			e1.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
