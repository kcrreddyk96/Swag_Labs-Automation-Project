package project_main.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import project_main.abstract_components.abstract_components;

import java.util.List;

public class home_Page extends abstract_components {
    WebDriver driver;
    WebElement product_name;

    public home_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_item")
    List<WebElement> products_List;

    By product = By.cssSelector(".inventory_item");

    By addtocart = By.cssSelector(".btn_small");

    WebElement product_add_to_cart;

    @FindBy(xpath = "//select[@class='product_sort_container' and @data-test='product_sort_container']")
    WebElement filter_dropdown;

    public List<WebElement> get_product_list() {
        wait_for_Element_to_Appear(product);
        return products_List;
    }

    public WebElement get_Product_Name(String productName) { //TODO - Filtering required product from product List and storing in "product_name"
        product_name = get_product_list().stream().filter(product -> product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);
        return product_name;
    }

    public void Apply_Filters(int select0to3) {
        Select filters_Dropdown = new Select(filter_dropdown);
        filters_Dropdown.selectByIndex(select0to3);
    }

    public cart_Page Add_To_Cart(String productName) {
        product_add_to_cart = get_Product_Name(productName);
        product_add_to_cart.findElement(addtocart).click();
        cart_Page cart_page = new cart_Page(driver); //TODO - Next Page Instance
        return cart_page;
    }
}
