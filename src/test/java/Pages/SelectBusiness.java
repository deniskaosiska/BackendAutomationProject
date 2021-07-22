package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.sql.SQLException;

public class SelectBusiness extends BasePage {
    public static WebDriver driver;
    //Getting driver from SingeltonDriver
    public SelectBusiness() throws SQLException {
        this.driver = SingletonDriver.getDriverInstance();
    }
//Assert current URL and excepted after pick business method
    public void assertUrl(){
        String CurrentUrl = driver.getCurrentUrl();
        Assert.assertEquals(CurrentUrl, "https://buyme.co.il/search?budget=2&category=8&region=11");
    }
    //Pick business after log in
    public void pickBusiness(){
       // clickElement(By.xpath("//*[@id=\"ember1645\"]/div[2]"));
        clickElement(By.cssSelector("a[href='https://buyme.co.il/package/348356/1005727'"));
      //  clickElement(By.cssSelector(".ember-view bm-btn"));


    }

}
