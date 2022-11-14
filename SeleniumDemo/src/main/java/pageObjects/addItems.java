package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class addItems {

    public WebDriver driver;
    By searchBox = By.id("twotabsearchtextbox");

    public addItems(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement searchBox() {
        return driver.findElement(searchBox);

    }
}
