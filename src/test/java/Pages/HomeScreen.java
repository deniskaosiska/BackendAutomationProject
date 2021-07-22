package Pages;

import BackendProject.SingeltonDB;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;

public class HomeScreen extends BasePage{
    public static WebDriver driver;
    //Getting driver from SingeltonDriver
    public HomeScreen() throws SQLException {
        this.driver = SingletonDriver.getDriverInstance();
    }
//Log in to site and fill credentianals
    public void logIn(){
        //new HomeScreen();
        clickElement(Constants.LogIn);
        sendKeysToElement(Constants.Mail, Constants.UserName);
        sendKeysToElement(Constants.Password, Constants.Pass);
        driver.findElement(By.cssSelector(".ember-view[placeholder='סיסמה']")).submit();
    }
    //Pick any present on site
    public void pickPresent(){
        clickElement(By.xpath("//*[@id=\"ember978_chosen\"]/a"));
        clickElement(By.xpath("//*[@id=\"ember978_chosen\"]/div/ul/li[3]"));
        clickElement(By.xpath("//*[@id=\"ember993_chosen\"]/a"));
        clickElement(By.xpath("//*[@id=\"ember993_chosen\"]/div/ul/li[3]"));
        clickElement(By.xpath("//*[@id=\"ember1003_chosen\"]/a"));
        clickElement(By.xpath("//*[@id=\"ember1003_chosen\"]/div/ul/li[5]"));
        clickElement(By.xpath("//*[@id=\"ember1038\"]"));
    }
    public void logOut(){
        clickElement(By.cssSelector("img[class='arrow']"));
        clickElement(By.cssSelector("a[class='dropdown-item']"));
    }

}
