package TestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    protected BasePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(3000));
    }

    public void setup(){
        driver.manage().window().maximize();
    }

    public void url (String url){
        driver.get(url);
    }

    public void close(){
        driver.quit();
    }

    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public void sendText(String inputText, By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = this.findElement(locator);
        element.clear();
        element.sendKeys(inputText);
    }


    public void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    public String getText(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.findElement(locator).getText();
    }
}
