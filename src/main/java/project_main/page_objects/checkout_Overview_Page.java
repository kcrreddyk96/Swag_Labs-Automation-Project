package project_main.page_objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project_main.abstract_components.abstract_components;

import java.util.List;

public class checkout_Overview_Page extends abstract_components {
    WebDriver driver;

    public checkout_Overview_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_item_name")
    List<WebElement> final_products;

    @FindBy(css ="#finish")
    WebElement finish;

    @FindBy(css ="#cancel")
    WebElement cancel;

    public Boolean verify_Product_Displayed(String productName) {  // TODO - Verifying the product added to cart is matching with in the checkout page
        Boolean product_match = final_products.stream().anyMatch(final_product ->
                final_product.getText().equalsIgnoreCase(productName));
        return product_match;
    }

    public void set_Cancel_Payments(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        cancel.click();
    }

    public order_Confirmation_Page finish_Payments(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        finish.click();
        order_Confirmation_Page order_confirmation_page = new order_Confirmation_Page(driver);
        return order_confirmation_page;
    }
}
