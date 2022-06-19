package api;

import io.restassured.response.Response;
import org.json.JSONObject;
import utils.LogUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApiUtils extends LogUtils {

    protected static Response response;
    protected static JSONObject body;
    protected static String uri;
    protected static Map<String, String> headers = new HashMap<>();
    protected static Map<String, String> params = new HashMap<>();
    protected static String token;

    public void log(String verbo) {

        logInfo("******* Dados enviados no request *********");
        logInfo(verbo + " " + uri);
        logInfo("Body : \n" + body);
        logInfo("Headers :" + headers);
        logInfo("Params :" + params);

        logInfo("******* Dados Recebidos ************");
        logInfo("Status code :" + response.statusCode());
        logInfo("Payload recebido: \n" + response.asPrettyString());
        logInfo("Tempo de resposta: " + response.timeIn(TimeUnit.MILLISECONDS));

    }
}
