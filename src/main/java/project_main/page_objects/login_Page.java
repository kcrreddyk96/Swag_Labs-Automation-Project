package project_main.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project_main.abstract_components.abstract_components;

public class login_Page extends abstract_components {
    WebDriver driver;

    public login_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='user-name' and @placeholder='Username']")
    WebElement user_name;

    @FindBy(xpath = "//input[@type='password' and @placeholder='Password']")
    WebElement user_password;

    @FindBy(xpath = "//input[@type='submit' and @id='login-button']")
    WebElement log_in;

    @FindBy(xpath = "//div[@class='error-message-container error']/h3")
    WebElement locked_error_message;

    @FindBy(xpath = "//div[@class='error-message-container error']/h3")
    WebElement wrong_username_message;

    public void getURL(String pageURL) {
        driver.get(pageURL);
    }

    public String locked_Error_Message() {
        locked_error_message.getText();
        return locked_error_message.getText();
    }

    public String wrong_Username_Message() {
        wrong_username_message.getText();
        return wrong_username_message.getText();
    }

    public home_Page Login_details(String user_Name, String Password) {
        user_name.sendKeys(user_Name);
        user_password.sendKeys(Password);
        log_in.click();

        home_Page home_page = new home_Page(driver); //TODO - Calling Next page
        return home_page;
    }
}
