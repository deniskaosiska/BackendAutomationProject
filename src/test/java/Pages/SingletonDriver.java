package Pages;

import BackendProject.SingeltonDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class SingletonDriver {

    private static WebDriver driver;

    public static String URL;
    private static final String USER_NAME = "sql6426362";
    private static final String DATABASE_NAME = "sql6426362";
    private static final String PASSWORD = "EIL5tsyzPV";
    private static final String PORT = "3306";
    private static final String SERVER = "sql6.freemysqlhosting.net";
    //public static Connection con;


//Singelton of driver and getting info from DB table or data.xml
    public static WebDriver getDriverInstance() throws SQLException {
        //Connect to DB table
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://" + SERVER + ":" + PORT, USER_NAME, PASSWORD);

        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".config;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        URL = rs.getString("config_data"); //Try to insert website from DB to String
        //Instal SingeltonDriver
        if(driver == null){
            try {

                if(URL==null){ //if DB is unavailable, use website from DATA.xml


                String URLXML = getData("websiteURL");
                String BrowserType = getData("browserType");
                if(BrowserType.equals("Chrome")){
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Denis.Kozyra\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.get(URLXML);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }else if(BrowserType.equals("FireFox")){
                    System.setProperty("webdriver.firefox.driver", "C:\\Users\\Denis.Kozyra\\Downloads\\geckodriver-v0.29.1-win64\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    driver.get(URLXML);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }
            } }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }
    //Getting info from data.xml by tag
    private static String getData (String keyName) throws Exception{
        ClassLoader classLoader = SingletonDriver.class.getClassLoader();
        String xmlFilePath = String.valueOf(new File(classLoader.getResource("data.xml").getFile()));
        File fXmlFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }

}
