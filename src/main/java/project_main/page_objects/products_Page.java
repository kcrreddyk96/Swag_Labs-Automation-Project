package project_main.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project_main.abstract_components.abstract_components;

import java.util.List;

public class products_Page extends abstract_components {
    WebDriver driver;

    public products_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebElement product_name;

    WebElement product_details;

    @FindBy(css = ".inventory_item")
    List<WebElement> products_List;

    By product = By.cssSelector(".inventory_item");

    By add_to_cart = By.xpath("//button[contains(text(),'Add to cart')]");

    By remove_cart = By.xpath("//button[contains(text(),'Remove')]");

    By back_to_products = By.xpath("//button[@id='back-to-products']");

    public List<WebElement> get_product_list() {
        wait_for_Element_to_Appear(product);
        return products_List;
    }

    public WebElement get_Product_Name(String productName) { //TODO - Filtering required product from product List and storing in "product_name"
        product_name = get_product_list().stream().filter(product ->
                product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);
        return product_name;
    }

    public void open_Product_Details(String productName) {
        product_details = get_Product_Name(productName);
        product_details.findElement(product).click();
    }

    public void add_To_Cart() {
        product_details.findElement(add_to_cart).click();
    }

    public void remove_From_Cart() {
        product_details.findElement(remove_cart).click();
    }

    public void back_To_Products() {
        product_details.findElement(back_to_products).click();
    }
}
