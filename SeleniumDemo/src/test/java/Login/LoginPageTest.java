package Login;

import BrowserInitializer.Base;
import DataDriven.ReadExcelFile;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.loginPage;

import java.io.IOException;

public class LoginPageTest extends Base {
    private static Logger log = LogManager.getLogger(LoginPageTest.class);
    public WebDriver driver;
    ExtentReports extent;
    @BeforeTest
    public void TearUp() throws IOException {
        driver = InitialiseDemo();
      //  driver.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_custrec_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
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
    public void LoginPage(String UserName, String Password, String text) throws IOException {
        ExtentTest test = extent.createTest("Initial demo");
        driver.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_custrec_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");

        loginPage lp = new loginPage(driver);
        lp.enterEmail().sendKeys(UserName);
        lp.continueButton().click();
        lp.enterPassword().sendKeys(Password);
        lp.login().click();
        System.out.println(text);

    }

    @DataProvider
    public Object[][] getData() throws InterruptedException {
        //Row stands for how many data types test should run
        //Columns stands for how many values per test
        ReadExcelFile config = new ReadExcelFile("src/main/resources/DDTDemo.xlsx");
        int rows = config.getRowCount(0);
        Object[][] credentials = new Object[rows][3];

        for (int i=0;i<rows;i++){
            credentials[i][0] = config.getData(0,i,0);
            credentials[i][1]= config.getData(0,i,1);
            credentials[i][2]= config.getData(0,i,2);
        }
        return credentials;
    }

    @AfterMethod
    void closeBrowser(){
        driver.manage().deleteAllCookies();
    }
    @AfterTest
    void TearDown() {
        driver.close();
        log.info("Driver is closed");
        extent.flush();
    }
}