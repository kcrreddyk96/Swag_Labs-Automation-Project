package project_test.test_cases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import project_main.page_objects.home_Page;
import project_main.page_objects.login_Page;
import project_test.test_components.base_test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class negative_Testing extends base_test {
    home_Page home_page;

    String wrong_username_error_message = "Epic sadface: Username and password do not match any user in this service";

    String locked_user_error_message = "Epic sadface: Sorry, this user has been locked out.";

    List<HashMap<String, String>> data;

    @Test(dataProvider = "getData", groups = {"login_Test", "Smoke"})
    public void locked_User_Login_Test(HashMap<String, String> input) {
        home_page = login_page.Login_details(input.get("user_Name"), input.get("Password"));
        System.out.println(login_page.locked_Error_Message());
        Assert.assertEquals(locked_user_error_message,login_page.locked_Error_Message());
    }

    @Test(dataProvider = "getData_Invalid", groups = {"login_Test"})
    public void invalid_Login_Details_Test(HashMap<String, String> input) {
        home_page = login_page.Login_details(input.get("user_Name"), input.get("Password"));
        System.out.println(login_page.wrong_Username_Message());
        Assert.assertEquals(wrong_username_error_message,login_page.wrong_Username_Message());
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        data = get_Json_Data_To_Map(System.getProperty("user.dir") + "//src//test//java//project_test//data_sets//data_sets.json");
        return new Object[][]{{data.get(2)}};
    }

    @DataProvider
    public Object[][] getData_Invalid() throws IOException {
        data = get_Json_Data_To_Map(System.getProperty("user.dir") + "//src//test//java//project_test//data_sets//data_sets.json");
        return new Object[][]{{data.get(1)}};
    }
}
