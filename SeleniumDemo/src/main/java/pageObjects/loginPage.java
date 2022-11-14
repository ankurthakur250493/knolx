package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginPage {

    public WebDriver driver;
    By emailAddress = By.id("ap_email");
    By button = By.id("continue");
    By password = By.id("ap_password");
    By loginButton = By.id("signInSubmit");

    public loginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement enterEmail() {
        return driver.findElement(emailAddress);

    }
    public WebElement continueButton() {
        return driver.findElement(button);

    }

    public WebElement enterPassword() {
        return driver.findElement(password);

    }

    public WebElement login() {
        return driver.findElement(loginButton);

    }
}
