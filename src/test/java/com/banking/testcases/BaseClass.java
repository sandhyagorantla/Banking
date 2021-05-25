package com.banking.testcases;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	
	public String username = "Mngr324929";
	public String password = "evunemE";
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public  WebDriver driver;
	public static Logger logger;
	
	@BeforeClass
	public void setup() {
		if (driver == null) {

			try {
				fis = new FileInputStream(
						"C:\\Users\\91910\\selenium\\Banking\\src\\test\\resources\\configuration\\configure.properties");
			} catch (Exception e) {

				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e) {

				e.printStackTrace();
			}
			if (config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (config.getProperty("browser").equals("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			driver.get(config.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			logger = Logger.getLogger("banking");
			PropertyConfigurator.configure("Log4j.properties");
		}
	}

	@AfterClass
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	
}



