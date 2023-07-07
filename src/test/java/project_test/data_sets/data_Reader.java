package project_test.data_sets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class data_Reader {
    String Json_Content;

    ObjectMapper objectMapper = new ObjectMapper();

    public List<HashMap<String, String>> get_Json_Data_To_Map() throws IOException {
        // TODO - Read Json file to String
        Json_Content = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "src//test//java//project_test//data_sets//data_sets.json"));

        // TODO - String to HashMap by using Jackson Databind
        List<HashMap<String, String>> data = objectMapper.readValue(Json_Content, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data; // TODO - Returns data as {Map},{Map}
    }
}
