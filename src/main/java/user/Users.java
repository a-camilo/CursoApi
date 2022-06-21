package user;

import com.google.gson.Gson;
import org.json.JSONObject;

public class Users {

    private String email;
    private String gender;
    private String name;
    private String status;

    public Users(String email, String gender, String name, String status) {
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.status = status;
    }

    public JSONObject getjson() {
        return new JSONObject(new Gson().toJson(this));
    }
}
