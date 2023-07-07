package project_main.page_objects;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project_main.abstract_components.abstract_components;

import java.util.List;

public class cart_Page extends abstract_components {
    WebDriver driver;
    public cart_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

   @FindBy(css =".inventory_item_name")
    private List<WebElement> cart_products;

    @FindBy(xpath = "//button[contains(text(),'Checkout') or @id='checkout']")
    WebElement checkout;

    @FindBy(xpath = "//button[@id='continue-shopping' or @name='continue-shopping' and @data-test='continue-shopping']")
    WebElement continue_shopping;

    public Boolean verify_Product_Displayed(String productName){  // TODO - Verifying the product added to cart is matching with in the cart
        Boolean product_match = cart_products.stream().anyMatch( cart_product ->
                cart_product.getText().equalsIgnoreCase(productName));
        return product_match;
    }

    public void continue_Shopping(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        continue_shopping.click();
    }

    public checkout_Page goto_Checkout_Page(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        checkout.click();
        checkout_Page checkout_page = new checkout_Page(driver); // TODO - Calling next page instance
        return checkout_page;
    }

}
