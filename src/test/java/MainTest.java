import BackendProject.SingeltonDB;
import Extra.Extra;
import Pages.HomeScreen;
import Pages.SelectBusiness;
import Pages.SenderAndReceiverScreen;
import Pages.SingletonDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * This is sanity tests for website Buyme.
 */
public class MainTest  {
    private static ExtentReports extent;
    private static ExtentTest test;
    public static WebDriver driver;
    public static Connection con;
    int i=0;
    HomeScreen homeScreen = new HomeScreen();
    SelectBusiness selectBusiness = new SelectBusiness();
    SenderAndReceiverScreen senderAndReceiverScreen = new SenderAndReceiverScreen();
    Extra extra = new Extra();
    SingeltonDB singeltonDB =new SingeltonDB();
    private static ArrayList MyList = new ArrayList<>();

    public MainTest() throws SQLException {
    }


    @BeforeTest

    public  void beforeClass() throws Exception {
        String cwd = System.getProperty("user.dir");
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(cwd + "\\extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test=extent.createTest("MyFirstWebTestAutomation", "Sanity Test of website Buyme");
        test.log(Status.INFO, "before test method");


        try {

            SingletonDriver singletonDriver = new SingletonDriver();
            this.driver = singletonDriver.getDriverInstance();
            test.log(Status.PASS, "Driver established successfully");
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Driver connection failed! " + e.getMessage());
            throw new Exception("Driver failed");
        }
    }
    //
//    @Test
//    public void registration(){
//        test.log(Status.INFO, "Test of registration");
//        try {
//        RegistrationScreen registrationScreen = new RegistrationScreen();
//        registrationScreen.register();
//        test.log(Status.PASS, "Test is successful");
//        }catch(Exception e){
//            test.log(Status.FAIL, "Failed: " + e.getMessage());
//        }
//    }
    //Test of home screen: Log in, pick present
    @Test
    public void homeScreen() {
        test.log(Status.INFO, "Test of home screen: Log in, pick present, Log out");
        try {
            //getSizeOfSpinner();
            homeScreen.logIn();
            homeScreen.pickPresent();
            homeScreen.logOut();
            test.log(Status.INFO, "Text color of step name: " + driver.findElement(By.cssSelector("div[class='label bottom-xs']")).getCssValue("color"));
            test.log(Status.PASS, "Test is successful");
            testResult(); //Write result: into DB or Result.txt
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Test of choosing business
    @Test
    public void pickBusiness(){
        test.log(Status.INFO, "Test of choosing business");
        try {
        homeScreen.pickPresent();
        selectBusiness.assertUrl();
        selectBusiness.pickBusiness();
        extra.scrollPage();
        test.log(Status.PASS, "Test is successful");
        testResult();
        }catch(Exception e){
            test.log(Status.FAIL, "Failed: " + e.getMessage());
        }

    }
    //Test of Sender and Receiver information data fill
    @Test
    public void receiverInfo() throws InterruptedException {
        test.log(Status.INFO, "Test of Sender and Receiver information data fill and assert sender and receiver names");
        try {
            //pickBusiness();//If user want to run only this test
            senderAndReceiverScreen.receiverInfo();
            senderAndReceiverScreen.senderInfo();
            test.log(Status.PASS, "Test is successful");
            testResult();
        }catch(Exception e){
            test.log(Status.FAIL, "Failed: " + e.getMessage());
        }
    }
    //Getting size of spinner. I tried to find how can I catch some element before disappear, actually using explacity/fluent, but i didn't..
    @Test
    public void getSizeOfSpinner() throws SQLException {
        Extra extra = new Extra();
        extra.spinnerSize();
    }
    //Assert Log error of Log in failed
    @Test
    public void assertErrorMessageLogIn(){
        test.log(Status.INFO, "Assert Log error of Log in failed");
        try {
        extra.assertHomeScreen();
        test.log(Status.PASS, "Test is successful");
        testResult();
        }catch(Exception e){
            test.log(Status.FAIL, "Failed: " + e.getMessage());
        }
    }
//Scroll down of pick business page
//    @Test
//    public void scrollDownPage() throws IOException {
//        test.log(Status.INFO, "Scroll down of pick business page");
//        try {
//        extra.scrollPage();
//        }catch(Exception e){
//            test.log(Status.FAIL, "Failed: " + e.getMessage());
//        }
//    }
    //Getting color of label Step name
//    @Test
//    public void  getColorStepName(){
//        test.log(Status.INFO, "Getting color of label Step name");
//        try {
//        extra.colorStepName();
//        }catch(Exception e){
//            test.log(Status.FAIL, "Failed: " + e.getMessage());
//        }
//    }

    //Time and date for result
public static String timestamp() {
    return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
}
//Method of create Result txt
public  void createResultTxt() {
    FileWriter newFile = null;
    try {
        newFile = new FileWriter("C:\\Users\\Denis.Kozyra\\OneDrive - xcircular.com\\Desktop\\results.txt");
    } catch (IOException e) {
        e.printStackTrace();
    }
    BufferedWriter bw = new BufferedWriter(newFile);
    /**
     * Write all results
     */
    try {
        int size = MyList.size();
        for (int i=0;i<size;i++) {
            String str = MyList.get(i).toString();
            newFile.write("Result "+ str);
            if(i < size-1) {//This prevent creating a blank like at the end of the file
                newFile.write("\n");
            }
        }
        newFile.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
//Method of creating result: or into DB or into Result txt
public void testResult(){
        try {
    if (con == null) {
        singeltonDB.insertHistory(con, i = i + 1, timestamp());
    } else {
        createResultTxt();
    }
}catch(Exception e){
        test.log(Status.FAIL, "Failed: " + e.getMessage());
    }
}


    @AfterClass
    public void afterClass(){
        extent.flush();
        driver.close();
        driver.quit();
    }
}
