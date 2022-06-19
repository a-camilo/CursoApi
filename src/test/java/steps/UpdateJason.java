package steps;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import utils.JsonUtils;

import java.io.IOException;

public class UpdateJason {

    JsonUtils jsonUtils = new JsonUtils();

    @Test
    public void updateJason() throws IOException {

       JSONObject js =  jsonUtils.parseJSONFile("update");
        System.out.println(jsonUtils.updateJson(js,"type","10"));


    }
}
