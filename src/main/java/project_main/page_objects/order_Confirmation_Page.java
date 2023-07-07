package project_main.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project_main.abstract_components.abstract_components;

public class order_Confirmation_Page extends abstract_components {
    WebDriver driver;

    //String Message = "Thank you for your order!";

    //String Page_Title = "Checkout: Complete!";

    public order_Confirmation_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css =".complete-header")
    WebElement confirmation_message;

    @FindBy(xpath = "//button[contains(text(),'Back Home')]")
    WebElement back_to_home_page;

    @FindBy(xpath = "//div/span[@class='title']")
    WebElement page_title;

    public Boolean get_Page_Title(String Page_Title){
       Boolean verify_Page_Title = page_title.getText().equals(Page_Title);
        return verify_Page_Title;
    }

    public Boolean verify_Confirmation_Message(String confirmation_Message){
        Boolean verify_confirmation_message = confirmation_message.getText().equals(confirmation_Message);
        return verify_confirmation_message;
    }

    public void go_Back_Home(){
        back_to_home_page.click();
    }
}
