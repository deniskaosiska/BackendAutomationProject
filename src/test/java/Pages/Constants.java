package Pages;

import org.openqa.selenium.By;

public class Constants  {
    public final static By Register = By.xpath("//*[@id=\"ember882\"]/div/div[1]/div/div/div[3]/div[1]/span");
    public final static By LogIn = By.xpath("//*[@id=\"ember915\"]/div/ul[1]/li[3]/a/span[1]");
    public final static By Password = By.cssSelector(".ember-view[placeholder='סיסמה']");
    public final static By RePassword = By.cssSelector(".ember-view[placeholder='אימות סיסמה']");
    public final static By Mail = By.cssSelector(".ember-view[placeholder='מייל']");
    public final static String UserName = "denis.kozyra@gmail.com";
    public final static String Pass = "Den140290";
}
