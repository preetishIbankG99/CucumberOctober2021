package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import utility.TestUtil;
import utility.WebEventListener;

public class TestBase {
public static Properties prop;
public static Logger logger;
public  static EventFiringWebDriver e_driver;
public static WebEventListener eventListener;
public static String browserName;

public static WebDriver driver;
public TestBase()throws FileNotFoundException,IOException{
    logger=Logger.getLogger("OrangeHRM");
    PropertyConfigurator.configure("log4j.properties");
        prop=new Properties();
    File f=new File("E:\\Eclipse All versions\\Workspace\\CucumberTestNG\\log4j.properties");
        FileInputStream fis=new FileInputStream(f);
    prop.load(fis);
}
public static  void initialization()throws IOException{
    browserName = prop.getProperty("browser");
    if (browserName.equalsIgnoreCase("firefox")) {
    	System.setProperty("webdriver.gecko.driver","E:\\Eclipse All versions\\Workspace\\CucumberTestNG\\Drivers\\geckodriver.exe");
    	
        driver = new FirefoxDriver();
        logger.info("Firefox::Browser::Opened");



    } else if (browserName.equalsIgnoreCase("chrome")) {
    	System.setProperty("webdriver.chrome.driver", "E:\\Eclipse All versions\\Workspace\\CucumberTestNG\\Drivers\\chromedriver.exe");
  	
        driver = new ChromeDriver();
        logger.info("Chrome::Browser::Opened");

    } else if (browserName.equalsIgnoreCase("ie")) {
        System.setProperty("webdriver.ie.driver", "E:\\Eclipse All versions\\Workspace\\CucumberTestNG\\Drivers\\IEDriverServer.exe");
        driver = new InternetExplorerDriver();
        logger.info("ie::Browser::Opened");

    }
e_driver=new EventFiringWebDriver(driver);
    eventListener=new WebEventListener();
    e_driver.register(eventListener);
    driver=e_driver;
    driver.manage().window().maximize();
    logger.info("Browser::Maximized");
    driver.get(prop.getProperty("url"));
    logger.info("url entered");
    driver.manage().deleteAllCookies();
    //driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    //Using Utility Class
driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
}
}



