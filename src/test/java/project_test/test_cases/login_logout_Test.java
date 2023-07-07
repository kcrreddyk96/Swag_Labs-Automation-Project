package project_test.test_cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import project_main.page_objects.home_Page;
import project_test.test_components.base_test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class login_logout_Test extends base_test {


    home_Page home_page;
    List<HashMap<String,String>> data;

    @Test(dataProvider ="getData", groups = {"Smoke", "Sanity"})
    public void login_Test(HashMap<String, String>input){
        home_page = login_page.Login_details(input.get("user_Name"),input.get("Password"));
        home_page.set_Burger_menu_open();
        home_page.set_Logout();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        data = get_Json_Data_To_Map(System.getProperty("user.dir") + "//src//test//java//project_test//data_sets//data_sets.json");
        return new Object[][] {{data.get(0)}};
    }
}
