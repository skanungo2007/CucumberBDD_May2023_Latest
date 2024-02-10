package base;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.internal.org.jline.utils.Log;
import utility.HelperClass;

public class BaseClass {

	
	protected static WebDriver driver;
	protected static String envName="";
	protected static String configFileName="";
	
	protected static Logger log = LogManager.getLogger(BaseClass.class);
	
	public static WebDriver setupBrowser() throws IOException {
		
		Path path = Paths.get("");
		String currentDirectory = path.toAbsolutePath().toString();
		
		if(HelperClass.readProperty("browser").equalsIgnoreCase("chrome")) {
			
//			String driverPath = currentDirectory + "/drivers/chromedriver.exe";
//			System.setProperty("webdriver.chrome.driver", driverPath);
//			driver = new ChromeDriver();
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			
			
			
		} else if(HelperClass.readProperty("browser").equalsIgnoreCase("edge")) {
				
//			String driverPath = currentDirectory + "/drivers/msedgedriver.exe";
//			System.setProperty("webdriver.edge.driver", driverPath);
//			driver = new EdgeDriver();
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
			
		} else {
			
//			String driverPath = currentDirectory + "/drivers/geckodriver.exe";
//			System.setProperty("webdriver.gecko.driver", driverPath);
//			driver = new FirefoxDriver();
			
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("private");
			driver = new FirefoxDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		
		configFileName = "./%s-config.properties";
		envName = System.getProperty("env");
		
		if(envName==null) {
			
			envName="qa";
		}
		
		log.info("Test Environment: " + envName.toUpperCase());
		configFileName = String.format(configFileName, envName);
		
		if(envName.equalsIgnoreCase("qa")) {
			
			driver.get(HelperClass.readEnvironment("urlQa", configFileName));
		
		} else if(envName.equalsIgnoreCase("int2")) {
			
			driver.get(HelperClass.readEnvironment("urlInt2", configFileName));
		
		} else {
			
			driver.get(HelperClass.readEnvironment("urlDev", configFileName));
		}
		
		
		log.info("Browser opened");
		
		return driver;
		
	}
	
	public static void close() {
		
		log.info("Browser closed");
		driver.quit();
		
	}
	
	
	public static void turnOffImplicitWait() {
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	

	public static void turnOnImplicitWait() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
}
