package project_main.abstract_components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class abstract_components {
    WebDriver driver;

    public abstract_components(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".shopping_cart_link")
    WebElement shopping_cart;

    @FindBy(css = "#react-burger-menu-btn")
    WebElement burger_menu;

    @FindBy(css = "#react-burger-cross-btn")
    WebElement burger_close;

    @FindBy(xpath = "//a[contains(text(),'All Items')]")
    WebElement all_items;

    @FindBy(xpath = "//a[contains(text(),'About') or @id='about_sidebar_link']")
    WebElement about;

    @FindBy(linkText = "Logout")
    WebElement logout;

    @FindBy(xpath = "//a[contains(text(),'Reset App State') and @tabindex='0']")
    WebElement reset_app_state;

    public void wait_for_Element_to_Appear(By findBy) { // TODO - Visibility of Web Element using BY Locator
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void wait_for_Element_to_Appear(WebElement findBy) { // TODO - Visibility of Web Element using WebElement Locator
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void wait_for_Element_to_Disappear(WebElement element) { // TODO - invisibility of Web Element using WebElement Locator
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    //TODO - Creating Static Element Methods
    public void goto_Shopping_Cart() {
        shopping_cart.click();
    }

    public void set_Burger_menu_open() {
        burger_menu.click();
    }

    public void set_Burger_close() {
        burger_close.click();
    }

    public void goto_All_Items() {
        all_items.click();
    }

    public void goto_About_Page() {
        about.click();
    }

    public void set_Logout() {
        logout.click();
    }

    public void set_Reset_App_State() {
        reset_app_state.click();
    }
}
