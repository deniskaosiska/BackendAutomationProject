package BackendProject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class SingeltonDB {
    private static final String USER_NAME = "sql6426362";
    private static final String DATABASE_NAME = "sql6426362";
    private static final String PASSWORD = "EIL5tsyzPV";
    private static final String PORT = "3306";
    private static final String SERVER = "sql6.freemysqlhosting.net";
    public static String config_name;
    public static String config_data;
    public static Connection con=null;
    public static WebDriver driver;




    public static void createTableHistory(Connection con) throws SQLException {


        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`history`(`test_id` INT NOT NULL, `test_date` VARCHAR(50) NOT NULL,  PRIMARY KEY (`test_id`));";

        con.createStatement().execute(statementToExecute);
    }

    public static void createTableConfig(Connection con) throws SQLException {


        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`config`(`config_id` INT NOT NULL, `config_name` VARCHAR(45) NOT NULL, `config_data` VARCHAR(100) NOT NULL, PRIMARY KEY (`config_id`));";

        con.createStatement().execute(statementToExecute);
    }

    public static void insertConfig(Connection con, int config_id, String config_name, String config_data) throws SQLException {
        String statementToExecute = "INSERT INTO " + DATABASE_NAME + ".config (`config_id`, `config_name`, `config_data`) VALUES ('" + config_id + "', '" + config_name + "', '" + config_data + "');";
        con.createStatement().execute(statementToExecute);
    }
    public void insertHistory(Connection con, int test_id, String test_date) throws SQLException {
        String statementToExecute = "INSERT INTO " + DATABASE_NAME + ".history (`test_id`, `test_date`) VALUES ('" + test_id + "', '" + test_date + "');";
        con.createStatement().execute(statementToExecute);
    }

    public static void getTableContent(Connection con) throws SQLException {
        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".config;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        config_data = rs.getString("config_data");
//        config_name = rs.getString("config_name");
        while(rs.next()){

            int config_id  = rs.getInt("config_id");
             config_name = rs.getString("config_name");
             config_data = rs.getString("config_data");


            System.out.print(" config_id: " + config_id);
            System.out.print(", config_name: " + config_name);
            System.out.print(", config_data: " + config_data);
        }
        rs.close();
    }


    public static void updateConfig(Connection con, int config_id, String config_data ) throws SQLException {
        String statementToExecute = "UPDATE `" + DATABASE_NAME + "`.`config` SET `config_data`='"+config_data+"' WHERE `config_id`='"+config_id+"';";
        con.createStatement().executeUpdate(statementToExecute);
    }




        public static void main (String[]args) throws SQLException {
             con = (Connection) DriverManager.getConnection("jdbc:mysql://"+SERVER+":"+PORT, USER_NAME, PASSWORD);

            //createTableHistory(con);

       // insertConfig(con, 1, "URL", "https://buyme.co.il/");
       // insertConfig(con, 2, "BROWSER", "chrome");

           // getTableContent(con);
           // updateConfig(con,1, "https://www.newsru.co.il/");
             con.close();
        }

    }

