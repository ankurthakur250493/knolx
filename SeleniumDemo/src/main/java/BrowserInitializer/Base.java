package BrowserInitializer;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public WebDriver driver;
    public Properties prop = new Properties();

    public WebDriver InitialiseDemo() throws IOException {


        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Data.properties");
        prop.load(fis);
        //  String browserName = System.getProperty("browser");
        String browserName = prop.getProperty("browser");


        switch (browserName) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/DriverFiles/geckodriver");
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/DriverFiles/chromedriver");
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                driver.manage().window().maximize();
                break;

            case "chromeheadless":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/DriverFiles/chromedriver");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model
                options.addArguments("--headless");

                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                driver.manage().window().maximize();
                break;

            case "IE":

                break;
        }
        return driver;
    }

    public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/screenshot/reports/" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;

    }
}
