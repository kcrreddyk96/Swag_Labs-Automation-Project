package project_test.test_cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import project_main.page_objects.*;
import project_test.test_components.base_test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class order_work_flow extends base_test {

    home_Page home_page;

    cart_Page cart_page;

    checkout_Page checkout_page;

    checkout_Overview_Page checkout_overview_page;

    order_Confirmation_Page order_confirmation_page;

    List<HashMap<String, String>> data;

    String Page_Title = "Checkout: Complete!";

    String Message = "Thank you for your order!";


    @Test(dataProvider = "getData", groups = {"Sanity", "Smoke"})
    public void order_Work_Flow(HashMap<String, String>input){
        home_page = login_page.Login_details(input.get("user_Name"), input.get("Password"));
        home_page.Apply_Filters(1);
        home_page.get_Product_Name(input.get("productName"));
        cart_page = home_page.Add_To_Cart(input.get("productName"));
        home_page.goto_Shopping_Cart();
        cart_page.verify_Product_Displayed(input.get("productName"));
        checkout_page = cart_page.goto_Checkout_Page();
        checkout_page.sending_Checkout_info(input.get("F_name"), input.get("L_name"), input.get("zip_Code"));
        checkout_overview_page = checkout_page.set_Next_continue();
        checkout_overview_page.verify_Product_Displayed(input.get("productName"));
        order_confirmation_page = checkout_overview_page.finish_Payments();
        order_confirmation_page.get_Page_Title(Page_Title);
        order_confirmation_page.verify_Confirmation_Message(Message);
        order_confirmation_page.go_Back_Home();
        order_confirmation_page.set_Burger_menu_open();
        order_confirmation_page.set_Logout();

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        data = get_Json_Data_To_Map(System.getProperty("user.dir") + "//src//test//java//project_test//data_sets//data_sets.json");
        return new Object[][]{{data.get(0)}};
    }

}