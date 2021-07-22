package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;

public class RegistrationScreen extends BasePage{
    private static WebDriver driver;


    public void register() throws SQLException {
        new RegistrationScreen();
        pressRegister();
        fillRegisterForm();
    }

    public RegistrationScreen() throws SQLException {
        this.driver= SingletonDriver.getDriverInstance();
    }
    public void pressRegister(){

        clickElement(Constants.LogIn);
        clickElement(Constants.Register);
    }
    public void fillRegisterForm(){
        sendKeysToElement(By.cssSelector(".ember-view[placeholder='שם פרטי']"), "דניס");
        sendKeysToElement(By.cssSelector(".ember-view[placeholder='מייל']"), "denis.kozyra@gmail.com");
        sendKeysToElement(Constants.Password, "123456");
        sendKeysToElement(Constants.RePassword, "123456");
        String Name = driver.findElement(By.cssSelector(".ember-view[placeholder='שם פרטי']")).getText();
        String Mail = driver.findElement(By.cssSelector(".ember-view[placeholder='מייל']")).getText();
        String Password = driver.findElement(Constants.Password).getText();
        String RePassword = driver.findElement(Constants.RePassword).getText();
//        Assert.assertEquals(Name, "דניס");
//        Assert.assertEquals(Mail, "denis.kozyra@gmail.com");
//        Assert.assertEquals(Password, "123456");
//        Assert.assertEquals(RePassword, "123456");
//        Assert.assertEquals(Password,RePassword);
        clickElement(By.partialLinkText("BUYME"));
    }

}
