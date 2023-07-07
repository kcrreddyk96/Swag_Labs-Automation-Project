package project_test.test_components;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import project_main.page_objects.login_Page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class base_test {
    public WebDriver driver;
    public login_Page login_page;
    String pageURL;
    String browserName;
    String jsonContent;

    public WebDriver initialize_browser() throws IOException {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//project_main//resources//config.properties");
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException exception caught in initialize method: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException exception caught in initialize method: " + e.getMessage());
        }

        // TODO - To get Browser input form Terminal or config.properties File
        browserName = System.getProperty("web_browser") != null ? System.getProperty("web_browser") : properties.getProperty("web_browser");
        //browserName = properties.getProperty("web_browser");

        // TODO - To get url input form Terminal or Config.properties File
        pageURL = System.getProperty("webpage_url") != null ? System.getProperty("webpage_url") : properties.getProperty("webpage_url");
        //properties.getProperty("url");

        //TODO - FireFox_Browser
        if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            System.out.println("Firefox Browser is Selected");
        }
        //TODO - Chrome_Browser
        else if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C://Users//KC - Reddy//AppData//Local//BraveSoftware//Brave-Browser//Application//brave.exe");
            driver = new ChromeDriver(options);
            System.out.println("Chrome Browser is Selected");
        }
        //TODO - Edge_Browser
        else if (browserName.equalsIgnoreCase("edge")) {
            System.out.println("Edge Browser is Selected");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        System.out.println("driver = " + driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }

    //TODO - Reading Json File and Converting into String &&
    // Convert String into HashMap using Jackson Databind JAR
    public List<HashMap<String, String>> get_Json_Data_To_Map(String FilePath) throws IOException {
        jsonContent = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper(); //TODO - Convert String into HashMap using Jackson Databind JAR
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        }); //TODO - Converting String into Hash Map and storing values in "data" as { {Map}, {Map1} }
        return data;
    }

    //TODO - Screenshot_Logic
    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File file_source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File file_location = new File(System.getProperty("user.dir") + "//src//test//java//project_test//test_reports//screenshots//" + testCaseName + ".png"); //TODO - Store in local drive
        FileUtils.copyFile(file_source, file_location);
        return System.getProperty("user.dir") + "//src//test//java//project_test//test_reports//screenshots//" + testCaseName + ".png";
    }

    //TODO - Initializing the Browser
    @BeforeMethod(alwaysRun = true)
    public login_Page launch_Browser() throws IOException {
        driver = initialize_browser();
        login_page = new login_Page(driver); //TODO - Calling Next Page Class Object
        login_page.getURL(pageURL); //TODO - Sending URL to load in the Browser
        return login_page;
    }

    //TODO - Closing the Browser
    @AfterMethod(alwaysRun = true)
    public void setShut_Down() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}