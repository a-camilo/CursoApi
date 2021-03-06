package steps;

import api.ApiHeaders;
import api.ApiRequest;
import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.json.JSONException;
import user.Users;
import user.UsersGetterSetter;
import utils.JsonUtils;
import utils.PropertiesUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GorestStep extends ApiRequest {

    PropertiesUtils prop = new PropertiesUtils();
    JsonUtils jsonUtils = new JsonUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    Faker faker = new Faker();
    Map<String, String> jsonValues = new HashMap<>();

    @Dado("que possuo gorest token valido")
    public void quePossuoGorestTokenValido() {
        token = prop.getProp("token_gorest");
    }

    @Quando("enviar um request de cadastro de usuario com dados validos")
    public void enviarUmRequestDeCadastroDeUsuarioComDadosValidos() throws IOException, JSONException {
        uri = prop.getProp("uri_gorest");
        headers = apiHeaders.gorestHeaders(token);
        /*  body = jsonUtils.setJsonValues(jsonUtils.parseJSONFile("create_user"),setJsonValues());*/
        //body = new Users(faker.internet().emailAddress(),"Male",
        //        faker.name().fullName(),
        //       "Active").getjson();

        UsersGetterSetter user = new UsersGetterSetter();
        user.setEmail(faker.internet().emailAddress());
        user.setStatus("Active");
        user.setGender("female");
        user.setName(faker.name().fullName());

        body = user.getjson();

        POST();
    }

    /*
        private Map<String, String> setJsonValues() {
            jsonValues.put("email",faker.internet().emailAddress());
            jsonValues.put("name",faker.name().fullName());
            jsonValues.put("gender","Female");
            jsonValues.put("status","Inactive");
            return jsonValues;
        }
    */
    @Entao("o usuario deve ser criado corretamente")
    public void oUsuarioDeveSerCriadoCorretamente() throws JSONException {
        assertEquals(body.getString("email"), response.jsonPath().getString("data.email"));
        assertEquals(body.getString("name"), response.jsonPath().getString("data.name"));
    }

    @E("o status code do request deve ser {int}")
    public void oStatusCodeDoRequestDeveSer(Integer statusCode) {
        assertEquals(statusCode, response.statusCode(), "Status code diferente do esperado");
    }
}
