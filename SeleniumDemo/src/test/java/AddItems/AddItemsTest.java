package AddItems;

import BrowserInitializer.Base;
import DataDriven.ReadExcelFile;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
//import pageObjects.addItems;
import pageObjects.loginPage;

import java.io.IOException;

public class AddItemsTest extends Base {
    private static Logger log = LogManager.getLogger(AddItemsTest.class);
    public WebDriver driver;
    ExtentReports extent;
    @BeforeTest
    public void TearUp() throws IOException {
        driver = InitialiseDemo();
        log.info("Driver is initialized");
            String path = System.getProperty("user.dir") + "/reports/index.html";
            System.out.println(System.getProperty("user.dir"));
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("SampleReportName");
            reporter.config().setDocumentTitle("SampleDocumentTitle");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester","Ankur");
        }


    @Test(dataProvider = "getData")
    public void searchItems(String SearchItem) throws IOException {
        ExtentTest test = extent.createTest("Initial demo");
        driver.get("https://www.amazon.in/");
//        addItems ai = new addItems(driver);
//        ai.searchBox().sendKeys(SearchItem);
//        ai.searchBox().sendKeys(Keys.RETURN);
        driver.manage().deleteAllCookies();
    }

    @DataProvider
    public Object[][] getData() throws InterruptedException {
        //Row stands for how many data types test should run
        //Columns stands for how many values per test
        ReadExcelFile config = new ReadExcelFile("src/main/resources/DDTItemTest.xlsx");
        int rows = config.getRowCount(0);
        Object[][] items = new Object[rows][1];

        for (int i=0;i<rows;i++){
            items[i][0] = config.getData(0,i,0);

        }
        return items;
    }

    @AfterMethod
    void closeBrowser(){
        driver.manage().deleteAllCookies();
    }

    @AfterTest
    void TearDown() {
        driver.close();
        log.info("Driver is closed");
    }
}