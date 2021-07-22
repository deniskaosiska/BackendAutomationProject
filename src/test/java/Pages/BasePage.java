package Pages;

import BackendProject.SingeltonDB;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BasePage {
    private WebElement getWebElement(By locator) throws SQLException {

            return SingletonDriver.getDriverInstance().findElement(locator);
        }


//Click on web element by any locator and create screenshot of web element not found exception
    public void clickElement (By locator) {

        try {
            getWebElement(locator).click();
            WebElement element =  getWebElement(locator);
            takeElementScreenshot(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //send keyword to web element(text box) by any locator and create screenshot of web element not found exception
    public void sendKeysToElement(By locator, String text) {
        try {
            getWebElement(locator).sendKeys(text);
            WebElement element =  getWebElement(locator);
            takeElementScreenshot(element);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //Taking screenshot of web element
    private static void takeElementScreenshot( WebElement element){
        File screenShotFile = element.getScreenshotAs(OutputType.FILE); // take the screenshot
        try {
            FileUtils.copyFile(screenShotFile, new File("element-screenshot"+ timestamp()+".png")); // save screenshot to disk
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
}
