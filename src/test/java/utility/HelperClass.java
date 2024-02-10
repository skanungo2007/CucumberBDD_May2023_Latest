package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import jdk.internal.org.jline.utils.Log;

public class HelperClass extends BaseClass {

	private static String directory = System.getProperty("user.dir");
	private static byte[] bytes;
	private static String decodedString;
	
	private static Logger log = LogManager.getLogger(HelperClass.class);
	
	
	public static String readProperty(String key) throws IOException {
		
		FileInputStream fis = new FileInputStream(directory + "/src/test/resources/config/config.properties");
		Properties property = new Properties();
		property.load(fis);
		return property.get(key).toString();	
		
	}
	
	public static String readEnvironment(String key, String configFileName) throws IOException {
		
		FileInputStream fis = new FileInputStream(directory + "/src/test/resources/config/" + configFileName);
		Properties property = new Properties();
		property.load(fis);
		return property.get(key).toString();
	}
	
	
	public static void doPause(int ms) throws InterruptedException, ExecutionException {
		
		final ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(1);
		scheduledThreadPoolExecutor.schedule(() -> {}, ms, TimeUnit.MILLISECONDS).get();
		
	}
	
	public static String getSubStringBySpace(String value, int position) {
		
		String[] splitStr = value.trim().split(" ");
		return splitStr[position];
	}
	
	public static String getSubStringBySymbol(String value, String symbol, int position) {
		
		String[] splitStr = value.trim().split(symbol);
		return splitStr[position];
	}
	
	
	
	public static String decode(String password) throws IOException {
		
		String encodedString = readProperty(password);
		Decoder decoder = Base64.getDecoder();
		bytes = decoder.decode(encodedString);
		decodedString = new String(bytes, StandardCharsets.UTF_8);
		
		return decodedString;
	}
	
	public static void explicitClick(WebDriver driver, WebElement element) {
		
		WebDriverWait expWait = new WebDriverWait(driver, 30);
		expWait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	
	public static void explicitWaitVisible(WebDriver driver, WebElement element) {
		
		WebDriverWait expWait = new WebDriverWait(driver, 60);
		expWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static WebElement explicitWaitReturnElement(WebDriver driver, WebElement element) {
		
		WebDriverWait expWait = new WebDriverWait(driver, 60);
		expWait.pollingEvery(10, TimeUnit.SECONDS);
		expWait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	
	
	
	
	public static void waitForElementDisappear(WebElement element) {
		
		int count=0;
		turnOffImplicitWait();
		
		
		try {
			
			while(element.isDisplayed()) {
				
				if(count>5000) {
					break;
				}
				
				count++;
			}
		} catch(Exception exc) {
			
		}
		
		turnOnImplicitWait();
		
	}
	
	
	public static void waitForElementDisappearByTime(WebElement element) {
		
		long endTime = System.currentTimeMillis() + 1200000;
		turnOffImplicitWait();
		
		try {
			
			while(element.isDisplayed()) {
				
				if(System.currentTimeMillis() > endTime) {
					log.info("Wait time exceeded");
					break;
				}
			} 
		} catch(Exception exc) {
			
		}
		
		turnOnImplicitWait();
	}
	
	
	public static void moveToElement(WebElement element) {
		
		Actions builder = new Actions(driver);
		Action move = builder.moveToElement(element).build();
		move.perform();
	}
	
	
	public static void moveAndClick(WebElement element) {
		
		Actions builder = new Actions(driver);
		Action moveClick = builder.moveToElement(element).click(element).build();
		moveClick.perform();
	}
	
	
	public static void clickElement(WebElement element) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public static void doubleClickElement(WebElement element) {
		
		Actions builder = new Actions(driver);
		Action doubleClick = builder.moveToElement(element).doubleClick(element).build();
		doubleClick.perform();
	}
	
	public static void keepScreenUnlocked() throws AWTException {
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
	}
	
	
	public static void scrollByPixel(int x, int y) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy('"+x+"','"+y+"')");
	}
	
	public static void smoothScroll(int value) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		for(int i=0; i<value; i++) {
			
			js.executeScript("window.scrollBy(0, '"+(i+100)+"')");
		}
	}
	
	
	public static void scrollToElement(WebElement element) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("arguments[0].scrollIntoView();", element);
		
	}
	
	
	public static void scrollBottom() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	
	public static void scrollTop() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");
	}
	
	
	public static void uploadFile(String path) throws AWTException {
		
		StringSelection selection = new StringSelection(path);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);
		
		Robot robot = new Robot();
		
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(250);

		
	}
	
}
