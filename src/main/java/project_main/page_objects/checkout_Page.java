package project_main.page_objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project_main.abstract_components.abstract_components;

public class checkout_Page extends abstract_components {
    WebDriver driver;

    public checkout_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='firstName' and @placeholder='First Name']")
    WebElement f_name;

    @FindBy(xpath = "//input[@id='last-name' and @placeholder='Last Name']")
    WebElement l_name;

    @FindBy(xpath = "//input[@id='postal-code' and @data-test='postalCode']")
    WebElement postal_code;

    @FindBy(xpath = "//input[@type='submit' or @value='Continue' and @name ='continue']")
    WebElement next_continue;

    @FindBy(xpath = "//button[@id='cancel' and @name ='cancel']")
    WebElement cancel;

    public void sending_Checkout_info(String F_name, String L_name, String zip_Code){
        f_name.sendKeys(F_name);
        l_name.sendKeys(L_name);
        postal_code.sendKeys(zip_Code);
    }

    public void set_Cancel_go_Back(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        cancel.click();
    }

    public checkout_Overview_Page set_Next_continue(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        next_continue.click();
        checkout_Overview_Page checkout_overview_page = new checkout_Overview_Page(driver);
        return checkout_overview_page;
    }
}

