package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.SQLException;

public class SenderAndReceiverScreen extends BasePage{

    public static WebDriver driver;
    //Getting driver from SingeltonDriver
    public SenderAndReceiverScreen() throws SQLException {
        this.driver=SingletonDriver.getDriverInstance();
    }
    //Fill receiver data info
    public void receiverInfo() throws InterruptedException {
        clickElement(By.cssSelector("span[class='text'"));
        driver.findElement(By.cssSelector("input[maxlength='25']")).clear();
        sendKeysToElement(By.cssSelector("input[maxlength='25']"), "אסתר");
        clickElement(By.cssSelector("span[class='selected-text'"));
        clickElement(By.cssSelector("li[value='14']"));
        driver.findElement(By.cssSelector("textarea[rows='4']")).clear();
        sendKeysToElement(By.cssSelector("textarea[rows='4']"), "מזל טוב לחתונה");
        WebElement UploadImg = driver.findElement(By.cssSelector("input[name='logo']"));
        UploadImg.sendKeys("C:\\Users\\Denis.Kozyra\\OneDrive - xcircular.com\\Desktop\\125.jpg");
        //assertReceiverName();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("button[type='submit']")).submit();
    }
    //Fill sender data info
    public void senderInfo(){
        clickElement(By.cssSelector("svg[gtm='method-email']"));
        sendKeysToElement(By.cssSelector("input[id='email']"),Constants.UserName);
        sendKeysToElement(By.cssSelector("input[type='text']"),"דניס");
       // assertSenderName();
        clickElement(By.cssSelector("button[type='submit']"));
    }
    //Assert sender name
    public void assertSenderName(){
        String SenderName = driver.findElement(By.xpath("//*[@id=\"ember1926\"]")).getText();
        Assert.assertEquals(SenderName, "דניס");
    }
    //Assert receiver name
    public void assertReceiverName(){
        String ReceiverName = driver.findElement(By.cssSelector("input[maxlength='25']")).getText();
        Assert.assertEquals(ReceiverName, "אסתר");
    }
}
